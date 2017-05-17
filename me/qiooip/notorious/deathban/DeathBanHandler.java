/*     */ package me.qiooip.notorious.deathban;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.integration.Vault;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.InventoryUtils;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.StringUtils;
/*     */ import org.apache.commons.lang.time.DurationFormatUtils;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerLoginEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class DeathBanHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private File deathbanFolder;
/*     */   private File deathbansFolder;
/*     */   private File inventoriesFolder;
/*     */   private File livesFolder;
/*     */   private List<DeathBanTime> deathBanTimes;
/*     */   private List<UUID> playerConfirmation;
/*     */   
/*     */   public DeathBanHandler(Notorious paramNotorious)
/*     */   {
/*  43 */     super(paramNotorious);
/*  44 */     this.deathBanTimes = new ArrayList();
/*  45 */     this.playerConfirmation = new ArrayList();
/*     */     
/*  47 */     this.deathbanFolder = new File(Notorious.getInstance().getDataFolder(), "deathban");
/*  48 */     this.deathbansFolder = new File(this.deathbanFolder, "deathbans");
/*  49 */     this.inventoriesFolder = new File(this.deathbanFolder, "inventories");
/*  50 */     this.livesFolder = new File(this.deathbanFolder, "lives");
/*  51 */     loadDeathBanTimes();
/*  52 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*  53 */     getInstance().getServer().getPluginManager().registerEvents(new me.qiooip.notorious.deathban.commands.DeathBanCommand(), getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  57 */     this.deathBanTimes.clear();
/*  58 */     this.playerConfirmation.clear();
/*     */   }
/*     */   
/*     */   public void loadDeathBanTimes() {
/*  62 */     ConfigurationSection localConfigurationSection = getInstance().getDeathBanFile().getConfiguration().getConfigurationSection("deathban-times");
/*  63 */     for (String str : localConfigurationSection.getKeys(false)) {
/*  64 */       DeathBanTime localDeathBanTime = new DeathBanTime();
/*  65 */       localDeathBanTime.setGroup(str);
/*  66 */       localDeathBanTime.setTime(localConfigurationSection.getInt(str + ".deathBanTime"));
/*     */       
/*  68 */       this.deathBanTimes.add(localDeathBanTime);
/*     */     }
/*     */   }
/*     */   
/*     */   public void banPlayer(Player paramPlayer, PlayerDeathEvent paramPlayerDeathEvent) {
/*  73 */     if (getInstance().getConfigHandler().isDeathbanEnabled()) {
/*  74 */       File localFile1 = new File(this.deathbansFolder, paramPlayer.getUniqueId().toString() + ".yml");
/*  75 */       if (!localFile1.exists()) {
/*     */         try {
/*  77 */           localFile1.createNewFile();
/*     */         } catch (IOException localIOException1) {
/*  79 */           localIOException1.printStackTrace();
/*     */         }
/*     */       }
/*  82 */       Location localLocation = paramPlayerDeathEvent.getEntity().getLocation();
/*  83 */       YamlConfiguration localYamlConfiguration1 = YamlConfiguration.loadConfiguration(localFile1);
/*  84 */       localYamlConfiguration1.set("ban_until", Long.valueOf(System.currentTimeMillis() + getBanTime(paramPlayer) * 60L * 1000L));
/*  85 */       localYamlConfiguration1.set("death_message", paramPlayerDeathEvent.getDeathMessage());
/*  86 */       localYamlConfiguration1.set("coords", StringUtils.getWorldName(localLocation) + ", " + localLocation.getBlockX() + ", " + localLocation.getBlockY() + ", " + localLocation.getBlockZ());
/*     */       try {
/*  88 */         localYamlConfiguration1.save(localFile1);
/*     */       } catch (IOException localIOException2) {
/*  90 */         localIOException2.printStackTrace();
/*     */       }
/*     */       
/*  93 */       File localFile2 = new File(this.inventoriesFolder, paramPlayer.getUniqueId() + ".yml");
/*  94 */       if (!localFile2.exists()) {
/*     */         try {
/*  96 */           localFile2.createNewFile();
/*     */         } catch (IOException localIOException3) {
/*  98 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/* 101 */       YamlConfiguration localYamlConfiguration2 = YamlConfiguration.loadConfiguration(localFile2);
/* 102 */       localYamlConfiguration2.set("inventory", InventoryUtils.itemStackArrayToBase64(paramPlayer.getInventory().getContents()));
/* 103 */       localYamlConfiguration2.set("armor", InventoryUtils.itemStackArrayToBase64(paramPlayer.getInventory().getArmorContents()));
/* 104 */       localYamlConfiguration2.set("used", Boolean.valueOf(false));
/*     */       try {
/* 106 */         localYamlConfiguration2.save(localFile2);
/*     */       } catch (IOException localIOException4) {
/* 108 */         localIOException4.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void banVillager(Player paramPlayer, Villager paramVillager, EntityDeathEvent paramEntityDeathEvent) {
/* 114 */     if (getInstance().getConfigHandler().isDeathbanEnabled()) {
/* 115 */       File localFile1 = new File(this.deathbansFolder, paramPlayer.getUniqueId().toString() + ".yml");
/* 116 */       if (!localFile1.exists()) {
/*     */         try {
/* 118 */           localFile1.createNewFile();
/*     */         } catch (IOException localIOException1) {
/* 120 */           localIOException1.printStackTrace();
/*     */         }
/*     */       }
/* 123 */       Location localLocation = paramEntityDeathEvent.getEntity().getLocation();
/* 124 */       YamlConfiguration localYamlConfiguration1 = YamlConfiguration.loadConfiguration(localFile1);
/* 125 */       localYamlConfiguration1.set("ban_until", Long.valueOf(System.currentTimeMillis() + getBanTime(paramPlayer) * 60L * 1000L));
/* 126 */       localYamlConfiguration1.set("death_message", "CombatLogger");
/* 127 */       localYamlConfiguration1.set("coords", StringUtils.getWorldName(localLocation) + ", " + localLocation.getBlockX() + ", " + localLocation.getBlockY() + ", " + localLocation.getBlockZ());
/*     */       try {
/* 129 */         localYamlConfiguration1.save(localFile1);
/*     */       } catch (IOException localIOException2) {
/* 131 */         localIOException2.printStackTrace();
/*     */       }
/*     */       
/* 134 */       File localFile2 = new File(this.inventoriesFolder, paramPlayer.getUniqueId() + ".yml");
/* 135 */       if (!localFile2.exists()) {
/*     */         try {
/* 137 */           localFile2.createNewFile();
/*     */         } catch (IOException localIOException3) {
/* 139 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/*     */       
/* 143 */       ItemStack[] arrayOfItemStack1 = (ItemStack[])((MetadataValue)paramVillager.getMetadata("Contents").get(0)).value();
/* 144 */       ItemStack[] arrayOfItemStack2 = (ItemStack[])((MetadataValue)paramVillager.getMetadata("Armor").get(0)).value();
/*     */       
/* 146 */       YamlConfiguration localYamlConfiguration2 = YamlConfiguration.loadConfiguration(localFile2);
/* 147 */       localYamlConfiguration2.set("inventory", InventoryUtils.itemStackArrayToBase64(arrayOfItemStack1));
/* 148 */       localYamlConfiguration2.set("armor", InventoryUtils.itemStackArrayToBase64(arrayOfItemStack2));
/* 149 */       localYamlConfiguration2.set("used", Boolean.valueOf(false));
/*     */       try {
/* 151 */         localYamlConfiguration2.save(localFile2);
/*     */       } catch (IOException localIOException4) {
/* 153 */         localIOException4.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public long getBanTime(Player paramPlayer) {
/* 159 */     long l = 0L;
/* 160 */     for (DeathBanTime localDeathBanTime : this.deathBanTimes) {
/* 161 */       if (Vault.getPlayerGroup(paramPlayer).equalsIgnoreCase(localDeathBanTime.getGroup())) {
/* 162 */         l = localDeathBanTime.getTime();
/*     */       }
/*     */     }
/* 165 */     return l;
/*     */   }
/*     */   
/*     */   public void kickPlayer(Player paramPlayer) {
/* 169 */     if (getInstance().getConfigHandler().isDeathbanEnabled()) {
/* 170 */       for (DeathBanTime localDeathBanTime : this.deathBanTimes) {
/* 171 */         if (Vault.getPlayerGroup(paramPlayer).equalsIgnoreCase(localDeathBanTime.getGroup())) {
/* 172 */           int i = localDeathBanTime.getTime();
/* 173 */           paramPlayer.kickPlayer(Language.DEATHBAN_BAN_MESSAGE.toString().replace("<time>", DurationFormatUtils.formatDurationWords(i * 60L * 1000L, true, true)));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLives(Player paramPlayer) {
/* 180 */     File localFile = new File(this.livesFolder, paramPlayer.getUniqueId() + ".yml");
/* 181 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 182 */     int i = localYamlConfiguration.getInt("lives");
/* 183 */     return i;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent) {
/* 188 */     final Player localPlayer = paramPlayerDeathEvent.getEntity();
/* 189 */     banPlayer(localPlayer, paramPlayerDeathEvent);
/* 190 */     new org.bukkit.scheduler.BukkitRunnable() {
/*     */       public void run() {
/* 192 */         DeathBanHandler.this.kickPlayer(localPlayer);
/*     */       }
/* 194 */     }.runTaskLater(Notorious.getInstance(), 10L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 199 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/*     */     
/* 201 */     File localFile = new File(this.livesFolder, localPlayer.getUniqueId() + ".yml");
/* 202 */     if (!localFile.exists()) {
/*     */       try {
/* 204 */         localFile.createNewFile();
/*     */       } catch (IOException localIOException1) {
/* 206 */         localIOException1.printStackTrace();
/*     */       }
/* 208 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 209 */       localYamlConfiguration.set("lives", Integer.valueOf(0));
/*     */       try {
/* 211 */         localYamlConfiguration.save(localFile);
/*     */       } catch (IOException localIOException2) {
/* 213 */         localIOException2.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerLogin(PlayerLoginEvent paramPlayerLoginEvent) {
/* 220 */     Player localPlayer = paramPlayerLoginEvent.getPlayer();
/*     */     
/* 222 */     File localFile1 = new File(this.deathbansFolder, localPlayer.getUniqueId() + ".yml");
/* 223 */     if (localFile1.exists()) {
/* 224 */       YamlConfiguration localYamlConfiguration1 = YamlConfiguration.loadConfiguration(localFile1);
/* 225 */       long l = localYamlConfiguration1.getLong("ban_until");
/*     */       
/* 227 */       if (System.currentTimeMillis() < l) {
/* 228 */         if (getLives(localPlayer) <= 0) {
/* 229 */           paramPlayerLoginEvent.setKickMessage(Language.DEATHBAN_BAN_MESSAGE.toString().replace("<time>", DurationFormatUtils.formatDurationWords(l - System.currentTimeMillis(), true, true)));
/* 230 */           paramPlayerLoginEvent.setResult(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_BANNED);
/*     */         }
/* 232 */         else if (this.playerConfirmation.contains(localPlayer.getUniqueId())) {
/* 233 */           File localFile2 = new File(this.livesFolder, localPlayer.getUniqueId() + ".yml");
/* 234 */           YamlConfiguration localYamlConfiguration2 = YamlConfiguration.loadConfiguration(localFile2);
/* 235 */           int i = localYamlConfiguration2.getInt("lives");
/* 236 */           localYamlConfiguration2.set("lives", Integer.valueOf(i - 1));
/*     */           try {
/* 238 */             localYamlConfiguration2.save(localFile2);
/*     */           } catch (IOException localIOException) {
/* 240 */             localIOException.printStackTrace();
/*     */           }
/* 242 */           localFile1.delete();
/* 243 */           this.playerConfirmation.remove(localPlayer.getUniqueId());
/*     */         } else {
/* 245 */           paramPlayerLoginEvent.setKickMessage(Language.DEATHBAN_BAN_MESSAGE.toString().replace("<time>", DurationFormatUtils.formatDurationWords(l - System.currentTimeMillis(), true, true)) + 
/* 246 */             "\n" + "\n" + Language.DEATHBAN_JOIN_AGAIN_FOR_REVIVE.toString().replace("<amount>", String.valueOf(getLives(localPlayer))));
/* 247 */           paramPlayerLoginEvent.setResult(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_BANNED);
/* 248 */           this.playerConfirmation.add(localPlayer.getUniqueId());
/*     */         }
/*     */       }
/*     */       else {
/* 252 */         localFile1.delete();
/* 253 */         this.playerConfirmation.remove(localPlayer.getUniqueId());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class DeathBanTime
/*     */   {
/*     */     private String group;
/*     */     private int time;
/*     */     
/*     */     public DeathBanTime() {}
/*     */     
/*     */     public String getGroup() {
/* 266 */       return this.group;
/*     */     }
/*     */     
/*     */     public void setGroup(String paramString) {
/* 270 */       this.group = paramString;
/*     */     }
/*     */     
/*     */     public int getTime() {
/* 274 */       return this.time;
/*     */     }
/*     */     
/*     */     public void setTime(int paramInt) {
/* 278 */       this.time = paramInt;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\deathban\DeathBanHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */