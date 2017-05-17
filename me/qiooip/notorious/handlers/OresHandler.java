/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.ItemStackUtils;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.Statistic;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class OresHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public OresHandler(Notorious paramNotorious)
/*    */   {
/* 22 */     super(paramNotorious);
/*    */     
/* 24 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public Inventory createOresInventory(Player paramPlayer) {
/* 28 */     Inventory localInventory = org.bukkit.Bukkit.createInventory(paramPlayer, 9, getInstance().getConfigHandler().getOresInventoryName() + paramPlayer.getName());
/*    */     
/* 30 */     ItemStack localItemStack1 = ItemStackUtils.setItemName(new ItemStack(Material.QUARTZ_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.QUARTZ_ORE))));
/* 31 */     ItemStack localItemStack2 = ItemStackUtils.setItemName(new ItemStack(Material.COAL_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.COAL_ORE))));
/* 32 */     ItemStack localItemStack3 = ItemStackUtils.setItemName(new ItemStack(Material.REDSTONE_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.REDSTONE_ORE))));
/* 33 */     ItemStack localItemStack4 = ItemStackUtils.setItemName(new ItemStack(Material.LAPIS_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.LAPIS_ORE))));
/*    */     
/* 35 */     ItemStack localItemStack5 = ItemStackUtils.setItemName(new ItemStack(Material.IRON_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.IRON_ORE))));
/* 36 */     ItemStack localItemStack6 = ItemStackUtils.setItemName(new ItemStack(Material.GOLD_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.GOLD_ORE))));
/* 37 */     ItemStack localItemStack7 = ItemStackUtils.setItemName(new ItemStack(Material.DIAMOND_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE))));
/* 38 */     ItemStack localItemStack8 = ItemStackUtils.setItemName(new ItemStack(Material.EMERALD_ORE, 1), Language.ORES_MINED_FORMAT.toString().replace("<amount>", String.valueOf(paramPlayer.getStatistic(Statistic.MINE_BLOCK, Material.EMERALD_ORE))));
/*    */     
/* 40 */     localInventory.setItem(0, localItemStack1);
/* 41 */     localInventory.setItem(1, localItemStack2);
/* 42 */     localInventory.setItem(2, localItemStack3);
/* 43 */     localInventory.setItem(3, localItemStack4);
/*    */     
/* 45 */     localInventory.setItem(5, localItemStack5);
/* 46 */     localInventory.setItem(6, localItemStack6);
/* 47 */     localInventory.setItem(7, localItemStack7);
/* 48 */     localInventory.setItem(8, localItemStack8);
/*    */     
/* 50 */     return localInventory;
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 55 */     InventoryAction localInventoryAction = paramInventoryClickEvent.getAction();
/* 56 */     Inventory localInventory = paramInventoryClickEvent.getInventory();
/*    */     
/* 58 */     if (localInventory.getName().startsWith(getInstance().getConfigHandler().getOresInventoryName())) {
/* 59 */       if ((localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.SWAP_WITH_CURSOR))) {
/* 60 */         paramInventoryClickEvent.setCancelled(true);
/*    */       }
/* 62 */       paramInventoryClickEvent.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\OresHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */