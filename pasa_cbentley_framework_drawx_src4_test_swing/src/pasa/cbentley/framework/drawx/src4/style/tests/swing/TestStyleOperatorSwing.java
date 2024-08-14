package pasa.cbentley.framework.drawx.src4.style.tests.swing;

import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.swing.ConfigCoreDrawSwingTest;
import pasa.cbentley.framework.drawx.src4.factories.tests.TestFigureFactory;
import pasa.cbentley.framework.drawx.src4.style.tests.TestStyleOperator;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStyleOperatorSwing extends TestStyleOperator {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }

   public IConfigCoreDraw getConfigCoreDraw() {
      return new ConfigCoreDrawSwingTest(uc);
   }

   
   public void test4LayersGradients() {
      super.test4LayersGradients();
   }
   
   public void testBgAndFg() {
      super.testBgAndFg();
   }
   
   public void testIncomplete() {
      super.testIncomplete();
   }
   
   public void test4LayersForegrounds() {
      super.test4LayersForegrounds();
   }
}
