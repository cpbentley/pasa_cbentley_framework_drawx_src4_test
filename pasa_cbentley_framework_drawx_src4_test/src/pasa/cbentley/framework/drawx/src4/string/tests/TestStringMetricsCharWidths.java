package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.core.src4.utils.StringUtils;
import pasa.cbentley.framework.coredraw.src4.interfaces.IMFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.ctx.tests.TestCaseFrameworkUiPluggedDrawX;
import pasa.cbentley.framework.drawx.src4.string.interfaces.ITechStringer;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetricsCharWidths extends TestCaseFrameworkUiPluggedDrawX implements ITechFont, ITechStringer {

   public TestStringMetricsCharWidths() {
      setTestFlag(TEST_FLAG_08_DEBUG_METHOD_NAMES, true);
      setTestFlag(TEST_FLAG_03_HIDE_OUT_SUCCESSES, false);
   }

   public void setupAbstractDrawX() {
   }

   public void testZCharWidthMono() {

      IMFont f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(7, f.charWidth('|'));
      assertEquals(7, f.charWidth(StringUtils.ENGLISH_SPACE));
      assertEquals(7, f.charWidth(StringUtils.INTER_PUNCT));
      assertEquals(7, f.charWidth(StringUtils.EM_QUAD));
      assertEquals(7, f.charWidth(StringUtils.EN_QUAD));
      assertEquals(7, f.charWidth(StringUtils.EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.EN_SPACE));
      assertEquals(7, f.charWidth(StringUtils.SIX_PER_EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.FOUR_PER_EM_SPACE));
      assertEquals(7, f.charWidth(StringUtils.THREE_PER_EM_SPACE));

      assertEquals(7, f.charWidth(StringUtils.PUA_START));
      assertEquals(7, f.charWidth(StringUtils.PUA_END));
   }

   public void testZCharWidthProp() {

      IMFont f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(16, f.getHeight());
      assertEquals(3, f.charWidth(' '));
      assertEquals(11, f.charWidth('m'));
      assertEquals(11, f.charWidth('W'));
      assertEquals(3, f.charWidth('i'));
      assertEquals(3, f.charWidth('|'));
      assertEquals(7, f.charWidth('d'));
      assertEquals(3, f.charWidth(StringUtils.ENGLISH_SPACE));
      assertEquals(3, f.charWidth(StringUtils.INTER_PUNCT));
      assertEquals(12, f.charWidth(StringUtils.EM_QUAD));
      assertEquals(6, f.charWidth(StringUtils.EN_QUAD));
      assertEquals(12, f.charWidth(StringUtils.EM_SPACE));
      assertEquals(6, f.charWidth(StringUtils.EN_SPACE));
      assertEquals(2, f.charWidth(StringUtils.SIX_PER_EM_SPACE));
      assertEquals(3, f.charWidth(StringUtils.FOUR_PER_EM_SPACE));
      assertEquals(4, f.charWidth(StringUtils.THREE_PER_EM_SPACE));

      assertEquals(8, f.charWidth(StringUtils.BACKSPACE));
      assertEquals(8, f.charWidth('\b'));
      assertEquals(8, f.charWidth('【'));
      assertEquals(8, f.charWidth('】'));
      assertEquals("【】", "【】");
      assertEquals(8, f.charWidth(StringUtils.FORM_FEED));
      assertEquals(8, f.charWidth(StringUtils.NULL_CHAR));
      assertEquals(8, f.charWidth(StringUtils.START_HEADING));
      assertEquals(8, f.charWidth(StringUtils.START_TEXT));
      assertEquals(8, f.charWidth(StringUtils.END_TEXT));
      assertEquals(8, f.charWidth(StringUtils.END_TRANSMISSION));

      assertEquals(8, f.charWidth(StringUtils.PUA_START));
      assertEquals(8, f.charWidth(StringUtils.PUA_END));

      assertEquals(8, f.charWidth(StringUtils.TAB_LINE));

      assertEquals(8, f.charWidth(StringUtils.PUA_START));
      assertEquals(8, f.charWidth(StringUtils.PUA_END));

      //look the same but are different.. font of editor does not have glyphs for all characters
      assertEquals("→", String.valueOf(StringUtils.ARROW_RIGHT));
      assertEquals("⎵", String.valueOf(StringUtils.BOTTOM_SQUARE_BRACKET));
      assertEquals("⏎", String.valueOf(StringUtils.SYMBOL_RETURN));
      assertEquals("␠", String.valueOf(StringUtils.SYMBOL_FOR_SPACE));
      assertEquals("·", String.valueOf(StringUtils.INTER_PUNCT));
      assertEquals("", String.valueOf(StringUtils.BACKSPACE));
      assertEquals("", String.valueOf(StringUtils.FORM_FEED));
      assertEquals("", String.valueOf(StringUtils.START_HEADING));
      assertEquals(" ", String.valueOf(StringUtils.LINE_SEPARATOR));
      assertEquals(" ", String.valueOf(StringUtils.LINE_SEPARATOR));

      assertEquals(StringUtils.FORM_FEED_F, StringUtils.FORM_FEED);

      assertEquals(0, f.charWidth(StringUtils.TAB));
      assertEquals(0, f.charWidth(StringUtils.TAB_CHAR));
      assertEquals(0, f.charWidth(StringUtils.NEW_LINE));
      assertEquals(0, f.charWidth(StringUtils.NEW_LINE_CARRIAGE_RETURN));
      assertEquals(0, f.charWidth(StringUtils.LINE_SEPARATOR));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_SPACE));

      assertEquals(6, f.charWidth(StringUtils.FIGURE_SPACE));
      assertEquals(6, f.charWidth('0'));
      assertEquals(6, f.charWidth('9'));

      assertEquals(8, f.charWidth('さ'));

      assertEquals(0, f.charWidth(StringUtils.UTF8_BOM_CHAR));

      String unicodeString = "The unicode for Omega is: \\u03A9";
      System.out.println(unicodeString);

      char quoteChar = 34; // ASCII value of "
      String quote = quoteChar + "To be or not to be, that is the question." + quoteChar;
      System.out.println(quote);
   }

   public void testZeroWidthChars() {

      IMFont f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_PROPORTIONAL, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(0, f.charWidth(StringUtils.NEW_LINE));
      assertEquals(0, f.charWidth(StringUtils.NEW_LINE_CARRIAGE_RETURN));
      assertEquals(0, f.charWidth(StringUtils.LINE_SEPARATOR));
      assertEquals(0, f.charWidth(StringUtils.TAB));
      assertEquals(0, f.charWidth(StringUtils.TAB_CHAR));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_JOINER));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_NON_JOINER));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_SPACE));

      f = drc.getCoreDrawCtx().getFontFactory().getFont(FACE_MONOSPACE, STYLE_PLAIN, SIZE_3_MEDIUM);

      assertEquals(0, f.charWidth(StringUtils.NEW_LINE));
      assertEquals(0, f.charWidth(StringUtils.NEW_LINE_CARRIAGE_RETURN));
      assertEquals(0, f.charWidth(StringUtils.LINE_SEPARATOR));
      assertEquals(0, f.charWidth(StringUtils.TAB));
      assertEquals(0, f.charWidth(StringUtils.TAB_CHAR));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_JOINER));
      assertEquals(0, f.charWidth(StringUtils.ZERO_WIDTH_NON_JOINER));

      //outlier.. might be a bug
      assertEquals(7, f.charWidth(StringUtils.ZERO_WIDTH_SPACE));

   }

   public void testZTricky() {

      // https://stackoverflow.com/questions/34538413/what-are-the-most-difficult-to-render-unicode-samples
      String str = "田中さんにあげて下さい";
      assertEquals(11, str.length());

      str = "Z̤͔ͧ̑̓ä͖̭̈̇lͮ̒ͫǧ̗͚̚o̙̔ͮ̇͐̇";
      assertEquals(28, str.length());

      str = "اختبار النص";
      assertEquals(11, str.length());

      //emojis with skin tone variations
      str = "👱👱🏻👱🏼👱🏽👱🏾👱🏿";
      assertEquals(22, str.length());

      Character c = new Character(' ');

   }

}
