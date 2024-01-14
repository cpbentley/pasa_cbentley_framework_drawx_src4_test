package pasa.cbentley.framework.drawx.src4.test.swing.factories;

import pasa.cbentley.framework.drawx.src4.factories.tests.TestRgbImageOperator;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestRgbImageOperatorSwing extends TestRgbImageOperator {

   private TestFrameworkUiSwingCtx tUiSwingCtx;

   public void setTestCtx(TestCtx tc) {
      tUiSwingCtx = (TestFrameworkUiSwingCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {

      ConfigPlugSwingDef configPlug = new ConfigPlugSwingDef(getC5());

      FrameworkPlugSwing plug = new FrameworkPlugSwing(configPlug, tUiSwingCtx, this);

      return plug;
   }

}