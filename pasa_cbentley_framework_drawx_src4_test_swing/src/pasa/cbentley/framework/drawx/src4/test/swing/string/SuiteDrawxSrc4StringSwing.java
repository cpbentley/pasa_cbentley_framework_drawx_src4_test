package pasa.cbentley.framework.drawx.src4.test.swing.string;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringMetrics;

public class SuiteDrawxSrc4StringSwing extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test String Swing");
      suite.addTestSuite(TestStringMetrics.class);
      suite.addTestSuite(TestStringDrawerSwing.class);
      suite.addTestSuite(TestStringerSwing.class);
      
      return suite;
   }
}
