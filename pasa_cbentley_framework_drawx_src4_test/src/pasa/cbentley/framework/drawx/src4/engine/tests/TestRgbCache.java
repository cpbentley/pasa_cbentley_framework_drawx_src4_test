package pasa.cbentley.framework.drawx.src4.engine.tests;

import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.framework.coredraw.src4.interfaces.IImage;
import pasa.cbentley.framework.drawx.src4.engine.BlendOp;
import pasa.cbentley.framework.drawx.src4.engine.RgbCache;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawX;
import pasa.cbentley.framework.drawx.src4.utils.DrawUtilz;

public abstract class TestRgbCache extends TestCaseDrawX {

   public TestRgbCache() {
      
   }

   public void testJ2SEImage() {

      IImage img = getImageFactory().createImage(10, 15);
      assertEquals(true, img.isMutable());
      assertEquals(10, img.getWidth());
      assertEquals(15, img.getHeight());

   }

   public void testImageUtilz() {
      int color = FULLY_OPAQUE_WHITE;
      int colorT = FULLY_TRANSPARENT_WHITE;
      int mc = BlendOp.mergePixelsOver(color, colorT);

      int transBg = BlendOp.mergePixelsOver(colorT, color);

      int c4 = ColorUtils.getRGBInt(26, 133, 89, 33);
      int c4Merg = BlendOp.mergePixelsOver(color, c4);


   }

   /**
    * We need Headless mode
    */
   public void testRgbCache() {
      int w = 200;
      int h = 300;

      RgbImage firstImage100_100 = rgbCache.createImage(100, 100);
      assertEquals(0, firstImage100_100.getOffset());

      assertEquals(true, firstImage100_100.isRgb());
      assertEquals(false, firstImage100_100.isRegion());

      firstImage100_100.setRgbMode(false);

      firstImage100_100.setRgbMode(true);

      firstImage100_100.changeDimension(100, 90);

      assertEquals(0, firstImage100_100.getOffset());

      RgbImage ri10_50 = rgbCache.createImage(10, 50);
      assertEquals(0, ri10_50.getOffset());

      firstImage100_100.changeDimension(110, 110);

      //create a root image
      int linkSig = 14;
      RgbImage rootL = rgbCache.createImageLink(40, 70, linkSig);

      assertEquals(40, rootL.getWidth());
      assertEquals(70, rootL.getHeight());

      rootL.changeDimension(60, 50);

      assertEquals(60, rootL.getWidth());
      assertEquals(50, rootL.getHeight());

      RgbImage rootFlipH = rgbCache.createImageLink(rootL, IImage.TRANSFORM_1_FLIP_H_MIRROR_ROT180);
      System.out.println(rootFlipH);
      RgbImage rootFlipV = rgbCache.createImageLink(rootL, IImage.TRANSFORM_2_FLIP_V_MIRROR);
      RgbImage rootRot90 = rgbCache.createImageLink(rootL, IImage.TRANSFORM_5_ROT_90);

      System.out.println(rgbCache);

      assertEquals(60, rootFlipH.getWidth());
      assertEquals(50, rootFlipH.getHeight());

      assertEquals(60, rootFlipV.getWidth());
      assertEquals(50, rootFlipV.getHeight());

      assertEquals(50, rootRot90.getWidth());
      assertEquals(60, rootRot90.getHeight());

      // not implementd yet
      //	 RgbImage rootRot90Sig = rc.createImageLink(60, 50, 14, RgbImage.TRANSFORM_5_ROT_90);
      //	 assertEquals(true, rootRot90Sig == rootRot90);

      RgbImage drawableCache = rgbCache.create(200, 100);
      //animation wants to move quickly those pixels on the screen
      RgbImage moveAnimation = drawableCache.acquireLock(RgbImage.FLAG_07_READ_LOCK);
      assertEquals(true, drawableCache == moveAnimation);

      RgbImage alphaChange = drawableCache.acquireLock(RgbImage.FLAG_06_WRITE_LOCK);
      assertEquals(true, drawableCache != alphaChange);
      assertEquals(true, alphaChange.hasFlag(RgbImage.FLAG_11_CLONED));

      //how to use micro emulator in headless mode?
      //	 RgbImage srcImage = RgbImage.create("/icons/dog_trans.png");
      //	 assertEquals(true, srcImage.getImage() != null);
      //	 RgbImage srcImage2 = RgbImage.create("/icons/dog_trans.png");
      //	 assertEquals(true, srcImage == srcImage2);
      //	 RgbImage lockedWriteDog = srcImage.acquireLock(RgbImage.FLAG_6WRITE_LOCK);
      //	 assertEquals(true, lockedWriteDog == srcImage2);
      //	 RgbImage readDog = srcImage.acquireLock(RgbImage.FLAG_7READ_LOCK);

      System.out.println(rgbCache.toString());

      RgbImage[] manySmalls = rgbCache.createImages(w, h, 20);
      assertEquals(manySmalls.length, 20);
      for (int i = 0; i < manySmalls.length; i++) {
         assertEquals(manySmalls[i].getWidth(), w);
         assertEquals(manySmalls[i].getHeight(), h);
      }
   }

   public void setupAbstractDrawX() {
      // TODO Auto-generated method stub
      
   }
}
