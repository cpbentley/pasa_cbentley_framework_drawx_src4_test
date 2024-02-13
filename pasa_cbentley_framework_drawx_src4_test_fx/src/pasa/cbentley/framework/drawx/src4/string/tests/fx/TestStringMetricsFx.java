package pasa.cbentley.framework.drawx.src4.string.tests.fx;

import pasa.cbentley.framework.drawx.src4.string.tests.TestStringMetrics;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugGuiFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStringMetricsFx extends TestStringMetrics {

   
   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiFx((TestFrameworkUiFxCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiFxCtx(boc);
   }

}
