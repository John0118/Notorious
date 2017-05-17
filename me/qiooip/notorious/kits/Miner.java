/*     */ package me.qiooip.notorious.kits;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.kits.utils.ClassType;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class Miner extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, ClassType> activeKit;
/*     */   private HashMap<UUID, MinerWarmup> warmup;
/*     */   
/*     */   public Miner(Notorious paramNotorious)
/*     */   {
/*  28 */     super(paramNotorious);
/*  29 */     this.activeKit = new HashMap();
/*  30 */     this.warmup = new HashMap();
/*     */   }
/*     */   
/*     */   public void enable() {
/*  34 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void checkPlayer(Player paramPlayer) {
/*  38 */     ClassType localClassType = ClassType.MINER;
/*  39 */     Object localObject; if (me.qiooip.notorious.utils.ItemStackUtils.isWearingArmor(paramPlayer, localClassType)) {
/*  40 */       if ((!this.warmup.containsKey(paramPlayer.getUniqueId())) && (!localClassType.getPlayers().contains(paramPlayer.getUniqueId()))) {
/*  41 */         localObject = new MinerWarmup(paramPlayer, localClassType);
/*  42 */         ((MinerWarmup)localObject).runTaskLater(Notorious.getInstance(), getInstance().getConfigHandler().getPvpClassWarmupDuration() * 20);
/*  43 */         this.warmup.put(paramPlayer.getUniqueId(), localObject);
/*  44 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMING_UP.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*     */     } else {
/*  47 */       if (this.warmup.containsKey(paramPlayer.getUniqueId())) {
/*  48 */         ((MinerWarmup)this.warmup.get(paramPlayer.getUniqueId())).cancel();
/*  49 */         this.warmup.remove(paramPlayer.getUniqueId());
/*  50 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMUP_CANCELLED.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*  52 */       if (localClassType.getPlayers().contains(paramPlayer.getUniqueId())) {
/*  53 */         localClassType.getPlayers().remove(paramPlayer.getUniqueId());
/*  54 */         paramPlayer.sendMessage(Language.COMBATCLASS_CLASS_DISABLED.toString().replace("<name>", localClassType.getName()));
/*  55 */         for (Iterator localIterator = localClassType.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/*  56 */           paramPlayer.removePotionEffect(((PotionEffect)localObject).getType());
/*     */         }
/*     */       }
/*     */     }
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
/*     */   public void checkEffects()
/*     */   {
/*  85 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  66 */         ClassType localClassType = ClassType.MINER;
/*  67 */         Player[] arrayOfPlayer; int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  68 */           if (localClassType.getPlayers().contains(localPlayer.getUniqueId())) {
/*  69 */             for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/*  70 */               if (localPotionEffect.getType().equals(PotionEffectType.INVISIBILITY)) {
/*  71 */                 if (localPlayer.getLocation().getBlockY() < 25) {
/*  72 */                   localPlayer.addPotionEffect(localPotionEffect);
/*     */                 } else {
/*  74 */                   localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */                 }
/*     */               }
/*  77 */               else if (!localPlayer.getActivePotionEffects().contains(localPotionEffect)) {
/*  78 */                 localPlayer.addPotionEffect(localPotionEffect);
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  85 */     }.runTaskTimer(Notorious.getInstance(), 20L, 20L);
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ClassType> getActiveKit() {
/*  89 */     return this.activeKit;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, MinerWarmup> getWarmups() {
/*  93 */     return this.warmup;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayersQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/*  98 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*  99 */     ClassType localClassType = ClassType.MINER;
/*     */     
/* 101 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 102 */       ((MinerWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 103 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 106 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 108 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 109 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 110 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 116 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 117 */     ClassType localClassType = ClassType.MINER;
/*     */     
/* 119 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 120 */       ((MinerWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 121 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 124 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 126 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 127 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 128 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   public class MinerWarmup extends BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     private ClassType classType;
/*     */     
/*     */     public MinerWarmup(Player paramPlayer, ClassType paramClassType) {
/* 138 */       this.player = paramPlayer;
/* 139 */       this.classType = paramClassType;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 144 */       Miner.this.warmup.remove(this.player.getUniqueId());
/* 145 */       Miner.this.activeKit.put(this.player.getUniqueId(), this.classType);
/* 146 */       this.classType.getPlayers().add(this.player.getUniqueId());
/* 147 */       this.player.sendMessage(Language.COMBATCLASS_CLASS_ACTIVATED.toString().replace("<name>", this.classType.getName()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\Miner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */