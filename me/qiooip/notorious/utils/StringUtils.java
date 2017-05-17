/*     */ package me.qiooip.notorious.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import me.qiooip.notorious.vanish.OptionType;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.World.Environment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class StringUtils
/*     */ {
/*     */   public static String formatMilisecondsToSeconds(Long paramLong)
/*     */   {
/*  19 */     float f = ((float)paramLong.longValue() + 0.0F) / 1000.0F;
/*     */     
/*  21 */     String str = String.format("%1$.1f", new Object[] { Float.valueOf(f) });
/*     */     
/*  23 */     return str;
/*     */   }
/*     */   
/*     */   public static String formatMilisecondsToMinutes(Long paramLong) {
/*  27 */     int i = (int)(paramLong.longValue() / 1000L % 60L);
/*  28 */     int j = (int)(paramLong.longValue() / 1000L / 60L);
/*     */     
/*  30 */     String str = String.format("%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) });
/*     */     
/*  32 */     return str;
/*     */   }
/*     */   
/*     */   public static String formatSecondsToMinutes(int paramInt) {
/*  36 */     int i = paramInt % 60;
/*  37 */     int j = paramInt / 60;
/*     */     
/*  39 */     String str = String.format("%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) });
/*     */     
/*  41 */     return str;
/*     */   }
/*     */   
/*     */   public static String formatSecondsToHours(int paramInt) {
/*  45 */     int i = paramInt / 3600;
/*  46 */     int j = paramInt % 3600 / 60;
/*  47 */     int k = paramInt % 60;
/*     */     
/*  49 */     String str = String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
/*     */     
/*  51 */     return str;
/*     */   }
/*     */   
/*     */   public static String getEffectNamesList(ArrayList<PotionEffect> paramArrayList) {
/*  55 */     StringBuilder localStringBuilder = new StringBuilder();
/*  56 */     for (PotionEffect localPotionEffect : paramArrayList) {
/*  57 */       localStringBuilder.append(getPotionEffectName(localPotionEffect.getType())).append(", ");
/*     */     }
/*  59 */     if (localStringBuilder.length() != 0) {
/*  60 */       localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length());
/*     */     }
/*     */     
/*  63 */     return localStringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static String getPotionEffectName(PotionEffectType paramPotionEffectType) { String str;
/*  67 */     switch ((str = paramPotionEffectType.getName()).hashCode()) {case -1929420024:  if (str.equals("POISON")) {} break; case -1892419057:  if (str.equals("NIGHT_VISION")) {} break; case -1833148097:  if (str.equals("SLOW_DIGGING")) {} break; case -1734240269:  if (str.equals("WITHER")) {} break; case -1481449460:  if (str.equals("INCREASE_DAMAGE")) {} break; case -1356753140:  if (str.equals("BLINDNESS")) {} break; case -960314854:  if (str.equals("WATER_BREATHING")) {} break; case -944915573:  if (str.equals("REGENERATION")) {} break; case -774622513:  if (str.equals("ABSORPTION")) break; break; case -583285606:  if (str.equals("FAST_DIGGING")) {} break; case -216510496:  if (str.equals("HEALTH_BOOST")) {} break; case 2210036:  if (str.equals("HARM")) {} break; case 2213352:  if (str.equals("HEAL")) {} break; case 2288686:  if (str.equals("JUMP")) {} break; case 2548225:  if (str.equals("SLOW")) {} break; case 46439887:  if (str.equals("WEAKNESS")) {} break; case 79104039:  if (str.equals("SPEED")) {} break; case 254601170:  if (str.equals("SATURATION")) {} break; case 428830473:  if (str.equals("DAMAGE_RESISTANCE")) {} break; case 536276471:  if (str.equals("INVISIBILITY")) {} break; case 1073139170:  if (str.equals("FIRE_RESISTANCE")) {} break; case 1993593830:  if (str.equals("CONFUSION")) {} break; case 2142192307:  if (!str.equals("HUNGER")) { break label549;
/*  68 */         return "Absorption";
/*  69 */         return "Blindness";
/*  70 */         return "Confusion";
/*  71 */         return "Resistance";
/*  72 */         return "Haste";
/*  73 */         return "Fire Resistance";
/*  74 */         return "Instant Damage";
/*  75 */         return "Instant Health";
/*  76 */         return "Health Boost";
/*  77 */       } else { return "Hunger";
/*  78 */         return "Strength";
/*  79 */         return "Invisibility";
/*  80 */         return "Jump";
/*  81 */         return "Night Vision";
/*  82 */         return "Poison";
/*  83 */         return "Regeneration";
/*  84 */         return "Saturation";
/*  85 */         return "Slowness";
/*  86 */         return "Slow Digging";
/*  87 */         return "Speed";
/*  88 */         return "Water Breathing";
/*  89 */         return "Weakness";
/*  90 */         return "Wither"; }
/*     */       break; }
/*  92 */     label549: return "";
/*     */   }
/*     */   
/*     */   public static String getEntityName(Entity paramEntity) { String str;
/*  96 */     switch ((str = paramEntity.getType().name()).hashCode()) {case -2125864634:  if (str.equals("VILLAGER")) {} break; case -1932423455:  if (str.equals("PLAYER")) {} break; case -1847105819:  if (str.equals("SILVERFISH")) {} break; case -1842623771:  if (str.equals("SPIDER")) {} break; case -1781303918:  if (str.equals("ENDERMAN")) {} break; case -1734240269:  if (str.equals("WITHER")) {} break; case -1643025882:  if (str.equals("ZOMBIE")) {} break; case -1484593075:  if (str.equals("SKELETON")) {} break; case -1385440745:  if (str.equals("PIG_ZOMBIE")) {} break; case -106320427:  if (str.equals("IRON_GOLEM")) {} break; case 2670162:  if (str.equals("WOLF")) {} break; case 13282263:  if (str.equals("CAVE_SPIDER")) {} break; case 63281826:  if (str.equals("BLAZE")) break; break; case 78988968:  if (str.equals("SLIME")) {} break; case 82603943:  if (str.equals("WITCH")) {} break; case 1282404205:  if (str.equals("MAGMA_CUBE")) {} break; case 1746652494:  if (!str.equals("CREEPER")) { break label430;
/*  97 */         return "Blaze";
/*  98 */         return "Cave Spider";
/*  99 */       } else { return "Creeper";
/* 100 */         return "Enderman";
/* 101 */         return "Iron Golem";
/* 102 */         return "Magma Cube";
/* 103 */         return "Pig Zombie";
/* 104 */         return "Player";
/* 105 */         return "Silverfish";
/* 106 */         return "Skeleton";
/* 107 */         return "Slime";
/* 108 */         return "Spider";
/* 109 */         return "Villager";
/* 110 */         return "Witch";
/* 111 */         return "Wither";
/* 112 */         return "Wolf";
/* 113 */         return "Zombie"; }
/*     */       break; }
/* 115 */     label430: return "";
/*     */   }
/*     */   
/*     */   public static String getWorldName(Location paramLocation) {
/* 119 */     String str = "";
/* 120 */     World localWorld = paramLocation.getWorld();
/* 121 */     if (localWorld.getEnvironment().equals(World.Environment.NORMAL)) {
/* 122 */       str = "World";
/* 123 */     } else if (localWorld.getEnvironment().equals(World.Environment.NETHER)) {
/* 124 */       str = "Nether";
/* 125 */     } else if (localWorld.getEnvironment().equals(World.Environment.THE_END)) {
/* 126 */       str = "End";
/*     */     } else {
/* 128 */       str = localWorld.getName();
/*     */     }
/* 130 */     return str;
/*     */   }
/*     */   
/*     */   public static String getVanishOptionsList(Player paramPlayer) {
/* 134 */     StringBuilder localStringBuilder = new StringBuilder();
/* 135 */     OptionType[] arrayOfOptionType; int j = (arrayOfOptionType = OptionType.values()).length; for (int i = 0; i < j; i++) { OptionType localOptionType = arrayOfOptionType[i];
/* 136 */       if (localOptionType.getPlayers().contains(paramPlayer.getUniqueId())) {
/* 137 */         localStringBuilder.append(ChatColor.GREEN + localOptionType.getName()).append(", ");
/*     */       } else {
/* 139 */         localStringBuilder.append(ChatColor.RED + localOptionType.getName()).append(ChatColor.GRAY + ", ");
/*     */       }
/*     */     }
/* 142 */     if (localStringBuilder.length() != 0) {
/* 143 */       localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length());
/*     */     }
/*     */     
/* 146 */     return localStringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */