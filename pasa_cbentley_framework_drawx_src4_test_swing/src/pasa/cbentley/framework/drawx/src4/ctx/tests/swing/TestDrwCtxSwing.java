package pasa.cbentley.framework.drawx.src4.ctx.tests.swing;

import pasa.cbentley.framework.drawx.src4.ctx.tests.TestDrwCtx;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestDrwCtxSwing extends TestDrwCtx {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }

 

}
