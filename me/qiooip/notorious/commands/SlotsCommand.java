/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.Message;
/*    */ import me.qiooip.notorious.utils.NumberUtils;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class SlotsCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if (paramCommand.getName().equalsIgnoreCase("slots"))
/*    */     {
/* 18 */       if (paramCommandSender.hasPermission("notorious.slots")) {
/* 19 */         if (paramArrayOfString.length == 1) {
/* 20 */           if (NumberUtils.isInteger(paramArrayOfString[0])) {
/* 21 */             Notorious.getInstance().getJoinFullServerHandler().setMaxSlots(Integer.valueOf(paramArrayOfString[0]).intValue());
/*    */             
/* 23 */             for (String str : Notorious.getInstance().getConfigHandler().getSlotsCommandMessage()) {
/* 24 */               Message.sendMessage(str.replace("<number>", paramArrayOfString[0]).replace("<player>", paramCommandSender.getName()));
/*    */             }
/*    */           }
/*    */           else {
/* 28 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*    */           }
/*    */         } else {
/* 31 */           paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.SLOTS_COMMAND_USAGE.toString());
/*    */         }
/*    */       } else {
/* 34 */         paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */       }
/*    */     }
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\SlotsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */