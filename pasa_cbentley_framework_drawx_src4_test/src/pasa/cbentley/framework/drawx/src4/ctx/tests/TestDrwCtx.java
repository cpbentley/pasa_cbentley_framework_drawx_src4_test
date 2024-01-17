package pasa.cbentley.framework.drawx.src4.ctx.tests;

public abstract class TestDrwCtx extends TestCaseDrawXPlugged {

   public TestDrwCtx() {

   }

   public void setupAbstractDrawX() {
   }

   public void testBasic() {

      assertNotNull(drc.getScalerFactory());
   }
}
