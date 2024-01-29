package pasa.cbentley.framework.drawx.src4.engine.tests;

import javax.swing.text.StyleConstants.ColorConstants;

import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkDrawX;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;

/**
 * Tests the creation of images.
 * <br>
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestImageCreation extends TestCaseFrameworkUiPluggedDrawX {

   public TestImageCreation() {
   }

   public void testCreateImg() {
      //empty
      RgbImage ri = rc.create(40, 50);

      assertEquals(40, ri.getWidth());
      assertEquals(50, ri.getHeight());

      int[] data = ri.getRgbData();

      for (int i = 0; i < data.length; i++) {
         assertEquals(data[i], 0);
      }

      GraphicsX g = ri.getGraphicsX();

      g.setColor(FULLY_OPAQUE_RED);
      g.fillRect(0, 0, 40, 50);
      
      data = ri.getRgbData();

      for (int i = 0; i < data.length; i++) {
         assertEquals(data[i], FULLY_OPAQUE_RED);
      }
      
      doImageTest(ri, "CreateImg");
      
   }
   
   public void testCreateImgRed() {
      //empty
      RgbImage ri = rc.create(40, 50,FULLY_OPAQUE_RED);
      assertEquals(40, ri.getWidth());
      assertEquals(50, ri.getHeight());

      int[] data = ri.getRgbData();

      for (int i = 0; i < data.length; i++) {
         assertEquals(data[i], FULLY_OPAQUE_RED);
      }
      
      doImageTest(ri, "CreateImgRed");
      
   }
}
