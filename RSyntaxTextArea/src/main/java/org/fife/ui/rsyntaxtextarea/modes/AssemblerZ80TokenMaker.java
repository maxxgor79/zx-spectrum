/* The following code was generated by JFlex 1.4.1 on 26/02/2024, 7:16 pm */

/*
 * 26/02/2024
 *
 * AssemblerZ80TokenMaker.java - An object that can take a chunk of text and
 * return a linked list of tokens representing X86 assembler.
 * 
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * This class takes plain text and returns tokens representing x86
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
 *   <li>The generated <code>AssemblerX86TokenMaker.java</code> file will contain two
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

public class AssemblerZ80TokenMaker extends AbstractJFlexTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\21\1\20\1\0\1\21\23\0\1\54\1\25\1\15\1\3"+
    "\1\3\1\24\1\25\1\16\2\0\1\23\1\23\1\14\1\23\1\50"+
    "\1\23\1\6\1\11\6\12\2\2\1\22\1\17\1\25\1\25\1\25"+
    "\1\51\1\0\1\4\1\10\1\30\1\33\1\34\1\35\1\13\1\5"+
    "\1\26\1\53\1\1\1\31\1\45\1\27\1\46\1\42\1\47\1\44"+
    "\1\36\1\41\1\32\1\37\1\40\1\7\1\52\1\43\1\14\1\0"+
    "\1\14\1\25\1\1\1\0\1\4\1\10\1\30\1\33\1\34\1\35"+
    "\1\13\1\5\1\26\1\53\1\1\1\31\1\45\1\27\1\46\1\42"+
    "\1\47\1\44\1\36\1\41\1\32\1\37\1\40\1\7\1\52\1\43"+
    "\1\0\1\25\1\0\1\25\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\2\1\1\2\1\1\2\3\1\2\1\1\1\3"+
    "\2\2\1\4\1\5\1\6\1\7\1\10\1\11\1\3"+
    "\1\1\5\3\2\1\1\3\4\1\1\12\1\1\1\13"+
    "\1\1\2\2\2\1\1\3\1\1\1\3\1\1\1\2"+
    "\2\1\1\2\1\14\1\15\1\3\3\16\4\1\2\16"+
    "\1\1\1\12\1\3\1\12\1\1\1\16\12\1\1\16"+
    "\1\1\1\16\3\1\1\16\10\0\3\16\1\3\1\2"+
    "\2\16\2\1\1\13\1\1\1\13\1\2\3\1\3\16"+
    "\1\1\1\16\2\0\1\12\13\0\11\1\2\0\2\13"+
    "\6\0\2\16\5\1\1\13\2\1\12\0\5\1\5\0"+
    "\1\13\7\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\132\0\207\0\264\0\341\0\u010e\0\u013b"+
    "\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276\0\u02a3"+
    "\0\132\0\u02d0\0\132\0\u02fd\0\u032a\0\u0357\0\u0384\0\u03b1"+
    "\0\u03de\0\u040b\0\u0438\0\u0465\0\u0492\0\u04bf\0\u04ec\0\u0519"+
    "\0\u0546\0\u0573\0\u05a0\0\u05a0\0\u040b\0\207\0\341\0\u05cd"+
    "\0\u05fa\0\u0627\0\u0654\0\207\0\u0681\0\u06ae\0\u06db\0\u0708"+
    "\0\u040b\0\132\0\132\0\u0735\0\u0762\0\u078f\0\207\0\u07bc"+
    "\0\u07e9\0\u0816\0\u0843\0\u0870\0\u089d\0\u08ca\0\u040b\0\u08f7"+
    "\0\207\0\u0924\0\u0951\0\u097e\0\u09ab\0\u09d8\0\u0a05\0\u0a32"+
    "\0\u0a5f\0\u0a8c\0\u0ab9\0\u0ae6\0\u0b13\0\u0b40\0\u0b6d\0\u0b9a"+
    "\0\u0bc7\0\u0bf4\0\u0c21\0\u0c4e\0\u0c7b\0\u0ca8\0\u0cd5\0\u0d02"+
    "\0\u0d2f\0\u0d5c\0\u0d89\0\u0db6\0\u0de3\0\u0e10\0\u040b\0\u05a0"+
    "\0\u0681\0\u06db\0\u0e3d\0\u0e6a\0\u0e97\0\u0ec4\0\u0ef1\0\207"+
    "\0\u0f1e\0\u0f4b\0\u0f78\0\u0fa5\0\u0fd2\0\u0fff\0\u102c\0\u1059"+
    "\0\u1086\0\u10b3\0\u10e0\0\132\0\u110d\0\u113a\0\u1167\0\u1194"+
    "\0\u11c1\0\u11ee\0\u121b\0\u1248\0\u1275\0\u12a2\0\u12cf\0\u12fc"+
    "\0\u1329\0\u1356\0\u1383\0\u13b0\0\u13dd\0\u140a\0\u1437\0\u1464"+
    "\0\u1491\0\u14be\0\u14eb\0\132\0\u1518\0\u1545\0\u1572\0\u159f"+
    "\0\u15cc\0\u15f9\0\132\0\u1626\0\u1653\0\u1680\0\u16ad\0\u16da"+
    "\0\u1707\0\u1734\0\u1761\0\u178e\0\u17bb\0\u17e8\0\u1815\0\u1842"+
    "\0\u186f\0\u189c\0\u18c9\0\u18f6\0\u1923\0\u1950\0\u197d\0\u19aa"+
    "\0\u19d7\0\u1a04\0\u1a31\0\u1a5e\0\u1a8b\0\u1ab8\0\u1ae5\0\u1b12"+
    "\0\u1b3f\0\u1b6c\0\u1b99\0\u1bc6\0\u1bf3\0\u1c20\0\u1c4d\0\u1c7a";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\4\1\3\1\16\1\17\1\20"+
    "\1\21\1\22\1\3\3\23\1\24\1\25\1\26\1\27"+
    "\1\4\1\30\1\31\1\32\1\33\3\4\1\34\1\4"+
    "\1\35\1\36\1\37\1\4\1\40\1\3\1\4\1\41"+
    "\1\22\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\4\1\3\1\16\1\17"+
    "\1\20\1\21\1\22\1\3\1\23\1\42\1\23\1\24"+
    "\1\25\1\26\1\27\1\4\1\30\1\31\1\32\1\33"+
    "\3\4\1\34\1\4\1\35\1\36\1\37\1\4\1\40"+
    "\1\3\1\4\1\41\1\22\55\0\1\43\2\4\1\43"+
    "\10\4\1\0\3\43\2\0\1\44\2\0\1\43\22\4"+
    "\1\0\1\43\2\4\1\0\1\43\1\4\1\5\1\43"+
    "\1\45\1\46\1\5\1\4\1\45\2\5\1\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\2\4\1\45\2\4"+
    "\3\45\12\4\1\0\1\43\2\4\3\0\1\47\1\0"+
    "\1\47\1\0\1\47\1\0\3\47\15\0\1\47\2\0"+
    "\3\47\17\0\1\43\1\4\1\45\1\43\1\45\1\46"+
    "\1\45\1\4\3\45\1\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\1\4\1\50\1\45\2\4\1\51\1\45"+
    "\1\52\12\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\1\53\7\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\3\4\1\54\16\4\1\0\1\43\2\4\1\0"+
    "\1\43\1\4\1\5\1\43\1\45\1\46\1\14\1\55"+
    "\1\56\1\14\1\15\1\46\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\45\2\4\3\45\12\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\20\4\1\57\1\4"+
    "\1\0\1\43\2\4\1\0\1\43\1\4\1\45\1\43"+
    "\1\45\1\46\1\45\1\4\3\45\1\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\1\60\1\4\1\32\2\4"+
    "\3\45\12\4\1\0\1\43\2\4\1\0\1\43\1\4"+
    "\1\5\1\43\1\45\1\46\1\14\1\4\1\61\1\14"+
    "\1\15\1\46\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\2\4\1\45\2\4\3\45\12\4\1\0\1\43\2\4"+
    "\1\0\1\43\1\4\1\5\1\43\1\45\1\46\1\15"+
    "\1\4\1\45\2\15\1\46\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\45\2\4\3\45\12\4\1\0"+
    "\1\43\2\4\1\0\15\16\1\62\37\16\16\17\1\63"+
    "\36\17\20\20\1\0\34\20\21\0\1\22\32\0\1\22"+
    "\1\43\2\4\1\43\3\4\1\64\4\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\1\4\1\65\15\4\1\66"+
    "\2\4\1\0\1\43\1\64\1\4\1\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\2\4\1\67\3\4\1\70\6\4\1\67\2\4\1\71"+
    "\1\4\1\0\1\43\2\4\1\0\1\43\1\4\1\45"+
    "\1\43\1\72\1\46\1\45\1\4\3\45\1\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\2\4\1\73\2\4"+
    "\3\45\4\4\1\74\5\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\5\4\1\75\14\4\1\0\1\43\2\4"+
    "\1\0\1\43\1\4\1\45\1\43\1\76\1\46\1\45"+
    "\1\4\1\77\2\45\1\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\1\67\1\4\1\45\2\4\1\77\1\100"+
    "\1\45\2\4\1\101\7\4\1\0\1\43\1\4\1\102"+
    "\1\0\1\43\1\4\1\45\1\43\1\45\1\46\1\45"+
    "\1\103\3\45\1\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\1\67\1\104\1\105\2\4\3\45\11\4\1\106"+
    "\1\0\1\43\2\4\1\0\1\43\1\4\1\45\1\43"+
    "\1\45\1\46\1\45\1\4\3\45\1\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\2\4\1\45\2\4\3\45"+
    "\12\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\1\107\7\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\2\4\1\110\1\111\1\112\1\113\1\60\5\4\1\54"+
    "\1\4\1\111\3\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\4\4\1\114\11\4\1\115\1\4\1\71\1\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\3\4\1\116"+
    "\2\4\1\117\1\4\1\60\5\4\1\120\3\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\6\4\1\121\13\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\4\4\1\122"+
    "\6\4\1\123\2\4\1\124\3\4\1\0\1\43\2\4"+
    "\27\0\1\125\4\0\1\126\1\127\1\0\1\130\3\0"+
    "\1\131\1\0\1\132\1\133\1\134\6\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\14\4\1\135\1\4\1\136\3\4\1\0\1\43\2\4"+
    "\2\0\2\42\1\0\10\42\12\0\22\42\2\0\2\42"+
    "\1\0\14\43\1\0\3\43\2\0\1\43\2\0\23\43"+
    "\1\0\3\43\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\5\4\1\67\14\4"+
    "\1\0\1\43\2\4\1\0\1\43\1\4\1\45\1\43"+
    "\1\45\1\46\1\45\1\4\3\45\1\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\2\4\1\137\2\4\1\137"+
    "\2\45\12\4\1\0\1\43\2\4\1\0\1\43\1\4"+
    "\1\45\1\43\1\45\1\46\1\45\1\4\3\45\1\4"+
    "\1\0\1\43\1\140\1\43\2\0\1\44\2\0\1\43"+
    "\2\4\1\45\2\4\3\45\12\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\3\4\1\60\16\4\1\0\1\43"+
    "\2\4\1\0\1\43\1\4\1\141\1\43\1\141\1\4"+
    "\1\141\1\4\3\141\1\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\141\2\4\3\141\12\4\1\0"+
    "\1\43\2\4\1\0\1\43\1\4\1\45\1\43\1\45"+
    "\1\46\1\56\1\4\1\45\1\56\1\45\1\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\2\4\1\45\2\4"+
    "\3\45\12\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\16\4\1\67\3\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\13\4\1\67\6\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\1\4\1\54\6\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\3\4\1\54\16\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\1\142\1\4\1\143"+
    "\2\4\1\142\14\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\1\144\6\4\1\101\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\7\4\1\67\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\14\4\1\67\5\4\1\0\1\43\2\4"+
    "\1\0\1\43\1\4\1\45\1\43\1\45\1\46\1\45"+
    "\1\4\3\45\1\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\2\4\1\45\1\145\1\4\3\45\12\4\1\0"+
    "\1\43\2\4\1\0\1\43\1\4\1\45\1\43\1\45"+
    "\1\46\1\45\1\4\3\45\1\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\2\4\1\45\2\4\2\45\1\137"+
    "\12\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\10\4\1\0\3\43\2\0\1\44\2\0\1\43\1\142"+
    "\2\4\1\67\1\4\1\142\14\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\1\142\4\4\1\142\14\4\1\0"+
    "\1\43\2\4\1\0\1\43\1\4\1\45\1\43\1\137"+
    "\1\46\1\45\1\4\3\45\1\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\2\4\1\45\2\4\3\45\12\4"+
    "\1\0\1\43\2\4\1\0\1\43\1\4\1\45\1\43"+
    "\1\45\1\46\1\45\1\4\3\45\1\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\2\4\1\137\2\4\2\45"+
    "\1\146\12\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\1\4\1\147\20\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\3\4\1\67\4\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\5\4\1\150\14\4\1\0\1\43\2\4"+
    "\1\0\1\43\1\4\1\45\1\43\1\45\1\151\1\45"+
    "\1\4\3\45\1\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\2\4\1\45\2\4\3\45\12\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\10\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\4\4\1\150\15\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\11\4\1\152\10\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\7\4\1\67"+
    "\12\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\1\67\7\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\3\4\1\67\16\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\4\4\1\67\3\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\67\17\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\10\4\1\153\11\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\10\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\1\154\21\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\1\67\7\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\2\4\1\155\17\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\10\4\1\156"+
    "\2\4\1\157\6\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\1\67\7\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\155\2\4\1\67\14\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\10\4\1\160\11\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\13\4\1\161"+
    "\6\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\10\4\1\0\3\43\2\0\1\44\2\0\1\43\1\57"+
    "\4\4\1\57\14\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\7\4\1\150\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\22\4\1\0\1\43\2\4\30\0\1\162"+
    "\15\0\1\163\17\0\1\164\22\0\1\164\1\165\3\0"+
    "\1\164\43\0\1\166\1\167\16\0\1\170\11\0\1\171"+
    "\114\0\1\172\44\0\1\173\54\0\1\174\64\0\1\175"+
    "\10\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\176"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\22\4\1\0\1\43\2\4\1\177\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\3\4\1\200\16\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\7\4\1\201\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\22\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\3\4\1\135\16\4\1\0\1\43\2\4"+
    "\1\0\1\43\1\4\1\45\1\43\1\45\1\46\1\45"+
    "\1\4\1\77\2\45\1\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\1\202\1\4\1\45\2\4\1\203\2\45"+
    "\2\4\1\101\3\4\1\204\3\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\15\4\1\67\4\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\10\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\20\4\1\150\1\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\6\4\1\205\13\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\1\4"+
    "\1\67\6\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\22\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\10\4\1\0\3\43\2\0\1\44\2\0\1\43\1\4"+
    "\1\206\20\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\1\67\7\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\22\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\20\4\1\207\1\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\2\67\20\4\1\0\1\43\2\4\1\176\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\10\4\1\210\11\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\1\67\4\4\1\67\14\4\1\0\1\43"+
    "\2\4\31\0\1\211\30\0\1\212\6\0\1\164\76\0"+
    "\1\213\52\0\1\214\26\0\1\215\101\0\1\214\61\0"+
    "\1\216\43\0\1\217\64\0\1\220\54\0\1\221\31\0"+
    "\1\214\70\0\1\222\1\223\11\0\1\224\1\223\1\0"+
    "\1\223\36\0\1\222\1\223\12\0\1\223\11\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\4\4\1\225\15\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\6\4\1\101\13\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\1\4\1\226\20\4\1\0\1\43"+
    "\2\4\1\0\1\43\1\4\1\45\1\43\1\45\1\46"+
    "\1\45\1\4\3\45\1\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\2\4\1\45\2\4\3\45\2\4\1\101"+
    "\7\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\10\4\1\0\3\43\2\0\1\44\2\0\1\43\6\4"+
    "\1\227\13\4\1\0\1\43\2\4\1\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\12\4\1\230\1\231\6\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\13\4\1\232\6\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\4\4\1\233\15\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\1\234\7\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\22\4\1\0\1\43"+
    "\2\4\32\0\1\235\36\0\1\236\51\0\1\164\15\0"+
    "\1\237\4\0\1\240\4\0\1\164\3\0\1\241\56\0"+
    "\1\242\42\0\1\243\47\0\1\244\73\0\1\245\44\0"+
    "\1\246\46\0\1\223\12\0\1\223\45\0\1\223\11\0"+
    "\1\223\6\0\1\43\2\4\1\43\10\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\5\4\1\226\14\4\1\0"+
    "\1\43\2\4\1\0\1\43\2\4\1\43\10\4\1\0"+
    "\3\43\2\0\1\44\2\0\1\43\6\4\1\150\13\4"+
    "\1\0\1\43\2\4\1\0\1\43\2\4\1\43\10\4"+
    "\1\0\3\43\2\0\1\44\2\0\1\43\10\4\1\101"+
    "\11\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\1\247\7\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\22\4\1\0\1\43\2\4\1\0\1\43\2\4\1\43"+
    "\1\250\7\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\15\4\1\251\4\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\3\4\1\252\16\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\10\4\1\0\3\43\2\0\1\44"+
    "\2\0\1\43\16\4\1\253\3\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\7\4\1\226\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\22\4\1\0\1\43\2\4"+
    "\33\0\1\254\56\0\1\164\47\0\1\255\65\0\1\164"+
    "\50\0\1\256\71\0\1\214\43\0\1\257\1\260\54\0"+
    "\1\261\45\0\1\262\26\0\1\263\50\0\1\43\2\4"+
    "\1\43\10\4\1\0\3\43\2\0\1\44\2\0\1\43"+
    "\11\4\1\150\10\4\1\0\1\43\2\4\1\0\1\43"+
    "\2\4\1\43\10\4\1\0\3\43\2\0\1\44\2\0"+
    "\1\43\14\4\1\150\5\4\1\0\1\43\2\4\1\0"+
    "\1\43\2\4\1\43\3\4\1\150\4\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\22\4\1\0\1\43\2\4"+
    "\1\0\1\43\2\4\1\43\10\4\1\0\3\43\2\0"+
    "\1\44\2\0\1\43\1\4\1\150\20\4\1\0\1\43"+
    "\2\4\1\0\1\43\2\4\1\43\10\4\1\0\3\43"+
    "\2\0\1\44\2\0\1\43\2\4\1\201\17\4\1\0"+
    "\1\43\2\4\34\0\1\255\55\0\1\214\56\0\1\164"+
    "\22\0\1\264\54\0\1\265\36\0\1\266\42\0\1\267"+
    "\67\0\1\270\23\0\1\255\100\0\1\214\57\0\1\214"+
    "\21\0\1\214\74\0\1\214\55\0\1\236\24\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7335];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\15\1\1\11\1\1\1\11\36\1\2\11"+
    "\41\1\10\0\25\1\2\0\1\11\13\0\11\1\2\0"+
    "\1\1\1\11\6\0\1\11\11\1\12\0\5\1\5\0"+
    "\1\1\7\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[184];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */


	/**
	 * Constructor.  We must have this here as JFLex does not generate a
	 * no parameter constructor.
	 */
	public AssemblerZ80TokenMaker() {
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




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AssemblerZ80TokenMaker(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public AssemblerZ80TokenMaker(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 190) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }

  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      if (zzMarkedPosL > zzStartRead) {
        switch (zzBufferL[zzMarkedPosL-1]) {
        case '\n':
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          zzAtBOL = true;
          break;
        case '\r': 
          if (zzMarkedPosL < zzEndReadL)
            zzAtBOL = zzBufferL[zzMarkedPosL] != '\n';
          else if (zzAtEOF)
            zzAtBOL = false;
          else {
            boolean eof = zzRefill();
            zzMarkedPosL = zzMarkedPos;
            zzEndReadL = zzEndRead;
            zzBufferL = zzBuffer;
            if (eof) 
              zzAtBOL = false;
            else 
              zzAtBOL = zzBufferL[zzMarkedPosL] != '\n';
          }
          break;
        default:
          zzAtBOL = false;
        }
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      if (zzAtBOL)
        zzState = ZZ_LEXSTATE[zzLexicalState+1];
      else
        zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 14: 
          { addToken(Token.RESERVED_WORD);
          }
        case 15: break;
        case 1: 
          { addToken(Token.IDENTIFIER);
          }
        case 16: break;
        case 12: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 17: break;
        case 10: 
          { addToken(Token.FUNCTION);
          }
        case 18: break;
        case 6: 
          { addToken(Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 19: break;
        case 8: 
          { addToken(Token.WHITESPACE);
          }
        case 20: break;
        case 3: 
          { addToken(Token.VARIABLE);
          }
        case 21: break;
        case 11: 
          { addToken(Token.PREPROCESSOR);
          }
        case 22: break;
        case 5: 
          { addToken(Token.ERROR_CHAR); /*addNullToken(); return firstToken;*/
          }
        case 23: break;
        case 9: 
          { addToken(Token.OPERATOR);
          }
        case 24: break;
        case 2: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 25: break;
        case 13: 
          { addToken(Token.LITERAL_CHAR);
          }
        case 26: break;
        case 4: 
          { addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken;
          }
        case 27: break;
        case 7: 
          { addNullToken(); return firstToken;
          }
        case 28: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 185: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
