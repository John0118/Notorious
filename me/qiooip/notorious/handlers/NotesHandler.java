/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.playerdata.PlayerData;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class NotesHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public NotesHandler(Notorious paramNotorious)
/*    */   {
/* 17 */     super(paramNotorious);
/*    */     
/* 19 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent)
/*    */   {
/* 25 */     Player localPlayer1 = paramPlayerJoinEvent.getPlayer();
/* 26 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(localPlayer1);
/*    */     
/* 28 */     if (!localPlayerData.getNotes().isEmpty()) { Player[] arrayOfPlayer;
/* 29 */       int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer2 = arrayOfPlayer[i];
/* 30 */         if (localPlayer2.hasPermission("notorious.staff")) {
/* 31 */           localPlayer2.sendMessage(Language.PREFIX.toString() + Language.NOTES_MESSAGE.toString().replace("<player>", localPlayer1.getName()));
/* 32 */           for (int k = 0; k < localPlayerData.getNotes().size(); k++) {
/* 33 */             int m = k + 1;
/* 34 */             localPlayer2.sendMessage(Language.NOTES_FORMAT.toString().replace("<number>", String.valueOf(m)).replace("<note>", (CharSequence)localPlayerData.getNotes().get(k)));
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\NotesHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */