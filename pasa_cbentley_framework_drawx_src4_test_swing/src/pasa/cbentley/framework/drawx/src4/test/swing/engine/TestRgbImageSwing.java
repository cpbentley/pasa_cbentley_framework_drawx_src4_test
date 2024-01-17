package pasa.cbentley.framework.drawx.src4.test.swing.engine;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestImageCreation;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbCache;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbImage;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.TestCaseFrameworkUiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

/**
 * 
 * @author Charles Bentley
 *
 */
public class TestRgbImageSwing extends TestRgbImage {

   private TestFrameworkUiSwingCtx tUiSwingCtx;

   public void setTestCtx(TestCtx tc) {
      tUiSwingCtx = (TestFrameworkUiSwingCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {
      ConfigPlugSwingDef configPlug = new ConfigPlugSwingDef(getC5());
      FrameworkPlugSwing plug = new FrameworkPlugSwing(configPlug, tUiSwingCtx, this);
      return plug;
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
