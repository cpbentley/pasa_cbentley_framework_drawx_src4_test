package pasa.cbentley.framework.drawx.src4.engine.tests;

import org.junit.Test;

import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseDrawXPlugged;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tech.ITechBox;

/**
 * Tests the Graphics
 * @author Charles Bentley
 *
 */
public abstract class TestGraphicsX extends TestCaseDrawXPlugged {

   /**
    * See {@link MordTestCase}
    */
   public TestGraphicsX() {
      
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK, false);
   }

   public void testDrawRedRect() {

      RgbImage ri = rc.create(30, 30, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      g.setColor(255, 0, 0);
      g.fillRect(0, 0, 20, 20);

      doImageTest(ri, prefix + "RectangleRedFill");
   }

   public void testDrawRedRectDraw() {

      RgbImage ri = rc.create(30, 30, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      //#debug
      toDLog().pTest("Before Drawing RedRect", ri, TestGraphicsX.class, "testDrawRedRectDraw", LVL_05_FINE, false);
      
      assertEquals(30, ri.getWidth());
      assertEquals(30, ri.getHeight());
      
      g.setColor(255, 0, 0);
      g.drawRect(0, 0, 20, 20);

      //#debug
      toDLog().pTest("After Drawing RedRect", ri, TestGraphicsX.class, "testDrawRedRectDraw", LVL_05_FINE, false);
     
      
      doImageTest(ri, prefix + "RectangleRedDraw");
   }

   public void testDrawSimpelString() {
      RgbImage ri = rc.create(250, 30, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      g.setColor(255, 0, 0);

      IMFont f = g.getFont(IMFont.FACE_MONOSPACE, IMFont.STYLE_BOLD, IMFont.SIZE_3_MEDIUM);

      g.setFont(f);

      g.drawString("Test String", 5, 5, ITechBox.ANCHOR);

      doImageTest(ri, prefix + "StringRedBoldMedium");
   }

   public void testDrawCharacters() {
      RgbImage ri = rc.create(150, 130, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      g.setColor(255, 0, 0);

      IMFont f = g.getFont(IMFont.FACE_MONOSPACE, IMFont.STYLE_BOLD, IMFont.SIZE_3_MEDIUM);

      //problem font needs a gc. with SWT, make sure default font is loaded
      g.setFont(f);

      int dx = 5;
      int dy = 5;
      g.drawChar('C', dx, dy, ITechBox.ANCHOR);

      dx += f.charWidth('C');

      //g.setFont(f);

      g.drawChar('2', dx, dy, ITechBox.ANCHOR);

      dy += f.getHeight();

      char[] chars = new char[] { '3', '4' };
      g.drawChars(chars, 0, 2, dx, dy, ITechBox.ANCHOR);

      dy += f.getHeight();

      chars = new char[] { 'c', 'z', 'Z' };
      g.drawChars(chars, 1, 2, dx, dy, ITechBox.ANCHOR);

      doImageTest(ri, prefix + "Characters");

   }
}
