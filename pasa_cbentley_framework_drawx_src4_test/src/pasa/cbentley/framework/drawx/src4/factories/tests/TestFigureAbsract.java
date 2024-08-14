package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigBorder;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigEllipse;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.layouter.src4.tech.IBOTblr;

public abstract class TestFigureAbsract extends TestCaseFrameworkUiPluggedDrawX implements IBOTblr, ITechGradient, IBOFigString, IBOFigBorder, IBOFigEllipse {

   FigureFactory  fac;

   protected int  h;

   FigureOperator op;

   protected int  w;

   protected int  x;

   protected int  y;

   public TestFigureAbsract() {
      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, true);
      
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK_ALL, false);

      x = 15;
      y = 15;
      w = 200;
      h = 150;
   }

   public void setupAbstractDrawX() {
      op = drc.getFigureOperator();
      fac = drc.getFigureFactory();
   }

   protected void testImageFigure(String msg, ByteObject figure) {
      int imgW = 2 * x + w;
      int imgH = 2 * y + h;
      RgbImage ri = rc.create(imgW, imgH, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      op.paintFigure(g, x, y, w, h, figure);

      doImageTest(ri, msg);
   }

}
