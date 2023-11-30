package pasa.cbentley.framework.drawx.src4.test.swing.string;

import pasa.cbentley.framework.drawx.src4.string.tests.TestStringer;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStringerSwing extends TestStringer {

   private TestFrameworkUiSwingCtx tUiSwingCtx;

   public void setTestCtx(TestCtx tc) {
      tUiSwingCtx = (TestFrameworkUiSwingCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {

      ConfigPlugSwingDef configPlug = new ConfigPlugSwingDef(getC5());

      FrameworkPlugSwing plug = new FrameworkPlugSwing(configPlug, tUiSwingCtx, this);

      //#debug
      toDLog().pTest("", plug, TestStringerSwing.class, "getUiPlug", LVL_05_FINE, false);

      //#debug
      toDLog().pTest("", tUiSwingCtx, TestStringerSwing.class, "getUiPlug", LVL_05_FINE, false);

      return plug;
   }

   public void setupAbstractDrawX() {
      super.setupAbstractDrawX();

   }
   public void testMultiLines_Natural1Line() {
      super.testMultiLines_Natural1Line();
   }
   public void testMultiLines_IvyGreen() {
      super.testMultiLines_IvyGreen();
   }
   
   public void testMultiLines_Natural() {
      super.testMultiLines_Natural();
   }
   public void testFormatWidth30() {
      super.testFormatWidth30();
   }
   public void testBreak1Width() {
      super.testBreak1Width();
   }

   public void testBreakWidthBigSingleLine() {
      super.testBreakWidthBigSingleLine();
   }

   public void testMonospace() {
      super.testMonospace();
   }
}
