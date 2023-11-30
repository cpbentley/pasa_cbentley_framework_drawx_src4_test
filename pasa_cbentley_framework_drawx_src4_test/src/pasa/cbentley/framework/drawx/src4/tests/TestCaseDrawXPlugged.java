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
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbCache;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.factories.AnchorFactory;
import pasa.cbentley.framework.drawx.src4.factories.BoxFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.GradientFactory;
import pasa.cbentley.framework.drawx.src4.factories.MaskFactory;
import pasa.cbentley.framework.drawx.src4.factories.TblrFactory;
import pasa.cbentley.framework.drawx.src4.tech.ITechFigure;
import pasa.cbentley.framework.drawx.src4.tech.ITechGradient;
import pasa.cbentley.framework.drawx.src4.tech.ITechMask;
import pasa.cbentley.framework.testing.gui.engine.TestCaseFrameworkUiPlugged;
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.src4.engine.LayoutOperator;
import pasa.cbentley.testing.engine.TestCaseBentley;

public abstract class TestCaseDrawXPlugged extends TestCaseFrameworkUiPlugged implements IBOTypesDrw, ITechMask, IColors, ITechFont, ITechGradient, ITechFigure {

   public TestCaseDrawXPlugged() {
   }

   protected BoxFactory           facBox;

   protected AnchorFactory        facAnchor;

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

   public ColorIterator getColorIterator(int color, ByteObject grad, int gradSize) {
      return drc.getColorFunctionFactory().getColorIterator(color, grad, gradSize);
   }

   public void setupAbstract() {
      super.setupAbstract();
      drc = uiframework.getDC();
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
      facAnchor = drc.getAnchorFactory();
      setupAbstractDrawX();
   }

   /**
    * Called at the end of {@link TestCaseBentley#setupAbstract()}
    */
   public abstract void setupAbstractDrawX();

   public void genericTestFigure(String name, ByteObject figure, int w, int h) {

      RgbImage ri = rc.create(w + 10, h + 10, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      figureOp.paintFigure(g, 5, 5, w, h, figure);
      doImageTest(ri, name);
   }

   public void genericTestFigure(String name, ByteObject[] figures, int w, int h) {
      int totalW = w;
      int totalH = figures.length * (h + 5);

      RgbImage ri = rc.create(totalW + 10, totalH + 10, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);
      int dy = 5;
      for (int i = 0; i < figures.length; i++) {

         figureOp.paintFigure(g, 5, dy, w, h, figures[i]);
         dy += (h + 5);
      }
      doImageTest(ri, name);
   }
}
