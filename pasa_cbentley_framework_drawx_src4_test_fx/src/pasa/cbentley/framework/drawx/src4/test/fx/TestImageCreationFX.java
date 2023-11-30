package pasa.cbentley.framework.drawx.src4.test.fx;

import pasa.cbentley.framework.drawx.src4.engine.tests.TestImageCreation;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.fx.ConfigPlugFxDef;
import pasa.cbentley.framework.testing.gui.fx.FrameworkPlugFx;
import pasa.cbentley.framework.testing.gui.fx.ctx.TestFrameworkUiFxCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestImageCreationFX extends TestImageCreation {

   protected TestFrameworkUiFxCtx tUiFxCtx;


   public void setTestCtx(TestCtx tc) {
      tUiFxCtx = (TestFrameworkUiFxCtx) tc;
      super.setTestCtx(tc);
   }

   public FrameworkPlugUI getUiPlug() {
      ConfigPlugFxDef config = new ConfigPlugFxDef(c5);
      FrameworkPlugFx plug = new FrameworkPlugFx(config,tUiFxCtx, this);
      return plug;
   }

   public void setupAbstractDrawX() {
      
   }


}
