/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.World.Environment;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Boat;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerPortalEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/*     */ import org.bukkit.event.vehicle.VehicleCreateEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class DynamicPlayerHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private ArrayList<UUID> staffScoreboard;
/*     */   private ArrayList<UUID> notifiedPvPTimer;
/*     */   private ArrayList<UUID> notifiedCombat;
/*     */   private Material[] materials;
/*     */   
/*     */   public DynamicPlayerHandler(Notorious paramNotorious)
/*     */   {
/*  46 */     super(paramNotorious);
/*  47 */     this.staffScoreboard = new ArrayList();
/*  48 */     this.notifiedPvPTimer = new ArrayList();
/*  49 */     this.notifiedCombat = new ArrayList();
/*  50 */     this.materials = new Material[] { Material.GOLDEN_APPLE, Material.COOKED_BEEF, Material.RAW_BEEF, Material.COOKED_CHICKEN, Material.RAW_CHICKEN, Material.BAKED_POTATO, Material.GOLDEN_CARROT, Material.PORK, Material.GRILLED_PORK, Material.PUMPKIN_PIE, Material.POTION };
/*     */     
/*  52 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  56 */     this.notifiedPvPTimer.clear();
/*  57 */     this.notifiedCombat.clear();
/*     */   }
/*     */   
/*     */   public ArrayList<UUID> getStaffScoreboard() {
/*  61 */     return this.staffScoreboard;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/*  66 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/*  67 */     Block localBlock = paramBlockPlaceEvent.getBlock();
/*  68 */     if ((localBlock.getType().equals(Material.MOB_SPAWNER)) && (localBlock.getWorld().getEnvironment().equals(World.Environment.NETHER))) {
/*  69 */       if (!localPlayer.isOp()) {
/*  70 */         paramBlockPlaceEvent.setCancelled(true);
/*  71 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.SPAWNERS_DISABLE_PLACE_NETHER.toString());
/*     */       }
/*  73 */     } else if ((localBlock.getType().equals(Material.MOB_SPAWNER)) && (localBlock.getWorld().getEnvironment().equals(World.Environment.THE_END)) && 
/*  74 */       (!localPlayer.isOp())) {
/*  75 */       paramBlockPlaceEvent.setCancelled(true);
/*  76 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.SPAWNERS_DISABLE_PLACE_END.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/*  83 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/*  84 */     Action localAction = paramPlayerInteractEvent.getAction();
/*  85 */     ItemStack localItemStack = localPlayer.getItemInHand();
/*  86 */     Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/*     */     
/*  88 */     if ((localItemStack == null) || (localItemStack.getType() == Material.AIR)) { return;
/*     */     }
/*  90 */     if ((localAction == Action.RIGHT_CLICK_BLOCK) && (
/*  91 */       (localBlock.getType() == Material.FENCE) || (localBlock.getType() == Material.NETHER_FENCE) || (localBlock.getType() == Material.CAULDRON))) { Material[] arrayOfMaterial;
/*  92 */       int j = (arrayOfMaterial = this.materials).length; for (int i = 0; i < j; i++) { Material localMaterial = arrayOfMaterial[i];
/*  93 */         if (localItemStack.getType() == localMaterial) {
/*  94 */           paramPlayerInteractEvent.setCancelled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent)
/*     */   {
/* 103 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 104 */     Block localBlock = paramBlockBreakEvent.getBlock();
/* 105 */     if ((localBlock.getType().equals(Material.MOB_SPAWNER)) && (localBlock.getWorld().getEnvironment().equals(World.Environment.NETHER))) {
/* 106 */       if (!localPlayer.isOp()) {
/* 107 */         paramBlockBreakEvent.setCancelled(true);
/* 108 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.SPAWNERS_DISABLE_BREAK_NETHER.toString());
/*     */       }
/* 110 */     } else if ((localBlock.getType().equals(Material.MOB_SPAWNER)) && (localBlock.getWorld().getEnvironment().equals(World.Environment.THE_END)) && 
/* 111 */       (!localPlayer.isOp())) {
/* 112 */       paramBlockBreakEvent.setCancelled(true);
/* 113 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.SPAWNERS_DISABLE_BREAK_NETHER.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent)
/*     */   {
/* 120 */     Player localPlayer1 = paramPlayerDeathEvent.getEntity();
/* 121 */     Player localPlayer2 = localPlayer1.getKiller();
/*     */     
/* 123 */     PlayerData localPlayerData1 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 124 */     localPlayerData1.addDeath();
/*     */     
/* 126 */     if ((localPlayer2 != null) && (localPlayer2 != localPlayer1)) {
/* 127 */       PlayerData localPlayerData2 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer2);
/* 128 */       localPlayerData2.addKill();
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 134 */     Player localPlayer = paramPlayerJoinEvent.getPlayer();
/* 135 */     paramPlayerJoinEvent.setJoinMessage("");
/* 136 */     if (!localPlayer.hasPlayedBefore()) {
/* 137 */       localPlayer.getInventory().setContents(getInstance().getConfigHandler().getFirstJoinItems());
/*     */     }
/* 139 */     if (localPlayer.hasPermission("notorious.staff")) {
/* 140 */       Bukkit.broadcast(Language.JOIN_MESSAGE.toString().replace("<player>", localPlayer.getName()), "notorious.notify.join");
/*     */     }
/* 142 */     for (String str : getInstance().getConfigHandler().getOnJoinMessage()) {
/* 143 */       localPlayer.sendMessage(str.replace("<player>", localPlayer.getName()));
/*     */     }
/*     */     
/* 146 */     if ((!localPlayer.isOp()) && (localPlayer.getGameMode().equals(GameMode.CREATIVE))) {
/* 147 */       localPlayer.setGameMode(GameMode.SURVIVAL);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onVehicleCreate(VehicleCreateEvent paramVehicleCreateEvent) {
/* 153 */     org.bukkit.entity.Vehicle localVehicle = paramVehicleCreateEvent.getVehicle();
/* 154 */     if ((localVehicle instanceof Boat)) {
/* 155 */       Boat localBoat = (Boat)localVehicle;
/* 156 */       Block localBlock = localBoat.getLocation().add(0.0D, -1.0D, 0.0D).getBlock();
/* 157 */       if ((localBlock.getType() != Material.WATER) && (localBlock.getType() != Material.STATIONARY_WATER)) {
/* 158 */         localBoat.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerPortal(PlayerPortalEvent paramPlayerPortalEvent) {
/* 165 */     Player localPlayer = paramPlayerPortalEvent.getPlayer();
/* 166 */     PlayerTeleportEvent.TeleportCause localTeleportCause = paramPlayerPortalEvent.getCause();
/* 167 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */     
/* 169 */     if (localTeleportCause.equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
/* 170 */       int i = 0;
/* 171 */       int j = 0;
/* 172 */       if (localPlayerData.getPvPTime() > 0) {
/* 173 */         paramPlayerPortalEvent.setCancelled(true);
/* 174 */         i = 1;
/* 175 */       } else if (getInstance().getCombatTagHandler().isCombatTagged(localPlayer)) {
/* 176 */         paramPlayerPortalEvent.setCancelled(true);
/* 177 */         j = 1;
/*     */       } else {
/* 179 */         if (this.notifiedPvPTimer.contains(localPlayer.getUniqueId())) this.notifiedPvPTimer.remove(localPlayer.getUniqueId());
/* 180 */         if (this.notifiedCombat.contains(localPlayer.getUniqueId())) this.notifiedCombat.remove(localPlayer.getUniqueId());
/* 181 */         if (getInstance().getConfigHandler().getEndSpawnLocation() != null) {
/* 182 */           paramPlayerPortalEvent.setCancelled(true);
/* 183 */           for (PotionEffect localPotionEffect : localPlayer.getActivePotionEffects()) {
/* 184 */             if (localPotionEffect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
/* 185 */               localPlayer.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
/*     */             }
/*     */           }
/* 188 */           localPlayer.teleport(getInstance().getConfigHandler().getEndSpawnLocation());
/*     */         } else {
/* 190 */           return;
/*     */         }
/*     */       }
/* 193 */       if (i == 0) {
/* 194 */         if (this.notifiedPvPTimer.contains(localPlayer.getUniqueId())) {
/* 195 */           this.notifiedPvPTimer.remove(localPlayer.getUniqueId());
/*     */         }
/* 197 */       } else if (!this.notifiedPvPTimer.contains(localPlayer.getUniqueId())) {
/* 198 */         this.notifiedPvPTimer.add(localPlayer.getUniqueId());
/* 199 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_END_PORTAL_TELEPORT_DENY.toString());
/*     */       }
/* 201 */       if (j == 0) {
/* 202 */         if (this.notifiedCombat.contains(localPlayer.getUniqueId())) {
/* 203 */           this.notifiedCombat.remove(localPlayer.getUniqueId());
/*     */         }
/* 205 */       } else if (!this.notifiedCombat.contains(localPlayer.getUniqueId())) {
/* 206 */         this.notifiedCombat.add(localPlayer.getUniqueId());
/* 207 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMBATTAG_END_PORTAL_TELEPORT_DENY.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onHungerChange(FoodLevelChangeEvent paramFoodLevelChangeEvent) {
/* 214 */     org.bukkit.entity.HumanEntity localHumanEntity = paramFoodLevelChangeEvent.getEntity();
/* 215 */     if ((localHumanEntity instanceof Player)) {
/* 216 */       Player localPlayer = (Player)localHumanEntity;
/* 217 */       localPlayer.setSaturation(1000.0F);
/* 218 */       localPlayer.setSaturation(10.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 224 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 225 */     paramPlayerQuitEvent.setQuitMessage("");
/* 226 */     if (localPlayer.hasPermission("notorious.staff")) {
/* 227 */       Bukkit.broadcast(Language.QUIT_MESSAGE.toString().replace("<player>", localPlayer.getName()), "notorious.notify.leave");
/*     */     }
/* 229 */     if (this.notifiedPvPTimer.contains(localPlayer.getUniqueId())) this.notifiedPvPTimer.remove(localPlayer.getUniqueId());
/* 230 */     if (this.notifiedCombat.contains(localPlayer.getUniqueId())) this.notifiedCombat.remove(localPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 235 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 236 */     paramPlayerKickEvent.setLeaveMessage("");
/* 237 */     if (this.notifiedPvPTimer.contains(localPlayer.getUniqueId())) this.notifiedPvPTimer.remove(localPlayer.getUniqueId());
/* 238 */     if (this.notifiedCombat.contains(localPlayer.getUniqueId())) this.notifiedCombat.remove(localPlayer.getUniqueId());
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\DynamicPlayerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */