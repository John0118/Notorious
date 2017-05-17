/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.CPSCountHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CPSAlertsCommand implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("cpsalerts")) {
/* 16 */       if ((paramCommandSender instanceof Player)) {
/* 17 */         Player localPlayer = (Player)paramCommandSender;
/* 18 */         if (paramCommandSender.hasPermission("notorious.cpsalerts")) {
/* 19 */           if (Notorious.getInstance().getCPSCountHandler().getAlertsRecievers().contains(localPlayer.getUniqueId())) {
/* 20 */             Notorious.getInstance().getCPSCountHandler().getAlertsRecievers().remove(localPlayer.getUniqueId());
/* 21 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.ALERTS_COMMAND_ENABLED.toString());
/*    */           } else {
/* 23 */             Notorious.getInstance().getCPSCountHandler().getAlertsRecievers().add(localPlayer.getUniqueId());
/* 24 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.ALERTS_COMMAND_DISABLED.toString());
/*    */           }
/*    */         } else {
/* 27 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 30 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\CPSAlertsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */