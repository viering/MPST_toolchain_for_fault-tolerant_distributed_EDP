grammar ProtocolGrammar;

/*
                LEXER
*/
SEND: '->';
PUNCTUATION: ':';
LBRACKET: '(';
RBRACKET: ')';
LCBRACKET: '{';
RCBRACKET: '}';
END: '0';
REK: 'mu';
SPAWN: 'spawn';
WITH: 'with';



WHITESPACE:
	('\t' | ' ' | '\r' | '\n' | '\u000C')+
	-> channel(HIDDEN)
;

IDENTIFIER:
	(LETTER) (LETTER | DIGIT)*
;


NUMBER : DIGIT+;


fragment LETTER:
	'a'..'z' | 'A'..'Z'
;
fragment LETTERUP:
    'A'..'Z'
;
fragment LETTERLOW:
    'a'..'z'
;

fragment DIGIT:
	'0'..'9'
;

fragment DOT:
	'.'
;

fragment UNDERSCORE:
	'_'
;

recID: IDENTIFIER;
sessionName : IDENTIFIER;


/*
    GRAMMAR
*/

gTop: '{'(sessionDef ';')+ '}'
;

role : IDENTIFIER('_' '{'(NUMBER ',')+ '}')?
;

roleSet : IDENTIFIER('_' '{'(NUMBER ',')+ '}')?
;



roleArg : role ':' roleSet
;
sessionDefCArgs : roleArg (','  roleArg )*
;
sessionDefPickArg : roleArg
;
sessionDefRArgs : (',' roleSet)*
;
sessionDef :
    sessionName '('
            sessionDefCArgs ','
            '_' sessionDefPickArg
            sessionDefRArgs
            ')' '=' globalType
;

globalType:
        (
        gWithg |
        send    |
        branching |
        recursion |
        recCall |
        fDetection |
        spawn |
        end
        )
        ;

end: END
;

spawn : SPAWN sessionName '(' (role',')+ ('_' roleSet) (',' roleSet)* ')' '.' globalType
;
gWithg:
    '{' globalType WITH globalType '}'
;

send:
    role SEND rRole ':' cLabel . globalType
;

rRole: role
;

branching:
    role SEND rRole ':' '{' branchCase (',' branchCase )* '}'
;

branchCase:
    cLabel ':' globalType
;

/*
    TODO: add a ',' between elements in a case class
        we have:
            CName( arg1 : T1 arg2 :T2)
            should be
            cName( arg1 : T1, arg2 : T2)
                            ^
                            |
                         need to be added
*/
cLabel:
    cName '(' (vName ':' vType )* ')'
;

cName : IDENTIFIER ('.' IDENTIFIER)*
;
vName : IDENTIFIER
;
vType : IDENTIFIER ('.' IDENTIFIER)*
;

fDetection:
    role '@' role '.' globalType
;

recursion:
    REK  recID '.'
        globalType
;

recCall:
    recID
    ;



