/*    */ package me.qiooip.notorious.playerdata;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class PlayerData
/*    */ {
/*    */   private int kills;
/*    */   private int deaths;
/*    */   private int pvpTime;
/*    */   private boolean isCombatLogger;
/*    */   private long goldenAppleCooldown;
/*    */   private List<String> notes;
/*    */   
/*    */   public int getKills()
/*    */   {
/* 17 */     return this.kills;
/*    */   }
/*    */   
/*    */   public void addKill() {
/* 21 */     this.kills += 1;
/*    */   }
/*    */   
/*    */   public void setKills(int paramInt) {
/* 25 */     this.kills = paramInt;
/*    */   }
/*    */   
/*    */   public int getDeaths() {
/* 29 */     return this.deaths;
/*    */   }
/*    */   
/*    */   public void addDeath() {
/* 33 */     this.deaths += 1;
/*    */   }
/*    */   
/*    */   public void setDeaths(int paramInt) {
/* 37 */     this.deaths = paramInt;
/*    */   }
/*    */   
/*    */   public int getPvPTime() {
/* 41 */     return this.pvpTime;
/*    */   }
/*    */   
/*    */   public void setPvPTime(int paramInt) {
/* 45 */     this.pvpTime = paramInt;
/*    */   }
/*    */   
/*    */   public boolean isCombatLogger() {
/* 49 */     return this.isCombatLogger;
/*    */   }
/*    */   
/*    */   public void setCombatLogger(boolean paramBoolean) {
/* 53 */     this.isCombatLogger = paramBoolean;
/*    */   }
/*    */   
/*    */   public long getGoldenAppleCooldown() {
/* 57 */     return this.goldenAppleCooldown;
/*    */   }
/*    */   
/*    */   public void setGoldenAppleCooldown(long paramLong) {
/* 61 */     this.goldenAppleCooldown = paramLong;
/*    */   }
/*    */   
/*    */   public List<String> getNotes() {
/* 65 */     return this.notes;
/*    */   }
/*    */   
/*    */   public void setNotes(List<String> paramList) {
/* 69 */     this.notes = paramList;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\playerdata\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */