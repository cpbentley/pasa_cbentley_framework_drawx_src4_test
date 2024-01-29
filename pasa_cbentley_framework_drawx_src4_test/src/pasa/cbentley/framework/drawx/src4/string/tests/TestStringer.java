package pasa.cbentley.framework.drawx.src4.string.tests;

import java.io.InputStream;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.drawer.DrawerString;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigString;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigure;
import pasa.cbentley.framework.drawx.src4.string.StringDraw;
import pasa.cbentley.framework.drawx.src4.string.StringFx;
import pasa.cbentley.framework.drawx.src4.string.StringFxLeaf;
import pasa.cbentley.framework.drawx.src4.string.StringStyleLayer;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.StringerEditor;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFxStr;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFxStrLine;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringDrw;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;
import pasa.cbentley.framework.drawx.src4.tech.ITechGraphicsX;
import pasa.cbentley.framework.drawx.src4.tech.ITechScaler;
import pasa.cbentley.testing.engine.ConfigUTest;

/**
 * Test the {@link DrawerString} from the base draw module.
 * <br>
 * <br>
 * 
 * @author Charles-Philip Bentley
 *
 */
public abstract class TestStringer extends TestCaseFrameworkUiPluggedDrawX implements ITechGraphicsX, ITechStringer, ITechStringDrw, IBOFxStr, IBOFxStrLine {

   private static boolean isStaticSetupDone = false;

   protected int          colorBgImage      = FULLY_OPAQUE_BLACK;

   String                 data              = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   GraphicsX              g                 = null;

   RgbImage               ri                = null;

   ByteObject             str               = null;

   public TestStringer() {

   }

   protected IConfigU createConfigU() {
      ConfigUTest configTest = new ConfigUTest();
      configTest.setLogConfigurator(new ILogConfigurator() {

         public void apply(IDLogConfig log) {

            log.setLevelGlobal(ITechLvl.LVL_03_FINEST);
            log.setFlagTag(FLAG_17_PRINT_TEST, true);
            log.setFlagTag(FLAG_08_PRINT_EXCEPTION, true);
            log.setFlagTag(FLAG_02_PRINT_NULL, true);
            log.setFlagTag(FLAG_09_PRINT_FLOW, true);

            log.setFlagTag(FLAG_20_PRINT_INIT, false);
         }
      });

      return configTest;
   }

   /**
    * Draws the whole Stringer using {@link Stringer#draw(GraphicsX)}
    * <br>
    * <br>
    * 
    * @param name
    * @param st
    * @param w
    * @param h
    */
   public void genericTestImg(String name, Stringer st, int w, int h) {

      RgbImage ri = rc.create(w, h, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      //#debug
      //toDLog().pTest("", ri, TestStringer.class, "genericTestImg", LVL_05_FINE, false);

      st.draw(g);

      doImageTest(ri, name);
   }

   public ByteObject getMaskFigure(int scope) {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fx = getTestMask(scope);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      return strFig;
   }

   public ByteObject getTestMask(int scope) {
      return getTestMask(scope, GRADIENT_TYPE_RECT_02_VERT, 50);
   }

   public ByteObject getTestMask(int scope, int gradType, int gradSec) {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, gradSec, gradType);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_ORANGE, grad);

      ByteObject filter = null;
      ByteObject mask = facMask.getMaskPreset(0, filter, figure);

      ByteObject fx = facStringFx.getFxMask(mask, scope);
      return fx;
   }

   public void setupAbstractDrawX() {

      if (!isStaticSetupDone) {
         setupTestStringerStatic();
         isStaticSetupDone = true;
      }

      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechFeaturesDraw.SUP_ID_06_CUSTOM_FONTS);
      //the family name must match exactly.. so no-
      fontCustomizer.setFontFamilyMonospace("Monoid");
      fontCustomizer.setFontFamilyProportional("Aleo");
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

   public void testBreakWidthSpacesAvoided() {

      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);

      st.setAreaXYWH(5, 5, 110, 110);
      st.setBreakWidth(100);
      st.ToStringSetDebugArea(true);

      //init stringer area and data
      st.buildForDisplayWith(str, data);

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testBreak1Width", LVL_05_FINE, false);

      genericTestImg("BreakWidthSpacesAvoided", st, 120, 140);

      assertEquals(67, st.getLen()); //there are 67..
      assertEquals(65, st.getLastNumDrawnChars()); //there are 65 drawn so indeed 2 spaces were ignored..
   }

   public void testBreakWidthRelativeChars() {

      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);

      st.setAreaXYWH(5, 5, 110, 110);
      st.setBreakWidth(100);
      st.ToStringSetDebugArea(true);

      st.setString(data.toCharArray(), 10, data.length() - 15); //-15-> 10 for the 10 first index and we drop 5 chars at the end
      //but 2 spaces are ignored so we drew 65 characters

      st.buildForDisplayWith(str);

      assertEquals(data.length(), 67);
      assertEquals(st.getLen(), 52);
      assertEquals(st.getOffsetChar(), 10);

      genericTestImg("BreakWidthRelativeChars", st, 120, 140);

   }

   /**
    * Test the difference between break characters set or any char breaks.
    * <br>
    * <br>
    * 
    */
   public void testBreakWidthBigSingleLine() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 14, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);

      int margin = 5;
      int areaW = 100;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      st.setBreakType(BREAK_1_WIDTH);
      String data = "qwertyuiopasdfghjklzxcvbnm";
      st.buildForDisplayWith(textFigure, data);

      genericTestImg("BreakWidth_BigSingleLine_Mono", st, imageW, imageH);

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_BOLD, 14, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(textFigure);

      genericTestImg("BreakWidth_BigSingleLine_Mono_Bold", st, imageW, imageH);

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_ITALIC, 14, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(textFigure);

      genericTestImg("BreakWidth_BigSingleLine_Mono_Italic", st, imageW, imageH);

      //      st = new Stringer(dc);
      //      st.setAreaXYWH(margin, margin, areaW, areaH);
      //      st.setBreakOnArea();
      //      st.ToStringSetDebugArea(true);
      //      st.setBreakType(BREAK_1_WIDTH);
      //      st.setString(data);
      ByteObject strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, 14, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      assertEquals(2, st.getNumOfLines());

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testBreakWidthBigSingleLine", LVL_05_FINE, false);

      genericTestImg("BreakWidth_BigSingleLine_Prop", st, imageW, imageH);

      strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_BOLD, 14, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      genericTestImg("BreakWidth_BigSingleLine_Prop_Bold", st, imageW, imageH);

      strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_ITALIC, 14, FULLY_OPAQUE_ORANGE);
      st.buildForDisplayWith(strProp);

      genericTestImg("BreakWidth_BigSingleLine_Prop_Italic", st, imageW, imageH);
   }

   public void testWordIntervals() {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 200, 100);
      st.setStringFig(strFig, "Several Masked Word");

      IntIntervals intervalWords = st.buildIntervalWordsAsI();

      //#debug
      toDLog().pTest("", intervalWords, TestStringer.class, "testBuildIntervals", LVL_05_FINE, false);

      assertEquals(3, intervalWords.getSize());

      IntInterval word1 = intervalWords.getIntervalIntersect(0);
      assertEquals(0, word1.getOffset());
      assertEquals(7, word1.getLen());

      assertEquals("Several", st.getIntervalString(word1));
      assertEquals("Masked", st.getIntervalString(intervalWords.getInterval(1)));
      assertEquals("Word", st.getIntervalString(intervalWords.getInterval(2)));

      assertEquals("Masked", st.getIntervalString(intervalWords.getIntervalIntersect(10)));
      assertEquals("Masked", st.getIntervalString(intervalWords.getIntervalIntersect(11)));
      assertEquals("Masked", st.getIntervalString(intervalWords.getIntervalIntersect(12)));
      assertEquals("Masked", st.getIntervalString(intervalWords.getIntervalIntersect(13)));

   }

   public void testDrawScaled() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      //set a scale object to figure
      ByteObject scaler = facScaler.getScaler(ITechScaler.SCALER_TYPE_1_FIT_BOTH, ITechScaler.SCALER_ID_1_BI_LINEAR);
      textFigure.addByteObject(scaler);

      Stringer st = new Stringer(dc);

      int margin = 5;
      int areaW = 100;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      st.setBreakType(BREAK_1_WIDTH);
      st.buildForDisplayWith(textFigure, "qw");

      genericTestImg("DrawScaled_qw", st, imageW, imageH);

   }

   /**
    * Test special capabilities of {@link Stringer#drawOffsets(GraphicsX, int, int, int, int, int, int)}
    * for scrolling purposes
    */
   public void testDrawOffsets() {
      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringBreak(strFig);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 120, 100);
      st.setBreakOnArea();
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.buildForDisplayWith(strFig);

      assertEquals(67, data.length());

      StringMetrics sm = st.getMetrics();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 5);

      assertEquals("Hello I'm Joe!", sm.getLineString(0));
      assertEquals("I would like", sm.getLineString(1));
      assertEquals("to eat some", sm.getLineString(2));
      assertEquals("meat and fish", sm.getLineString(3));
      assertEquals("and potatoes.", sm.getLineString(4));

      RgbImage ri = rc.create(200, 100, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      st.drawOffsets(g, 0, 0, 2, 5, 1, 2);

      doImageTest(ri, "DrawOffsets");
   }

   public void testTrim3Lines() {
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
      assertEquals("to eat some", sm.getLineString(2));

      assertEquals("meat and fish", sm.getLineString(3));
      assertEquals("and potatoes.", sm.getLineString(4));

      genericTestImg("Trim3Lines", st, 120, 100);

   }

   public void test4LinesAppend() {
      
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      facFigure.setFigStringBreak(textFigure);
      Stringer stringer = new Stringer(dc);
      stringer.setString("First Line");
      stringer.buildForDisplayWith(textFigure);
      StringMetrics sm = stringer.getMetrics();
      
      StringerEditor editor = stringer.getEditor();

      ByteObject fx1 = facStringFx.getFxEffectColor(FULLY_OPAQUE_BLUE);
      editor.appendLine("Second Line", fx1);
      stringer.buildAgain();

      ByteObject fx2 = facStringFx.getFxEffectColor(FULLY_OPAQUE_PURPLE);
      editor.appendLine("Third", fx2);
      stringer.buildAgain();

      ByteObject fx3 = facStringFx.getFxEffectColor(FULLY_OPAQUE_GREEN);
      editor.appendLine("4th Line", fx3);
      stringer.buildAgain();

      assertEquals(4, sm.getNumOfLines());
      
      IntIntervals intervalsOfLeaves = stringer.getIntervalsOfLeaves();
      
      assertEquals(4, intervalsOfLeaves.getSize());
      
      //#debug
      toDLog().pTest("intervalsOfLeaves", intervalsOfLeaves, TestStringer.class, "test4LinesAppend", LVL_05_FINE, false);
      
      //assertEquals(121, sm.getLine(1).getPixelsW());
      //assertEquals(22, sm.getLine(1).getPixelsH());
      
      
      genericTestImg("4Lines", stringer, 150, 170);

      
   }
   public void testEmpty() {

      Stringer stringer = new Stringer(dc);

      assertNotNull(stringer.getDraw());
      assertNotNull(stringer.getMetrics());
      assertNotNull(stringer.getStringFx());

      int len = stringer.getLen();
      assertEquals(len, 0);

      String disString = stringer.getDisplayedString();
      assertEquals("", disString);
   }

   public void testFormatWidth30() {
      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.ToStringSetDebugArea(true);

      int breakWidth = 40;
      int margin = 5;
      int areaW = 60;
      int areaH = 80;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWH(breakWidth, 0);
      st.setBreakType(BREAK_1_WIDTH);
      st.buildForDisplayWith(str, "Bonjour Joe!");

      assertEquals(true, st.hasState(ITechStringer.STATE_07_BROKEN));

      genericTestImg("FormatWidth30", st, imageW, imageH);
   }

   public void testFormatWidthBorderCases() {
      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.ToStringSetDebugArea(true);

      int breakWidth = 8;
      int margin = 5;
      int areaW = 100;
      int areaH = 200;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWH(breakWidth, 0);
      st.setBreakType(BREAK_1_WIDTH);
      st.buildForDisplayWith(str, "Bonjour");

      assertEquals(st.getLen(), st.getNumOfLines());

      assertEquals(true, st.hasState(ITechStringer.STATE_07_BROKEN));

      genericTestImg("WidthBorderCase_Bo", st, imageW, imageH);

      st.setString("\nB");
      st.buildAgain();

      genericTestImg("WidthBorderCase_nB", st, imageW, imageH);

   }

   public void testSingleLine1OverlayStyles() {
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_0_IGNORE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_0_NONE);

      Stringer stringer = new Stringer(dc);

      char[] chars = "###Life is great!##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);

      assertEquals(14, stringer.getLen());
      int layerID = 1;
      stringer.createLayer("overlay", layerID);

      ByteObject fxOverlay = facStringFx.getFxFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_GREEN);
      stringer.addInterval(2, 5, layerID, fxOverlay);

      stringer.buildForDisplayWith(textFigure);

      IntIntervals intervals = stringer.getIntervalsOfLeaves();
      assertEquals(3, intervals.getSize());
      assertEquals("[0,1]", intervals.getInterval(0).toStringOffsetsBracket());
      assertEquals("[2,6]", intervals.getInterval(1).toStringOffsetsBracket());
      assertEquals("[7,13]", intervals.getInterval(2).toStringOffsetsBracket());

      int margin = 5;
      int areaW = 300;
      int areaH = 200;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.ToStringSetDebugArea(true);

      genericTestImg("LifeisGreat2Fonts", stringer, imageW, imageH);

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

      assertEquals(14, stringer.getLen());
      int layerID = 1;
      stringer.createLayer("overlay", layerID);

      ByteObject fxOverlay = facStringFx.getFxFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_GREEN);
      stringer.addInterval(2, 5, layerID, fxOverlay);

      StringStyleLayer layer = stringer.getStyleLayer(layerID);

      assertEquals(null, layer.getStyle(0));
      assertEquals(null, layer.getStyle(1));
      assertEquals(fxOverlay, layer.getStyle(2));
      assertEquals(fxOverlay, layer.getStyle(3));
      assertEquals(fxOverlay, layer.getStyle(4));
      assertEquals(fxOverlay, layer.getStyle(5));
      assertEquals(fxOverlay, layer.getStyle(6));
      assertEquals(null, layer.getStyle(7));
      assertEquals(null, layer.getStyle(8));

      ByteObject fxOverlay2 = facStringFx.getFxFont(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_5_HUGE, FULLY_OPAQUE_RED);
      //adding on the same layer, will generate an overwrite of payload
      stringer.addInterval(4, 4, layerID, fxOverlay2);

      assertEquals(null, layer.getStyle(0));
      assertEquals(null, layer.getStyle(1));
      assertEquals(fxOverlay, layer.getStyle(2));
      assertEquals(fxOverlay, layer.getStyle(3));
      assertEquals(fxOverlay2, layer.getStyle(4));
      assertEquals(fxOverlay2, layer.getStyle(5));
      assertEquals(fxOverlay2, layer.getStyle(6));
      assertEquals(fxOverlay2, layer.getStyle(7));
      assertEquals(null, layer.getStyle(8));
      assertEquals(null, layer.getStyle(9));

      //#debug
      toDLog().pTest("msg", stringer, TestStringer.class, "testSingleLine2OverlayStyles", LVL_05_FINE, false);
      stringer.buildForDisplayWith(textFigure);

      IntIntervals intervals = stringer.getIntervalsOfLeaves();
      assertEquals(4, intervals.getSize());
      assertEquals("[0,1]", intervals.getInterval(0).toStringOffsetsBracket());
      assertEquals("[2,3]", intervals.getInterval(1).toStringOffsetsBracket());
      assertEquals("[4,7]", intervals.getInterval(2).toStringOffsetsBracket());
      assertEquals("[8,13]", intervals.getInterval(3).toStringOffsetsBracket());

      int margin = 5;
      int areaW = 300;
      int areaH = 200;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.ToStringSetDebugArea(true);

      genericTestImg("LifeisGreat3Fonts", stringer, imageW, imageH);

   }

   public void testFxSelectLayerSimple() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fxSelect = facStringFx.getFxEffect(FX_SCOPE_0_TEXT);

      fxSelect.set4(FX_OFFSET_09_COLOR4, FULLY_OPAQUE_GREEN);
      facStringFx.setFontTransparent(fxSelect);
      fxSelect.set1(FX_OFFSET_05_SCOPE_FX1, FX_FIG_SCOPE_0_TEXT);
      ByteObject figBgFx = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE);
      facStringFx.addFxFigure(fxSelect, figBgFx);

      Stringer st = new Stringer(dc);

      int margin = 5;
      int areaW = 150;
      int areaH = 40;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      String text = "##Bonjour!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      st.setString(chars, offset, len);
      st.setTextFigure(strFig);
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.ToStringSetDebugArea(true);
      //create a 
      StringStyleLayer layerSelect = st.createLayer("select");

      assertEquals(1, layerSelect.getId());
      layerSelect.addInterval(2, 3, fxSelect);

      st.buildTextEffects();

      IntIntervals intervalsOfLeaves = st.getIntervalsOfLeaves();

      assertEquals(3, intervalsOfLeaves.getSize());

      assertEquals(0, intervalsOfLeaves.getInterval(0).getOffset());
      assertEquals(2, intervalsOfLeaves.getInterval(0).getLen());

      assertEquals(2, intervalsOfLeaves.getInterval(1).getOffset());
      assertEquals(3, intervalsOfLeaves.getInterval(1).getLen());

      assertEquals(5, intervalsOfLeaves.getInterval(2).getOffset());
      assertEquals(3, intervalsOfLeaves.getInterval(2).getLen());

      StringFxLeaf bofx0 = (StringFxLeaf) intervalsOfLeaves.getInterval(0).getPayload();
      assertEquals(FULLY_OPAQUE_ORANGE, bofx0.getFx().getSrcFx().get4(IBOFxStr.FX_OFFSET_09_COLOR4));

      StringFxLeaf bofx1 = (StringFxLeaf) intervalsOfLeaves.getInterval(1).getPayload();
      assertEquals(FULLY_OPAQUE_GREEN, bofx1.getFx().getSrcFx().get4(IBOFxStr.FX_OFFSET_09_COLOR4));

      Object fx3 = st.getCharFx(3);

      assertEquals(true, fx3 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_GREEN, ((StringFx) fx3).getColor());

      Object fx0 = st.getCharFx(0);
      assertEquals(true, fx0 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_ORANGE, ((StringFx) fx0).getColor());

      assertEquals(true, fx0 != fx3);

      st.setBreakType(ITechStringDrw.BREAK_0_NONE);
      st.buildAgain();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testFxSelectLayerSimple", LVL_05_FINE, false);

      genericTestImg("SelectLayerSimple", st, imageW, imageH);

   }

   public void testFxDynamic() {

      IMFont f = cdc.getFontFactory().getDefaultFont();

      ByteObject strFig = facFigure.getFigString(f, FULLY_OPAQUE_ORANGE);

      ByteObject fxChar = facStringFx.getFxEffect(FX_SCOPE_1_CHAR);
      fxChar.setFlag(FX_OFFSET_02_FLAGX, FX_FLAGZ_2_DYNAMIC, true);

      ByteObject fig = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE);
      fxChar.addByteObject(fig);

      fxChar.setFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_2_FIGURE, true);

      facFigure.addTxtFXToStringFig(strFig, fxChar);

      Stringer st = new Stringer(dc);

      //create a 
      StringStyleLayer layerSelect = st.createLayer("select");

      assertEquals(1, layerSelect.getId());

      int margin = 5;
      int areaW = 150;
      int areaH = 40;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      String text = "##Bonjour!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      st.setString(chars, offset, len);
      st.setTextFigure(strFig);
      st.setAreaXYWH(0, 0, areaW, areaH);

      genericTestImg("FxDyn1", st, imageW, imageH);

      layerSelect.addInterval(2, 2, fxChar);

      //st.setInterval(2, 2, 1);

      genericTestImg("FxDyn22", st, imageW, imageH);

   }

   public void testSourceStringBuilder() {
      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      StringBBuilder sb = new StringBBuilder(uc, 40);
      sb.append("string and me");
      st.setSource(sb);

      int margin = 5;
      int areaW = 150;
      int areaH = 40;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      st.setTextFigure(strFig);
      st.setAreaXYWH(0, 0, areaW, areaH);

      genericTestImg("StringBBuilder", st, imageW, imageH);

   }

   public void testFxMaskedBasic() {

      RgbImage ri = rc.create(100, 200, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_2_RGB_IMAGE);

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      ByteObject fx = getTestMask(FX_SCOPE_1_CHAR);
      facFigure.addTxtFXToStringFig(strFig, fx);

      Stringer st = new Stringer(dc);
      StringMetrics sm = st.getMetrics();

      int dy = 0;
      st.setAreaXYWH(0, dy, 100, 100);
      st.setString("Masked Chars");
      st.buildForDisplayWith(strFig);
      st.draw(g);

      assertEquals(8, sm.getCharWidth(0));
      assertEquals(8, sm.getCharWidth(1));

      //remove mask and draw on another line

      int height = sm.getLineHeight();
      dy += (height + 5);
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_1_CHAR, GRADIENT_TYPE_RECT_01_HORIZ, 100);
      facFigure.addTxtFXToStringFig(strFig, fx);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_1_CHAR, GRADIENT_TYPE_RECT_00_SQUARE, 100);
      facFigure.addTxtFXToStringFig(strFig, fx);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_1_CHAR, GRADIENT_TYPE_RECT_03_TOPLEFT, 100);
      facFigure.addTxtFXToStringFig(strFig, fx);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_1_CHAR, GRADIENT_TYPE_RECT_07_L_TOP, 100);
      facFigure.addTxtFXToStringFig(strFig, fx);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      //color used by the mask is fully defined in the mask
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_SKY_GREEN);
      fx = getTestMask(FX_SCOPE_0_TEXT, GRADIENT_TYPE_RECT_01_HORIZ, 50);
      facFigure.addTxtFXToStringFig(strFig, fx);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.setStringFig(strFig, "Masked Chars");
      st.buildAgain();
      st.draw(g);

      height = sm.getLineHeight();
      dy += (height + 5);

      doImageTest(ri, "StringerMaskedChar");
   }

   public void testFxMaskLineDiag() {

      ByteObject strFig = getMaskFigure(FX_SCOPE_2_LINE);

      ByteObject fx = strFig.getSubFirst(TYPE_070_TEXT_EFFECTS);
      fx.set1(FXLINE_OFFSET_03_CHAR_Y_OFFSET1, 2);
      fx.setFlag(FXLINE_OFFSET_01_FLAG, FXLINE_FLAG_6_DEFINED_YF, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.setStringFig(strFig, "One Masked Diagonal Line");

      genericTestImg("StringerMaskLineDiag2Pixels", st, 120, 40);
   }

   public void testFxMaskLineOne() {

      ByteObject strFig = getMaskFigure(FX_SCOPE_2_LINE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.setStringFig(strFig, "One Masked Line");

      genericTestImg("StringerMaskLineOne", st, 120, 40);
   }

   public void testFxMaskScopeWord() {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 60, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_PURPLE, grad);

      ByteObject filter = null;
      ByteObject mask = facMask.getMaskPreset(0, filter, figure);

      ByteObject fx = facStringFx.getFxMask(mask, FX_SCOPE_2_WORD);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 500, 200);
      st.buildForDisplayWith(strFig, "Several Masked Word");

      genericTestImg("StringerMask_Scope_Word", st, 500, 200);
   }

   public void testFxMaskScopeLine() {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 60, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_PURPLE, grad);

      ByteObject filter = null;
      ByteObject mask = facMask.getMaskPreset(0, filter, figure);

      ByteObject fx = facStringFx.getFxMask(mask, FX_SCOPE_2_LINE);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 500, 200);
      st.setString("Several Many\nNicely Masked \n Word Chars");

      st.buildForDisplayWith(strFig);

      genericTestImg("StringerMask_Scope_Line", st, 500, 200);
   }

   public void testAddLayer() {

      Stringer st = new Stringer(dc);
      assertEquals(1, st.getStyleLayers().length);
      assertEquals(Stringer.NAME_ROOT_STYLE_LAYER, st.getStyleLayer(0).getName());

      st = new Stringer(dc);
      st.addLayer("Select");
      assertEquals(2, st.getStyleLayers().length);
      assertEquals("Select", st.getStyleLayer(1).getName());

      assertEquals(Stringer.NAME_ROOT_STYLE_LAYER, st.getStyleLayerRoot().getName());

   }

   public void testFxMaskScopeText() {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 60, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_PURPLE, grad);

      ByteObject filter = null;
      ByteObject mask = facMask.getMaskPreset(0, filter, figure);

      ByteObject fx = facStringFx.getFxMask(mask, FX_SCOPE_0_TEXT);
      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      //scope text

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 500, 200);
      st.setString("Several Many\nNicely Masked \n Word Chars");
      st.buildForDisplayWith(strFig);

      genericTestImg("StringerMask_Scope_Line", st, 500, 200);
   }

   /**
    * Tests the figure hook for scaling using {@link IDrwTypes#TYPE_055_SCALE} on the figure.
    * 
    */
   public void testFxScaleFitFirst() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      ByteObject scaler = facScaler.getScaler(SCALER_ID_0_LINEAR, SCALER_TYPE_4_FIT_FIRST, null, null);

      strFig.addSub(scaler);
      strFig.setFlag(IBOFigure.FIG__OFFSET_04_FLAGX, IBOFigure.FIG_FLAGX_5_SCALER, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 120, 40);
      st.setStringFig(strFig, "Scaled String");

      genericTestImg("FxScalerFitFirst", st, 120, 40);
   }

   public void testGetNumofLines() {

   }

   public void testSetStringFigString() {
      Stringer st = new Stringer(dc);

   }

   public void testInterval() {
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject strPlain = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important

      ri = rc.create(100, 100, colorBgImage);
      g = ri.getGraphicsX(MODE_1_IMAGE);

      st.buildForDisplayWith(strPlain, "mmm_mmm");

   }

   public void testMonospace() {
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject strPlain = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important

      ri = rc.create(100, 100, colorBgImage);
      g = ri.getGraphicsX(MODE_1_IMAGE);

      st.buildForDisplayWith(strPlain, "mmm_mmm");
      int pw_mmm = st.getMetrics().getPrefWidth();
      st.draw(g);

      //keep the setup
      st.appendNewLine();

      st.buildForDisplayWith(strPlain, "iii_iii");
      int pw_iii = st.getMetrics().getPrefWidth();
      st.draw(g);
      st.appendNewLine();

      ByteObject strBlod = facFigure.getFigString(FACE_MONOSPACE, STYLE_BOLD, SIZE_3_MEDIUM, color);
      st.buildForDisplayWith(strBlod, "mmm_mmm");
      st.draw(g);
      st.appendNewLine();

      st.buildForDisplayWith(strBlod, "iii_iii");
      st.draw(g);
      st.appendNewLine();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMonospace", LVL_05_FINE, false);

      doImageTest(ri, "Monospace_mmm_vs_iii_plain");

      assertEquals(pw_iii, pw_mmm);

   }

   public void testMultiColor() {
      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 100;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(textFigure);

      ByteObject mask = facMask.getMaskGradient(FR_ORANGE_Abricot, FR_BLEU_Azur_clair);
      ByteObject fx = facStringFx.getFxChar(mask, 0);

      ByteObject anchor = facAnchor.getCenterCenter();
      StringerEditor editor = st.getEditor();
      int base = 8;
      int[] fontSizes = fontFac.getFontSizes();
      for (int i = 0; i < 10; i++) {
         int size = base + i;
         String str = String.valueOf(i);
         facStringFx.setFontSize(fx, size);
         editor.append(str, fx);

      }

      genericTestImg("MultiColor", st, imageW, imageH);

   }

   public void testMultiLines_Simple() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 200;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakType(BREAK_2_NATURAL);
      st.ToStringSetDebugArea(true);
      st.setString("I am free.\nI love it.");
      st.buildForDisplayWith(strFig);

      genericTestImg("MultiLines_Simple", st, imageW, imageH);

      assertEquals(2, st.getNumOfLines());

   }

   public void testJustifySimple() {

      resetFontToDefaults();

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);

      Stringer stringer = new Stringer(dc);

      int margin = 5;
      int areaW = 260;
      int areaH = 100;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;

      stringer.setAreaXYWH(margin, margin, areaW, areaH);
      stringer.ToStringSetDebugArea(true);
      stringer.ToStringSetDebugBreakLines(true);
      stringer.setBreakWidth(240);

      char[] chars = "###Life is a long snake. It takes forever to reach the tail. And once you get to it. What?##".toCharArray();
      int offset = 3;
      int len = chars.length - 5;
      stringer.setString(chars, offset, len);
      stringer.buildForDisplayWith(textFigure);

      StringMetrics sm = stringer.getMetrics();

      StringFx charFx = stringer.getCharFx(0);
      assertEquals(FACE_MONOSPACE, charFx.getFont().getFace());
      assertEquals(STYLE_PLAIN, charFx.getFont().getStyle());
      assertEquals(SIZE_4_LARGE, charFx.getFont().getSize());

      //#debug
      toDLog().pTest("Font CharFx", charFx.getFont(), TestStringer.class, "testJustifySimple", LVL_05_FINE, false);

      assertEquals(4, sm.getNumOfLines());

      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(230, sm.getLineWidth(1));
      assertEquals(230, sm.getLineWidth(2));
      assertEquals(170, sm.getLineWidth(3));

      genericTestImg("Justify_Simple", stringer, imageW, imageH);

      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_2_NICE_WORD);
      textFigure.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_2_JUSTIFIED);

      stringer.ToStringSetDebugArea(true);
      stringer.buildForDisplayWith(textFigure);

      assertEquals(4, sm.getNumOfLines());

      //same string as above but with linewidth as 240 for all except the last line
      assertEquals("Life is a long snake. It", sm.getLineString(0));
      assertEquals("takes forever to reach", sm.getLineString(1));
      assertEquals("the tail. And once you", sm.getLineString(2));
      assertEquals("get to it. What?", sm.getLineString(3));

      genericTestImg("Justify_Simple", stringer, imageW, imageH);

      assertEquals(240, sm.getLineWidth(0));
      assertEquals(240, sm.getLineWidth(1));
      assertEquals(240, sm.getLineWidth(2));
      assertEquals(160, sm.getLineWidth(3));

   }

   private void resetFontToDefaults() {
      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechFeaturesDraw.SUP_ID_06_CUSTOM_FONTS);
      //the family name must match exactly.. so no-
      fontCustomizer.setFontFamilyMonospace(null);
      fontCustomizer.setFontFamilyProportional(null);
      fontCustomizer.setFontFamilySystem(null);
   }

   public void testMultiLines_IvyGreen() {
      char[] data = uc.getIOU().readFileAsChars("/the_ivy_green.txt", "UTF-8");

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 310;
      int areaH = 410;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWidth(500); //does nothing in word wrap none
      st.ToStringSetDebugArea(true);
      st.setString(data, 0, data.length);

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_1_NORMAL);

      st.buildForDisplayWith(strFig);

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_IvyGreen", st, imageW, imageH);

      assertEquals(34, st.getNumOfLines());

      assertEquals(374, st.getMetrics().getPrefHeight());
      assertEquals(230, st.getMetrics().getPrefWidth());

      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_06_NEWLINE1, NEWLINE_MANAGER_1_WORK);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_07_WORDWRAP1, WORDWRAP_0_NONE);
      strFig.set1(IBOFigString.FIG_STRING_OFFSET_09_SPACE_TRIM1, SPACETRIM_2_JUSTIFIED);

      //or should it be justified on the biggest
      st.setBreakWidth(300); //does nothing in word wrap none

      st.buildForDisplayWith(strFig);

      genericTestImg("MultiLines_IvyGreen_Justified", st, imageW, imageH);

   }

   public void testMultiLines_Natural() {
      String data = uc.getIOU().readFileAsStringWindows("/string_multilines.txt", "UTF-8");

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 410;
      int areaH = 410;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakType(BREAK_2_NATURAL);
      st.setBreakWidth(100); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(data);

      st.buildForDisplayWith(strFig);

      //what about tabs?

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural", st, imageW, imageH);

      assertEquals(40, st.getNumOfLines());

   }

   public void testMultiLines_Natural1Line() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 410;
      int areaH = 50;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWidth(100); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(this.data);
      st.buildForDisplayWith(strFig);
      //#debug
      toDLog().pTest("", st, TestStringer.class, "MultiLines_Natural1Line", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural1Line", st, imageW, imageH);

      assertEquals(1, st.getNumOfLines());

      assertEquals(16, st.getMetrics().getPrefHeight());
      assertEquals(536, st.getMetrics().getPrefWidth());

   }

   /**
    * Test diffent {@link RgbImage#getGraphicsX()} modes.
    */
   public void testPlain() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.setStringFig(strFig, "Plain Line");
      st.buildForDisplayWith(strFig);

      RgbImage ri = rc.create(100, 100, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(MODE_1_IMAGE);

      st.draw(g);

      doImageTest(ri, "StringerPlainLineImage");

      ri = rc.create(100, 100, FULLY_OPAQUE_BLACK);
      GraphicsX gRgb = ri.getGraphicsX(GraphicsX.MODE_2_RGB_IMAGE);

      st.draw(gRgb);
      //ri.disposeGraphics();
      doImageTest(ri, "StringerPlainLineRGB");

   }

   public void testProportinal() {
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject strFig = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important
      st.setString("mmm_mmm");

      st.buildForDisplayWith(strFig);

      assertEquals(15, st.getMetrics().getPrefWidth());

      st.setString("iii_iii");
      st.buildForDisplayWith(strFig);

      assertEquals(15, st.getMetrics().getPrefWidth());

   }

   public void testTrimFitHeight() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      facFigure.setFigStringTrimFitH(strFig);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 200;
      int areaH = 50;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWH(areaW - 5, areaH - 5); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(this.data);
      st.buildForDisplayWith(strFig);

      genericTestImg("testTrimFitHeight", st, imageW, imageH);

      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);

      char[] chars = data.toCharArray();
      //init stringer area and data
      st.setAreaXYWH(5, 5, 70, 40);
      st.setStringFig(str, chars, 0, data.length());

      st.buildForDisplayWith(strFig);

      genericTestImg("StringTrimDoubleLine", st, 120, 50);

      assertEquals(st.getLen(), 14);
      assertEquals(st.getNumOfLines(), 2);

      assertEquals(true, st.hasState(ITechStringer.STATE_04_TRIMMED));

   }

   public void testCharWidthNewLine() {

      IMFont myFont = dc.getFontFactory().getFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM);
      int charWidthNewLine = myFont.charWidth('\n');

      assertEquals(0, charWidthNewLine);

      int charWidthTab = myFont.charWidth('\t');
      assertEquals(0, charWidthTab);

      int charWidthM = myFont.charWidth('m');
      assertEquals(8, charWidthM);

   }

   public void testTrimSingleLine() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 200;
      int areaH = 50;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakType(BREAK_4_TRIM_SINGLE_LINE);
      st.setBreakWidth(195); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.buildForDisplayWith(strFig, this.data);

      assertEquals(true, st.hasState(ITechStringer.STATE_04_TRIMMED));

      //#debug
      toDLog().pTest("", st, TestStringer.class, "TrimSingleLine", LVL_05_FINE, false);

      genericTestImg("TrimSingleLine", st, imageW, imageH);

      assertEquals(1, st.getNumOfLines());

      assertEquals(16, st.getMetrics().getPrefHeight());
      assertEquals(195, st.getMetrics().getPrefWidth());

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringer.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringer.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
