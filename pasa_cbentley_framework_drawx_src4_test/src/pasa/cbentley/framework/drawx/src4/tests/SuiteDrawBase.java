package pasa.cbentley.framework.drawx.src4.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.framework.drawx.src4.color.tests.TestColorFunction;
import pasa.cbentley.framework.drawx.src4.color.tests.TestGradientFunction;
import pasa.cbentley.framework.drawx.src4.draw.tests.TestDrawRectangles;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestGraphicsXTransformations;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbCache;
import pasa.cbentley.framework.drawx.src4.engine.tests.TestRgbImage;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringer;
import pasa.cbentley.framework.drawx.src4.tech.tests.TestITechFigure;

public class SuiteDrawBase extends TestSuite {

   public static Test suite() {

      TestSuite suite = new TestSuite("Test for All Base Draw Classes");
      suite.addTestSuite(TestRgbCache.class);
      suite.addTestSuite(TestStringer.class);
      suite.addTestSuite(TestRgbImage.class);
      suite.addTestSuite(TestGraphicsXTransformations.class);
      
      suite.addTestSuite(TestGradientFunction.class);
      suite.addTestSuite(TestDrawRectangles.class);
      suite.addTestSuite(TestColorFunction.class);
      suite.addTestSuite(TestITechFigure.class);
      
      return suite;
   }
}
