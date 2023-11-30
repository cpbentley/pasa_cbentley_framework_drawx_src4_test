package pasa.cbentley.framework.drawx.src4.string.tests;

import java.io.InputStream;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.drawer.DrawerString;
import pasa.cbentley.framework.drawx.src4.string.IBOFxStr;
import pasa.cbentley.framework.drawx.src4.string.IBOFxStrLine;
import pasa.cbentley.framework.drawx.src4.string.ITechStringDrw;
import pasa.cbentley.framework.drawx.src4.string.ITechStringer;
import pasa.cbentley.framework.drawx.src4.string.StringLayerStyle;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.tech.IBOFigString;
import pasa.cbentley.framework.drawx.src4.tech.ITechGraphicsX;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

/**
 * Test the {@link DrawerString} from the base draw module.
 * <br>
 * <br>
 * 
 * @author Charles-Philip Bentley
 *
 */
public abstract class TestStringer extends TestCaseDrawXPlugged implements ITechGraphicsX, ITechStringer, ITechStringDrw, IBOFxStr, IBOFxStrLine {

   private static boolean isStaticSetupDone = false;

   protected int          colorBgImage      = FULLY_OPAQUE_BLACK;

   String                 data              = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   GraphicsX              g                 = null;

   RgbImage               ri                = null;

   ByteObject             str               = null;

   public TestStringer() {

   }

   public ILogConfigurator createLogConfigurator() {
      return new LogConfiguratorTestStringer();
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
      ByteObject mask = facMask.getMaskPreset(0, figure, filter);

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

      String fontStr = "/fonts/Monoid-Regular.ttf";
      InputStream is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);

      fontCustomizer.loadFont(is, fontStr);

      fontStr = "/fonts/Monoid-Bold.ttf";
      is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);
      fontCustomizer.loadFont(is, fontStr);

      fontStr = "/fonts/Monoid-Italic.ttf";
      is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);
      fontCustomizer.loadFont(is, fontStr);

      fontStr = "/fonts/Aleo-Regular.otf";
      is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);

      fontCustomizer.loadFont(is, fontStr);

      fontStr = "/fonts/Aleo-Bold.otf";
      is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);
      fontCustomizer.loadFont(is, fontStr);

      fontStr = "/fonts/Aleo-Italic.otf";
      is = this.getClass().getResourceAsStream(fontStr);
      uc.toStringCheckNull(is);
      fontCustomizer.loadFont(is, fontStr);
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

      ByteObject mask = facMask.getMaskGradient(FR_ORANGE_Abricot, FR_BLEU_Azur_clair);
      ByteObject fx = facStringFx.getFxChar(mask, 0);
      int base = 8;
      for (int i = 0; i < 10; i++) {
         int size = base + i;
         String str = String.valueOf(i);
         ByteObject textFigure = facFigure.getFigString(str, FACE_MONOSPACE, STYLE_PLAIN, size, FULLY_OPAQUE_ORANGE);

         st.append(textFigure);

      }

      genericTestImg("MultiColor", st, imageW, imageH);

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
      st.setString(data);

      st.setTextFigure(textFigure);
      st.meterString();

      genericTestImg("BreakWidth_BigSingleLine_Mono", st, imageW, imageH);

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_BOLD, 14, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(textFigure);
      st.meterString();

      genericTestImg("BreakWidth_BigSingleLine_Mono_Bold", st, imageW, imageH);

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_ITALIC, 14, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(textFigure);
      st.meterString();

      genericTestImg("BreakWidth_BigSingleLine_Mono_Italic", st, imageW, imageH);

      //      st = new Stringer(dc);
      //      st.setAreaXYWH(margin, margin, areaW, areaH);
      //      st.setBreakOnArea();
      //      st.ToStringSetDebugArea(true);
      //      st.setBreakType(BREAK_1_WIDTH);
      //      st.setString(data);
      ByteObject strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, 14, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(strProp);
      st.meterString();

      assertEquals(2, st.getNumOfLines());

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testBreakWidthBigSingleLine", LVL_05_FINE, false);

      genericTestImg("BreakWidth_BigSingleLine_Prop", st, imageW, imageH);

      strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_BOLD, 14, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(strProp);
      st.meterString();

      genericTestImg("BreakWidth_BigSingleLine_Prop_Bold", st, imageW, imageH);

      strProp = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_ITALIC, 14, FULLY_OPAQUE_ORANGE);
      st.setTextFigure(strProp);
      st.meterString();

      genericTestImg("BreakWidth_BigSingleLine_Prop_Italic", st, imageW, imageH);
   }

   public void testFormatWidth30() {
      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.ToStringSetDebugArea(true);

      int breakWidth = 30;
      int margin = 5;
      int areaW = 60;
      int areaH = 80;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakWH(breakWidth, 0);
      st.setBreakType(BREAK_1_WIDTH);
      st.setString("Bonjour Joe!");
      st.setTextFigure(str);

      st.meterString();

      assertEquals(true, st.hasState(ITechStringer.STATE_07_BROKEN));

      genericTestImg("FormatWidth30", st, imageW, imageH);
   }

   public void testGetNumofLines() {

   }

   public void testMonospace() {
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject strPlain = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important

      ri = rc.create(100, 100, colorBgImage);
      g = ri.getGraphicsX(MODE_1_IMAGE);

      st.initFig(strPlain, "mmm_mmm");
      st.meterString();
      int pw_mmm = st.getMetrics().getPrefWidth();
      st.draw(g);

      //keep the setup
      st.newLine();

      st.initFig(strPlain, "iii_iii");
      st.meterString();
      int pw_iii = st.getMetrics().getPrefWidth();
      st.draw(g);
      st.newLine();

      ByteObject strBlod = facFigure.getFigString(FACE_MONOSPACE, STYLE_BOLD, SIZE_3_MEDIUM, color);
      st.initFig(strBlod, "mmm_mmm");
      st.meterString();
      st.draw(g);
      st.newLine();

      st.initFig(strBlod, "iii_iii");
      st.meterString();
      st.draw(g);
      st.newLine();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMonospace", LVL_05_FINE, false);

      doImageTest(ri, "Monospace_mmm_vs_iii_plain");

      assertEquals(pw_iii, pw_mmm);

   }

   public void testInterval() {
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject strPlain = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important

      ri = rc.create(100, 100, colorBgImage);
      g = ri.getGraphicsX(MODE_1_IMAGE);

      st.initFig(strPlain, "mmm_mmm");

      st.meterString();

   }

   /**
    * Test diffent {@link RgbImage#getGraphicsX()} modes.
    */
   public void testPlain() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.initFig(strFig, "Plain Line");
      st.meterString();

      assertEquals(ITechStringer.TYPE_0_SINGLE_LINE, st.getDrawType());

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
      ByteObject str = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_3_MEDIUM, color);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 30); //not really important
      st.initFig(str, "mmm_mmm");

      st.meterString();

      assertEquals(15, st.getMetrics().getPrefWidth());

      st.initFig(str, "iii_iii");
      st.meterString();
      assertEquals(15, st.getMetrics().getPrefWidth());

   }

   public void testBreak1Width() {

      ByteObject str = facFigure.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);

      st.setAreaXYWH(5, 5, 110, 110);
      st.setBreakType(BREAK_1_WIDTH);
      st.setBreakWidth(100);
      st.ToStringSetDebugArea(true);

      //init stringer area and data
      st.initFig(str, data);

      st.meterString();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testBreak1Width", LVL_05_FINE, false);

      assertEquals(ITechStringer.TYPE_2_BREAKS, st.getDrawType());

      StringMetrics metrics = st.getMetrics();
      int[] trimResult = metrics.getBreaks();

      assertNotNull(trimResult);

      genericTestImg("Break1Width", st, 120, 140);

      int numLines = trimResult[0] / 3;

      assertEquals(4, numLines);
      assertEquals(4, st.getNumOfLines());

      //System.out.println(MUtils.debugString(trimResult));
      assertEquals(0, trimResult[1]);
      assertEquals(17, trimResult[2]);

      assertEquals("Hello I'm Joe! I ", data.substring(trimResult[1], trimResult[1] + trimResult[2]));

      assertEquals(17, trimResult[4]);
      assertEquals(18, trimResult[5]);
      assertEquals("would like to eat ", data.substring(trimResult[4], trimResult[4] + trimResult[5]));

      assertEquals(35, trimResult[7]);
      assertEquals(19, trimResult[8]);
      assertEquals("some meat and fish ", data.substring(trimResult[7], trimResult[7] + trimResult[8]));

      assertEquals(54, trimResult[10]);
      assertEquals(13, trimResult[11]);
      assertEquals("and potatoes.", data.substring(trimResult[10], trimResult[10] + trimResult[11]));

      trimResult = metrics.getBreaks();
      assertNotNull(trimResult);

      assertEquals(48, metrics.getPrefHeight());
      assertEquals(97, metrics.getPrefWidth());

      //draws all

      //draws only offsets
      RgbImage ri = rc.create(120, 80, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      //second line, 10 characters after the 3
      int dy = 0;
      st.drawOffsets(g, 0, dy, 0, 10, 1, 1);
      dy += metrics.getLineHeight();
      st.drawOffsets(g, 0, dy, 1, 10, 1, 1);
      dy += metrics.getLineHeight();
      st.drawOffsets(g, 0, dy, 2, 10, 1, 1);
      dy += metrics.getLineHeight();
      st.drawOffsets(g, 0, dy, 3, 10, 1, 1);
      dy += metrics.getLineHeight();
      st.drawOffsets(g, 0, dy, 4, 10, 1, 1);

      doImageTest(ri, "Break1Width_2ndLine");

      ri = rc.create(120, 40, FULLY_OPAQUE_BLACK);
      g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      //second line, 10 characters after the 3
      st.drawOffsets(g, 0, 0, 0, 100, 2, 2);

      doImageTest(ri, "Break1Width_Last2Lines");

      str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);

      ByteObject anchor = facAnchor.getCenterCenter();

      //#debug
      toDLog().pTest("Anchor", anchor, TestStringer.class, "testBreak1Width", LVL_05_FINE, false);

      facFigure.addAnchor(str, anchor);

      st = new Stringer(dc);
      st.setAreaXYWH(5, 5, 180, 180);

      st.ToStringSetDebugArea(true);
      //init stringer area and data
      st.initFig(str, data);

      st.meterString(ITechStringDrw.BREAK_1_WIDTH, 0);

      //assertEquals(178, st.getMetrics().getPrefWidthLine(0));
      //assertEquals(168, st.getMetrics().getPrefWidthLine(1));

      genericTestImg("Break1Width_AnchorCenter", st, 210, 210);

   }

   public void testAddChar() {
      Stringer stringer = new Stringer(dc);

      char[] cs = "Hello".toCharArray();

      stringer.addChar(cs, 0, 'H');

      assertEquals(stringer.getLen(), 1);

      assertEquals("H", stringer.getDisplayedString());

      stringer.addChar(cs, 1, 'H');

      assertEquals(stringer.getLen(), 2);
      assertEquals("He", stringer.getDisplayedString());

   }

   public void testDrawOffsets() {
      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.setBreakType(BREAK_1_WIDTH);
      st.setBreakMaxLines(4);
      st.initFig(strFig, data);
      st.meterString();

      int numLines = st.getMetrics().getNumOfLines();
      assertEquals(numLines, 5);
      RgbImage ri = rc.create(100, 100, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_2_RGB_IMAGE);
      st.drawOffsets(g, 0, 0, 0, 100, 1, 1);

      doImageTest(ri, "DrawOffsets");
   }

   public void testMultiLines_IvyGreen() {
      String data = uc.getIOU().readFileAsStringWindows("/the_ivy_green.txt", "UTF-8");

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_1_TINY, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 310;
      int areaH = 410;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakType(BREAK_2_NATURAL);
      st.setBreakWidth(300); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(data);
      st.setTextFigure(strFig);

      st.meterString();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_IvyGreen", st, imageW, imageH);

      assertEquals(34, st.getNumOfLines());

      assertEquals(14, st.getMetrics().getPrefHeight());
      assertEquals(14, st.getMetrics().getPrefWidth());

   }

   public void testTrimSingleLine() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      assertNotNull(data);

      Stringer st = new Stringer(dc);
      int margin = 5;
      int areaW = 410;
      int areaH = 50;
      int imageW = areaW + margin * 2;
      int imageH = areaH + margin * 2;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakType(BREAK_4_TRIM_SINGLE_LINE);
      st.setBreakWidth(100); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(this.data);
      st.setTextFigure(strFig);
      st.meterString();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "TrimSingleLine", LVL_05_FINE, false);

      genericTestImg("TrimSingleLine", st, imageW, imageH);

      assertEquals(1, st.getNumOfLines());

      assertEquals(16, st.getMetrics().getPrefHeight());
      assertEquals(536, st.getMetrics().getPrefWidth());

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
      st.setBreakType(BREAK_2_NATURAL);
      st.setBreakWidth(100); //does nothing in natural mode
      st.ToStringSetDebugArea(true);
      st.setString(this.data);
      st.setTextFigure(strFig);
      st.meterString();
      //#debug
      toDLog().pTest("", st, TestStringer.class, "MultiLines_Natural1Line", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural1Line", st, imageW, imageH);

      assertEquals(1, st.getNumOfLines());

      assertEquals(16, st.getMetrics().getPrefHeight());
      assertEquals(536, st.getMetrics().getPrefWidth());

      st.setBreakType(BREAK_4_TRIM_SINGLE_LINE);
      st.meterString();

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
      st.setTextFigure(strFig);

      st.meterString();

      //what about tabs?

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testMultiLines_Natural", LVL_05_FINE, false);

      genericTestImg("MultiLines_Natural", st, imageW, imageH);

      assertEquals(39, st.getNumOfLines());

      assertEquals(14, st.getMetrics().getPrefHeight());
      assertEquals(14, st.getMetrics().getPrefWidth());
   }

   public void testEmpty() {

      Stringer stringer = new Stringer(dc);

      assertNotNull(stringer.getDraw());
      assertNotNull(stringer.getMetrics());
      assertNotNull(stringer.getStringFx());

      int len = stringer.getLen();
      assertEquals(len, 0);

      int drawType = stringer.getDrawType();
      assertEquals(drawType, 0);

      String disString = stringer.getDisplayedString();
      assertEquals("", disString);
   }

   public void testFxMaskChar() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fx = getTestMask(FX_SCOPE_0_CHAR);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      Stringer st = new Stringer(dc);

      int dy = 0;
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();

      assertEquals(ITechStringer.TYPE_1_SINGLE_LINE_FX, st.getDrawType());

      RgbImage ri = rc.create(100, 200, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_2_RGB_IMAGE);
      st.draw(g);

      //remove mask and draw on another line

      int height = st.getMetrics().getLineHeight();
      dy += (height + 5);
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();
      st.draw(g);

      height = st.getMetrics().getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_0_CHAR, GRADIENT_TYPE_RECT_01_HORIZ, 100);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();
      st.draw(g);

      height = st.getMetrics().getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_0_CHAR, GRADIENT_TYPE_RECT_00_SQUARE, 100);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();
      st.draw(g);

      height = st.getMetrics().getLineHeight();
      dy += (height + 5);

      //draw different mask figure
      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_0_CHAR, GRADIENT_TYPE_RECT_03_TOPLEFT, 100);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();
      st.draw(g);

      height = st.getMetrics().getLineHeight();
      dy += (height + 5);

      strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      fx = getTestMask(FX_SCOPE_0_CHAR, GRADIENT_TYPE_RECT_07_L_TOP, 100);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_01_FLAG, IBOFigString.FIG_STRING_FLAG_5_EFFECT, true);

      st = new Stringer(dc);
      st.setAreaXYWH(0, dy, 100, 100);
      st.initFig(strFig, "Masked Chars");
      st.meterString();
      st.draw(g);

      doImageTest(ri, "StringerMaskedChar");
   }

   public void testFxMaskLineDiag() {

      ByteObject strFig = getMaskFigure(FX_SCOPE_2_LINE);

      ByteObject fx = strFig.getSubFirst(TYPE_070_TEXT_EFFECTS);
      fx.set1(FXLINE_OFFSET_03_CHAR_Y_OFFSET1, 2);
      fx.setFlag(FXLINE_OFFSET_01_FLAG, FXLINE_FLAG_6_DEFINED_YF, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.initFig(strFig, "One Masked Diagonal Line");

      genericTestImg("StringerMaskLineDiag2Pixels", st, 120, 40);
   }

   public void testFxMaskLineOne() {

      ByteObject strFig = getMaskFigure(FX_SCOPE_2_LINE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 100, 100);
      st.initFig(strFig, "One Masked Line");

      genericTestImg("StringerMaskLineOne", st, 120, 40);
   }

   public void testFxMaskWord() {
      ByteObject strFig = getMaskFigure(FX_SCOPE_1_WORD);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 200, 100);
      st.initFig(strFig, "Several Masked Word");

      genericTestImg("StringerMaskWord", st, 120, 40);
   }

   /**
    * Tests the figure hook for scaling using {@link IDrwTypes#TYPE_055_SCALE} on the figure.
    * 
    */
   public void testFxScaleFitFirst() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);

      ByteObject scaler = facScaler.getScaler(SCALER_ID_0_LINEAR, SCALER_TYPE_4_FIT_FIRST, null, null);

      strFig.addSub(scaler);
      strFig.setFlag(FIG__OFFSET_04_FLAGX, FIG_FLAGX_5_SCALER, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 120, 40);
      st.initFig(strFig, "Scaled String");

      genericTestImg("FxScalerFitFirst", st, 120, 40);
   }

   public void testFxDynamic() {

      IMFont f = cdc.getFontFactory().getDefaultFont();

      ByteObject strFig = facFigure.getFigString(f, FULLY_OPAQUE_ORANGE);
      
      ByteObject fxChar = facStringFx.getFx(FX_SCOPE_0_CHAR);
      fxChar.setFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_2_DYNAMIC, true);

      ByteObject fig = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE);
      fxChar.addByteObject(fig);

      fxChar.setFlag(FX_OFFSET_03_FLAGZ, FX_FLAGZ_2_FIGURE, true);

      facFigure.addTxtFXToStringFig(strFig, fxChar);

      Stringer st = new Stringer(dc);

      //create a 
      StringLayerStyle layer = st.createLayer("select");
      
      char[] chars = "Bonjour!".toCharArray();
      st.setAreaXYWH(0, 0, 150, 40);
      st.initFig(strFig, chars, 0, chars.length);

      genericTestImg("FxDyn1", st, 120, 50);

      layer.addInterval(2, 2, fxChar);
      
      //st.setInterval(2, 2, 1);

      genericTestImg("FxDyn22", st, 120, 50);

   }

   /**
    * Simply tests the {@link StringMetrics#getTrimSingleLine(int, Stringer)}
    * <br>
    * <br>
    * 
    */
   public void testTrim() {
      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      String data = "Bonjour I'm Joe!";
      char[] chars = data.toCharArray();
      //init stringer area and data
      st.setAreaXYWH(5, 5, 20, 20);
      st.initFig(str, chars, 0, data.length());

      //the trimming will depends on the size of the font
      int[] trimResult = st.getTrimSingleLine(20);

      assertNotNull(trimResult);

      assertEquals(18, trimResult[3]);

      //what is the process to break the string?
      st.meterString(20, 20, ITechStringDrw.BREAK_4_TRIM_SINGLE_LINE, 1);

      genericTestImg("StringTrim", st, 120, 50);

   }

   public void testTrimFitHeight() {
      ByteObject str = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 11, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      char[] chars = data.toCharArray();
      //init stringer area and data
      st.setAreaXYWH(5, 5, 70, 40);
      st.initFig(str, chars, 0, data.length());

      int[] trim = st.getTrimFormat(30, 2);

      assertEquals(trim.length, 7);

      st.meterString(50, 40, ITechStringDrw.BREAK_5_TRIM_FIT_HEIGHT, 2);

      assertEquals(st.getLen(), 21);
      assertEquals(st.getNumOfLines(), 2);

      assertEquals(true, st.hasState(ITechStringer.STATE_04_TRIMMED));

      genericTestImg("StringTrimDoubleLine", st, 120, 50);

   }
}
