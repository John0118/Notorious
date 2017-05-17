/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ public class NumberUtils
/*    */ {
/*    */   public static boolean isInteger(String paramString) {
/*    */     try {
/*  7 */       Integer.parseInt(paramString);
/*    */     } catch (NumberFormatException localNumberFormatException) {
/*  9 */       return false;
/*    */     }
/* 11 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\NumberUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */