package pasa.cbentley.framework.drawx.src4.color.tests;

import java.util.Random;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.functions.Function;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.framework.drawx.src4.color.ColorFunction;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawX;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXSwing;

public class TestColorFunction extends TestCaseDrawX {

   public TestColorFunction() {
   }

   public void testColorRandomizer() {

      ColorFunction cf = facColorFun.getColorFunctionRandom();
      Random r = new Random(10);
      cf.setRandom(r);

      assertEquals(toStringColor(ColorUtils.getRGBInt(186, 253, 122, 210)), toStringColor(cf.fx(FULLY_OPAQUE_BLUE)));
      assertEquals(toStringColor(ColorUtils.getRGBInt(114, 21, 22, 248)), toStringColor(cf.fx(FULLY_OPAQUE_BLUE)));

   }

   /**
    * 
    */
   public void testFunctionChannelAXC_AlphaGreenBlue() {
      int color = ColorUtils.getRGBInt(10, 10, 10, 10);

      ByteObject fct = facColorFun.getColorFunction( 1, 6, true, false, true, true);
      Function f = facFunction.createFunction(fct);

      assertEquals(true, f != null);
      assertEquals(true, f instanceof ColorFunction);

      System.out.println(f);

      int newc = f.fx(color);

      assertEquals(16, (newc >> 24) & 0xFF);
      assertEquals(10, (newc >> 16) & 0xFF);
      assertEquals(16, (newc >> 8) & 0xFF);
      assertEquals(16, (newc >> 0) & 0xFF);

      f.dispose();

   }

   /**
    * Apply a function on each ARGB channel.
    * <br>
    * Usually, the A function is different.
    */
   public void testFunctionChannelAXC_RedBlue() {

      int a = 1;
      int c = 5;
      ByteObject fun = facFunction.getFunctionAxC( a, c);

      //color function acting on red channel and blue channel
      ByteObject fo = facColorFun.getColorFunction(fun, false, true, false, true);

      Function f = facFunction.createFunction(fo);

      System.out.println(f);

      int color = ColorUtils.getRGBInt(128, 128, 128, 128);

      assertEquals(toStringColor(ColorUtils.getRGBInt(128, 133, 128, 133)), toStringColor(f.fx(color)));
      assertEquals(ColorUtils.getRGBInt(128, 138, 128, 138), f.fx(f.fx(color)));

   }

   public void setupAbstractDrawX() {
      // TODO Auto-generated method stub
      
   }

}
