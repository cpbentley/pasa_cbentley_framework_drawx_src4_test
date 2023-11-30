package pasa.cbentley.framework.drawx.src4.test.swing;

import pasa.cbentley.framework.drawx.src4.draw.tests.ConfigCoreDrawTestAliasOff;
import pasa.cbentley.framework.drawx.src4.draw.tests.TestDrawEllipse;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

/**
 * How to do a different configuration
 * @author Charles Bentley
 *
 */
public class TestDrawEllipseSwingAliasOff extends TestDrawEllipse {

   private TestFrameworkUiSwingCtx tUiSwingCtx;

   public void setTestCtx(TestCtx tc) {
      tUiSwingCtx = (TestFrameworkUiSwingCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {
      ConfigPlugSwingDef configPlug = new ConfigPlugSwingDef(getC5());

      ConfigCoreDrawTestAliasOff configDraw = new ConfigCoreDrawTestAliasOff(uc);
      configPlug.setConfigCoreDraw(configDraw);

      FrameworkPlugSwing plug = new FrameworkPlugSwing(configPlug, tUiSwingCtx, this);
      return plug;
   }

   public String getSuffix() {
      return "AliasOff";
   }
}
