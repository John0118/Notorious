/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class LootingAmplifierHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public LootingAmplifierHandler(Notorious paramNotorious)
/*    */   {
/* 16 */     super(paramNotorious);
/*    */     
/* 18 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onEntityDeath(EntityDeathEvent paramEntityDeathEvent) {
/* 23 */     LivingEntity localLivingEntity = paramEntityDeathEvent.getEntity();
/* 24 */     Player localPlayer1 = localLivingEntity.getKiller();
/*    */     
/* 26 */     if ((localPlayer1 instanceof Player)) {
/* 27 */       Player localPlayer2 = (Player)localPlayer1;
/* 28 */       if ((localPlayer2 != null) && (localPlayer2.getItemInHand() != null) && (localPlayer2.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_MOBS))) {
/* 29 */         int i = localPlayer2.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) + getInstance().getConfigHandler().getLootingBonus();
/* 30 */         paramEntityDeathEvent.setDroppedExp(paramEntityDeathEvent.getDroppedExp() * i);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\LootingAmplifierHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */