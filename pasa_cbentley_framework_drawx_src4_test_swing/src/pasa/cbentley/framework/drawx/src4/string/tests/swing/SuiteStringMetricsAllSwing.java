package pasa.cbentley.framework.drawx.src4.string.tests.swing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteStringMetricsAllSwing extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test StringMetrics  Swing");
      suite.addTestSuite(TestStringMetricsSwing.class);
      suite.addTestSuite(TestStringMetricsCharWidthsSwing.class);
      suite.addTestSuite(TestStringMetricsEditorSwing.class);
      suite.addTestSuite(TestStringMetricsSpecialsSwing.class);
      
      return suite;
   }
}
