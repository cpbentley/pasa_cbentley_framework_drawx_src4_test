package pasa.cbentley.framework.drawx.src4.test.swing.engine;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestImageCreation;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

/**
 * 
 * @author Charles Bentley
 *
 */
public class TestImageCreationSwing extends TestImageCreation {


   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   public void setupAbstractDrawX() {
      
   }


}
