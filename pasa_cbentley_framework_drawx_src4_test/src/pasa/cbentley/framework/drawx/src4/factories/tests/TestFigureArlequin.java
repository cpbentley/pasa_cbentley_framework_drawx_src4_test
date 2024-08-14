package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigArlequin;

public abstract class TestFigureArlequin extends TestFigureAbsract {

   public void testArlequin_GreenGrey() {
      ByteObject ar = fac.getFigArlequin(FULLY_OPAQUE_GREEN, FULLY_OPAQUE_GREY);

      testImageFigure("Arlequin_GreenGrey", ar);

   }

   public void testArlequin_GreenGrey_Size10() {
      ByteObject ar = fac.getFigArlequin(FULLY_OPAQUE_GREEN, FULLY_OPAQUE_GREY, 10);

      testImageFigure("Arlequin_GreenGrey_Size10", ar);

   }

   public void testArlequin_WhiteGrey_Size23() {
      ByteObject ar = fac.getFigArlequin(FULLY_OPAQUE_WHITE, FULLY_OPAQUE_GREY, 23);

      testImageFigure("Arlequin_GreenGrey_Size23", ar);

      ar.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_7_EXTRA_W_TYPE1, EXTRA_1_TOP_LEFT);
      ar.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_8_EXTRA_H_TYPE1, EXTRA_1_TOP_LEFT);

      testImageFigure("Arlequin_GreenGrey_Size23_TopLeft", ar);
      
      ar.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_7_EXTRA_W_TYPE1, EXTRA_0_CENTER);
      ar.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_8_EXTRA_H_TYPE1, EXTRA_0_CENTER);

      testImageFigure("Arlequin_GreenGrey_Size23_Center", ar);

   }

   public void testArlequin_3Gradients_Size23() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 100, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, rect3 };

      ByteObject arl = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 6);

      testImageFigure("Arlequin_3Gradients_Cursor100_6", arl);

   }

   public void testArlequin_3Gradients_Cursor0() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, rect3 };

      ByteObject arl = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 6);

      testImageFigure("Arlequin_3Gradients_Cursor0_6", arl);

   }

   public void testArlequin_4GradientsWithNull_Size23() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 100, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, null, rect3 };

      ByteObject arl = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 8);

      testImageFigure("Arlequin_4GradientsNull_8", arl);

   }

   public void testArlequin_3Gradients_6_Separator4_Center() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, rect3 };

      ByteObject fig = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 8,4,4);

      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_7_EXTRA_W_TYPE1, EXTRA_0_CENTER);
      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_8_EXTRA_H_TYPE1, EXTRA_0_CENTER);

      testImageFigure("Arlequin_3GradientsCursor0_6_Sep4_Center", fig);

   }
   
   public void testArlequin_3Gradients_6_Separator4_TopLeft() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, rect3 };

      ByteObject fig = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 8,4,4);

      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_7_EXTRA_W_TYPE1, EXTRA_1_TOP_LEFT);
      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_8_EXTRA_H_TYPE1, EXTRA_1_TOP_LEFT);

      testImageFigure("Arlequin_3GradientsCursor0_6_Sep4_TopLeft", fig);

   }
   
   public void testArlequin_3Gradients_6_Separator4_BotRight() {

      ByteObject grad = gradFac.getGradient(FULLY_OPAQUE_GREY, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject rect1 = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);
      ByteObject rect2 = fac.getFigRect(FULLY_OPAQUE_BLUE, grad);
      ByteObject rect3 = fac.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      ByteObject[] ar = new ByteObject[] { rect1, rect2, rect3 };

      ByteObject fig = fac.getFigArlequinNumFill(FULLY_OPAQUE_WHITE, ar, 8,4,4);

      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_7_EXTRA_W_TYPE1, EXTRA_2_BOT_RITE);
      fig.set1(IBOFigArlequin.FIG_ARLEQUIN_OFFSET_8_EXTRA_H_TYPE1, EXTRA_2_BOT_RITE);

      testImageFigure("Arlequin_3GradientsCursor0_6_Sep4_BotRight", fig);

   }
}
