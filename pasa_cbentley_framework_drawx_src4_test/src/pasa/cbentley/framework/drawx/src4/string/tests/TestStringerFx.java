package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ColorFunctionFactory;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.structs.IntIntervals;
import pasa.cbentley.core.src4.utils.interfaces.IColorsHTML;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigure;
import pasa.cbentley.framework.drawx.src4.string.StringFx;
import pasa.cbentley.framework.drawx.src4.string.StringFxLeaf;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.StringStyleLayer;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.StringerEditor;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;

public abstract class TestStringerFx extends TestStringerAbstract {

   public void testBackgroundFigure() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject fxSelect = facStringFx.getFxEffect(ITechStringer.FX_SCOPE_0_TEXT);

      fxSelect.set4(FX_OFFSET_09_COLOR4, FULLY_OPAQUE_GREEN);

      facStringFx.setFontTransparent(fxSelect);

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

      layerSelect.addInterval(1, 1, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("BackgroundBlue_1_1", st, imageW, imageH);

   }

   public void testBackgroundFigureLineAlone() {

      ByteObject gradientBgMain = facGradient.getGradient(FULLY_OPAQUE_YELLOW, 50, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figBgMain = facFigure.getFigRect(FULLY_OPAQUE_GREEN, gradientBgMain);
      ByteObject fx = facStringFx.getFxFigureBg(ITechStringer.FX_SCOPE_0_TEXT, figBgMain);

      int face = FACE_MONOSPACE;
      int style = STYLE_PLAIN;
      int size = SIZE_3_MEDIUM;
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject mask = null;
      ByteObject scale = null;
      ByteObject anchor = anchorFac.getCenterCenter();

      ByteObject strFig = facFigure.getFigString(face, style, size, color, fx, mask, scale, anchor);

      Stringer st = new Stringer(dc);

      int areaW = 180;
      int areaH = 70;

      String text = "##Bonjour!\nJe suis heureux.\nLe soleil brille!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      st.setString(chars, offset, len);
      st.setTextFigure(strFig);
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.ToStringSetDebugArea(true);

      st.buildFxAndMeter();

      genericTestImg("BackgroundLineAlone", st);

   }

   /**
    * Tests competing backgrounds. Select background takes over
    */
   public void testBackgroundFigureLineSelect() {

      ByteObject fxSelect = facStringFx.getFxColor(ITechStringer.FX_SCOPE_0_TEXT, FULLY_OPAQUE_BLACK);
      ByteObject gradientBg = facGradient.getGradient(FULLY_OPAQUE_GREY, 50, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figBgFx = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE, gradientBg);
      facStringFx.addFxFigure(fxSelect, figBgFx);

      //END SELECT

      ByteObject gradientBgMain = facGradient.getGradient(FULLY_OPAQUE_YELLOW, 50, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figBgMain = facFigure.getFigRect(FULLY_OPAQUE_GREEN, gradientBgMain);
      ByteObject fx = facStringFx.getFxFigureBg(ITechStringer.FX_SCOPE_0_TEXT, figBgMain);

      int face = FACE_MONOSPACE;
      int style = STYLE_PLAIN;
      int size = SIZE_3_MEDIUM;
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject mask = null;
      ByteObject scale = null;
      ByteObject anchor = anchorFac.getCenterCenter();

      ByteObject strFig = facFigure.getFigString(face, style, size, color, fx, mask, scale, anchor);

      Stringer st = new Stringer(dc);

      int areaW = 180;
      int areaH = 70;

      String text = "##Bonjour!\nJe suis heureux.\nLe soleil brille!###";
      char[] chars = text.toCharArray();
      int offset = 2;
      int trail = 3;
      int len = text.length() - offset - trail;
      st.setString(chars, offset, len);
      st.setTextFigure(strFig);
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.ToStringSetDebugArea(true);

      st.buildFxAndMeter();

      //create a 
      StringStyleLayer layerSelect = st.createLayer("select");

      layerSelect.addInterval(1, 5, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("BackgroundLineSelect_1_5", st);

   }

   public void testFxScaleFitFirst() {

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      ByteObject scaler = facScaler.getScaler(SCALER_ID_0_LINEAR, SCALER_TYPE_4_FIT_FIRST, null, null);

      strFig.addSub(scaler);
      strFig.setFlag(IBOFigure.FIG__OFFSET_04_FLAGX, IBOFigure.FIG_FLAGX_5_SCALER, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 120, 40);
      st.setStringFig(strFig, "Scaled String");
      st.buildFxAndMeter();

      genericTestImg("FxScalerFitFirst", st, 120, 40);
   }

   public void testFxSelectLayerSimple() {

      ByteObject fxSelect = getFxSelect();
      Stringer st = getStringerBonjour();

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
      assertEquals(FULLY_OPAQUE_ORANGE, bofx0.getFx().getSrcFx().get4(FX_OFFSET_09_COLOR4));

      StringFxLeaf bofx1 = (StringFxLeaf) intervalsOfLeaves.getInterval(1).getPayload();
      assertEquals(FULLY_OPAQUE_GREEN, bofx1.getFx().getSrcFx().get4(FX_OFFSET_09_COLOR4));

      Object fx3 = st.getCharFx(3);

      assertEquals(true, fx3 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_GREEN, ((StringFx) fx3).getColor());

      Object fx0 = st.getCharFx(0);
      assertEquals(true, fx0 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_ORANGE, ((StringFx) fx0).getColor());

      assertEquals(true, fx0 != fx3);

      st.buildFxAndMeter();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testFxSelectLayerSimple", LVL_05_FINE, false);

      genericTestImg("SelectLayerSimple", st);

   }

   public void testFxSelectLayerSimple1() {

      ByteObject fxSelect = getFxSelect();
      Stringer st = getStringerBonjour();

      //create a 
      StringStyleLayer layerSelect = st.createLayer("select");

      layerSelect.addInterval(0, 1, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_0_1", st);

      layerSelect.addInterval(0, 2, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_0_2", st);

      layerSelect.removeInterval(0, 1);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_Removed_0_1", st);

      layerSelect.addInterval(3, 3, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_3_3", st);

   }

   public void testFxSelectLayerSimpleBlueBg() {

      ByteObject fxSelect = getFxSelect();
      Stringer st = getStringerBonjour();

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
      assertEquals(FULLY_OPAQUE_ORANGE, bofx0.getFx().getSrcFx().get4(FX_OFFSET_09_COLOR4));

      StringFxLeaf bofx1 = (StringFxLeaf) intervalsOfLeaves.getInterval(1).getPayload();
      assertEquals(FULLY_OPAQUE_GREEN, bofx1.getFx().getSrcFx().get4(FX_OFFSET_09_COLOR4));

      Object fx3 = st.getCharFx(3);

      assertEquals(true, fx3 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_GREEN, ((StringFx) fx3).getColor());

      Object fx0 = st.getCharFx(0);
      assertEquals(true, fx0 instanceof StringFx);
      assertEquals(FULLY_OPAQUE_ORANGE, ((StringFx) fx0).getColor());

      assertEquals(true, fx0 != fx3);

      st.buildFxAndMeter();

      //#debug
      toDLog().pTest("", st, TestStringer.class, "testFxSelectLayerSimple", LVL_05_FINE, false);

      genericTestImg("SelectLayerSimpleBlueBgs", st);

   }

   public void testFxSelectLayerSimpleMerge() {

      ByteObject fxSelect = getFxSelect();
      Stringer st = getStringerBonjour();

      //create a 
      StringStyleLayer layerSelect = st.createLayer("select");

      layerSelect.addInterval(0, 1, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_0_1", st);

      layerSelect.addInterval(0, 2, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_0_2", st);

      layerSelect.removeInterval(0, 1);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_Removed_0_1", st);

      layerSelect.addInterval(3, 3, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_3_3", st);

      layerSelect.addInterval(2, 3, fxSelect);
      st.buildFxAndMeter();

      genericTestImg("SelectLayerSimple_merge", st);

   }

   public void testSingleLine1OverlayStyles() {

      ByteObject strAuxFormat = facStrAux.getStrAuxFormat_NoWrapNoTrim();
      ByteObject strAuxNewLine = facStrAux.getStrAuxSpecials_IgnoreNewLineTabSingleSpace();
      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE, strAuxFormat, strAuxNewLine);

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

   public void testMultiColor() {
      ByteObject anchor = facAnchor.getCenterCenter();

      ColorFunctionFactory cff = boc.getColorFunctionFactory();
      int[] values = new int[] { IColorsHTML.BLUE_CadetBlue, IColorsHTML.BLUE_MediumBlue, IColorsHTML.BLUE_PowderBlue, IColorsHTML.BLUE_CornflowerBlue, IColorsHTML.BLUE_Aqua };
      ByteObject cfun = cff.getColorFunction(values, true);

      ByteObject fx = facStringFx.getFxColorFunction(ITechStringer.FX_SCOPE_0_TEXT, cfun);
      //fx = null;
      ByteObject mask = null;
      ByteObject scale = null;
      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE, fx, mask, scale, anchor);

      Stringer st = new Stringer(dc);
      int areaW = 370;
      int areaH = 100;
      st.setAreaXYWH(margin, margin, areaW, areaH);
      st.setBreakOnArea();
      st.setTextFigure(textFigure);
      st.setString("Multicolor function applied on the text\nBlues\nNot so easy to debug actually.");
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);

      st.buildFxAndMeter();

      genericTestImg("MultiColor", st);

   }

   public void testFonts() {

      ByteObject textFigure = facStrAux.getFigStringMonoPlain(SIZE_4_LARGE, FULLY_OPAQUE_ORANGE);

      Stringer stringer = new Stringer(dc);
      stringer.setString("Font Large Monospace");
      stringer.setTextFigure(textFigure);
      stringer.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      stringer.buildFxAndMeter();

      genericTestImg("FontDifferences_MonoAlone", stringer, 150, 170);

      StringMetrics sm = stringer.getMetrics();
      StringerEditor editor = stringer.getEditor();

      editor.appendLine("Font Large Proportional", facStringFx.getFxEffectFontFace(FACE_PROPORTIONAL));
      editor.appendLine("Font Large System", facStringFx.getFxEffectFontFace(FACE_SYSTEM));
      editor.appendLine("Font Large Monospace Bold", facStringFx.getFxEffectFontFaceStyle(FACE_MONOSPACE, STYLE_BOLD));
      editor.appendLine("Font Large Proportional Bold", facStringFx.getFxEffectFontFaceStyle(FACE_PROPORTIONAL, STYLE_BOLD));
      editor.appendLine("Font Large System Bold", facStringFx.getFxEffectFontFaceStyle(FACE_SYSTEM, STYLE_BOLD));
      editor.appendLine("Font Large Monospace Italic", facStringFx.getFxEffectFontFaceStyle(FACE_MONOSPACE, STYLE_ITALIC));
      editor.appendLine("Font Large Proportional Italic", facStringFx.getFxEffectFontFaceStyle(FACE_PROPORTIONAL, STYLE_ITALIC));
      editor.appendLine("Font Large System Italic", facStringFx.getFxEffectFontFaceStyle(FACE_SYSTEM, STYLE_ITALIC));

      stringer.buildAgain();

      genericTestImg("FontDifferences", stringer, 300, 250);

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringerFx.class, 242);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringerFx.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

}
