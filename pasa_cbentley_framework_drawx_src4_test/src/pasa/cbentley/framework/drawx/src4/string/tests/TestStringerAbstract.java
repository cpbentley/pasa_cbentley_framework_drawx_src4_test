package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.framework.coredraw.src4.ctx.CoreDrawCtx;
import pasa.cbentley.framework.coredraw.src4.interfaces.IFontCustomizer;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechHostFeatureDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.drawer.DrawerString;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOStrAuxFx;
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
public abstract class TestStringerAbstract extends TestCaseFrameworkUiPluggedDrawX implements ITechGraphicsX, ITechStringer, ITechStringDrw, IBOStrAuxFx {

   private static boolean isStaticSetupDone = false;

   protected int          colorBgImage      = FULLY_OPAQUE_BLACK;

   String                 data              = "Hello I'm Joe! I would like to eat some meat and fish and potatoes.";

   protected int          margin            = 5;

   ByteObject             str               = null;

   public TestStringerAbstract() {

   }

   protected ILogConfigurator createLogConfigurator() {
      return new LogConfiguratorTestStringer();
   }

   public void genericTestImg(String name, Stringer st) {

      int w = st.getAreaW() + 10;
      int h = st.getAreaH() + 10;
      RgbImage ri = rc.create(w, h, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      st.draw(g);

      doImageTest(ri, name);
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

   protected ByteObject getFxSelect() {
      ByteObject fxSelect = facStringFx.getFxEffect(ITechStringer.FX_SCOPE_0_TEXT);
      fxSelect.set4(FX_OFFSET_09_COLOR4, FULLY_OPAQUE_GREEN);
      facStringFx.setFontTransparent(fxSelect);
      ByteObject figBgFx = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE);
      facStringFx.addFxFigure(fxSelect, figBgFx);
      return fxSelect;
   }

   public ByteObject getMaskFigure(int scope) {
      ByteObject strFig = facFigure.getFigString("Masked String", FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fx = getTestMask(scope);

      strFig.addSub(fx);
      strFig.setFlag(IBOFigString.FIG_STRING_OFFSET_02_FLAGX, IBOFigString.FIG_STRING_FLAGX_2_DEFINED_FX, true);

      return strFig;
   }

   protected ByteObject getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab() {
      return getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab(0);
   }

   protected ByteObject getStrFigOrangeMediumNiceWordNormalTrimNewLineworkSpaceTab(int maxLines) {
      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NiceWordNormalTrim(maxLines);
      ByteObject auxSpecials = facStrAux.getStrAuxSpecials_NewLineWorkSingleSpaceTab();
      ByteObject strFig = facStrAux.getFigStringMonoPlain(SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE, strAuxFormat, auxSpecials);
      return strFig;
   }

   protected Stringer getStringerBonjour() {
      ByteObject strFig = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      Stringer st = new Stringer(dc);

      int areaW = 150;
      int areaH = 40;

      String text = "##Bonjour!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      st.setString(chars, offset, len);
      st.setTextFigure(strFig);
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.ToStringSetDebugArea(true);
      return st;
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

   protected void resetFontToDefaults() {
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

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringerAbstract.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringerAbstract.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
