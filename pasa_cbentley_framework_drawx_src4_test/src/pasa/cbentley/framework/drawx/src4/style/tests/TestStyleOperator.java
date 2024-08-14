package pasa.cbentley.framework.drawx.src4.style.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ColorRepo;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.byteobjects.src4.objects.pointer.MergeFactory;
import pasa.cbentley.core.src4.utils.ColorUtils;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.IFlagsToStringDrw;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOFigure;
import pasa.cbentley.framework.drawx.src4.style.IBOStyle;
import pasa.cbentley.framework.drawx.src4.style.StyleFactory;
import pasa.cbentley.framework.drawx.src4.style.StyleOperator;
import pasa.cbentley.framework.drawx.src4.tech.ITechStyle;
import pasa.cbentley.layouter.src4.ctx.IBOTypesLayout;
import pasa.cbentley.layouter.src4.tech.IBOTblr;

public abstract class TestStyleOperator extends TestCaseFrameworkUiPluggedDrawX implements IBOTblr, IBOStyle, ITechGradient, IBOFigure {

   StyleFactory  fac;

   StyleOperator op;

   int           x = 5;

   int           y = 5;

   int           w = 100;

   int           h = 80;

   public TestStyleOperator() {
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, true);
      setTestFlag(TEST_FLAG_19_MANUAL_CHECK_NEW, true);
   }

   public void setupAbstractDrawX() {
      op = drc.getStyleOperator();
      fac = drc.getStyleFactory();
   }

   public void testGetStyleFigure() {

      ByteObject fig = figureFac.getFigEllipse(FULLY_OPAQUE_ORANGE);
      ByteObject style = fac.getStyle(fig);

      testStyleVisually("getStyle_Ellipse", style);
   }

   private void testStyleVisually(String msg, ByteObject style) {
      int imgW = 2 * x + w;
      int imgH = 2 * y + h;
      RgbImage ri = rc.create(imgW, imgH, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      op.drawStyle(g, x, y, w, h, style);

      doImageTest(ri, msg);
   }

   public void test3Layers() {
      ColorRepo colorRepo = new ColorRepo(boc);
      colorRepo.createNewRandom(4564654);

      int pcolor = colorRepo.getBg1();
      int bg2 = colorRepo.getBg2();
      int bg3 = colorRepo.getBg3();

      int ccolor = colorRepo.getContent1();

      int br1 = colorRepo.getBorder1();
      int br2 = colorRepo.getBorder2();
      int br3 = colorRepo.getBorder3();

      ByteObject content = figureFactory.getFigString(FACE_PROPORTIONAL, STYLE_PLAIN, ITechFont.SIZE_3_MEDIUM, ccolor);
      ByteObject anchor = anchorFac.getLeftTop();

      ByteObject margin = tblrFactory.getTBLRPixel(5);
      ByteObject border = tblrFactory.getTBLRPixel(10);
      ByteObject pad = tblrFactory.getTBLRPixel(15);

      ByteObject gradientContent = gradFac.getGradient(bg2, 100, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject bgContent = figureFactory.getFigRect(pcolor, gradientContent);

      ByteObject gradientMargin = gradFac.getGradient(br3, 50, ITechGradient.GRADIENT_TYPE_RECT_02_VERT);
      ByteObject bgMargin = figureFactory.getFigRect(pcolor, gradientMargin);

      int borderColor1 = br1;
      int borderColor2 = br3;
      int arcSize = 4;
      ByteObject gradientBorder = gradFac.getGradient(borderColor2, 100, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject bgBorder = figureFactory.getFigBorder(border, arcSize, arcSize, borderColor1, gradientBorder);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgContent, bgBorder };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_2_CONTENT);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_0_BORDER);

      testStyleVisually("getStyle_3Layers", style);
   }

   public void test4Layers() {
      ColorRepo colorRepo = new ColorRepo(boc);
      colorRepo.createNewRandom(7777);

      int c1 = colorRepo.getBg1();
      int c2 = colorRepo.getBg2();
      int c3 = colorRepo.getBg3();
      int c4 = colorRepo.getContent1();

      ByteObject content = null;
      ByteObject anchor = null;

      ByteObject margin = tblrFactory.getTBLRPixel(5);
      ByteObject border = tblrFactory.getTBLRPixel(8);
      ByteObject pad = tblrFactory.getTBLRPixel(11);

      ByteObject bgMargin = figureFactory.getFigRect(c1);
      ByteObject bgBorder = figureFactory.getFigRect(c2);
      ByteObject bgContent = figureFactory.getFigRect(c3);
      ByteObject bgPadding = figureFactory.getFigRect(c4);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgBorder, bgPadding, bgContent };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_4_BG, ITechStyle.STYLE_ANC_2_CONTENT);

      testStyleVisually("getStyle_4Layers", style);
   }

   public void test4LayersGradients() {
      getRepo(7777);

      w = 240;
      h = 220;
      boolean isReverse = false;
      ByteObject style = get4LayersGradientStyle(isReverse);

      testStyleVisually("getStyle_4LayersGradients", style);
   }

   public void test4LayersForegrounds() {
      getRepo(7777);
      w = 240;
      h = 220;
      boolean isReverse = false;
      ByteObject style = get4LayersForeGrounds(isReverse);

      testStyleVisually("ForegroundsOn4LayersGradients", style);
   }

   private ColorRepo getRepo(int seed) {
      ColorRepo colorRepo = new ColorRepo(boc);
      colorRepo.createNewRandom(seed);
      return colorRepo;
   }

   public void test4LayersGradientsReverse() {
      getRepo(7777);

      w = 240;
      h = 220;
      boolean isReverse = true;
      ByteObject style = get4LayersGradientStyle(isReverse);

      testStyleVisually("getStyle_4LayersGradientsReverse", style);
   }

   public void testBgAndFg() {

      int colorMargin = FULLY_OPAQUE_YELLOW;
      int colorBorder = FULLY_OPAQUE_GREEN;
      int colorPadding = FULLY_OPAQUE_BLUE;
      int colorContent = FULLY_OPAQUE_RED;

      int cg = FULLY_OPAQUE_WHITE;

      ByteObject content = null;
      ByteObject anchor = null;

      ByteObject margin = tblrFactory.getTBLRPixel(20);
      ByteObject border = tblrFactory.getTBLRPixel(20);
      ByteObject pad = tblrFactory.getTBLRPixel(20);

      ByteObject bgMargin = figureFactory.getFigRect(colorMargin);
      ByteObject bgBorder = figureFactory.getFigRect(colorBorder);
      ByteObject bgPadding = figureFactory.getFigRect(colorPadding);
      ByteObject bgContent = figureFactory.getFigRect(colorContent);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgBorder, bgPadding, bgContent };

      ByteObject fgPadding = figureFactory.getFigRect(ColorUtils.getRGBInt(200, 128, 128, 250), true);

      ByteObject[] fgs = new ByteObject[] { fgPadding };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin, fgs);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_4_BG, ITechStyle.STYLE_ANC_2_CONTENT);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_3_PADDING);

      drc.toStringSetToStringFlag(IFlagsToStringDrw.D_FLAG_01_STYLE, true);
      //#debug
      toDLog().pTest("StyleGAnchorContent", style, TestStyleOperator.class, "testBgAndFgs", LVL_05_FINE, false);

      w = 240;
      h = 220;
      testStyleVisually("bgAndFg", style);

   }

   private ByteObject get4LayersGradientStyle(boolean isReverse) {
      int colorMargin = FULLY_OPAQUE_YELLOW;
      int colorBorder = FULLY_OPAQUE_GREEN;
      int colorPadding = FULLY_OPAQUE_BLUE;
      int colorContent = FULLY_OPAQUE_RED;

      int cg = FULLY_OPAQUE_WHITE;

      ByteObject content = null;
      ByteObject anchor = null;

      ByteObject margin = tblrFactory.getTBLRPixel(20);
      ByteObject border = tblrFactory.getTBLRPixel(20);
      ByteObject pad = tblrFactory.getTBLRPixel(20);

      int arcSize = 0;

      ByteObject gradientMargin = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgMargin = figureFactory.getFigBorder(margin, arcSize, arcSize, colorMargin, gradientMargin);

      ByteObject gradientBorder = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgBorder = figureFactory.getFigBorder(border, arcSize, arcSize, colorBorder, gradientBorder);

      ByteObject gradientPadding = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgPadding = figureFactory.getFigBorder(pad, arcSize, arcSize, colorPadding, gradientPadding);

      ByteObject gradientContent = gradFac.getGradient(cg, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject bgContent = figureFactory.getFigRect(colorContent, gradientContent);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgBorder, bgPadding, bgContent };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_4_BG, ITechStyle.STYLE_ANC_2_CONTENT);
      return style;
   }

   
   public void testMergeGradients() {

      boolean isReverse = false;

      int colorBase = FULLY_OPAQUE_BLUE;
      int colorGrad = FULLY_OPAQUE_SKY_GREEN;
      
      ByteObject gradientMargin = gradFac.getGradient(colorGrad, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bg = figureFactory.getFigRect(colorBase, gradientMargin);

      ByteObject style = styleFactory.getStyleFig(bg);
      
      testStyleVisually("MergeGradients_Root", style);
      
      
      int colorGradMerge = FULLY_OPAQUE_PURPLE;
      
      ByteObject gradientMerge = gradFac.getGradient_T_Color(colorGradMerge);
      //how do we know the figure's type? in this case. we assume it is a rect 
      
      ByteObject bgT = figureFactory.getFigRectGradientT(gradientMargin);
      
      ByteObject merge = styleFactory.getStyleFigT(bg);
     
   }
   
   private ByteObject get4LayersForeGrounds(boolean isReverse) {
      int colorMargin = FULLY_OPAQUE_YELLOW;
      int colorBorder = FULLY_OPAQUE_GREEN;
      int colorPadding = FULLY_OPAQUE_BLUE;
      int colorContent = FULLY_OPAQUE_RED;

      int cg = FULLY_OPAQUE_WHITE;

      ByteObject content = null;
      ByteObject anchor = null;

      ByteObject margin = tblrFactory.getTBLRPixel(20);
      ByteObject border = tblrFactory.getTBLRPixel(20);
      ByteObject pad = tblrFactory.getTBLRPixel(20);

      int arcSize = 0;

      ByteObject gradientMargin = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgMargin = figureFactory.getFigBorder(margin, arcSize, arcSize, colorMargin, gradientMargin);

      ByteObject gradientBorder = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgBorder = figureFactory.getFigBorder(border, arcSize, arcSize, colorBorder, gradientBorder);

      ByteObject gradientPadding = gradFac.getGradient(cg, 50, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE, isReverse);
      ByteObject bgPadding = figureFactory.getFigBorder(pad, arcSize, arcSize, colorPadding, gradientPadding);

      ByteObject gradientContent = gradFac.getGradient(cg, 0, ITechGradient.GRADIENT_TYPE_RECT_00_SQUARE);
      ByteObject bgContent = figureFactory.getFigRect(colorContent, gradientContent);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgBorder, bgPadding, bgContent };

      ByteObject fgMargin = figureFactory.getFigRect(ColorUtils.getRGBInt(96, 128, 128, 128), true);
      ByteObject fgBorder = figureFactory.getFigRect(ColorUtils.getRGBInt(150, 250, 128, 128), true);
      ByteObject fgPadding = figureFactory.getFigRect(ColorUtils.getRGBInt(200, 128, 128, 250), true);
      ByteObject fgContent = figureFactory.getFigRect(ColorUtils.getRGBInt(96, 128, 250, 128), true);

      ByteObject[] fgs = new ByteObject[] { fgMargin, fgBorder, fgPadding, fgContent };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin, fgs);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_4_BG, ITechStyle.STYLE_ANC_2_CONTENT);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_6_FG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_7_FG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_8_FG, ITechStyle.STYLE_ANC_2_CONTENT);

      return style;
   }

   private ByteObject getStyleBasic() {
      int colorMargin = FULLY_OPAQUE_YELLOW;
      int colorBorder = FULLY_OPAQUE_GREEN;
      int colorPadding = FULLY_OPAQUE_BLUE;
      int colorContent = FULLY_OPAQUE_RED;

      ByteObject content = null;
      ByteObject anchor = null;

      ByteObject margin = tblrFactory.getTBLRPixel(5);
      ByteObject border = tblrFactory.getTBLRPixel(10);
      ByteObject pad = tblrFactory.getTBLRPixel(15);

      ByteObject bgMargin = figureFactory.getFigRect(colorMargin);
      ByteObject bgBorder = figureFactory.getFigRect(colorBorder);
      ByteObject bgPadding = figureFactory.getFigRect(colorPadding);
      ByteObject bgContent = figureFactory.getFigRect(colorContent);

      ByteObject[] bgs = new ByteObject[] { bgMargin, bgBorder, bgPadding, bgContent };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_1_BG, ITechStyle.STYLE_ANC_1_MARGIN);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_2_BG, ITechStyle.STYLE_ANC_0_BORDER);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_3_BG, ITechStyle.STYLE_ANC_3_PADDING);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_4_BG, ITechStyle.STYLE_ANC_2_CONTENT);

      
      return style;
   }

   public void testMergeMargin() {
      ByteObject content = null;
      ByteObject anchor = null;
      ByteObject margin = tblrFactory.getTBLRPixel(15);
      ByteObject pad = null;
      ByteObject border = null;

      ByteObject bgContent = figureFactory.getFigRect(ColorUtils.getRGBInt(100, 128, 150, 150),true);
      ByteObject[] bgs = null;
      ByteObject[] fgs = new ByteObject[] { bgContent };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin, fgs);
      
      styleOp.setStyleIncomplete(style);
      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_2_CONTENT);

      ByteObject root = getStyleBasic();
      
      w = 200;
      h = 180;

      
      testStyleVisually("MergedMargin_Root", root);
      ByteObject merge = op.mergeStyle(root, style);

      testStyleVisually("MergedMargin_Merge_Margin15GreyContent", merge);
      
      
   }

   public void testIncomplete() {
      ColorRepo colorRepo = getRepo(7777);

      w = 240;
      h = 220;

      ByteObject content = null;
      ByteObject anchor = null;
      ByteObject margin = null;
      ByteObject pad = null;
      ByteObject border = null;

      int colorGrey = ColorUtils.getRGBInt(128, 128, 128, 128);
      ByteObject bg = figureFactory.getFigRect(colorGrey);
      bg.setFlag(FIG__OFFSET_02_FLAG, FIG_FLAGP_5_IGNORE_ALPHA, false);
      bg.setFlag(FIG__OFFSET_03_FLAGP, FIG_FLAGP_3_OPAQUE, false);

      ByteObject[] bgs = null;
      ByteObject[] fgs = new ByteObject[] { bg };

      ByteObject style = styleFactory.getStyle(bgs, content, anchor, pad, border, margin, fgs);

      style.setFlag(IBOStyle.STYLE_OFFSET_5_FLAG_X, IBOStyle.STYLE_FLAG_X_1_INCOMPLETE, true);

      //#debug
      style.toStringSetName("GreyIncomplete");

      ByteObject root = get4LayersGradientStyle(false);

      ByteObject merge = op.mergeStyle(root, style);

      testStyleVisually("greyMergedOver_4LayersGradients_DefaultBorder", merge);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_1_MARGIN);
      root = get4LayersGradientStyle(true);
      merge = op.mergeStyle(root, style);

      testStyleVisually("greyMergedOver_4LayersGradientsReverse_FG_Margin", merge);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_0_BORDER);
      root = get4LayersGradientStyle(true);
      merge = op.mergeStyle(root, style);
      testStyleVisually("greyMergedOver_4LayersGradientsReverse_FG_Border", merge);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_3_PADDING);
      root = get4LayersGradientStyle(true);
      merge = op.mergeStyle(root, style);
      testStyleVisually("greyMergedOver_4LayersGradientsReverse_FG_Padding", merge);

      styleOp.setGAnchors(style, IBOStyle.STYLE_FLAG_B_5_FG, ITechStyle.STYLE_ANC_2_CONTENT);
      drc.toStringSetToStringFlag(IFlagsToStringDrw.D_FLAG_01_STYLE, true);
      //#debug
      toDLog().pTest("StyleGAnchorContent", style, TestStyleOperator.class, "testIncomplete", LVL_05_FINE, false);

      root = get4LayersGradientStyle(true);
      merge = op.mergeStyle(root, style);
      testStyleVisually("greyMergedOver_4LayersGradientsReverse_FG_Content", merge);

   }
}
