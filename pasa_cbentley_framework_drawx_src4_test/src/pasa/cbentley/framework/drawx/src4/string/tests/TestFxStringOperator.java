package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseDrawXPlugged;
import pasa.cbentley.framework.drawx.src4.factories.interfaces.IBOTblr;
import pasa.cbentley.framework.drawx.src4.string.FxStringFactory;
import pasa.cbentley.framework.drawx.src4.string.FxStringOperator;
import pasa.cbentley.framework.drawx.src4.string.interfaces.IBOFxStr;

public abstract class TestFxStringOperator extends TestCaseDrawXPlugged implements IBOTblr, IBOFxStr {

   FxStringFactory  fac;

   FxStringOperator op;

   public void setupAbstractDrawX() {
      op = drc.getFxStringOperator();
      fac = drc.getFxStringFactory();
   }

   public void testMergeWithBgFigure() {
      ByteObject fxSelect = facStringFx.getFxEffectColor(FULLY_OPAQUE_GREEN);

      ByteObject figBgFx = facFigure.getFigRect(FULLY_OPAQUE_SKY_BLUE);
      facStringFx.addFxFigure(fxSelect, figBgFx);

      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_1_UNDEFINED_FONT_FACE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_2_UNDEFINED_FONT_STYLE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_3_UNDEFINED_FONT_SIZE));
      assertEquals(false, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_4_UNDEFINED_COLOR));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_5_UNDEFINED_SCOPE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_8_INCOMPLETE));
      assertEquals(FX_SCOPE_1_CHAR, fxSelect.get1(FX_OFFSET_05_SCOPE_FX1));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_2_FIGURE));
      assertEquals(false, fxSelect.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_3_MASK));

      ByteObject merge = fxSelect;

      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);

      ByteObject root = facStringFx.createFxFromFigure(strFig);
      assertEquals(FX_SCOPE_0_TEXT, root.get1(FX_OFFSET_05_SCOPE_FX1));

      ByteObject merged = op.mergeTxtEffects(root, merge);

      //#debug
      toDLog().pTest("Root TextFX", root, TestFxStringOperator.class, "testMerge", LVL_05_FINE, false);

      //#debug
      toDLog().pTest("Select TextFX", fxSelect, TestFxStringOperator.class, "testMerge", LVL_05_FINE, false);

      //#debug
      toDLog().pTest("Merged TextFX", merged, TestFxStringOperator.class, "testMerge", LVL_05_FINE, false);

      assertEquals(FULLY_OPAQUE_GREEN, merged.get4(FX_OFFSET_09_COLOR4));

      assertEquals(figBgFx, merged.getSubFirst(TYPE_050_FIGURE));

      assertEquals(FX_SCOPE_0_TEXT, merged.get1(FX_OFFSET_05_SCOPE_FX1));

      //check flags
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_1_UNDEFINED_FONT_FACE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_2_UNDEFINED_FONT_STYLE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_3_UNDEFINED_FONT_SIZE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_4_UNDEFINED_COLOR));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_5_UNDEFINED_SCOPE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_8_INCOMPLETE));

      assertEquals(true, merged.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_2_FIGURE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_3_MASK));
   }

   public void testMergeWithMask() {

      ByteObject fxSelect = facStringFx.getFxEffectColor(FULLY_OPAQUE_GREEN);
      facStringFx.setFontStyle(fxSelect, STYLE_BOLD);

      ByteObject mask = facMask.getMaskGradient(FULLY_OPAQUE_SKY_BLUE, FULLY_OPAQUE_GREY);
      facStringFx.addFxMask(fxSelect, mask);

      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_1_UNDEFINED_FONT_FACE));
      assertEquals(false, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_2_UNDEFINED_FONT_STYLE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_3_UNDEFINED_FONT_SIZE));
      assertEquals(false, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_4_UNDEFINED_COLOR));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_5_UNDEFINED_SCOPE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_8_INCOMPLETE));

      assertEquals(false, fxSelect.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_2_FIGURE));
      assertEquals(true, fxSelect.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_3_MASK));

      assertEquals(FX_SCOPE_1_CHAR, fxSelect.get1(FX_OFFSET_05_SCOPE_FX1));

      ByteObject merge = fxSelect;
      ByteObject strFig = facFigure.getFigString(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM, FULLY_OPAQUE_ORANGE);
      ByteObject root = facStringFx.createFxFromFigure(strFig);
      assertEquals(FX_SCOPE_0_TEXT, root.get1(FX_OFFSET_05_SCOPE_FX1));

      ByteObject merged = op.mergeTxtEffects(root, merge);
      

      assertEquals(mask, merged.getSubFirst(TYPE_058_MASK));

      assertEquals(FX_SCOPE_0_TEXT, merged.get1(FX_OFFSET_05_SCOPE_FX1));
      assertEquals(FACE_MONOSPACE, merged.get1(FX_OFFSET_06_FACE1));
      assertEquals(STYLE_BOLD, merged.get1(FX_OFFSET_07_STYLE1));
      assertEquals(SIZE_3_MEDIUM, merged.get1(FX_OFFSET_08_SIZE1));
      assertEquals(FULLY_OPAQUE_GREEN, merged.get4(FX_OFFSET_09_COLOR4));

      //check flags
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_1_UNDEFINED_FONT_FACE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_2_UNDEFINED_FONT_STYLE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_3_UNDEFINED_FONT_SIZE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_4_UNDEFINED_COLOR));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_5_UNDEFINED_SCOPE));
      assertEquals(false, merged.hasFlag(FX_OFFSET_02_FLAGX, FX_FLAGX_8_INCOMPLETE));

      assertEquals(false, merged.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_2_FIGURE));
      assertEquals(true, merged.hasFlag(FX_OFFSET_03_FLAGY, FX_FLAGY_3_MASK));
   }
}
