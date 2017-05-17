/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class BookDisenchantHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public BookDisenchantHandler(Notorious paramNotorious)
/*    */   {
/* 23 */     super(paramNotorious);
/*    */     
/* 25 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 30 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 31 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 32 */     ItemStack localItemStack = paramPlayerInteractEvent.getItem();
/* 33 */     Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/*    */     
/* 35 */     if (localItemStack == null) { return;
/*    */     }
/* 37 */     if ((localItemStack.getType().equals(Material.ENCHANTED_BOOK)) && 
/* 38 */       (localAction.equals(Action.LEFT_CLICK_BLOCK)) && 
/* 39 */       (localBlock.getType().equals(Material.ENCHANTMENT_TABLE)) && 
/* 40 */       (!localPlayer.getGameMode().equals(GameMode.CREATIVE))) {
/* 41 */       org.bukkit.inventory.meta.ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 42 */       if ((localItemMeta instanceof EnchantmentStorageMeta)) {
/* 43 */         EnchantmentStorageMeta localEnchantmentStorageMeta = (EnchantmentStorageMeta)localItemMeta;
/* 44 */         for (Enchantment localEnchantment : localEnchantmentStorageMeta.getStoredEnchants().keySet()) {
/* 45 */           localEnchantmentStorageMeta.removeStoredEnchant(localEnchantment);
/*    */         }
/* 47 */         paramPlayerInteractEvent.setCancelled(true);
/* 48 */         localPlayer.setItemInHand(new ItemStack(Material.BOOK, 1));
/* 49 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.BOOKDISENCHANT_MESSAGE.toString());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\BookDisenchantHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */