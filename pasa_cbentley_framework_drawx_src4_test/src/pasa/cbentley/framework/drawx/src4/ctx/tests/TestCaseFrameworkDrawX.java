package pasa.cbentley.framework.drawx.src4.ctx.tests;

import pasa.cbentley.byteobjects.src4.objects.color.ColorFunctionFactory;
import pasa.cbentley.byteobjects.src4.objects.color.GradientFactory;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.byteobjects.src4.objects.function.FunctionFactory;
import pasa.cbentley.byteobjects.src4.objects.pointer.MergeFactory;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.DrwCtx;
import pasa.cbentley.framework.drawx.src4.ctx.IBOTypesDrawX;
import pasa.cbentley.framework.drawx.src4.engine.RgbCache;
import pasa.cbentley.framework.drawx.src4.factories.BoxFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.MaskFactory;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOMask;
import pasa.cbentley.framework.drawx.src4.tech.ITechFigure;
import pasa.cbentley.framework.testing.core.engine.TestCaseFramework;
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.src4.engine.LayoutOperator;
import pasa.cbentley.layouter.src4.engine.SizerFactory;
import pasa.cbentley.layouter.src4.engine.TblrFactory;

public abstract class TestCaseFrameworkDrawX extends TestCaseFramework implements IBOTypesDrawX, IBOMask, IColors, ITechFont, ITechGradient, ITechFigure {

   protected BoxFactory           facBox;

   protected ColorFunctionFactory facColorFun;

   protected DrwCtx               drc;

   protected FigureOperator       opFigure;

   protected FigureFactory        figureFac;

   protected FunctionFactory      facFunction;

   protected GradientFactory      facGradient;

   protected LayoutOperator       layOp;

   protected MaskFactory          facMask;

   protected MergeFactory     facMergeMask;

   protected RgbCache             rgbCache;

   protected TblrFactory          facTBLR;

   protected SizerFactory         facSizer;

   private LayouterCtx            lac;

   public TestCaseFrameworkDrawX() {
   }

   public void setupAbstract() {
      super.setupAbstract();
      lac = new LayouterCtx(boc);
      drc = new DrwCtx(cdc, lac);
      facSizer = lac.getFactorySizer();
      facBox = drc.getBoxFactory();
      facColorFun = drc.getColorFunctionFactory();
      opFigure = drc.getFigureOperator();
      figureFac = drc.getFigureFactory();
      facFunction = boc.getFunctionFactory();
      facGradient = drc.getGradientFactory();
      layOp = lac.getLayoutOperator();
      facMask = drc.getMaskFactory();
      facMergeMask = boc.getMergeFactory();
      rgbCache = drc.getCache();
      facTBLR = lac.getTblrFactory();
      setupAbstractDrawX();
   }

   public abstract void setupAbstractDrawX();

   public String toStringColor(int c) {
      return "(" + ((c >> 24) & 0xFF) + "," + ((c >> 16) & 0xFF) + "," + ((c >> 8) & 0xFF) + "," + (c & 0xFF) + ")";
   }
}
