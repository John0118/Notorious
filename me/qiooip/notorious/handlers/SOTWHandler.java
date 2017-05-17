/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class SOTWHandler extends Handler implements Listener
/*    */ {
/*    */   private SOTWTask sotwTask;
/*    */   
/*    */   public SOTWHandler(Notorious paramNotorious)
/*    */   {
/* 18 */     super(paramNotorious);
/* 19 */     this.sotwTask = null;
/*    */     
/* 21 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public boolean isRunning() {
/* 25 */     return this.sotwTask != null;
/*    */   }
/*    */   
/*    */   public void startSOTW(int paramInt) {
/* 29 */     SOTWTask localSOTWTask = new SOTWTask(paramInt);
/* 30 */     localSOTWTask.runTaskTimer(getInstance(), 20L, 20L);
/* 31 */     this.sotwTask = localSOTWTask;
/*    */   }
/*    */   
/*    */   public void stopSOTW() {
/* 35 */     if (isRunning()) {
/* 36 */       this.sotwTask.cancel();
/* 37 */       this.sotwTask = null;
/*    */     }
/*    */   }
/*    */   
/*    */   public SOTWTask getSOTWTask() {
/* 42 */     return this.sotwTask;
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onEntityDamage(EntityDamageEvent paramEntityDamageEvent) {
/* 47 */     Entity localEntity = paramEntityDamageEvent.getEntity();
/* 48 */     if (((localEntity instanceof org.bukkit.entity.Player)) && 
/* 49 */       (isRunning())) {
/* 50 */       paramEntityDamageEvent.setCancelled(true);
/*    */     }
/*    */   }
/*    */   
/*    */   public class SOTWTask extends BukkitRunnable
/*    */   {
/*    */     private int time;
/*    */     
/*    */     public SOTWTask(int paramInt)
/*    */     {
/* 60 */       this.time = paramInt;
/*    */     }
/*    */     
/*    */     public void run()
/*    */     {
/* 65 */       if (this.time > 0) {
/* 66 */         this.time -= 1;
/*    */       } else {
/* 68 */         cancel();
/* 69 */         SOTWHandler.this.sotwTask = null;
/*    */       }
/*    */     }
/*    */     
/*    */     public int getTime() {
/* 74 */       return this.time;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\SOTWHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */