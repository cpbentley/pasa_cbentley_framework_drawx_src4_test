package pasa.cbentley.framework.drawx.src4.factories.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOArtifact;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.ITechArtifact;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.layouter.src4.engine.CodedCodePageFigure;
import pasa.cbentley.layouter.src4.tech.ITechLayout;

public abstract class TestFigureRectangle extends TestFigureAbsract {

   public void testRectangle_Green() {
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN);

      testImageFigure("Rectangle_Green", rect);

   }

   public void testRectangle_Green_Fill30() {
      ByteObject rect = fac.getFigRectFill(FULLY_OPAQUE_GREEN, 30);

      testImageFigure("Rectangle_Green_Fill30", rect);

   }

   public void testRectangle_Green_Fill30_Round20() {
      ByteObject rect = fac.getFigRectFill(FULLY_OPAQUE_GREEN, 30, 20, 20);

      testImageFigure("Rectangle_Green_Fill30_Round20", rect);

   }

   public void testRectangle_Gradient() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);

      testImageFigure("Rectangle_Gradient_Cursor0", rect);

   }

   public void testRectangle_Gradient_Fill20() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRectFill(FULLY_OPAQUE_GREEN, grad, 20);

      testImageFigure("Rectangle_Gradient_Fill20", rect);

   }

   public void testRectangle_Gradient_Round2020() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, 20, 20, grad);

      testImageFigure("Rectangle_Gradient_Cursor0_Round2020", rect);

   }

   public void testRectangle_Gradient_Round2020_FillArea() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, 20, 20, grad);

      testImageFigure("Rectangle_Gradient_Cursor0_Round2020_FillArea", rect);

   }

   public void testRectangle_Gradient_Cursor20_Round4050_FillArea() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 20, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      testImageFigure("Rectangle_Gradient_Cursor20_Round2020_FillArea", rect);

   }

   public void testRectangle_Gradient_Cursor100_Round4050_FillArea() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 100, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      testImageFigure("Rectangle_Gradient_Cursor100_Round2020_FillArea", rect);

   }

   public void testRectangle_Gradient_Merge_Color() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 100, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rectRoot = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      ByteObject rectMerge = fac.getFigRect_T_Color(FULLY_OPAQUE_PINK);

      ByteObject res = rectRoot.mergeByteObject(rectMerge);

      testImageFigure("Rectangle_Gradient_Merge_TColorPink", res);

   }
   
   public void testRectangle_Gradient_Merge_Cursor() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 100, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rectRoot = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      ByteObject gradMerge = facGradient.getGradient_T_Cursor(50);
      
      ByteObject rectMerge = fac.getFigRect_T_Gradient(gradMerge);

      ByteObject res = rectRoot.mergeByteObject(rectMerge);

      testImageFigure("Rectangle_Gradient_Merge_Cursor50", res);

   }
   
   public void testRectangle_Gradient_Merge_Type2() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 100, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rectRoot = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      ByteObject gradMerge = facGradient.getGradient_T_Type(2);
      
      ByteObject rectMerge = fac.getFigRect_T_Gradient(gradMerge);

      ByteObject res = rectRoot.mergeByteObject(rectMerge);

      testImageFigure("Rectangle_Gradient_Merge_Type2", res);

   }
   
   public void testRectangle_Gradient_Merge_Step() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 100, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rectRoot = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      ByteObject gradMerge = facGradient.getGradient_T_Step(3);
      
      ByteObject rectMerge = fac.getFigRect_T_Gradient(gradMerge);

      ByteObject res = rectRoot.mergeByteObject(rectMerge);

      testImageFigure("Rectangle_Gradient_Merge_Step3", res);

   }
   
   public void testRectangle_Gradient_Merge_3rdColor() {

      ByteObject grad = facGradient.getGradientFill(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rectRoot = fac.getFigRect(FULLY_OPAQUE_GREEN, 40, 50, grad);

      //#debug
      toDLog().pTest("msg", rectRoot, TestFigureRectangle.class, "testRectangle_Gradient_Merge_3rdColor", LVL_05_FINE, false);
      ByteObject gradMerge = facGradient.getGradient_T_ThirdColor(FULLY_OPAQUE_YELLOW);
      
      ByteObject rectMerge = fac.getFigRect_T_Gradient(gradMerge);

      ByteObject res =  rectRoot.mergeByteObject(rectMerge);

      testImageFigure("Rectangle_GradientGreenBlue_Merge_3rdColorYellow", res);

   }

   public void testRectangle_Gradient_RoundSizerDiv2() {

      ByteObject sizer = facSizer.getSizerDivide(2);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, sizer, sizer, grad);

      testImageFigure("Rectangle_Gradient_RoundSizerDiv2", rect);

   }

   public void testRectangle_Gradient_RoundSizer() {

      ByteObject sizer = facSizer.getSizerRatio100(10);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, sizer, sizer, grad);

      testImageFigure("Rectangle_Gradient_RoundRatio10", rect);

   }

   public void testRectangle_Gradient_FillerSizer() {

      ByteObject sizer = facSizer.getSizerRatio100EtalonFun(50, ITechLayout.ET_FUN_10_MIN_HALF);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject rect = fac.getFigRectFiller(FULLY_OPAQUE_GREEN, sizer, grad);

      testImageFigure("Rectangle_Gradient_FillterSize50PerCent", rect);

   }

   public void testRectangle_Sub() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject grad1 = facGradient.getGradient(FULLY_OPAQUE_PINK, 0, GRADIENT_TYPE_RECT_00_SQUARE);

      //40 30 no need to code low values
      ByteObject box1 = facBox.getBoxRightBot(40, 30);
      ByteObject rectSub1 = fac.getFigRect(FULLY_OPAQUE_GREY, grad1, box1);

      ByteObject[] subs = new ByteObject[] { rectSub1 };

      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, grad, null, null, subs);

      testImageFigure("Rectangle_Sub_BottomRight4030", rect);

   }

   public void testRectangle_Round_Artifact() {
      ByteObject sizer = facSizer.getSizerRatio100(20);

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_02_VERT, 5, FULLY_OPAQUE_GREY);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, sizer, sizer, grad);

      int type = 0;
      int aw = 5;
      int ah = 5;
      int spac = 0;
      boolean rw = false;
      boolean rh = false;
      boolean rs = false;
      ByteObject artifact = facArtifact.getArtifact(type, aw, ah, spac, rw, rh, rs);
      artifact.set4(IBOArtifact.ARTIFACT_OFFSET_07_SEED4, 444);

      op.addArtifact(rect, artifact);

      testImageFigure("RectangleRound_Gradient_Artifact", rect);
   }

   public void testRectangle_Artifact() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 50, GRADIENT_TYPE_RECT_02_VERT, 5, FULLY_OPAQUE_GREY);
      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, grad);

      int type = 1;
      int aw = 5;
      int ah = 6;
      int spac = 4;
      boolean rw = false;
      boolean rh = false;
      boolean rs = false;
      boolean rf = false;
      int figType = ITechArtifact.ART_FIG_0_RECT;
      long seed = 444;
      ByteObject artifact = facArtifact.getArtifact(type, aw, ah, spac, figType, rw, rh, rs, rf, seed);

      op.addArtifact(rect, artifact);

      testImageFigure("Rectangle_Gradient_Artifact", rect);
   }

   public void testRectangle_Subs2() {

      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 0, GRADIENT_TYPE_RECT_00_SQUARE);

      ByteObject grad1 = facGradient.getGradient(FULLY_OPAQUE_PINK, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject grad2 = facGradient.getGradient(FULLY_OPAQUE_ORANGE, 0, GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject grad3 = facGradient.getGradient(FULLY_OPAQUE_YELLOW, 0, GRADIENT_TYPE_RECT_00_SQUARE);

      CodedCodePageFigure coder = layOp.getCodePageFigure();
      int encoded1 = coder.encodeRatioMinWHSizee(10);

      ByteObject box1 = facBox.getBoxLeftCenter(encoded1, encoded1);
      ByteObject rectSub1 = fac.getFigRect(FULLY_OPAQUE_GREY, grad1, box1);

      int encoded2 = coder.encodeRatioMinWHSizee(20);
      ByteObject box2 = facBox.getBoxCenterCenter(encoded2, encoded2);
      ByteObject rectSub2 = fac.getFigRect(FULLY_OPAQUE_GREY, grad2, box2);

      int encoded3 = coder.encodeRatioMinWHSizee(30);
      ByteObject box3 = facBox.getBoxRightCenter(encoded3, encoded3);
      ByteObject rectSub3 = fac.getFigRect(FULLY_OPAQUE_GREY, grad3, box3);

      ByteObject[] subs = new ByteObject[] { rectSub1, rectSub2, rectSub3 };

      ByteObject rect = fac.getFigRect(FULLY_OPAQUE_GREEN, grad, null, null, subs);

      testImageFigure("Rectangle_Sub_Center_LeftCenterRightRatio102030", rect);

   }
}
