/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.config.UtilitiesFile;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.InventoryUtils;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class MapKitHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   private Inventory mapKitInv;
/*    */   private List<UUID> editingMapKit;
/*    */   
/*    */   public MapKitHandler(Notorious paramNotorious)
/*    */   {
/* 29 */     super(paramNotorious);
/* 30 */     this.editingMapKit = new java.util.ArrayList();
/*    */     
/* 32 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/* 33 */     loadInventory();
/*    */   }
/*    */   
/*    */   public void disable() {
/* 37 */     saveInventory();
/*    */   }
/*    */   
/*    */   public void loadInventory() {
/* 41 */     this.mapKitInv = org.bukkit.Bukkit.createInventory(null, getInstance().getConfigHandler().getMapkitInventorySize(), getInstance().getConfigHandler().getMapkitInventoryName());
/* 42 */     String str = getInstance().getUtilitiesFile().getString("map-kit-items");
/* 43 */     if ((str != null) && 
/* 44 */       (!str.isEmpty())) {
/*    */       try {
/* 46 */         ItemStack[] arrayOfItemStack = InventoryUtils.itemStackArrayFromBase64(getInstance().getUtilitiesFile().getString("map-kit-items"));
/* 47 */         this.mapKitInv.setContents(arrayOfItemStack);
/*    */       } catch (IOException localIOException) {
/* 49 */         localIOException.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void saveInventory()
/*    */   {
/* 56 */     ItemStack[] arrayOfItemStack = this.mapKitInv.getContents();
/* 57 */     String str = InventoryUtils.itemStackArrayToBase64(arrayOfItemStack);
/* 58 */     getInstance().getUtilitiesFile().getConfiguration().set("map-kit-items", str);
/*    */     try {
/* 60 */       getInstance().getUtilitiesFile().getConfiguration().save(getInstance().getUtilitiesFile().getFile());
/*    */     } catch (IOException localIOException) {
/* 62 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public Inventory getMapKitInventory() {
/* 67 */     return this.mapKitInv;
/*    */   }
/*    */   
/*    */   public List<UUID> getEditingMapKit() {
/* 71 */     return this.editingMapKit;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 76 */     HumanEntity localHumanEntity = paramInventoryClickEvent.getWhoClicked();
/* 77 */     Inventory localInventory = paramInventoryClickEvent.getInventory();
/*    */     
/* 79 */     if ((localHumanEntity instanceof Player)) {
/* 80 */       Player localPlayer = (Player)localHumanEntity;
/* 81 */       if ((localInventory.getName().equals(this.mapKitInv.getName())) && (
/* 82 */         (!localPlayer.hasPermission("notorious.mapkit.edit")) || (!this.editingMapKit.contains(localPlayer.getUniqueId())))) {
/* 83 */         paramInventoryClickEvent.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onInventoryClose(InventoryCloseEvent paramInventoryCloseEvent)
/*    */   {
/* 91 */     HumanEntity localHumanEntity = paramInventoryCloseEvent.getPlayer();
/* 92 */     Inventory localInventory = paramInventoryCloseEvent.getInventory();
/*    */     
/* 94 */     if ((localHumanEntity instanceof Player)) {
/* 95 */       Player localPlayer = (Player)localHumanEntity;
/* 96 */       if ((localInventory.getName().equals(this.mapKitInv.getName())) && 
/* 97 */         (this.editingMapKit.contains(localPlayer.getUniqueId()))) {
/* 98 */         this.editingMapKit.remove(localPlayer.getUniqueId());
/* 99 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.MAPKIT_EDIT_MESSAGE.toString());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\MapKitHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */