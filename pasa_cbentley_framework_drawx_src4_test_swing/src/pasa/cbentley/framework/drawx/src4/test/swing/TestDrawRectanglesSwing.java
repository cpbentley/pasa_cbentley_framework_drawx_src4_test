package pasa.cbentley.framework.drawx.src4.test.swing;

import pasa.cbentley.framework.drawx.src4.draw.tests.TestDrawRectangles;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestDrawRectanglesSwing extends TestDrawRectangles {

   private TestFrameworkUiSwingCtx tUiSwingCtx;

   public void setTestCtx(TestCtx tc) {
      tUiSwingCtx = (TestFrameworkUiSwingCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {
      return new FrameworkPlugSwing(tUiSwingCtx, this);
   }

}
