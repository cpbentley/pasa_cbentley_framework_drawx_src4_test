package pasa.cbentley.framework.drawx.src4.engine.tests.swing;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteDrawxSrc4SwingEngine extends TestSuite {

   
   public static Test suite() {

      TestSuite suite = new TestSuite("Tests Engine Draw Classes");
      
      suite.addTestSuite(TestGraphicsXSwing.class);
      suite.addTestSuite(TestGraphicsXTransformationsSwing.class);
      suite.addTestSuite(TestImageCreationSwing.class);
      suite.addTestSuite(TestRgbCacheSwing.class);
      suite.addTestSuite(TestRgbImageSwing.class);
      
      return suite;
   }
}
