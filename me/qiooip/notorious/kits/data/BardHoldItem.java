/*    */ package me.qiooip.notorious.kits.data;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ public class BardHoldItem
/*    */ {
/*    */   private Material material;
/*    */   private PotionEffect potionEffect;
/*    */   private int distance;
/*    */   private boolean canBardHimself;
/*    */   private int duration;
/*    */   private int level;
/*    */   
/*    */   public Material getMaterial()
/*    */   {
/* 18 */     return this.material;
/*    */   }
/*    */   
/*    */   public void setMaterial(Material paramMaterial) {
/* 22 */     this.material = paramMaterial;
/*    */   }
/*    */   
/*    */   public PotionEffect getPotionEffect() {
/* 26 */     return this.potionEffect;
/*    */   }
/*    */   
/*    */   public void setPotionEffect(PotionEffect paramPotionEffect) {
/* 30 */     this.potionEffect = paramPotionEffect;
/*    */   }
/*    */   
/*    */   public int getDistance() {
/* 34 */     return this.distance;
/*    */   }
/*    */   
/*    */   public void setDistance(int paramInt) {
/* 38 */     this.distance = paramInt;
/*    */   }
/*    */   
/*    */   public int getDuration() {
/* 42 */     return this.duration;
/*    */   }
/*    */   
/*    */   public void setDuration(int paramInt) {
/* 46 */     this.duration = paramInt;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 50 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int paramInt) {
/* 54 */     this.level = paramInt;
/*    */   }
/*    */   
/*    */   public boolean canBardHimself() {
/* 58 */     return this.canBardHimself;
/*    */   }
/*    */   
/*    */   public void setCanBardHimself(boolean paramBoolean) {
/* 62 */     this.canBardHimself = paramBoolean;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\data\BardHoldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */