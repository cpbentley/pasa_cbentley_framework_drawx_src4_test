package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;

public class LogConfiguratorTestStringer implements ILogConfigurator {

   public void apply(IDLogConfig log) {
      log.setLevelGlobal(ITechLvl.LVL_03_FINEST);

      log.setFlagTag(FLAG_17_PRINT_TEST, true);
      log.setFlagTag(FLAG_08_PRINT_EXCEPTION, true);
      log.setFlagTag(FLAG_25_PRINT_NULL, true);
      log.setFlagTag(FLAG_20_PRINT_INIT, true);
      
   }

}
