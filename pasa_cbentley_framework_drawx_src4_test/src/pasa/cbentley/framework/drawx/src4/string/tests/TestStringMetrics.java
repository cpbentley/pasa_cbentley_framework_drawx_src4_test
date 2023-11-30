package pasa.cbentley.framework.drawx.src4.string.tests;

import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.drawx.src4.string.StringMetrics;
import pasa.cbentley.framework.drawx.src4.string.Stringer;
import pasa.cbentley.framework.drawx.src4.tests.TestCaseDrawXPlugged;

/**
 * We need a plug to actually measure things
 * @author Charles Bentley
 *
 */
public abstract class TestStringMetrics extends TestCaseDrawXPlugged implements ITechFont {

   public TestStringMetrics() {
   }

   public void testEmpty() {
      Stringer stringer = new Stringer(dc);
      StringMetrics sm = new StringMetrics(dc, stringer);

      //#debug
      toDLog().pTest("Default Font", dc.getFontFactory().getDefaultFont(), TestStringMetrics.class, "testEmpty", LVL_05_FINE, false);

      assertEquals(0, sm.getPrefWidth());
      assertEquals(21, sm.getPrefHeight());
      assertEquals(21, dc.getFontFactory().getDefaultFont().getHeight());

      assertEquals(21, sm.getPrefCharHeight());
      assertEquals(0, sm.getPrefCharWidth());
      assertEquals(0, sm.getPrefWidthLine(5));

      assertEquals(0, sm.getCharWidthEtalon());

      assertEquals(21, sm.getLineHeight());

      assertEquals(0, sm.getLineWidth(1));

      assertEquals(21, sm.getLineHeight(2));

   }

   public void setupAbstractDrawX() {
   }

}
