package pasa.cbentley.framework.drawx.src4.test.swing;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsXTransformations;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestGraphicsXTransformationsSwing extends TestGraphicsXTransformations {

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(uc);
   }

   public FrameworkPlugUI getUiPlug() {
      ConfigPlugSwingDef configPlug = new ConfigPlugSwingDef(getC5());
      FrameworkPlugSwing plug = new FrameworkPlugSwing(configPlug, (TestFrameworkUiSwingCtx) tfuc, this);
      return plug;
   }

   public void setupAbstractDrawX() {
   }

}
