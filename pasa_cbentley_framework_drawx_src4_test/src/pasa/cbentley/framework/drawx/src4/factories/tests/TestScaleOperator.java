package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseDrawXPlugged;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.ScaleOperator;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOTblr;

public abstract class TestScaleOperator extends TestCaseDrawXPlugged implements IBOTblr {

   ScaleOperator op;

   public void setupAbstractDrawX() {
      op = dc.getScaleOperator();
   }

   public void testScaleBiCubic() {
      int w = 80;
      int h = 50;

      int type = ITechGradient.GRADIENT_TYPE_ELLIPSE_11_WATER_DROP_TOP;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_PINK, 50, type, FULLY_OPAQUE_SKY_GREEN);
      ByteObject figureEllipse = facFigure.getFigEllipse(FULLY_OPAQUE_ORANGE, grad);
      RgbImage img = opFigure.getFigImageTrans(figureEllipse, w, h);
      
      img = op.getScaledBiCubic(img, 40, 40);

      doImageTest(img, "BiCubic_Ellipse_scaled_40_40");

      img = op.getScaledBiCubic(img, 300, 400);

      doImageTest(img, "BiCubic_Ellipse_scaled_300_400");
   }

   public void testScaleBiLinear() {

      int w = 80;
      int h = 50;

      int type = ITechGradient.GRADIENT_TYPE_ELLIPSE_11_WATER_DROP_TOP;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_PINK, 50, type, FULLY_OPAQUE_SKY_GREEN);
      ByteObject figureEllipse = facFigure.getFigEllipse(FULLY_OPAQUE_ORANGE, grad);
      RgbImage img = opFigure.getFigImageTrans(figureEllipse, w, h);

      doImageTest(img, "BiLinear_Ellipse_based_80_50");
      img = op.getScaledBiLinear(img, 120, 70);

      doImageTest(img, "BiLinear_Ellipse_scaled_120_70");

      img = op.getScaledBiLinear(img, 40, 40);

      doImageTest(img, "BiLinear_Ellipse_scaled_40_40");


   }

}
