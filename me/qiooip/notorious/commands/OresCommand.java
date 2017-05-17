/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.OresHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class OresCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if (paramCommand.getName().equalsIgnoreCase("ores")) {
/* 17 */       if ((paramCommandSender instanceof Player)) {
/* 18 */         Player localPlayer1 = (Player)paramCommandSender;
/* 19 */         if (paramArrayOfString.length == 0) {
/* 20 */           localPlayer1.openInventory(Notorious.getInstance().getOresHandler().createOresInventory(localPlayer1));
/* 21 */         } else if (paramArrayOfString.length == 1) {
/* 22 */           Player localPlayer2 = Bukkit.getPlayer(paramArrayOfString[0]);
/* 23 */           if (localPlayer2 != null) {
/* 24 */             localPlayer1.openInventory(Notorious.getInstance().getOresHandler().createOresInventory(localPlayer2));
/*    */           } else {
/* 26 */             localPlayer1.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[0]));
/*    */           }
/*    */         } else {
/* 29 */           sendUsage(localPlayer1);
/*    */         }
/*    */       } else {
/* 32 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 39 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.ORES_COMMAND_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\OresCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */