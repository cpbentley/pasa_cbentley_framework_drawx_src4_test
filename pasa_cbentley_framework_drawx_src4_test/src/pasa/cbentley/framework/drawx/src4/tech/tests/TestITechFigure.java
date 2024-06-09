package pasa.cbentley.framework.drawx.src4.tech.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.IBOTypesBOC;
import pasa.cbentley.byteobjects.src4.ctx.IToStringFlagsBO;
import pasa.cbentley.byteobjects.src4.objects.color.IBOGradient;
import pasa.cbentley.byteobjects.src4.objects.pointer.IBOMergeMask;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkDrawX;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigPixels;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigTriangle;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigure;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.layouter.src4.ctx.IBOTypesLayout;
import pasa.cbentley.layouter.src4.engine.Anchor32Bits;
import pasa.cbentley.layouter.src4.tech.ITechLayout;

/**
 * Test ITech definitions 
 * @author Charles Bentley
 *
 */
public class TestITechFigure extends TestCaseFrameworkDrawX {

   public TestITechFigure() {
   }

   /**
    * Testing Triangle with a percent of a
    */
   public void testFigTriangle() {
      int sizew = 5;
      int sizeh = 100;
      int bcolor = 101010;
      int littleH = 30;


      ByteObject anchorLB = facBox.getLeftBot(sizew, sizeh);

      //Etalon is 0 for context
      int encodedH = layOp.codedSizeEncodeRatioDefEtalon(littleH);

      ByteObject triLB = figureFac.getFigTriangleAngle(bcolor, C.ANGLE_UP_90, encodedH, anchorLB);

      //System.out.println(triLB);

      assertEquals(bcolor, triLB.get4(IBOFigure.FIG__OFFSET_06_COLOR4));
      assertEquals(C.ANGLE_UP_90, triLB.get2(IBOFigTriangle.FIG_TRIANGLE_OFFSET_03_ANGLE2));

      assertEquals(encodedH, triLB.get4(IBOFigTriangle.FIG_TRIANGLE_OFFSET_04_h4));

      triLB = figureFac.getFigTriangleAngle(bcolor, C.ANGLE_UP_90, encodedH, anchorLB);

      assertEquals(bcolor, triLB.getValue(IBOFigure.FIG__OFFSET_06_COLOR4, 4));
      assertEquals(C.ANGLE_UP_90, triLB.getValue(IBOFigTriangle.FIG_TRIANGLE_OFFSET_03_ANGLE2, 2));
      assertEquals(encodedH, triLB.getValue(IBOFigTriangle.FIG_TRIANGLE_OFFSET_04_h4, 4));

   }

   public void testFigString() {

      ByteObject bo = figureFac.getFigString(IMFont.FACE_MONOSPACE, IMFont.STYLE_BOLD, IMFont.SIZE_4_LARGE, FULLY_OPAQUE_BLUE);

      ByteObject top = figureFac.getFigStringTColor(FULLY_OPAQUE_GREEN);

      boc.toStringSetToStringFlag(IToStringFlagsBO.TOSTRING_FLAG_2_IGNORE_PARAMS, false);

      //#debug
      toDLog().pTest("", top, TestITechFigure.class, "testFigString", LVL_05_FINE, true);

      ByteObject merge = bo.mergeByteObject(top);

      assertEquals(IMFont.FACE_MONOSPACE, merge.get1(IBOFigString.FIG_STRING_OFFSET_03_FACE1));
      assertEquals(IMFont.STYLE_BOLD, merge.get1(IBOFigString.FIG_STRING_OFFSET_04_STYLE1));
      assertEquals(IMFont.SIZE_4_LARGE, merge.get1(IBOFigString.FIG_STRING_OFFSET_05_SIZE1));
      assertEquals(FULLY_OPAQUE_GREEN, merge.get4(IBOFigure.FIG__OFFSET_06_COLOR4));

      ByteObject small = figureFac.getFigStringTFontSize(IMFont.SIZE_2_SMALL);

      //#debug
      toDLog().pTest("", small, TestITechFigure.class, "testFigString", LVL_05_FINE, true);

      merge = merge.mergeByteObject(small);

      assertEquals(IMFont.FACE_MONOSPACE, merge.get1(IBOFigString.FIG_STRING_OFFSET_03_FACE1));
      assertEquals(IMFont.STYLE_BOLD, merge.get1(IBOFigString.FIG_STRING_OFFSET_04_STYLE1));
      assertEquals(IMFont.SIZE_2_SMALL, merge.get1(IBOFigString.FIG_STRING_OFFSET_05_SIZE1));
      assertEquals(FULLY_OPAQUE_GREEN, merge.get4(IBOFigure.FIG__OFFSET_06_COLOR4));

   }

   public void testMask() {

      int maskBgColor = 45;
      int maskOpenColor = 123456789;
      int bgFondu = 45;
      int openFondu = 56;
      ByteObject maskFilter = null;
      ByteObject bgFigure = figureFac.getFigRect(1234567);
      ByteObject mask = facMask.getMask(maskBgColor, maskOpenColor, bgFondu, openFondu, maskFilter, bgFigure);

      assertEquals(maskBgColor, mask.get4(MASK_OFFSET_2_COLOR_BG4));
      assertEquals(maskOpenColor, mask.get4(MASK_OFFSET_4_COLOR_SHAPE4));
      assertEquals(bgFondu, mask.get1(MASK_OFFSET_8_ALPHA_BG1));
      assertEquals(openFondu, mask.get1(MASK_OFFSET_9_ALPHA_SHAPE1));
      assertEquals(bgFigure, mask.getSubFirst(TYPE_DRWX_00_FIGURE));
      assertEquals(maskFilter, mask.getSubFirst(IBOTypesBOC.TYPE_040_COLOR_FILTER));

   }

   public void testFigLine() {

   }

   public void testFigLineRepeater() {

   }

   public void testFigGrid() {

   }

   public void testFigBorder() {
      ByteObject grad = facGradient.getGradient(1122334455, 45, 0);
      int size = 5;
      int arcw = 3;
      int arch = 3;
      int color = ColorUtils.FULLY_OPAQUE_BLACK;

      ByteObject border = figureFac.getFigBorder(size, arcw, arch, color, grad);

      ByteObject tblr = border.getSubFirst(IBOTypesLayout.FTYPE_2_TBLR);
      
      assertEquals(size, layOp.getTBLRValue(tblr, C.POS_1_BOT));
      assertEquals(size, layOp.getTBLRValue(tblr, C.POS_0_TOP));
      assertEquals(size, layOp.getTBLRValue(tblr, C.POS_2_LEFT));
      assertEquals(size, layOp.getTBLRValue(tblr, C.POS_3_RIGHT));

      System.out.println(border);
      //not set. the rectangle takes
      assertEquals(0, border.getValue(IBOFigure.FIG__OFFSET_06_COLOR4, 4));

      ByteObject rect = border.getSubFirst(TYPE_DRWX_00_FIGURE);
      assertEquals(color, rect.getValue(IBOFigure.FIG__OFFSET_06_COLOR4, 4));

      assertNotNull(rect.getSubFirst(IBOTypesBOC.TYPE_038_GRADIENT));
   }

   public void testGradient() {
      ByteObject grad = facGradient.getGradient(1122334455, 45);
      assertEquals(1122334455, grad.get4(IBOGradient.GRADIENT_OFFSET_04_COLOR4));
      assertEquals(45, grad.get1(IBOGradient.GRADIENT_OFFSET_05_CURSOR1));

      ByteObject color3 = boc.getLitteralIntFactory().getLitteralInt(999);

      //third color merge mask
      ByteObject grad3rdColor = facGradient.getGradient(0, 0, 0, color3);

      ByteObject mm = facMergeMask.getMergeMask(IBOMergeMask.MERGE_MASK_OFFSET_1FLAG1, IBOGradient.GRADIENT_FLAG_3_THIRD_COLOR);
      facMergeMask.setMergeMask(mm, grad3rdColor);

   }

   public void testFigPixels() {

      ByteObject pix = figureFac.getFigPixels(500, true, false, new int[] { 0, -1, -65536, -16711936, 2 });
      assertEquals(true, pix.hasFlag(IBOFigPixels.FIG_PIXEL_OFFSET_01_FLAG, IBOFigPixels.FIG_PIXEL_FLAG_1_RANDOM_SIZE));
      assertEquals(false, pix.hasFlag(IBOFigPixels.FIG_PIXEL_OFFSET_01_FLAG, IBOFigPixels.FIG_PIXEL_FLAG_2_RANDOM_COLOR));
      
      assertEquals(500, pix.getValue(IBOFigPixels.FIG_PIXEL_OFFSET_07_LENGTH_H2, 2));
      
      int[] v = pix.getValues(IBOFigPixels.FIG_PIXEL_OFFSET_04_COLORSX);
      assertEquals(v.length, 5);
      assertEquals(v[0], 0);
      assertEquals(v[1], -1);
      assertEquals(v[2], -65536);
      assertEquals(v[3], -16711936);
      assertEquals(v[4], 2);

      pix = figureFac.getFigPixels(1, true, false, new int[] { 0, -1, -65536 });
      assertEquals(1, pix.getValue(IBOFigPixels.FIG_PIXEL_OFFSET_07_LENGTH_H2, 2));
      v = pix.getValues(IBOFigPixels.FIG_PIXEL_OFFSET_04_COLORSX);
      assertEquals(v.length, 3);
      assertEquals(v[0], 0);
      assertEquals(v[1], -1);
      assertEquals(v[2], -65536);

      assertEquals(0, pix.getVersion());

   }

   public void testRepository() {

      int bgColor = 1234932;

      ByteObject scBg = figureFac.getFigRect(bgColor);
      ByteObject scBg2 = figureFac.getFigRect(bgColor);

      System.out.println(scBg);
      System.out.println(scBg2);

      assertEquals(scBg.get4(IBOFigure.FIG__OFFSET_06_COLOR4), bgColor);
      assertEquals(scBg2.get4(IBOFigure.FIG__OFFSET_06_COLOR4), bgColor);

   }

   public void testAnchor32Bits() {
      int anchor = Anchor32Bits.anchorEncode(Anchor32Bits.ALIGN_BOTTOM, Anchor32Bits.ALIGN_CENTER, 20, 50);
      assertEquals(Anchor32Bits.ALIGN_BOTTOM, Anchor32Bits.anchorDecodeHA(anchor));
      assertEquals(Anchor32Bits.ALIGN_CENTER, Anchor32Bits.anchorDecodeVA(anchor));
      assertEquals(20, Anchor32Bits.anchorDecodeW(anchor));
      assertEquals(50, Anchor32Bits.anchorDecodeH(anchor));
      assertEquals(40, Anchor32Bits.anchorDecodeW(anchor, 200));
      assertEquals(100, Anchor32Bits.anchorDecodeH(anchor, 200));

      anchor = Anchor32Bits.anchorEncodePixel(20, 50);
      assertEquals(Anchor32Bits.ALIGN_FILL, Anchor32Bits.anchorDecodeHA(anchor));
      assertEquals(Anchor32Bits.ALIGN_FILL, Anchor32Bits.anchorDecodeVA(anchor));
      assertEquals(20, Anchor32Bits.anchorDecodeW(anchor));
      assertEquals(50, Anchor32Bits.anchorDecodeH(anchor));
      assertEquals(20, Anchor32Bits.anchorDecodeW(anchor, 200));
      assertEquals(50, Anchor32Bits.anchorDecodeH(anchor, 200));
   }



   public void testCodedSize() {
      //      gc.getVCAppli().newSize(150, 200);
      //      gc.getRootViewCtx().newSize(150, 200);
      //      //dd.setRefFontSize(20);
      //
      //      ISizable bs = gc.getSizer();
      //      int codedSize = bs.codedSizeEncode(20, CODED_SIZE_TYPE_PERCENT);
      //      assertEquals(60, bs.codedSizeDecode(codedSize, 300));
      //
      //      codedSize = bs.codedSizeEncode(20, CODED_SIZE_TYPE_PERCENT, CODED_SIZE_FLAG_ETALON_H, 30);
      //      assertEquals(40, bs.codedSizeDecode(codedSize));
      //
      //      codedSize = bs.codedSizeEncode(20, CODED_SIZE_TYPE_PERCENT, CODED_SIZE_FLAG_ETALON_FONT, 50);
      //
      //      assertEquals(50, bs.codedSizeDecode(codedSize));
      //
      //      codedSize = bs.codedSizeEncode(60, CODED_SIZE_TYPE_PERTEN, CODED_SIZE_FLAG_ETALON_FONT, 50);
      //      //60 / 10 * font size
      //      assertEquals(120, bs.codedSizeDecode(codedSize));
      //      System.out.println(bs.codedSizeDebug1Line(codedSize));
      //
      //      codedSize = bs.codedSizeEncode(60, CODED_SIZE_TYPE_PERMILLE, CODED_SIZE_FLAG_ETALON_FONT, 50);
      //      assertEquals(50, bs.codedSizeDecode(codedSize));
      //
      //      codedSize = bs.codedSizeEncode(60, CODED_SIZE_TYPE_PERMILLE, CODED_SIZE_FLAG_ETALON_FONT);
      //      assertEquals(1, bs.codedSizeDecode(codedSize));
      //
      //      codedSize = bs.codedSizeEncode(0, CODED_SIZE_TYPE_PERMILLE, CODED_SIZE_FLAG_ETALON_FONT);
      //      assertEquals(0, bs.codedSizeDecode(codedSize));
      //
      //      System.out.println(bs.codedSizeDebug1Line(codedSize));
   }

   public void testFigBgColor() {

      int bColor = ColorUtils.getRGBInt(140, 140, 240);
      ByteObject figureBg = figureFac.getFigRect(bColor);

      assertEquals(bColor, figureBg.get4(IBOFigure.FIG__OFFSET_06_COLOR4));

      int transGreyColor = ColorUtils.getRGBInt(64, 40, 140, 40);
      ByteObject greyFg = figureFac.getFigRect(transGreyColor);

   }


   public void setupAbstractDrawX() {

   }



}
