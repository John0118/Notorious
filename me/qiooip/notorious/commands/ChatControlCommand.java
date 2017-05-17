/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.ChatControlHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.NumberUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class ChatControlCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if (paramCommand.getName().equalsIgnoreCase("chat")) {
/* 17 */       if ((paramCommandSender.hasPermission("notorious.chatcontrol.mute")) || (paramCommandSender.hasPermission("notorious.chatcontrol.unmute")) || (paramCommandSender.hasPermission("notorious.chatcontrol.clear")) || (paramCommandSender.hasPermission("notorious.chatcontrol.delay"))) {
/* 18 */         if (paramArrayOfString.length == 0) {
/* 19 */           sendUsage(paramCommandSender); } else { int i;
/* 20 */           if (paramArrayOfString.length == 1) {
/* 21 */             if (paramArrayOfString[0].equals("mute")) {
/* 22 */               if (paramCommandSender.hasPermission("notorious.chatcontrol.mute")) {
/* 23 */                 if (!Notorious.getInstance().getChatControlHandler().isMuted()) {
/* 24 */                   Notorious.getInstance().getChatControlHandler().setMuted(true);
/* 25 */                   Bukkit.broadcastMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_MUTED.toString().replace("<player>", paramCommandSender.getName()));
/*    */                 } else {
/* 27 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_ALREADY_MUTED.toString());
/*    */                 }
/*    */               } else {
/* 30 */                 paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/* 32 */             } else if (paramArrayOfString[0].equals("unmute")) {
/* 33 */               if (paramCommandSender.hasPermission("notorious.chatcontrol.unmute")) {
/* 34 */                 if (Notorious.getInstance().getChatControlHandler().isMuted()) {
/* 35 */                   Notorious.getInstance().getChatControlHandler().setMuted(false);
/* 36 */                   Bukkit.broadcastMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_UNMUTED.toString().replace("<player>", paramCommandSender.getName()));
/*    */                 } else {
/* 38 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_NOT_MUTED.toString());
/*    */                 }
/*    */               } else {
/* 41 */                 paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/* 43 */             } else if (paramArrayOfString[0].equals("clear")) {
/* 44 */               if (paramCommandSender.hasPermission("notorious.chatcontrol.clear")) {
/* 45 */                 for (i = 0; i < 100; i++) {
/* 46 */                   Bukkit.broadcastMessage("");
/*    */                 }
/* 48 */                 Bukkit.broadcastMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_CLEAR_BROADCAST.toString().replace("<player>", paramCommandSender.getName()));
/*    */               } else {
/* 50 */                 paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/*    */             } else {
/* 53 */               sendUsage(paramCommandSender);
/*    */             }
/* 55 */           } else if (paramArrayOfString.length == 2) {
/* 56 */             if (paramArrayOfString[0].equals("delay")) {
/* 57 */               if (paramCommandSender.hasPermission("notorious.chatcontrol.delay")) {
/* 58 */                 if (NumberUtils.isInteger(paramArrayOfString[1])) {
/* 59 */                   i = Math.abs(Integer.valueOf(paramArrayOfString[1]).intValue());
/* 60 */                   Notorious.getInstance().getChatControlHandler().setDelay(i);
/* 61 */                   Bukkit.broadcastMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHAT_DELAY_BROADCAST.toString().replace("<delay>", String.valueOf(i)));
/*    */                 } else {
/* 63 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*    */                 }
/*    */               } else {
/* 66 */                 paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/*    */             }
/*    */           } else
/* 70 */             sendUsage(paramCommandSender);
/*    */         }
/*    */       } else {
/* 73 */         paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */       }
/*    */     }
/* 76 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(CommandSender paramCommandSender) {
/* 80 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_COMMAND_MUTE_FORMAT.toString());
/* 81 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_COMMAND_UNMUTE_FORMAT.toString());
/* 82 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_COMMAND_CLEAR_FORMAT.toString());
/* 83 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_COMMAND_DELAY_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\ChatControlCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */