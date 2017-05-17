/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.Event.Result;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class FreezeHandler extends Handler implements Listener
/*     */ {
/*     */   private ArrayList<UUID> freezedPlayers;
/*     */   private HashMap<UUID, FreezeTask> freezeTasks;
/*     */   
/*     */   public FreezeHandler(Notorious paramNotorious)
/*     */   {
/*  43 */     super(paramNotorious);
/*  44 */     this.freezedPlayers = new ArrayList();
/*  45 */     this.freezeTasks = new HashMap();
/*     */     
/*  47 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  51 */     this.freezedPlayers.clear();
/*  52 */     this.freezeTasks.clear();
/*     */   }
/*     */   
/*     */   public boolean isFrozen(Player paramPlayer) {
/*  56 */     return this.freezedPlayers.contains(paramPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   public void addFreeze(Player paramPlayer1, Player paramPlayer2) {
/*  60 */     this.freezedPlayers.add(paramPlayer2.getUniqueId());
/*  61 */     int i = getInstance().getConfigHandler().getFreezeMessageInterval() * 20;
/*  62 */     FreezeTask localFreezeTask = new FreezeTask(paramPlayer2);
/*  63 */     localFreezeTask.runTaskTimer(getInstance(), 0L, i);
/*  64 */     this.freezeTasks.put(paramPlayer2.getUniqueId(), localFreezeTask);
/*     */     
/*  66 */     paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_FREEZED_MESSAGE_PLAYER.toString().replace("<target>", paramPlayer2.getName()));
/*     */   }
/*     */   
/*     */   public void removeFreeze(Player paramPlayer1, Player paramPlayer2) {
/*  70 */     if (this.freezeTasks.containsKey(paramPlayer2.getUniqueId())) {
/*  71 */       ((FreezeTask)this.freezeTasks.get(paramPlayer2.getUniqueId())).cancel();
/*  72 */       this.freezeTasks.remove(paramPlayer2.getUniqueId());
/*     */     }
/*  74 */     if (this.freezedPlayers.contains(paramPlayer2.getUniqueId())) {
/*  75 */       this.freezedPlayers.remove(paramPlayer2.getUniqueId());
/*     */     }
/*  77 */     paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_UNFREEZED_MESSAGE_PLAYER.toString().replace("<target>", paramPlayer2.getName()));
/*  78 */     paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.FREEZE_UNFREEZED_MESSAGE_TARGET.toString().replace("<player>", paramPlayer1.getName()));
/*     */   }
/*     */   
/*     */   public ArrayList<UUID> getFreezedPlayers() {
/*  82 */     return this.freezedPlayers;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
/*     */   {
/*  88 */     if ((paramEntityDamageByEntityEvent.getEntity() instanceof Player)) { Object localObject;
/*  89 */       Player localPlayer1; if ((paramEntityDamageByEntityEvent.getDamager() instanceof Player)) {
/*  90 */         localObject = (Player)paramEntityDamageByEntityEvent.getEntity();
/*  91 */         localPlayer1 = (Player)paramEntityDamageByEntityEvent.getDamager();
/*     */         
/*  93 */         if (this.freezedPlayers.contains(localPlayer1.getUniqueId())) {
/*  94 */           paramEntityDamageByEntityEvent.setCancelled(true);
/*  95 */           localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_PVP_DENY_MESSAGE_DAMAGER.toString());
/*  96 */         } else if (this.freezedPlayers.contains(((Player)localObject).getUniqueId())) {
/*  97 */           paramEntityDamageByEntityEvent.setCancelled(true);
/*  98 */           localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_PVP_DENY_MESSAGE_VICTIM.toString().replace("<player>", ((Player)localObject).getName()));
/*     */         }
/* 100 */       } else if ((paramEntityDamageByEntityEvent.getDamager() instanceof Projectile)) {
/* 101 */         localObject = (Projectile)paramEntityDamageByEntityEvent.getDamager();
/* 102 */         if ((((Projectile)localObject).getShooter() instanceof Player)) {
/* 103 */           localPlayer1 = (Player)((Projectile)localObject).getShooter();
/* 104 */           if (localPlayer1 != paramEntityDamageByEntityEvent.getEntity()) {
/* 105 */             Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getEntity();
/*     */             
/* 107 */             if (this.freezedPlayers.contains(localPlayer1.getUniqueId())) {
/* 108 */               paramEntityDamageByEntityEvent.setCancelled(true);
/* 109 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_PVP_DENY_MESSAGE_DAMAGER.toString());
/* 110 */             } else if (this.freezedPlayers.contains(localPlayer2.getUniqueId())) {
/* 111 */               paramEntityDamageByEntityEvent.setCancelled(true);
/* 112 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_PVP_DENY_MESSAGE_VICTIM.toString().replace("<player>", localPlayer2.getName()));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 122 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 123 */     Action localAction = paramPlayerInteractEvent.getAction();
/*     */     
/* 125 */     if (localPlayer.getGameMode().equals(GameMode.CREATIVE)) { return;
/*     */     }
/* 127 */     if ((paramPlayerInteractEvent.hasItem()) && ((localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK))) && (paramPlayerInteractEvent.getItem().getType().equals(Material.ENDER_PEARL)) && 
/* 128 */       (this.freezedPlayers.contains(localPlayer.getUniqueId()))) {
/* 129 */       paramPlayerInteractEvent.setUseItemInHand(Event.Result.DENY);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamage(EntityDamageEvent paramEntityDamageEvent)
/*     */   {
/* 136 */     org.bukkit.entity.Entity localEntity = paramEntityDamageEvent.getEntity();
/*     */     
/* 138 */     if ((localEntity instanceof Player)) {
/* 139 */       Player localPlayer = (Player)localEntity;
/* 140 */       if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 141 */         paramEntityDamageEvent.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent paramPlayerCommandPreprocessEvent) {
/* 148 */     Player localPlayer = paramPlayerCommandPreprocessEvent.getPlayer();
/* 149 */     if (getFreezedPlayers().contains(localPlayer.getUniqueId())) {
/* 150 */       for (String str : getInstance().getConfigHandler().getFreezeDisabledCommands()) {
/* 151 */         if (paramPlayerCommandPreprocessEvent.getMessage().startsWith("/" + str)) {
/* 152 */           paramPlayerCommandPreprocessEvent.setCancelled(true);
/* 153 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.FREEZE_DISABLED_COMMAND_MESSAGE.toString());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMove(PlayerMoveEvent paramPlayerMoveEvent) {
/* 161 */     Player localPlayer = paramPlayerMoveEvent.getPlayer();
/* 162 */     Location localLocation1 = paramPlayerMoveEvent.getFrom();
/* 163 */     Location localLocation2 = paramPlayerMoveEvent.getTo();
/* 164 */     if ((this.freezedPlayers.contains(localPlayer.getUniqueId())) && (
/* 165 */       (localLocation1.getX() != localLocation2.getX()) || (localLocation1.getZ() != localLocation2.getZ()))) {
/* 166 */       localPlayer.teleport(localLocation1);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent)
/*     */   {
/* 173 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/* 174 */     if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 175 */       paramBlockPlaceEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
/* 181 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 182 */     if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 183 */       paramBlockBreakEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 189 */     org.bukkit.entity.HumanEntity localHumanEntity = paramInventoryClickEvent.getWhoClicked();
/* 190 */     if ((localHumanEntity instanceof Player)) {
/* 191 */       Player localPlayer = (Player)localHumanEntity;
/* 192 */       if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 193 */         paramInventoryClickEvent.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerItemDrop(PlayerDropItemEvent paramPlayerDropItemEvent) {
/* 200 */     Player localPlayer = paramPlayerDropItemEvent.getPlayer();
/* 201 */     if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 202 */       paramPlayerDropItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerPickupItem(PlayerPickupItemEvent paramPlayerPickupItemEvent) {
/* 208 */     Player localPlayer = paramPlayerPickupItemEvent.getPlayer();
/* 209 */     if (this.freezedPlayers.contains(localPlayer.getUniqueId())) {
/* 210 */       paramPlayerPickupItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuitEvent(PlayerQuitEvent paramPlayerQuitEvent) {
/* 216 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 217 */     if (this.freezeTasks.containsKey(localPlayer.getUniqueId())) {
/* 218 */       ((FreezeTask)this.freezeTasks.get(localPlayer.getUniqueId())).cancel();
/* 219 */       this.freezeTasks.remove(localPlayer.getUniqueId());
/* 220 */       Bukkit.broadcast(Language.PREFIX.toString() + Language.FREEZE_QUIT_WHEN_FROZEN.toString().replace("<player>", localPlayer.getName()), "notorious.staff");
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKickEvent(PlayerKickEvent paramPlayerKickEvent) {
/* 226 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 227 */     if (this.freezeTasks.containsKey(localPlayer.getUniqueId())) {
/* 228 */       ((FreezeTask)this.freezeTasks.get(localPlayer.getUniqueId())).cancel();
/* 229 */       this.freezeTasks.remove(localPlayer.getUniqueId());
/* 230 */       Bukkit.broadcast(Language.PREFIX.toString() + Language.FREEZE_QUIT_WHEN_FROZEN.toString().replace("<player>", localPlayer.getName()), "notorious.staff");
/*     */     }
/*     */   }
/*     */   
/*     */   public class FreezeTask extends BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     
/*     */     public FreezeTask(Player paramPlayer) {
/* 239 */       this.player = paramPlayer;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 244 */       for (String str : Notorious.getInstance().getConfigHandler().getFreezeCommandMessage()) {
/* 245 */         this.player.sendMessage(str);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\FreezeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */