import java_cup.runtime.ComplexSymbolFactory.*;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java.lang.*;
import java.io.InputStreamReader;
import Semantic.StringTable;

%%

%class Lexer
%public
%implements ParserSym
%unicode
%line
%column
%cup
%type ComplexSymbol
%char
%{
	StringBuilder lexeme = new StringBuilder();
	private StringTable stringTable;
	public Lexer(ComplexSymbolFactory sf, java.io.Reader reader, StringTable stringtable){
		this(reader);
        symbolFactory = sf;
        this.stringTable=stringtable;
    }

    private StringBuffer sb;
    private ComplexSymbolFactory symbolFactory;
    private int csline,cscolumn;

    public ComplexSymbol symbol(String name, int code){
		return new ComplexSymbol(name, code,
						new Location(yyline+1,yycolumn+1, yychar), // -yylength()
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()));
    }
    public ComplexSymbol symbol(String name, int code, Object value){

	ComplexSymbol complex=new ComplexSymbol(name, code,
						new Location(yyline+1, yycolumn +1, yychar),
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), value);
	    this.stringTable.addSymbol(complex);
	    return complex;
    }

    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }

    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" +
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

Newline = \r|\n|\r\n
InputCharacter = [^\r\n]
Whitespace     = {Newline} | [ \t\f]

/* COMMENTS */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {Newline}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

any = .
IDENTIFIER = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*
INT_CONST = 0 | [1-9][0-9]* /*Decimal number is 0 or start with a number that not is zero followed by 0 or plus digits*/
FLOAT_CONST = (0 | [1-9][0-9]*)\.[0-9]+
/*String literal*/
STRING_CONST = [^\r\n\"\\]
CHAR_CONST = '({any})?'

%state STRING


%%


<YYINITIAL> {
/* whitespace & comment */
{Whitespace} {                      }
{Comment}    {   }

  /* keywords */
  "function" { return symbol("FUNCTION",ParserSym.FUNCTION); }
  "end" { return symbol("END",ParserSym.END); }
  "if" { return symbol("IF",ParserSym.IF); }
  "then" { return symbol("THEN",ParserSym.THEN); }
  "else" { return symbol("ELSE",ParserSym.ELSE); }
  "while" { return symbol("WHILE",ParserSym.WHILE); }
  "do" { return symbol("DO",ParserSym.DO); }
  "for" { return symbol("FOR",ParserSym.FOR); }
  "local" { return symbol("LOCAL",ParserSym.LOCAL); }
  "global" { return symbol("GLOBAL",ParserSym.GLOBAL); }
  "<==" { return symbol("READ",ParserSym.READ); }
  "==>" { return symbol("WRITE",ParserSym.WRITE); }
  "return" { return symbol("RETURN",ParserSym.RETURN); }
  "true" { return symbol("TRUE",ParserSym.TRUE); }
  "false" { return symbol("FALSE",ParserSym.FALSE); }
  "not" { return symbol("NOT",ParserSym.NOT); }
  "#" { return symbol("SHARP",ParserSym.SHARP); }


  /* Types Keywords */
  "nil" { return symbol("NIL",ParserSym.NIL); }
  "int" { return symbol("INT",ParserSym.INT); }
  "bool" { return symbol("BOOL",ParserSym.BOOL); }
  "float" { return symbol("FLOAT",ParserSym.FLOAT); }
  "string" { return symbol("STRING",ParserSym.STRING); }

  /* separators */
  "(" { return symbol("LPAR",ParserSym.LPAR); }
  ")" { return symbol("RPAR",ParserSym.RPAR); }
  "{" { return symbol("BLPAR",ParserSym.BLPAR); }
  "}" { return symbol("BRPAR",ParserSym.BRPAR); }
  "[" { return symbol("SLPAR",ParserSym.SLPAR); }
  "]" { return symbol("SRPAR",ParserSym.SRPAR); }
  "," { return symbol("COMMA",ParserSym.COMMA); }
  ";" { return symbol("SEMI",ParserSym.SEMI); }
  ":" { return symbol("COLON",ParserSym.COLON); }

  /* relop */
  "and" { return symbol("AND",ParserSym.AND); }
  "or" { return symbol("OR",ParserSym.OR); }
  "<" { return symbol("LT",ParserSym.LT); }
  "<=" { return symbol("LE",ParserSym.LE); }
  ">" { return symbol("GT",ParserSym.GT); }
  ">=" { return symbol("GE",ParserSym.GE); }
  "->" { return symbol("ARROW",ParserSym.ARROW); }
  "=" { return symbol("ASSIGN",ParserSym.ASSIGN); }
  "==" { return symbol("EQ",ParserSym.EQ); }
  "!=" { return symbol("NE",ParserSym.NE); }
  "nop" { return symbol("NOP",ParserSym.NOP); }

  /* arop */
  "+" { return symbol("PLUS",ParserSym.PLUS); }
  "-" { return symbol("MINUS",ParserSym.MINUS); }
  "*" { return symbol("TIMES",ParserSym.TIMES); }
  "/" { return symbol("DIV",ParserSym.DIV); }


  /* identifiers */
  {IDENTIFIER} { return symbol("ID",ParserSym.ID, yytext()); }

  /* literals */
  {INT_CONST} { return symbol("INT_CONST",ParserSym.INT_CONST,Integer.parseInt(yytext()));}
  {FLOAT_CONST} { return symbol("FLOAT_CONST",ParserSym.FLOAT_CONST,Float.parseFloat(yytext()));}
    \" { yybegin(STRING); lexeme.setLength(0);}

}

<STRING> {
 \" { yybegin(YYINITIAL); return symbol("STRING_CONST",ParserSym.STRING_CONST,lexeme.toString()); }
 /* escape sequences */
   {STRING_CONST}+ { lexeme.append( yytext()); }
   "\\b" 	{ lexeme.append( '\b' ); }
   "\\t" 	{ lexeme.append( '\t' ); }
   "\\n" 	{ lexeme.append( '\n' ); }
   "\\f" 	{ lexeme.append( '\f' ); }
   "\\r" 	{ lexeme.append( '\r' ); }
   "\\\"" 	{ lexeme.append( '\"' ); }
   "\\'" 	{ lexeme.append( '\'' ); }
   "\\\\" 	{ lexeme.append( '\\' ); }
}

<<EOF>> { return symbol("EOF",ParserSym.EOF); }

/* error fallback */
[^] {
  throw new RuntimeException("Error:(" + yyline + ":" + yycolumn + ") Cannot resolve symbol '"+yytext()+"'");
}