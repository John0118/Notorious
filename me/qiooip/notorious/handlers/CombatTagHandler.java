/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.integration.WorldGuard;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.vanish.VanishHandler;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ import org.zencode.mango.factions.claims.ClaimManager;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ import org.zencode.mango.factions.types.SystemFaction;
/*     */ 
/*     */ public class CombatTagHandler extends me.qiooip.notorious.utils.Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, Long> tagged;
/*     */   
/*     */   public CombatTagHandler(Notorious paramNotorious)
/*     */   {
/*  32 */     super(paramNotorious);
/*  33 */     this.tagged = new HashMap();
/*     */     
/*  35 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  39 */     this.tagged.clear();
/*     */   }
/*     */   
/*     */   public boolean isCombatTagged(Player paramPlayer) {
/*  43 */     return (this.tagged.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.tagged.get(paramPlayer.getUniqueId())).longValue());
/*     */   }
/*     */   
/*     */   public void applyTagger(Player paramPlayer1, Player paramPlayer2) {
/*  47 */     if (!this.tagged.containsKey(paramPlayer1.getUniqueId())) {
/*  48 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.COMBATTAG_MESSAGE_TAGGER.toString().replace("<name>", paramPlayer2.getName()).replace("<time>", String.valueOf(getInstance().getConfigFile().getInt("combat-tag-duration"))));
/*     */     }
/*  50 */     this.tagged.put(paramPlayer1.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getCombatTagDuration() * 1000));
/*     */   }
/*     */   
/*     */   public void applyOther(Player paramPlayer1, Player paramPlayer2) {
/*  54 */     if (!this.tagged.containsKey(paramPlayer2.getUniqueId())) {
/*  55 */       paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.COMBATTAG_MESSAGE_OTHER.toString().replace("<name>", paramPlayer1.getName()).replace("<time>", String.valueOf(getInstance().getConfigFile().getInt("combat-tag-duration"))));
/*     */     }
/*  57 */     this.tagged.put(paramPlayer2.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getCombatTagDuration() * 1000));
/*     */   }
/*     */   
/*     */   public long getMillisecondsLeft(Player paramPlayer) {
/*  61 */     if (this.tagged.containsKey(paramPlayer.getUniqueId())) {
/*  62 */       return Math.max(((Long)this.tagged.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*     */     }
/*  64 */     return 0L;
/*     */   }
/*     */   
/*     */   public Long getByPlayerUUID(UUID paramUUID) {
/*  68 */     return (Long)this.tagged.get(paramUUID.toString());
/*     */   }
/*     */   
/*     */   public HashMap<UUID, Long> getTaggedPlayers() {
/*  72 */     return this.tagged;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
/*     */   {
/*  78 */     if ((paramEntityDamageByEntityEvent.getEntity() instanceof Player)) { Object localObject1;
/*  79 */       Player localPlayer; Object localObject2; PlayerData localPlayerData; Object localObject3; PlayerFaction localPlayerFaction; Object localObject4; Claim localClaim1; if ((paramEntityDamageByEntityEvent.getDamager() instanceof Player)) {
/*  80 */         localObject1 = (Player)paramEntityDamageByEntityEvent.getEntity();
/*  81 */         localPlayer = (Player)paramEntityDamageByEntityEvent.getDamager();
/*     */         
/*  83 */         localObject2 = getInstance().getPlayerDataHandler().getByPlayer((Player)localObject1);
/*  84 */         localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */         
/*  86 */         localObject3 = Mango.getInstance().getFactionManager().getFaction((Player)localObject1);
/*  87 */         localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/*     */         
/*  89 */         localObject4 = Mango.getInstance().getClaimManager().getClaimAt(((Player)localObject1).getLocation());
/*  90 */         localClaim1 = Mango.getInstance().getClaimManager().getClaimAt(localPlayer.getLocation());
/*     */         
/*  92 */         if ((localObject4 != null) && ((((Claim)localObject4).getOwner() instanceof SystemFaction)) && 
/*  93 */           (!((SystemFaction)((Claim)localObject4).getOwner()).isDeathbanBoolean())) {
/*  94 */           return;
/*     */         }
/*     */         
/*  97 */         if ((localClaim1 != null) && ((localClaim1.getOwner() instanceof SystemFaction)) && 
/*  98 */           (!((SystemFaction)((Claim)localObject4).getOwner()).isDeathbanBoolean())) {
/*  99 */           return;
/*     */         }
/*     */         
/*     */ 
/* 103 */         if ((((PlayerData)localObject2).getPvPTime() > 0) || (localPlayerData.getPvPTime() > 0)) return;
/* 104 */         if ((localObject3 != null) && (localPlayerFaction != null) && (localObject3.equals(localPlayerFaction))) return;
/* 105 */         if ((!WorldGuard.isPvPEnabled((Player)localObject1)) || (!WorldGuard.isPvPEnabled(localPlayer))) return;
/* 106 */         if ((Notorious.getInstance().getFreezeHandler().isFrozen((Player)localObject1)) || (Notorious.getInstance().getFreezeHandler().isFrozen(localPlayer))) return;
/* 107 */         if ((Notorious.getInstance().getVanishHandler().isVanished((Player)localObject1)) || (Notorious.getInstance().getVanishHandler().isVanished(localPlayer))) return;
/* 108 */         if ((localObject3 != null) && (localPlayerFaction != null) && (((PlayerFaction)localObject3).getAllies().contains(localPlayerFaction))) { return;
/*     */         }
/* 110 */         applyTagger(localPlayer, (Player)localObject1);
/* 111 */         applyOther(localPlayer, (Player)localObject1);
/* 112 */       } else if ((paramEntityDamageByEntityEvent.getDamager() instanceof Projectile)) {
/* 113 */         localObject1 = (Projectile)paramEntityDamageByEntityEvent.getDamager();
/* 114 */         if ((((Projectile)localObject1).getShooter() instanceof Player)) {
/* 115 */           localPlayer = (Player)((Projectile)localObject1).getShooter();
/* 116 */           if (localPlayer != paramEntityDamageByEntityEvent.getEntity()) {
/* 117 */             localObject2 = (Player)paramEntityDamageByEntityEvent.getEntity();
/*     */             
/* 119 */             localPlayerData = getInstance().getPlayerDataHandler().getByPlayer((Player)localObject2);
/* 120 */             localObject3 = getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*     */             
/* 122 */             localPlayerFaction = Mango.getInstance().getFactionManager().getFaction((Player)localObject2);
/* 123 */             localObject4 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/*     */             
/*     */ 
/* 126 */             localClaim1 = Mango.getInstance().getClaimManager().getClaimAt(((Player)localObject2).getLocation());
/* 127 */             Claim localClaim2 = Mango.getInstance().getClaimManager().getClaimAt(localPlayer.getLocation());
/*     */             
/* 129 */             if ((localClaim1 != null) && ((localClaim1.getOwner() instanceof SystemFaction)) && 
/* 130 */               (!((SystemFaction)localClaim1.getOwner()).isDeathbanBoolean())) {
/* 131 */               return;
/*     */             }
/*     */             
/* 134 */             if ((localClaim2 != null) && ((localClaim2.getOwner() instanceof SystemFaction)) && 
/* 135 */               (!((SystemFaction)localClaim2.getOwner()).isDeathbanBoolean())) {
/* 136 */               return;
/*     */             }
/*     */             
/*     */ 
/* 140 */             if ((localPlayerData.getPvPTime() > 0) || (((PlayerData)localObject3).getPvPTime() > 0)) return;
/* 141 */             if ((localPlayerFaction != null) && (localObject4 != null) && (localPlayerFaction.equals(localObject4))) return;
/* 142 */             if ((!WorldGuard.isPvPEnabled((Player)localObject2)) || (!WorldGuard.isPvPEnabled(localPlayer))) return;
/* 143 */             if ((Notorious.getInstance().getFreezeHandler().isFrozen((Player)localObject2)) || (Notorious.getInstance().getFreezeHandler().isFrozen(localPlayer))) return;
/* 144 */             if ((Notorious.getInstance().getVanishHandler().isVanished((Player)localObject2)) || (Notorious.getInstance().getVanishHandler().isVanished(localPlayer))) return;
/* 145 */             if ((localPlayerFaction != null) && (localObject4 != null) && (localPlayerFaction.getAllies().contains(localObject4))) { return;
/*     */             }
/* 147 */             applyTagger(localPlayer, (Player)localObject2);
/* 148 */             applyOther(localPlayer, (Player)localObject2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent paramPlayerCommandPreprocessEvent) {
/* 157 */     Player localPlayer = paramPlayerCommandPreprocessEvent.getPlayer();
/* 158 */     if (isCombatTagged(localPlayer)) {
/* 159 */       for (String str : getInstance().getConfigHandler().getCombattagDisabledCommands()) {
/* 160 */         if (paramPlayerCommandPreprocessEvent.getMessage().startsWith("/" + str)) {
/* 161 */           paramPlayerCommandPreprocessEvent.setCancelled(true);
/* 162 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMBATTAG_COMMAND_DENY_MESSAGE.toString());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent paramPlayerDeathEvent) {
/* 170 */     Player localPlayer = paramPlayerDeathEvent.getEntity();
/* 171 */     if (this.tagged.containsKey(localPlayer.getUniqueId())) {
/* 172 */       this.tagged.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerRespawn(PlayerRespawnEvent paramPlayerRespawnEvent) {
/* 178 */     Player localPlayer = paramPlayerRespawnEvent.getPlayer();
/* 179 */     if (this.tagged.containsKey(localPlayer.getUniqueId())) {
/* 180 */       this.tagged.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 186 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 187 */     if (this.tagged.containsKey(localPlayer.getUniqueId())) {
/* 188 */       this.tagged.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 194 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 195 */     if (this.tagged.containsKey(localPlayer.getUniqueId())) {
/* 196 */       this.tagged.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\CombatTagHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */