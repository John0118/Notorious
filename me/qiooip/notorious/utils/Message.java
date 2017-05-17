/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Message
/*    */ {
/*    */   public static void sendMessage(String paramString)
/*    */   {
/*    */     Player[] arrayOfPlayer;
/* 10 */     int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 11 */       localPlayer.sendMessage(paramString);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void sendMessage(String paramString1, String paramString2) {
/*    */     Player[] arrayOfPlayer;
/* 17 */     int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*    */       
/* 19 */       if (localPlayer.hasPermission(paramString2)) {
/* 20 */         localPlayer.sendMessage(paramString1);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void sendMessageWithoutPlayer(Player paramPlayer, String paramString)
/*    */   {
/*    */     Player[] arrayOfPlayer;
/* 28 */     int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 29 */       if (localPlayer != paramPlayer) {
/* 30 */         localPlayer.sendMessage(paramString);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void sendMessageWithoutPlayer(Player paramPlayer, String paramString1, String paramString2) {
/*    */     Player[] arrayOfPlayer;
/* 37 */     int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*    */       
/* 39 */       if ((localPlayer != paramPlayer) && (localPlayer.hasPermission(paramString2))) {
/* 40 */         localPlayer.sendMessage(paramString1);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */