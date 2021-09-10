package event_lang.intern.parser

import org.antlr.v4.runtime.tree.TerminalNode
import event_lang._
import event_lang.types.GlobalTypes._
import event_lang.types._
import event_lang.intern.logging.Logger

import scala.collection.JavaConverters._

class GTypeVisitor extends ProtocolGrammarBaseVisitor[GType] with Logger {

  var rToRs = Map[String, RoleSet]()
  var rs = Set[RoleSet]()

  override def visitRecID(ctx: ProtocolGrammarParser.RecIDContext): RecT = {
    RecT(getID(ctx.IDENTIFIER()))
  }

  override def visitEnd(ctx: ProtocolGrammarParser.EndContext): End = End()

  override def visitCName(ctx: ProtocolGrammarParser.CNameContext): GType = ???

  override def visitVName(ctx: ProtocolGrammarParser.VNameContext): GType = ???

  override def visitVType(ctx: ProtocolGrammarParser.VTypeContext): GType = ???

  override def visitSessionName(ctx: ProtocolGrammarParser.SessionNameContext): GType = super.visitSessionName(ctx)

  override def visitGTop(ctx: ProtocolGrammarParser.GTopContext): GTop = {
    info(s"[Parsing]GTop")


    GTop(ctx.sessionDef().asScala.map(visitSessionDef).toSet)
  }


  override def visitRoleArg(ctx: ProtocolGrammarParser.RoleArgContext): Role = {
    val rId = getID(ctx.role().IDENTIFIER())
    val rsId = getID(ctx.roleSet().IDENTIFIER())
    rToRs += (rId -> RoleSet(rsId))
    Role(rId, RoleSet(rsId))
  }

  override def visitRole(ctx: ProtocolGrammarParser.RoleContext): Role = {
    val rId = ctx.IDENTIFIER().getSymbol.getText
    Role(rId, rToRs(rId))
  }

  override def visitRoleSet(ctx: ProtocolGrammarParser.RoleSetContext): RoleSet = {
    RoleSet(ctx.IDENTIFIER().getSymbol.getText)
  }

  override def visitSessionDef(ctx: ProtocolGrammarParser.SessionDefContext): SubSession = {
    assert(rToRs.isEmpty)
    assert(rs.isEmpty)
    val sName = ctx.sessionName().IDENTIFIER().toString
    info(s"Parsing Sub session: $sName")
    //val cRoles = ctx.sessionDefCArgs().role().asScala.map(visitRole(_))
    val roles = ctx.sessionDefCArgs().roleArg().asScala.map(visitRoleArg)

    val pRole = visitRoleArg(ctx.sessionDefPickArg().roleArg())
    //val pRoleSet = visitRoleSet(ctx.sessionDefPickArg().roleSet()).asInstanceOf[RoleSet]
    val rSets = ctx.sessionDefRArgs().roleSet().asScala.map(visitRoleSet)

    rs = rSets.toSet// + pRole.rs

    info(s"$sName($roles, _$pRole, $rSets)")
    val gt = visit(ctx.globalType())

    val ss = SubSession(sName, roles.toList, pRole, rSets.toList, gt)
    debug(s"Parsed: $ss")
    rToRs = Map()
    rs = Set()
    ss
  }

  override def visitGlobalType(ctx: ProtocolGrammarParser.GlobalTypeContext): GType = {
    assert(ctx.children.size() == 1,s"Only one child allowed for ${ctx.getText} but has ${ctx.children.asScala.map(_.getText).mkString(" ; ")}")
    visit(ctx.children.get(0))
    //super.visitGlobalType(ctx)
  }

  override def visitSpawn(ctx: ProtocolGrammarParser.SpawnContext): GType = {
    Spawn(getID(ctx.sessionName().IDENTIFIER()),
      ctx.role().asScala.map(visitRole).toList,
      visitRoleSet(ctx.roleSet(0)),
      ctx.roleSet().asScala.tail.map(visitRoleSet).toList,
      visit(ctx.globalType()))
  }

  override def visitGWithg(ctx: ProtocolGrammarParser.GWithgContext): GType = {
    Hdl(visit(ctx.globalType(0)), visit(ctx.globalType(1)))
  }

  override def visitRRole(ctx: ProtocolGrammarParser.RRoleContext): RRole = {
    val id = getID(ctx.role().IDENTIFIER())
    if (rs.contains(RoleSet(id)))
      RoleSet(id)
    else
      Role(id, rToRs(id))
  }

  override def visitSend(ctx: ProtocolGrammarParser.SendContext): GType = {
    val frm = visitRole(ctx.role())
    val to = visitRRole(ctx.rRole())
    val l = visitCLabel(ctx.cLabel())
    val con = visit(ctx.globalType())
    Interaction(frm, to, Map((l, con)))
  }

  override def visitBranching(ctx: ProtocolGrammarParser.BranchingContext): GType = {
    val frm = visitRole(ctx.role)
    val to = visitRRole(ctx.rRole)

    val ls = ctx.branchCase().asScala.map(c => (visitCLabel(c.cLabel()), visit(c.globalType()))).toMap
    Interaction(frm, to, ls)
  }

  override def visitBranchCase(ctx: ProtocolGrammarParser.BranchCaseContext): GType = {
    assert(false)
    null
  }

  override def visitCLabel(ctx: ProtocolGrammarParser.CLabelContext): Label = {
    val name = ctx.cName().IDENTIFIER().asScala.map(getID).mkString(".")
    val v = ctx.vName().asScala.map(x => getID(x.IDENTIFIER()))
    val t = ctx.vType().asScala.map(x => x.IDENTIFIER().asScala.map(getID).mkString("."))
    Label(name, v.zip(t).toList)
  }

  def getID(t: TerminalNode): String = t.getText

  override def visitFDetection(ctx: ProtocolGrammarParser.FDetectionContext): FDetection = {
    FDetection(visitRole(ctx.role(0)), visitRole(ctx.role(1)), visit(ctx.globalType()))
  }

  override def visitRecursion(ctx: ProtocolGrammarParser.RecursionContext): Rec = {
    Rec(visitRecID(ctx.recID()), visit(ctx.globalType()))
  }


  override def visitRecCall(ctx: ProtocolGrammarParser.RecCallContext): RecT = {
    visitRecID(ctx.recID())
  }


  override def visitSessionDefCArgs(ctx: ProtocolGrammarParser.SessionDefCArgsContext): GType = {
    assert(false)
    null
  }


  override def visitSessionDefPickArg(ctx: ProtocolGrammarParser.SessionDefPickArgContext): GType = {
    assert(false)
    super.visitSessionDefPickArg(ctx)
  }

  override def visitSessionDefRArgs(ctx: ProtocolGrammarParser.SessionDefRArgsContext): GType = {
    assert(false)
    super.visitSessionDefRArgs(ctx)
  }


}
