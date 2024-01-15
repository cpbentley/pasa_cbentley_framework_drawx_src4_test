package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.string.ITechStringer;
import pasa.cbentley.framework.drawx.src4.string.StringFx;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.StringerEditor;
import pasa.cbentley.framework.drawx.src4.tech.IBOFigString;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;
import pasa.cbentley.testing.engine.ConfigUTest;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetrics extends TestCaseDrawXPlugged implements ITechFont, ITechStringer {

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
            log.setFlagTag(FLAG_25_PRINT_NULL, true);

            log.setFlagTag(FLAG_20_PRINT_INIT, false);
         }
      });

      return configTest;
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

   public void testCharWidthMono() {

      IMFont f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(7, f.charWidth('|'));
      assertEquals(7, f.charWidth(StringUtils.ENGLISH_SPACE));
      assertEquals(7, f.charWidth(StringUtils.INTER_PUNCT));
      assertEquals(7, f.charWidth(StringUtils.EM_QUAD));
      assertEquals(7, f.charWidth(StringUtils.EN_QUAD));
      assertEquals(7, f.charWidth(StringUtils.EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.EN_SPACE));
      assertEquals(7, f.charWidth(StringUtils.SIX_PER_EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.FOUR_PER_EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.THREE_PER_EM_SPACE));
   }

   public void testCharWidthProp() {

      IMFont f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(16, f.getHeight());
      assertEquals(3, f.charWidth(' '));
      assertEquals(11, f.charWidth('m'));
      assertEquals(11, f.charWidth('W'));
      assertEquals(3, f.charWidth('i'));
      assertEquals(3, f.charWidth('|'));
      assertEquals(7, f.charWidth('d'));
      assertEquals(3, f.charWidth(StringUtils.ENGLISH_SPACE));
      assertEquals(3, f.charWidth(StringUtils.INTER_PUNCT));
      assertEquals(12, f.charWidth(StringUtils.EM_QUAD));
      assertEquals(6, f.charWidth(StringUtils.EN_QUAD));
      assertEquals(12, f.charWidth(StringUtils.EM_SPACE));
      assertEquals(6, f.charWidth(StringUtils.EN_SPACE));
      assertEquals(2, f.charWidth(StringUtils.SIX_PER_EM_SPACE));
      assertEquals(3, f.charWidth(StringUtils.FOUR_PER_EM_SPACE));
      assertEquals(4, f.charWidth(StringUtils.THREE_PER_EM_SPACE));

      assertEquals(8, f.charWidth(StringUtils.BACKSPACE));
      assertEquals(8, f.charWidth('\b'));
      assertEquals(8, f.charWidth(StringUtils.FORM_FEED));
      assertEquals(8, f.charWidth(StringUtils.NULL_CHAR));
      assertEquals(8, f.charWidth(StringUtils.START_HEADING));
      assertEquals(8, f.charWidth(StringUtils.START_TEXT));
      assertEquals(8, f.charWidth(StringUtils.END_TEXT));
      assertEquals(8, f.charWidth(StringUtils.END_TRANSMISSION));

      assertEquals(0, f.charWidth(StringUtils.TAB));
      assertEquals(0, f.charWidth(StringUtils.TAB_CHAR));
      assertEquals(8, f.charWidth(StringUtils.TAB_LINE));

      //look the same but are different.. font of editor does not have glyphs for all characters
      assertEquals("→", String.valueOf(StringUtils.ARROW_RIGHT));
      assertEquals("⎵", String.valueOf(StringUtils.BOTTOM_SQUARE_BRACKET));
      assertEquals("⏎", String.valueOf(StringUtils.SYMBOL_RETURN));
      assertEquals("␠", String.valueOf(StringUtils.SYMBOL_FOR_SPACE));
      assertEquals("·", String.valueOf(StringUtils.INTER_PUNCT));
      assertEquals("", String.valueOf(StringUtils.BACKSPACE));
      assertEquals("", String.valueOf(StringUtils.FORM_FEED));
      assertEquals("", String.valueOf(StringUtils.START_HEADING));
      assertEquals(" ", String.valueOf(StringUtils.LINE_SEPARATOR));

      assertEquals(StringUtils.FORM_FEED_F, StringUtils.FORM_FEED);

      assertEquals(0, f.charWidth(StringUtils.NEW_LINE));
      assertEquals(0, f.charWidth(StringUtils.NEW_LINE_CARRIAGE_RETURN));
      assertEquals(0, f.charWidth(StringUtils.LINE_SEPARATOR));

      assertEquals(6, f.charWidth(StringUtils.FIGURE_SPACE));
      assertEquals(6, f.charWidth('0'));
      assertEquals(6, f.charWidth('9'));

      assertEquals(0, f.charWidth(StringUtils.UTF8_BOM_CHAR));

      String unicodeString = "The unicode for Omega is: \\u03A9";
      System.out.println(unicodeString);

      char quoteChar = 34; // ASCII value of "
      String quote = quoteChar + "To be or not to be, that is the question." + quoteChar;
      System.out.println(quote);
   }

   public void testEmptyString() {
      //we have a empty line at a minimum

      Stringer stringer = new Stringer(dc);
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      int maxLines = 2;
      facFigure.setFigStringP3(textFigure, NEWLINE_MANAGER_1_WORK, WORDWRAP_3_NICE_HYPHENATION, maxLines);

      stringer.buildForDisplayWith(textFigure, "");

      assertEquals(0, stringer.getBreakH()); //default value if not setup
      assertEquals(0, stringer.getBreakW()); //default value if not setup
      assertEquals(maxLines, stringer.getBreakMaxLines());
      assertEquals(NEWLINE_MANAGER_1_WORK, stringer.getNewLineManager());
      assertEquals(WORDWRAP_3_NICE_HYPHENATION, stringer.getWordwrap());

      StringMetrics sm = stringer.getMetrics();

      //we have a empty line at a minimum
      assertEquals(1, sm.getNumOfLines());
      assertEquals(21, sm.getLineHeight());
      assertEquals(21, sm.getLineHeight(0));
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
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_1_ANYWHERE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);

      //TODO delete fronting space in lines ?
      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(160);
      char[] chars = "###  Words often goes   along well with a sentence . It   is such  a nice   evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();
      assertEquals(4, sm.getNumOfLines());

      assertEquals("  Words often goes   al", sm.getLineString(0)); //prefix space is gone
      assertEquals("ong well with a sentenc", sm.getLineString(1));
      assertEquals("e . It   is such  a nice   e", sm.getLineString(2));
      assertEquals("vening today.", sm.getLineString(3));

      //space trim only works with nice words
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);
      stringer.buildForDisplayWith(textFigure);

      assertEquals(4, sm.getNumOfLines());
      assertEquals("Words often goes", sm.getLineString(0)); //prefix space is gone
      assertEquals("along well with a", sm.getLineString(1));
      //does not remove double spaces inside text
      assertEquals("sentence . It   is such", sm.getLineString(2));
      assertEquals("a nice   evening today.", sm.getLineString(3));

   }

   /**
    * 
    */
   public void testFormFeed() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);

      Stringer stringer = new Stringer(dc);
      stringer.setNumLinesPerPage(4);

      char[] chars = "###Words often goes. \f It is such a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);
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

   public void testHiddenCharsSingleLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_0_IGNORE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_1_ANYWHERE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 1);

      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_2_SHOW_HIDDEN_CHARS, true);

      Stringer stringer = new Stringer(dc);

      stringer.setBreakWidth(400);

      char[] chars = "###Life\n is\t a\r\n long\f real?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());

      assertEquals("Life↵·is→·a⏎↵·long♀·real?", sm.getLineString(0));
   }

   public void testLineHeights() {

      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);

      Stringer stringer = new Stringer(dc);
      stringer.buildForDisplayWith(textFigure, "word\nsentence\nnice");

      assertEquals(false, stringer.hasState(STATE_02_CHAR_WIDTHS));

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

   public void testSingleLine1OverlayStyle() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_0_IGNORE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is great!##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

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
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_0_IGNORE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is great!##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

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

   public void testSingleSmallWord() {

      //does not work empty
      Stringer stringer = new Stringer(dc);

      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      int maxLines = 2;
      facFigure.setFigStringP3(textFigure, NEWLINE_MANAGER_1_WORK, WORDWRAP_1_ANYWHERE, maxLines);
      assertEquals(false, stringer.hasState(ITechStringer.STATE_01_CHAR_EFFECTS));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_02_CHAR_WIDTHS));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_03_CHECK_CLIP));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_04_TRIMMED));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_05_STR_WIDTH));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_06_CHAR_POSITIONS));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_07_BROKEN));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_08_ACTIVE_STYLE));
      assertEquals(false, stringer.hasState(ITechStringer.STATE_11_DIFFERENT_FONTS));

      stringer.buildForDisplayWith(textFigure, "word");

      StringMetrics sm = stringer.getMetrics();

      //#debug
      toDLog().pTest("", stringer, TestStringMetrics.class, "testStringWord", LVL_05_FINE, false);

      assertEquals(1, sm.getNumOfLines());

      assertEquals(false, stringer.hasState(STATE_18_FULL_MONOSPACE));

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
      assertEquals(true, stringer.hasState(STATE_18_FULL_MONOSPACE));

      assertEquals(10, sm.getCharWidth(0));
      assertEquals(10, sm.getCharWidth(1));
      assertEquals(10, sm.getCharWidth(2));
      assertEquals(10, sm.getCharWidth(3));

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure, "word");
      sm = stringer.getMetrics();
      assertEquals(1, sm.getNumOfLines());
      assertEquals(true, stringer.hasState(STATE_18_FULL_MONOSPACE));

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
      assertEquals(false, stringer.hasState(STATE_18_FULL_MONOSPACE));

      assertEquals(12, sm.getCharWidth(0));
      assertEquals(9, sm.getCharWidth(1));
      assertEquals(6, sm.getCharWidth(2));
      assertEquals(9, sm.getCharWidth(3));
   }

   public void testTabAsSpace() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_10_TAB_MANAGER1, TAB_MANAGER_0_SINGLE_SPACE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###eat\tmanger\tесть##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("eat manger есть", sm.getLineString(0));

      //      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_TAB_MANAGER1, TAB_MANAGER_1_ESCAPED);
      //      stringer.buildForDisplayWith(textFigure);
      //
      //      assertEquals(1, sm.getNumOfLines());
      //      assertEquals("eat\\tmanger\\tесть", sm.getLineString(0));
      //
      //      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_TAB_MANAGER1, TAB_MANAGER_2_COLUMN);
      //      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_10_TAB_AUX1, 8);
      //      stringer.buildForDisplayWith(textFigure);
      //
      //      assertEquals(1, sm.getNumOfLines());
      //      assertEquals("eat     manger  есть", sm.getLineString(0));

   }

   public void testTrim3Lines() {
      setFontsToMonoidAleo();
      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringTrimMaxLines(strFig, 3);

      Stringer st = new Stringer(dc);
      st.setBreakWidth(120);
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.buildForDisplayWith(strFig);

      assertEquals(67, data.length());

      StringMetrics sm = st.getMetrics();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 3);

      assertEquals("Hello I'm Joe!", sm.getLineString(0));
      assertEquals("I would like", sm.getLineString(1));
      assertEquals("to eat so..", sm.getLineString(2));

      assertEquals(112, sm.getLineWidth(0));
      assertEquals(104, sm.getLineWidth(1));
      assertEquals(96, sm.getLineWidth(2));

   }

   public void testWidthBreakNewLines() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_1_ANYWHERE);

      //TODO delete fronting space in lines ?
      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(140);

      char[] chars = "###word often goes along well \r\n with a sentence\nsuch a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(7, sm.getNumOfLines());

      assertEquals("word often goe", sm.getLineString(0));
      assertEquals("s along well ", sm.getLineString(1));
      assertEquals("", sm.getLineString(2));
      assertEquals(" with a senten", sm.getLineString(3));
      assertEquals("ce", sm.getLineString(4));
      assertEquals("such a nice ev", sm.getLineString(5));
      assertEquals("ening today.", sm.getLineString(6));

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      stringer.buildForDisplayWith(textFigure);

      sm = stringer.getMetrics();

      assertEquals(9, sm.getNumOfLines());
      assertEquals("word often", sm.getLineString(0));
      assertEquals(" goes along", sm.getLineString(1));
      assertEquals(" well ", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));
      assertEquals(" with a", sm.getLineString(4));
      assertEquals(" sentence", sm.getLineString(5));
      assertEquals("such a nice", sm.getLineString(6));
      assertEquals(" evening", sm.getLineString(7));
      assertEquals(" today.", sm.getLineString(8));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);
      stringer.buildForDisplayWith(textFigure);

      sm = stringer.getMetrics();

      assertEquals(9, sm.getNumOfLines());
      assertEquals("word often", sm.getLineString(0));
      assertEquals("goes along", sm.getLineString(1));
      assertEquals(" well ", sm.getLineString(2));
      assertEquals("", sm.getLineString(3));
      assertEquals("with a", sm.getLineString(4));
      assertEquals(" sentence", sm.getLineString(5));
      assertEquals("such a nice", sm.getLineString(6));
      assertEquals("evening", sm.getLineString(7));
      assertEquals("today.", sm.getLineString(8));
   }

   public void testWidthBreakNiceWords() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

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

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_2_JUSTIFIED);
      stringer.buildForDisplayWith(textFigure);

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

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));
      assertEquals(160, sm.getLineWidth(3));

      StringFx firstCharFx = stringer.getCharFx(sm.getLine(3).getOffset());
      int lenLastLine = firstCharFx.getFont().stringWidth("get to it. What?");
      assertEquals(lenLastLine, sm.getLineWidth(3));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 2);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, true);

      stringer.buildForDisplayWith(textFigure);

      assertEquals(2, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to rea..", sm.getLineString(1));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 1);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, true);

      stringer.buildForDisplayWith(textFigure);

      assertEquals(1, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. ..", sm.getLineString(0));
   }

   public void testWidthBreakPropVsMono() {
      ByteObject textFigure = facFigure.getFigString(FACE_SYSTEM, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_1_ANYWHERE);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(160);

      char[] chars = "###Words often goes along well with a sentence. It is such a nice evening today.##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(4, sm.getNumOfLines());

      assertEquals(false, stringer.hasState(STATE_18_FULL_MONOSPACE));
      assertEquals("Words often goes alon", sm.getLineString(0));
      assertEquals("g well with a sentence. ", sm.getLineString(1));
      assertEquals("It is such a nice evenin", sm.getLineString(2));
      assertEquals("g today.", sm.getLineString(3));

      //shows the different between prop and mono
      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_1_ANYWHERE);

      stringer.buildForDisplayWith(textFigure);

      assertEquals(5, sm.getNumOfLines());

      assertEquals(true, stringer.hasState(STATE_18_FULL_MONOSPACE));
      assertEquals("Words often goes", sm.getLineString(0));
      assertEquals(" along well with", sm.getLineString(1));
      assertEquals(" a sentence. It ", sm.getLineString(2));
      assertEquals("is such a nice e", sm.getLineString(3));
      assertEquals("vening today.", sm.getLineString(4));

   }

   private Stringer getTestSetup1() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

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

   public void testCharPositions() {

      Stringer stringer = getTestSetup1();

      StringMetrics sm = stringer.getMetrics();

      assertEquals(false, stringer.hasState(STATE_06_CHAR_POSITIONS));
      assertEquals(0, sm.getCharX(0));
      assertEquals(10, sm.getCharX(1));
      assertEquals(20, sm.getCharX(2));
      assertEquals(0, sm.getCharY(1));
      assertEquals(true, stringer.hasState(STATE_06_CHAR_POSITIONS));

   }

   public void testEditorAppendChar() {
      Stringer stringer = new Stringer(dc);
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      stringer.buildForDisplayWith(textFigure);
      StringerEditor editor = stringer.getEditor();
      
      
      char[] cs = "Hello".toCharArray();

      editor.addChar(cs, 0, 'H');

      assertEquals(stringer.getLen(), 1);

      assertEquals("H", stringer.getDisplayedString());

      editor.addChar(cs, 1, 'H');

      assertEquals(stringer.getLen(), 2);
      assertEquals("He", stringer.getDisplayedString());

   }

   public void testSingleLineEndingWithNewLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringBreak(textFigure);
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
   
   public void testEmptyFictiveLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringBreak(textFigure);
      Stringer stringer = new Stringer(dc);
      stringer.setTextFigure(textFigure);
      //before using. must build
      stringer.buildAgain();
      
      StringMetrics sm = stringer.getMetrics();
      
      assertEquals(1, sm.getNumOfLines());
      assertEquals("", sm.getLineString(0));
      
      assertEquals(0, stringer.getLen());
   }
   
   
   public void testEditorAppendLine() {
      
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringBreak(textFigure);
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

   public void testWidthTrim() {

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 1);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, false);

      Stringer stringer = new Stringer(dc);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is a long snake. It", sm.getLineString(0));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 1);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, true);
      stringer.buildForDisplayWith(textFigure);

      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is a long snake. ..", sm.getLineString(0));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 2);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, false);
      stringer.buildForDisplayWith(textFigure);

      //word wrap is none.. so we only have one line
      assertEquals(1, sm.getNumOfLines());
      assertEquals("Life is a long snake. It ", sm.getLineString(0));
      assertEquals("Life is a long snake. ..", sm.getLineString(1));

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_08_MAXLINES1, 2);
      textFigure.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_3_TRIM_ARTIFACT, true);
      stringer.buildForDisplayWith(textFigure);

      assertEquals("Life is a long snake. ..", sm.getLineString(0));
      assertEquals("Life is a long snake. ..", sm.getLineString(1));

   }

}
