package pasa.cbentley.framework.drawx.src4.test.fx;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestImageCreation;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugGuiFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestImageCreationFX extends TestImageCreation {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiFx((TestFrameworkUiFxCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiFxCtx(boc);
   }

   public void setupAbstractDrawX() {

   }

}
