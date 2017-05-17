/*    */ package me.qiooip.notorious.staff;
/*    */ 
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class StaffPlayerData
/*    */ {
/*    */   private ItemStack[] contents;
/*    */   private ItemStack[] armor;
/*    */   private GameMode gameMode;
/*    */   
/*    */   public ItemStack[] getContents()
/*    */   {
/* 15 */     return this.contents;
/*    */   }
/*    */   
/*    */   public void setContents(ItemStack[] paramArrayOfItemStack) {
/* 19 */     this.contents = paramArrayOfItemStack;
/*    */   }
/*    */   
/*    */   public ItemStack[] getArmor() {
/* 23 */     return this.armor;
/*    */   }
/*    */   
/*    */   public void setArmor(ItemStack[] paramArrayOfItemStack) {
/* 27 */     this.armor = paramArrayOfItemStack;
/*    */   }
/*    */   
/*    */   public GameMode getGameMode() {
/* 31 */     return this.gameMode;
/*    */   }
/*    */   
/*    */   public void setGameMode(GameMode paramGameMode) {
/* 35 */     this.gameMode = paramGameMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\staff\StaffPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */