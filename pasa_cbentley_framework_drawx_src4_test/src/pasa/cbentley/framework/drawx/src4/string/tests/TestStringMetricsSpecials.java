package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;
import pasa.cbentley.testing.engine.ConfigUTest;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetricsSpecials extends TestCaseFrameworkUiPluggedDrawX implements ITechFont, ITechStringer {

   private static boolean isStaticSetupDone;

   String                 data = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   public TestStringMetricsSpecials() {
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
      assertEquals(230, sm.getLineWidth(1));
      assertEquals(230, sm.getLineWidth(2));
      assertEquals(170, sm.getLineWidth(3));
      return stringer;
   }

   private void setFontsToMonoidAleo() {
      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechFeaturesDraw.SUP_ID_06_CUSTOM_FONTS);
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

      if (!cdc.hasFeatureSupport(ITechFeaturesDraw.SUP_ID_06_CUSTOM_FONTS)) {
         throw new RuntimeException();
      }

      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechFeaturesDraw.SUP_ID_06_CUSTOM_FONTS);

      fontCustomizer.loadFont("/fonts/Monoid-Regular.ttf");
      fontCustomizer.loadFont("/fonts/Monoid-Bold.ttf");
      fontCustomizer.loadFont("/fonts/Monoid-Italic.ttf");
      fontCustomizer.loadFont("/fonts/Aleo-Regular.otf");
      fontCustomizer.loadFont("/fonts/Aleo-Bold.otf");
      fontCustomizer.loadFont("/fonts/Aleo-Italic.otf");

   }

   public void test_FormFeed_0_Ignored() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Words often goes. \f It is such a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);
      stringer.setNumLinesPerPage(4);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.setShowHiddenChars(true);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Words·often·goes.··It·is·such·a·nice·evening·today.", sm.getLineString(0)); //prefix space is gone

   }

   public void test_FormFeed_1_Space() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###eat\fmanger\fесть##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eat manger есть", sm.getLineString(0));

      stringer.setShowHiddenChars(true);
      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eat♀manger♀есть", sm.getLineString(0));
   }

   /**
    * 
    */
   public void test_FormFeed_3_Work() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Words often goes. \f It is such a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_3_NEW_PAGE);
      stringer.setNumLinesPerPage(4);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_1_NORMAL);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(5, sm.getNumOfLines());

      assertEquals("Words often goes. ", sm.getLineString(0)); //prefix space is gone
      assertEquals("", sm.getLineString(1));
      assertEquals("", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));
      assertEquals(" It is such a nice evening today.", sm.getLineString(4));

      assertEquals(131, sm.getLineWidth(0));
      assertEquals(0, sm.getLineWidth(1));
      assertEquals(0, sm.getLineWidth(2));
      assertEquals(0, sm.getLineWidth(3));
      assertEquals(213, sm.getLineWidth(4));

   }

   public void test_Mix_Specials_0_Ignored() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_2_SHOW_HIDDEN_CHARS, true);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is\t a\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(1);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life·is·a·long·real?", sm.getLineString(0));

   }

   public void test_Newline_0_Ignored() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_2_SHOW_HIDDEN_CHARS, true);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(1);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life·is·real?", sm.getLineString(0));

      assertEquals(14, sm.getLine(0).getLengthInStringer());
      assertEquals(13, sm.getLine(0).getNumCharVisible());

      assertEquals(' ', stringer.getCharSourceAtRelative(5));
      //not the same because \n was deleted
      assertEquals('i', stringer.getCharVisibleAtRelative(5));

   }

   public void test_Newline_0_Ignored2() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_2_SHOW_HIDDEN_CHARS, true);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is\n real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(1);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life·is·real?", sm.getLineString(0));

      assertEquals(15, sm.getLine(0).getLengthInStringer());
      assertEquals(13, sm.getLine(0).getNumCharVisible());

      assertEquals(' ', stringer.getCharSourceAtRelative(5));
      //not the same because \n was deleted
      assertEquals('i', stringer.getCharVisibleAtRelative(5));

   }

   public void test_Newline_1_Space() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);
      stringer.setBreakMaxLines(1);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life↵·is·real?", sm.getLineString(0));

      stringer.setShowHiddenChars(false);
      stringer.buildFxAndMeter();

      assertEquals("Life  is real?", sm.getLineString(0));

   }
   
   

   public void test_Newline_2_Java() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_2_JAVA_ESCAPED);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);
      stringer.setBreakMaxLines(1);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life\\n·is·real?", sm.getLineString(0));
      assertEquals(14, sm.getLine(0).getLengthInStringer());
      assertEquals(15, sm.getLine(0).getNumCharVisible());

   }

   public void test_Newline_3_Work() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(2, sm.getNumOfLines());

      assertEquals("Life↵", sm.getLineString(0));
      assertEquals("·is·real?", sm.getLineString(1));

      assertEquals(' ', stringer.getCharSourceAtRelative(5));
      assertEquals('·', stringer.getCharVisibleAtRelative(5));
   }

   public void test_Tab_0_Ignored() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###eat\tmanger\tесть##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eatmangerесть", sm.getLineString(0));

   }

   public void test_Tab_1_Space() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###eat\tmanger\tесть##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eat manger есть", sm.getLineString(0));

      stringer.setShowHiddenChars(true);
      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eat→manger→есть", sm.getLineString(0));
   }

   public void test_Tab_4_Eclipse() {

      //go mono
      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);

      char[] chars = "###One\tFour\tThree\n1\t4\t3\n11\t\tFive\n##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_4_ECLIPSE);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals("One Four    Three", sm.getLineString(0));
      assertEquals("1   4   3", sm.getLineString(1));
      assertEquals("11      Five", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));

      //max width or break width
      //justified lines have exactly the same width

      stringer.setShowHiddenChars(true);
      stringer.buildFxAndMeter();

      assertEquals("One→Four→→→→Three↵", sm.getLineString(0));
      assertEquals("1→→→4→→→3↵", sm.getLineString(1));
      assertEquals("11→→→→→→Five↵", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));

   }

   public void test_Tab_4_Eclipse_Simple() {

      //go mono
      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);

      char[] chars = "###One\t##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_4_ECLIPSE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, stringer.getDirectiveTabAux());
      assertEquals("One→", sm.getLineString(0));
      assertEquals(0, sm.getLine(0).getNumOfSpaces());
      assertEquals(4, sm.getLine(0).getNumCharVisible());
      assertEquals(40, sm.getLine(0).getPixelsW());
   }

   public void test_Tab_4_Eclipse_Simple2() {

      //go mono
      ByteObject strFig = facFigure.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);

      char[] chars = "###One\t:\t##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setBreakWidth(240);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_4_ECLIPSE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, stringer.getDirectiveTabAux());
      assertEquals("One→:→→→", sm.getLineString(0));
      assertEquals(0, sm.getLine(0).getNumOfSpaces());
      assertEquals(8, sm.getLine(0).getNumCharVisible());
      assertEquals(80, sm.getLine(0).getPixelsW());
   }

   public void test_Tab_5_Columns2() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      char[] chars = "###One\tTwo\tThree\t|\n11\t\t\t|##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_5_COLUMN);
      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());

      assertEquals("One Two Three |", sm.getLineString(0));
      assertEquals("11            |", sm.getLineString(1));

   }

   public void test_Tab_5_Columns3() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      char[] chars = "###One\tTwo\tThree\t|\n1\t2\t33\t|##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_5_COLUMN);
      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());

      assertEquals("One Two Three |", sm.getLineString(0));
      assertEquals("1   2   33    |", sm.getLineString(1));
   }

   public void test_Tab_5_Columns4() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      Stringer stringer = new Stringer(dc);
      StringMetrics sm = stringer.getMetrics();

      char[] chars = "###One\tTwo\tThree\t|\n1\t2\t33\t|\n1\t2\t33\t|##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_5_COLUMN);
      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("One Two Three |", sm.getLineString(0));
      assertEquals("1   2   33    |", sm.getLineString(1));
      assertEquals("1   2   33    |", sm.getLineString(2));
   }

   
   public void test_Tab_5_Columns() {

      ByteObject strFig = facFigure.getFigStringSystemPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.setBreakWidth(240);

      char[] chars = "###One\tTwo\tThree\t|\n1\t2\t3\t|\n11\t\t\t|\n##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(strFig);
      stringer.setString(chars, offset, len);
      stringer.setFormatWordwrap(WORDWRAP_0_NONE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.setDirectiveTab(SPECIALS_TAB_5_COLUMN);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals("One Two Three |", sm.getLineString(0));
      assertEquals("1   2   3     |", sm.getLineString(1));
      assertEquals("11            |", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));

   }

   public void testHiddenCharsSingleLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400); //make sure there is one line to avoid code of wordwrap

      char[] chars = "###Life\n is\t a\r\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(1);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life↵·is→·a⏎↵·long♀·real?", sm.getLineString(0));

      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);

      stringer.buildFxAndMeter();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life·is·a·long·real?", sm.getLineString(0));
   }
   
   public void testHiddenCharsWrap() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(80); //make sure there is one line to avoid code of wordwrap

      char[] chars = "###Life\n is\t a\r\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals("Life↵·is", sm.getLineString(0));
      assertEquals("→·a⏎↵·lo", sm.getLineString(1));
      assertEquals("ng♀·real", sm.getLineString(2));
      assertEquals("?", sm.getLineString(3));

      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);

      stringer.buildFxAndMeter();

      assertEquals(3, sm.getNumOfLines());

      assertEquals("Life·is·", sm.getLineString(0));
      assertEquals("a·long·r", sm.getLineString(1));
      assertEquals("eal?", sm.getLineString(2));
      
   }
   
   public void testHiddenCharsWrap_NewLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(80); //make sure there is one line to avoid code of wordwrap

      char[] chars = "###Life \n\n\nGreat \n\n\nHappy##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();


      assertEquals("Life·↵↵↵", sm.getLineString(0));
      assertEquals("Great·↵↵", sm.getLineString(1));
      assertEquals("↵Happy", sm.getLineString(2)); //first and unique on a newline

      assertEquals(3, sm.getNumOfLines());
      
      
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);

      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());

      assertEquals("Life·Gre", sm.getLineString(0));
      assertEquals("at·Happy", sm.getLineString(1));
   }
   
   public void testHiddenCharsWrap_Tab() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(80); //make sure there is one line to avoid code of wordwrap

      char[] chars = "###Life\t\t\tGreat\t\t\tHappy##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();


      assertEquals("Life→→→G", sm.getLineString(0));
      assertEquals("reat→→→H", sm.getLineString(1));
      assertEquals("appy", sm.getLineString(2));

      assertEquals(3, sm.getNumOfLines());
      
      
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_0_IGNORED);
      stringer.setDirectiveTab(SPECIALS_TAB_0_IGNORED);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_0_IGNORED);

      stringer.buildFxAndMeter();

      assertEquals(2, sm.getNumOfLines());

      assertEquals("LifeGrea", sm.getLineString(0));
      assertEquals("tHappy", sm.getLineString(1));
   }


   public void testHiddenCharsSingleLine2() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is\t a\r\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setTextFigure(textFigure);
      stringer.setString(chars, offset, len);

      //custom hardcoded stringer parameters
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      stringer.setDirectiveTab(SPECIALS_TAB_1_SPACE_SPECIAL);
      stringer.setDirectiveFormFeed(SPECIALS_FORMFEED_1_SPACE_SPECIAL);
      stringer.setFormatWordwrap(WORDWRAP_1_ANYWHERE);
      stringer.setSpaceTrimManager(SPACETRIM_0_NONE);
      stringer.setBreakMaxLines(1);
      stringer.setShowHiddenChars(true);

      stringer.buildFxAndMeter();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life↵·is→·a⏎↵·long♀·real?", sm.getLineString(0));

   }

}
