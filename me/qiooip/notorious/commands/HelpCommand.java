/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class HelpCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 14 */     if ((paramCommand.getName().equalsIgnoreCase("help")) && 
/* 15 */       ((paramCommandSender instanceof Player))) {
/* 16 */       Player localPlayer = (Player)paramCommandSender;
/* 17 */       if (paramArrayOfString.length == 0) {
/* 18 */         for (String str : Notorious.getInstance().getConfigHandler().getHelpCommandMessage()) {
/* 19 */           localPlayer.sendMessage(str);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\HelpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */