package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.interfaces.C;

public abstract class TestFigureTriangle extends TestFigureAbsract {

   public void testTriangle_Green_TypeTop() {
      ByteObject rect = fac.getFigTriangleTypeTop(FULLY_OPAQUE_GREEN);

      testImageFigure("Triangle_Green_Top", rect);

   }
   
   public void testTriangle_Green_TypeRight_Gradient() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_TRIG_00_TENT);
      
      ByteObject rect = fac.getFigTriangleType(FULLY_OPAQUE_GREEN, C.TYPE_03_RIGHT, grad);

      testImageFigure("Triangle_Green_Right_Gradient", rect);

   }

   public void testTriangle_Green_TypeBottom_Gradient_Fill() {
      
      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_TRIG_01_TENT_JESUS);
      
      ByteObject rect = fac.getFigTriangleType(FULLY_OPAQUE_GREEN, C.TYPE_01_BOTTOM, grad);

      testImageFigure("Triangle_Green_Bottom_GradientFill", rect);

   }
   
   public void testTriangle_Green_TypeBottom_Gradient() {
      
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 30, GRADIENT_TYPE_TRIG_02_TOP_JESUS);
      
      ByteObject rect = fac.getFigTriangleType(FULLY_OPAQUE_GREEN, C.TYPE_04_TopLeft, grad);

      testImageFigure("Triangle_Green_TopLeft_Gradient", rect);

   }

}
