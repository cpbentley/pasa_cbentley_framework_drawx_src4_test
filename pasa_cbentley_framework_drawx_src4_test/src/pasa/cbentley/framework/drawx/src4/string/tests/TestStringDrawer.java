package pasa.cbentley.framework.drawx.src4.string.tests;

import java.awt.Graphics;
import java.awt.RenderingHints;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.coredraw.src4.interfaces.IImage;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.drawer.DrawerString;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOAnchor;

/**
 * Test the {@link DrawerString} from the base draw module.
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestStringDrawer extends TestCaseFrameworkUiPluggedDrawX {

   public String ex            = "Explicit";

   public int    FONT_BG_COLOR = FULLY_OPAQUE_RED;

   public int    FONT_COLOR    = FULLY_OPAQUE_WHITE;

   public String str           = "Hello ! :)";

   public TestStringDrawer() {
   }

   private void doTestFont(int font, String title) {
      DrawerString stringDrawer = new DrawerString(dc);

      char[] chars = str.toCharArray();

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, font, FONT_COLOR);

      assertEquals(FONT_COLOR, stringDrawer.getStringColor(textFigure));

      RgbImage ri = rc.create(120, 50, FONT_BG_COLOR);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      stringDrawer.drawFigString(g, 0, 0, 120, 40, textFigure, chars, 0, chars.length);

      doImageTest(ri, title);
   }

   private void doTestFontEx(int font, String title) {

      ByteObject textFigure = facFigure.getFigString(ex, FACE_MONOSPACE, STYLE_BOLD, font, FONT_COLOR);

      RgbImage ri = rc.create(120, 50, FONT_BG_COLOR);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      figureOp.paintFigure(g, 0, 0, 120, 50, textFigure);

      doImageTest(ri, title);
   }

   public void setupAbstractDrawX() {

   }

   public void testBasicStringerJ2SEFont() {

      DrawerString ser = new DrawerString(dc);

      char[] chars = "Hello!".toCharArray();

      ByteObject textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 24, FONT_COLOR);

      RgbImage ri = rc.create(120, 50, FONT_BG_COLOR);

      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      ser.drawFigString(g, 0, 0, 120, 40, textFigure, chars, 0, chars.length);

      doImageTest(ri, "HelloFont24");

      textFigure = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, 26, FONT_COLOR);

      ri = rc.create(120, 50, FONT_BG_COLOR);

      g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      ser.drawFigString(g, 0, 0, 120, 40, textFigure, chars, 0, chars.length);

      doImageTest(ri, "HelloFont26");
   }

   public void testCharCrop() {
      DrawerString stringDrawer = new DrawerString(dc);

      IMFont font = dc.getFontFactory().getFont(FACE_MONOSPACE, STYLE_BOLD, SIZE_3_MEDIUM);

      IImage imageM = stringDrawer.cropChar('M', font);

      RgbImage ri = rc.create(50, 50, FONT_BG_COLOR);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      g.drawImage(imageM, 25, 25, IBOAnchor.ANCHOR_G_CENTER_CENTER);

      doImageTest(ri, "croppedCharM_center");
   }

   public void testFont1Tiny() {
      doTestFont(SIZE_1_TINY, "HelloFont1Tiny");
   }

   public void testFont2Small() {
      doTestFont(SIZE_2_SMALL, "HelloFont2Small");
   }

   /**
    * Why is Anti-aliasing on? Where is it switch on-off?
    * <br>
    * Response: in the {@link Graphics} class of the brige, {@link RenderingHints}
    * <br>
    * <br>
    * We want to disable it for the unit tests.
    */
   public void testFont3Medium() {
      doTestFont(SIZE_3_MEDIUM, "HelloFont3Medium");
   }

   public void testFont4Large() {
      doTestFont(SIZE_4_LARGE, "HelloFont4Large");
   }

   public void testFont5Hug() {
      doTestFont(SIZE_5_HUGE, "HelloFont5Huge");
   }

   public void testTextFigureFont3Medium() {
      doTestFontEx(SIZE_3_MEDIUM, "TextFigure");
   }

   public void testTextNewLineFont3Medium() {
      doTestFontEx(SIZE_3_MEDIUM, "TextFigure");
   }

}
