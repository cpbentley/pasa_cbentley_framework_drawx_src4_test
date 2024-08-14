package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.objects.color.ITechGradient;
import pasa.cbentley.framework.drawx.src4.engine.GraphicsX;
import pasa.cbentley.framework.drawx.src4.engine.RgbImage;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;

public abstract class TestStringerFxMask extends TestStringerAbstract {



   public void testMaskInFigure() {

      //MASK Definition
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 60, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      int maskPreset = MASK_PRESET_0HAL0;
      ByteObject filter = null;
      ByteObject mask = facMask.getMaskPreset(maskPreset, filter, figure);

      int face = FACE_MONOSPACE;
      int style = STYLE_PLAIN;
      int size = SIZE_4_LARGE;
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject scale = null;
      ByteObject fx = null;
      ByteObject anchor = anchorFac.getCenterCenter();

      ByteObject strFig = facFigure.getFigString(face, style, size, color, fx, mask, scale, anchor);

      //scope text

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(margin, margin, 400, 100);
      st.setString("Mask was defined\nin the ByteObject Figure.\nNot in the Fx");
      st.setTextFigure(strFig);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.buildFxAndMeter();

      genericTestImg("MaskInFigure", st);
   }

   private Stringer getStringerMaskScope(int scope) {

      //Fx Mask Definition
      ByteObject grad = facGradient.getGradient(FULLY_OPAQUE_BLUE, 60, ITechGradient.GRADIENT_TYPE_RECT_01_HORIZ);
      ByteObject figure = facFigure.getFigRect(FULLY_OPAQUE_PURPLE, grad);
      int maskPreset = MASK_PRESET_0HAL0;
      ByteObject filter = null;
      ByteObject maskFx = facMask.getMaskPreset(maskPreset, filter, figure);
      ByteObject fx = facStringFx.getFxMask(maskFx, scope);

      //

      int face = FACE_MONOSPACE;
      int style = STYLE_PLAIN;
      int size = SIZE_4_LARGE;
      int color = FULLY_OPAQUE_ORANGE;
      ByteObject scale = null;
      ByteObject mask = null;
      ByteObject anchor = anchorFac.getCenterCenter();

      ByteObject strFig = facFigure.getFigString(face, style, size, color, fx, mask, scale, anchor);

      //scope text

      Stringer st = new Stringer(dc);
      st.setAreaXYWH(margin, margin, 400, 100);
      st.setString("Several Many\nNicely Masked \n Word Chars");
      st.setTextFigure(strFig);
      st.setDirectiveNewLine(SPECIALS_NEWLINE_3_WORK);
      st.buildFxAndMeter();

      return st;
   }

   public void testMaskScope_0_Text() {
      Stringer st = getStringerMaskScope(ITechStringer.FX_SCOPE_0_TEXT);
      genericTestImg("Mask_Scope_0_Text", st);
   }

   public void testMaskScope_1_Char() {
      Stringer st = getStringerMaskScope(ITechStringer.FX_SCOPE_1_CHAR);
      genericTestImg("Mask_Scope_1_Char", st);
   }
   public void testMaskScope_2_Word() {
      Stringer st = getStringerMaskScope(ITechStringer.FX_SCOPE_2_WORD);
      genericTestImg("Mask_Scope_0_Word", st);
   }
   public void testMaskScope_3_Para() {
      Stringer st = getStringerMaskScope(ITechStringer.FX_SCOPE_3_PARA);
      genericTestImg("Mask_Scope_0_Para", st);
   }


   /**
    * Draws the whole Stringer using {@link Stringer#draw(GraphicsX)}
    * <br>
    * <br>
    * 
    * @param name
    * @param st
    * @param w
    * @param h
    */
   public void genericTestImg(String name, Stringer st, int w, int h) {

      RgbImage ri = rc.create(w, h, FULLY_OPAQUE_BLACK);
      GraphicsX g = ri.getGraphicsX(GraphicsX.MODE_1_IMAGE);

      st.draw(g);

      doImageTest(ri, name);
   }
}
