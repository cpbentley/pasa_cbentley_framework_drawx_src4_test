package pasa.cbentley.framework.drawx.src4.string.tests.swing;

import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.swing.ConfigCoreDrawSwingTest;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringMetrics;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringer;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStringMetricsSwing extends TestStringMetrics {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   public IConfigCoreDraw getConfigCoreDraw() {
      return new ConfigCoreDrawSwingTest(uc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }

}
