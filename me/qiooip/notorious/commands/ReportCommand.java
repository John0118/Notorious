/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.ReportHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import me.qiooip.notorious.vanish.VanishHandler;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ReportCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramCommand.getName().equalsIgnoreCase("report")) {
/* 18 */       if ((paramCommandSender instanceof Player)) {
/* 19 */         Player localPlayer1 = (Player)paramCommandSender;
/* 20 */         if (paramArrayOfString.length == 0) {
/* 21 */           sendUsage(localPlayer1);
/* 22 */         } else if (paramArrayOfString.length == 1) {
/* 23 */           Player localPlayer2 = org.bukkit.Bukkit.getPlayer(paramArrayOfString[0]);
/* 24 */           if ((localPlayer2 != null) && (!Notorious.getInstance().getVanishHandler().isVanished(localPlayer2))) {
/* 25 */             if (localPlayer2 != localPlayer1) {
/* 26 */               if (Notorious.getInstance().getReportHandler().isActive(localPlayer1)) {
/* 27 */                 localPlayer1.sendMessage(Language.PREFIX.toString() + Language.REPORT_DELAY_MESSAGE.toString().replace("<seconds>", 
/* 28 */                   StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.getInstance().getReportHandler().getMillisecondsLeft(localPlayer1)))));
/*    */               } else {
/* 30 */                 Notorious.getInstance().getReportHandler().addReporter(localPlayer1, localPlayer2);
/* 31 */                 localPlayer1.openInventory(Notorious.getInstance().getReportHandler().getInventory());
/*    */               }
/*    */             } else {
/* 34 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.REPORT_CAN_NOT_REPORT_YOURSELF.toString());
/*    */             }
/*    */           } else {
/* 37 */             localPlayer1.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[0]));
/*    */           }
/*    */         } else {
/* 40 */           sendUsage(localPlayer1);
/*    */         }
/*    */       } else {
/* 43 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 50 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.REPORT_COMMAND_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\ReportCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */