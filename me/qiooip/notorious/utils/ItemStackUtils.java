/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.qiooip.notorious.kits.utils.ClassType;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public class ItemStackUtils
/*    */ {
/*    */   public static boolean isWearingArmor(Player paramPlayer, ClassType paramClassType)
/*    */   {
/* 16 */     ItemStack localItemStack1 = paramPlayer.getInventory().getHelmet();
/* 17 */     ItemStack localItemStack2 = paramPlayer.getInventory().getChestplate();
/* 18 */     ItemStack localItemStack3 = paramPlayer.getInventory().getLeggings();
/* 19 */     ItemStack localItemStack4 = paramPlayer.getInventory().getBoots();
/*    */     
/* 21 */     return (localItemStack1 != null) && (localItemStack2 != null) && (localItemStack3 != null) && (localItemStack4 != null) && (localItemStack1.getType() == paramClassType.getHelmet()) && (localItemStack2.getType() == paramClassType.getChestplate()) && (localItemStack3.getType() == paramClassType.getLeggings()) && (localItemStack4.getType() == paramClassType.getBoots());
/*    */   }
/*    */   
/*    */   public static void removeOneItem(Player paramPlayer) {
/* 25 */     if (paramPlayer.getItemInHand().getAmount() > 1) {
/* 26 */       paramPlayer.getItemInHand().setAmount(paramPlayer.getItemInHand().getAmount() - 1);
/*    */     } else {
/* 28 */       paramPlayer.getInventory().setItemInHand(new ItemStack(Material.AIR));
/*    */     }
/*    */   }
/*    */   
/*    */   public static ItemStack setItemName(ItemStack paramItemStack, String paramString) {
/* 33 */     ItemMeta localItemMeta = paramItemStack.getItemMeta();
/* 34 */     localItemMeta.setDisplayName(paramString);
/* 35 */     paramItemStack.setItemMeta(localItemMeta);
/*    */     
/* 37 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static ItemStack setItemNameAndLore(ItemStack paramItemStack, String paramString, List<String> paramList) {
/* 41 */     ItemMeta localItemMeta = paramItemStack.getItemMeta();
/* 42 */     localItemMeta.setDisplayName(paramString);
/* 43 */     localItemMeta.setLore(paramList);
/* 44 */     paramItemStack.setItemMeta(localItemMeta);
/*    */     
/* 46 */     return paramItemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\ItemStackUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */