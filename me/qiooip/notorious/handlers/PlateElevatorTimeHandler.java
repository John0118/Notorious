/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlateElevatorTimeHandler extends Handler
/*    */ {
/*    */   private HashMap<UUID, Long> warmup;
/*    */   
/*    */   public PlateElevatorTimeHandler(Notorious paramNotorious)
/*    */   {
/* 16 */     super(paramNotorious);
/* 17 */     this.warmup = new HashMap();
/*    */   }
/*    */   
/*    */   public void disable() {
/* 21 */     this.warmup.clear();
/*    */   }
/*    */   
/*    */   public void applyWarmup(Player paramPlayer) {
/* 25 */     this.warmup.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getElevatorTeleportDelay() * 1000));
/*    */   }
/*    */   
/*    */   public boolean isActive(Player paramPlayer) {
/* 29 */     return (this.warmup.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.warmup.get(paramPlayer.getUniqueId())).longValue());
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(Player paramPlayer) {
/* 33 */     if (this.warmup.containsKey(paramPlayer.getUniqueId())) {
/* 34 */       return Math.max(((Long)this.warmup.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*    */     }
/* 36 */     return 0L;
/*    */   }
/*    */   
/*    */   public Long getByPlayerUUID(UUID paramUUID) {
/* 40 */     return (Long)this.warmup.get(paramUUID.toString());
/*    */   }
/*    */   
/*    */   public HashMap<UUID, Long> getActiveWarmups() {
/* 44 */     return this.warmup;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\PlateElevatorTimeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */