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
/*    */ public class MessagesFile
/*    */ {
/*    */   private File file;
/*    */   private YamlConfiguration configuration;
/*    */   
/*    */   public MessagesFile()
/*    */   {
/* 19 */     this.file = new File(Notorious.getInstance().getDataFolder(), "messages.yml");
/* 20 */     if (!this.file.exists()) {
/* 21 */       Notorious.getInstance().saveResource("messages.yml", false);
/*    */     }
/* 23 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public void load() {
/* 27 */     this.file = new File(Notorious.getInstance().getDataFolder(), "messages.yml");
/* 28 */     if (!this.file.exists()) {
/* 29 */       Notorious.getInstance().saveResource("messages.yml", false);
/*    */     }
/* 31 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public YamlConfiguration getConfiguration() {
/* 35 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public double getDouble(String paramString) {
/* 39 */     if (this.configuration.contains(paramString)) {
/* 40 */       return this.configuration.getDouble(paramString);
/*    */     }
/* 42 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public int getInt(String paramString) {
/* 46 */     if (this.configuration.contains(paramString)) {
/* 47 */       return this.configuration.getInt(paramString);
/*    */     }
/* 49 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String paramString) {
/* 53 */     return (this.configuration.contains(paramString)) && (this.configuration.getBoolean(paramString));
/*    */   }
/*    */   
/*    */   public String getString(String paramString) {
/* 57 */     if (this.configuration.contains(paramString)) {
/* 58 */       return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(paramString));
/*    */     }
/* 60 */     return "String at path: " + paramString + " not found!";
/*    */   }
/*    */   
/*    */   public List<String> getStringList(String paramString) {
/* 64 */     if (this.configuration.contains(paramString)) {
/* 65 */       ArrayList localArrayList = new ArrayList();
/* 66 */       for (String str : this.configuration.getStringList(paramString)) {
/* 67 */         localArrayList.add(ChatColor.translateAlternateColorCodes('&', str));
/*    */       }
/* 69 */       return localArrayList;
/*    */     }
/* 71 */     return Arrays.asList(new String[] { "String List at path: " + paramString + " not found!" });
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\MessagesFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */