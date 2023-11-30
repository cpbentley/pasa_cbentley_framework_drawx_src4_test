package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.RgbImageOperator;
import pasa.cbentley.framework.drawx.src4.tech.ITechTblr;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

public abstract class TestRgbImageOperator extends TestCaseDrawXPlugged implements ITechTblr {

   RgbImageOperator op;
   

   public void setupAbstractDrawX() {
      op = dc.getRgbImageOperator();
   }
   
   public void testTrim() {
      int w = 20;
      int h = 20;
      RgbImage ri = rc.create(w + 10, h + 10, FULLY_OPAQUE_GREEN);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
     
      g.setColor(IColors.FULLY_OPAQUE_RED);
      g.fillRect(5, 10, 8, 4);

      RgbImage riTrim0 = op.trim(ri, 0, FULLY_OPAQUE_GREEN);
      
      RgbImage riTrim1 = op.trim(ri, 1, FULLY_OPAQUE_GREEN);
      
      doImageTest(ri, "TrimRectSource");
      doImageTest(riTrim0, "TrimRect0Pad");
      doImageTest(riTrim1, "TrimRect1Pad");

   }

}
