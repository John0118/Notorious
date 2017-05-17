/*     */ package me.qiooip.notorious.playerdata;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigFile;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerDataHandler
/*     */   extends Handler
/*     */   implements Listener
/*     */ {
/*  29 */   private HashMap<UUID, PlayerData> playerData = new HashMap();
/*  30 */   private File playerDataDirectory = new File(getInstance().getDataFolder(), "playerdata");
/*     */   
/*     */   public PlayerDataHandler(Notorious paramNotorious)
/*     */   {
/*  28 */     super(paramNotorious);
/*     */     
/*     */     Player[] arrayOfPlayer;
/*     */     
/*  32 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  33 */       loadData(localPlayer);
/*     */     }
/*  35 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*     */     Player[] arrayOfPlayer;
/*  40 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  41 */       saveData(localPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadData(Player paramPlayer) {
/*  46 */     File localFile = new File(this.playerDataDirectory, paramPlayer.getUniqueId().toString() + ".yml");
/*  47 */     PlayerData localPlayerData = new PlayerData();
/*  48 */     if (!localFile.exists()) {
/*     */       try {
/*  50 */         localFile.createNewFile();
/*     */         
/*  52 */         localPlayerData.setKills(0);
/*  53 */         localPlayerData.setDeaths(0);
/*  54 */         localPlayerData.setPvPTime(getInstance().getConfigFile().getInt("pvp-timer-duration"));
/*  55 */         localPlayerData.setCombatLogger(Boolean.FALSE.booleanValue());
/*  56 */         localPlayerData.setGoldenAppleCooldown(0L);
/*  57 */         localPlayerData.setNotes(new ArrayList());
/*     */         
/*  59 */         addPlayerData(paramPlayer, localPlayerData);
/*     */       } catch (IOException localIOException) {
/*  61 */         localIOException.printStackTrace();
/*     */       }
/*     */     } else {
/*  64 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/*     */       
/*  66 */       localPlayerData.setKills(localYamlConfiguration.getInt("kills"));
/*  67 */       localPlayerData.setDeaths(localYamlConfiguration.getInt("deaths"));
/*  68 */       localPlayerData.setPvPTime(localYamlConfiguration.getInt("pvptimer"));
/*  69 */       localPlayerData.setCombatLogger(localYamlConfiguration.getBoolean("combatLogger"));
/*  70 */       localPlayerData.setGoldenAppleCooldown(localYamlConfiguration.getLong("goldenAppleCooldown"));
/*  71 */       localPlayerData.setNotes(localYamlConfiguration.getStringList("notes"));
/*     */       
/*  73 */       addPlayerData(paramPlayer, localPlayerData);
/*     */     }
/*     */   }
/*     */   
/*     */   public void saveData(Player paramPlayer) {
/*  78 */     File localFile = new File(this.playerDataDirectory, paramPlayer.getUniqueId().toString() + ".yml");
/*  79 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/*  80 */     PlayerData localPlayerData = (PlayerData)this.playerData.get(paramPlayer.getUniqueId());
/*     */     
/*  82 */     localYamlConfiguration.set("kills", Integer.valueOf(localPlayerData.getKills()));
/*  83 */     localYamlConfiguration.set("deaths", Integer.valueOf(localPlayerData.getDeaths()));
/*  84 */     localYamlConfiguration.set("pvptimer", Integer.valueOf(localPlayerData.getPvPTime()));
/*  85 */     localYamlConfiguration.set("combatLogger", Boolean.valueOf(localPlayerData.isCombatLogger()));
/*  86 */     localYamlConfiguration.set("goldenAppleCooldown", Long.valueOf(localPlayerData.getGoldenAppleCooldown()));
/*  87 */     localYamlConfiguration.set("notes", localPlayerData.getNotes());
/*     */     try
/*     */     {
/*  90 */       localYamlConfiguration.save(localFile);
/*     */     } catch (IOException localIOException) {
/*  92 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public PlayerData getByPlayer(Player paramPlayer) {
/*  97 */     return (PlayerData)this.playerData.get(paramPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   public void addPlayerData(Player paramPlayer, PlayerData paramPlayerData) {
/* 101 */     this.playerData.put(paramPlayer.getUniqueId(), paramPlayerData);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 106 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/* 107 */     loadData(localPlayer);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 112 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 113 */     saveData(localPlayer);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 118 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 119 */     saveData(localPlayer);
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\playerdata\PlayerDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */