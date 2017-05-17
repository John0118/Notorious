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
/*    */ public class ModulesFile
/*    */ {
/*    */   private File file;
/*    */   private YamlConfiguration configuration;
/*    */   
/*    */   public ModulesFile()
/*    */   {
/* 19 */     this.file = new File(Notorious.getInstance().getDataFolder(), "modules.yml");
/* 20 */     if (!this.file.exists()) {
/* 21 */       Notorious.getInstance().saveResource("modules.yml", false);
/*    */     }
/* 23 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public YamlConfiguration getConfiguration() {
/* 27 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public double getDouble(String paramString) {
/* 31 */     if (this.configuration.contains(paramString)) {
/* 32 */       return this.configuration.getDouble(paramString);
/*    */     }
/* 34 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public int getInt(String paramString) {
/* 38 */     if (this.configuration.contains(paramString)) {
/* 39 */       return this.configuration.getInt(paramString);
/*    */     }
/* 41 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String paramString) {
/* 45 */     return (this.configuration.contains(paramString)) && (this.configuration.getBoolean(paramString));
/*    */   }
/*    */   
/*    */   public String getString(String paramString) {
/* 49 */     if (this.configuration.contains(paramString)) {
/* 50 */       return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(paramString));
/*    */     }
/* 52 */     return "String at path: " + paramString + " not found!";
/*    */   }
/*    */   
/*    */   public List<String> getStringList(String paramString) {
/* 56 */     if (this.configuration.contains(paramString)) {
/* 57 */       ArrayList localArrayList = new ArrayList();
/* 58 */       for (String str : this.configuration.getStringList(paramString)) {
/* 59 */         localArrayList.add(ChatColor.translateAlternateColorCodes('&', str));
/*    */       }
/* 61 */       return localArrayList;
/*    */     }
/* 63 */     return Arrays.asList(new String[] { "String List at path: " + paramString + " not found!" });
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\ModulesFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */