package pasa.cbentley.framework.drawx.src4.test.swing.string;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.drawx.src4.string.tests.TestStringer;
import pasa.cbentley.framework.testing.core.ctx.TestFrameworkCtx;
import pasa.cbentley.framework.testing.gui.engine.FrameworkPlugAbstractGui;
import pasa.cbentley.framework.testing.gui.swing.ConfigPlugSwingDef;
import pasa.cbentley.framework.testing.gui.swing.FrameworkPlugGuiSwing;
import pasa.cbentley.framework.testing.gui.swing.ctx.TestFrameworkUiSwingCtx;
import pasa.cbentley.testing.ctx.TestCtx;

public class TestStringerSwing extends TestStringer {


   public TestStringerSwing() {
      setTestFlag(TEST_FLAG_01_PRINT_ANYWAYS, false);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, true);
      setTestFlag(TEST_FLAG_05_SHOW_OUT_INIT, false);
      setTestFlag(TEST_FLAG_08_DEBUG_METHOD_NAMES, false);

      //
      setTestFlag(TEST_FLAG_17_IGNORE_OLD_IMAGES, false);
      setTestFlag(TEST_FLAG_18_MANUAL_CHECK, false);

   }

   public FrameworkPlugAbstractGui createFrameworkPlug(TestFrameworkCtx tfc) {
      return new FrameworkPlugGuiSwing((TestFrameworkUiSwingCtx) tfc);
   }

   protected TestCtx createTestCtx() {
      return new TestFrameworkUiSwingCtx(boc);
   }
   
   public void testFxMaskScopeWord() {
      super.testFxMaskScopeWord();
   }
   
   public void test4LinesAppend() {
      super.test4LinesAppend();
   }
   
   public void testDrawOffsets() {
      super.testDrawOffsets();
   }

   public void testFxScaleFitFirst() {
      super.testFxScaleFitFirst();
   }

   public void testTrim3Lines() {
      super.testTrim3Lines();
   }
   
   public void testAddLayer() {
      super.testAddLayer();
   }
   
   public void testFxMaskedBasic() {
      super.testFxMaskedBasic();
   }

   public void testDrawScaled() {
      super.testDrawScaled();
   }
   
   public void testSingleLine2OverlayStyles() {
      super.testSingleLine2OverlayStyles();
   }
   
   public void testCharWidthNewLine() {
      super.testCharWidthNewLine();
   }

   public void testTrimSingleLine() {
      super.testTrimSingleLine();
   }

   public void testTrimFitHeight() {
      super.testTrimFitHeight();
   }

   public void testFxSelectLayerSimple() {
      super.testFxSelectLayerSimple();
   }
   
   public void testFormatWidth30() {
      super.testFormatWidth30();

   }

   public void testBreakWidthSpacesAvoided() {
      super.testBreakWidthSpacesAvoided();
   }

   public void testBreakWidthRelativeChars() {
      super.testBreakWidthRelativeChars();
   }

   public void testWordIntervals() {
      super.testWordIntervals();
   }

   public void testFxDynamic() {
      super.testFxDynamic();
   }
   
   public void testMonospace() {
      super.testMonospace();
   }
   
   public void testJustifySimple() {
      super.testJustifySimple();
   }

   public void testMultiLines_IvyGreen() {
      super.testMultiLines_IvyGreen();
   }

   public void testFormatWidthBorderCases() {
      super.testFormatWidthBorderCases();
   }

   public void testMultiLines_Simple() {
      super.testMultiLines_Simple();
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, TestStringerSwing.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, TestStringerSwing.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
