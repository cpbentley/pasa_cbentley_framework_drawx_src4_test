package pasa.cbentley.framework.drawx.src4.test.swing.string;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.framework.drawx.src4.color.tests.TestColorFunction;
import pasa.cbentley.framework.drawx.src4.color.tests.TestGradientFunction;
import pasa.cbentley.framework.drawx.src4.draw.tests.TestDrawRectangles;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsXTransformations;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbCache;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbImage;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringMetrics;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringer;
import pasa.cbentley.framework.drawx.src4.tech.tests.TestITechFigure;

public class SuiteStringSwing extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test String Swing");
      suite.addTestSuite(TestStringMetrics.class);
      suite.addTestSuite(TestStringDrawerSwing.class);
      suite.addTestSuite(TestStringerSwing.class);
      
      return suite;
   }
}
