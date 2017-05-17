/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.LogoutHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class LogoutCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("logout")) {
/* 16 */       if ((paramCommandSender instanceof Player)) {
/* 17 */         Player localPlayer = (Player)paramCommandSender;
/* 18 */         if (Notorious.getInstance().getLogoutHandler().getLogoutTasks().containsKey(localPlayer.getUniqueId())) {
/* 19 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.LOGOUT_ALREADY_RUNNING.toString());
/*    */         } else {
/* 21 */           Notorious.getInstance().getLogoutHandler().createLogout(localPlayer);
/* 22 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.LOGOUT_START_MESSAGE.toString().replace("<seconds>", 
/* 23 */             String.valueOf(Notorious.getInstance().getConfigHandler().getLogoutTime())));
/*    */         }
/*    */       } else {
/* 26 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\LogoutCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */