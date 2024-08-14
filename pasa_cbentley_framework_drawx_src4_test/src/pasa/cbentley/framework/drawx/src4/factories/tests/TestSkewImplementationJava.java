package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.SkewImplementationJava;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.ITechArtifact;
import pasa.cbentley.framework.drawx.src4.tech.ITechSkew;

public abstract class TestSkewImplementationJava extends TestCaseFrameworkUiPluggedDrawX {

   public TestSkewImplementationJava() {
      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);
   }

   public void testOne() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_02_VERT, 5, FULLY_OPAQUE_GREY);
      ByteObject rect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);

      int type = 1;
      int aw = 5;
      int ah = 6;
      int spac = 4;
      boolean rw = false;
      boolean rh = false;
      boolean rs = false;
      boolean rf = false;
      int figType = ITechArtifact.ART_FIG_0_RECT;
      long seed = 444;
      ByteObject artifact = facArtifact.getArtifact(type, aw, ah, spac, figType, rw, rh, rs, rf, seed);
      opFigure.addArtifact(rect, artifact);
      
      RgbImage ri = opFigure.getFigImageNonNull(rect, 60, 50);

      int interpolation = ITechSkew.SKEW_TYPE_2_BILINEAR_4SPLIT;
      int edgeType = ITechSkew.SKEW_EDGE_0_ZERO;

      ByteObject skewer = drc.getScalerFactory().getSkewer(interpolation, edgeType);

      SkewImplementationJava skew = new SkewImplementationJava(drc, ri, skewer);
      
      int imgW = ri.getWidth();
      int imgH = ri.getHeight();
      int x0 = 5;
      int y0 = 5;

      //uppwer right
      int x1 = imgW + 5;
      int y1 =5;

      //lower right
      int x2 = imgW + 5;
      int y2 = imgH + 5;

      //lower left
      int x3 = 5;
      int y3 = imgH +5;

      RgbImage riSkewed = skew.setCorners(x0, y0, x1, y1, x2, y2, x3, y3);

      doImageTest(riSkewed, "SkewedRectangleGradArti");
   }

}
