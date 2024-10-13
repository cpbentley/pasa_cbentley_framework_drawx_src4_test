package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.structs.IntInterval;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechHostFeatureDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.drawer.DrawerString;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.StringerEditor;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringDrw;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;
import pasa.cbentley.framework.drawx.src4.tech.ITechGraphicsX;

/**
 * Test the {@link DrawerString} from the base draw module.
 * <br>
 * <br>
 * 
 * @author Charles-Philip Bentley
 *
 */
public abstract class TestStringer extends TestCaseFrameworkUiPluggedDrawX implements ITechGraphicsX, ITechStringer, ITechStringDrw {

   private static boolean isStaticSetupDone = false;

   protected int          colorBgImage      = FULLY_OPAQUE_BLACK;

   String                 data              = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   ByteObject             str               = null;

   public TestStringer() {

   }

   protected ILogConfigurator createLogConfigurator() {
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

      st.draw(g);

      doImageTest(ri, name);
   }

   public ByteObject getMaskFigure(int scope) {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fx = getTestMask(scope);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_02_FLAGX, IBOFigString.FIG_STRING_FLAGX_2_DEFINED_FX, true);

      return strFig;
   }

   private ByteObject getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab() {
      return getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab(0);
   }

   private ByteObject getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab(int maxLines) {
      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim(maxLines);
      ByteObject auxSpecials = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();
      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE, strAuxFormat, auxSpecials);
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

   private void resetFontToDefaults() {
      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechHostFeatureDraw.SUP_ID_06_CUSTOM_FONTS);
      //the family name must match exactly.. so no-
      fontCustomizer.setFontFamilyMonospace(null);
      fontCustomizer.setFontFamilyProportional(null);
      fontCustomizer.setFontFamilySystem(null);
   }

   public void setupAbstractDrawX() {

      if (!isStaticSetupDone) {
         setupTestStringerStatic();
         isStaticSetupDone = true;
      }

      IFontCustomizer fontCustomizer = (IFontCustomizer) cdc.getFeatureObject(ITechHostFeatureDraw.SUP_ID_06_CUSTOM_FONTS);
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

   public void test4LinesAppend() {

      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim();
      ByteObject strAuxNewLine = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE, strAuxFormat, strAuxNewLine);

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

   /**
    * Test special capabilities of {@link Stringer#drawOffsets(GraphicsX, int, int, int, int, int, int)}
    * for scrolling purposes
    */
   public void testDrawOffsets() {


      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(5, 5, 120, 100);
      st.setString(data);
      st.setTextFigure(textFigure);
      st.setBreakOnArea();
      st.setFormatWordwrap(WORDWRAP_2_NICE_WORD);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.setDirectiveTab(SPECIALS_NEWLINE_1_SPACE_SPECIAL);
      st.ToStringSetDebugArea(true);
      st.buildFxAndMeter();

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

   public void testEmpty() {

      Stringer stringer = new Stringer(dc);

      assertNotNull(stringer.getDraw());
      assertNotNull(stringer.getMetrics());

      assertNull(stringer.getStringFx());

      int len = stringer.getLen();
      assertEquals(len, 0);

      String disString = stringer.getDisplayedString();
      assertEquals("", disString);
   }

   public void testWordIntervals() {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
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
