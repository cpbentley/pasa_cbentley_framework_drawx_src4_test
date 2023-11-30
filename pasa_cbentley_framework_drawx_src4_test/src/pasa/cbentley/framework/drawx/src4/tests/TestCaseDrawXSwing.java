package pasa.cbentley.framework.drawx.src4.tests;

import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.IBOTypesDrw;
import pasa.cbentley.framework.drawx.src4.tech.ITechFigure;
import pasa.cbentley.framework.drawx.src4.tech.ITechGradient;
import pasa.cbentley.framework.drawx.src4.tech.ITechMask;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugUI;
import pasa.cbentley.framework.testing.gui.engine.IUiPlug;

public abstract class TestCaseDrawXSwing extends TestCaseDrawX implements IBOTypesDrw, ITechMask, IColors, ITechFont, ITechGradient, ITechFigure {

   public TestCaseDrawXSwing() {
   }

   private FrameworkPlugUI plug;

   public FrameworkPlugUI getUiPlug() {
      return plug;
   }

   public void setPlug(FrameworkPlugUI plug) {
      this.plug = plug;
   }

}
