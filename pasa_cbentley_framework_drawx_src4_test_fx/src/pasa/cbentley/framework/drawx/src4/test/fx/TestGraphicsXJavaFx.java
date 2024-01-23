package pasa.cbentley.framework.drawx.src4.test.fx;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsX;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.fx.ConfigPlugFxDef;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestGraphicsXJavaFx extends TestGraphicsX {


   protected TestCtx createTestCtx() {
      initCtxBeforeTestFramworkCtxCreation();
      return new TestFrameworkUiFxCtx(boc);
   }
   
   public FrameworkPlugUI getUiPlug() {
      ConfigPlugFxDef config = new ConfigPlugFxDef(c5);
      FrameworkPlugFx plug = new FrameworkPlugFx(config, (TestFrameworkUiFxCtx) tfuc, this);
      return plug;
   }

   public void setupAbstractDrawX() {

   }
}
