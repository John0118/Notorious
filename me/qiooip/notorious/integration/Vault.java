/*    */ package me.qiooip.notorious.integration;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import net.milkbowl.vault.permission.Permission;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ 
/*    */ public class Vault
/*    */ {
/*    */   public static Permission permission;
/*    */   
/*    */   public static boolean setup()
/*    */   {
/* 14 */     RegisteredServiceProvider localRegisteredServiceProvider = Notorious.getInstance().getServer().getServicesManager().getRegistration(Permission.class);
/* 15 */     if (localRegisteredServiceProvider != null) {
/* 16 */       permission = (Permission)localRegisteredServiceProvider.getProvider();
/*    */     }
/* 18 */     return permission != null;
/*    */   }
/*    */   
/*    */   public static String getPlayerGroup(Player paramPlayer) {
/* 22 */     return permission.getPrimaryGroup(paramPlayer);
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\integration\Vault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */