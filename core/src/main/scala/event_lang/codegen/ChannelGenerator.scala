package event_lang.codegen

import event_lang._
import event_lang.intern.logging.Logger
import event_lang.types.LocalTypes._
import event_lang.types.{Label, RRole, Role, RoleSet}
import intern.parser.ProtocolParser.parseString

import scala.collection.mutable


/**
 * todo: the code contains some hashcode hacks - and case classes with vars
 *
 *
 * Fixme: if we have mu ta -> b{ l1: ... t, l2: ... t} we only generate one t node
 * not two different ts. That enables writing an handler that should not be allowed
 * Issue:
 * mu t a -> b { l1 : a-> c l3. t, l2: a-> c l4 t}
 *
 * register(a.sndL1, a.sndL3) ... b!(l2).b!(l4)
 *
 * is valid since both b!l4 and b!l3 have as their next node t
 */
object ChannelGenerator extends Logger {
  val QUOTE = "\""

  def withQuotes(str: String): String = QUOTE + str + QUOTE

  def apply(eps: AllEndPointTypes): CodeGenManager = {

    var msgClasses = Map[String, Set[Label]]()
    var epTypes = Map[RoleSet, Set[CGSubSession]]()
    implicit var subReg = Map[String, CGSubSession]()
    for ((rs, ltop) <- eps.ep) {
      implicit val lt = ltop
      var subCG = Set[CGSubSession]()
      for (sub <- ltop.subSession) {
        subReg += sub.name -> CGSubSession(sub.name, sub.prjTo, sub.argsC, sub.argsP, sub.argsRs, sub.rootRole, null, null)
      }
      for (sub <- ltop.subSession) {
        val cxt = GenerateCxt(Set(), PClass(s"EP${sub.name}_${sub.prjTo.pName}"),
          rootPClass = PClass(s"__EPType_${rs.rs}"), branchChain = List(),
          subName = sub.name,
          rec = Map(),
          role = sub.prjTo)
        val s = CGSubSession(sub.name, sub.prjTo, sub.argsC, sub.argsP, sub.argsRs, sub.rootRole,
          generateSubSessionCode(sub.body, cxt), cxt)
        msgClasses += sub.name -> (msgClasses.getOrElse(sub.name, Set()) ++ s.labels())
        subReg += sub.name -> s
        subCG += s
      }
      epTypes += (rs -> subCG)
    }
    CodeGenManager(eps.protocolName, msgClasses, epTypes)
  }


  private def generateSubSessionCode(l: LocalType, cxt: GenerateCxt)
                                    (implicit ltop: LTop,
                                     subReg: Map[String, CGSubSession]
                                    ): CodeGenNode = {
    trace(s"[generateSubSessionCode] LT: $l") // - CXT: $cxt")
    import types.LocalTypes._
    l match {
      case LSnd(to, con) =>
        con.keys.foreach(
          m => assert(!cxt.chNames.contains(m.ccName), s"$m is already present in ${cxt.chNames}"))
        val nCxt = con.keys.foldLeft(cxt)((c, l) => c.copy(chNames = c.chNames + l.ccName))
        val cName = CName("Sel" + con.keys.map(_.ccName).mkString(""))
        if (con.size > 1) {
          CGSelect(to, cName, cxt, con.map { case (lbl, lt) => {
            (lbl, CGSnd(to, CName("Snd" + lbl.ccName), cxt, lbl,
              generateSubSessionCode(lt, nCxt.copy(
                branchChain = lbl.ccName :: nCxt.branchChain))))
          }
          })
        } else {
          CGSnd(to, CName("Snd" + con.keys.head.ccName), cxt, con.keys.head,
            generateSubSessionCode(con.values.head, nCxt))
        }
      case LSpawn(cname, argsc, argp, argsRs, con) =>
        CGSpawn(cName = CName("spawn" + cname), subReg(cname), cxt = cxt, y = argsc,
          pickR = argp /*ltop.subSession.find(_.name == cname).get.argsP*/ , rs = argsRs,
          con = generateSubSessionCode(con, cxt))
      case LHdl(d, f) => CGHdl(
        generateSubSessionCode(d, cxt.copy(branchChain = cxt.branchChain)),
        generateSubSessionCode(f, cxt.copy(branchChain = "FHandling" :: cxt.branchChain))
        , cxt)
      case LRcv(frm, con) =>
        con.keys.foreach(m => assert(!cxt.chNames.contains(m.ccName), s"$m contained twice"))
        val nCxt = con.keys.foldLeft(cxt)((c, l) => c.copy(chNames = c.chNames + l.ccName))
        val cName = CName("Sel" + con.keys.map(_.ccName).mkString(""))
        if (con.size > 1) {
          CGBranch(frm, cName, cxt, con.map { case (lbl, lt) => {
            (lbl, CGRcv(frm, CName("rcv" + lbl.ccName), cxt, lbl,
              generateSubSessionCode(lt, nCxt.copy(
                branchChain = lbl.ccName :: nCxt.branchChain))))
          }
          })
        } else {
          CGRcv(frm, CName("Rcv" + con.keys.head.ccName), cxt, con.keys.head,
            generateSubSessionCode(con.values.head, nCxt))
        }
      case LEnd() =>
        CGEnd(CName("End_" + s"${cxt.role.pName}_" + cxt.subName + cxt.branchChain.mkString("_")), cxt)

      case LRec(t, lt) =>
        val r = CGRec(t, cxt, 0, null)
        val cxtWRec = cxt.copy(rec = cxt.rec + (t -> r))
        r.body = generateSubSessionCode(lt, cxtWRec)
        r
      case t@LRecT(_) =>
        val mutT = cxt.rec(t)
        mutT.tCount += 1
        CGRecT(t, cxt, /* mutT,*/ mutT.tCount)

      case LFDetection(p, con) =>
        CGFDetection(p, generateSubSessionCode(con, cxt), cxt = cxt)
      case LMerge(options) =>
        CGMerge(
          CName("Merge_" + options.map({
            case l: LRcv => l.con.head._1.ccName
            case l: LSpawn => l.c
          }).mkString("_")),
          options.map(o => {
            generateSubSessionCode(o, cxt.copy(branchChain = {
              o match {
                case l: LRcv => l.con.head._1.ccName
                case l: LSpawn => l.c
              }
            } :: cxt.branchChain))
          }), cxt)
    }
  }

  case class GenerateCxt(
                          chNames: Set[String],
                          pClass: PClass,
                          rootPClass: PClass,
                          branchChain: List[String],
                          subName: String,
                          rec: Map[LRecT, CGRec],
                          role: RRole) {
    def lblFullName(l: Label): String = s"MESSAGES.$subName.${l.ccName}"
  }

  private def codeTrait(name: String, body: String*): String = {
    s"trait $name {\n" + body.mkString("\n") + "\n}\n"
  }

  private def codeObject(name: String, body: String*): String = {
    s"object $name {\n" + body.mkString("\n") + "\n}\n"
  }

  private def codeObject(name: String, body: Iterable[String]): String = {
    s"object $name {\n" + body.mkString("\n") + "\n}\n"
  }

  private def codeTrait(name: String, body: Iterable[String]): String = {
    s"trait $name {\n" + body.mkString("\n") + "\n}\n"
  }

  private def codeCaseClassFrmLbl(l: Label): String = {
    s"case class ${
      l.ccName
    }(${
      l.types.map(t => t._1 + ":" + t._2).mkString(",")
    }) extends MSG {\n " +
      s"  override def l : String = $QUOTE${
        l.ccName
      }$QUOTE\n" +
      s"}\n"
  }


  case class CodeGenManager(protocolName: String, msgClasses: Map[String, Set[Label]],
                            epTypes: Map[RoleSet, Set[CGSubSession]]) {

    case class ProtocolArgs(argsC: List[Role],
                            argsP: Role,
                            argsRs: List[RoleSet]
                           )

    private val subsToRoles = {
      val acc = mutable.Map[String, ProtocolArgs]()
      for (subs <- epTypes.values;
           sub <- subs) {
        val args = ProtocolArgs(sub.argsC, sub.argsP, sub.argsRs)
        if (!acc.contains(sub.name)) {
          acc.+=(sub.name -> args)
        } else {
          assert(acc(sub.name) == args)
        }
      }
      acc
    }

    def channelCode(): String = {


      val createSubList = (subS: Set[CGSubSession]) => {
        s"val subs : Seq[dsl.ChannelTypeSubS] = List(" + subS.map(
          s => s.sObj + "." + s.cName.tName).mkString(
          ",") + ")"
      }
      "import event_lang._ \n" +
        "import event_lang.dsl.{AbstractChannel, AbstractChannelImp, AbstractChannelType, AbstractEndPoint, EndPointType,Session,TState}\n" +
        "import event_lang.types.{MSG, Role, RoleSet,RRole}\n" +
        "/* ########################################## \n" +
        " * ###### GENERATED CODE - DO NOT EDIT ######\n" +
        " * ##########################################\n" +
        "*/\n" +
        "\n\n\n" +
        // Generate the MESSAGES
        codeObject(protocolName,
          codeObject("RS",
            epTypes.map { case (rs, _) => {
              s"val ${rs.rs} : RoleSet = RoleSet(${withQuotes(rs.rs)})"
            }
            }
          ) ::
            codeObject("MESSAGES",
              msgClasses.map { case (ssN, lbls) => {
                codeObject(ssN, lbls.map(codeCaseClassFrmLbl))
              }
              }
            ) ::
            codeObject("PROTOCOLS",
              subsToRoles.map({
                case (subName, args) =>
                  codeObject(subName,
                    s"val ${args.argsP.pName} = ${args.argsP.toString}"
                      ::
                      args.argsRs.map(rs =>
                        s"val ${rs.pName} = " +
                          s"${rs.toString}")
                        ++
                        args.argsC.map(r =>
                          s"val ${r.pName} = " +
                            s"${r.toString}")
                  )
              })
            ) ::
            // Generate End point channels
            epTypes.map { case (rs, subS) =>
              //code for ONE end point
              codeObject(rs.pName,
                createSubList(subS) ::
                  codeTrait(s"__EPType_${rs.rs} extends AbstractChannelType"
                  ) ::
                  codeTrait(s"EPType_${rs.rs}[T<: TState] extends AbstractEndPoint[__EPType_${rs.rs},T]",
                    s"override val roleSet: RoleSet = ${rs}\n  " +
                      s"override ${createSubList(subS)}\n") ::
                  subS.map(_.generateAll()).toList
              )
            }.toList
        )
    }
  }

  trait CodeGenNode {
    val isPublic = false

    //FIXME: The CodeGen Graph Traversal
    /*
      The T node contains the RecT Node as a child (with that we have a cycle)
      The current traversal just goes into every child unless i manual override all method which
      travers (that is
      error prun)
      Add a method with controls the traversal??
     */

    def childNodes: List[CodeGenNode]


    def labels(): Set[Label] = childNodes.foldLeft(_labels)((acc, v) => acc ++ v.labels())

    def _labels: Set[Label]

    val cName: CName
    val cxt: GenerateCxt

    def generateAll(): String = {
      generateThis() + childNodes.map(_.generateAll()).mkString("\n")
    }

    def nodeKind(): String

    def visMod(): String = {
      if (!isPublic) "protected "
      else " "
    }

    def generateThis(): String = {
      s"\n${visMod()} trait ${cName.tName} extends ${cxt.pClass.n} with ${nodeKind()}\n" +
        s"${visMod()} object ${cName.oName} extends ${cName.tName} {\n" +
        s"  override protected def __children: List[${cxt.pClass.n}] = List(${
          childNodes.map(_.cName.tName).mkString(",")
        })\n" +
        s"  override type implT = ${cName.iName}\n" +
        //todo revise the impl of implNextT
        s"  override type implNextT = ${
          if (childNodes.nonEmpty) childNodes.head.cName.iName else
            "Nothing"
        }\n" +
        s"""override def toString() : String = {"${generateToString()}"}\n""" +
        s"  ${generateInObj()}\n" +
        s"  override protected def __create(c : AbstractChannel, session : Session) : implT = ${cName.iName}(c,session)" +
        s"}\n\n" +
        s"protected case class ${cName.iName}(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {\n" + //with
        // ${nodeKind()}{\n" +
        s"  override def from : AbstractChannelType = {\n" +
        s"    ${cName.oName}" +
        s"\n}\n" +
        //s"  override def children: List[${cxt.rootPClass.n}] = ???\n" +
        s"  ${generateInImpl()}" +
        s"\n}\n\n"
    }

    def generateInImpl(): String = ""

    def generateInObj(): String = ""

    def generateToString(): String = s"${cxt.pClass.n}.${cName.tName}"
  }

  case class CName(n: String) {
    def tName = n.capitalize

    def oName: String = tName.capitalize

    def iName: String = "__" + oName.capitalize + "Imp"
  }

  case class PClass(n: String) {

  }

  //  case class MSG(m: String) {
  //    def tMSG: String = m
  //  }
  case class Dummy(cxt: GenerateCxt) extends CodeGenNode {
    override def childNodes: List[CodeGenNode] = List()

    override val cName: CName = CName("DUMMY")

    override def generateThis(): String = "\n //DUMMY \n"

    override def _labels: Set[Label] = Set()

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeDUMMY"
  }

  case class CGSubSession(name: String, r: RRole, argsC: List[Role],
                          argsP: Role,
                          argsRs: List[RoleSet],
                          rootRole: Role,
                          body: CodeGenNode, cxt: GenerateCxt) extends CodeGenNode {
    override def childNodes: List[CodeGenNode] = List(body)

    override def _labels: Set[Label] = Set()

    override val cName: CName = CName(s"EP${name}_${r.pName}")
    val sObj = s"${name}_${r.pName}"

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeSubS"


    override def hashCode(): Int = scala.runtime.ScalaRunTime._hashCode((name, r, argsC, argsP, argsRs, rootRole))

    override def equals(obj: Any): Boolean = {
      if (obj == null) false
      else obj match {
        case o: CGSubSession =>
          o.name == name && o.r == r && o.argsC == argsC && o.argsP == argsP && o.argsRs == argsRs && o.rootRole == rootRole
        case _ => false
      }
    }

    override def generateAll(): String = {
      s"object $sObj{\n" +
        s"trait ${cName.tName} extends ${cxt.rootPClass.n}\n\n" +
        //s"\ntrait ${cName.tName} extends ${cxt.pClass.n} with ${nodeKind()}\n" +
        s"object ${cName.oName} extends ${cName.tName} with ${nodeKind()} {\n" +
        s"  override protected def __children: List[${cxt.pClass.n}] = List(${
          childNodes.map(_.cName.tName).mkString(",")
        })\n" +
        s"  override type implT = ${cName.iName} \n" +
        s"  ${generateInObj()}\n" +
        s"  override def __create(c : AbstractChannel, session : Session) : implT = ${cName.iName}(c,session) \n" +
        s"  override def body: AbstractChannelType = children.head\n" +
        s"  override def argsC: List[Role] = $argsC \n" +
        s"  override def argsP: Role = $argsP \n" +
        s"  override def argsRs: List[RoleSet] = $argsRs \n" +
        s"  override def prjTo : RRole = $r \n" +
        s"  override def rootRole: Role = $rootRole \n" +
        s"  override def name : String = " + "\"" + name + "\"\n" +
        s"}\n\n" +
        s"protected case class ${cName.iName}(private val c : AbstractChannel, session : Session) extends AbstractChannelImp {\n" +
        s"  override def from : AbstractChannelType = {\n" +
        s"    EP${name}_${r.pName}" +
        s"\n}\n" +
        //s"  override def children: List[${cxt.rootPClass.n}] = ???\n" +
        s"  ${generateInImpl()}" +
        s"\n}\n\n" +
        childNodes.map(_.generateAll()).mkString("\n") +
        "\n}\n"
    }
  }

  case class CGSelect(to: RRole, cName: CName, cxt: GenerateCxt, con: Map[Label, CodeGenNode])
    extends CodeGenNode {
    override def generateInImpl(): String = {
      s"private var notUsed = true\n" +
      con.map { case (k, v: CGSnd) =>
        s"def !(m : ${cxt.lblFullName(k)}) : ${v.con.cName.iName} = " +
          s"{" +
          "\nassert(notUsed, s\"The channel send musted be used linear\")"+
          s"\n notUsed=false\n" +
          s"c.snd($to,m)\n ${v.con.cName.iName}(c,session)}\n" +
          s"def sndTo${to.pName}(m : ${cxt.lblFullName(k)}) : " +
          s"${v.con.cName.iName} = {" +
          "\nassert(notUsed, s\"The channel send musted be used linear\")"+
          s"\n notUsed=false\n" +
          s"c.snd($to,m)\n ${v.con.cName.iName}(c,session)}\n"
      case _  =>
          assert(false,s"The con of a CGSelectNodes must be CGSnds $this")
          ???
      }.mkString("\n")
    }

    override def _labels: Set[Label] = con.keySet

    override def childNodes: List[CodeGenNode] = con.values.toList

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeSel"
  }

  case class CGSnd(to: RRole, cName: CName, cxt: GenerateCxt, lbl: Label, con: CodeGenNode)
    extends CodeGenNode {
    override val isPublic = true

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeSnd"

    override def _labels: Set[Label] = Set(lbl)


    override def generateInObj(): String = {
      s"  override def to : RRole = $to \n " +
        s"  override def l : String = $QUOTE${lbl.ccName}$QUOTE \n"
    }

    override def generateInImpl(): String = {
      s"private var notUsed = true\n" +
        s"def sndTo${to.pName}(m : ${cxt.lblFullName(lbl)}) : ${con.cName.iName} = {" +
        "\nassert(notUsed, s\"The channel send musted be used linear\")"+
        s"\n notUsed=false\n" +
        s"c.snd($to,m)\n" +
        s"${con.cName.iName}(c,session)}\n" +
        s"def !(m : ${cxt.lblFullName(lbl)}) : ${con.cName.iName} = {" +
        "\nassert(notUsed, s\"The channel send musted be used linear\")"+
        s"\n notUsed=false\n" +
        s"c.snd($to,m)\n" +
        s"${con.cName.iName}(c,session)}\n" +
        s"def snd(m : ${cxt.lblFullName(lbl)}) : ${con.cName.iName} = {" +
        "\nassert(notUsed, s\"The channel send musted be used linear\")"+
        s"\n notUsed=false\n" +
        s"c.snd($to,m)\n" +
        s"${con.cName.iName}(c,session)}\n"
    }

    override def childNodes: List[CodeGenNode] = List(con)
  }

  case class CGHdl(dBlock: CodeGenNode, fBlock: CodeGenNode, cxt: GenerateCxt,
                   cName: CName = CName("Hdl")) extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeHdl"

    override def _labels: Set[Label] = Set()

    override def childNodes: List[CodeGenNode] = List(dBlock, fBlock)

    //override def generateThis(): String = s"\n// NO CODE FOR HDL NODES \n"
  }

  case class CGSpawn(cName: CName, subSession: CGSubSession, cxt: GenerateCxt, y: List[Role],
                     pickR: RoleSet,
                     rs: List[RoleSet],
                     con: CodeGenNode) extends CodeGenNode {
    override val isPublic = true

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeSpawn"

    override def _labels: Set[Label] = Set()

    override def childNodes: List[CodeGenNode] = List(con)


    override def generateInObj(): String = {
      s"  override def y: List[Role] = $y \n" +
        s"  override def pickR: RoleSet = $pickR \n" +
        //s"  override def pickR: Role = $pickR \n" +
        s"  override def rs: List[RoleSet] = $rs \n" +
        s"  override def name: String = ${'"'}${subSession.name}${'"'} \n" +
        s"  override def subC(r : RRole) : event_lang.dsl.ChannelTypeSubS = {" +
        s"  null" +
        s"   }"
    }

    override def generateInImpl(): String = s"// SPAWN is handled internally -- i.e. no use code " +
      s"here"
  }

  case class CGBranch(frm: Role, cName: CName, cxt: GenerateCxt, con: Map[Label, CodeGenNode])
    extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeBrn"

    override def _labels: Set[Label] = con.keySet

    override def childNodes: List[CodeGenNode] = con.values.toList


    override def generateInImpl(): String = s"// Branching is only a valid return type not a " +
      s"valid input type"
  }


  case class CGMerge(cName: CName, options: List[CodeGenNode], cxt: GenerateCxt) extends
    CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeMerge"

    override def childNodes: List[CodeGenNode] = options

    override def _labels: Set[Label] = Set()


    //override def generateThis(): String = ""
  }

  case class CGRcv(frm: Role, cName: CName, cxt: GenerateCxt, lbl: Label, con: CodeGenNode)
    extends CodeGenNode {
    override val isPublic = true

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeRcv"

    override def _labels: Set[Label] = Set(lbl)

    override def childNodes: List[CodeGenNode] = List(con)


    override def generateInObj(): String = {
      s"override type msgT = ${cxt.lblFullName(lbl)}\n " +
        s"  override def frm : Role = $frm \n " +
        s"  override def l : String = $QUOTE${lbl.ccName}$QUOTE"
    }

    override def generateInImpl(): String =
      s"def rcvFrm${frm.pName} : (${cxt.lblFullName(lbl)},${con.cName.iName}) = {" +
        s"(c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}],${con.cName.iName}(c,session))" +
        s"}\n" +
        s"def ?[T](f : PartialFunction[(${cxt.lblFullName(lbl)},${con.cName.iName}),T]) : T = {\n" +
        s"  f((c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}],${con.cName.iName}(c,session))) \n" +
        s"}\n" +
        //        s"(c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}],${con.cName.iName}(c,session))" +
        //        s"}\n" +
        //        s"def ? : (${cxt.lblFullName(lbl)},${con.cName.iName}) = {" +
        //        s"(c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}],${con.cName.iName}(c,session))" +
        //        s"}\n" +
        s"def rcvMSG : ${cxt.lblFullName(lbl)} = {" +
        s"c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}]" +
        s"}\n" +
        s"def ? : ${cxt.lblFullName(lbl)} = {" +
        s"c.rcv($frm).asInstanceOf[${cxt.lblFullName(lbl)}]" +
        s"}\n" +
        s"def channelCon : ${con.cName.iName} = {" +
        s"${con.cName.iName}(c,session)" +
        s"}\n"
  }

  case class CGEnd(cName: CName, cxt: GenerateCxt) extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeEnd"

    override def childNodes: List[CodeGenNode] = List()

    override def _labels: Set[Label] = Set()
  }

  case class CGRec(t: LRecT, cxt: GenerateCxt, var tCount: Int = 0, var body: CodeGenNode)
    extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeRec"

    override def childNodes: List[CodeGenNode] = List(body)

    override def _labels: Set[Label] = Set()

    override val cName: CName = CName("Rec" + t.symbol.capitalize)

    override def toString: String = {
      assert(false)
      ""
    }
  }

  case class CGRecTBuilder(t: LRecT, cxt: GenerateCxt, mut: CodeGenNode) extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeT"

    override def childNodes: List[CodeGenNode] = List(mut)

    override def labels(): Set[Label] = Set()

    override def _labels: Set[Label] = Set()

    override def generateAll(): String = {
      generateThis()
    }

    override val cName: CName = CName(t.symbol)
  }


  case class CGRecT(t: LRecT, cxt: GenerateCxt /*, mut: CodeGenNode*/ , tC: Int) extends CodeGenNode {
    override def nodeKind(): String = "event_lang.dsl.ChannelTypeT"

    override def childNodes: List[CodeGenNode] = List(cxt.rec(t)) //List(mut)

    override def labels(): Set[Label] = Set()

    override def _labels: Set[Label] = Set()

    override def generateAll(): String = {
      if (tC == 1)
        generateThis()
      else
        "//there was an occurens of t already"
    }


    override val cName: CName = CName(t.symbol)
  }

  case class CGFDetection(who: Role, body: CodeGenNode, cxt: GenerateCxt) extends CodeGenNode {

    override val isPublic = true

    override def nodeKind(): String = "event_lang.dsl.ChannelTypeFDtct"

    override def childNodes: List[CodeGenNode] = List(body)

    override def _labels: Set[Label] = Set()

    override val cName: CName = CName(s"failed_${who.pName}")


    override def generateInObj(): String = s"override def suspect : Role = $who \n"

    //Todo: the monitoring action/failure activation (failed_role) is not doing a lot on the process level
    //That action is important on the type level, but only a nohup in the event handler reduction.
    //We could consider removing it from the even handler body implemenation.
    override def generateInImpl(): String = {
      s"def failed_${who.pName}(): ${body.cName.iName} = {" +
        s"${body.cName.iName}(c,session)}\n"
    }
  }


  //  case class LRcv(childNodes : List[CodeGenNode]) extends CodeGenNode
  //  case class LMerge(childNodes : List[CodeGenNode]) extends CodeGenNode
  //
  //  case class LFDetection(childNodes : List[CodeGenNode]) extends CodeGenNode
  //  case class LSpawn(c: String, y: List[Role], pickR: RoleSet, rs: List[RoleSet],
  //  con: CodeGenNode) extends
  //  CodeGenNode
  //
  //  case class LHdl(dBlock: CodeGenNode, fBlock: CodeGenNode) extends CodeGenNode
  //  case class LActvHdl(fBlock: CodeGenNode) extends CodeGenNode
  //  case class LRec(t: LRecT, l: CodeGenNode) extends CodeGenNode
  //  case class LRecT(symbol: String) extends CodeGenNode
  //  case class LEnd() extends CodeGenNode
  //  case class LSnd(to: RRole, con: Map[MSG, LocalType]) extends LocalType
  //  case class LRcv(frm: Role, con: Map[MSG, LocalType]) extends LocalType
  //  case class LMerge(options: List[LocalType]) extends LocalType
  //
  //  case class LFDetection(p: Role, con: LocalType) extends LocalType
  //  case class LSpawn(c: String, y: List[Role], pickR: RoleSet, rs: List[RoleSet],
  //  con: LocalType) extends LocalType
  //
  //  case class LHdl(dBlock: LocalType, fBlock: LocalType) extends LocalType
  //  case class LActvHdl(fBlock: LocalType) extends LocalType
  //  case class LRec(t: LRecT, l: LocalType) extends LocalType
  //  case class LRecT(symbol: String) extends LocalType
  //  case class LEnd() extends LocalType

}
