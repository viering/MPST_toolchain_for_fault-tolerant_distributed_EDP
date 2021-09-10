package event_lang.types

import event_lang.types.GlobalTypes._
import event_lang.types.LocalTypes._
import event_lang.types.Projection.prjToCRole

object Projection {


  def checkWF(gt: GTop): Boolean = {


    def checkSub(aSubP: SubSession) {
      def checkSpawn(g: GType): Unit = {
        g match {
          case Spawn(name, argsC, pickR, rs, con) =>
            val sp = gt.subS.find(sp => sp.name == name &&
              argsC.size == sp.argsC.size&&
              argsC.zip(sp.argsC).forall(rr => rr._1.rs == rr._2.rs) &&
              rs.size == sp.argsRs.size &&
              rs.zip(sp.argsRs).forall(RR => RR._1 == RR._2) &&
              pickR == sp.argsP.rs)
            assert(sp.nonEmpty, s"Missing matching SubP for $g in ${gt.subS}")
            checkSpawn(con)
          case _ =>
            g.children.foreach(g => checkSpawn(g))
        }
      }

      // states: 0 => HDl, 1 => FDect, 2 => Notification
      def check(g: GType, rs: Set[Role], Rs: Set[RoleSet], state: Int = 0, monitor: Option[Role] = None): Unit = {
        g match {
          case _ if rs.isEmpty && Rs.isEmpty && state == 2 =>
          case Hdl(dBlock, fBlock) if state == 0 =>
            assert(dBlock.labels.intersect(fBlock.labels).isEmpty, s"DBlock and FBlock must have disjoint labels, violated by $g")
            check(fBlock, rs, Rs, 1)
          case FDetection(fP, monitorP, con) if state == 1 =>
            assert(fP == aSubP.argsP, s"The picked role must be monitored, not the case in $aSubP")
            assert(!con.allRoles.contains(fP), s"Monitored role should not be inside failure handling, not the case in $aSubP")
            check(con, rs - monitorP, Rs, 2, Some(monitorP))
          case Interaction(frm, to, con) if state == 2 && monitor.nonEmpty =>
            assert(frm == monitor.get, s"The monitor still needs to inform ppl, violated in\n $aSubP @\n $g")
            to match {
              case r@Role(toR, toRs) =>
                assert(rs.contains(r), s"$r is not waiting for a notification, violated in $aSubP @ $g")
                con.foreach(lg => {
                  check(lg._2, rs.-(r), Rs, state, monitor)
                })
              case R@RoleSet(_) =>
                assert(Rs.contains(R), s"$R is not waiting for a notification, violated in $aSubP @ $g")
                val new_rs = rs.filter(r => r.rs != R)
                con.foreach(lg => {
                  check(lg._2, new_rs, Rs - R, state, monitor)
                })
            }

          case _ =>
            assert(false, s"We probably are missing notification message in $aSubP, at $g")
        }
      }

      check(aSubP.gType, aSubP.argsC.toSet, aSubP.argsRs.toSet)
      checkSpawn(aSubP)
    }

    for (g <- gt.subS) {
      checkSub(g)
    }

    true
  }

  def apply(gt: GTop, pName: String = "Dummy Protocol Name"): AllEndPointTypes = {
    checkWF(gt)
    AllEndPointTypes(pName,
      (gt.allRoleSets ++ gt.allRoles.map(_.rs)).foldLeft(Map[RoleSet, LTop]())((m, rs) =>
        m + (rs -> LTop(
          Projection(gt, rs)))
      )
    )
  }

  def apply(gtype: GTop, p: RoleSet): Set[LSubSession] = {
    val prj = gtype.subS.flatMap(ss => {
      val cPjr = (ss.argsP :: ss.argsC).flatMap {
        case r if r.rs == p =>
          Some(LSubSession(
            ss.name,
            ss.argsC,
            ss.argsP,
            ss.argsRs,
            prjToCRole(ss.gType, r)(r == ss.argsP),
            r,
            ss.rootRole.get
          ))
        case _ => None
      }
      val RPrj = ss.argsRs.flatMap({
        case r if r == p => {
          Some(LSubSession(
            ss.name,
            ss.argsC,
            ss.argsP,
            ss.argsRs,
            prjToRoleSet(ss.gType, p),
            p,
            ss.rootRole.get
          ))
        }
        case _ => None
      })
      cPjr ++ RPrj
    })
    prj
  }

  private def prjToRoleSet(l: GType, r: RoleSet): LocalType = {
    l match {
      // case: r' -> R' && R == r
      case Interaction(frm, to, con) if to == r =>
        LRcv(frm, con.map {
          case (k, v) => (k, prjToRoleSet(v, r))
        })
      // case r' -> R' && R' != r
      case in@Interaction(frm, to, con) =>
        if (con.size == 1) {
          prjToRoleSet(con.head._2, r)
        } else {


          val notSB = con.values.exists(b1 => con.values.exists(b2 => {
            prjToRoleSet(b1, r) != prjToRoleSet(b2, r)
          }))
          if (notSB) {
            val f = con.values.foldLeft(Set[LocalType]())((acc, n) => {
              prjToRoleSet(n, r) match {
                case merge: LMerge =>
                  acc ++ merge.options
                case l =>
                  acc + l
              }
            })
            val toRcvs = f.map(_.asInstanceOf[LRcv])
            assert(toRcvs.forall(rcv => rcv.frm == frm))
            assert(toRcvs.forall(rcv => toRcvs.forall(r => r == rcv || r.con.keySet.intersect(rcv.con.keySet).isEmpty)))
            LMerge(f.toList)
          } else {
            prjToRoleSet(con.head._2, r)
          }
        }

      case Hdl(dg, fg) =>
        val dBlock = if (dg.allRoleSets.contains(r)) prjToRoleSet(dg, r) else LEnd()
        val fBlock = if (fg.allRoleSets.contains(r)) prjToRoleSet(fg, r) else LEnd()
        LHdl(dBlock = dBlock, fBlock)

      case Spawn(c, y, pick, rs, con) if pick == r || rs.contains(r) => LSpawn(c, y, pick, rs, prjToRoleSet(con, r))
      case Spawn(c, y, pick, rs, con) => prjToRoleSet(con, r)
      // role set are never the detector of a failure
      case FDetection(fp, dp, con) => prjToRoleSet(con, r)
      case Rec(t, g) =>
        val l = prjToRoleSet(g, r)
        if (l.isInstanceOf[LRecT]) {
          return LEnd()
        }
        LRec(LRecT(t.symbol), l)
      case RecT(t) =>
        LRecT(t)
      case End() => LEnd()
    }
  }

  private def prjToCRole(l: GType, cp: Role)(implicit isPromRole: Boolean): LocalType = {
    l match {
      // case: frm -> to && frm == cp
      case Interaction(frm, to, con) if frm == cp =>
        LSnd(to, con.map {
          case (k, v) => (k, prjToCRole(v, cp))
        })
      case Interaction(frm, to, con) if to == cp || cp.rs == to =>
        LRcv(frm, con.map {
          case (k, v) => (k, prjToCRole(v, cp))
        })
      // case r' -> R' && R' != r
      case in@Interaction(frm, to, con) => //d
        val notSB = con.values.exists(b1 => con.values.exists(b2 => {
          prjToCRole(b1, cp) != prjToCRole(b2, cp)
        }))

        if (notSB) {
          val f = con.values.foldLeft(Set[LocalType]())((acc, n) => {
            prjToCRole(n, cp) match {
              case merge: LMerge =>
                acc ++ merge.options
              case l =>
                acc + l
            }
          })
          val toRcvs = f.map(l => {
            assert(l.isInstanceOf[LRcv], s"We can only merge rcv on different levels. Not the case for $in for $cp")
            l.asInstanceOf[LRcv]
          })
          assert(toRcvs.forall(rcv => rcv.frm == frm))
          assert(toRcvs.forall(rcv => toRcvs.forall(r => r == rcv || r.con.keySet.intersect(rcv.con.keySet).isEmpty)), s"Merge for $in for $cp not possible since labels are not distinct")
          //TODO we do not really need merge, should probably just be a receive
          LMerge(f.toList)
        } else {
          prjToCRole(con.values.head, cp)
        }
      case Hdl(dg, fg) if !isPromRole
      =>
        val dBlock = if (dg.allRoles.contains(cp) || dg.allRoleSets.contains(cp.rs)) prjToCRole(dg, cp) else LEnd()
        LHdl(dBlock, prjToCRole(fg, cp))
      case Hdl(dg, fg) if isPromRole
      =>
        LHdl(prjToCRole(dg, cp), LEnd())
      case Spawn(c, y, pick, rs, con)
        if y.contains(cp) || pick == cp.rs || rs.contains(cp.rs)
      => LSpawn(c, y, pick,
        rs,
        prjToCRole(con, cp))
      case Spawn(c, y, pick, rs, con)
      => prjToCRole(con, cp)
      // role set are never the detector of a failure
      case FDetection(fp, dp, con)
        if dp == cp
      => LFDetection(fp, prjToCRole(con, cp))
      case FDetection(fp, dp, con)
      => prjToCRole(con, cp)
      case Rec(t, g)
      =>
        val l = prjToCRole(g, cp)
        if (l.isInstanceOf[LRecT]) {
          return LEnd()
        }

        LRec(LRecT(t.symbol), l)
      case RecT(t)
      => LRecT(t)
      case End()
      => LEnd()
    }
  }
}
