package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOPassMosaic;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.ITechArtifact;
import pasa.cbentley.framework.drawx.src4.tech.ITechSkew;

public abstract class TestPassDrawOperator extends TestCaseFrameworkUiPluggedDrawX {

   public TestPassDrawOperator() {
      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);
   }

   
   public void testDrawSkewBox() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_02_VERT, 5, FULLY_OPAQUE_GREY);
      ByteObject rect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);

      addArtifact(rect);
      
      RgbImage ri = opFigure.getFigImageNonNull(rect, 60, 50);

      int interpolation = ITechSkew.SKEW_TYPE_2_BILINEAR_4SPLIT;
      int edgeType = ITechSkew.SKEW_EDGE_0_ZERO;

      ByteObject skewer = drc.getScalerFactory().getSkewer(interpolation, edgeType);

      int x = 5;
      int y = 5;
      int w = 500;
      int h = 400;
      int imgW = 2 * x + w;
      int imgH = 2 * y + h;
      
      RgbImage rip = rc.create(imgW, imgH, FULLY_OPAQUE_BLACK);
      GraphicsX g = rip.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      drc.getPassDrawOperator().drawSkewBox(g, ri, 5, 5, 500, 400, skewer);


      doImageTest(rip, "SkewBox1");
      
      
   }
   
   public void testDrawMosaic4() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_ELLIPSE_12_WATER_DROP_BOT, 1, FULLY_OPAQUE_GREY);
      ByteObject fig = facFigure.getFigEllipse(FULLY_OPAQUE_GREEN, grad);

      addArtifact(fig);
      
      RgbImage ri = opFigure.getFigImageNonNull(fig, 200, 150);


      ByteObject skewer = drc.getScalerFactory().getMosaic(IBOPassMosaic.PMOSAIC_TYPE_1_SQUARE4, true);

      int x = 5;
      int y = 5;
      int w = 500;
      int h = 400;
      int imgW = 2 * x + w;
      int imgH = 2 * y + h;
      
      RgbImage rip = rc.create(imgW, imgH, FULLY_OPAQUE_BLACK);
      GraphicsX g = rip.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      drc.getPassDrawOperator().drawMosaic(g, ri, 5, 5, skewer);


      doImageTest(rip, "Mosaic4_WaterDropBot");
      
      
   }


   private void addArtifact(ByteObject rect) {
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
   }
   
   public void testDrawMosaic9() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_TRIG_10_SWIPE, 2, FULLY_OPAQUE_GREY);
      ByteObject rect = facFigure.getFigTriangleTypeTop(FULLY_OPAQUE_GREEN, grad);

      addArtifact(rect);
      
      RgbImage ri = opFigure.getFigImageNonNull(rect, 200, 100);


      ByteObject skewer = drc.getScalerFactory().getMosaic(IBOPassMosaic.PMOSAIC_TYPE_2_SQUARE9, true);

      int x = 5;
      int y = 5;
      int w = 650;
      int h = 400;
      int imgW = 2 * x + w;
      int imgH = 2 * y + h;
      
      RgbImage rip = rc.create(imgW, imgH, FULLY_OPAQUE_BLACK);
      GraphicsX g = rip.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      drc.getPassDrawOperator().drawMosaic(g, ri, 5, 5, skewer);


      doImageTest(rip, "Mosaic9");
      
      
   }

}
