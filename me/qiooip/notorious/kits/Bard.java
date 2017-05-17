/*     */ package me.qiooip.notorious.kits;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.cooldown.CooldownHandler;
/*     */ import me.qiooip.notorious.handlers.CombatTagHandler;
/*     */ import me.qiooip.notorious.kits.data.BardClickableItem;
/*     */ import me.qiooip.notorious.kits.data.BardHoldItem;
/*     */ import me.qiooip.notorious.kits.utils.BardUtils;
/*     */ import me.qiooip.notorious.kits.utils.ClassType;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.ItemStackUtils;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.StringUtils;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ import org.zencode.mango.factions.claims.ClaimManager;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ import org.zencode.mango.factions.types.SystemFaction;
/*     */ 
/*     */ public class Bard extends me.qiooip.notorious.utils.Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, ClassType> activeKit;
/*     */   private HashMap<UUID, BardWarmup> warmup;
/*     */   private HashMap<UUID, BardPower> bardPowers;
/*     */   private ArrayList<UUID> notified;
/*     */   
/*     */   public Bard(Notorious paramNotorious)
/*     */   {
/*  46 */     super(paramNotorious);
/*  47 */     this.activeKit = new HashMap();
/*  48 */     this.warmup = new HashMap();
/*  49 */     this.bardPowers = new HashMap();
/*  50 */     this.notified = new ArrayList();
/*     */   }
/*     */   
/*     */   public void enable() {
/*  54 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void checkPlayer(Player paramPlayer) {
/*  58 */     ClassType localClassType = ClassType.BARD;
/*  59 */     Object localObject; if (ItemStackUtils.isWearingArmor(paramPlayer, localClassType)) {
/*  60 */       if ((!this.warmup.containsKey(paramPlayer.getUniqueId())) && (!localClassType.getPlayers().contains(paramPlayer.getUniqueId()))) {
/*  61 */         localObject = new BardWarmup(paramPlayer, localClassType);
/*  62 */         ((BardWarmup)localObject).runTaskLater(Notorious.getInstance(), getInstance().getConfigHandler().getPvpClassWarmupDuration() * 20);
/*  63 */         this.warmup.put(paramPlayer.getUniqueId(), localObject);
/*  64 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMING_UP.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*     */     } else {
/*  67 */       if (this.warmup.containsKey(paramPlayer.getUniqueId())) {
/*  68 */         ((BardWarmup)this.warmup.get(paramPlayer.getUniqueId())).cancel();
/*  69 */         this.warmup.remove(paramPlayer.getUniqueId());
/*  70 */         paramPlayer.sendMessage(Language.COMBATCLASS_WARMUP_CANCELLED.toString().replace("<name>", localClassType.getName()));
/*     */       }
/*  72 */       if (localClassType.getPlayers().contains(paramPlayer.getUniqueId())) {
/*  73 */         localClassType.getPlayers().remove(paramPlayer.getUniqueId());
/*  74 */         ((BardPower)this.bardPowers.get(paramPlayer.getUniqueId())).cancel();
/*  75 */         this.bardPowers.remove(paramPlayer.getUniqueId());
/*  76 */         paramPlayer.sendMessage(Language.COMBATCLASS_CLASS_DISABLED.toString().replace("<name>", localClassType.getName()));
/*  77 */         for (Iterator localIterator = localClassType.getEffects().iterator(); localIterator.hasNext();) { localObject = (PotionEffect)localIterator.next();
/*  78 */           paramPlayer.removePotionEffect(((PotionEffect)localObject).getType());
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
/*  88 */         ClassType localClassType = ClassType.BARD;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void checkBardHoldItems()
/*     */   {
/* 158 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 106 */         ClassType localClassType = ClassType.BARD;
/* 107 */         Player[] arrayOfPlayer; int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer1 = arrayOfPlayer[i];
/* 108 */           if (localClassType.getPlayers().contains(localPlayer1.getUniqueId())) {
/* 109 */             PlayerData localPlayerData = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 110 */             PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer1);
/* 111 */             Claim localClaim = Mango.getInstance().getClaimManager().getClaimAt(localPlayer1.getLocation());
/*     */             
/* 113 */             if ((localClaim != null) && ((localClaim.getOwner() instanceof SystemFaction)) && 
/* 114 */               (!((SystemFaction)localClaim.getOwner()).isDeathbanBoolean())) {
/* 115 */               return;
/*     */             }
/*     */             
/* 118 */             if (localPlayerData.getPvPTime() > 0) return;
/* 119 */             if (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer1)) return;
/* 120 */             int k = 0;
/* 121 */             if (localPlayer1 != null) {
/* 122 */               ItemStack localItemStack = localPlayer1.getItemInHand();
/* 123 */               for (BardHoldItem localBardHoldItem : Notorious.getInstance().getBardHoldItemHandler().getBardHoldItems()) {
/* 124 */                 if (localBardHoldItem.getMaterial() == localItemStack.getType()) {
/* 125 */                   if (localPlayerFaction == null) {
/* 126 */                     k = 1;
/*     */                   }
/* 128 */                   if (k == 0) {
/* 129 */                     if (Bard.this.notified.contains(localPlayer1.getUniqueId())) {
/* 130 */                       Bard.this.notified.remove(localPlayer1.getUniqueId());
/*     */                     }
/* 132 */                   } else if (!Bard.this.notified.contains(localPlayer1.getUniqueId())) {
/* 133 */                     Bard.this.notified.add(localPlayer1.getUniqueId());
/* 134 */                     localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_NOT_IN_FACTION_MESSAGE.toString());
/*     */                   }
/* 136 */                   if (k != 0) {
/* 137 */                     return;
/*     */                   }
/* 139 */                   if (localPlayerFaction != null) {
/* 140 */                     for (Player localPlayer2 : localPlayerFaction.getOnlinePlayers()) {
/* 141 */                       if (localBardHoldItem.canBardHimself()) {
/* 142 */                         if ((localPlayer1.getWorld().equals(localPlayer2.getWorld())) && (localPlayer1.getLocation().distance(localPlayer2.getLocation()) <= localBardHoldItem.getDistance())) {
/* 143 */                           BardUtils.addBardHoldPotionEffect(localPlayer2, localBardHoldItem.getPotionEffect());
/*     */                         }
/*     */                       }
/* 146 */                       else if ((!ClassType.BARD.getPlayers().contains(localPlayer1.getUniqueId())) || ((localPlayer1 != localPlayer2) && (localPlayer1.getWorld().equals(localPlayer2.getWorld())) && (localPlayer1.getLocation().distance(localPlayer2.getLocation()) <= localBardHoldItem.getDistance()))) {
/* 147 */                         BardUtils.addBardHoldPotionEffect(localPlayer2, localBardHoldItem.getPotionEffect());
/*     */                       }
/*     */                       
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 158 */     }.runTaskTimer(getInstance(), 20L, 20L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerItemHeld(PlayerItemHeldEvent paramPlayerItemHeldEvent) {
/* 163 */     Player localPlayer1 = paramPlayerItemHeldEvent.getPlayer();
/* 164 */     ClassType localClassType = ClassType.BARD;
/* 165 */     if (localClassType.getPlayers().contains(localPlayer1.getUniqueId())) {
/* 166 */       PlayerData localPlayerData = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 167 */       PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer1);
/* 168 */       Claim localClaim = Mango.getInstance().getClaimManager().getClaimAt(localPlayer1.getLocation());
/*     */       
/* 170 */       if ((localClaim != null) && ((localClaim.getOwner() instanceof SystemFaction)) && 
/* 171 */         (!((SystemFaction)localClaim.getOwner()).isDeathbanBoolean())) {
/* 172 */         return;
/*     */       }
/*     */       
/* 175 */       if (localPlayerData.getPvPTime() > 0) return;
/* 176 */       if (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer1)) return;
/* 177 */       int i = 0;
/* 178 */       if (localPlayer1 != null) {
/* 179 */         ItemStack localItemStack = localPlayer1.getItemInHand();
/* 180 */         for (BardHoldItem localBardHoldItem : Notorious.getInstance().getBardHoldItemHandler().getBardHoldItems()) {
/* 181 */           if (localBardHoldItem.getMaterial() == localItemStack.getType()) {
/* 182 */             if (localPlayerFaction == null) {
/* 183 */               i = 1;
/*     */             }
/* 185 */             if (i == 0) {
/* 186 */               if (this.notified.contains(localPlayer1.getUniqueId())) {
/* 187 */                 this.notified.remove(localPlayer1.getUniqueId());
/*     */               }
/* 189 */             } else if (!this.notified.contains(localPlayer1.getUniqueId())) {
/* 190 */               this.notified.add(localPlayer1.getUniqueId());
/* 191 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_NOT_IN_FACTION_MESSAGE.toString());
/*     */             }
/* 193 */             if (i != 0) {
/* 194 */               return;
/*     */             }
/* 196 */             if (localPlayerFaction != null) {
/* 197 */               for (Player localPlayer2 : localPlayerFaction.getOnlinePlayers()) {
/* 198 */                 if (localBardHoldItem.canBardHimself()) {
/* 199 */                   if ((localPlayer1.getWorld().equals(localPlayer2.getWorld())) && (localPlayer1.getLocation().distance(localPlayer2.getLocation()) <= localBardHoldItem.getDistance())) {
/* 200 */                     BardUtils.addBardHoldPotionEffect(localPlayer2, localBardHoldItem.getPotionEffect());
/*     */                   }
/*     */                 }
/* 203 */                 else if ((!ClassType.BARD.getPlayers().contains(localPlayer1.getUniqueId())) || ((localPlayer1 != localPlayer2) && (localPlayer1.getWorld().equals(localPlayer2.getWorld())) && (localPlayer1.getLocation().distance(localPlayer2.getLocation()) <= localBardHoldItem.getDistance()))) {
/* 204 */                   BardUtils.addBardHoldPotionEffect(localPlayer2, localBardHoldItem.getPotionEffect());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/* 217 */     Player localPlayer1 = paramPlayerInteractEvent.getPlayer();
/* 218 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 219 */     ItemStack localItemStack = paramPlayerInteractEvent.getItem();
/*     */     
/* 221 */     if (localItemStack == null) { return;
/*     */     }
/* 223 */     if ((localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK))) {
/* 224 */       ClassType localClassType = ClassType.BARD;
/* 225 */       if (localClassType.getPlayers().contains(localPlayer1.getUniqueId())) {
/* 226 */         PlayerData localPlayerData = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/* 227 */         PlayerFaction localPlayerFaction1 = Mango.getInstance().getFactionManager().getFaction(localPlayer1);
/* 228 */         Claim localClaim = Mango.getInstance().getClaimManager().getClaimAt(localPlayer1.getLocation());
/*     */         
/* 230 */         if ((localClaim != null) && ((localClaim.getOwner() instanceof SystemFaction)) && 
/* 231 */           (!((SystemFaction)localClaim.getOwner()).isDeathbanBoolean())) {
/* 232 */           return;
/*     */         }
/*     */         
/* 235 */         if (localPlayerData.getPvPTime() > 0) return;
/* 236 */         if (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer1)) { return;
/*     */         }
/* 238 */         for (BardClickableItem localBardClickableItem : Notorious.getInstance().getBardClickableItemHandler().getBardClickableItems()) {
/* 239 */           if (localBardClickableItem.getMaterial() == localItemStack.getType()) {
/* 240 */             if (localPlayerFaction1 == null) {
/* 241 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_NOT_IN_FACTION_MESSAGE.toString());
/* 242 */               return;
/*     */             }
/* 244 */             if (getInstance().getCooldownHandler().isActive(localPlayer1.getUniqueId().toString() + "_bardclickable")) {
/* 245 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEMS_ACTIVE_COOLDOWN.toString().replace("<seconds>", 
/* 246 */                 StringUtils.formatMilisecondsToSeconds(Long.valueOf(getInstance().getCooldownHandler().getMillisecondsLeft(new StringBuilder(String.valueOf(localPlayer1.getUniqueId().toString())).append("_bardclickable").toString())))));
/* 247 */               return;
/*     */             }
/* 249 */             if (!BardUtils.hasEnoughEnergy(localPlayer1, localBardClickableItem.getEnergyNeeded())) {
/* 250 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEMS_NOT_ENOUGH_ENERGY.toString());
/* 251 */               return;
/*     */             }
/* 253 */             if (localPlayerFaction1 != null) { Iterator localIterator2;
/* 254 */               Object localObject; if (!localBardClickableItem.getPotionEffect().getType().equals(org.bukkit.potion.PotionEffectType.WITHER)) {
/* 255 */                 ItemStackUtils.removeOneItem(localPlayer1);
/* 256 */                 localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEM_MESSAGE_BARD_FRIENDLY.toString().replace("<effect>", 
/* 257 */                   StringUtils.getPotionEffectName(localBardClickableItem.getPotionEffect().getType())));
/* 258 */                 for (localIterator2 = localPlayerFaction1.getOnlinePlayers().iterator(); localIterator2.hasNext();) { localObject = (Player)localIterator2.next();
/* 259 */                   if ((localPlayer1.getWorld().equals(((Player)localObject).getWorld())) && (localPlayer1.getLocation().distance(((Player)localObject).getLocation()) <= localBardClickableItem.getDistance())) {
/* 260 */                     if (localPlayer1 != localObject) {
/* 261 */                       ((Player)localObject).sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEM_MESSAGE_OTHERS.toString().replace("<effect>", 
/* 262 */                         StringUtils.getPotionEffectName(localBardClickableItem.getPotionEffect().getType())));
/*     */                     }
/* 264 */                     BardUtils.modifyBardEnergy(localPlayer1, localBardClickableItem.getEnergyNeeded());
/* 265 */                     getInstance().getCooldownHandler().createCooldown(localPlayer1.getUniqueId().toString() + "_bardclickable", getInstance().getConfigHandler().getBardClickableItemsCooldown());
/* 266 */                     BardUtils.addBardClickablePotionEffect((Player)localObject, localBardClickableItem.getPotionEffect());
/*     */                   }
/*     */                 }
/*     */               } else {
/* 270 */                 ItemStackUtils.removeOneItem(localPlayer1);
/* 271 */                 localPlayer1.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEM_MESSAGE_BARD_ENEMY.toString().replace("<effect>", 
/* 272 */                   StringUtils.getPotionEffectName(localBardClickableItem.getPotionEffect().getType())));
/* 273 */                 BardUtils.addBardClickablePotionEffect(localPlayer1, localBardClickableItem.getPotionEffect());
/* 274 */                 BardUtils.modifyBardEnergy(localPlayer1, localBardClickableItem.getEnergyNeeded());
/* 275 */                 getInstance().getCooldownHandler().createCooldown(localPlayer1.getUniqueId().toString() + "_bardclickable", getInstance().getConfigHandler().getBardClickableItemsCooldown());
/* 276 */                 for (localIterator2 = localPlayer1.getNearbyEntities(localBardClickableItem.getDistance(), localBardClickableItem.getDistance(), localBardClickableItem.getDistance()).iterator(); localIterator2.hasNext();) { localObject = (org.bukkit.entity.Entity)localIterator2.next();
/* 277 */                   if ((localObject instanceof Player)) {
/* 278 */                     Player localPlayer2 = (Player)localObject;
/* 279 */                     if ((!Notorious.getInstance().getStaffModeHandler().isInStaffMode(localPlayer2)) && 
/* 280 */                       (!Notorious.getInstance().getVanishHandler().isVanished(localPlayer2))) {
/* 281 */                       PlayerFaction localPlayerFaction2 = Mango.getInstance().getFactionManager().getFaction(localPlayer2);
/* 282 */                       if (localPlayerFaction2 != null) {
/* 283 */                         if ((localPlayerFaction2 != localPlayerFaction1) && (!localPlayerFaction2.getAllies().contains(localPlayerFaction1))) {
/* 284 */                           BardUtils.addBardClickablePotionEffect(localPlayer2, localBardClickableItem.getPotionEffect());
/* 285 */                           getInstance().getCombatTagHandler().applyTagger(localPlayer1, localPlayer2);
/* 286 */                           getInstance().getCombatTagHandler().applyOther(localPlayer1, localPlayer2);
/* 287 */                           localPlayer2.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEM_MESSAGE_OTHERS.toString().replace("<effect>", 
/* 288 */                             StringUtils.getPotionEffectName(localBardClickableItem.getPotionEffect().getType())));
/*     */                         }
/*     */                       } else {
/* 291 */                         BardUtils.addBardClickablePotionEffect(localPlayer2, localBardClickableItem.getPotionEffect());
/* 292 */                         getInstance().getCombatTagHandler().applyTagger(localPlayer1, localPlayer2);
/* 293 */                         getInstance().getCombatTagHandler().applyOther(localPlayer1, localPlayer2);
/* 294 */                         localPlayer2.sendMessage(Language.PREFIX.toString() + Language.BARD_CLICKABLE_ITEM_MESSAGE_OTHERS.toString().replace("<effect>", 
/* 295 */                           StringUtils.getPotionEffectName(localBardClickableItem.getPotionEffect().getType())));
/*     */                       }
/*     */                     }
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public HashMap<UUID, ClassType> getActiveKit()
/*     */   {
/* 310 */     return this.activeKit;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, BardWarmup> getWarmups() {
/* 314 */     return this.warmup;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, BardPower> getBardPowers() {
/* 318 */     return this.bardPowers;
/*     */   }
/*     */   
/*     */   public BardPower getBardPower(Player paramPlayer) {
/* 322 */     return (BardPower)this.bardPowers.get(paramPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayersQuit(org.bukkit.event.player.PlayerQuitEvent paramPlayerQuitEvent) {
/* 327 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 328 */     ClassType localClassType = ClassType.BARD;
/*     */     
/* 330 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 331 */       ((BardWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 332 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 335 */     if (this.bardPowers.containsKey(localPlayer.getUniqueId())) {
/* 336 */       ((BardPower)this.bardPowers.get(localPlayer.getUniqueId())).cancel();
/* 337 */       this.bardPowers.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 340 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 342 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 343 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 344 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 350 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 351 */     ClassType localClassType = ClassType.BARD;
/*     */     
/* 353 */     if (this.warmup.containsKey(localPlayer.getUniqueId())) {
/* 354 */       ((BardWarmup)this.warmup.get(localPlayer.getUniqueId())).cancel();
/* 355 */       this.warmup.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 358 */     if (this.bardPowers.containsKey(localPlayer.getUniqueId())) {
/* 359 */       ((BardPower)this.bardPowers.get(localPlayer.getUniqueId())).cancel();
/* 360 */       this.bardPowers.remove(localPlayer.getUniqueId());
/*     */     }
/*     */     
/* 363 */     this.activeKit.remove(localPlayer.getUniqueId());
/*     */     
/* 365 */     localClassType.getPlayers().remove(localPlayer.getUniqueId());
/* 366 */     for (PotionEffect localPotionEffect : localClassType.getEffects()) {
/* 367 */       localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */     }
/*     */   }
/*     */   
/*     */   public class BardWarmup extends BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     private ClassType classType;
/*     */     
/*     */     public BardWarmup(Player paramPlayer, ClassType paramClassType) {
/* 377 */       this.player = paramPlayer;
/* 378 */       this.classType = paramClassType;
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 383 */       Bard.this.warmup.remove(this.player.getUniqueId());
/* 384 */       Bard.this.activeKit.put(this.player.getUniqueId(), this.classType);
/* 385 */       this.classType.getPlayers().add(this.player.getUniqueId());
/* 386 */       this.player.sendMessage(Language.COMBATCLASS_CLASS_ACTIVATED.toString().replace("<name>", this.classType.getName()));
/*     */       
/* 388 */       Bard.this.bardPowers.put(this.player.getUniqueId(), new Bard.BardPower(Bard.this, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   public class BardPower extends BukkitRunnable
/*     */   {
/*     */     private int power;
/*     */     
/*     */     public BardPower(int paramInt) {
/* 397 */       this.power = paramInt;
/* 398 */       runTaskTimer(Notorious.getInstance(), 20L, 20L);
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/* 403 */       if (this.power < 100) {
/* 404 */         this.power += 1;
/*     */       }
/*     */     }
/*     */     
/*     */     public int getPower() {
/* 409 */       return this.power;
/*     */     }
/*     */     
/*     */     public void setPower(int paramInt) {
/* 413 */       this.power = paramInt;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\Bard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */