/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.world.ChunkUnloadEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ 
/*     */ public class CombatLoggerHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<String, List<Player>> combatLoggers;
/*     */   
/*     */   public CombatLoggerHandler(Notorious paramNotorious)
/*     */   {
/*  45 */     super(paramNotorious);
/*  46 */     this.combatLoggers = new HashMap();
/*     */     
/*  48 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */     
/*  50 */     new BukkitRunnable() {
/*     */       public void run() {
/*  52 */         CombatLoggerHandler.this.removeVillagers();
/*     */       }
/*  54 */     }.runTaskLater(getInstance(), 40L);
/*     */   }
/*     */   
/*     */ 
/*  58 */   public void disable() { this.combatLoggers.clear(); }
/*     */   
/*     */   public void removeVillagers() {
/*     */     Iterator localIterator2;
/*  62 */     for (Iterator localIterator1 = Bukkit.getWorlds().iterator(); localIterator1.hasNext(); 
/*  63 */         localIterator2.hasNext())
/*     */     {
/*  62 */       World localWorld = (World)localIterator1.next();
/*  63 */       localIterator2 = localWorld.getEntitiesByClass(Villager.class).iterator(); continue;Villager localVillager = (Villager)localIterator2.next();
/*  64 */       if ((!localVillager.isDead()) && (localVillager.hasMetadata("CombatLogger"))) {
/*  65 */         localVillager.removeMetadata("CombatLogger", Notorious.getInstance());
/*  66 */         localVillager.removeMetadata("Player", Notorious.getInstance());
/*  67 */         localVillager.removeMetadata("Contents", Notorious.getInstance());
/*  68 */         localVillager.removeMetadata("Armor", Notorious.getInstance());
/*  69 */         localVillager.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onJoin(Player paramPlayer)
/*     */   {
/*  76 */     for (Villager localVillager : paramPlayer.getWorld().getEntitiesByClass(Villager.class)) {
/*  77 */       if ((!localVillager.isDead()) && (localVillager.hasMetadata("CombatLogger")) && 
/*  78 */         (localVillager.getCustomName().equals(paramPlayer.getName()))) {
/*  79 */         localVillager.removeMetadata("CombatLogger", Notorious.getInstance());
/*  80 */         localVillager.removeMetadata("Player", Notorious.getInstance());
/*  81 */         localVillager.removeMetadata("Contents", Notorious.getInstance());
/*  82 */         localVillager.removeMetadata("Armor", Notorious.getInstance());
/*  83 */         localVillager.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void onQuit(Player paramPlayer)
/*     */   {
/*  90 */     if (paramPlayer.getHealth() == 0.0D) return;
/*  91 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/*  92 */     if (localPlayerData.getPvPTime() > 0) return;
/*  93 */     if (paramPlayer.hasPermission("notorious.combatlogger.bypass")) return;
/*  94 */     if (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(paramPlayer)) return;
/*  95 */     if (getInstance().getSOTWHandler().isRunning()) return;
/*  96 */     if (paramPlayer.hasMetadata("LogoutCommand")) { return;
/*     */     }
/*  98 */     PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(paramPlayer);
/*  99 */     if (localPlayerFaction != null) {
/* 100 */       this.combatLoggers.put(paramPlayer.getName(), localPlayerFaction.getOnlinePlayers());
/*     */     } else {
/* 102 */       this.combatLoggers.put(paramPlayer.getName(), java.util.Arrays.asList(new Player[] { paramPlayer }));
/*     */     }
/*     */     
/* 105 */     spawnVillager(paramPlayer);
/*     */   }
/*     */   
/*     */   public void spawnVillager(Player paramPlayer)
/*     */   {
/* 110 */     final Villager localVillager = (Villager)paramPlayer.getWorld().spawnEntity(paramPlayer.getLocation(), org.bukkit.entity.EntityType.VILLAGER);
/* 111 */     localVillager.setCustomName(paramPlayer.getName());
/* 112 */     localVillager.setCustomNameVisible(true);
/* 113 */     localVillager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 100));
/* 114 */     localVillager.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 100));
/* 115 */     localVillager.setMetadata("CombatLogger", new FixedMetadataValue(getInstance(), paramPlayer.getUniqueId()));
/* 116 */     localVillager.setMetadata("Player", new FixedMetadataValue(getInstance(), paramPlayer));
/* 117 */     localVillager.setMetadata("Contents", new FixedMetadataValue(getInstance(), paramPlayer.getInventory().getContents()));
/* 118 */     localVillager.setMetadata("Armor", new FixedMetadataValue(getInstance(), paramPlayer.getInventory().getArmorContents()));
/* 119 */     localVillager.setMaxHealth(40);
/* 120 */     localVillager.setHealth(localVillager.getMaxHealth());
/*     */     
/* 122 */     new BukkitRunnable() {
/*     */       public void run() {
/* 124 */         if ((localVillager != null) && (!localVillager.isDead())) {
/* 125 */           localVillager.removeMetadata("CombatLogger", Notorious.getInstance());
/* 126 */           localVillager.removeMetadata("Player", Notorious.getInstance());
/* 127 */           localVillager.removeMetadata("Contents", Notorious.getInstance());
/* 128 */           localVillager.removeMetadata("Armor", Notorious.getInstance());
/* 129 */           localVillager.remove();
/*     */         }
/*     */       }
/* 132 */     }.runTaskLater(getInstance(), getInstance().getConfigHandler().getCombatLoggerTime() * 20);
/*     */   }
/*     */   
/*     */   public HashMap<String, List<Player>> getCombatLoggers() {
/* 136 */     return this.combatLoggers;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDeath(EntityDeathEvent paramEntityDeathEvent) {
/* 141 */     LivingEntity localLivingEntity = paramEntityDeathEvent.getEntity();
/* 142 */     if ((localLivingEntity instanceof Villager)) {
/* 143 */       Villager localVillager = (Villager)localLivingEntity;
/* 144 */       Player localPlayer1 = localLivingEntity.getKiller();
/* 145 */       if (localVillager.hasMetadata("CombatLogger")) {
/* 146 */         Player localPlayer2 = (Player)((MetadataValue)localVillager.getMetadata("Player").get(0)).value();
/* 147 */         PlayerData localPlayerData1 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer2);
/* 148 */         PlayerData localPlayerData2 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 149 */         localPlayerData1.addDeath();
/* 150 */         localPlayerData2.addKill();
/*     */         
/* 152 */         if (localPlayer1 != null) {
/* 153 */           Bukkit.broadcastMessage(Language.COMBATLOGGER_DEATHMESSAGE_WITH_KILLER.toString().replace("<player>", localVillager.getCustomName()).replace("<killer>", 
/* 154 */             localPlayer1.getName()).replace("<kills1>", String.valueOf(localPlayerData1.getKills())).replace("<kills2>", String.valueOf(localPlayerData2.getKills())));
/*     */         } else {
/* 156 */           Bukkit.broadcastMessage(Language.COMBATLOGGER_DEATHMESSAGE_NO_KILLER.toString().replace("<player>", localVillager.getCustomName()));
/*     */         }
/*     */         
/*     */ 
/* 160 */         ItemStack[] arrayOfItemStack1 = (ItemStack[])((MetadataValue)localVillager.getMetadata("Contents").get(0)).value();
/* 161 */         ItemStack[] arrayOfItemStack2 = (ItemStack[])((MetadataValue)localVillager.getMetadata("Armor").get(0)).value();
/*     */         ItemStack[] arrayOfItemStack3;
/* 163 */         int j = (arrayOfItemStack3 = arrayOfItemStack1).length; for (int i = 0; i < j; i++) { localObject = arrayOfItemStack3[i];
/* 164 */           if ((localObject != null) && (!((ItemStack)localObject).getType().equals(Material.AIR))) {
/* 165 */             localVillager.getWorld().dropItemNaturally(localVillager.getLocation(), (ItemStack)localObject);
/*     */           }
/*     */         }
/* 168 */         j = (arrayOfItemStack3 = arrayOfItemStack2).length; for (i = 0; i < j; i++) { localObject = arrayOfItemStack3[i];
/* 169 */           if ((localObject != null) && (!((ItemStack)localObject).getType().equals(Material.AIR))) {
/* 170 */             localVillager.getWorld().dropItemNaturally(localVillager.getLocation(), (ItemStack)localObject);
/*     */           }
/*     */         }
/*     */         
/* 174 */         localPlayerData1.setCombatLogger(true);
/* 175 */         getInstance().getPlayerDataHandler().saveData(localPlayer2);
/* 176 */         Object localObject = Mango.getInstance().getFactionManager().getFaction(localPlayer2);
/* 177 */         if ((localObject != null) && ((localObject instanceof PlayerFaction))) {
/* 178 */           ((PlayerFaction)localObject).freeze(Mango.getInstance().getConfigFile().getInt("FREEZE_DURATION"));
/* 179 */           ((PlayerFaction)localObject).setDtr(((PlayerFaction)localObject).getDtrDecimal().subtract(BigDecimal.valueOf(1L)));
/* 180 */           ((PlayerFaction)localObject).sendMessage(Mango.getInstance().getLanguageFile().getString("PLAYER_EVENTS.DEATH").replace("{player}", localPlayer2.getName()).replace("{dtr}", ((PlayerFaction)localObject).getDtrDecimal()).replace("{maxdtr}", ((PlayerFaction)localObject).getMaxDtr()));
/*     */         }
/* 182 */         getInstance().getDeathBanHandler().banVillager(localPlayer2, localVillager, paramEntityDeathEvent);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/* 189 */     Entity localEntity1 = paramEntityDamageByEntityEvent.getEntity();
/* 190 */     Entity localEntity2 = paramEntityDamageByEntityEvent.getDamager();
/* 191 */     if ((localEntity1 instanceof Villager)) {
/* 192 */       Villager localVillager = (Villager)localEntity1;
/* 193 */       if ((localEntity2 instanceof Player)) {
/* 194 */         Player localPlayer = (Player)localEntity2;
/* 195 */         if ((localVillager.hasMetadata("CombatLogger")) && 
/* 196 */           (this.combatLoggers.containsKey(localVillager.getCustomName())) && 
/* 197 */           (((List)this.combatLoggers.get(localVillager.getCustomName())).contains(localPlayer))) {
/* 198 */           paramEntityDamageByEntityEvent.setCancelled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerInteractEntity(PlayerInteractEntityEvent paramPlayerInteractEntityEvent)
/*     */   {
/* 208 */     if (paramPlayerInteractEntityEvent.getRightClicked().hasMetadata("CombatLogger"))
/* 209 */       paramPlayerInteractEntityEvent.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onChunkUnload(ChunkUnloadEvent paramChunkUnloadEvent) {
/*     */     Entity[] arrayOfEntity;
/* 215 */     int j = (arrayOfEntity = paramChunkUnloadEvent.getChunk().getEntities()).length; for (int i = 0; i < j; i++) { Entity localEntity = arrayOfEntity[i];
/* 216 */       if ((localEntity.hasMetadata("CombatLogger")) && (!localEntity.isDead())) {
/* 217 */         paramChunkUnloadEvent.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 224 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/* 225 */     onJoin(localPlayer);
/* 226 */     if (localPlayer.hasMetadata("LogoutCommand")) {
/* 227 */       localPlayer.removeMetadata("LogoutCommand", getInstance());
/*     */     }
/* 229 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/* 230 */     if (localPlayerData.isCombatLogger()) {
/* 231 */       Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + localPlayer.getName());
/* 232 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMBATLOGGER_ON_DEATH_MESSAGE.toString());
/* 233 */       localPlayer.getInventory().clear();
/* 234 */       localPlayer.getInventory().setArmorContents(new ItemStack[4]);
/* 235 */       localPlayerData.setPvPTime(getInstance().getConfigHandler().getPvPTimerDuration());
/*     */     }
/* 237 */     localPlayerData.setCombatLogger(false);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 242 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 243 */     onQuit(localPlayer);
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\CombatLoggerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */