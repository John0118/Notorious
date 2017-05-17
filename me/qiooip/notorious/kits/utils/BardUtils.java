/*     */ package me.qiooip.notorious.kits.utils;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.kits.Bard;
/*     */ import me.qiooip.notorious.kits.Bard.BardPower;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class BardUtils
/*     */ {
/*  13 */   public static HashMap<String, RestoreEffectTask> restorersHold = new HashMap();
/*  14 */   public static HashMap<String, RestoreEffectTask> restorersClickable = new HashMap();
/*     */   
/*     */   public static void addBardHoldPotionEffect(Player paramPlayer, PotionEffect paramPotionEffect) {
/*  17 */     if (paramPlayer.hasPotionEffect(paramPotionEffect.getType())) {
/*  18 */       for (PotionEffect localPotionEffect : paramPlayer.getActivePotionEffects()) {
/*  19 */         if (localPotionEffect.getType().equals(paramPotionEffect.getType())) {
/*  20 */           if (paramPotionEffect.getAmplifier() < localPotionEffect.getAmplifier()) return;
/*  21 */           if ((paramPotionEffect.getAmplifier() == localPotionEffect.getAmplifier()) && (paramPotionEffect.getDuration() < localPotionEffect.getDuration())) { return;
/*     */           }
/*  23 */           if ((paramPotionEffect.getAmplifier() > localPotionEffect.getAmplifier()) || ((paramPotionEffect.getAmplifier() == localPotionEffect.getAmplifier()) && (paramPotionEffect.getDuration() > localPotionEffect.getDuration()))) { RestoreEffectTask localRestoreEffectTask;
/*  24 */             if (restorersHold.containsKey(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())) {
/*  25 */               ((RestoreEffectTask)restorersHold.get(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())).cancel();
/*  26 */               localRestoreEffectTask = new RestoreEffectTask(paramPlayer, ((RestoreEffectTask)restorersHold.get(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())).getEffect(), paramPotionEffect.getDuration() - 2);
/*  27 */               restorersHold.put(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName(), localRestoreEffectTask);
/*  28 */               paramPlayer.removePotionEffect(paramPotionEffect.getType());
/*  29 */               paramPlayer.addPotionEffect(paramPotionEffect);
/*     */             } else {
/*  31 */               localRestoreEffectTask = new RestoreEffectTask(paramPlayer, localPotionEffect, paramPotionEffect.getDuration() - 2);
/*  32 */               restorersHold.put(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName(), localRestoreEffectTask);
/*  33 */               paramPlayer.removePotionEffect(paramPotionEffect.getType());
/*  34 */               paramPlayer.addPotionEffect(paramPotionEffect);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/*  40 */       paramPlayer.addPotionEffect(paramPotionEffect);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void addBardClickablePotionEffect(Player paramPlayer, PotionEffect paramPotionEffect) {
/*  45 */     if (paramPlayer.hasPotionEffect(paramPotionEffect.getType())) {
/*  46 */       for (PotionEffect localPotionEffect : paramPlayer.getActivePotionEffects()) {
/*  47 */         if (localPotionEffect.getType().equals(paramPotionEffect.getType())) {
/*  48 */           if (paramPotionEffect.getAmplifier() < localPotionEffect.getAmplifier()) return;
/*  49 */           if ((paramPotionEffect.getAmplifier() == localPotionEffect.getAmplifier()) && (paramPotionEffect.getDuration() < localPotionEffect.getDuration())) { return;
/*     */           }
/*  51 */           if ((paramPotionEffect.getAmplifier() > localPotionEffect.getAmplifier()) || ((paramPotionEffect.getAmplifier() == localPotionEffect.getAmplifier()) && (paramPotionEffect.getDuration() > localPotionEffect.getDuration()))) { RestoreEffectTask localRestoreEffectTask;
/*  52 */             if (restorersClickable.containsKey(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())) {
/*  53 */               ((RestoreEffectTask)restorersClickable.get(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())).cancel();
/*  54 */               localRestoreEffectTask = new RestoreEffectTask(paramPlayer, ((RestoreEffectTask)restorersClickable.get(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName())).getEffect(), paramPotionEffect.getDuration() - 2);
/*  55 */               restorersClickable.put(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName(), localRestoreEffectTask);
/*  56 */               paramPlayer.removePotionEffect(paramPotionEffect.getType());
/*  57 */               paramPlayer.addPotionEffect(paramPotionEffect);
/*     */             } else {
/*  59 */               localRestoreEffectTask = new RestoreEffectTask(paramPlayer, localPotionEffect, paramPotionEffect.getDuration() - 2);
/*  60 */               restorersClickable.put(paramPlayer.getUniqueId() + "_" + localPotionEffect.getType().getName(), localRestoreEffectTask);
/*  61 */               paramPlayer.removePotionEffect(paramPotionEffect.getType());
/*  62 */               paramPlayer.addPotionEffect(paramPotionEffect);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/*  68 */       paramPlayer.addPotionEffect(paramPotionEffect);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean hasEnoughEnergy(Player paramPlayer, int paramInt) {
/*  73 */     return Notorious.getInstance().getBardClass().getBardPower(paramPlayer).getPower() >= paramInt;
/*     */   }
/*     */   
/*     */   public static void modifyBardEnergy(Player paramPlayer, int paramInt) {
/*  77 */     if (hasEnoughEnergy(paramPlayer, paramInt)) {
/*  78 */       int i = Notorious.getInstance().getBardClass().getBardPower(paramPlayer).getPower();
/*  79 */       Notorious.getInstance().getBardClass().getBardPower(paramPlayer).setPower(i - paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RestoreEffectTask extends org.bukkit.scheduler.BukkitRunnable
/*     */   {
/*     */     private Player player;
/*     */     private PotionEffect effect;
/*     */     
/*     */     public RestoreEffectTask(Player paramPlayer, PotionEffect paramPotionEffect, int paramInt) {
/*  89 */       this.player = paramPlayer;
/*  90 */       this.effect = paramPotionEffect;
/*  91 */       runTaskLater(Notorious.getInstance(), paramInt);
/*     */     }
/*     */     
/*     */     public void run()
/*     */     {
/*  96 */       this.player.removePotionEffect(this.effect.getType());
/*  97 */       this.player.addPotionEffect(this.effect);
/*     */     }
/*     */     
/*     */     public PotionEffect getEffect() {
/* 101 */       return this.effect;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\utils\BardUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */