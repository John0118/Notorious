/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.StringUtils;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class DeathMessageHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   public DeathMessageHandler(Notorious paramNotorious)
/*     */   {
/*  26 */     super(paramNotorious);
/*     */     
/*  28 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   @org.bukkit.event.EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent)
/*     */   {
/*  34 */     Player localPlayer = paramPlayerDeathEvent.getEntity();
/*  35 */     EntityDamageEvent.DamageCause localDamageCause = null;
/*  36 */     if (localPlayer.getLastDamageCause() == null) {
/*  37 */       localDamageCause = EntityDamageEvent.DamageCause.CUSTOM;
/*     */     } else {
/*  39 */       localDamageCause = localPlayer.getLastDamageCause().getCause();
/*     */     }
/*     */     
/*  42 */     String str = "";
/*     */     
/*  44 */     if ((localPlayer.getLastDamageCause() instanceof EntityDamageByEntityEvent)) {
/*  45 */       EntityDamageByEntityEvent localEntityDamageByEntityEvent = (EntityDamageByEntityEvent)localPlayer.getLastDamageCause();
/*  46 */       Object localObject1; if (localDamageCause.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
/*  47 */         if ((localEntityDamageByEntityEvent.getDamager() instanceof Player)) {
/*  48 */           localObject1 = (Player)localEntityDamageByEntityEvent.getDamager();
/*  49 */           if ((((Player)localObject1).getItemInHand() != null) && (((Player)localObject1).getItemInHand().getType() != Material.AIR)) {
/*  50 */             str = 
/*  51 */               Language.DEATHMESSAGE_REASON_ENTITY_ATTACK_PLAYER_ITEM.toString().replace("<player>", getPlayerName(localPlayer)).replace("<killer>", getKillerName((Player)localObject1)).replace("<item>", itemToName(((Player)localObject1).getItemInHand()));
/*     */           } else {
/*  53 */             str = Language.DEATHMESSAGE_REASON_ENTITY_ATTACK_PLAYER_NO_ITEM.toString().replace("<player>", getPlayerName(localPlayer)).replace("<killer>", 
/*  54 */               getKillerName((Player)localObject1));
/*     */           }
/*     */         } else {
/*  57 */           localObject1 = localEntityDamageByEntityEvent.getDamager();
/*  58 */           str = Language.DEATHMESSAGE_REASON_ENTITY_ATTACK_ENTITY.toString().replace("<player>", getPlayerName(localPlayer)).replace("<entity>", 
/*  59 */             StringUtils.getEntityName((Entity)localObject1));
/*     */         }
/*  61 */       } else if (localDamageCause.equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
/*  62 */         localObject1 = (Projectile)localEntityDamageByEntityEvent.getDamager();
/*  63 */         Object localObject2; if ((((Projectile)localObject1).getShooter() instanceof Player)) {
/*  64 */           localObject2 = (Player)((Projectile)localObject1).getShooter();
/*  65 */           if (localDamageCause.equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
/*  66 */             if ((((Player)localObject2).getItemInHand() != null) && (!((Player)localObject2).getItemInHand().getType().equals(Material.AIR)) && (((Player)localObject2).getItemInHand().getType().equals(Material.BOW))) {
/*  67 */               str = 
/*  68 */                 Language.DEATHMESSAGE_REASON_PROJECTILE_ITEM.toString().replace("<player>", getPlayerName(localPlayer)).replace("<killer>", getKillerName((Player)localObject2)).replace("<item>", itemToName(((Player)localObject2).getItemInHand()));
/*     */             } else {
/*  70 */               str = Language.DEATHMESSAGE_REASON_PROJECTILE_NO_ITEM.toString().replace("<player>", getPlayerName(localPlayer)).replace("<killer>", 
/*  71 */                 getKillerName((Player)localObject2));
/*     */             }
/*     */           }
/*     */         } else {
/*  75 */           localObject2 = ((Projectile)localObject1).getShooter();
/*  76 */           str = Language.DEATHMESSAGE_REASON_PROJECTILE_ENTITY.toString().replace("<player>", getPlayerName(localPlayer)).replace("<entity>", 
/*  77 */             StringUtils.getEntityName((Entity)localObject2));
/*     */         }
/*  79 */       } else if (localDamageCause.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
/*  80 */         if ((localEntityDamageByEntityEvent.getDamager() instanceof Creeper)) {
/*  81 */           str = Language.DEATHMESSAGE_REASON_ENTITY_EXPLOSION.toString().replace("<player>", getPlayerName(localPlayer));
/*     */         }
/*  83 */       } else if (localDamageCause.equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
/*  84 */         str = Language.DEATHMESSAGE_REASON_FALLING_BLOCK.toString().replace("<player>", getPlayerName(localPlayer));
/*  85 */       } else if (localDamageCause.equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
/*  86 */         str = Language.DEATHMESSAGE_REASON_LIGHTNING.toString().replace("<player>", getPlayerName(localPlayer));
/*     */       }
/*     */     } else {
/*  89 */       switch (localDamageCause) {
/*  90 */       case LAVA:  str = Language.DEATHMESSAGE_REASON_BLOCK_EXPLOSION.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  91 */       case BLOCK_EXPLOSION:  str = Language.DEATHMESSAGE_REASON_CONTACT.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  92 */       case WITHER:  str = Language.DEATHMESSAGE_REASON_CUSTOM.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  93 */       case FIRE_TICK:  str = Language.DEATHMESSAGE_REASON_DROWNING.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  94 */       case ENTITY_ATTACK:  str = Language.DEATHMESSAGE_REASON_FALL.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  95 */       case ENTITY_EXPLOSION:  str = Language.DEATHMESSAGE_REASON_FIRE.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  96 */       case FALL:  str = Language.DEATHMESSAGE_REASON_FIRE_TICK.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  97 */       case FIRE:  str = Language.DEATHMESSAGE_REASON_LAVA.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  98 */       case SUFFOCATION:  str = Language.DEATHMESSAGE_REASON_MAGIC.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*  99 */       case FALLING_BLOCK:  str = Language.DEATHMESSAGE_REASON_MELTING.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 100 */       case STARVATION:  str = Language.DEATHMESSAGE_REASON_POISON.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 101 */       case PROJECTILE:  str = Language.DEATHMESSAGE_REASON_STARVATION.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 102 */       case DROWNING:  str = Language.DEATHMESSAGE_REASON_SUFFOCATION.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 103 */       case POISON:  str = Language.DEATHMESSAGE_REASON_SUICIDE.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 104 */       case VOID:  str = Language.DEATHMESSAGE_REASON_THORNS.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 105 */       case MAGIC:  str = Language.DEATHMESSAGE_REASON_VOID.toString().replace("<player>", getPlayerName(localPlayer)); break;
/* 106 */       case SUICIDE:  str = Language.DEATHMESSAGE_REASON_WITHER.toString().replace("<player>", getPlayerName(localPlayer)); break;
/*     */       }
/*     */       
/*     */     }
/* 110 */     paramPlayerDeathEvent.setDeathMessage(str);
/*     */   }
/*     */   
/*     */   public String getPlayerName(Player paramPlayer) {
/* 114 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/* 115 */     return Language.DEATHMESSAGE_PLAYER_NAME_FORMAT.toString().replace("<player>", paramPlayer.getName()).replace("<kills>", String.valueOf(localPlayerData.getKills()));
/*     */   }
/*     */   
/*     */   public String getKillerName(Player paramPlayer) {
/* 119 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/* 120 */     return Language.DEATHMESSAGE_PLAYER_NAME_FORMAT.toString().replace("<player>", paramPlayer.getName()).replace("<kills>", String.valueOf(localPlayerData.getKills()));
/*     */   }
/*     */   
/*     */   public String itemToName(ItemStack paramItemStack) {
/* 124 */     if (paramItemStack.hasItemMeta()) {
/* 125 */       ItemMeta localItemMeta = paramItemStack.getItemMeta();
/* 126 */       if (localItemMeta.hasDisplayName()) {
/* 127 */         return localItemMeta.getDisplayName();
/*     */       }
/*     */     }
/* 130 */     return materialToName(paramItemStack.getType());
/*     */   }
/*     */   
/*     */   public static String materialToName(Enum<?> paramEnum) {
/* 134 */     return org.apache.commons.lang.WordUtils.capitalize(paramEnum.name().replace("_", " ").toLowerCase());
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\DeathMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */