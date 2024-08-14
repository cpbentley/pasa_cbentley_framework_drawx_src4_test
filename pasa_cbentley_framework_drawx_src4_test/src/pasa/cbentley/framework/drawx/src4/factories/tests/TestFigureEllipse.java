package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;

public abstract class TestFigureEllipse extends TestFigureAbsract {

   public void testEllipse_Green() {
      ByteObject rect = fac.getFigEllipse(FULLY_OPAQUE_GREEN);

      testImageFigure("Ellipse_Green", rect);

   }
   
   public void testEllipse_Gradient() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_ELLIPSE_00_NORMAL);
      ByteObject rect = fac.getFigEllipse(FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Ellipse_Gradient_Cursor0", rect);

   }
   
   public void testEllipse_Gradient_Cursor50() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_ELLIPSE_00_NORMAL);
      ByteObject rect = fac.getFigEllipse(FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Ellipse_Gradient_Cursor50", rect);

   }
   
   public void testEllipse_Gradient_Cursor100_Fill() {
      
      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_ELLIPSE_00_NORMAL);
      
      ByteObject rect = fac.getFigEllipse(FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Ellipse_Gradient_Cursor50_Fill", rect);

   }
}
