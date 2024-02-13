package pasa.cbentley.framework.drawx.src4.ctx.tests.fx;

import pasa.cbentley.framework.drawx.src4.ctx.tests.TestDrwCtx;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugGuiFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestDrwCtxFx extends TestDrwCtx {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiFx((TestFrameworkUiFxCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiFxCtx(boc);
   }

 

}
