package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseDrawX;
import pasa.cbentley.framework.drawx.src4.tech.ITechTblr;

public class TestTblrFactory extends TestCaseDrawX implements ITechTblr {

   public void setupAbstractDrawX() {

   }

   public void testSingle2() {
      ByteObject pad = facTBLR.getTBLR(2);

      
      //#debug
      toDLog().pTest("", pad, TestTblrFactory.class, "testTBLRSingle2");

      assertEquals(2, facTBLR.getTBLRValue(pad, C.POS_0_TOP));
      assertEquals(2, facTBLR.getTBLRValue(pad, C.POS_1_BOT));
      assertEquals(2, facTBLR.getTBLRValue(pad, C.POS_2_LEFT));
      assertEquals(2, facTBLR.getTBLRValue(pad, C.POS_3_RIGHT));

   }

   public void testDifferentValuesSmall() {
      ByteObject pad = facTBLR.getTBLR(24, 25, 26, 27);
      
      //#debug
      toDLog().pTest("", pad, TestTblrFactory.class, "testDifferentValuesSmall");

      assertEquals(false, pad.hasFlag(TBLR_OFFSET_01_FLAG, TBLR_FLAG_1_USING_ARRAY));
     
      assertEquals(24, facTBLR.getTBLRValue(pad, C.POS_0_TOP));
      assertEquals(25, facTBLR.getTBLRValue(pad, C.POS_1_BOT));
      assertEquals(26, facTBLR.getTBLRValue(pad, C.POS_2_LEFT));
      assertEquals(27, facTBLR.getTBLRValue(pad, C.POS_3_RIGHT));
   }
   
   public void testDifferentValuesBig() {
      ByteObject pad = facTBLR.getTBLR(2400, 125, 26, 4000);
      
      //#debug
      toDLog().pTest("", pad, TestTblrFactory.class, "testDifferentValuesBig");

      assertEquals(true, pad.hasFlag(TBLR_OFFSET_01_FLAG, TBLR_FLAG_1_USING_ARRAY));
     
      assertEquals(2400, facTBLR.getTBLRValue(pad, C.POS_0_TOP));
      assertEquals(125, facTBLR.getTBLRValue(pad, C.POS_1_BOT));
      assertEquals(26, facTBLR.getTBLRValue(pad, C.POS_2_LEFT));
      assertEquals(4000, facTBLR.getTBLRValue(pad, C.POS_3_RIGHT));
   }
}
