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
/*    */ public class ConfigFile
/*    */ {
/*    */   private Notorious plugin;
/*    */   private File file;
/*    */   private YamlConfiguration configuration;
/*    */   
/*    */   public ConfigFile()
/*    */   {
/* 20 */     (this.plugin = Notorious.getInstance()).saveDefaultConfig();
/* 21 */     this.file = new File(this.plugin.getDataFolder(), "config.yml");
/* 22 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public void load() {
/* 26 */     this.file = new File(this.plugin.getDataFolder(), "config.yml");
/* 27 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public YamlConfiguration getConfiguration() {
/* 31 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public double getDouble(String paramString) {
/* 35 */     if (this.configuration.contains(paramString)) {
/* 36 */       return this.configuration.getDouble(paramString);
/*    */     }
/* 38 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public int getInt(String paramString) {
/* 42 */     if (this.configuration.contains(paramString)) {
/* 43 */       return this.configuration.getInt(paramString);
/*    */     }
/* 45 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String paramString) {
/* 49 */     return (this.configuration.contains(paramString)) && (this.configuration.getBoolean(paramString));
/*    */   }
/*    */   
/*    */   public String getString(String paramString) {
/* 53 */     if (this.configuration.contains(paramString)) {
/* 54 */       return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(paramString));
/*    */     }
/* 56 */     return "String at path: " + paramString + " not found!";
/*    */   }
/*    */   
/*    */   public List<String> getStringList(String paramString) {
/* 60 */     if (this.configuration.contains(paramString)) {
/* 61 */       ArrayList localArrayList = new ArrayList();
/* 62 */       for (String str : this.configuration.getStringList(paramString)) {
/* 63 */         localArrayList.add(ChatColor.translateAlternateColorCodes('&', str));
/*    */       }
/* 65 */       return localArrayList;
/*    */     }
/* 67 */     return Arrays.asList(new String[] { "String List at path: " + paramString + " not found!" });
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\ConfigFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */