package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechHostFeatureDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.string.StringFx;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;
import pasa.cbentley.testing.engine.ConfigUTest;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetrics extends TestCaseFrameworkUiPluggedDrawX implements ITechFont, ITechStringer {

   private static boolean isStaticSetupDone;

   String                 data = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   public TestStringMetrics() {
      setTestFlag(TEST_FLAG_08_DEBUG_METHOD_NAMES, true);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, false);
   }

   protected IConfigU createConfigU() {
      ConfigUTest configTest = new ConfigUTest();
      configTest.setLogConfigurator(new ILogConfigurator() {

         public void apply(IDLogConfig log) {

            log.setClassNegative(UCtx.class, true);

            log.setLevelGlobal(ITechLvl.LVL_03_FINEST);
            log.setFlagTag(FLAG_17_PRINT_TEST, true);
            log.setFlagTag(FLAG_08_PRINT_EXCEPTION, true);
            log.setFlagTag(FLAG_02_PRINT_NULL, true);
            log.setFlagTag(FLAG_15_PRINT_DATA, true);

            log.setFlagTag(FLAG_20_PRINT_INIT, false);
         }
      });

      return configTest;
   }

   private ByteObject getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab(int maxLines) {
      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim(maxLines);
      ByteObject auxSpecials = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();
      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE, strAuxFormat, auxSpecials);
      return strFig;
   }

   private Stringer getTestSetup1() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
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
      return stringer;
   }

   private void setFontsToMonoidAleo() {
      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechHostFeatureDraw.SUP_ID_06_CUSTOM_FONTS);
      //the family name must match exactly.. so no-
      fontCustomizer.setFontFamilyMonospace("Monoid");
      fontCustomizer.setFontFamilyProportional("Aleo");
      fontCustomizer.setFontFamilySystem(null);
   }

   public void setupAbstractDrawX() {
      if (!isStaticSetupDone) {
         setupTestStringerStatic();
         isStaticSetupDone = true;
      }
   }

   /**
    * Guarded by a static variable of this class
    */
   public void setupTestStringerStatic() {
      //Family is Monoid-Tight
      //static check only once.. We don't need to load 
      //we want a specific font, otherwise the tests won't work
      CoreDrawCtx cdc = plugUI.getCDC();

      if (!cdc.hasFeatureSupport(ITechHostFeatureDraw.SUP_ID_06_CUSTOM_FONTS)) {
         throw new RuntimeException();
      }

      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechHostFeatureDraw.SUP_ID_06_CUSTOM_FONTS);

      fontCustomizer.loadFont("/fonts/Monoid-Regular.ttf");
      fontCustomizer.loadFont("/fonts/Monoid-Bold.ttf");
      fontCustomizer.loadFont("/fonts/Monoid-Italic.ttf");
      fontCustomizer.loadFont("/fonts/Aleo-Regular.otf");
      fontCustomizer.loadFont("/fonts/Aleo-Bold.otf");
      fontCustomizer.loadFont("/fonts/Aleo-Italic.otf");

   }

   public void testArea() {
      Stringer stringer = new Stringer(dc);

      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      assertEquals(0, stringer.getBreakH()); //default value if not setup
      assertEquals(0, stringer.getBreakW()); //default value if not setup

      stringer.setBreakWH(50, 100);
      assertEquals(50, stringer.getBreakW());
      assertEquals(100, stringer.getBreakH());

      stringer.setAreaXYWH(5, 10, 40, 80);

      assertEquals(5, stringer.getAreaX());
      assertEquals(10, stringer.getAreaY());
      assertEquals(40, stringer.getAreaW());
      assertEquals(80, stringer.getAreaH());

      assertEquals(50, stringer.getBreakW());
      assertEquals(100, stringer.getBreakH());

      stringer.setBreakOnArea();

      assertEquals(35, stringer.getBreakW());
      assertEquals(70, stringer.getBreakH());

   }

   public void testBigText() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);

      char[] data = uc.getIOU().readFileAsChars("/the_ivy_green.txt", "UTF-8");

      assertNotNull(data);

      Stringer stringer = new Stringer(dc);
      int margin = 5;
      int areaW = 310;
      int areaH = 410;
      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(500); //does nothing in word wrap none
      stringer.ToStringSetDebugArea(true);
      stringer.setTextFigure(textFigure);
      stringer.setString(data, 0, data.length);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();
      assertEquals("Oh, a dainty plant is the Ivy green,", sm.getLineString(0));
      assertEquals("That creepeth o'er ruins old!", sm.getLineString(1));
      assertEquals("Of right choice food are his meals, I ween,", sm.getLineString(2));
      assertEquals("In his cell so lone and cold.", sm.getLineString(3));

      assertEquals(34, stringer.getNumOfLines());

      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_4_WORK_SHOW);
      stringer.buildFxAndMeter();

   }

   public void testBreakHeight1() {
      //setFontsToMonoidAleo();

      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);
      Stringer st = new Stringer(dc);

      st.setBreakWidth(200);
      st.setBreakHeight(40); //key call to enforce fit height

      st.setTextFigure(strFig);
      st.setString(this.data);
      st.setTrimArtifacts(true);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setFormatLineWrap(LINEWRAP_1_ANYWHERE);
      st.setSpaceTrimManager(SPACETRIM_1_NORMAL);

      //no max lines.. its the height of the area that defines the number of lines
      st.setBreakMaxLines(0); //explicitely set it to infinite.

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();

      assertEquals(2, st.getNumOfLines());

      assertEquals(10, sm.getLine(0).getWidthMono());
      assertEquals(10, sm.getLine(1).getWidthMono());

      assertEquals(200, st.getBreakW());
      assertEquals(40, st.getBreakH());

      assertEquals("Hello I'm Joe! I wou", sm.getLineString(0));
      assertEquals("ld like to eat som..", sm.getLineString(1));

      assertEquals(20, sm.getLine(0).getNumCharVisible());
      assertEquals(20, sm.getLine(1).getNumCharVisible());

      assertEquals(true, st.hasFlagState(ITechStringer.STATE_04_TRIMMED));

   }

   public void testBreakHeight2() {
      setFontsToMonoidAleo();

      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);
      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 200;
      int areaH = 40;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWidth(areaW - 5);
      st.setBreakHeight(areaH - 5); //key call to enforce fit height
      st.ToStringSetDebugArea(true);
      st.setTextFigure(strFig);
      st.setString(this.data);
      st.setTrimArtifacts(true);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setFormatLineWrap(LINEWRAP_1_ANYWHERE);
      st.setSpaceTrimManager(SPACETRIM_1_NORMAL);

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();
      assertEquals("Hello I'm Joe! I would", sm.getLineString(0));
      assertEquals("like to eat some me..", sm.getLineString(1));

      assertEquals(2, st.getNumOfLines());

      assertEquals(true, st.hasFlagState(ITechStringer.STATE_04_TRIMMED));

   }

   public void testCharPositions() {

      Stringer stringer = getTestSetup1();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(0, sm.getCharX(0));
      assertEquals(10, sm.getCharX(1));
      assertEquals(20, sm.getCharX(2));

      assertEquals(0, sm.getCharY(0));
      assertEquals(0, sm.getCharY(1));
      assertEquals(19, sm.getCharY(26));

   }

   public void testEmptyFictiveLine() {
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

      assertEquals(0, stringer.getLen());
   }

   public void testEmptyString() {
      //we have a empty line at a minimum

      ByteObject textFigure = facStrAux.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.buildForDisplayWith(textFigure, "");

      StringMetrics sm = stringer.getMetrics();

      //we have a empty line at a minimum
      assertEquals(1, sm.getNumOfLines());
      assertEquals(21, sm.getLineHeight(0));
      assertEquals(21, sm.getLineHeight());
      assertEquals(0, sm.getLineWidth(0));

      //#debug
      toDLog().pTest("Default Font", dc.getFontFactory().getDefaultFont(), TestStringMetrics.class, "testEmpty", LVL_05_FINE, false);

      assertEquals(0, sm.getPrefWidth());
      assertEquals(21, sm.getPrefHeight()); //size of the font
      assertEquals(21, dc.getFontFactory().getDefaultFont().getHeight());

      assertEquals(21, sm.getPrefCharHeight());
      assertEquals(0, sm.getPrefCharWidth());

      assertEquals(0, sm.getCharWidthEtalon());

      //line index out of bounds
      try {
         assertEquals(21, sm.getLineHeight(2));
         assertTrue(false);
      } catch (ArrayIndexOutOfBoundsException e) {
         assertTrue(true);
      }

      char[] lineBreakChars = sm.getLineBreakChars();
      assertEquals(' ', lineBreakChars[0]);
      assertEquals('?', lineBreakChars[1]);

      assertEquals(" ?;,.!:-=()[]", new String(lineBreakChars));
   }

   public void testExtraSpaceManagement() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      //TODO delete fronting space in lines ?
      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(160);
      char[] chars = "###  Words often goes   along well with a sentence . It   is such  a nice   evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();
      assertEquals(4, sm.getNumOfLines());

      assertEquals("  Words often goes   al", sm.getLineString(0)); //prefix space is gone
      assertEquals("ong well with a sentenc", sm.getLineString(1));
      assertEquals("e . It   is such  a nice   e", sm.getLineString(2));
      assertEquals("vening today.", sm.getLineString(3));

      //space trim only works with nice words
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      assertEquals(4, sm.getNumOfLines());
      assertEquals("  Words often goes  ", sm.getLineString(0)); //prefix space is gone
      assertEquals("along well with a", sm.getLineString(1));
      //does not remove double spaces inside text
      assertEquals("sentence . It   is such  a", sm.getLineString(2));
      assertEquals("nice   evening today.", sm.getLineString(3));

   }

   public void testJustify2Words() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);
      StringMetrics sm = stringer.getMetrics();

      char[] chars = "###Life and Death.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);

      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life and Death.", sm.getLineString(0));
      assertEquals(240, sm.getLineWidth(0));

      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);

      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life and Death.", sm.getLineString(0));
      assertEquals(106, sm.getLineWidth(0));
   }

   public void testJustify2WordsOne2Lines() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);
      StringMetrics sm = stringer.getMetrics();

      char[] chars = "###Life and Death.\nTrue and False##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);

      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life and Death.", sm.getLineString(0));
      assertEquals("True and False", sm.getLineString(1));
      assertEquals(106, sm.getLineWidth(0));
      assertEquals(104, sm.getLineWidth(1));

      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);

      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life and Death.", sm.getLineString(0));
      assertEquals("True and False", sm.getLineString(1));
      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));

      //NOTE: a forced new line removes leading and trailing spaces ?
      chars = "###Life and Death. \n True and False ##".toCharArray();
      len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);

      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life and Death. ", sm.getLineString(0));
      assertEquals(" True and False ", sm.getLineString(1));
      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));

   }

   public void testJustifySimple_WrapAny() {

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

      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("Life is a long snake. It takes foreve", sm.getLineString(0));
      assertEquals("r to reach the tail. And once you ge", sm.getLineString(1));
      assertEquals("t to it. What?", sm.getLineString(2));

      assertEquals(7, sm.getLine(0).getNumOfSpaces());
      assertEquals(8, sm.getLine(1).getNumOfSpaces());
      assertEquals(3, sm.getLine(2).getNumOfSpaces());

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));

   }

   public void testJustifySimple_WrapNiceWord() {

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

      assertEquals(6, sm.getLine(0).getNumOfSpaces());
      assertEquals(6, sm.getLine(1).getNumOfSpaces());
      assertEquals(4, sm.getLine(2).getNumOfSpaces());

      //max width or break width
      //justified lines have exactly the same width
      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));

      assertEquals(9, sm.getCharWidth(0));
      assertEquals(4, sm.getCharWidth(1));
      assertEquals(4, sm.getCharWidth(2));
      assertEquals(9, sm.getCharWidth(3));

      assertEquals(' ', stringer.getCharVisibleAtRelative(4));
      assertEquals(' ', stringer.getCharVisibleAtRelative(7));
      assertEquals(' ', stringer.getCharVisibleAtRelative(9));
      assertEquals(' ', stringer.getCharVisibleAtRelative(14));
      assertEquals(13, sm.getCharWidth(4));
      assertEquals(12, sm.getCharWidth(7));
      assertEquals(12, sm.getCharWidth(9));
      assertEquals(12, sm.getCharWidth(14));

   }

   public void testLineHeights() {

      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setTextFigure(textFigure);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setString("word\nsentence\nnice");

      stringer.buildFxAndMeter();

      assertEquals(false, stringer.hasFlagState(STATE_02_CHAR_WIDTHS));

      StringMetrics sm = stringer.getMetrics();
      assertEquals(3, sm.getNumOfLines());
      assertEquals(21, sm.getLineHeight(0));
      assertEquals(21, sm.getLineHeight(1));
      assertEquals(21, sm.getLineHeight(2));

      assertEquals(34, sm.getLineWidth(0));
      assertEquals(63, sm.getLineWidth(1));
      assertEquals(29, sm.getLineWidth(2));

      assertEquals(0, sm.getLineX(0));
      assertEquals(0, sm.getLineX(1));
      assertEquals(0, sm.getLineX(2));

      assertEquals(0, sm.getLineY(0));
      assertEquals(21, sm.getLineY(1));
      assertEquals(42, sm.getLineY(2));

   }

   public void testNumOfSpaces() {

      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(60);

      char[] chars = "###5      333    44  ##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("5     ", sm.getLineString(0));
      assertEquals(" 333  ", sm.getLineString(1));
      assertEquals("  44  ", sm.getLineString(2));

      assertEquals(5, sm.getLine(0).getNumOfSpaces());
      assertEquals(3, sm.getLine(1).getNumOfSpaces());
      assertEquals(4, sm.getLine(2).getNumOfSpaces());
   }

   public void testSingleLine1OverlayStyle() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is great!##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is great!", sm.getLineString(0));
      assertEquals(140, sm.getLineWidth(0));

      ByteObject fxOverlay = facStringFx.getFxFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_CYAN);
      int layerID = 0;
      stringer.createLayer("overlay", layerID);
      stringer.addInterval(2, 7, layerID, fxOverlay);
      stringer.buildAgain();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is great!", sm.getLineString(0));
      assertEquals(112, sm.getLineWidth(0));

   }

   public void testSingleLine2OverlayStyles() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is great!##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is great!", sm.getLineString(0));
      assertEquals(140, sm.getLineWidth(0));

      int layerID = 1;
      stringer.createLayer("overlay", layerID);

      ByteObject fxOverlay = facStringFx.getFxFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_CYAN);
      stringer.addInterval(2, 7, layerID, fxOverlay);

      ByteObject fxOverlay2 = facStringFx.getFxFont(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_5_HUGE, FULLY_OPAQUE_RED);
      stringer.addInterval(4, 7, layerID, fxOverlay2);

      stringer.buildAgain();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is great!", sm.getLineString(0));
      assertEquals(121, sm.getLineWidth(0));

   }

   public void testSingleLineEndingWithNewLine() {

      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim();
      ByteObject strAuxNewLine = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE, strAuxFormat, strAuxNewLine);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is a long\n##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life is a long", sm.getLineString(0));
      assertEquals("", sm.getLineString(1));

      stringer.setString("Life is a long\n");
      stringer.buildForDisplayWith(textFigure);

      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life is a long", sm.getLineString(0));
      assertEquals("", sm.getLineString(1));

   }

   public void testSingleSmallWord() {
      int maxLines = 2;

      ByteObject textFigure = facStrAux.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_01_CHAR_EFFECTS));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_02_CHAR_WIDTHS));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_03_CHECK_CLIP));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_04_TRIMMED));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_05_STR_WIDTH));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_06_CHAR_POSITIONS));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_07_BROKEN));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_08_ACTIVE_STYLE));
      assertEquals(false, stringer.hasFlagState(ITechStringer.STATE_11_DIFFERENT_FONTS));

      stringer.buildForDisplayWith(textFigure, "word");

      StringMetrics sm = stringer.getMetrics();

      //#debug
      toDLog().pTest("", stringer, TestStringMetrics.class, "testStringWord", LVL_05_FINE, false);

      assertEquals(1, sm.getNumOfLines());

      assertEquals(false, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));

      assertEquals(34, sm.getPrefWidth());
      assertEquals(0, sm.getPrefCharWidth());

      assertEquals(21, sm.getPrefHeight()); //size of the font
      assertEquals(21, sm.getPrefCharHeight());

      assertEquals(11, sm.getCharWidthEtalon());

      assertEquals(21, sm.getLineHeight(0));
      assertEquals(34, sm.getLineWidth(0));
      assertEquals(0, sm.getLineX(0));
      assertEquals(0, sm.getLineY(0));

   }

   public void testStringWordMono() {
      //does not work empty
      Stringer stringer = new Stringer(dc);
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure, "word");
      StringMetrics sm = stringer.getMetrics();
      assertEquals(1, sm.getNumOfLines());
      assertEquals(true, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));

      assertEquals(10, sm.getCharWidth(0));
      assertEquals(10, sm.getCharWidth(1));
      assertEquals(10, sm.getCharWidth(2));
      assertEquals(10, sm.getCharWidth(3));

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure, "word");
      sm = stringer.getMetrics();
      assertEquals(1, sm.getNumOfLines());
      assertEquals(true, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));

      assertEquals(5, sm.getCharWidth(0));
      assertEquals(5, sm.getCharWidth(1));
      assertEquals(5, sm.getCharWidth(2));
      assertEquals(5, sm.getCharWidth(3));
   }

   public void testStringWordProportional() {
      //does not work empty
      Stringer stringer = new Stringer(dc);
      ByteObject textFigure = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure, "word");
      StringMetrics sm = stringer.getMetrics();
      assertEquals(1, sm.getNumOfLines());
      assertEquals(false, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));

      assertEquals(12, sm.getCharWidth(0));
      assertEquals(9, sm.getCharWidth(1));
      assertEquals(6, sm.getCharWidth(2));
      assertEquals(9, sm.getCharWidth(3));
   }

   public void testTableSymbolsTrim() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setTextFigure(textFigure);
      stringer.setBreakWH(65, 40);
      stringer.setString("Table of Symbols");
      stringer.setBreakMaxLines(1);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setTrimArtifacts(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      //we do we see the .. when wordwrap is none ? TODO
      assertEquals("Table of Symbo..", sm.getLineString(0));
      assertEquals(16, sm.getLine(0).getNumCharVisible());
      assertEquals(160, sm.getPrefWidth());
      
      stringer.setTextFigure(textFigure);
      stringer.setBreakWH(60, 40);
      stringer.setString("Table of Symbols");
      stringer.setBreakMaxLines(1);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setTrimArtifacts(true);

      stringer.buildFxAndMeter();

      assertEquals("Tabl..", sm.getLineString(0));
      assertEquals(60, sm.getPrefWidth());
      
      
   }

   public void testTrim3Lines() {
      setFontsToMonoidAleo();

      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      //#debug
      toDLog().pTest("strFig", strFig, TestStringMetrics.class, "testTrim3Lines", LVL_05_FINE, false);

      Stringer st = new Stringer(dc);
      st.setBreakWidth(120);
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.setTextFigure(strFig);
      st.setTrimArtifacts(true);
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setBreakMaxLines(3);
      assertEquals(true, st.isTrimArtifacts());

      st.buildFxAndMeter();

      assertEquals(67, data.length());

      StringMetrics sm = st.getMetrics();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 3);

      assertEquals("Hello I'm Joe!", sm.getLineString(0));
      assertEquals("I would like to", sm.getLineString(1));
      assertEquals("eat some me..", sm.getLineString(2));

      assertEquals(112, sm.getLineWidth(0));
      assertEquals(120, sm.getLineWidth(1));
      assertEquals(104, sm.getLineWidth(2));

   }

   public void testTrim1LineNotHappening() {
      setFontsToMonoidAleo();

      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.setBreakWidth(320);
      st.ToStringSetDebugArea(true);
      st.setString("This line should not be trimmed");
      st.setTextFigure(strFig);
      st.setTrimArtifacts(true);
      st.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      st.setBreakMaxLines(1);
      assertEquals(true, st.isTrimArtifacts());

      st.buildFxAndMeter();

      StringMetrics sm = st.getMetrics();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 1);

      assertEquals("This line should not be trimmed", sm.getLineString(0));

   }
   
   public void testWidthBreakNewLines() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###word often goes along well \n with a sentence\nsuch a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);

      stringer.setBreakWidth(140);
      stringer.setTextFigure(textFigure);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(10, sm.getLine(0).getWidthMono());
      assertEquals(14, sm.getLine(0).getNumCharVisible());
      assertEquals(140, sm.getLine(0).getPixelsW());

      assertEquals("word often goe", sm.getLineString(0));
      assertEquals("s along well ", sm.getLineString(1));
      assertEquals(" with a senten", sm.getLineString(2));
      assertEquals("ce", sm.getLineString(3));
      assertEquals("such a nice ev", sm.getLineString(4));
      assertEquals("ening today.", sm.getLineString(5));
      assertEquals(6, sm.getNumOfLines());

      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.buildFxAndMeter();

      sm = stringer.getMetrics();

      assertEquals("word often", sm.getLineString(0));
      assertEquals("goes along", sm.getLineString(1));
      assertEquals("well ", sm.getLineString(2));
      assertEquals(" with a", sm.getLineString(3));
      assertEquals("sentence", sm.getLineString(4));
      assertEquals("such a nice", sm.getLineString(5));
      assertEquals("evening today.", sm.getLineString(6));

      assertEquals(7, sm.getNumOfLines());

      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.buildFxAndMeter();

   }

   public void testWidthBreakNiceWords() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      //assertEquals(4, sm.getNumOfLines());

      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      assertEquals(24, sm.getLine(0).getLengthInStringer());
      assertEquals(22, sm.getLine(1).getLengthInStringer());
      assertEquals(22, sm.getLine(2).getLengthInStringer());
      assertEquals(16, sm.getLine(3).getLengthInStringer());

      assertEquals(10, sm.getLine(0).getWidthMono());
      assertEquals(10, sm.getLine(1).getWidthMono());
      assertEquals(10, sm.getLine(2).getWidthMono());
      assertEquals(10, sm.getLine(3).getWidthMono());

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(220, sm.getLineWidth(1));
      assertEquals(220, sm.getLineWidth(2));
      assertEquals(160, sm.getLineWidth(3));

      String str = stringer.getStringCopyVisible(0, 2, 1, 5);
      assertEquals("fe is a long snake. It takes", str);

      str = stringer.getStringCopyVisible(0, 2, 2, 9);
      assertEquals("fe is a long snake. It takes forever to reach the tail.", str);

      str = stringer.getStringCopyVisible(0, 2, 0, 8);
      assertEquals("fe is ", str);

      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_2_JUSTIFIED);
      stringer.buildFxAndMeter();

      StringFx charFx = stringer.getCharFx(0);
      assertEquals(FACE_MONOSPACE, charFx.getFont().getFace());
      assertEquals(STYLE_PLAIN, charFx.getFont().getStyle());
      assertEquals(SIZE_4_LARGE, charFx.getFont().getSize());

      //#debug
      toDLog().pTest("Font CharFx", charFx.getFont(), TestStringer.class, "testJustifySimple", LVL_05_FINE, true);

      assertEquals(4, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      assertEquals(5, sm.getLine(0).getNumOfSpaces());
      assertEquals(3, sm.getLine(1).getNumOfSpaces());
      assertEquals(4, sm.getLine(2).getNumOfSpaces());
      assertEquals(3, sm.getLine(3).getNumOfSpaces());

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));
      assertEquals(240, sm.getLineWidth(3)); //justified

      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.setBreakMaxLines(2);
      stringer.setTrimArtifacts(true);
      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to rea..", sm.getLineString(1));

      stringer.setBreakMaxLines(1);
      stringer.setTrimArtifacts(true);

      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. ..", sm.getLineString(0));

   }

   public void testWidthBreakPropVsMono() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(160);

      char[] chars = "###Words often goes along well with a sentence. It is such a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals(false, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));
      assertEquals("Words often goes alon", sm.getLineString(0));
      assertEquals("g well with a sentence. ", sm.getLineString(1));
      assertEquals("It is such a nice evenin", sm.getLineString(2));
      assertEquals("g today.", sm.getLineString(3));

      //shows the different between prop and mono
      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      stringer.setTextFigure(textFigure);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setTrimArtifacts(true);

      stringer.buildFxAndMeter();

      assertEquals(5, sm.getNumOfLines());

      assertEquals(true, stringer.hasFlagState(STATE_18_FULL_MONOSPACE));
      assertEquals("Words often goes", sm.getLineString(0));
      assertEquals(" along well with", sm.getLineString(1));
      assertEquals(" a sentence. It ", sm.getLineString(2));
      assertEquals("is such a nice e", sm.getLineString(3));
      assertEquals("vening today.", sm.getLineString(4));

   }

   public void testWidthTrim() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setBreakMaxLines(1);
      stringer.setTrimArtifacts(false);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is a long snake. It", sm.getLineString(0));

      stringer.setBreakMaxLines(1);
      stringer.setTrimArtifacts(true);
      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is a long snake. ..", sm.getLineString(0));

      stringer.setBreakMaxLines(2);
      stringer.setTrimArtifacts(false);
      stringer.buildFxAndMeter();

      //word wrap is none.. so we only have one line
      assertEquals(2, sm.getNumOfLines());
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));

      stringer.setBreakMaxLines(2);
      stringer.setTrimArtifacts(true);
      stringer.buildFxAndMeter();

      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to rea..", sm.getLineString(1));

   }

}
