/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.handlers.SOTWHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class SOTWCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("sotw")) {
/* 16 */       if (paramCommandSender.hasPermission("notorious.staff")) {
/* 17 */         if (paramArrayOfString.length == 0) {
/* 18 */           sendUsage(paramCommandSender);
/* 19 */         } else if (paramArrayOfString.length == 1) {
/* 20 */           if (paramArrayOfString[0].equals("start")) {
/* 21 */             if (paramCommandSender.hasPermission("notorious.sotw.manage")) {
/* 22 */               if (!Notorious.getInstance().getSOTWHandler().isRunning()) {
/* 23 */                 Notorious.getInstance().getSOTWHandler().startSOTW(Notorious.getInstance().getConfigHandler().getDefaultSotwDuration());
/*    */               } else {
/* 25 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SOTW_ALREADY_RUNNING.toString());
/*    */               }
/*    */             } else {
/* 28 */               paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */             }
/* 30 */           } else if (paramArrayOfString[0].equals("stop")) {
/* 31 */             if (paramCommandSender.hasPermission("notorious.sotw.manage")) {
/* 32 */               if (Notorious.getInstance().getSOTWHandler().isRunning()) {
/* 33 */                 Notorious.getInstance().getSOTWHandler().stopSOTW();
/*    */               } else {
/* 35 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SOTW_NOT_RUNNING.toString());
/*    */               }
/*    */             } else {
/* 38 */               paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */             }
/*    */           } else {
/* 41 */             sendUsage(paramCommandSender);
/*    */           }
/* 43 */         } else if (paramArrayOfString.length == 2) {
/* 44 */           if (paramArrayOfString[0].equals("start")) {
/* 45 */             if (me.qiooip.notorious.utils.NumberUtils.isInteger(paramArrayOfString[1])) {
/* 46 */               if (!Notorious.getInstance().getSOTWHandler().isRunning()) {
/* 47 */                 Notorious.getInstance().getSOTWHandler().startSOTW(Integer.valueOf(paramArrayOfString[1]).intValue());
/*    */               } else {
/* 49 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SOTW_ALREADY_RUNNING.toString());
/*    */               }
/*    */             } else {
/* 52 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*    */             }
/*    */           } else {
/* 55 */             sendUsage(paramCommandSender);
/*    */           }
/*    */         } else {
/* 58 */           sendUsage(paramCommandSender);
/*    */         }
/*    */       } else {
/* 61 */         paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */       }
/*    */     }
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(CommandSender paramCommandSender) {
/* 68 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SOTW_FORMAT_START.toString());
/* 69 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SOTW_FORMAT_STOP.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\SOTWCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */