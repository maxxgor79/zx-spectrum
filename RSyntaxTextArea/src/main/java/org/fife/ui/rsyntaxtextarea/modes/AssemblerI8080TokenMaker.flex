/*
 * 26/02/2024
 *
 * AssemblerI8080TokenMaker.java - An object that can take a chunk of text and
 * return a linked list of tokens representing i8080 assembler.
 * 
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * This class takes plain text and returns tokens representing i8080
 * assembler.<p>
 *
 * This implementation was created using
 * <a href="https://www.jflex.de/">JFlex</a> 1.4.1; however, the generated file
 * was modified for performance.  Memory allocation needs to be almost
 * completely removed to be competitive with the handwritten lexers (subclasses
 * of <code>AbstractTokenMaker</code>), so this class has been modified so that
 * Strings are never allocated (via yytext()), and the scanner never has to
 * worry about refilling its buffer (needlessly copying chars around).
 * We can achieve this because RText always scans exactly 1 line of tokens at a
 * time, and hands the scanner this line as an array of characters (a Segment
 * really).  Since tokens contain pointers to char arrays instead of Strings
 * holding their contents, there is no need for allocating new memory for
 * Strings.<p>
 *
 * The actual algorithm generated for scanning has, of course, not been
 * modified.<p>
 *
 * If you wish to regenerate this file yourself, keep in mind the following:
 * <ul>
 *   <li>The generated <code>AssemblerI8080TokenMaker.java</code> file will contain two
 *       definitions of both <code>zzRefill</code> and <code>yyreset</code>.
 *       You should hand-delete the second of each definition (the ones
 *       generated by the lexer), as these generated methods modify the input
 *       buffer, which we'll never have to do.</li>
 *   <li>You should also change the declaration/definition of zzBuffer to NOT
 *       be initialized.  This is a needless memory allocation for us since we
 *       will be pointing the array somewhere else anyway.</li>
 *   <li>You should NOT call <code>yylex()</code> on the generated scanner
 *       directly; rather, you should use <code>getTokenList</code> as you would
 *       with any other <code>TokenMaker</code> instance.</li>
 * </ul>
 *
 * @author Robert Futrell & Maxim Gorin
 * @version 0.2
 *
 */
%%

%public
%class AssemblerI8080TokenMaker
%extends AbstractJFlexTokenMaker
%unicode
%ignorecase
%type org.fife.ui.rsyntaxtextarea.Token


%{


	/**
	 * Constructor.  We must have this here as JFLex does not generate a
	 * no parameter constructor.
	 */
	public AssemblerI8080TokenMaker() {
		super();
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *                    occurs.
	 */
	@Override
	public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
		super.addToken(array, start,end, tokenType, startOffset);
		zzStartRead = zzMarkedPos;
	}


	@Override
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return new String[] { ";", null };
	}


	/**
	 * Returns the first token in the linked list of tokens generated
	 * from <code>text</code>.  This method must be implemented by
	 * subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text The text from which to get tokens.
	 * @param initialTokenType The token type we should start with.
	 * @param startOffset The offset into the document at which
	 *                    <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing
	 *         the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
			default:
				state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos>=s.offset+s.count;
	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream 
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream 
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL  = true;
		zzAtEOF  = false;
	}


%}

Letter				= ([A-Za-z_])
Digit				= ([0-9])
IntegerNumber       = ({Digit}+)
HexNumber           = ("#"[0-9A-Fa-f]+|"$"[0-9A-Fa-f]+|[0-9A-Fa-f]+"h"|"0x"[0-9A-Fa-f]+)
BinaryNumber        = ("0b"[0-1]+|[0-1]+"b")
OctalNumber	    = ([0-7]+"g")
Number				= ({IntegerNumber} | {HexNumber} | {BinaryNumber} | {OctalNumber})

Identifier			= (({Letter}|{Digit})[^ \t\f\n\,\.\+\-\*\/\%\[\]]+)

UnclosedStringLiteral	= ([\"][^\"]*)
StringLiteral			= ({UnclosedStringLiteral}[\"])
UnclosedCharLiteral		= ([\'][^\']*)
CharLiteral			= ({UnclosedCharLiteral}[\'])

CommentBegin			= ([;])

LineTerminator			= (\n)
WhiteSpace			= ([ \t\f])

Label				= (({Letter}|{Digit})+[\:])

Operator				= ("+"|"-"|"*"|"/"|"%"|"^"|"|"|"&"|"~"|"!"|"="|"<"|">")

%%

<YYINITIAL> {

	/* Keywords */
	"INCLUDE" |
	"DEF" |
	"DEFINE" |
	"SAVEWAV" |
	"SAVETAP" |
	"SAVETZX" |
	"PRINT" |
	"MESSAGE" |
	"PRINTLN" |
	"ECHO" |
	"ORG" |
	"END" |
	"EQU" |
	".Z80" |
	".INCLUDE" |
	".DEF" |
	".DEFINE" |
	".SAVEWAV" |
	".SAVETAP" |
	".SAVETZX" |
	".PRINT" |
	".MESSAGE" |
	".PRINTLN" |
	".ORG" |
	".END" |
	".EQU" |
	".ECHO?"		{ addToken(Token.PREPROCESSOR); }

	"DB" |
	"DW" |
	"DD" |
	"DDW" |
	"DEFB" |
	"DEFW" |
	"DEFDW" |
	"RESOURCE" |
	"DEFRES" |
	"IMAGE" |
	"IMG" |
	".DB" |
	".DW" |
	".DD" |
	".DDW" |
	".DEFB" |
	".DEFW" |
	".DEFDW" |
	".RESOURCE" |
	".DEFRES" |
	".IMAGE" |
	".IMG"			{ addToken(Token.FUNCTION); }

	/* Registers */
	"A" |
	"B" |
	"C" |
	"D" |
	"E" |
	"H" |
	"L" |
	"M" 		{ addToken(Token.VARIABLE); }

	/* i8080 Instructions. */
	"NOP" |
	"ADD" |
	"ADI" |
	"ADC" |
	"ACI" |
	"ANA" |
	"CALL" |
	"CZ" |
	"CNZ" |
	"CP" |
	"CM" |
	"CC" |
	"CNC" |
	"CPE" |
	"CPO" |
	"CMA" |
	"CMC" |
	"CMP" |
	"CPI" |
	"DAA" |
	"DAD" |
	"DCR" |
	"DCX" |
	"DI" |
	"EI" |
	"HLT" |
	"IN" |
	"INR" |
	"INX" |
	"JMP" |
	"JZ" |
	"JNZ" |
	"JP" |
	"JM" |
	"JC" |
	"JNC" |
	"JPE" |
	"JPO" |
	"LDA" |
	"LDAX" |
	"LHLD" |
	"LXI"  |
	"MOV" |
	"MVI" |
	"ORA" |
	"ORI" |
	"OUT" |
	"PCHL" |
	"POP" |
	"PUSH" |
	"RAL" |
	"RAR" |
	"RRC" |
	"RLC" |
	"RIM" |
	"RET" |
	"RZ" |
	"RNZ" |
	"RP" |
	"RM" |
	"RC" |
	"RNC" |
	"RPE" |
	"RPO" |
	"RST" |
	"SIM" |
	"SPHL" |
	"SHLD" |
	"STA" |
	"STAX" |
	"STC" |
	"SUB" |
	"SUI" |
	"SBB" |
	"SBI" |
	"XCHG" |
	"XTHL" |
	"XRA" |
	"XRI" 			{ addToken(Token.RESERVED_WORD); }
}

<YYINITIAL> {

	{LineTerminator}				{ addNullToken(); return firstToken; }

	{WhiteSpace}+					{ addToken(Token.WHITESPACE); }

	/* String/Character Literals. */
	{CharLiteral}					{ addToken(Token.LITERAL_CHAR); }
	{UnclosedCharLiteral}			{ addToken(Token.ERROR_CHAR); /*addNullToken(); return firstToken;*/ }
	{StringLiteral}				{ addToken(Token.LITERAL_STRING_DOUBLE_QUOTE); }
	{UnclosedStringLiteral}			{ addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken; }

	/* Labels. */
	{Label}						{ addToken(Token.PREPROCESSOR); }

	^%({Letter}|{Digit})*			{ addToken(Token.FUNCTION); }

	/* Comment Literals. */
	{CommentBegin}.*				{ addToken(Token.COMMENT_EOL); addNullToken(); return firstToken; }

	/* Operators. */
	{Operator}					{ addToken(Token.OPERATOR); }

	/* Numbers */
	{Number}						{ addToken(Token.LITERAL_NUMBER_DECIMAL_INT); }

	/* Ended with a line not in a string or comment. */
	<<EOF>>						{ addNullToken(); return firstToken; }

	/* Catch any other (unhandled) characters. */
	{Identifier}					{ addToken(Token.IDENTIFIER); }
	.							{ addToken(Token.IDENTIFIER); }

}
