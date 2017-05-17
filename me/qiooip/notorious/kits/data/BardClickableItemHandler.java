/*    */ package me.qiooip.notorious.kits.data;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigFile;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ public class BardClickableItemHandler extends Handler
/*    */ {
/*    */   private ArrayList<BardClickableItem> bardClickableItems;
/*    */   
/*    */   public BardClickableItemHandler(Notorious paramNotorious)
/*    */   {
/* 18 */     super(paramNotorious);
/* 19 */     this.bardClickableItems = new ArrayList();
/*    */     
/* 21 */     loadBardClickableItems();
/*    */   }
/*    */   
/*    */   public void loadBardClickableItems()
/*    */   {
/* 26 */     if (this.bardClickableItems != null) this.bardClickableItems.clear();
/* 27 */     ConfigurationSection localConfigurationSection1 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("bard-clickable-items");
/* 28 */     Iterator localIterator2; for (Iterator localIterator1 = localConfigurationSection1.getKeys(false).iterator(); localIterator1.hasNext(); 
/*    */         
/* 30 */         localIterator2.hasNext())
/*    */     {
/* 28 */       String str1 = (String)localIterator1.next();
/* 29 */       ConfigurationSection localConfigurationSection2 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("bard-clickable-items." + str1);
/* 30 */       localIterator2 = localConfigurationSection2.getKeys(false).iterator(); continue;String str2 = (String)localIterator2.next();
/* 31 */       BardClickableItem localBardClickableItem = new BardClickableItem();
/* 32 */       localBardClickableItem.setMaterial(Material.getMaterial(Integer.valueOf(str1).intValue()));
/* 33 */       localBardClickableItem.setEnergyNeeded(localConfigurationSection2.getInt(str2 + ".energyNeeded"));
/* 34 */       localBardClickableItem.setDistance(localConfigurationSection2.getInt(str2 + ".distance"));
/* 35 */       localBardClickableItem.setDuration(localConfigurationSection2.getInt(str2 + ".duration") * 20);
/* 36 */       localBardClickableItem.setLevel(localConfigurationSection2.getInt(str2 + ".level") - 1);
/* 37 */       localBardClickableItem.setPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.getByName(str2), localBardClickableItem.getDuration(), localBardClickableItem.getLevel()));
/*    */       
/* 39 */       addBardClickableItem(localBardClickableItem);
/*    */     }
/*    */   }
/*    */   
/*    */   public void addBardClickableItem(BardClickableItem paramBardClickableItem)
/*    */   {
/* 45 */     this.bardClickableItems.add(paramBardClickableItem);
/*    */   }
/*    */   
/*    */   public void removeBardHeldItem(BardClickableItem paramBardClickableItem) {
/* 49 */     this.bardClickableItems.remove(paramBardClickableItem);
/*    */   }
/*    */   
/*    */   public ArrayList<BardClickableItem> getBardClickableItems() {
/* 53 */     return this.bardClickableItems;
/*    */   }
/*    */   
/*    */   public BardClickableItem getBardClickableItemByMaterial(Material paramMaterial) {
/* 57 */     for (BardClickableItem localBardClickableItem : this.bardClickableItems) {
/* 58 */       if (localBardClickableItem.getMaterial().equals(paramMaterial)) {
/* 59 */         return localBardClickableItem;
/*    */       }
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\data\BardClickableItemHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */