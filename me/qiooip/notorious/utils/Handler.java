/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ 
/*    */ public class Handler
/*    */ {
/*    */   private Notorious plugin;
/*    */   
/*    */   public Handler(Notorious paramNotorious) {
/* 10 */     this.plugin = paramNotorious;
/*    */   }
/*    */   
/*    */   public void disable() {}
/*    */   
/*    */   public Notorious getInstance() {
/* 16 */     return this.plugin;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */