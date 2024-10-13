package pasa.cbentley.framework.drawx.src4.ctx.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.ABOCtx;
import pasa.cbentley.core.src4.ctx.ConfigAbstract;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.IBOGraphics;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechFont;

/**
 * Configuration by default
 * @author Charles Bentley
 *
 */
public abstract class ConfigCoreDrawTest extends ConfigAbstract implements IConfigCoreDraw {

   public ConfigCoreDrawTest(UCtx uc) {
      super(uc);
   }

   public int getColorImageBackgroundDefault() {
      return IColors.FULLY_OPAQUE_BLACK;
   }
   
   public int getDefaultFontSize() {
      return ITechFont.SIZE_3_MEDIUM;
   }

   public boolean isUsingFontCache() {
      return false;
   }

   public int getFontPointsExtraShift() {
      return 0;
   }

   public int getFontPoint_01_Tiny() {
      return 8;
   }

   public int getFontPoint_02_Small() {
      return 12;
   }

   public int getFontPoint_03_Medium() {
      return 16;
   }

   public int getFontPoint_04_Large() {
      return 20;
   }

   public int getFontPoint_05_Huge() {
      return 22;
   }
   public void postProcessing(ByteObject settings, ABOCtx ctx) {

   }
}
