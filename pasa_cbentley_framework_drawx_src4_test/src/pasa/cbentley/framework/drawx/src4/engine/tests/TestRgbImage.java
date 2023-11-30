package pasa.cbentley.framework.drawx.src4.engine.tests;

import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

/**
 * Check the .classpath file for correct ordering of export.
 * <br>
 * <br>
 * 
 * @author Mordan
 *
 */
public abstract class TestRgbImage extends TestCaseDrawXPlugged  {

   public TestRgbImage() {
      
   }

   public void testClone() {

      RgbImage ri = rc.create(100, 130, FULLY_OPAQUE_BLUE);

      RgbImage clone = ri.cloneImg();

      assertEquals(clone.getWidth(), 100);
      assertEquals(clone.getHeight(), 130);

   }

   /**
    * Test variation of {@link RgbImage#getGraphicsX()} in a neutral manner.
    */
   public void testGetGraphicsNeutral() {
      //TODO
      RgbImage ri = rc.create(40, 20, FULLY_OPAQUE_BLACK);

      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      RgbImage r = rc.create(40, 20, FULLY_OPAQUE_BLACK);

      GraphicsX gi = r.getGraphicsX();

      assertEquals(GraphicsX.MODE_1_IMAGE, gi.getPaintMode());

      gi.setColor(FULLY_OPAQUE_ORANGE);
      gi.fillRect(0, 0, 25, 10);

      g.drawRgbImage(r, 5, 5);

      doImageTest(ri, "RgbOpaqueBlack");
   }

   public void testGetGraphicsNeutralTras() {
      RgbImage ri = rc.create(40, 20, FULLY_OPAQUE_BLACK);

      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      RgbImage r = rc.create(40, 20, 0);

      GraphicsX gi = r.getGraphicsX();

      assertEquals(GraphicsX.MODE_2_RGB_IMAGE, gi.getPaintMode());

      gi.setColor(FULLY_OPAQUE_ORANGE);
      gi.fillRect(0, 0, 25, 10);

      g.drawRgbImage(r, 5, 5);

      doImageTest(ri, "RgbTransBgBlack");
   }

   public void setupAbstractDrawX() {
      
   }
}
