package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.ToStringStaticBO;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigTriangle;
import pasa.cbentley.layouter.src4.tech.IBOTblr;

public abstract class TestFigureOperatorGradientRect extends TestCaseFrameworkUiPluggedDrawX implements IBOTblr, ITechGradient {

   FigureFactory  fac;

   FigureOperator op;

   public TestFigureOperatorGradientRect() {

      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, true);

      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK_ALL, false);
   }

   public void setupAbstractDrawX() {
      op = drc.getFigureOperator();
      fac = drc.getFigureFactory();
   }

   public void testGradient_0_SQUARE() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_1_HORIZ() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_2_VERT() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_02_VERT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_3_TOPLEFT() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_03_TOPLEFT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_4_TOPRIGHT() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_04_TOPRIGHT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_7_LTOP() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_07_L_TOP;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_20_LTOP() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_22_PIC_MID_LEFT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }

   public void testGradient_11_LTOP() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_13_L_THIN_LEFT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }
   
   public void testGradient_17_() {
      int w = 120;
      int h = 80;
      int gradient = ITechGradient.GRADIENT_TYPE_RECT_19_PIC_BOT_RIGHT;
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradient, FULLY_OPAQUE_RED);
      ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
      RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
      String str = ToStringStaticBO.toStringGradRect(gradient);
      doImageTest(img, "testGradient_0_" + str);
   }
}
