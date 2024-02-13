package pasa.cbentley.framework.drawx.src4.engine.tests.swing;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsX;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestGraphicsXSwing extends TestGraphicsX {

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   public void setupAbstractDrawX() {
   }

}