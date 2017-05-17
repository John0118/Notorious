/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.EOTWHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.NumberUtils;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.ConsoleCommandSender;
/*    */ 
/*    */ public class EOTWCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if (paramCommand.getName().equalsIgnoreCase("eotw")) {
/* 17 */       if ((paramCommandSender instanceof ConsoleCommandSender)) {
/* 18 */         if (paramArrayOfString.length == 0) {
/* 19 */           sendUsage(paramCommandSender);
/* 20 */         } else if (paramArrayOfString.length == 1) {
/* 21 */           if (paramArrayOfString[0].equals("stop")) {
/* 22 */             if ((Notorious.getInstance().getEOTWHandler().isRunning()) || (Notorious.getInstance().getEOTWHandler().getEOTWTask() != null)) {
/* 23 */               Notorious.getInstance().getEOTWHandler().stopTimer();
/* 24 */               Notorious.getInstance().getEOTWHandler().stopEotw();
/*    */             } else {
/* 26 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.EOTW_NOT_RUNNING.toString());
/*    */             }
/*    */           } else {
/* 29 */             sendUsage(paramCommandSender);
/*    */           }
/* 31 */         } else if (paramArrayOfString.length == 2) {
/* 32 */           if (paramArrayOfString[0].equals("start")) {
/* 33 */             if (NumberUtils.isInteger(paramArrayOfString[1])) {
/* 34 */               int i = Integer.valueOf(paramArrayOfString[1]).intValue();
/* 35 */               Notorious.getInstance().getEOTWHandler().startTimer(i);
/*    */             } else {
/* 37 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*    */             }
/*    */           } else {
/* 40 */             sendUsage(paramCommandSender);
/*    */           }
/*    */         } else {
/* 43 */           sendUsage(paramCommandSender);
/*    */         }
/*    */       } else {
/* 46 */         paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_FOR_CONSOLE_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(CommandSender paramCommandSender) {
/* 53 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.EOTW_FORMAT_START.toString());
/* 54 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.EOTW_FORMAT_STOP.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\EOTWCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */