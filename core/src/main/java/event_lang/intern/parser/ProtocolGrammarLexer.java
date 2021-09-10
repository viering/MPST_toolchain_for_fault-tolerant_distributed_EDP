// Generated from ProtocolGrammar.g by ANTLR 4.5.1
package event_lang.intern.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProtocolGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, SEND=7, PUNCTUATION=8, 
		LBRACKET=9, RBRACKET=10, LCBRACKET=11, RCBRACKET=12, END=13, REK=14, SPAWN=15, 
		WITH=16, WHITESPACE=17, IDENTIFIER=18, NUMBER=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "SEND", "PUNCTUATION", 
		"LBRACKET", "RBRACKET", "LCBRACKET", "RCBRACKET", "END", "REK", "SPAWN", 
		"WITH", "WHITESPACE", "IDENTIFIER", "NUMBER", "LETTER", "LETTERUP", "LETTERLOW", 
		"DIGIT", "DOT", "UNDERSCORE"
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


	public ProtocolGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ProtocolGrammar.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25~\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\6\22`\n\22"+
		"\r\22\16\22a\3\22\3\22\3\23\3\23\3\23\7\23i\n\23\f\23\16\23l\13\23\3\24"+
		"\6\24o\n\24\r\24\16\24p\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\2+\2-\2/\2\61\2\63\2\3\2"+
		"\4\5\2\13\f\16\17\"\"\4\2C\\c|{\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3\65\3\2\2"+
		"\2\5\67\3\2\2\2\79\3\2\2\2\t;\3\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2"+
		"\2\21D\3\2\2\2\23F\3\2\2\2\25H\3\2\2\2\27J\3\2\2\2\31L\3\2\2\2\33N\3\2"+
		"\2\2\35P\3\2\2\2\37S\3\2\2\2!Y\3\2\2\2#_\3\2\2\2%e\3\2\2\2\'n\3\2\2\2"+
		")r\3\2\2\2+t\3\2\2\2-v\3\2\2\2/x\3\2\2\2\61z\3\2\2\2\63|\3\2\2\2\65\66"+
		"\7=\2\2\66\4\3\2\2\2\678\7a\2\28\6\3\2\2\29:\7.\2\2:\b\3\2\2\2;<\7?\2"+
		"\2<\n\3\2\2\2=>\7\60\2\2>\f\3\2\2\2?@\7B\2\2@\16\3\2\2\2AB\7/\2\2BC\7"+
		"@\2\2C\20\3\2\2\2DE\7<\2\2E\22\3\2\2\2FG\7*\2\2G\24\3\2\2\2HI\7+\2\2I"+
		"\26\3\2\2\2JK\7}\2\2K\30\3\2\2\2LM\7\177\2\2M\32\3\2\2\2NO\7\62\2\2O\34"+
		"\3\2\2\2PQ\7o\2\2QR\7w\2\2R\36\3\2\2\2ST\7u\2\2TU\7r\2\2UV\7c\2\2VW\7"+
		"y\2\2WX\7p\2\2X \3\2\2\2YZ\7y\2\2Z[\7k\2\2[\\\7v\2\2\\]\7j\2\2]\"\3\2"+
		"\2\2^`\t\2\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\b\22"+
		"\2\2d$\3\2\2\2ej\5)\25\2fi\5)\25\2gi\5/\30\2hf\3\2\2\2hg\3\2\2\2il\3\2"+
		"\2\2jh\3\2\2\2jk\3\2\2\2k&\3\2\2\2lj\3\2\2\2mo\5/\30\2nm\3\2\2\2op\3\2"+
		"\2\2pn\3\2\2\2pq\3\2\2\2q(\3\2\2\2rs\t\3\2\2s*\3\2\2\2tu\4C\\\2u,\3\2"+
		"\2\2vw\4c|\2w.\3\2\2\2xy\4\62;\2y\60\3\2\2\2z{\7\60\2\2{\62\3\2\2\2|}"+
		"\7a\2\2}\64\3\2\2\2\7\2ahjp\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}