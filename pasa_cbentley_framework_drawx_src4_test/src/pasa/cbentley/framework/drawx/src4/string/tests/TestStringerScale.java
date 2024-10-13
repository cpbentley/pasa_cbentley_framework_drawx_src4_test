package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigure;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.tech.ITechScaler;

public abstract class TestStringerScale extends TestStringerAbstract {

   public void testFxScaleFitFirst() {

      ByteObject strFig = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_2_SMALL, FULLY_OPAQUE_ORANGE);

      ByteObject scaler = facScaler.getScaler(SCALER_ID_0_LINEAR, SCALER_TYPE_4_FIT_FIRST, null, null);

      strFig.addSub(scaler);
      strFig.setFlag(IBOFigure.FIG__OFFSET_04_FLAGX, IBOFigure.FIG_FLAGX_5_SCALER, true);

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(0, 0, 120, 40);
      st.setStringFig(strFig, "Scaled String");
      st.buildFxAndMeter();

      genericTestImg("FxScalerFitFirst", st, 120, 40);
   }

   public void testDrawScaled() {
      ByteObject textFigure = facFigure.getFigString(FACE_01_MONOSPACE, STYLE_0_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

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
      st.buildForDisplayWith(textFigure, "qw");

      genericTestImg("DrawScaled_qw", st, imageW, imageH);

   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringerScale.class, 242);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringerScale.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug

}
