package pasa.cbentley.framework.drawx.src4.draw.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXSwing;

public abstract class TestDrawEllipse extends TestCaseDrawXPlugged {

   public void testEllipseSimple() {

      ByteObject ellipse = facFigure.getFigEllipse(FULLY_OPAQUE_WHITE);

      genericTestFigure("EllipseSimpleWhite50_20", ellipse, 50, 20);

      genericTestFigure("EllipseSimpleWhite50_200", ellipse, 50, 200);
   }

   public void testEllipseGradient() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100, GRADIENT_TYPE_ELLIPSE_00_NORMAL);
      ByteObject ellipse = facFigure.getFigEllipse(FULLY_OPAQUE_WHITE, grad);

      genericTestFigure("EllipseGradientWhite60_40", ellipse, 60, 40);

      grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 0, GRADIENT_TYPE_ELLIPSE_00_NORMAL);
      ellipse = facFigure.getFigEllipse(FULLY_OPAQUE_WHITE, grad);

      genericTestFigure("EllipseGradientWhite60_60", ellipse, 60, 60);
   }

   public String getSuffix() {
      return null;
   }

   public void setupAbstractDrawX() {

   }

}
