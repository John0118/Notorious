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
/*    */ public class BardHoldItemHandler extends Handler
/*    */ {
/*    */   private ArrayList<BardHoldItem> bardHoldItems;
/*    */   
/*    */   public BardHoldItemHandler(Notorious paramNotorious)
/*    */   {
/* 18 */     super(paramNotorious);
/* 19 */     this.bardHoldItems = new ArrayList();
/*    */     
/* 21 */     loadBardHoldItems();
/*    */   }
/*    */   
/*    */   public void loadBardHoldItems()
/*    */   {
/* 26 */     if (this.bardHoldItems != null) this.bardHoldItems.clear();
/* 27 */     ConfigurationSection localConfigurationSection1 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("bard-hold-items");
/* 28 */     Iterator localIterator2; for (Iterator localIterator1 = localConfigurationSection1.getKeys(false).iterator(); localIterator1.hasNext(); 
/*    */         
/* 30 */         localIterator2.hasNext())
/*    */     {
/* 28 */       String str1 = (String)localIterator1.next();
/* 29 */       ConfigurationSection localConfigurationSection2 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("bard-hold-items." + str1);
/* 30 */       localIterator2 = localConfigurationSection2.getKeys(false).iterator(); continue;String str2 = (String)localIterator2.next();
/* 31 */       BardHoldItem localBardHoldItem = new BardHoldItem();
/* 32 */       localBardHoldItem.setMaterial(Material.getMaterial(Integer.valueOf(str1).intValue()));
/* 33 */       localBardHoldItem.setDistance(localConfigurationSection2.getInt(str2 + ".distance"));
/* 34 */       localBardHoldItem.setCanBardHimself(localConfigurationSection2.getBoolean(str2 + ".canBardHimself"));
/* 35 */       localBardHoldItem.setDuration(localConfigurationSection2.getInt(str2 + ".duration") * 20);
/* 36 */       localBardHoldItem.setLevel(localConfigurationSection2.getInt(str2 + ".level") - 1);
/* 37 */       localBardHoldItem.setPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.getByName(str2), localBardHoldItem.getDuration(), localBardHoldItem.getLevel()));
/*    */       
/* 39 */       addBardHoldItem(localBardHoldItem);
/*    */     }
/*    */   }
/*    */   
/*    */   public void addBardHoldItem(BardHoldItem paramBardHoldItem)
/*    */   {
/* 45 */     this.bardHoldItems.add(paramBardHoldItem);
/*    */   }
/*    */   
/*    */   public void removeBardHeldItem(BardHoldItem paramBardHoldItem) {
/* 49 */     this.bardHoldItems.remove(paramBardHoldItem);
/*    */   }
/*    */   
/*    */   public ArrayList<BardHoldItem> getBardHoldItems() {
/* 53 */     return this.bardHoldItems;
/*    */   }
/*    */   
/*    */   public BardHoldItem getBardHoldItemByMaterial(Material paramMaterial) {
/* 57 */     for (BardHoldItem localBardHoldItem : this.bardHoldItems) {
/* 58 */       if (localBardHoldItem.getMaterial().equals(paramMaterial)) {
/* 59 */         return localBardHoldItem;
/*    */       }
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\data\BardHoldItemHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */