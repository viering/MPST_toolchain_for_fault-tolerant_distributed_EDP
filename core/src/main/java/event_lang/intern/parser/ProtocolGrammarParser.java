// Generated from ProtocolGrammar.g by ANTLR 4.5.1
package event_lang.intern.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProtocolGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, SEND=7, PUNCTUATION=8, 
		LBRACKET=9, RBRACKET=10, LCBRACKET=11, RCBRACKET=12, END=13, REK=14, SPAWN=15, 
		WITH=16, WHITESPACE=17, IDENTIFIER=18, NUMBER=19;
	public static final int
		RULE_recID = 0, RULE_sessionName = 1, RULE_gTop = 2, RULE_role = 3, RULE_roleSet = 4, 
		RULE_roleArg = 5, RULE_sessionDefCArgs = 6, RULE_sessionDefPickArg = 7, 
		RULE_sessionDefRArgs = 8, RULE_sessionDef = 9, RULE_globalType = 10, RULE_end = 11, 
		RULE_spawn = 12, RULE_gWithg = 13, RULE_send = 14, RULE_rRole = 15, RULE_branching = 16, 
		RULE_branchCase = 17, RULE_cLabel = 18, RULE_cName = 19, RULE_vName = 20, 
		RULE_vType = 21, RULE_fDetection = 22, RULE_recursion = 23, RULE_recCall = 24;
	public static final String[] ruleNames = {
		"recID", "sessionName", "gTop", "role", "roleSet", "roleArg", "sessionDefCArgs", 
		"sessionDefPickArg", "sessionDefRArgs", "sessionDef", "globalType", "end", 
		"spawn", "gWithg", "send", "rRole", "branching", "branchCase", "cLabel", 
		"cName", "vName", "vType", "fDetection", "recursion", "recCall"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'_'", "','", "'='", "'.'", "'@'", "'->'", "':'", "'('", 
		"')'", "'{'", "'}'", "'0'", "'mu'", "'spawn'", "'with'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "SEND", "PUNCTUATION", "LBRACKET", 
		"RBRACKET", "LCBRACKET", "RCBRACKET", "END", "REK", "SPAWN", "WITH", "WHITESPACE", 
		"IDENTIFIER", "NUMBER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ProtocolGrammar.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProtocolGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RecIDContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProtocolGrammarParser.IDENTIFIER, 0); }
		public RecIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRecID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRecID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRecID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecIDContext recID() throws RecognitionException {
		RecIDContext _localctx = new RecIDContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_recID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProtocolGrammarParser.IDENTIFIER, 0); }
		public SessionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sessionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSessionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSessionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSessionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionNameContext sessionName() throws RecognitionException {
		SessionNameContext _localctx = new SessionNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sessionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GTopContext extends ParserRuleContext {
		public List<SessionDefContext> sessionDef() {
			return getRuleContexts(SessionDefContext.class);
		}
		public SessionDefContext sessionDef(int i) {
			return getRuleContext(SessionDefContext.class,i);
		}
		public GTopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gTop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterGTop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitGTop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitGTop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GTopContext gTop() throws RecognitionException {
		GTopContext _localctx = new GTopContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_gTop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(LCBRACKET);
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				sessionDef();
				setState(56);
				match(T__0);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(62);
			match(RCBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoleContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProtocolGrammarParser.IDENTIFIER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(ProtocolGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ProtocolGrammarParser.NUMBER, i);
		}
		public RoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_role; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleContext role() throws RecognitionException {
		RoleContext _localctx = new RoleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_role);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(IDENTIFIER);
			setState(74);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(65);
				match(T__1);
				setState(66);
				match(LCBRACKET);
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(67);
					match(NUMBER);
					setState(68);
					match(T__2);
					}
					}
					setState(71); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NUMBER );
				setState(73);
				match(RCBRACKET);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoleSetContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProtocolGrammarParser.IDENTIFIER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(ProtocolGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ProtocolGrammarParser.NUMBER, i);
		}
		public RoleSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRoleSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRoleSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRoleSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleSetContext roleSet() throws RecognitionException {
		RoleSetContext _localctx = new RoleSetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_roleSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(IDENTIFIER);
			setState(86);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(77);
				match(T__1);
				setState(78);
				match(LCBRACKET);
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					match(NUMBER);
					setState(80);
					match(T__2);
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NUMBER );
				setState(85);
				match(RCBRACKET);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoleArgContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public RoleSetContext roleSet() {
			return getRuleContext(RoleSetContext.class,0);
		}
		public RoleArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRoleArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRoleArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRoleArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleArgContext roleArg() throws RecognitionException {
		RoleArgContext _localctx = new RoleArgContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_roleArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			role();
			setState(89);
			match(PUNCTUATION);
			setState(90);
			roleSet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionDefCArgsContext extends ParserRuleContext {
		public List<RoleArgContext> roleArg() {
			return getRuleContexts(RoleArgContext.class);
		}
		public RoleArgContext roleArg(int i) {
			return getRuleContext(RoleArgContext.class,i);
		}
		public SessionDefCArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sessionDefCArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSessionDefCArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSessionDefCArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSessionDefCArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionDefCArgsContext sessionDefCArgs() throws RecognitionException {
		SessionDefCArgsContext _localctx = new SessionDefCArgsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sessionDefCArgs);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			roleArg();
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					match(T__2);
					setState(94);
					roleArg();
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionDefPickArgContext extends ParserRuleContext {
		public RoleArgContext roleArg() {
			return getRuleContext(RoleArgContext.class,0);
		}
		public SessionDefPickArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sessionDefPickArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSessionDefPickArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSessionDefPickArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSessionDefPickArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionDefPickArgContext sessionDefPickArg() throws RecognitionException {
		SessionDefPickArgContext _localctx = new SessionDefPickArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sessionDefPickArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			roleArg();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionDefRArgsContext extends ParserRuleContext {
		public List<RoleSetContext> roleSet() {
			return getRuleContexts(RoleSetContext.class);
		}
		public RoleSetContext roleSet(int i) {
			return getRuleContext(RoleSetContext.class,i);
		}
		public SessionDefRArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sessionDefRArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSessionDefRArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSessionDefRArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSessionDefRArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionDefRArgsContext sessionDefRArgs() throws RecognitionException {
		SessionDefRArgsContext _localctx = new SessionDefRArgsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_sessionDefRArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(102);
				match(T__2);
				setState(103);
				roleSet();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionDefContext extends ParserRuleContext {
		public SessionNameContext sessionName() {
			return getRuleContext(SessionNameContext.class,0);
		}
		public SessionDefCArgsContext sessionDefCArgs() {
			return getRuleContext(SessionDefCArgsContext.class,0);
		}
		public SessionDefPickArgContext sessionDefPickArg() {
			return getRuleContext(SessionDefPickArgContext.class,0);
		}
		public SessionDefRArgsContext sessionDefRArgs() {
			return getRuleContext(SessionDefRArgsContext.class,0);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public SessionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sessionDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSessionDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSessionDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSessionDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionDefContext sessionDef() throws RecognitionException {
		SessionDefContext _localctx = new SessionDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sessionDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			sessionName();
			setState(110);
			match(LBRACKET);
			setState(111);
			sessionDefCArgs();
			setState(112);
			match(T__2);
			setState(113);
			match(T__1);
			setState(114);
			sessionDefPickArg();
			setState(115);
			sessionDefRArgs();
			setState(116);
			match(RBRACKET);
			setState(117);
			match(T__3);
			setState(118);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalTypeContext extends ParserRuleContext {
		public GWithgContext gWithg() {
			return getRuleContext(GWithgContext.class,0);
		}
		public SendContext send() {
			return getRuleContext(SendContext.class,0);
		}
		public BranchingContext branching() {
			return getRuleContext(BranchingContext.class,0);
		}
		public RecursionContext recursion() {
			return getRuleContext(RecursionContext.class,0);
		}
		public RecCallContext recCall() {
			return getRuleContext(RecCallContext.class,0);
		}
		public FDetectionContext fDetection() {
			return getRuleContext(FDetectionContext.class,0);
		}
		public SpawnContext spawn() {
			return getRuleContext(SpawnContext.class,0);
		}
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public GlobalTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterGlobalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitGlobalType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitGlobalType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalTypeContext globalType() throws RecognitionException {
		GlobalTypeContext _localctx = new GlobalTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_globalType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(120);
				gWithg();
				}
				break;
			case 2:
				{
				setState(121);
				send();
				}
				break;
			case 3:
				{
				setState(122);
				branching();
				}
				break;
			case 4:
				{
				setState(123);
				recursion();
				}
				break;
			case 5:
				{
				setState(124);
				recCall();
				}
				break;
			case 6:
				{
				setState(125);
				fDetection();
				}
				break;
			case 7:
				{
				setState(126);
				spawn();
				}
				break;
			case 8:
				{
				setState(127);
				end();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(ProtocolGrammarParser.END, 0); }
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpawnContext extends ParserRuleContext {
		public TerminalNode SPAWN() { return getToken(ProtocolGrammarParser.SPAWN, 0); }
		public SessionNameContext sessionName() {
			return getRuleContext(SessionNameContext.class,0);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public List<RoleSetContext> roleSet() {
			return getRuleContexts(RoleSetContext.class);
		}
		public RoleSetContext roleSet(int i) {
			return getRuleContext(RoleSetContext.class,i);
		}
		public List<RoleContext> role() {
			return getRuleContexts(RoleContext.class);
		}
		public RoleContext role(int i) {
			return getRuleContext(RoleContext.class,i);
		}
		public SpawnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spawn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSpawn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSpawn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSpawn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpawnContext spawn() throws RecognitionException {
		SpawnContext _localctx = new SpawnContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_spawn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(SPAWN);
			setState(133);
			sessionName();
			setState(134);
			match(LBRACKET);
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				role();
				setState(136);
				match(T__2);
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			{
			setState(142);
			match(T__1);
			setState(143);
			roleSet();
			}
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(145);
				match(T__2);
				setState(146);
				roleSet();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			match(RBRACKET);
			setState(153);
			match(T__4);
			setState(154);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GWithgContext extends ParserRuleContext {
		public List<GlobalTypeContext> globalType() {
			return getRuleContexts(GlobalTypeContext.class);
		}
		public GlobalTypeContext globalType(int i) {
			return getRuleContext(GlobalTypeContext.class,i);
		}
		public TerminalNode WITH() { return getToken(ProtocolGrammarParser.WITH, 0); }
		public GWithgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gWithg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterGWithg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitGWithg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitGWithg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GWithgContext gWithg() throws RecognitionException {
		GWithgContext _localctx = new GWithgContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_gWithg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(LCBRACKET);
			setState(157);
			globalType();
			setState(158);
			match(WITH);
			setState(159);
			globalType();
			setState(160);
			match(RCBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SendContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public TerminalNode SEND() { return getToken(ProtocolGrammarParser.SEND, 0); }
		public RRoleContext rRole() {
			return getRuleContext(RRoleContext.class,0);
		}
		public CLabelContext cLabel() {
			return getRuleContext(CLabelContext.class,0);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public SendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_send; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterSend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitSend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitSend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SendContext send() throws RecognitionException {
		SendContext _localctx = new SendContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_send);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			role();
			setState(163);
			match(SEND);
			setState(164);
			rRole();
			setState(165);
			match(PUNCTUATION);
			setState(166);
			cLabel();
			setState(167);
			matchWildcard();
			setState(168);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RRoleContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public RRoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rRole; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RRoleContext rRole() throws RecognitionException {
		RRoleContext _localctx = new RRoleContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_rRole);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			role();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchingContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public TerminalNode SEND() { return getToken(ProtocolGrammarParser.SEND, 0); }
		public RRoleContext rRole() {
			return getRuleContext(RRoleContext.class,0);
		}
		public List<BranchCaseContext> branchCase() {
			return getRuleContexts(BranchCaseContext.class);
		}
		public BranchCaseContext branchCase(int i) {
			return getRuleContext(BranchCaseContext.class,i);
		}
		public BranchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterBranching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitBranching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitBranching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchingContext branching() throws RecognitionException {
		BranchingContext _localctx = new BranchingContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_branching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			role();
			setState(173);
			match(SEND);
			setState(174);
			rRole();
			setState(175);
			match(PUNCTUATION);
			setState(176);
			match(LCBRACKET);
			setState(177);
			branchCase();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(178);
				match(T__2);
				setState(179);
				branchCase();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(185);
			match(RCBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchCaseContext extends ParserRuleContext {
		public CLabelContext cLabel() {
			return getRuleContext(CLabelContext.class,0);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public BranchCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branchCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterBranchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitBranchCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitBranchCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchCaseContext branchCase() throws RecognitionException {
		BranchCaseContext _localctx = new BranchCaseContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_branchCase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			cLabel();
			setState(188);
			match(PUNCTUATION);
			setState(189);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CLabelContext extends ParserRuleContext {
		public CNameContext cName() {
			return getRuleContext(CNameContext.class,0);
		}
		public List<VNameContext> vName() {
			return getRuleContexts(VNameContext.class);
		}
		public VNameContext vName(int i) {
			return getRuleContext(VNameContext.class,i);
		}
		public List<VTypeContext> vType() {
			return getRuleContexts(VTypeContext.class);
		}
		public VTypeContext vType(int i) {
			return getRuleContext(VTypeContext.class,i);
		}
		public CLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterCLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitCLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitCLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CLabelContext cLabel() throws RecognitionException {
		CLabelContext _localctx = new CLabelContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_cLabel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			cName();
			setState(192);
			match(LBRACKET);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(193);
				vName();
				setState(194);
				match(PUNCTUATION);
				setState(195);
				vType();
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ProtocolGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ProtocolGrammarParser.IDENTIFIER, i);
		}
		public CNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterCName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitCName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitCName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CNameContext cName() throws RecognitionException {
		CNameContext _localctx = new CNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_cName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(IDENTIFIER);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(205);
				match(T__4);
				setState(206);
				match(IDENTIFIER);
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProtocolGrammarParser.IDENTIFIER, 0); }
		public VNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterVName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitVName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitVName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VNameContext vName() throws RecognitionException {
		VNameContext _localctx = new VNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_vName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VTypeContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ProtocolGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ProtocolGrammarParser.IDENTIFIER, i);
		}
		public VTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterVType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitVType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitVType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VTypeContext vType() throws RecognitionException {
		VTypeContext _localctx = new VTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_vType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(IDENTIFIER);
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(215);
				match(T__4);
				setState(216);
				match(IDENTIFIER);
				}
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FDetectionContext extends ParserRuleContext {
		public List<RoleContext> role() {
			return getRuleContexts(RoleContext.class);
		}
		public RoleContext role(int i) {
			return getRuleContext(RoleContext.class,i);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public FDetectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fDetection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterFDetection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitFDetection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitFDetection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FDetectionContext fDetection() throws RecognitionException {
		FDetectionContext _localctx = new FDetectionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fDetection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			role();
			setState(223);
			match(T__5);
			setState(224);
			role();
			setState(225);
			match(T__4);
			setState(226);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecursionContext extends ParserRuleContext {
		public TerminalNode REK() { return getToken(ProtocolGrammarParser.REK, 0); }
		public RecIDContext recID() {
			return getRuleContext(RecIDContext.class,0);
		}
		public GlobalTypeContext globalType() {
			return getRuleContext(GlobalTypeContext.class,0);
		}
		public RecursionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recursion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRecursion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRecursion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRecursion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursionContext recursion() throws RecognitionException {
		RecursionContext _localctx = new RecursionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_recursion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(REK);
			setState(229);
			recID();
			setState(230);
			match(T__4);
			setState(231);
			globalType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecCallContext extends ParserRuleContext {
		public RecIDContext recID() {
			return getRuleContext(RecIDContext.class,0);
		}
		public RecCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).enterRecCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProtocolGrammarListener ) ((ProtocolGrammarListener)listener).exitRecCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProtocolGrammarVisitor ) return ((ProtocolGrammarVisitor<? extends T>)visitor).visitRecCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecCallContext recCall() throws RecognitionException {
		RecCallContext _localctx = new RecCallContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_recCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			recID();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\u00ee\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\6\4=\n\4\r\4\16\4>\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\6\5H\n\5\r\5\16\5I\3\5\5\5M\n\5\3\6\3\6\3\6\3\6\3"+
		"\6\6\6T\n\6\r\6\16\6U\3\6\5\6Y\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\bb\n"+
		"\b\f\b\16\be\13\b\3\t\3\t\3\n\3\n\7\nk\n\n\f\n\16\nn\13\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0083\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\6\16\u008d"+
		"\n\16\r\16\16\16\u008e\3\16\3\16\3\16\3\16\3\16\7\16\u0096\n\16\f\16\16"+
		"\16\u0099\13\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\7\22\u00b7\n\22\f\22\16\22\u00ba\13\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u00c8\n\24\f\24\16"+
		"\24\u00cb\13\24\3\24\3\24\3\25\3\25\3\25\7\25\u00d2\n\25\f\25\16\25\u00d5"+
		"\13\25\3\26\3\26\3\27\3\27\3\27\7\27\u00dc\n\27\f\27\16\27\u00df\13\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\2\u00e8"+
		"\2\64\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\bB\3\2\2\2\nN\3\2\2\2\fZ\3\2\2\2"+
		"\16^\3\2\2\2\20f\3\2\2\2\22l\3\2\2\2\24o\3\2\2\2\26\u0082\3\2\2\2\30\u0084"+
		"\3\2\2\2\32\u0086\3\2\2\2\34\u009e\3\2\2\2\36\u00a4\3\2\2\2 \u00ac\3\2"+
		"\2\2\"\u00ae\3\2\2\2$\u00bd\3\2\2\2&\u00c1\3\2\2\2(\u00ce\3\2\2\2*\u00d6"+
		"\3\2\2\2,\u00d8\3\2\2\2.\u00e0\3\2\2\2\60\u00e6\3\2\2\2\62\u00eb\3\2\2"+
		"\2\64\65\7\24\2\2\65\3\3\2\2\2\66\67\7\24\2\2\67\5\3\2\2\28<\7\r\2\29"+
		":\5\24\13\2:;\7\3\2\2;=\3\2\2\2<9\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2"+
		"\2?@\3\2\2\2@A\7\16\2\2A\7\3\2\2\2BL\7\24\2\2CD\7\4\2\2DG\7\r\2\2EF\7"+
		"\25\2\2FH\7\5\2\2GE\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KM"+
		"\7\16\2\2LC\3\2\2\2LM\3\2\2\2M\t\3\2\2\2NX\7\24\2\2OP\7\4\2\2PS\7\r\2"+
		"\2QR\7\25\2\2RT\7\5\2\2SQ\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2"+
		"\2\2WY\7\16\2\2XO\3\2\2\2XY\3\2\2\2Y\13\3\2\2\2Z[\5\b\5\2[\\\7\n\2\2\\"+
		"]\5\n\6\2]\r\3\2\2\2^c\5\f\7\2_`\7\5\2\2`b\5\f\7\2a_\3\2\2\2be\3\2\2\2"+
		"ca\3\2\2\2cd\3\2\2\2d\17\3\2\2\2ec\3\2\2\2fg\5\f\7\2g\21\3\2\2\2hi\7\5"+
		"\2\2ik\5\n\6\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\23\3\2\2\2nl\3"+
		"\2\2\2op\5\4\3\2pq\7\13\2\2qr\5\16\b\2rs\7\5\2\2st\7\4\2\2tu\5\20\t\2"+
		"uv\5\22\n\2vw\7\f\2\2wx\7\6\2\2xy\5\26\f\2y\25\3\2\2\2z\u0083\5\34\17"+
		"\2{\u0083\5\36\20\2|\u0083\5\"\22\2}\u0083\5\60\31\2~\u0083\5\62\32\2"+
		"\177\u0083\5.\30\2\u0080\u0083\5\32\16\2\u0081\u0083\5\30\r\2\u0082z\3"+
		"\2\2\2\u0082{\3\2\2\2\u0082|\3\2\2\2\u0082}\3\2\2\2\u0082~\3\2\2\2\u0082"+
		"\177\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\27\3\2\2\2"+
		"\u0084\u0085\7\17\2\2\u0085\31\3\2\2\2\u0086\u0087\7\21\2\2\u0087\u0088"+
		"\5\4\3\2\u0088\u008c\7\13\2\2\u0089\u008a\5\b\5\2\u008a\u008b\7\5\2\2"+
		"\u008b\u008d\3\2\2\2\u008c\u0089\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7\4\2\2\u0091"+
		"\u0092\5\n\6\2\u0092\u0097\3\2\2\2\u0093\u0094\7\5\2\2\u0094\u0096\5\n"+
		"\6\2\u0095\u0093\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7\f"+
		"\2\2\u009b\u009c\7\7\2\2\u009c\u009d\5\26\f\2\u009d\33\3\2\2\2\u009e\u009f"+
		"\7\r\2\2\u009f\u00a0\5\26\f\2\u00a0\u00a1\7\22\2\2\u00a1\u00a2\5\26\f"+
		"\2\u00a2\u00a3\7\16\2\2\u00a3\35\3\2\2\2\u00a4\u00a5\5\b\5\2\u00a5\u00a6"+
		"\7\t\2\2\u00a6\u00a7\5 \21\2\u00a7\u00a8\7\n\2\2\u00a8\u00a9\5&\24\2\u00a9"+
		"\u00aa\13\2\2\2\u00aa\u00ab\5\26\f\2\u00ab\37\3\2\2\2\u00ac\u00ad\5\b"+
		"\5\2\u00ad!\3\2\2\2\u00ae\u00af\5\b\5\2\u00af\u00b0\7\t\2\2\u00b0\u00b1"+
		"\5 \21\2\u00b1\u00b2\7\n\2\2\u00b2\u00b3\7\r\2\2\u00b3\u00b8\5$\23\2\u00b4"+
		"\u00b5\7\5\2\2\u00b5\u00b7\5$\23\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bc\7\16\2\2\u00bc#\3\2\2\2\u00bd\u00be\5&\24\2"+
		"\u00be\u00bf\7\n\2\2\u00bf\u00c0\5\26\f\2\u00c0%\3\2\2\2\u00c1\u00c2\5"+
		"(\25\2\u00c2\u00c9\7\13\2\2\u00c3\u00c4\5*\26\2\u00c4\u00c5\7\n\2\2\u00c5"+
		"\u00c6\5,\27\2\u00c6\u00c8\3\2\2\2\u00c7\u00c3\3\2\2\2\u00c8\u00cb\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\7\f\2\2\u00cd\'\3\2\2\2\u00ce\u00d3\7\24\2"+
		"\2\u00cf\u00d0\7\7\2\2\u00d0\u00d2\7\24\2\2\u00d1\u00cf\3\2\2\2\u00d2"+
		"\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4)\3\2\2\2"+
		"\u00d5\u00d3\3\2\2\2\u00d6\u00d7\7\24\2\2\u00d7+\3\2\2\2\u00d8\u00dd\7"+
		"\24\2\2\u00d9\u00da\7\7\2\2\u00da\u00dc\7\24\2\2\u00db\u00d9\3\2\2\2\u00dc"+
		"\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de-\3\2\2\2"+
		"\u00df\u00dd\3\2\2\2\u00e0\u00e1\5\b\5\2\u00e1\u00e2\7\b\2\2\u00e2\u00e3"+
		"\5\b\5\2\u00e3\u00e4\7\7\2\2\u00e4\u00e5\5\26\f\2\u00e5/\3\2\2\2\u00e6"+
		"\u00e7\7\20\2\2\u00e7\u00e8\5\2\2\2\u00e8\u00e9\7\7\2\2\u00e9\u00ea\5"+
		"\26\f\2\u00ea\61\3\2\2\2\u00eb\u00ec\5\2\2\2\u00ec\63\3\2\2\2\20>ILUX"+
		"cl\u0082\u008e\u0097\u00b8\u00c9\u00d3\u00dd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}