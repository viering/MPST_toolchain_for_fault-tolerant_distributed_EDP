// Generated from ProtocolGrammar.g by ANTLR 4.5.1
package event_lang.intern.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProtocolGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProtocolGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#recID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecID(ProtocolGrammarParser.RecIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#sessionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionName(ProtocolGrammarParser.SessionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#gTop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTop(ProtocolGrammarParser.GTopContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(ProtocolGrammarParser.RoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#roleSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleSet(ProtocolGrammarParser.RoleSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#roleArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleArg(ProtocolGrammarParser.RoleArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#sessionDefCArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionDefCArgs(ProtocolGrammarParser.SessionDefCArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#sessionDefPickArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionDefPickArg(ProtocolGrammarParser.SessionDefPickArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#sessionDefRArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionDefRArgs(ProtocolGrammarParser.SessionDefRArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#sessionDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionDef(ProtocolGrammarParser.SessionDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#globalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalType(ProtocolGrammarParser.GlobalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(ProtocolGrammarParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#spawn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpawn(ProtocolGrammarParser.SpawnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#gWithg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGWithg(ProtocolGrammarParser.GWithgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#send}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSend(ProtocolGrammarParser.SendContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#rRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRRole(ProtocolGrammarParser.RRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#branching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranching(ProtocolGrammarParser.BranchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#branchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchCase(ProtocolGrammarParser.BranchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#cLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCLabel(ProtocolGrammarParser.CLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#cName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCName(ProtocolGrammarParser.CNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#vName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVName(ProtocolGrammarParser.VNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#vType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVType(ProtocolGrammarParser.VTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#fDetection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFDetection(ProtocolGrammarParser.FDetectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#recursion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursion(ProtocolGrammarParser.RecursionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProtocolGrammarParser#recCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecCall(ProtocolGrammarParser.RecCallContext ctx);
}