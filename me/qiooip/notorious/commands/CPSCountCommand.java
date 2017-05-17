/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.CPSCountHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CPSCountCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if ((paramCommand.getName().equalsIgnoreCase("cps")) && 
/* 17 */       (paramCommandSender.hasPermission("notorious.cps"))) {
/* 18 */       if (paramArrayOfString.length == 0) {
/* 19 */         sendUsage(paramCommandSender);
/* 20 */       } else if (paramArrayOfString.length == 1) {
/* 21 */         Player localPlayer = org.bukkit.Bukkit.getPlayer(paramArrayOfString[0]);
/* 22 */         if (localPlayer != null) {
/* 23 */           if (Notorious.getInstance().getCPSCountHandler().getCpsCounters().containsKey(localPlayer.getUniqueId())) {
/* 24 */             if (((Integer)Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer.getUniqueId())).intValue() < 5) {
/* 25 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_MESSAGE_FORMAT.toString().replace("<player>", localPlayer.getName()).replace("<cps>", 
/* 26 */                 String.valueOf(Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer.getUniqueId()))));
/*    */             } else {
/* 28 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_MESSAGE_FORMAT.toString().replace("<player>", localPlayer.getName()).replace("<cps>", 
/* 29 */                 String.valueOf(((Integer)Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer.getUniqueId())).intValue() / 5)));
/*    */             }
/*    */           } else {
/* 32 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", localPlayer.getName()));
/*    */           }
/*    */         } else {
/* 35 */           paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[0]));
/*    */         }
/*    */       } else {
/* 38 */         sendUsage(paramCommandSender);
/*    */       }
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(CommandSender paramCommandSender) {
/* 46 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\CPSCountCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */