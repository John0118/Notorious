/*    */ package me.qiooip.notorious.config;
/*    */ 
/*    */ import java.io.File;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ public class CooldownFile
/*    */ {
/*    */   private File file;
/*    */   private YamlConfiguration configuration;
/*    */   
/*    */   public CooldownFile()
/*    */   {
/* 15 */     this.file = new File(Notorious.getInstance().getDataFolder(), "cooldowns.yml");
/* 16 */     if (!this.file.exists()) {
/* 17 */       Notorious.getInstance().saveResource("cooldowns.yml", false);
/*    */     }
/* 19 */     this.configuration = YamlConfiguration.loadConfiguration(this.file);
/*    */   }
/*    */   
/*    */   public YamlConfiguration getConfiguration() {
/* 23 */     return this.configuration;
/*    */   }
/*    */   
/*    */   public File getFile() {
/* 27 */     return this.file;
/*    */   }
/*    */   
/*    */   public long getLong(String paramString) {
/* 31 */     if (this.configuration.contains(paramString)) {
/* 32 */       return this.configuration.getLong(paramString);
/*    */     }
/* 34 */     return 0L;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\CooldownFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */