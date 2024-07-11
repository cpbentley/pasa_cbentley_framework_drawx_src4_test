package pasa.cbentley.framework.drawx.src4.factories.tests.swing;

import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.swing.ConfigCoreDrawSwingTest;
import pasa.cbentley.framework.drawx.src4.factories.tests.TestFigureOperator;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestFigureOperatorSwing extends TestFigureOperator {

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   public IConfigCoreDraw getConfigCoreDraw() {
      return new ConfigCoreDrawSwingTest(uc);
   }
   
   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }
   
   public void testGradients_Triangle() {
      super.testGradients_Triangle();
   }
   
   public void testGetFigImage_Triangle() {
      super.testGetFigImage_Triangle();
   }
   
   public void testGetFigImage_Losange() {
      super.testGetFigImage_Losange();
   }
   
   public void testGetFigImage_TriangleAncors() {
      super.testGetFigImage_TriangleAncors();
   }
}
