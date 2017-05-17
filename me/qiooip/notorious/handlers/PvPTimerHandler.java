/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.integration.WorldGuard;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class PvPTimerHandler extends Handler implements Listener
/*     */ {
/*     */   private ArrayList<java.util.UUID> notified;
/*     */   
/*     */   public PvPTimerHandler(Notorious paramNotorious)
/*     */   {
/*  34 */     super(paramNotorious);
/*  35 */     this.notified = new ArrayList();
/*     */     
/*  37 */     startTask();
/*  38 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void startTask()
/*     */   {
/*  67 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         Player[] arrayOfPlayer;
/*  45 */         int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  46 */           if (!localPlayer.isDead()) {
/*  47 */             int k = 0;
/*  48 */             PlayerData localPlayerData = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  49 */             if (!WorldGuard.isPvPEnabled(localPlayer)) {
/*  50 */               k = 1;
/*     */             }
/*  52 */             if (localPlayerData.getPvPTime() > 0) {
/*  53 */               if (k == 0) {
/*  54 */                 if (PvPTimerHandler.this.notified.contains(localPlayer.getUniqueId())) {
/*  55 */                   PvPTimerHandler.this.notified.remove(localPlayer.getUniqueId());
/*  56 */                   localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_UNFROZEN_MESSAGE.toString());
/*     */                 }
/*  58 */                 localPlayerData.setPvPTime(localPlayerData.getPvPTime() - 1);
/*  59 */               } else if (!PvPTimerHandler.this.notified.contains(localPlayer.getUniqueId())) {
/*  60 */                 PvPTimerHandler.this.notified.add(localPlayer.getUniqueId());
/*  61 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_FROZEN_MESSAGE.toString());
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  67 */     }.runTaskTimer(Notorious.getInstance(), 20L, 20L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerRespawn(PlayerRespawnEvent paramPlayerRespawnEvent) {
/*  72 */     Player localPlayer = paramPlayerRespawnEvent.getPlayer();
/*     */     
/*  74 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  75 */     localPlayerData.setPvPTime(getInstance().getConfigHandler().getPvPTimerDuration());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent) {
/*  80 */     Player localPlayer = paramPlayerDeathEvent.getEntity();
/*     */     
/*  82 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  83 */     localPlayerData.setPvPTime(getInstance().getConfigHandler().getPvPTimerDuration());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onFoodLevelChange(FoodLevelChangeEvent paramFoodLevelChangeEvent) {
/*  88 */     org.bukkit.entity.HumanEntity localHumanEntity = paramFoodLevelChangeEvent.getEntity();
/*  89 */     if ((localHumanEntity instanceof Player)) {
/*  90 */       Player localPlayer = (Player)localHumanEntity;
/*  91 */       PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  92 */       if (localPlayerData.getPvPTime() > 0) {
/*  93 */         paramFoodLevelChangeEvent.setFoodLevel(20);
/*  94 */         localPlayer.setSaturation(20.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
/*     */   {
/* 102 */     if ((paramEntityDamageByEntityEvent.getEntity() instanceof Player)) { Object localObject1;
/* 103 */       Player localPlayer; Object localObject2; PlayerData localPlayerData1; if ((paramEntityDamageByEntityEvent.getDamager() instanceof Player)) {
/* 104 */         localObject1 = (Player)paramEntityDamageByEntityEvent.getEntity();
/* 105 */         localPlayer = (Player)paramEntityDamageByEntityEvent.getDamager();
/*     */         
/* 107 */         localObject2 = getInstance().getPlayerDataHandler().getByPlayer((Player)localObject1);
/* 108 */         localPlayerData1 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */         
/* 110 */         if ((((PlayerData)localObject2).getPvPTime() > 0) && (localPlayerData1.getPvPTime() > 0)) {
/* 111 */           paramEntityDamageByEntityEvent.setCancelled(true);
/* 112 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_ATTACKER.toString().replace("<name>", ((Player)localObject1).getName()));
/* 113 */         } else if (((PlayerData)localObject2).getPvPTime() > 0) {
/* 114 */           paramEntityDamageByEntityEvent.setCancelled(true);
/* 115 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_ATTACKER.toString().replace("<name>", ((Player)localObject1).getName()));
/* 116 */         } else if (localPlayerData1.getPvPTime() > 0) {
/* 117 */           paramEntityDamageByEntityEvent.setCancelled(true);
/* 118 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_VICTIM.toString());
/*     */         }
/*     */       }
/* 121 */       else if ((paramEntityDamageByEntityEvent.getDamager() instanceof Projectile)) {
/* 122 */         localObject1 = (Projectile)paramEntityDamageByEntityEvent.getDamager();
/* 123 */         if ((((Projectile)localObject1).getShooter() instanceof Player)) {
/* 124 */           localPlayer = (Player)((Projectile)localObject1).getShooter();
/* 125 */           if (localPlayer != paramEntityDamageByEntityEvent.getEntity()) {
/* 126 */             localObject2 = (Player)paramEntityDamageByEntityEvent.getEntity();
/*     */             
/* 128 */             localPlayerData1 = getInstance().getPlayerDataHandler().getByPlayer((Player)localObject2);
/* 129 */             PlayerData localPlayerData2 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */             
/* 131 */             if ((localPlayerData1.getPvPTime() > 0) && (localPlayerData2.getPvPTime() > 0)) {
/* 132 */               paramEntityDamageByEntityEvent.setCancelled(true);
/* 133 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_ATTACKER.toString().replace("<name>", ((Player)localObject2).getName()));
/* 134 */             } else if (localPlayerData1.getPvPTime() > 0) {
/* 135 */               paramEntityDamageByEntityEvent.setCancelled(true);
/* 136 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_VICTIM.toString());
/* 137 */             } else if (localPlayerData2.getPvPTime() > 0) {
/* 138 */               paramEntityDamageByEntityEvent.setCancelled(true);
/* 139 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_PVPDENY_ATTACKER.toString().replace("<name>", ((Player)localObject2).getName()));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 149 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 150 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 151 */     ItemStack localItemStack = paramPlayerInteractEvent.getItem();
/*     */     
/* 153 */     if (localItemStack == null) { return;
/*     */     }
/* 155 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */     
/* 157 */     if ((localPlayerData.getPvPTime() > 0) && (
/* 158 */       (localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK)))) {
/* 159 */       for (Material localMaterial : getInstance().getConfigHandler().getPvPTimerDisabledItems()) {
/* 160 */         if ((localItemStack.getType().equals(localMaterial)) && 
/* 161 */           (!localPlayer.isOp())) {
/* 162 */           paramPlayerInteractEvent.setCancelled(true);
/* 163 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_ITEM_DENY_MESSAGE.toString().replace("<item>", localItemStack.getType().name().replace("_", " ")));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent paramPlayerCommandPreprocessEvent)
/*     */   {
/* 173 */     Player localPlayer = paramPlayerCommandPreprocessEvent.getPlayer();
/* 174 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/* 175 */     if (localPlayerData.getPvPTime() > 0) {
/* 176 */       for (String str : getInstance().getConfigHandler().getPvpTimerDisabledCommands()) {
/* 177 */         if (paramPlayerCommandPreprocessEvent.getMessage().startsWith("/" + str)) {
/* 178 */           paramPlayerCommandPreprocessEvent.setCancelled(true);
/* 179 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_COMMAND_DENY_MESSAGE.toString());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\PvPTimerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */