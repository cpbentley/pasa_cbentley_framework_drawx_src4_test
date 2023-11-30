package pasa.cbentley.framework.drawx.src4.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.extra.MergeMaskFactory;
import pasa.cbentley.byteobjects.src4.functions.FunctionFactory;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.color.ColorFunctionFactory;
import pasa.cbentley.framework.drawx.src4.color.ColorIterator;
import pasa.cbentley.framework.drawx.src4.ctx.DrwCtx;
import pasa.cbentley.framework.drawx.src4.ctx.IBOTypesDrw;
import pasa.cbentley.framework.drawx.src4.engine.RgbCache;
import pasa.cbentley.framework.drawx.src4.factories.BoxFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.GradientFactory;
import pasa.cbentley.framework.drawx.src4.factories.MaskFactory;
import pasa.cbentley.framework.drawx.src4.factories.TblrFactory;
import pasa.cbentley.framework.drawx.src4.tech.ITechFigure;
import pasa.cbentley.framework.drawx.src4.tech.ITechGradient;
import pasa.cbentley.framework.drawx.src4.tech.ITechMask;
import pasa.cbentley.framework.testing.core.engine.TestCaseFramework;
import pasa.cbentley.framework.testing.gui.engine.IUiPlug;
import pasa.cbentley.framework.testing.gui.engine.TestCaseFrameworkUiPlugged;
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.src4.engine.LayoutOperator;

public abstract class TestCaseDrawX extends TestCaseFramework implements IBOTypesDrw, ITechMask, IColors, ITechFont, ITechGradient, ITechFigure {

   protected BoxFactory           facBox;

   protected ColorFunctionFactory facColorFun;

   protected DrwCtx               drc;

   protected FigureOperator       opFigure;

   protected FigureFactory        figureFac;

   protected FunctionFactory      facFunction;

   protected GradientFactory      facGradient;

   protected LayoutOperator       layOp;

   protected MaskFactory          facMask;

   protected MergeMaskFactory     facMergeMask;

   protected RgbCache             rgbCache;

   protected TblrFactory          facTBLR;

   private LayouterCtx            lac;

   public TestCaseDrawX() {
   }

   public ColorIterator getColorIterator(int color, ByteObject grad, int gradSize) {
      return drc.getColorFunctionFactory().getColorIterator(color, grad, gradSize);
   }

   public void setupAbstract() {
      super.setupAbstract();
      lac = new LayouterCtx(boc);
      drc = new DrwCtx(cdc, lac);
      facBox = drc.getBoxFactory();
      facColorFun = drc.getColorFunctionFactory();
      opFigure = drc.getFigureOperator();
      figureFac = drc.getFigureFactory();
      facFunction = boc.getFunctionFactory();
      facGradient = drc.getGradientFactory();
      layOp = lac.getLayoutOperator();
      facMask = drc.getMaskFactory();
      facMergeMask = boc.getMergeMaskFactory();
      rgbCache = drc.getCache();
      facTBLR = drc.getTblrFactory();
      setupAbstractDrawX();
   }

   public abstract void setupAbstractDrawX();

   public String toStringColor(int c) {
      return "(" + ((c >> 24) & 0xFF) + "," + ((c >> 16) & 0xFF) + "," + ((c >> 8) & 0xFF) + "," + (c & 0xFF) + ")";
   }
}
