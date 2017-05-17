/*     */ package me.qiooip.notorious.kits;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.cooldown.CooldownHandler;
/*     */ import me.qiooip.notorious.handlers.ArcherTagTimeHandler;
/*     */ import me.qiooip.notorious.kits.utils.ArcherTag;
/*     */ import me.qiooip.notorious.kits.utils.ClassType;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.StringUtils;
/*     */ import me.qiooip.notorious.vanish.VanishHandler;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ import org.zencode.mango.factions.types.SystemFaction;
/*     */ 
/*     */ public class Archer extends me.qiooip.notorious.utils.Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, ClassType> activeKit;
/*     */   private HashMap<UUID, ArcherWarmup> warmup;
/*     */   private HashMap<UUID, BukkitTask> archerSpeedTask;
/*     */   
/*     */   public Archer(Notorious paramNotorious)
/*     */   {
/*  44 */     super(paramNotorious);
/*  45 */     this.activeKit = new HashMap();
/*  46 */     this.warmup = new HashMap();
/*  47 */     this.archerSpeedTask = new HashMap();
/*     */   }
/*     */   
/*     */   public void enable() {
/*  51 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void checkPlayer(Player paramPlayer) {
/*  55 */     ClassType localClassType = ClassType.ARCHER;
/*  56 */     Object localObject; if (me.qiooip.notorious.utils.ItemStackUtils.isWearingArmor(paramPlayer, localClassType)) {
/*  57 */       if ((!this.warmup.containsKey(paramPlayer.getUniqueId())) && (!localClassType.getPlayers().contains(paramPlayer.getUniqueId()))) {
/*  58 */         localObject = new ArcherWarmup(paramPlayer, localClassType);
/*  59 */         ((ArcherWarmup)localObject).runTaskLater(Notorious.getInstance(), getInstance().getConfigHandler().getPvpClassWarmupDuration() * 20);
/*  60 */         this.warmup.put(paramPlayer.getUniqueId(), localObject);
/*  61 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMING_UP.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*     */     } else {
/*  64 */       if (this.warmup.containsKey(paramPlayer.getUniqueId())) {
/*  65 */         ((ArcherWarmup)this.warmup.get(paramPlayer.getUniqueId())).cancel();
/*  66 */         this.warmup.remove(paramPlayer.getUniqueId());
/*  67 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMUP_CANCELLED.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*  69 */       if (localClassType.getPlayers().contains(paramPlayer.getUniqueId())) {
/*  70 */         localClassType.getPlayers().remove(paramPlayer.getUniqueId());
/*  71 */         paramPlayer.sendMessage(Language.COMBATCLASS_CLASS_DISABLED.toString().replace("<name>", localClassType.getName()));
/*  72 */         for (Iterator localIterator = localClassType.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/*  73 */           paramPlayer.removePotionEffect(((PotionEffect)localObject).getType());
/*     */         }
/*  75 */         if (getInstance().getCooldownHandler().isActive(paramPlayer.getUniqueId().toString() + "_archerspeed")) {
/*  76 */           getInstance().getCooldownHandler().deleteCooldown(paramPlayer.getUniqueId().toString() + "_archerspeed");
/*  77 */           ((BukkitTask)this.archerSpeedTask.get(paramPlayer.getUniqueId())).cancel();
/*  78 */           this.archerSpeedTask.remove(paramPlayer.getUniqueId());
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
/*     */   public void checkEffects()
/*     */   {
/*  99 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  88 */         ClassType localClassType = ClassType.ARCHER;
/*  89 */         Player[] arrayOfPlayer; int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  90 */           if (localClassType.getPlayers().contains(localPlayer.getUniqueId())) {
/*  91 */             for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/*  92 */               if (!localPlayer.getActivePotionEffects().contains(localPotionEffect)) {
/*  93 */                 localPlayer.addPotionEffect(localPotionEffect);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  99 */     }.runTaskTimer(Notorious.getInstance(), 20L, 20L);
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ClassType> getActiveKit() {
/* 103 */     return this.activeKit;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ArcherWarmup> getWarmups() {
/* 107 */     return this.warmup;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 112 */     final Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 113 */     Action localAction = paramPlayerInteractEvent.getAction();
/*     */     
/* 115 */     if ((ClassType.ARCHER.getPlayers().contains(localPlayer.getUniqueId())) && 
/* 116 */       ((localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK))) && 
/* 117 */       (localPlayer.getItemInHand().getType().equals(Material.SUGAR))) {
/* 118 */       String str1 = localPlayer.getUniqueId() + "_archerspeed";
/* 119 */       int i = getInstance().getConfigHandler().getArcherSpeedboostCooldown();
/* 120 */       if (getInstance().getCooldownHandler().isActive(str1)) {
/* 121 */         paramPlayerInteractEvent.setCancelled(true);
/* 122 */         String str2 = StringUtils.formatMilisecondsToSeconds(Long.valueOf(getInstance().getCooldownHandler().getMillisecondsLeft(str1)));
/* 123 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.ARCHER_SPEEDBOOST_COOLDOWN_DENY.toString().replace("<seconds>", str2));
/*     */       } else {
/* 125 */         getInstance().getCooldownHandler().createCooldown(str1, i);
/* 126 */         int j = getInstance().getConfigHandler().getArcherSpeedboostDuration();
/* 127 */         int k = getInstance().getConfigHandler().getArcherSpeedboostLevel() - 1;
/* 128 */         localPlayer.removePotionEffect(PotionEffectType.SPEED);
/* 129 */         localPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, j * 20, k));
/* 130 */         me.qiooip.notorious.utils.ItemStackUtils.removeOneItem(localPlayer);
/* 131 */         this.archerSpeedTask.put(localPlayer.getUniqueId(), new BukkitRunnable() {
/*     */           public void run() {
/* 133 */             for (PotionEffect localPotionEffect : ClassType.ARCHER.getEffects()) {
/* 134 */               if (localPotionEffect.getType().equals(PotionEffectType.SPEED)) {
/* 135 */                 localPlayer.removePotionEffect(PotionEffectType.SPEED);
/* 136 */                 localPlayer.addPotionEffect(localPotionEffect);
/*     */               }
/*     */             }
/*     */           }
/* 140 */         }.runTaskLater(getInstance(), j * 20 - 2));
/* 141 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.ARCHER_SPEEDBOOST_ACTIVATED.toString().replace("<seconds>", String.valueOf(j)));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
/*     */   {
/* 151 */     if (paramEntityDamageByEntityEvent.isCancelled()) return;
/* 152 */     if (((paramEntityDamageByEntityEvent.getEntity() instanceof Player)) && 
/* 153 */       ((paramEntityDamageByEntityEvent.getDamager() instanceof Arrow))) {
/* 154 */       Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getEntity();
/* 155 */       Arrow localArrow = (Arrow)paramEntityDamageByEntityEvent.getDamager();
/* 156 */       if ((localArrow.getShooter() instanceof Player)) {
/* 157 */         Player localPlayer2 = (Player)localArrow.getShooter();
/*     */         
/* 159 */         PlayerData localPlayerData1 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 160 */         PlayerData localPlayerData2 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer2);
/*     */         
/* 162 */         PlayerFaction localPlayerFaction1 = Mango.getInstance().getFactionManager().getFaction(localPlayer1);
/* 163 */         PlayerFaction localPlayerFaction2 = Mango.getInstance().getFactionManager().getFaction(localPlayer2);
/*     */         
/* 165 */         Claim localClaim1 = Mango.getInstance().getClaimManager().getClaimAt(localPlayer1.getLocation());
/* 166 */         Claim localClaim2 = Mango.getInstance().getClaimManager().getClaimAt(localPlayer2.getLocation());
/*     */         
/* 168 */         if ((localClaim1 != null) && ((localClaim1.getOwner() instanceof SystemFaction)) && 
/* 169 */           (!((SystemFaction)localClaim1.getOwner()).isDeathbanBoolean())) {
/* 170 */           return;
/*     */         }
/*     */         
/* 173 */         if ((localClaim2 != null) && ((localClaim2.getOwner() instanceof SystemFaction)) && 
/* 174 */           (!((SystemFaction)localClaim2.getOwner()).isDeathbanBoolean())) {
/* 175 */           return;
/*     */         }
/*     */         
/*     */ 
/* 179 */         if ((localPlayerData1.getPvPTime() > 0) || (localPlayerData2.getPvPTime() > 0)) return;
/* 180 */         if ((localPlayerFaction1 != null) && (localPlayerFaction2 != null) && (localPlayerFaction1.equals(localPlayerFaction2))) return;
/* 181 */         if ((!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer1)) || (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer2))) return;
/* 182 */         if ((Notorious.getInstance().getFreezeHandler().isFrozen(localPlayer1)) || (Notorious.getInstance().getFreezeHandler().isFrozen(localPlayer2))) return;
/* 183 */         if ((Notorious.getInstance().getVanishHandler().isVanished(localPlayer1)) || (Notorious.getInstance().getVanishHandler().isVanished(localPlayer2))) return;
/* 184 */         if ((localPlayerFaction1 != null) && (localPlayerFaction2 != null) && (localPlayerFaction1.getAllies().contains(localPlayerFaction2))) { return;
/*     */         }
/* 186 */         if ((ClassType.ARCHER.getPlayers().contains(localPlayer2.getUniqueId())) && 
/* 187 */           (localPlayer2 != localPlayer1)) {
/* 188 */           updateArcherTagForPlayer(localPlayer2, localPlayer1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler(priority=org.bukkit.event.EventPriority.MONITOR)
/*     */   public void onPlayersQuit(PlayerQuitEvent paramPlayerQuitEvent)
/*     */   {
/* 198 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 199 */     ClassType localClassType = ClassType.ARCHER;
/*     */     
/* 201 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 202 */       ((ArcherWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 203 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 206 */     if (this.archerSpeedTask.containsKey(localPlayer.getUniqueId())) {
/* 207 */       ((BukkitTask)this.archerSpeedTask.get(localPlayer.getUniqueId())).cancel();
/* 208 */       this.archerSpeedTask.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 211 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 213 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 214 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 215 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler(priority=org.bukkit.event.EventPriority.MONITOR)
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 221 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 222 */     ClassType localClassType = ClassType.ARCHER;
/*     */     
/* 224 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 225 */       ((ArcherWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 226 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 229 */     if (this.archerSpeedTask.containsKey(localPlayer.getUniqueId())) {
/* 230 */       ((BukkitTask)this.archerSpeedTask.get(localPlayer.getUniqueId())).cancel();
/* 231 */       this.archerSpeedTask.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 234 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 236 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 237 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 238 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateArcherTagForPlayer(Player paramPlayer1, final Player paramPlayer2) {
/* 243 */     final ArcherTag localArcherTag1 = ArcherTag.TAG_1;
/* 244 */     final ArcherTag localArcherTag2 = ArcherTag.TAG_2;
/* 245 */     final ArcherTag localArcherTag3 = ArcherTag.TAG_3;
/*     */     Iterator localIterator;
/* 247 */     Object localObject; if ((!localArcherTag1.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) && (!localArcherTag2.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) && (!localArcherTag3.getTaggedPlayers().contains(paramPlayer2.getUniqueId()))) {
/* 248 */       localArcherTag1.getTaggedPlayers().add(paramPlayer2.getUniqueId());
/* 249 */       for (localIterator = localArcherTag1.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/* 250 */         paramPlayer2.addPotionEffect((PotionEffect)localObject);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 258 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 254 */           if (localArcherTag1.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) {
/* 255 */             localArcherTag1.getTaggedPlayers().remove(paramPlayer2.getUniqueId());
/*     */           }
/*     */         }
/* 258 */       }.runTaskLater(getInstance(), 200L);
/* 259 */       getInstance().getArcherTagTimeHandler().applyWarmup(paramPlayer2);
/* 260 */       localObject = StringUtils.getEffectNamesList(localArcherTag1.getEffects());
/* 261 */       paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_VICTIM.toString().replace("<tagger>", paramPlayer1.getName()).replace("<level>", localArcherTag1.getLevel()).replace("<effect>", (CharSequence)localObject));
/* 262 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_TAGGER.toString().replace("<victim>", paramPlayer2.getName()).replace("<level>", localArcherTag1.getLevel()).replace("<effect>", (CharSequence)localObject));
/* 263 */     } else if (localArcherTag1.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) {
/* 264 */       localArcherTag1.getTaggedPlayers().remove(paramPlayer2.getUniqueId());
/* 265 */       localArcherTag2.getTaggedPlayers().add(paramPlayer2.getUniqueId());
/* 266 */       for (localIterator = localArcherTag2.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/* 267 */         paramPlayer2.addPotionEffect((PotionEffect)localObject);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 275 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 271 */           if (localArcherTag2.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) {
/* 272 */             localArcherTag2.getTaggedPlayers().remove(paramPlayer2.getUniqueId());
/*     */           }
/*     */         }
/* 275 */       }.runTaskLater(getInstance(), 200L);
/* 276 */       getInstance().getArcherTagTimeHandler().applyWarmup(paramPlayer2);
/* 277 */       localObject = StringUtils.getEffectNamesList(localArcherTag2.getEffects());
/* 278 */       paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_VICTIM.toString().replace("<tagger>", paramPlayer1.getName()).replace("<level>", localArcherTag2.getLevel()).replace("<effect>", (CharSequence)localObject));
/* 279 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_TAGGER.toString().replace("<victim>", paramPlayer2.getName()).replace("<level>", localArcherTag2.getLevel()).replace("<effect>", (CharSequence)localObject));
/* 280 */     } else if (localArcherTag2.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) {
/* 281 */       localArcherTag2.getTaggedPlayers().remove(paramPlayer2.getUniqueId());
/* 282 */       localArcherTag3.getTaggedPlayers().add(paramPlayer2.getUniqueId());
/* 283 */       for (localIterator = localArcherTag3.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/* 284 */         paramPlayer2.addPotionEffect((PotionEffect)localObject);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 292 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 288 */           if (localArcherTag3.getTaggedPlayers().contains(paramPlayer2.getUniqueId())) {
/* 289 */             localArcherTag3.getTaggedPlayers().remove(paramPlayer2.getUniqueId());
/*     */           }
/*     */         }
/* 292 */       }.runTaskLater(getInstance(), 200L);
/* 293 */       getInstance().getArcherTagTimeHandler().applyWarmup(paramPlayer2);
/* 294 */       localObject = StringUtils.getEffectNamesList(localArcherTag3.getEffects());
/* 295 */       paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_VICTIM.toString().replace("<tagger>", paramPlayer1.getName()).replace("<level>", localArcherTag3.getLevel()).replace("<effect>", (CharSequence)localObject));
/* 296 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.ARCHER_TAG_TAGGER.toString().replace("<victim>", paramPlayer2.getName()).replace("<level>", localArcherTag3.getLevel()).replace("<effect>", (CharSequence)localObject));
/*     */     }
/*     */   }
/*     */   
/*     */   public class ArcherWarmup extends BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     private ClassType classType;
/*     */     
/*     */     public ArcherWarmup(Player paramPlayer, ClassType paramClassType) {
/* 306 */       this.player = paramPlayer;
/* 307 */       this.classType = paramClassType;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 312 */       Archer.this.warmup.remove(this.player.getUniqueId());
/* 313 */       Archer.this.activeKit.put(this.player.getUniqueId(), this.classType);
/* 314 */       this.classType.getPlayers().add(this.player.getUniqueId());
/* 315 */       this.player.sendMessage(Language.COMBATCLASS_CLASS_ACTIVATED.toString().replace("<name>", this.classType.getName()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\Archer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */