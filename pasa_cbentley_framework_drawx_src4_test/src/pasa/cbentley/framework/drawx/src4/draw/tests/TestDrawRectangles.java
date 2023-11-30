package pasa.cbentley.framework.drawx.src4.draw.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXSwing;

public abstract class TestDrawRectangles extends TestCaseDrawXPlugged {

   public void testBorder() {

      ByteObject tblr = facTBLR.getTBLR(4);
      ByteObject gradBorder = facGradient.getGradient(FULLY_OPAQUE_WHITE, 100, GRADIENT_TYPE_RECT_02_VERT);
      ByteObject border1 = facFigure.getFigBorder(tblr, 5, 5, FULLY_OPAQUE_BLUE, gradBorder);

      //draws several border on top of each other.

      gradBorder = facGradient.getGradient(FULLY_OPAQUE_WHITE, 100, GRADIENT_TYPE_RECT_02_VERT);
      ByteObject border2 = facFigure.getFigBorder(tblr, 5, 5, FULLY_OPAQUE_GREEN, gradBorder);

      gradBorder = facGradient.getGradient(FULLY_OPAQUE_WHITE, 100, GRADIENT_TYPE_RECT_02_VERT);
      ByteObject border3 = facFigure.getFigBorder(tblr, 0, 0, FULLY_OPAQUE_GREEN, gradBorder);

      ByteObject[] borders = new ByteObject[] { border1, border2, border3 };

      genericTestFigure("BorderArc5-5", borders, 50, 20);
   }


   public void setupAbstractDrawX() {

   }

}
