/*    */ package me.qiooip.notorious.cooldown;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ 
/*    */ public class CooldownHandler extends Handler
/*    */ {
/*    */   private HashMap<String, Long> cooldowns;
/*    */   
/*    */   public CooldownHandler(Notorious paramNotorious)
/*    */   {
/* 13 */     super(paramNotorious);
/* 14 */     this.cooldowns = new HashMap();
/*    */   }
/*    */   
/*    */   public void disable() {
/* 18 */     this.cooldowns.clear();
/*    */   }
/*    */   
/*    */   public void createCooldown(String paramString, int paramInt) {
/* 22 */     this.cooldowns.put(paramString, Long.valueOf(System.currentTimeMillis() + paramInt * 1000));
/*    */   }
/*    */   
/*    */   public void deleteCooldown(String paramString) {
/* 26 */     this.cooldowns.remove(paramString);
/*    */   }
/*    */   
/*    */   public boolean isActive(String paramString) {
/* 30 */     return (this.cooldowns.containsKey(paramString)) && (System.currentTimeMillis() < ((Long)this.cooldowns.get(paramString)).longValue());
/*    */   }
/*    */   
/*    */   public long getByName(String paramString) {
/* 34 */     return ((Long)this.cooldowns.get(paramString)).longValue();
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(String paramString) {
/* 38 */     if (this.cooldowns.containsKey(paramString)) {
/* 39 */       return Math.max(((Long)this.cooldowns.get(paramString)).longValue() - System.currentTimeMillis(), 0L);
/*    */     }
/* 41 */     return 0L;
/*    */   }
/*    */   
/*    */   public HashMap<String, Long> getActiveCooldowns() {
/* 45 */     return this.cooldowns;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\cooldown\CooldownHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */