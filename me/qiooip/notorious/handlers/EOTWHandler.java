/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.zencode.mango.Mango;
/*    */ import org.zencode.mango.config.ConfigFile;
/*    */ import org.zencode.mango.factions.Faction;
/*    */ import org.zencode.mango.factions.FactionManager;
/*    */ import org.zencode.mango.factions.types.PlayerFaction;
/*    */ 
/*    */ public class EOTWHandler extends Handler
/*    */ {
/*    */   private EOTWTask eotwTask;
/*    */   private boolean running;
/*    */   
/*    */   public EOTWHandler(Notorious paramNotorious)
/*    */   {
/* 22 */     super(paramNotorious);
/* 23 */     this.eotwTask = null;
/* 24 */     this.running = false;
/*    */   }
/*    */   
/*    */   public void startTimer(int paramInt)
/*    */   {
/* 29 */     EOTWTask localEOTWTask = new EOTWTask(paramInt);
/* 30 */     this.eotwTask = localEOTWTask;
/* 31 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 32 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.EOTW_TIMER_STARTED.toString().replace("<seconds>", String.valueOf(paramInt)));
/*    */     }
/*    */   }
/*    */   
/*    */   public void stopTimer() {
/* 37 */     if (this.eotwTask != null) {
/* 38 */       this.eotwTask.cancel();
/*    */     }
/* 40 */     this.eotwTask = null;
/*    */   }
/*    */   
/*    */   public void toggle(boolean paramBoolean) {
/* 44 */     for (Faction localFaction : Mango.getInstance().getFactionManager().getFactions()) {
/* 45 */       if ((localFaction instanceof PlayerFaction)) {
/* 46 */         PlayerFaction localPlayerFaction = (PlayerFaction)localFaction;
/* 47 */         if (paramBoolean) {
/* 48 */           localPlayerFaction.setDtr(BigDecimal.valueOf(-6.5D));
/* 49 */           localPlayerFaction.freeze(Integer.MAX_VALUE);
/*    */         } else {
/* 51 */           localPlayerFaction.setDtr(BigDecimal.valueOf(Mango.getInstance().getConfigFile().getDouble("MAX_DTR")));
/* 52 */           localPlayerFaction.unfreeze();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void startEotw()
/*    */   {
/* 60 */     toggle(true);
/* 61 */     this.running = true;
/* 62 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 63 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.EOTW_BROADCAST_START.toString());
/*    */     }
/*    */   }
/*    */   
/*    */   public void stopEotw()
/*    */   {
/* 69 */     toggle(false);
/* 70 */     this.running = false;
/* 71 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 72 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.EOTW_BROADCAST_STOP.toString());
/*    */     }
/*    */   }
/*    */   
/*    */   public EOTWTask getEOTWTask() {
/* 77 */     return this.eotwTask;
/*    */   }
/*    */   
/*    */   public boolean isRunning() {
/* 81 */     return this.running;
/*    */   }
/*    */   
/*    */   public class EOTWTask extends org.bukkit.scheduler.BukkitRunnable
/*    */   {
/*    */     public EOTWTask(int paramInt) {
/* 87 */       runTaskLater(Notorious.getInstance(), paramInt * 20L);
/*    */     }
/*    */     
/*    */     public void run() {
/* 91 */       EOTWHandler.this.startEotw();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\EOTWHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */