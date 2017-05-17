/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class CoordsCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 13 */     if (paramCommand.getName().equalsIgnoreCase("coords")) {
/* 14 */       for (String str : Notorious.getInstance().getConfigHandler().getCoordsMessage()) {
/* 15 */         paramCommandSender.sendMessage(str);
/*    */       }
/*    */     }
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\CoordsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */