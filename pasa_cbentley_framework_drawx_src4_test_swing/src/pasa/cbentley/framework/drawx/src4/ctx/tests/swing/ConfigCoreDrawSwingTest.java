/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.framework.drawx.src4.ctx.tests.swing;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.coredraw.j2se.ctx.ConfigCoreDrawJ2seDef;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechGraphics;
import pasa.cbentley.framework.coredraw.swing.ctx.IConfigCoreDrawSwing;

public class ConfigCoreDrawSwingTest extends ConfigCoreDrawJ2seDef implements IConfigCoreDrawSwing {

   public ConfigCoreDrawSwingTest(UCtx uc) {
      super(uc);
   }

   public int getFontPoint_01_Tiny() {
      return 8;
   }

   public int getFontPoint_02_Small() {
      return 10;
   }

   public int getFontPoint_03_Medium() {
      return 12;
   }

   public int getFontPoint_04_Large() {
      return 16;
   }

   public int getFontPoint_05_Huge() {
      return 22;
   }
   
   public int getAliasMode() {
      return ITechGraphics.MODSET_APP_ALIAS_2_OFF;
   }

   public int getAliasModeText() {
      return ITechGraphics.MODSET_APP_ALIAS_2_OFF;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ConfigCoreDrawSwingTest.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ConfigCoreDrawSwingTest.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
