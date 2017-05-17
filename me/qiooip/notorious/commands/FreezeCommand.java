/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.FreezeHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class FreezeCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 16 */     if (paramCommand.getName().equalsIgnoreCase("freeze")) {
/* 17 */       if ((paramCommandSender instanceof Player)) {
/* 18 */         Player localPlayer1 = (Player)paramCommandSender;
/* 19 */         if (localPlayer1.hasPermission("notorious.freeze.use")) {
/* 20 */           if (paramArrayOfString.length == 0) {
/* 21 */             localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_USAGE.toString());
/* 22 */           } else if (paramArrayOfString.length == 1) {
/* 23 */             Player localPlayer2 = org.bukkit.Bukkit.getPlayer(paramArrayOfString[0]);
/* 24 */             if (localPlayer2 != null) {
/* 25 */               if (Notorious.getInstance().getFreezeHandler().getFreezedPlayers().contains(localPlayer2.getUniqueId())) {
/* 26 */                 Notorious.getInstance().getFreezeHandler().removeFreeze(localPlayer1, localPlayer2);
/*    */               }
/* 28 */               else if (localPlayer2 != localPlayer1) {
/* 29 */                 Notorious.getInstance().getFreezeHandler().addFreeze(localPlayer1, localPlayer2);
/*    */               } else {
/* 31 */                 localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_CAN_NOT_FREEZE_YOURSELF.toString());
/*    */               }
/*    */             }
/*    */             else {
/* 35 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[0]));
/*    */             }
/*    */           } else {
/* 38 */             localPlayer1.sendMessage(Language.PREFIX.toString() + Language.FREEZE_USAGE.toString());
/*    */           }
/*    */         } else {
/* 41 */           localPlayer1.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 44 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\FreezeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */