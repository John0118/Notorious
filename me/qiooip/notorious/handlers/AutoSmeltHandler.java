/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class AutoSmeltHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public AutoSmeltHandler(Notorious paramNotorious)
/*    */   {
/* 19 */     super(paramNotorious);
/*    */     
/* 21 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
/* 26 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 27 */     Block localBlock = paramBlockBreakEvent.getBlock();
/* 28 */     World localWorld = localBlock.getWorld();
/*    */     
/* 30 */     if (paramBlockBreakEvent.isCancelled()) { return;
/*    */     }
/* 32 */     if (localPlayer.hasPermission("notorious.autosmelt")) {
/* 33 */       ItemStack localItemStack1 = localPlayer.getItemInHand();
/* 34 */       if ((localItemStack1 != null) && (localItemStack1.getType() != Material.AIR) && (!localItemStack1.containsEnchantment(Enchantment.SILK_TOUCH))) { ItemStack localItemStack2;
/* 35 */         if (localBlock.getType() == Material.IRON_ORE) {
/* 36 */           localItemStack2 = new ItemStack(Material.IRON_INGOT, 1);
/* 37 */           localWorld.dropItemNaturally(localBlock.getLocation(), localItemStack2);
/* 38 */           localBlock.setType(Material.AIR);
/* 39 */           localBlock.getState().update();
/*    */         }
/* 41 */         if (localBlock.getType() == Material.GOLD_ORE) {
/* 42 */           localItemStack2 = new ItemStack(Material.GOLD_INGOT, 1);
/* 43 */           localWorld.dropItemNaturally(localBlock.getLocation(), localItemStack2);
/* 44 */           localBlock.setType(Material.AIR);
/* 45 */           localBlock.getState().update();
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\AutoSmeltHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */