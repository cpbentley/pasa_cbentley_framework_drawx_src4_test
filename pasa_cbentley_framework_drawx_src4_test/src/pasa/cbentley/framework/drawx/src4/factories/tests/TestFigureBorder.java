package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechBlend;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.core.src4.interfaces.ITechTransform;
import pasa.cbentley.core.src4.utils.interfaces.IColorsBase;

public abstract class TestFigureBorder extends TestFigureAbsract {

   private ByteObject[] getFigures8() {
      ByteObject gradRect = facGradient.getGradient(FULLY_OPAQUE_BLACK, 0, GRADIENT_TYPE_RECT_00_SQUARE);

      int colorGrad3 = FULLY_OPAQUE_PINK;

      ByteObject gradCoin1 = gradFac.getGradient(IColorsBase.BASE_04_BLUE, 100, ITechGradient.GRADIENT_TYPE_RECT_06_BOTRIGHT, colorGrad3);
      ByteObject rectCoin1 = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradCoin1);

      ByteObject gradCoin2 = gradFac.getGradient(IColorsBase.BASE_02_RED, 100, ITechGradient.GRADIENT_TYPE_RECT_05_BOTLEFT, colorGrad3);
      ByteObject rectCoin2 = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradCoin2);

      ByteObject gradCoin3 = gradFac.getGradient(IColorsBase.BASE_03_GREEN, 100, ITechGradient.GRADIENT_TYPE_RECT_04_TOPRIGHT, colorGrad3);
      ByteObject rectCoin3 = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradCoin3);

      ByteObject gradCoin4 = gradFac.getGradient(IColorsBase.BASE_05_YELLOW, 100, ITechGradient.GRADIENT_TYPE_RECT_03_TOPLEFT, colorGrad3);
      ByteObject rectCoin4 = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradCoin4);

      ByteObject rectTop = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradRect);
      ByteObject rectBot = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradRect);
      ByteObject rectLeft = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradRect);
      ByteObject rectRight = figureFac.getFigRect(FULLY_OPAQUE_GREY, gradRect);

      ByteObject[] figures = new ByteObject[] { rectTop, rectBot, rectLeft, rectRight, rectCoin1, rectCoin2, rectCoin3, rectCoin4 };
      return figures;
   }

   public void testBorderCornerShift10() {
      ByteObject border = fac.getFigBorder(20, FULLY_OPAQUE_GREEN);

      border.set1(FIG_BORDER_OFFSET_2_CORNER_SHIFT1, 10);

      testImageFigure("Border_CornerShift10_Gradient20", border);

   }

   public void testBorderCornerShift10WithCoins() {
      ByteObject border = fac.getFigBorder(20, FULLY_OPAQUE_GREEN);

      border.set1(FIG_BORDER_OFFSET_2_CORNER_SHIFT1, 10);

      border.setFlag(FIG_BORDER_OFFSET_1_FLAG, FIG_BORDER_FLAG_4_COIN, true);
      //we want a specific figure for coins

      testImageFigure("Border_CornerShift10_WithCoins", border);

   }

   public void testBorderCornerShift5_Gradient() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject border = fac.getFigBorder(20, FULLY_OPAQUE_GREEN, grad);

      border.set1(FIG_BORDER_OFFSET_2_CORNER_SHIFT1, 5);

      testImageFigure("Border20_CornerShift5_Gradient_Cursor0", border);

      grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_00_SQUARE);
      border = fac.getFigBorder(30, FULLY_OPAQUE_PURPLE, grad);

      border.set1(FIG_BORDER_OFFSET_2_CORNER_SHIFT1, 10);

      testImageFigure("Border30_CornerShift10_Gradient_Cursor50", border);

   }

   public void testBorderFigures8() {
      ByteObject[] figures = getFigures8();

      ByteObject tblr = facTBLR.getTBLRPixel(20, 40, 30, 10);

      ByteObject border = fac.getFigBorder(tblr, figures, false);

      testImageFigure("Border_Figures8", border);

   }

   public void testBorderFigures8_Coinshift10() {
      ByteObject[] figures = getFigures8();

      ByteObject tblr = facTBLR.getTBLRPixel(20, 40, 30, 10);

      ByteObject border = fac.getFigBorder(tblr, figures, false);
      border.set1(FIG_BORDER_OFFSET_2_CORNER_SHIFT1, 10);

      testImageFigure("Border_Figures8_Coinshift10", border);

   }

   public void testBorderFilledGradient15RoundBorders() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_ORANGE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREY, 45, 45, grad);

      ByteObject border = fac.getFigBorderFilled(20, rect, false);

      testImageFigure("Border_Filled20_Round5-10_Cursor0_0Square", border);

      grad = facGradient.getGradient(FULLY_OPAQUE_ORANGE, 50, GRADIENT_TYPE_RECT_24_WIN_BOT);
      rect = fac.getFigRect(FULLY_OPAQUE_GREY, 5, 10, grad);

      border = fac.getFigBorderFilled(20, rect, false);

      testImageFigure("Border_Filled20_Round5-10_Cursor50_24WinBot", border);

      //we want a background filler
   }

   public void testBorderFilledGradient15() {

      int gradType = GRADIENT_TYPE_RECT_00_SQUARE;

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, gradType);
      ByteObject border = fac.getFigBorderFilled(15, FULLY_OPAQUE_GREY, grad, false);

      testImageFigure("Border_FilledGradient15_Cursor0", border);

      grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, gradType);
      border = fac.getFigBorderFilled(15, FULLY_OPAQUE_GREY, grad, false);

      testImageFigure("Border_FilledGradient15_Cursor50", border);

      grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 100, gradType);
      border = fac.getFigBorderFilled(15, FULLY_OPAQUE_GREY, grad, false);

      testImageFigure("Border_FilledGradient15_Cursor100", border);

      grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 90, gradType);
      border = fac.getFigBorderFilled(15, FULLY_OPAQUE_GREY, grad, false);

      testImageFigure("Border_FilledGradient15_Cursor90", border);

   }

   public void testBorderGradient() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject border = fac.getFigBorder(20, FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Border_Gradient20", border);

   }

   public void testBorderGradient_Filter() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject border = fac.getFigBorder(30, FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Border30_Gradient_Cursor0_Filter_None", border);

      ByteObject gradRect = facGradient.getGradient(FULLY_OPAQUE_ORANGE, 50, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figRect = fac.getFigRect(FULLY_OPAQUE_GREY, gradRect);

      ByteObject filter = facFilter.getFilterBlenderExternal(ITechBlend.BLENDING_19_SCREEN_DODGE, ITechBlend.ALPHA_0_OVER, figRect);
      op.setFilter(border, filter);

      testImageFigure("Border30_Gradient_Cursor0_Filter_ScreenDodge", border);

      filter = facFilter.getFilterBlenderExternal(ITechBlend.BLENDING_16_MULTIPLY_BURN, ITechBlend.ALPHA_0_OVER, figRect);
      op.setFilter(border, filter);
      testImageFigure("Border30_Gradient_Cursor0_Filter_MultiplyBurn", border);
      
      filter = facFilter.getFilterBlenderExternal(ITechBlend.BLENDING_17_COLOR_BURN, ITechBlend.ALPHA_0_OVER, figRect);
      op.setFilter(border, filter);
      testImageFigure("Border30_Gradient_Cursor0_Filter_ColorBurn", border);
      
      filter = facFilter.getFilterBlenderExternal(ITechBlend.BLENDING_18_LINEAR_BURN, ITechBlend.ALPHA_0_OVER, figRect);
      op.setFilter(border, filter);
      testImageFigure("Border30_Gradient_Cursor0_Filter_LinearBurn", border);
      
      filter = facFilter.getFilterBlenderExternal(ITechBlend.BLENDING_24_DIFFERENCE, ITechBlend.ALPHA_0_OVER, figRect);
      op.setFilter(border, filter);
      testImageFigure("Border30_Gradient_Cursor0_Filter_Difference", border);
   }

   public void testBorderGradientTBLR() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);

      ByteObject tblr = facTBLR.getTBLRPixel(20, 35, 25, 10);
      boolean isRectFigs = true;
      int dimMaster = DIM_MASTER_0_NONE;
      boolean isDrawingCoins = true;
      ByteObject border = fac.getFigBorder(tblr, rect, false, isRectFigs, isDrawingCoins, dimMaster);

      testImageFigure("Border_GradientTBLR", border);

   }

   public void testBorderGrey10() {
      ByteObject border = fac.getFigBorder(10, FULLY_OPAQUE_GREY);

      testImageFigure("Border_Grey10", border);

   }

   public void testBorderGrey10Outer() {
      ByteObject border = fac.getFigBorder(15, FULLY_OPAQUE_GREY, true);

      testImageFigure("Border_Grey10Outer", border);

   }
}
