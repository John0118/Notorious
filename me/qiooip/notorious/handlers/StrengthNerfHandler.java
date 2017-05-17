/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class StrengthNerfHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public StrengthNerfHandler(Notorious paramNotorious)
/*    */   {
/* 16 */     super(paramNotorious);
/*    */     
/* 18 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerDamage(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/* 23 */     if ((paramEntityDamageByEntityEvent.getDamager() instanceof Player)) {
/* 24 */       Player localPlayer = (Player)paramEntityDamageByEntityEvent.getDamager();
/* 25 */       if (localPlayer.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
/* 26 */         for (PotionEffect localPotionEffect : localPlayer.getActivePotionEffects()) {
/* 27 */           if (localPotionEffect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
/* 28 */             int i = localPotionEffect.getAmplifier() + 1;
/*    */             
/* 30 */             if (i == 1) {
/* 31 */               paramEntityDamageByEntityEvent.setDamage(10.0D * paramEntityDamageByEntityEvent.getDamage() / (10.0D + 13.0D * i) + 13.0D * paramEntityDamageByEntityEvent.getDamage() * i * getInstance().getConfigHandler().getStrength1NerfPercentage() / 100.0D / (10.0D + 13.0D * i));
/*    */             } else {
/* 33 */               paramEntityDamageByEntityEvent.setDamage(10.0D * paramEntityDamageByEntityEvent.getDamage() / (10.0D + 13.0D * i) + 13.0D * paramEntityDamageByEntityEvent.getDamage() * i * getInstance().getConfigHandler().getStrength2NerfPercentage() / 100.0D / (10.0D + 13.0D * i));
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\StrengthNerfHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */