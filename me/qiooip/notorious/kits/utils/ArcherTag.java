/*    */ package me.qiooip.notorious.kits.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ public enum ArcherTag
/*    */ {
/* 12 */   TAG_1("1", new ArrayList(), Notorious.getInstance().getConfigHandler().getArcherTagEffectsTag1()), 
/* 13 */   TAG_2("2", new ArrayList(), Notorious.getInstance().getConfigHandler().getArcherTagEffectsTag2()), 
/* 14 */   TAG_3("3", new ArrayList(), Notorious.getInstance().getConfigHandler().getArcherTagEffectsTag3());
/*    */   
/*    */   private String level;
/*    */   private ArrayList<UUID> taggedPlayers;
/*    */   private ArrayList<PotionEffect> effects;
/*    */   
/*    */   private ArcherTag(String paramString, ArrayList<UUID> paramArrayList, ArrayList<PotionEffect> paramArrayList1) {
/* 21 */     this.level = paramString;
/* 22 */     this.taggedPlayers = paramArrayList;
/* 23 */     this.effects = paramArrayList1;
/*    */   }
/*    */   
/*    */   public String getLevel() {
/* 27 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(String paramString) {
/* 31 */     this.level = paramString;
/*    */   }
/*    */   
/*    */   public ArrayList<UUID> getTaggedPlayers() {
/* 35 */     return this.taggedPlayers;
/*    */   }
/*    */   
/*    */   public void setTaggedPlayers(ArrayList<UUID> paramArrayList) {
/* 39 */     this.taggedPlayers = paramArrayList;
/*    */   }
/*    */   
/*    */   public ArrayList<PotionEffect> getEffects() {
/* 43 */     return this.effects;
/*    */   }
/*    */   
/*    */   public void setEffects(ArrayList<PotionEffect> paramArrayList) {
/* 47 */     this.effects = paramArrayList;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\kits\utils\ArcherTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */