package pasa.cbentley.framework.drawx.src4.engine.tests;

import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

public abstract class TestGraphicsXTransformations extends TestCaseDrawXPlugged {

   public TestGraphicsXTransformations() {
   }

   /**
    * 
    */
   public void testClipTranslate() {

      RgbImage ri = rc.create(100, 50, FULLY_OPAQUE_BLUE);

      GraphicsX g = ri.getGraphicsX();

      g.setTranslationShift(0, 10);

      //the clip values are set relative to translation. this is an intersection.
      g.clipSet(10, 10, 50, 50);

      assertEquals(g.getClipX(), 10);
      assertEquals(g.getClipY(), 20); //that 10 translated up 10 = 20

      assertEquals(g.getClipXRelative(), 10);
      assertEquals(g.getClipYRelative(), 30); //that 10 translated up 10 = 20

      assertEquals(g.getClipWidth(), 50);
      assertEquals(g.getClipHeight(), 30); //translated up 10, y=10 and total image is 50

      g.setTranslationShift(0, -10);

      assertEquals(g.getClipX(), 10);
      assertEquals(g.getClipY(), 20); //that 10 translated up 10 = 20

      assertEquals(g.getClipWidth(), 50);
      //still 30 because the translation does not modify the clip at all
      assertEquals(g.getClipHeight(), 30);

      assertEquals(g.getClipXRelative(), 10);
      assertEquals(g.getClipYRelative(), 20); //that 10 translated up 10 = 20

   }

   public void testClip() {
      RgbImage ri = rc.create(200, 300, FULLY_OPAQUE_BLUE);

      GraphicsX g = ri.getGraphicsX();

      assertEquals(0, g.getClipX());
      assertEquals(0, g.getClipY());
      //clip values are initialized to underlying image size
      assertEquals(200, g.getClipWidth());
      assertEquals(300, g.getClipHeight());

      //intersection
      g.clipSet(10, 10, 150, 150);
      
      //#debug
      toDLog().pTest("", g, TestGraphicsXTransformations.class, "testClip", LVL_05_FINE, false);

      assertEquals(10, g.getClipX());
      assertEquals(10, g.getClipY());
      assertEquals(150, g.getClipWidth());
      assertEquals(150, g.getClipHeight());

      //insection totally inside
      g.clipSet(20, 20, 100, 40);

      assertEquals(20, g.getClipX());
      assertEquals(20, g.getClipY());
      assertEquals(100, g.getClipWidth());
      assertEquals(40, g.getClipHeight());

      System.out.println(g.toStringClip());

      g.clipReset();

      assertEquals(10, g.getClipX());
      assertEquals(10, g.getClipY());
      assertEquals(150, g.getClipWidth());
      assertEquals(150, g.getClipHeight());

      g.setTranslationShift(-15, -10);

      assertEquals(10, g.getClipX());
      assertEquals(10, g.getClipY());
      assertEquals(150, g.getClipWidth());
      assertEquals(150, g.getClipHeight());

   }

   public void setupAbstractDrawX() {
      // TODO Auto-generated method stub
      
   }
}
