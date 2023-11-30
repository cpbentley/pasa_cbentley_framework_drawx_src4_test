package pasa.cbentley.framework.drawx.src4.draw.tests;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechDrawer;

/**
 * Configuration by default
 * @author Charles Bentley
 *
 */
public class ConfigCoreDrawTestAliasOn extends ConfigCoreDrawTest implements IConfigCoreDraw {

   public ConfigCoreDrawTestAliasOn(UCtx uc) {
      super(uc);
   }

   public int getAliasMode() {
      return ITechDrawer.MODSET_APP_ALIAS_1_ON;
   }

   public int getAliasModeText() {
      return ITechDrawer.MODSET_APP_ALIAS_1_ON;
   }
}
