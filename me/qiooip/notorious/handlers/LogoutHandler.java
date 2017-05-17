/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class LogoutHandler extends Handler implements Listener
/*     */ {
/*     */   private HashMap<UUID, LogoutTask> logoutTasks;
/*     */   private HashMap<UUID, Long> warmup;
/*     */   
/*     */   public LogoutHandler(Notorious paramNotorious)
/*     */   {
/*  28 */     super(paramNotorious);
/*  29 */     this.logoutTasks = new HashMap();
/*  30 */     this.warmup = new HashMap();
/*     */     
/*  32 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  36 */     this.logoutTasks.clear();
/*  37 */     this.warmup.clear();
/*     */   }
/*     */   
/*     */   public void applyWarmup(Player paramPlayer) {
/*  41 */     this.warmup.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getLogoutTime() * 1000));
/*     */   }
/*     */   
/*     */   public boolean isActive(Player paramPlayer) {
/*  45 */     return (this.warmup.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.warmup.get(paramPlayer.getUniqueId())).longValue());
/*     */   }
/*     */   
/*     */   public long getMillisecondsLeft(Player paramPlayer) {
/*  49 */     if (this.warmup.containsKey(paramPlayer.getUniqueId())) {
/*  50 */       return Math.max(((Long)this.warmup.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*     */     }
/*  52 */     return 0L;
/*     */   }
/*     */   
/*     */   public void createLogout(Player paramPlayer) {
/*  56 */     LogoutTask localLogoutTask = new LogoutTask(paramPlayer);
/*  57 */     localLogoutTask.runTaskLater(Notorious.getInstance(), getInstance().getConfigHandler().getLogoutTime() * 20);
/*  58 */     applyWarmup(paramPlayer);
/*  59 */     this.logoutTasks.put(paramPlayer.getUniqueId(), localLogoutTask);
/*     */   }
/*     */   
/*     */   public void removeLogout(Player paramPlayer) {
/*  63 */     if (this.logoutTasks.containsKey(paramPlayer.getUniqueId())) {
/*  64 */       ((LogoutTask)this.logoutTasks.get(paramPlayer.getUniqueId())).cancel();
/*  65 */       this.logoutTasks.remove(paramPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   public HashMap<UUID, LogoutTask> getLogoutTasks() {
/*  70 */     return this.logoutTasks;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMove(PlayerMoveEvent paramPlayerMoveEvent) {
/*  75 */     Player localPlayer = paramPlayerMoveEvent.getPlayer();
/*  76 */     Location localLocation1 = paramPlayerMoveEvent.getFrom();
/*  77 */     Location localLocation2 = paramPlayerMoveEvent.getTo();
/*  78 */     if ((localLocation1.getPitch() != localLocation2.getPitch()) || (localLocation1.getYaw() != localLocation2.getYaw())) return;
/*  79 */     if ((this.logoutTasks.containsKey(localPlayer.getUniqueId())) && (
/*  80 */       (localLocation1.getBlockX() != localLocation2.getBlockX()) || (localLocation1.getBlockZ() != localLocation2.getBlockZ()))) {
/*  81 */       ((LogoutTask)this.logoutTasks.get(localPlayer.getUniqueId())).cancel();
/*  82 */       this.logoutTasks.remove(localPlayer.getUniqueId());
/*  83 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.LOGOUT_TELEPORT_CANCELLED.toString().replace("<reason>", "Moved"));
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDamage(EntityDamageEvent paramEntityDamageEvent)
/*     */   {
/*  90 */     org.bukkit.entity.Entity localEntity = paramEntityDamageEvent.getEntity();
/*     */     
/*  92 */     if ((localEntity instanceof Player)) {
/*  93 */       Player localPlayer = (Player)localEntity;
/*  94 */       if (this.logoutTasks.containsKey(localPlayer.getUniqueId())) {
/*  95 */         ((LogoutTask)this.logoutTasks.get(localPlayer.getUniqueId())).cancel();
/*  96 */         this.logoutTasks.remove(localPlayer.getUniqueId());
/*  97 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.LOGOUT_TELEPORT_CANCELLED.toString().replace("<reason>", "Were Damaged"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 104 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 105 */     if (this.logoutTasks.containsKey(localPlayer.getUniqueId())) {
/* 106 */       ((LogoutTask)this.logoutTasks.get(localPlayer.getUniqueId())).cancel();
/* 107 */       this.logoutTasks.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerKickEvent paramPlayerKickEvent) {
/* 113 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/*     */     
/* 115 */     if (this.logoutTasks.containsKey(localPlayer.getUniqueId())) {
/* 116 */       ((LogoutTask)this.logoutTasks.get(localPlayer.getUniqueId())).cancel();
/* 117 */       this.logoutTasks.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   public class LogoutTask extends org.bukkit.scheduler.BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     
/*     */     public LogoutTask(Player paramPlayer) {
/* 126 */       this.player = paramPlayer;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 131 */       this.player.setMetadata("LogoutCommand", new FixedMetadataValue(Notorious.getInstance(), Boolean.TRUE));
/* 132 */       this.player.kickPlayer(Language.LOGOUT_KICK_MESSAGE.toString());
/* 133 */       LogoutHandler.this.logoutTasks.remove(this.player.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\LogoutHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */