/*    */ package me.qiooip.notorious.kits.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ public enum ClassType
/*    */ {
/* 13 */   ARCHER("Archer", Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, new ArrayList(), Notorious.getInstance().getConfigHandler().getArcherPotionEffects()), 
/* 14 */   BARD("Bard", Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, new ArrayList(), Notorious.getInstance().getConfigHandler().getBardPotionEffects()), 
/* 15 */   MINER("Miner", Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, new ArrayList(), Notorious.getInstance().getConfigHandler().getMinerPotionEffects());
/*    */   
/*    */   private String name;
/*    */   private Material helmet;
/*    */   private Material chestplate;
/*    */   private Material leggings;
/*    */   private Material boots;
/*    */   private ArrayList<UUID> players;
/*    */   private ArrayList<PotionEffect> effects;
/*    */   
/*    */   private ClassType(String paramString, Material paramMaterial1, Material paramMaterial2, Material paramMaterial3, Material paramMaterial4, ArrayList<UUID> paramArrayList, ArrayList<PotionEffect> paramArrayList1) {
/* 26 */     this.name = paramString;
/* 27 */     this.helmet = paramMaterial1;
/* 28 */     this.chestplate = paramMaterial2;
/* 29 */     this.leggings = paramMaterial3;
/* 30 */     this.boots = paramMaterial4;
/* 31 */     this.players = paramArrayList;
/* 32 */     this.effects = paramArrayList1;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 36 */     return this.name;
/*    */   }
/*    */   
/*    */   public Material getHelmet() {
/* 40 */     return this.helmet;
/*    */   }
/*    */   
/*    */   public Material getChestplate() {
/* 44 */     return this.chestplate;
/*    */   }
/*    */   
/*    */   public Material getLeggings() {
/* 48 */     return this.leggings;
/*    */   }
/*    */   
/*    */   public Material getBoots() {
/* 52 */     return this.boots;
/*    */   }
/*    */   
/*    */   public ArrayList<UUID> getPlayers() {
/* 56 */     return this.players;
/*    */   }
/*    */   
/*    */   public ArrayList<PotionEffect> getEffects() {
/* 60 */     return this.effects;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\utils\ClassType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */