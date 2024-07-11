package pasa.cbentley.framework.drawx.src4.string.tests.swing;

import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.swing.ConfigCoreDrawSwingTest;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringerFxMask;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStringerFxMaskSwing extends TestStringerFxMask {

   public TestStringerFxMaskSwing() {
      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, false);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, false);
      setTestFlag(TEST_FLAG_05_SHOW_OUT_INIT, false);
      setTestFlag(TEST_FLAG_08_DEBUG_METHOD_NAMES, false);

      //
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK_ALL, false);
      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);

   }

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }

   public IConfigCoreDraw getConfigCoreDraw() {
      return new ConfigCoreDrawSwingTest(uc);
   }

   public void testMaskInFigure() {
      super.testMaskInFigure();
   }

   public void testMaskScope_2_Word() {
      super.testMaskScope_2_Word();
   }

}
