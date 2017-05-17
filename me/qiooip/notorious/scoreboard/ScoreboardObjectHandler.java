/*    */ package me.qiooip.notorious.scoreboard;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ import org.bukkit.scoreboard.ScoreboardManager;
/*    */ 
/*    */ 
/*    */ public class ScoreboardObjectHandler
/*    */   extends Handler
/*    */   implements Listener
/*    */ {
/* 26 */   private Map<UUID, ScoreboardObject> sbData = new HashMap();
/*    */   
/*    */   public ScoreboardObjectHandler(Notorious paramNotorious)
/*    */   {
/* 25 */     super(paramNotorious);
/*    */     
/*    */     Player[] arrayOfPlayer;
/* 28 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 29 */       loadData(localPlayer);
/*    */     }
/* 31 */     Notorious.getInstance().getServer().getPluginManager().registerEvents(this, Notorious.getInstance());
/*    */   }
/*    */   
/*    */   public void reload() {
/*    */     Player[] arrayOfPlayer;
/* 36 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 37 */       reloadData(localPlayer);
/*    */     }
/*    */   }
/*    */   
/*    */   public ScoreboardObject getScoreboardFor(Player paramPlayer) {
/* 42 */     return (ScoreboardObject)this.sbData.get(paramPlayer.getUniqueId());
/*    */   }
/*    */   
/*    */   public void loadData(Player paramPlayer) {
/* 46 */     Scoreboard localScoreboard = Notorious.getInstance().getServer().getScoreboardManager().getNewScoreboard();
/* 47 */     paramPlayer.setScoreboard(localScoreboard);
/* 48 */     this.sbData.put(paramPlayer.getUniqueId(), new ScoreboardObject(localScoreboard, ChatColor.translateAlternateColorCodes('&', getInstance().getConfigHandler().getScoreboardTitle()), paramPlayer));
/*    */   }
/*    */   
/*    */   public void reloadData(Player paramPlayer) {
/* 52 */     if (this.sbData.containsKey(paramPlayer.getUniqueId())) {
/* 53 */       Scoreboard localScoreboard = paramPlayer.getScoreboard();
/* 54 */       paramPlayer.setScoreboard(localScoreboard);
/* 55 */       this.sbData.put(paramPlayer.getUniqueId(), new ScoreboardObject(localScoreboard, ChatColor.translateAlternateColorCodes('&', getInstance().getConfigHandler().getScoreboardTitle()), paramPlayer));
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 61 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/* 62 */     loadData(localPlayer);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 67 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 68 */     for (String str : localPlayer.getScoreboard().getEntries()) {
/* 69 */       localPlayer.getScoreboard().resetScores(str);
/*    */     }
/* 71 */     this.sbData.remove(localPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\scoreboard\ScoreboardObjectHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */