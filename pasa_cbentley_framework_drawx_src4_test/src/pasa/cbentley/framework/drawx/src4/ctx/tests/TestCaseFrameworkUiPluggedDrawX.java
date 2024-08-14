package pasa.cbentley.framework.drawx.src4.ctx.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ColorFunctionFactory;
import pasa.cbentley.byteobjects.src4.objects.color.ColorIterator;
import pasa.cbentley.byteobjects.src4.objects.color.FilterFactory;
import pasa.cbentley.byteobjects.src4.objects.color.FilterOperator;
import pasa.cbentley.byteobjects.src4.objects.color.GradientFactory;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.byteobjects.src4.objects.function.FunctionFactory;
import pasa.cbentley.byteobjects.src4.objects.pointer.MergeFactory;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.DrwCtx;
import pasa.cbentley.framework.drawx.src4.ctx.IBOTypesDrawX;
import pasa.cbentley.framework.drawx.src4.engine.RgbCache;
import pasa.cbentley.framework.drawx.src4.factories.AnchorFactory;
import pasa.cbentley.framework.drawx.src4.factories.ArtifactFactory;
import pasa.cbentley.framework.drawx.src4.factories.BoxFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureFactory;
import pasa.cbentley.framework.drawx.src4.factories.FigureOperator;
import pasa.cbentley.framework.drawx.src4.factories.MaskFactory;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOMask;
import pasa.cbentley.framework.drawx.src4.tech.ITechFigure;
import pasa.cbentley.framework.testing.gui.engine.TestCaseFrameworkUiPlugged;
import pasa.cbentley.layouter.src4.engine.LayoutOperator;
import pasa.cbentley.layouter.src4.engine.TblrFactory;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestCaseFrameworkUiPluggedDrawX extends TestCaseFrameworkUiPlugged implements IBOTypesDrawX, IBOMask, IColors, ITechFont, ITechGradient, ITechFigure {

   protected DrwCtx               drc;

   protected AnchorFactory        facAnchor;

   protected BoxFactory           facBox;

   protected ColorFunctionFactory facColorFun;

   protected FilterFactory        facFilter;

   protected FunctionFactory      facFunction;

   protected GradientFactory      facGradient;

   protected ArtifactFactory      facArtifact;

   protected MaskFactory          facMask;

   protected MergeFactory         facMergeMask;

   protected TblrFactory          facTBLR;

   protected FigureFactory        figureFac;

   protected LayoutOperator       layOp;

   protected FigureOperator       opFigure;

   protected FilterOperator       opFilter;

   protected RgbCache             rgbCache;

   public TestCaseFrameworkUiPluggedDrawX() {
   }

   public ColorIterator getColorIterator(int color, ByteObject grad, int gradSize) {
      return drc.getColorFunctionFactory().getColorIterator(color, grad, gradSize);
   }

   public void setupAbstract() {
      super.setupAbstract();
      drc = plugUI.getDC();
      facBox = drc.getBoxFactory();
      facColorFun = drc.getColorFunctionFactory();
      opFigure = drc.getFigureOperator();
      figureFac = drc.getFigureFactory();
      facFunction = boc.getFunctionFactory();
      facFilter = boc.getFilterFactory();
      opFilter = boc.getFilterOperator();
      facGradient = drc.getGradientFactory();
      layOp = lac.getLayoutOperator();
      facMask = drc.getMaskFactory();
      facMergeMask = boc.getMergeFactory();
      rgbCache = drc.getCache();
      facTBLR = drc.getTblrFactory();
      facAnchor = drc.getAnchorFactory();
      facArtifact = drc.getArtifactFactory();
      setupAbstractDrawX();
   }

   /**
    * Called at the end of {@link TestCaseBentley#setupAbstract()}
    */
   public abstract void setupAbstractDrawX();

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestCaseFrameworkUiPluggedDrawX.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestCaseFrameworkUiPluggedDrawX.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
