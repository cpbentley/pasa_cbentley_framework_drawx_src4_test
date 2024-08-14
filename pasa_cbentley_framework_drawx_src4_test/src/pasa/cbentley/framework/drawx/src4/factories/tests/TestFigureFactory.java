package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.string.StringAuxFactory;
import pasa.cbentley.framework.drawx.src4.string.StringAuxOperator;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFigString;
import pasa.cbentley.layouter.src4.tech.IBOTblr;

public abstract class TestFigureFactory extends TestCaseFrameworkUiPluggedDrawX implements IBOTblr, ITechGradient,IBOFigString {

   FigureFactory  fac;

   FigureOperator op;

   public TestFigureFactory() {

      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, true);
      
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK_ALL, false);
   }

   public void setupAbstractDrawX() {
      op = drc.getFigureOperator();
      fac = drc.getFigureFactory();
   }
   
   public void testFigString() {
      
      
      int face = FACE_MONOSPACE;
      int style = STYLE_PLAIN;
      int size = SIZE_4_LARGE;
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject scale = null;
      ByteObject fx = null;
      ByteObject mask = null;
      ByteObject anchor = anchorFac.getCenterCenter();
      ByteObject format = dc.getStringAuxFactory().getStrAuxFormat_NiceWordNormalTrim();
      ByteObject specials = dc.getStringAuxFactory().getStrAuxSpecials_NewLineWorkSingleSpaceTab();
   
      ByteObject strFig = facFigure.getFigString(null, face, style, size, color, fx, mask, scale, anchor, format, specials);

      
      assertEquals(FULLY_OPAQUE_ORANGE, strFig.get4(FIG__OFFSET_06_COLOR4));
      assertEquals(FACE_MONOSPACE, strFig.get1(FIG_STRING_OFFSET_03_FACE1));
      assertEquals(STYLE_PLAIN, strFig.get1(FIG_STRING_OFFSET_04_STYLE1));
      assertEquals(SIZE_4_LARGE, strFig.get1(FIG_STRING_OFFSET_05_SIZE1));
      
      assertEquals(true, strFig.hasFlag(FIG__OFFSET_02_FLAG, FIG_FLAG_1_BOX));
      assertEquals(true, strFig.hasFlag(FIG_STRING_OFFSET_02_FLAGX, FIG_STRING_FLAGX_3_DEFINED_FORMAT));
      assertEquals(true, strFig.hasFlag(FIG_STRING_OFFSET_02_FLAGX, FIG_STRING_FLAGX_4_DEFINED_SPECIALS));

      assertEquals(false, strFig.hasFlag(FIG__OFFSET_02_FLAG, FIG_FLAG_4_MASK));
      assertEquals(false, strFig.hasFlag(FIG_STRING_OFFSET_02_FLAGX, FIG_STRING_FLAGX_1_DEFINED_SCALER));
      assertEquals(false, strFig.hasFlag(FIG_STRING_OFFSET_02_FLAGX, FIG_STRING_FLAGX_2_DEFINED_FX));
      
      StringAuxOperator auxOp = drc.getStrAuxOperator();
      assertEquals(true, format == auxOp.getSubFormat(strFig));
      assertEquals(true, specials == auxOp.getSubSpecial(strFig));
   }

}
