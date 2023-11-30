package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.ToStringStaticDrawx;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.tech.ITechGradient;
import pasa.cbentley.framework.drawx.src4.tech.ITechTblr;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

public abstract class TestFigureOperator extends TestCaseDrawXPlugged implements ITechTblr, ITechGradient {

   FigureOperator op;

   FigureFactory  fac;

   public void testGetFigImage_Ellipse() {
      int w = 80;
      int h = 50;

      ByteObject figureEllipse = fac.getEllipse(FULLY_OPAQUE_ORANGE);

      boolean whiteopaque = true;
      boolean justSwitch = false;
      RgbImage img = op.getFigImage(figureEllipse, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);

      doImageTest(img, "GetFigImage_Ellipse");
   }

   public void testGradients_Rectangle() {
      int w = 120;
      int h = 80;
      int max = GRADIENT_TYPE_RECT_MAX_MODULO;
      for (int i = 0; i < max; i++) {
         ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, i, FULLY_OPAQUE_RED);
         ByteObject figRect = facFigure.getFigRect(FULLY_OPAQUE_GREEN, grad);
         RgbImage img = opFigure.getFigImageTrans(figRect, w, h);
         String str = ToStringStaticDrawx.gradRect(i);
         String prettyI = uc.getStrU().prettyInt0Padd(i, 2);
         doImageTest(img, "Gradients_Rectangle_" + prettyI + "_" + str);
      }
   }

   public void testGradients_Losange() {
      int w = 120;
      int h = 80;
      int max = GRADIENT_TYPE_LOSANGE_MAX_MODULO;
      int overstep = 0;
      boolean pap = false;
      boolean horiz = true;
      boolean countour = false;
      for (int i = 0; i < max; i++) {
         ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, i, FULLY_OPAQUE_RED);
         ByteObject figLosange = facFigure.getFigLosange(FULLY_OPAQUE_GREEN, overstep, horiz, pap, countour, grad);
         RgbImage img = opFigure.getFigImageTrans(figLosange, w, h);
         String str = ToStringStaticDrawx.gradLosange(i);
         String prettyI = uc.getStrU().prettyInt0Padd(i, 2);
         doImageTest(img, "Gradients_LosangeHoriz_" + prettyI + "_" + str);
      }
   }

   public void testGradients_LosangeTriangle() {
      int w = 120;
      int h = 80;
      boolean pap = false;
      boolean horiz = true;
      boolean countour = false;
      int max = GRADIENT_TYPE_TRIG_MAX_MODULO;
      int type = C.TYPE_00_TOP;
      for (int i = 0; i < max; i++) {
         ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, i, FULLY_OPAQUE_RED);
         ByteObject figTrig = facFigure.getFigTriangleType(FULLY_OPAQUE_GREEN, type, grad);
         ByteObject figLosange = facFigure.getFigLosange(figTrig, pap, countour);
         
         RgbImage img = opFigure.getFigImageTrans(figLosange, w, h);

         String str = ToStringStaticDrawx.gradTrig(i);
         String prettyI = uc.getStrU().prettyInt0Padd(i, 2);
         doImageTest(img, "Gradients_Losange_Triangle_" + prettyI + "_" + str);
      }
   }

   public void testGradients_Triangle() {
      int w = 120;
      int h = 80;

      int max = GRADIENT_TYPE_TRIG_MAX_MODULO;
      int type = C.TYPE_00_TOP;
      for (int i = 0; i < max; i++) {
         ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, i, FULLY_OPAQUE_RED);
         ByteObject figTrig = facFigure.getFigTriangleType(FULLY_OPAQUE_GREEN, type, grad);
         RgbImage img = opFigure.getFigImageTrans(figTrig, w, h);

         String str = ToStringStaticDrawx.gradTrig(i);

         String prettyI = uc.getStrU().prettyInt0Padd(i, 2);
         doImageTest(img, "Gradients_Triangle_" + prettyI + "_" + str);
      }
   }

   public void testGradients_Ellipse() {
      int w = 120;
      int h = 80;

      int type = ITechGradient.GRADIENT_TYPE_ELLIPSE_11_WATER_DROP_TOP;
      int max = GRADIENT_TYPE_ELLIPSE_MAX_MODULO;
      for (int i = 0; i < max; i++) {
         ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, i, FULLY_OPAQUE_RED);
         ByteObject figureEllipse = facFigure.getFigEllipse(FULLY_OPAQUE_GREEN, grad);
         RgbImage img = opFigure.getFigImageTrans(figureEllipse, w, h);

         String str = ToStringStaticDrawx.gradEllipse(i);

         String prettyI = uc.getStrU().prettyInt0Padd(i, 2);
         doImageTest(img, "Gradients_Ellipse_" + prettyI + "_" + str);
      }

   }

   public void testGetFigImage_String() {
      int color = FULLY_OPAQUE_ORANGE;

      ByteObject figureStr = fac.getFigString("Hello", ITechFont.FACE_MONOSPACE, ITechFont.SIZE_3_MEDIUM, ITechFont.STYLE_BOLD, color);

      RgbImage img = op.getFigImageTrans(figureStr, 100, 30);

      doImageTest(img, "GetFigImage_String_Hello");
   }

   public void testGetFigImage_Ellipse_Trans() {
      int w = 80;
      int h = 50;

      ByteObject figureEllipse = fac.getEllipse(FULLY_OPAQUE_ORANGE);
      RgbImage img = op.getFigImageTrans(figureEllipse, w, h);

      doImageTest(img, "GetFigImage_EllipseTrans");
   }

   public void testGetFigImage_Borders() {
      int w = 80;
      int h = 50;

      ByteObject figureBorder = fac.getFigBorder(10, FULLY_OPAQUE_ORANGE);

      boolean whiteopaque = true;
      boolean justSwitch = false;
      RgbImage img = op.getFigImage(figureBorder, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_Border");

      img = op.getFigImageTrans(figureBorder, w, h);
      doImageTest(img, "GetFigImage_BorderTrans");
   }

   public void testGetFigImage_Losange() {
      int w = 80;
      int h = 50;

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 50, ITechGradient.GRADIENT_TYPE_TRIG_00_TENT);
      ByteObject figTriangle = fac.getFigTriangle(FULLY_OPAQUE_ORANGE, C.ANGLE_UP_90, 5, grad);
      ByteObject figureLosange = fac.getFigLosange(figTriangle, false, false);

      boolean whiteopaque = true;
      boolean justSwitch = false;
      RgbImage img = op.getFigImage(figureLosange, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_Losange");

      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans");

      int color = FULLY_OPAQUE_ORANGE;

      int overstep = 0;
      boolean horiz = true;
      boolean pap = false;
      boolean contour = false;
      grad = null;
      figureLosange = fac.getFigLosange(color, overstep, horiz, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicHoriz");

      grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 50);
      figureLosange = fac.getFigLosange(color, overstep, horiz, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicHorizGrad");
      grad = null;

      figureLosange = fac.getFigLosange(color, overstep, false, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicVert");

      pap = true;
      figureLosange = fac.getFigLosange(color, overstep, horiz, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicPap");

      figureLosange = fac.getFigLosange(color, 5, false, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicPapVert5");
      pap = false;
      contour = true;
      figureLosange = fac.getFigLosange(color, overstep, horiz, pap, contour, grad);
      img = op.getFigImageTrans(figureLosange, w, h);
      doImageTest(img, "GetFigImage_LosangeTrans_BasicContour");
   }

   public void testGetFigImage_Trefle() {
      int w = 50;
      int h = 50;
      ByteObject figTrefle = fac.getFigCardTrefle(FULLY_OPAQUE_ORANGE);
      RgbImage img = op.getFigImageTrans(figTrefle, w, h);
      doImageTest(img, "GetFigImage_Trefle");
   }

   public void testGetFigImage_Triangle() {
      int w = 80;
      int h = 50;

      ByteObject figTriangleUp = fac.getFigTriangle(FULLY_OPAQUE_ORANGE, C.ANGLE_UP_90);
      boolean whiteopaque = true;
      boolean justSwitch = false;
      RgbImage img = op.getFigImage(figTriangleUp, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_TriangleUp");

      ByteObject figTriangleDown = fac.getFigTriangle(FULLY_OPAQUE_ORANGE, C.ANGLE_DOWN_270);
      img = op.getFigImage(figTriangleDown, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_TriangleDown");

      ByteObject figTriangleRight = fac.getFigTriangle(FULLY_OPAQUE_ORANGE, C.ANGLE_RIGHT_0);
      img = op.getFigImage(figTriangleRight, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_TriangleRight");

      ByteObject figTriangleLeft = fac.getFigTriangle(FULLY_OPAQUE_ORANGE, C.ANGLE_LEFT_180);
      img = op.getFigImage(figTriangleLeft, w, h, justSwitch, whiteopaque, FR_BLEU_Celeste);
      doImageTest(img, "GetFigImage_TriangleLeft");
   }

   public void setupAbstractDrawX() {
      op = drc.getFigureOperator();
      fac = drc.getFigureFactory();
   }

}
