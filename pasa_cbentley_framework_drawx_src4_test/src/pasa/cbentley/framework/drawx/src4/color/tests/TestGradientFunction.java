package pasa.cbentley.framework.drawx.src4.color.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.functions.Function;
import pasa.cbentley.byteobjects.src4.tech.ITechFunction;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.framework.drawx.src4.color.GradientFunction;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXSwing;

public class TestGradientFunction extends TestCaseDrawXSwing {

   /**
    * 
    */
   public TestGradientFunction() {
   }

   public void setupAbstractDrawX() {

   }

   public void testBasic() {
      ByteObject gradBorder = facGradient.getGradient(FULLY_OPAQUE_WHITE, 100, GRADIENT_TYPE_RECT_02_VERT);

   }

   public void testFunctionGradient() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);

      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 3, grad);

      System.out.println(gf);

      int[] colors = gf.getColors();
      assertEquals(toStringColor(FULLY_OPAQUE_BLACK), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 127, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(FULLY_OPAQUE_GREEN), toStringColor(colors[colors.length - 1]));

   }

   public void testFunctionGradient4() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 50);

      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 4, grad);

      System.out.println(gf);

      int[] colors = gf.getColors();
      assertEquals(4, colors.length);
      assertEquals(toStringColor(FULLY_OPAQUE_BLACK), toStringColor(colors[0]));
      assertEquals(toStringColor(FULLY_OPAQUE_GREEN), toStringColor(colors[1]));
      assertEquals(toStringColor(FULLY_OPAQUE_GREEN), toStringColor(colors[2]));
      assertEquals(toStringColor(FULLY_OPAQUE_BLACK), toStringColor(colors[3]));

   }

   public void testFunctionGradientAlpha() {
      ByteObject grad = facGradient.getGradient(FULLY_TRANSPARENT_BLACK, 50);
      grad.setFlag(GRADIENT_OFFSET_01_FLAG, GRADIENT_FLAG_4_USEALPHA, true);

      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 9, grad);
      int[] colors = gf.getColors();

      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(170, 0, 0, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(85, 0, 0, 0)), toStringColor(colors[2]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(0, 0, 0, 0)), toStringColor(colors[3]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(0, 0, 0, 0)), toStringColor(colors[4]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(63, 0, 0, 0)), toStringColor(colors[5]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(127, 0, 0, 0)), toStringColor(colors[6]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(191, 0, 0, 0)), toStringColor(colors[7]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[8]));

      gf.init(FULLY_OPAQUE_BLACK, 8, grad);
      colors = gf.getColors();

      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(170, 0, 0, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(85, 0, 0, 0)), toStringColor(colors[2]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(0, 0, 0, 0)), toStringColor(colors[3]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(0, 0, 0, 0)), toStringColor(colors[4]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(84, 0, 0, 0)), toStringColor(colors[5]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(170, 0, 0, 0)), toStringColor(colors[6]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[7]));
   }

   public void testFunctionGradientExcludePart1Left() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_3_PART1_EXCLUDE_LEFT, true);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_4_PART1_EXCLUDE_RIGHT, false);
      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 3, grad);
      int[] colors = gf.getColors();
      assertEqualsToStringColor(ColorUtils.getRGBInt(255, 0, 63, 0), colors[0]);
      assertEquals(ColorUtils.getRGBInt(255, 0, 127, 0), colors[1]);
      assertEquals(ColorUtils.getRGBInt(255, 0, 191, 0), colors[colors.length - 1]);
   }

   /**
    * Excluse on the left of part 1 so we don't have black and we don't have real green
    */
   public void testFunctionGradientExcludePart1LeftAndRight() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_3_PART1_EXCLUDE_LEFT, true);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_4_PART1_EXCLUDE_RIGHT, true);
      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 3, grad);
      int[] colors = gf.getColors();
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 63, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 127, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 191, 0)), toStringColor(colors[colors.length - 1]));

   }

   public void testFunctionGradientExcludePart1None() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_3_PART1_EXCLUDE_LEFT, false);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_4_PART1_EXCLUDE_RIGHT, false);
      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 3, grad);
      int[] colors = gf.getColors();
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 127, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 255, 0)), toStringColor(colors[colors.length - 1]));

   }

   public void testFunctionGradientExcludePart1Right() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_3_PART1_EXCLUDE_LEFT, false);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_4_PART1_EXCLUDE_RIGHT, true);

      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 3, grad);
      int[] colors = gf.getColors();
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 84, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 170, 0)), toStringColor(colors[colors.length - 1]));
   }

   public void testFunctionGradientExcludePart1RightEquivalent() {
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_GREEN, 100);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_3_PART1_EXCLUDE_LEFT, false);
      grad.setFlag(GRADIENT_OFFSET_02_FLAGK_EXCLUDE, GRADIENT_FLAGK_4_PART1_EXCLUDE_RIGHT, false);
      GradientFunction gf = new GradientFunction(drc);
      gf.init(FULLY_OPAQUE_BLACK, 4, grad);
      int[] colors = gf.getColors();
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 0, 0)), toStringColor(colors[0]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 84, 0)), toStringColor(colors[1]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 170, 0)), toStringColor(colors[2]));
      assertEquals(toStringColor(ColorUtils.getRGBInt(255, 0, 255, 0)), toStringColor(colors[colors.length - 1]));
   }

}
