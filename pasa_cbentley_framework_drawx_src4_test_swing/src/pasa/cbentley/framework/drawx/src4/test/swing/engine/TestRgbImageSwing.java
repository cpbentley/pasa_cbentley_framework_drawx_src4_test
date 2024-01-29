package pasa.cbentley.framework.drawx.src4.test.swing.engine;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbImage;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

/**
 * 
 * @author Charles Bentley
 *
 */
public class TestRgbImageSwing extends TestRgbImage {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }
   
   public void setupAbstractDrawX() {

   }

   public void testGetPixel_Rgb() {
      super.testGetPixel_Rgb();
   }

   public void testGetPixel_Image() {
      super.testGetPixel_Image();
   }
   
   public void testCrop() {
      super.testCrop();
   }
}
