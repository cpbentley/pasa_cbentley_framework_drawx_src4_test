package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFeaturesDraw;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.MaskFactory;
import pasa.cbentley.framework.drawx.src4.factories.MaskOperator;
import pasa.cbentley.framework.drawx.src4.tech.ITechGradient;
import pasa.cbentley.framework.drawx.src4.tech.ITechTblr;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

public abstract class TestMaskOperator extends TestCaseDrawXPlugged implements ITechTblr {

   MaskFactory  fac;

   MaskOperator op;

   public void setupAbstractDrawX() {
      op = drc.getMaskOperator();
      fac = drc.getMaskFactory();
   }

   public void testCreateMaskedFigure() {
      int w = 100;
      int h = 80;

      ByteObject mask = fac.getMaskGradient(FULLY_OPAQUE_BLACK, FULLY_OPAQUE_GREEN);
      ByteObject figureEllipse = facFigure.getEllipse(FULLY_OPAQUE_WHITE);

      RgbImage img = op.createMaskedFigure(mask, w, h, figureEllipse);

      doImageTest(img, "MaskedEllipse_GradientBlackGreen");

   }

   public void testCreateMaskedFigure_Fig() {
      int w = 100;
      int h = 80;

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, FULLY_OPAQUE_RED);
      //the figure
      ByteObject figMaskBgRect = facFigure.getRect(FULLY_OPAQUE_WHITE, grad);
      ByteObject filter = facFilter.getFilterSmoothStep();
      ByteObject mask = fac.getMask(figMaskBgRect, filter);

      //#debug
      toDLog().pTest("", mask, TestMaskOperator.class, "testCreateMaskedFigure_Fig", LVL_05_FINE, false);

      ByteObject figMaskEllipse = facFigure.getEllipse(FULLY_OPAQUE_BLACK);
      RgbImage img = op.createMaskedFigure(mask, w, h, figMaskEllipse);

      doImageTest(img, "MaskedFigure_Fig");

      //create a whole new context for this?
      boolean featureEnable = cdc.featureEnable(ITechFeaturesDraw.SUP_ID_04_ALIAS, true);
      assertEquals(featureEnable, true);
      img = op.createMaskedFigure(mask, w, h, figMaskEllipse);

      doImageTest(img, "MaskedFigure_Fig_AliasOn");

   }

   public void testDrawMasked_Char() {
      int w = 50;
      int h = 30;
      RgbImage ri = rc.create(w, h, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      
      ByteObject mask = fac.getMaskGradient(FULLY_OPAQUE_PINK, FULLY_OPAQUE_GREEN);
      
      op.drawMask(g, 0, 0, mask, 'W', drc.getFontFactory().getDefaultFontMono());

      doImageTest(ri, "DrawMasked_Char");
   }

   public void testDrawMasked_String() {

      int w = 140;
      int h = 40;
      RgbImage ri = rc.create(w, h, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      ByteObject mask = fac.getMaskGradient(FULLY_OPAQUE_PINK, FULLY_OPAQUE_GREEN);
      op.drawMask(g, 0, 0, mask, "Hello World", drc.getFontFactory().getDefaultFontMono());

      doImageTest(ri, "DrawMasked_String");

   }

   public void testShapeMask() {
      int w = 100;
      int h = 80;

      ByteObject mask = fac.getMaskGradient(FULLY_OPAQUE_RED, FULLY_OPAQUE_GREEN);
      ByteObject figureEllipse = facFigure.getEllipse(FULLY_OPAQUE_BLUE);

      RgbImage img = op.createShapeMask(mask, w, h, figureEllipse);

      doImageTest(img, "ShapeMask_Ellipse_Full");

      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getLeftTop());
      doImageTest(img, "ShapeMask_Ellipse_50percent_LeftTop");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getLeftCenter());
      doImageTest(img, "ShapeMask_Ellipse_50percent_LeftCenter");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getLeftBottom());
      doImageTest(img, "ShapeMask_Ellipse_50percent_LeftBot");

      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getCenterTop());
      doImageTest(img, "ShapeMask_Ellipse_50percent_CenterTop");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getCenterCenter());
      doImageTest(img, "ShapeMask_Ellipse_50percent_CenterCenter");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getCenterBottom());
      doImageTest(img, "ShapeMask_Ellipse_50percent_CenterBottom");

      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getRightTop());
      doImageTest(img, "ShapeMask_Ellipse_50percent_RightTop");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getRightCenter());
      doImageTest(img, "ShapeMask_Ellipse_50percent_RightCenter");
      img = op.createShapeMask(mask, w, h, figureEllipse, 50, facAnchor.getRightBottom());
      doImageTest(img, "ShapeMask_Ellipse_50percent_RightBot");
   }

   public void testShapeMask_Filter() {
      int w = 100;
      int h = 80;
      boolean or48 = false;
      int start = 255;
      int touchMod = 5;
      ByteObject fct = facFun.getFunctionTouchXY(start, touchMod);
      int touchColor = IColors.FULLY_OPAQUE_BLACK;
      ByteObject filter = facFilter.getFilterTouch(touchColor, or48, fct);
      ByteObject figureEllipse = facFigure.getEllipse(FULLY_OPAQUE_CYAN); //color here is irrelevant
      ByteObject mask = fac.getMask(figureEllipse, filter);

      RgbImage img = op.createShapeMask(mask, w, h, figureEllipse);

      doImageTest(img, "ShapeMask_FilterTouchBlack");

      touchColor = IColors.FULLY_OPAQUE_WHITE;
      filter = facFilter.getFilterTouch(touchColor, or48, fct);
      figureEllipse = facFigure.getEllipse(FULLY_OPAQUE_CYAN);
      mask = fac.getMask(figureEllipse, filter);

      img = op.createShapeMask(mask, w, h, figureEllipse);

      doImageTest(img, "ShapeMask_FilterTouchWhite");
   }
}
