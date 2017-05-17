/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class PlateElevatorHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, ElevatorTeleportTask> teleportTasks;
/*     */   private ArrayList<UUID> notified;
/*     */   
/*     */   public PlateElevatorHandler(Notorious paramNotorious)
/*     */   {
/*  30 */     super(paramNotorious);
/*  31 */     this.teleportTasks = new HashMap();
/*  32 */     this.notified = new ArrayList();
/*     */     
/*  34 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/*  39 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/*  40 */     Block localBlock = paramBlockPlaceEvent.getBlockPlaced();
/*  41 */     Location localLocation = localBlock.getLocation();
/*     */     
/*  43 */     if (isElevator(localLocation)) {
/*  44 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATE_ELEVATOR_CREATED_MESSAGE.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
/*  50 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/*  51 */     Block localBlock = paramBlockBreakEvent.getBlock();
/*  52 */     Location localLocation = localBlock.getLocation();
/*     */     
/*  54 */     if (isElevatorForDestroying(localLocation)) {
/*  55 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATE_ELEVATOR_DESTROYED_MESSAGE.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMove(PlayerMoveEvent paramPlayerMoveEvent) {
/*  61 */     Player localPlayer = paramPlayerMoveEvent.getPlayer();
/*  62 */     Location localLocation1 = localPlayer.getLocation();
/*  63 */     Location localLocation2 = paramPlayerMoveEvent.getFrom();
/*  64 */     Location localLocation3 = paramPlayerMoveEvent.getTo();
/*     */     
/*  66 */     if ((this.teleportTasks.containsKey(localPlayer.getUniqueId())) && (
/*  67 */       (localLocation2.getBlockX() != localLocation3.getBlockX()) || (localLocation2.getBlockY() != localLocation3.getBlockY()) || (localLocation2.getBlockZ() != localLocation3.getBlockZ()))) {
/*  68 */       ((ElevatorTeleportTask)this.teleportTasks.get(localPlayer.getUniqueId())).cancel();
/*  69 */       this.teleportTasks.remove(localPlayer.getUniqueId());
/*  70 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATE_ELEVATOR_TELEPORT_CANCELLED.toString().replace("<reason>", "Moved"));
/*     */     }
/*     */     
/*     */ 
/*  74 */     int i = 0;
/*  75 */     for (int j = localLocation1.getBlockY(); j < localLocation1.getWorld().getMaxHeight(); j++) {
/*  76 */       if ((isElevator(localLocation1)) && (j != localLocation1.getBlockY()) && (isElevator(new Location(localLocation1.getWorld(), localLocation1.getBlockX(), j, localLocation1.getBlockZ())))) {
/*  77 */         i = 1;
/*     */       }
/*     */     }
/*  80 */     if (i == 0) {
/*  81 */       if (this.notified.contains(localPlayer.getUniqueId())) {
/*  82 */         this.notified.remove(localPlayer.getUniqueId());
/*     */       }
/*  84 */     } else if (!this.notified.contains(localPlayer.getUniqueId())) {
/*  85 */       this.notified.add(localPlayer.getUniqueId());
/*  86 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATE_ELEVATOR_TELEPORT_MESSAGE.toString().replace("<seconds>", 
/*  87 */         String.valueOf(getInstance().getConfigHandler().getElevatorTeleportDelay())));
/*  88 */       ElevatorTeleportTask localElevatorTeleportTask = new ElevatorTeleportTask(localPlayer, localLocation1);
/*  89 */       localElevatorTeleportTask.runTaskLater(getInstance(), getInstance().getConfigHandler().getElevatorTeleportDelay() * 20);
/*  90 */       this.teleportTasks.put(localPlayer.getUniqueId(), localElevatorTeleportTask);
/*  91 */       getInstance().getPlateElevatorTimeHandler().applyWarmup(localPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDamage(EntityDamageEvent paramEntityDamageEvent) {
/*  97 */     org.bukkit.entity.Entity localEntity = paramEntityDamageEvent.getEntity();
/*     */     
/*  99 */     if ((localEntity instanceof Player)) {
/* 100 */       Player localPlayer = (Player)localEntity;
/* 101 */       if (this.teleportTasks.containsKey(localPlayer.getUniqueId())) {
/* 102 */         ((ElevatorTeleportTask)this.teleportTasks.get(localPlayer.getUniqueId())).cancel();
/* 103 */         this.teleportTasks.remove(localPlayer.getUniqueId());
/* 104 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATE_ELEVATOR_TELEPORT_CANCELLED.toString().replace("<reason>", "Were Damaged"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isElevator(Location paramLocation) {
/* 110 */     Location localLocation = new Location(paramLocation.getWorld(), paramLocation.getBlockX(), paramLocation.getBlockY() - 1, paramLocation.getBlockZ());
/* 111 */     Material localMaterial = getInstance().getConfigHandler().getElevatorBottomBlock();
/* 112 */     if ((paramLocation.getBlock().getType().equals(Material.GOLD_PLATE)) && (localLocation.getBlock().getType().equals(localMaterial))) {
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isElevatorForDestroying(Location paramLocation) {
/* 119 */     Location localLocation1 = new Location(paramLocation.getWorld(), paramLocation.getBlockX(), paramLocation.getBlockY() - 1, paramLocation.getBlockZ());
/* 120 */     Location localLocation2 = new Location(paramLocation.getWorld(), paramLocation.getBlockX(), paramLocation.getBlockY() + 1, paramLocation.getBlockZ());
/* 121 */     Material localMaterial = getInstance().getConfigHandler().getElevatorBottomBlock();
/* 122 */     if ((paramLocation.getBlock().getType().equals(Material.GOLD_PLATE)) && (localLocation1.getBlock().getType().equals(localMaterial))) {
/* 123 */       return true;
/*     */     }
/* 125 */     if ((paramLocation.getBlock().getType().equals(localMaterial)) && (localLocation2.getBlock().getType().equals(Material.GOLD_PLATE))) {
/* 126 */       return true;
/*     */     }
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ElevatorTeleportTask> getTeleportTasks() {
/* 132 */     return this.teleportTasks;
/*     */   }
/*     */   
/*     */   public class ElevatorTeleportTask extends org.bukkit.scheduler.BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     private Location location;
/*     */     
/*     */     public ElevatorTeleportTask(Player paramPlayer, Location paramLocation) {
/* 141 */       this.player = paramPlayer;
/* 142 */       this.location = paramLocation;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 147 */       for (int i = this.location.getBlockY(); i < this.location.getWorld().getMaxHeight(); i++) {
/* 148 */         if ((i != this.location.getBlockY()) && (PlateElevatorHandler.this.isElevator(new Location(this.location.getWorld(), this.location.getBlockX(), i, this.location.getBlockZ())))) {
/* 149 */           this.player.teleport(new Location(this.location.getWorld(), this.location.getBlockX() + 0.5D, i, this.location.getBlockZ() + 0.5D, this.player.getLocation().getYaw(), this.player.getLocation().getPitch()));
/*     */         }
/*     */       }
/* 152 */       PlateElevatorHandler.this.teleportTasks.remove(this.player.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\PlateElevatorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */