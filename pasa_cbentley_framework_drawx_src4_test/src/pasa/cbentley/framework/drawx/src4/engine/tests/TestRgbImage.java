package pasa.cbentley.framework.drawx.src4.engine.tests;

import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tech.ITechGraphicsX;

/**
 * Check the .classpath file for correct ordering of export.
 * <br>
 * <br>
 * 
 * @author Mordan
 *
 */
public abstract class TestRgbImage extends TestCaseFrameworkUiPluggedDrawX implements ITechGraphicsX {

   public TestRgbImage() {

      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK_ALL, false);

   }

   RgbImage getDrawRgbImage(int mode) {

      //first draw a filled rect on 
      RgbImage r = rc.create(40, 20, FULLY_OPAQUE_SKY_GREEN);
      GraphicsX gi = r.getGraphicsX();
      assertEquals(MODE_1_IMAGE, gi.getPaintMode());
      gi.setColor(FULLY_OPAQUE_ORANGE);
      gi.fillRect(0, 0, 20, 10);

      RgbImage imageMain = rc.create(60, 40, FULLY_OPAQUE_BLUE);
      GraphicsX g = imageMain.getGraphicsX(mode);
      g.drawRgbImage(r, 0, 0);

      return imageMain;
   }

   RgbImage getDrawSubStringHellooo(int mode) {
      //first draw a filled rect on 
      RgbImage r = rc.create(100, 60, FULLY_OPAQUE_SKY_GREEN);
      GraphicsX gi = r.getGraphicsX(mode);
      gi.setColor(FULLY_OPAQUE_ORANGE);
      gi.drawString("Hellooo!", 0, 0, ITechGraphicsX.ANCHOR);
      gi.setColor(FULLY_OPAQUE_GREY);
      gi.drawSubstring("Hellooo!", 1, 4, 0, 30, ITechGraphicsX.ANCHOR);

      return r;
   }

   RgbImage getFillRectOrangeOnBlue(int mode) {
      RgbImage ri = rc.create(60, 40, FULLY_OPAQUE_BLUE);

      GraphicsX g = ri.getGraphicsX(mode);
      assertEquals(mode, g.getPaintMode());

      g.setColor(FULLY_OPAQUE_ORANGE);
      g.fillRect(0, 0, 30, 20);


      int pixel0 = ri.getPixel(0, 0);
      
      int pixel1 = ri.getPixel(0, 0);
      
      assertEquals(pixel1, pixel0);
      assertEquals(pixel0, FULLY_OPAQUE_ORANGE);

      assertEquals("Orange[Opaque]", ri.toStringPixelName(0, 0));
      assertEquals("Orange[Opaque]", ri.toStringPixelName(0, 19));
      assertEquals("Blue[Opaque]", ri.toStringPixelName(0, 20));
      assertEquals("Blue[Opaque]", ri.toStringPixelName(0, 21));
      
     
      return ri;
   }

   public void testFullyColored() {

      RgbImage ri = rc.create(30, 20, FULLY_OPAQUE_BLUE);
      
      assertEquals(true, ri.isFullyColored(FULLY_OPAQUE_BLUE));

   }

   public void testCrop() {

      RgbImage ri = rc.create(30, 20, FULLY_OPAQUE_BLUE);
      GraphicsX g = ri.getGraphicsX(MODE_1_IMAGE);
      g.setColor(FULLY_OPAQUE_GREY);
      g.fillRect(4, 2, 10, 5);

      doImageTest(ri, "CroppedGreyOnly1");
      
      //remove blue colors around the grey
      RgbImage cropped = ri.crop(FULLY_OPAQUE_BLUE);

      assertEquals(10, cropped.getWidth());
      assertEquals(5, cropped.getHeight());

      doImageTest(cropped, "CroppedGreyOnly");
      
      assertEquals("Grey[Opaque]", cropped.toStringPixelName(0, 0));
      assertEquals(true, cropped.isFullyColored(FULLY_OPAQUE_GREY));

   }

   public void testIsEmpty() {
      RgbImage ri = rc.create(30, 20, FULLY_OPAQUE_BLUE);

      assertEquals(true, ri.isEmpty(0, 0, 10, 10));
   }

   public void testGetPixel_Image() {
      RgbImage ri = rc.create(10, 10, FULLY_OPAQUE_BLUE);

      int pixel0 = ri.getPixel(0, 0);
      assertEquals(pixel0, FULLY_OPAQUE_BLUE);
   }

   public void testGetPixel_Rgb() {
      RgbImage ri = rc.createRGB(10, 10, FULLY_OPAQUE_BLUE);

      int pixel0 = ri.getPixel(0, 0);
      assertEquals(pixel0, FULLY_OPAQUE_BLUE);

   }

   public void setupAbstractDrawX() {

   }

   public void testCloneEmpty() {

      RgbImage ri = rc.create(100, 130, FULLY_OPAQUE_BLUE);

      int[] rgbData = ri.getRgbData();

      assertEquals(100 * 130, rgbData.length);
      assertEquals(rgbData[0], FULLY_OPAQUE_BLUE);

      RgbImage clone = ri.cloneImg();

      assertEquals(clone.getWidth(), 100);
      assertEquals(clone.getHeight(), 130);

      int[] rgbDataCloned = clone.getRgbData();
      assertEquals(100 * 130, rgbDataCloned.length);
      assertEquals(rgbDataCloned[0], FULLY_OPAQUE_BLUE);

   }

   public void testDrawRgbImage_Mode1Image() {

      RgbImage imageMain = getDrawRgbImage(MODE_1_IMAGE);
      doImageTest(imageMain, "DrawRgbImage_Mode1Image");
   }

   public void testDrawRgbImage_Mode2RgbImage() {

      RgbImage imageMain = getDrawRgbImage(MODE_2_RGB_IMAGE);
      doImageTest(imageMain, "DrawRgbImage_Mode2RgbImage");
   }

   public void testDrawStrings_Mode1Image() {
      RgbImage imageMain = getDrawSubStringHellooo(MODE_1_IMAGE);
      doImageTest(imageMain, "DrawStrings_Mode1Image_Hello");
   }

   public void testDrawStrings_Mode2RgbImage() {
      RgbImage imageMain = getDrawSubStringHellooo(MODE_2_RGB_IMAGE);
      doImageTest(imageMain, "DrawStrings_Mode2RgbImage_Hello");
   }

   /**
    * Test variation of {@link RgbImage#getGraphicsX()} in a neutral manner.
    */
   public void testFillRect_Mode1Image() {

      RgbImage ri = getFillRectOrangeOnBlue(MODE_1_IMAGE);

      doImageTest(ri, "FillRect_Mode1Image_OrangeOnBlue");
   }

   public void testFillRect_Mode2RgbImage() {
      RgbImage ri = getFillRectOrangeOnBlue(MODE_2_RGB_IMAGE);
      doImageTest(ri, "FillRect_Mode2RgbImage_OrangeOnBlue");
   }

}
