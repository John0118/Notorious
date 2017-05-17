/*     */ package me.qiooip.notorious.scoreboard;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scoreboard.DisplaySlot;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.Score;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.Team;
/*     */ 
/*     */ public class ScoreboardObject
/*     */ {
/*     */   private List<ScoreboardInput> list;
/*     */   private Scoreboard scoreboard;
/*     */   private Objective objective;
/*     */   private String title;
/*     */   private int lastSentCount;
/*     */   
/*     */   public ScoreboardObject(Scoreboard paramScoreboard, Player paramPlayer)
/*     */   {
/*  25 */     this.list = new ArrayList();
/*  26 */     this.title = "DefaultTitle";
/*  27 */     this.lastSentCount = -1;
/*  28 */     this.scoreboard = paramScoreboard;
/*  29 */     (this.objective = getOrCreateObjective(this.title)).setDisplaySlot(DisplaySlot.SIDEBAR);
/*     */   }
/*     */   
/*     */   public ScoreboardObject(Scoreboard paramScoreboard, String paramString, Player paramPlayer) {
/*  33 */     this.list = new ArrayList();
/*  34 */     this.title = "PlaceHolder";
/*  35 */     this.lastSentCount = -1;
/*  36 */     Preconditions.checkState(paramString.length() <= 32, "Max characters for Title is 32");
/*  37 */     this.title = ChatColor.translateAlternateColorCodes('&', paramString);
/*  38 */     this.scoreboard = paramScoreboard;
/*  39 */     (this.objective = getOrCreateObjective(this.title)).setDisplaySlot(DisplaySlot.SIDEBAR);
/*     */   }
/*     */   
/*     */   public void add(String paramString) {
/*  43 */     paramString = ChatColor.translateAlternateColorCodes('&', paramString);
/*  44 */     ScoreboardInput localScoreboardInput = null;
/*  45 */     if (paramString.length() <= 16) {
/*  46 */       localScoreboardInput = new ScoreboardInput(paramString, "");
/*     */     }
/*     */     else {
/*  49 */       String str1 = paramString.substring(0, 16);
/*  50 */       String str2 = paramString.substring(16, paramString.length());
/*  51 */       if (str1.endsWith(String.valueOf('§'))) {
/*  52 */         str1 = str1.substring(0, str1.length() - 1);
/*  53 */         str2 = String.valueOf('§') + str2;
/*     */       }
/*  55 */       String str3 = ChatColor.getLastColors(str1);
/*  56 */       str2 = String.valueOf(str3) + str2;
/*  57 */       localScoreboardInput = new ScoreboardInput(str1, StringUtils.left(str2, 16));
/*     */     }
/*  59 */     this.list.add(localScoreboardInput);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  63 */     this.list.clear();
/*     */   }
/*     */   
/*     */   public void remove(int paramInt) {
/*  67 */     String str = getNameForIndex(paramInt);
/*  68 */     this.scoreboard.resetScores(str);
/*  69 */     Team localTeam = getOrCreateTeam(String.valueOf(ChatColor.stripColor(StringUtils.left(this.title, 14))) + paramInt, paramInt);
/*  70 */     localTeam.unregister();
/*     */   }
/*     */   
/*     */   public void update(Player paramPlayer) {
/*  74 */     paramPlayer.setScoreboard(this.scoreboard);
/*  75 */     for (int i = 0; i < this.list.size(); i++) {
/*  76 */       Team localTeam = getOrCreateTeam(String.valueOf(ChatColor.stripColor(StringUtils.left(this.title, 14))) + i, i);
/*  77 */       ScoreboardInput localScoreboardInput = (ScoreboardInput)this.list.get(this.list.size() - i - 1);
/*  78 */       localTeam.setPrefix(localScoreboardInput.getLeft());
/*  79 */       localTeam.setSuffix(localScoreboardInput.getRight());
/*  80 */       this.objective.getScore(getNameForIndex(i)).setScore(i + 1);
/*     */     }
/*  82 */     if (this.lastSentCount != -1) {
/*  83 */       i = this.list.size(); for (int j = 0; j < this.lastSentCount - i; j++) {
/*  84 */         remove(i + j);
/*     */       }
/*     */     }
/*  87 */     this.lastSentCount = this.list.size();
/*     */   }
/*     */   
/*     */   public Team getOrCreateTeam(String paramString, int paramInt) {
/*  91 */     Team localTeam = this.scoreboard.getTeam(paramString);
/*  92 */     if (localTeam == null) {
/*  93 */       localTeam = this.scoreboard.registerNewTeam(paramString);
/*  94 */       localTeam.addEntry(getNameForIndex(paramInt));
/*     */     }
/*  96 */     return localTeam;
/*     */   }
/*     */   
/*     */   public Objective getOrCreateObjective(String paramString) {
/* 100 */     Objective localObjective = this.scoreboard.getObjective("notoriuos");
/* 101 */     if (localObjective == null) {
/* 102 */       localObjective = this.scoreboard.registerNewObjective("notoriuos", "dummy");
/*     */     }
/* 104 */     localObjective.setDisplayName(paramString);
/* 105 */     return localObjective;
/*     */   }
/*     */   
/*     */   public String getNameForIndex(int paramInt) {
/* 109 */     return String.valueOf(ChatColor.values()[paramInt].toString()) + ChatColor.RESET;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\scoreboard\ScoreboardObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */