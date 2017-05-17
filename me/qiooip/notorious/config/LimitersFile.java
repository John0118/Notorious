/*    */ package me.qiooip.notorious.config;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ public class LimitersFile
/*    */ {
/*    */   private File file;
/*    */   private YamlConfiguration configuration;
/*    */   
/*    */   public LimitersFile()
/*    */   {
/* 19 */     this.file = new File(Notorious.getInstance().getDataFolder(), "limiters.yml");
/* 20 */     if (!this.file.exists()) {
/* 21 */       Notorious.getInstance().saveResource("limiters.yml", false);
/*    */     }
/* 23 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public void load() {
/* 27 */     this.file = new File(Notorious.getInstance().getDataFolder(), "limiters.yml");
/* 28 */     if (!this.file.exists()) {
/* 29 */       Notorious.getInstance().saveResource("limiters.yml", false);
/*    */     }
/* 31 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public YamlConfiguration getConfiguration() {
/* 35 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 39 */     return this.file;
/*    */   }
/*    */   
/*    */   public double getDouble(String paramString) {
/* 43 */     if (this.configuration.contains(paramString)) {
/* 44 */       return this.configuration.getDouble(paramString);
/*    */     }
/* 46 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public int getInt(String paramString) {
/* 50 */     if (this.configuration.contains(paramString)) {
/* 51 */       return this.configuration.getInt(paramString);
/*    */     }
/* 53 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String paramString) {
/* 57 */     return (this.configuration.contains(paramString)) && (this.configuration.getBoolean(paramString));
/*    */   }
/*    */   
/*    */   public String getString(String paramString) {
/* 61 */     if (this.configuration.contains(paramString)) {
/* 62 */       return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(paramString));
/*    */     }
/* 64 */     return "String at path: " + paramString + " not found!";
/*    */   }
/*    */   
/*    */   public List<String> getStringList(String paramString) {
/* 68 */     if (this.configuration.contains(paramString)) {
/* 69 */       ArrayList localArrayList = new ArrayList();
/* 70 */       for (String str : this.configuration.getStringList(paramString)) {
/* 71 */         localArrayList.add(ChatColor.translateAlternateColorCodes('&', str));
/*    */       }
/* 73 */       return localArrayList;
/*    */     }
/* 75 */     return Arrays.asList(new String[] { "String List at path: " + paramString + " not found!" });
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\LimitersFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */