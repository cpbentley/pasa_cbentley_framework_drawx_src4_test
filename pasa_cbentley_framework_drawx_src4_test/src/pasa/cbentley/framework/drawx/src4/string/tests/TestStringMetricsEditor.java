package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.string.StringFx;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.StringerEditor;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;
import pasa.cbentley.testing.engine.ConfigUTest;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetricsEditor extends TestCaseFrameworkUiPluggedDrawX implements ITechFont, ITechStringer {

   public TestStringMetricsEditor() {
      setTestFlag(TEST_FLAG_08_DEBUG_METHOD_NAMES, true);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, false);
   }

   public void setupAbstractDrawX() {
   }

   public void testEditorAppendChar() {
      Stringer stringer = new Stringer(dc);
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure);
      StringerEditor editor = stringer.getEditor();

      char[] cs = "Hello".toCharArray();

      editor.appendChar('H');

      assertEquals(stringer.getLen(), 1);

      assertEquals("H", stringer.getDisplayedString());

      editor.appendChar('e');

      assertEquals(stringer.getLen(), 2);
      assertEquals("He", stringer.getDisplayedString());

   }
   
   public void testDeleteChar() {
      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);

      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("Life is a long snake. It takes", sm.getLineString(0));
      assertEquals("forever to reach the tail. And once", sm.getLineString(1));
      assertEquals("you get to it. What?", sm.getLineString(2));

      
      assertEquals(' ', stringer.getCharVisibleAtRelative(4)); 
      assertEquals(' ', stringer.getCharVisibleAtRelative(7)); 
      assertEquals(' ', stringer.getCharVisibleAtRelative(9)); 
      assertEquals(' ', stringer.getCharVisibleAtRelative(14)); 
      assertEquals(13, sm.getCharWidth(4)); 
      assertEquals(12, sm.getCharWidth(7));
      assertEquals(12, sm.getCharWidth(9));
      assertEquals(12, sm.getCharWidth(14));
      
      StringerEditor editor = stringer.getEditor();
      
      int caretIndex = editor.getCaretIndex();
      assertEquals(0, caretIndex);
      
      editor.deleteCharAt(4);
      //rebuild it automatic with those methods
      
      assertEquals(3, sm.getNumOfLines());

      assertEquals("Lifeis a long snake. It takes forever", sm.getLineString(0));
      assertEquals("to reach the tail. And once you get", sm.getLineString(1));
      assertEquals(12, sm.getLine(2).getLengthInStringer());
      assertEquals("to it. What?", sm.getLineString(2));


   }
   
   public void testDeleteChar2() {
      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);

      char[] chars = "Life is a long snake. It takes forever to reach the tail. And once you get to it. What?".toCharArray();
      int offset = 0;
      int len = chars.length;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);

      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("Life is a long snake. It takes", sm.getLineString(0));
      assertEquals("forever to reach the tail. And once", sm.getLineString(1));
      assertEquals("you get to it. What?", sm.getLineString(2));
      
      StringerEditor editor = stringer.getEditor();
      
      editor.deleteCharsAt(5,2);
      
      assertEquals(3, sm.getNumOfLines());

      assertEquals("Life  a long snake. It takes forever", sm.getLineString(0));
      assertEquals("to reach the tail. And once you get", sm.getLineString(1));
      assertEquals("to it. What?", sm.getLineString(2));

   }

   public void testEditorAppendLine() {

      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim();
      ByteObject strAuxNewLine = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE, strAuxFormat, strAuxNewLine);

      Stringer stringer = new Stringer(dc);
      stringer.setTextFigure(textFigure);

      //before using. must build
      stringer.buildAgain();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("", sm.getLineString(0));
      assertEquals(true, sm.getLine(0).isFictiveLine());

      //cannot have an interval of zero size
      assertEquals(0, stringer.getIntervalsOfLeaves().getSize());

      StringerEditor editor = stringer.getEditor();

      ByteObject fx1 = facStringFx.getFxEffectColor(FULLY_OPAQUE_BLUE);
      editor.appendLine("Line 01", fx1);
      stringer.buildAgain();

      assertEquals(2, sm.getNumOfLines());
      assertEquals("", sm.getLineString(0));
      assertEquals("Line 01", sm.getLineString(1));
      assertEquals(1, stringer.getIntervalsOfLeaves().getSize());

      ByteObject fx2 = facStringFx.getFxEffectColor(FULLY_OPAQUE_PURPLE);
      editor.appendLine("Line 02", fx2);
      stringer.buildAgain();
      assertEquals(2, stringer.getIntervalsOfLeaves().getSize());

      assertEquals(3, sm.getNumOfLines());
      assertEquals("", sm.getLineString(0));
      assertEquals("Line 01", sm.getLineString(1));
      assertEquals("Line 02", sm.getLineString(2));

      ByteObject fx3 = facStringFx.getFxEffectColor(FULLY_OPAQUE_GREEN);
      editor.appendLine("Line 03 with more words", fx3);
      stringer.buildAgain();

      assertEquals(4, sm.getNumOfLines());
      assertEquals("", sm.getLineString(0));
      assertEquals("Line 01", sm.getLineString(1));
      assertEquals("Line 02", sm.getLineString(2));
      assertEquals("Line 03 with more words", sm.getLineString(3));

   }
}
