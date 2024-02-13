package pasa.cbentley.framework.drawx.src4.engine.tests.fx;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsX;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugGuiFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestGraphicsXFx extends TestGraphicsX {


   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiFx((TestFrameworkUiFxCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiFxCtx(boc);
   }

   public void setupAbstractDrawX() {

   }
}
