package pasa.cbentley.framework.drawx.src4.draw.tests;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.ABOCtx;
import pasa.cbentley.core.src4.ctx.ConfigAbstract;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.utils.interfaces.IColors;
import pasa.cbentley.framework.coredraw.src4.ctx.IConfigCoreDraw;
import pasa.cbentley.framework.coredraw.src4.interfaces.ITechDrawer;

/**
 * Configuration by default
 * @author Charles Bentley
 *
 */
public abstract class ConfigCoreDrawTest extends ConfigAbstract implements IConfigCoreDraw {

   public ConfigCoreDrawTest(UCtx uc) {
      super(uc);
   }

   public static final int FONT_LARGE_POINTS      = 20;

   public static final int FONT_MEDIUM_POINTS     = 16;

   public static final int FONT_SMALL_POINTS      = 12;

   public static final int FONT_VERY_LARGE_POINTS = 24;

   public static final int FONT_VERY_SMALL_POINTS = 8;

   private int[]           fontPoints             = new int[] { FONT_SMALL_POINTS, FONT_MEDIUM_POINTS, FONT_LARGE_POINTS, FONT_VERY_SMALL_POINTS, FONT_VERY_LARGE_POINTS };

   public int getColorImageBackgroundDefault() {
      return IColors.FULLY_OPAQUE_BLACK;
   }

   public int getFontPointsExtraShift() {
      return 0;
   }

   public int[] getFontPoints() {
      return fontPoints;
   }

   public void postProcessing(ByteObject settings, ABOCtx ctx) {

   }
}
