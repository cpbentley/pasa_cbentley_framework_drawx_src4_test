package pasa.cbentley.framework.drawx.src4.test.swing.factories;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteDrawxSrc4SwingFactories extends TestSuite {

   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests Factory Draw Classes");
      
      suite.addTestSuite(TestFigureOperatorSwing.class);
      suite.addTestSuite(TestFxStringOperatorSwing.class);
      suite.addTestSuite(TestMaskOperatorSwing.class);
      suite.addTestSuite(TestRgbImageOperatorSwing.class);
      suite.addTestSuite(TestScaleOperatorSwing.class);
      
      return suite;
   }
}
