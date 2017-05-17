/*    */ package me.qiooip.notorious.platinum;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.CooldownFile;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ 
/*    */ public class PlatinumReviveHandler
/*    */   extends Handler
/*    */   implements Listener
/*    */ {
/* 25 */   private Map<UUID, Long> cooldownData = new HashMap();
/*    */   
/*    */   public PlatinumReviveHandler(Notorious paramNotorious)
/*    */   {
/* 24 */     super(paramNotorious);
/*    */     
/*    */     Player[] arrayOfPlayer;
/* 27 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 28 */       loadData(localPlayer);
/*    */     }
/* 30 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void disable() {
/*    */     Player[] arrayOfPlayer;
/* 35 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 36 */       saveData(localPlayer);
/*    */     }
/*    */   }
/*    */   
/*    */   public void loadData(Player paramPlayer) {
/* 41 */     if (getInstance().getCooldownFile().getConfiguration().contains(paramPlayer.getUniqueId().toString())) {
/* 42 */       this.cooldownData.put(paramPlayer.getUniqueId(), Long.valueOf(getInstance().getCooldownFile().getLong(paramPlayer.getUniqueId().toString())));
/*    */     }
/*    */   }
/*    */   
/*    */   public void saveData(Player paramPlayer) {
/* 47 */     if (this.cooldownData.containsKey(paramPlayer.getUniqueId())) {
/* 48 */       getInstance().getCooldownFile().getConfiguration().set(paramPlayer.getUniqueId().toString(), this.cooldownData.get(paramPlayer.getUniqueId()));
/*    */       try {
/* 50 */         getInstance().getCooldownFile().getConfiguration().save(getInstance().getCooldownFile().getFile());
/*    */       } catch (IOException localIOException) {
/* 52 */         localIOException.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isActive(Player paramPlayer) {
/* 58 */     return (this.cooldownData.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.cooldownData.get(paramPlayer.getUniqueId())).longValue());
/*    */   }
/*    */   
/*    */   public void apply(Player paramPlayer) {
/* 62 */     this.cooldownData.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + 3600000L));
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(Player paramPlayer) {
/* 66 */     if (this.cooldownData.containsKey(paramPlayer.getUniqueId())) {
/* 67 */       return Math.max(((Long)this.cooldownData.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*    */     }
/* 69 */     return 0L;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 74 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/*    */     
/* 76 */     loadData(localPlayer);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 81 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*    */     
/* 83 */     saveData(localPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\platinum\PlatinumReviveHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */