/*    */ package me.qiooip.notorious.vanish;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public enum OptionType
/*    */ {
/*  8 */   DAMAGE("Damage", new ArrayList()), 
/*  9 */   CHAT("Chat", new ArrayList()), 
/* 10 */   PLACE("Place", new ArrayList()), 
/* 11 */   BREAK("Break", new ArrayList()), 
/* 12 */   PICKUP("Pickup", new ArrayList()), 
/* 13 */   INTERACT("Interact", new ArrayList()), 
/* 14 */   CHEST("Chest", new ArrayList());
/*    */   
/*    */   private String name;
/*    */   private ArrayList<UUID> players;
/*    */   
/*    */   private OptionType(String paramString, ArrayList<UUID> paramArrayList) {
/* 20 */     this.name = paramString;
/* 21 */     this.players = paramArrayList;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 25 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String paramString) {
/* 29 */     this.name = paramString;
/*    */   }
/*    */   
/*    */   public ArrayList<UUID> getPlayers() {
/* 33 */     return this.players;
/*    */   }
/*    */   
/*    */   public void setPlayers(ArrayList<UUID> paramArrayList) {
/* 37 */     this.players = paramArrayList;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\vanish\OptionType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */