package pasa.cbentley.framework.drawx.src4.test.swing.factories;

import pasa.cbentley.framework.drawx.src4.factories.tests.TestScaleOperator;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestScaleOperatorSwing extends TestScaleOperator {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }
}
