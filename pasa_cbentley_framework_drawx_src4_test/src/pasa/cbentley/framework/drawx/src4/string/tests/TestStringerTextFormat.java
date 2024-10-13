package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;

public abstract class TestStringerTextFormat extends TestStringerAbstract {

   public void testMultiLines_IvyGreen() {
      char[] data = uc.getIOU().readFileAsChars("/the_ivy_green.txt", "UTF-8");

      assertNotNull(data);

      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_1_TINY, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      int margin = 5;
      int areaW = 270;
      int areaH = 400;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.ToStringSetDebugArea(true);
      st.setTextFigure(strFig);
      st.setString(data, 0, data.length);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setFormatWordwrap(WORDWRAP_0_NONE);
      st.setBreakOnArea();
      st.setSpaceTrimManager(SPACETRIM_0_NONE);

      st.buildFxAndMeter();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_IvyGreen", st, imageW, imageH);

      assertEquals(34, st.getNumOfLines());

      ByteObject anchor = drc.getAnchorFactory().getCenterCenter();
      st.setAnchor(anchor);
      st.buildAgain();

      genericTestImg("MultiLines_IvyGreen_AlignCenter", st, imageW, imageH);

      anchor = drc.getAnchorFactory().getRightCenter();
      st.setAnchor(anchor);
      st.buildAgain();

      genericTestImg("MultiLines_IvyGreen_RightCenter", st, imageW, imageH);

      st.setAnchor(null);
      st.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);
      st.buildAgain();

      genericTestImg("MultiLines_IvyGreen_Justified", st, imageW, imageH);

   }

   public void testTrimFitHeight() {
      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      int areaW = 200;
      int areaH = 40;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWH(areaW - 5, areaH - 5); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(this.data);
      st.setTextFigure(strFig);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setFormatLineWrap(WORDWRAP_1_ANYWHERE); //
      st.setBreakMaxLines(0); //the height decides the number of lines
      st.setTrimArtifacts(true);

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();
      assertEquals("Hello I'm Joe! I would", sm.getLineString(0));
      assertEquals("like to eat some me..", sm.getLineString(1));

      genericTestImg("testTrimFitHeight", st);

      assertEquals(true, st.hasFlagState(ITechStringer.STATE_04_TRIMMED));
      assertEquals(2, st.getNumOfLines());
      
      assertEquals(21, sm.getLine(1).getNumCharVisible());
      assertEquals(21, st.getLastNumDrawnChars()); //there are 65 drawn so indeed 2 spaces were ignored..
      
   }

   /**
    * Test the difference between break characters set or any char breaks.
    * <br>
    * <br>
    * 
    */
   public void testBreakWidthBigSingleLine() {

      ByteObject textFigure = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);

      int areaW = 100;
      int areaH = 100;

      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      String data = "qwertyuiopasdfghjklzxcvbnm";
      st.setString(data);
      st.setTextFigure(textFigure);
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.buildFxAndMeter();

      genericTestImg("BreakWidth_BigSingleLine_Mono_Plain", st);

      textFigure = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_1_BOLD, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      st.setTextFigure(textFigure);
      st.buildFxAndMeter();

      genericTestImg("BreakWidth_BigSingleLine_Mono_Bold", st);

      textFigure = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_2_ITALIC, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(textFigure);

      genericTestImg("BreakWidth_BigSingleLine_Mono_Italic", st);

      ByteObject strProp = facFigure.getFigString(FACE_02_PROPORTIONAL, STYLE_0_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      assertEquals(3, st.getNumOfLines());

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testBreakWidthBigSingleLine", LVL_05_FINE, false);

      genericTestImg("BreakWidth_BigSingleLine_Prop_Plain", st);

      strProp = facFigure.getFigString(FACE_02_PROPORTIONAL, STYLE_1_BOLD, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      genericTestImg("BreakWidth_BigSingleLine_Prop_Bold", st);

      strProp = facFigure.getFigString(FACE_02_PROPORTIONAL, STYLE_2_ITALIC, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      genericTestImg("BreakWidth_BigSingleLine_Prop_Italic", st);
   }

   public void testJustifySimple() {

      resetFontToDefaults();

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.ToStringSetDebugArea(true);
      stringer.ToStringSetDebugBreakLines(true);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.setTextFigure(textFigure);
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(220, sm.getLineWidth(1));
      assertEquals(220, sm.getLineWidth(2));
      assertEquals(160, sm.getLineWidth(3));

      
      genericTestImg("Justify_Simple_Trim_Normal", stringer);

      
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);
      stringer.buildFxAndMeter();

      assertEquals(4, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));
      assertEquals(240, sm.getLineWidth(3));

      assertEquals(0, sm.getLineY(0));
      assertEquals(19, sm.getLineY(1));
      assertEquals(38, sm.getLineY(2));
      assertEquals(57, sm.getLineY(3));

      genericTestImg("Justify_Simple_Trim_Justified", stringer);

   }

   public void testBreakWidth_1Char() {

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      int areaW = 100;
      int areaH = 200;
      Stringer st = new Stringer(dc);
      st.ToStringSetDebugArea(true);

      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setBreakWidth(8); //very small

      st.buildForDisplayWith(textFigure, "Bonjour");

      StringMetrics sm = st.getMetrics();
      assertEquals(st.getLen(), st.getNumOfLines());
      assertEquals("B", sm.getLineString(0));
      assertEquals("o", sm.getLineString(1));

      genericTestImg("BreakWidth_1Char", st);

   }

   public void testBreakWidth_2Chars() {

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.ToStringSetDebugArea(true);

      int margin = 5;
      int areaW = 50;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setTextFigure(textFigure);
      st.setBreakWidth(22); //very small
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setString("Bonjour");
      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();

      genericTestImg("BreakWidth_2Chars", st, imageW, imageH);

      assertEquals("Bo", sm.getLineString(0));
      assertEquals("nj", sm.getLineString(1));
      assertEquals("ou", sm.getLineString(2));
      assertEquals("r", sm.getLineString(3));

   }

   public void testMultiLines_Natural() {
      String data = uc.getIOU().readFileAsStringWindows("/string_multilines.txt", "UTF-8");

      ByteObject strFig = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int areaW = 410;
      int areaH = 410;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL); //we have tabs in this text file
      st.setBreakWidth(100); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.setTextFigure(strFig);

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();

      assertEquals("Business DrawingBoard#inputKey #Current CK", sm.getLineString(0));
      assertEquals(42, sm.getLine(0).getNumCharVisible());
      assertEquals(42, sm.getLine(0).getLengthInStringer());

      //what about tabs?
      assertEquals(40, st.getNumOfLines());

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural", st);

   }

   public void testMultiLines_Natural2() {
      String data = uc.getIOU().readFileAsStringWindows("/string_multilines.txt", "UTF-8");

      ByteObject strFig = facFigure.getFigString(FACE_00_SYSTEM, STYLE_0_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int areaW = 350;
      int areaH = 410;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setString(data);
      st.setTextFigure(strFig);
      st.setDirectiveTab(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      st.setDirectiveNewLine(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      st.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      st.setBreakOnArea();
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setShowHiddenChars(true);
      st.ToStringSetDebugArea(true);

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();

      assertEquals("Business→DrawingBoard#inputKey·#Current·CK↵→→→#ClassKey·Grid↵→→→FLAG_", sm.getLineString(0));
      assertEquals(69, sm.getLine(0).getNumCharVisible());
      assertEquals(69, sm.getLine(0).getLengthInStringer());

      
      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural2", st);

   }

   public void testAnchorCenter5Lines() {
      ByteObject strFig = getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab();

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(5, 5, 120, 120);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.setTextFigure(strFig);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);

      ByteObject anchor = facAnchor.getCenterCenter();
      st.setAnchor(anchor);

      st.buildFxAndMeter();

      assertEquals(67, data.length());

      StringMetrics sm = st.getMetrics();

      genericTestImg("AnchorCenter5Lines", st);

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 5);

      assertEquals("Hello I'm Joe!", sm.getLineString(0));
      assertEquals("I would like", sm.getLineString(1));
      assertEquals("to eat some", sm.getLineString(2));
      assertEquals("meat and fish", sm.getLineString(3));
      assertEquals("and potatoes.", sm.getLineString(4));

   }

   public void testTrim3Lines() {
      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(5, 5, 115, 75);
      st.setBreakOnArea2();
      st.ToStringSetDebugArea(true);
      st.setTextFigure(strFig);
      st.setString(data);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setBreakMaxLines(3);
      st.setTrimArtifacts(true);

      st.buildFxAndMeter();

      assertEquals(67, data.length());

      StringMetrics sm = st.getMetrics();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 3);

      assertEquals("Hello I'm Joe!", sm.getLineString(0));
      assertEquals("I would like", sm.getLineString(1));
      assertEquals("to eat so..", sm.getLineString(2));

      assertEquals(11, sm.getLine(2).getNumCharVisible());
      assertEquals(11, sm.getLine(2).getLengthInStringer());

      genericTestImg("Trim3Lines", st);

      assertEquals("Hello I'm Joe! I would like to eat so..", st.getDisplayedString());
   }

   public void testHiddenChars() {
      ByteObject textFigure = facFigure.getFigString(FACE_00_SYSTEM, STYLE_0_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);


      char[] chars = "###Life\n is\t a\r\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      stringer.setAreaXYWH(5, 5, 215, 35);
      stringer.setBreakOnArea2();
      stringer.ToStringSetDebugArea(true);

      
      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(0);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      genericTestImg("HiddenChars_1line", stringer);

      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);

      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life·is·a·long·real?", sm.getLineString(0));

      genericTestImg("HiddenChars_Ignored", stringer);

   }

   public void testMultiLines_Simple() {

      ByteObject strFig = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer stringer = new Stringer(dc);
      int areaW = 195;
      int areaH = 100;
      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakOnArea2();
      stringer.ToStringSetDebugArea(true);
      stringer.setString("I am free.\nI love it.");
      stringer.setTextFigure(strFig);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);

      stringer.buildFxAndMeter();

      genericTestImg("MultiLines_Simple", stringer);

      assertEquals(2, stringer.getNumOfLines());

      stringer.setString("I am free.\nI love it. This line should cut somewhere.");
      stringer.buildFxAndMeter();

      genericTestImg("MultiLines_Simple_WithCut", stringer);

   }

   public void testTrimSingleLine() {

      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      int areaW = 200;
      int areaH = 50;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      st.setTextFigure(strFig);
      st.setString(this.data);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setBreakMaxLines(1);
      st.setTrimArtifacts(true);
      st.buildFxAndMeter();

      genericTestImg("TrimSingleLine", st);

      StringMetrics sm = st.getMetrics();
      assertEquals(1, st.getNumOfLines());
      assertEquals("Hello I'm Joe! I wou..", sm.getLineString(0));
      assertEquals(true, st.hasFlagState(ITechStringer.STATE_04_TRIMMED));

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringerTextFormat.class, 242);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringerTextFormat.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

}
