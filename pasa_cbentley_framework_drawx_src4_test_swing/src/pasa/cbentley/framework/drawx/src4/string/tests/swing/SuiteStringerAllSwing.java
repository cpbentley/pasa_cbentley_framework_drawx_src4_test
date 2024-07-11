package pasa.cbentley.framework.drawx.src4.string.tests.swing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteStringerAllSwing extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test Stringer  Swing");
      suite.addTestSuite(TestStringerSwing.class);
      suite.addTestSuite(TestStringerFxMaskSwing.class);
      suite.addTestSuite(TestStringerScaleSwing.class);
      suite.addTestSuite(TestStringerFxSwing.class);
      suite.addTestSuite(TestStringerTextFormatSwing.class);
      
      return suite;
   }
}
