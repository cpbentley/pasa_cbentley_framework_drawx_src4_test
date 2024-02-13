package pasa.cbentley.framework.drawx.src4.ctx.tests.swing;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestDrwCtx;
import pasa.cbentley.framework.drawx.src4.engine.tests.swing.SuiteDrawxSrc4SwingEngine;
import pasa.cbentley.framework.drawx.src4.factories.tests.swing.SuiteDrawxSrc4SwingFactories;
import pasa.cbentley.framework.drawx.src4.string.tests.swing.SuiteDrawxSrc4StringSwing;

public class SuiteAllDrawxSrc4Swing extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test for All Base Draw Classes");
      suite.addTestSuite(TestDrwCtx.class);
      suite.addTest(SuiteDrawxSrc4SwingEngine.suite());
      suite.addTest(SuiteDrawxSrc4SwingFactories.suite());
      suite.addTest(SuiteDrawxSrc4StringSwing.suite());
            
      return suite;
   }
}
