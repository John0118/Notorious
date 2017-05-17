/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.RequestHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class RequestCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramCommand.getName().equalsIgnoreCase("request")) {
/* 18 */       if ((paramCommandSender instanceof Player)) {
/* 19 */         Player localPlayer = (Player)paramCommandSender;
/* 20 */         if (paramArrayOfString.length == 0) {
/* 21 */           sendUsage(localPlayer);
/*    */         }
/* 23 */         else if (Notorious.getInstance().getRequestHandler().isActive(localPlayer)) {
/* 24 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.REQUEST_DELAY_MESSAGE.toString().replace("<seconds>", 
/* 25 */             StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.getInstance().getRequestHandler().getMillisecondsLeft(localPlayer)))));
/*    */         } else {
/* 27 */           Notorious.getInstance().getRequestHandler().applyCooldown(localPlayer);
/* 28 */           StringBuilder localStringBuilder = new StringBuilder();
/* 29 */           for (int i = 0; i < paramArrayOfString.length; i++) {
/* 30 */             localStringBuilder.append(paramArrayOfString[i]).append(" ");
/*    */           }
/* 32 */           Bukkit.broadcast(Language.REQUEST_STAFF_MESSAGE_FORMAT.toString().replace("<player>", localPlayer.getName()).replace("<message>", localStringBuilder), "notorious.request.receive");
/*    */         }
/*    */       }
/*    */       else {
/* 36 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 43 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.REQUEST_COMMAND_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\RequestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */