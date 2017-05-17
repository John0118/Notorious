/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.CobbleHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CobbleCommand implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("cobble")) {
/* 16 */       if ((paramCommandSender instanceof Player)) {
/* 17 */         Player localPlayer = (Player)paramCommandSender;
/* 18 */         if (Notorious.getInstance().getCobbleHandler().getCobblePlayers().contains(localPlayer.getUniqueId())) {
/* 19 */           Notorious.getInstance().getCobbleHandler().getCobblePlayers().remove(localPlayer.getUniqueId());
/* 20 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.COBBLECOMMAND_ENABLED.toString());
/*    */         } else {
/* 22 */           Notorious.getInstance().getCobbleHandler().getCobblePlayers().add(localPlayer.getUniqueId());
/* 23 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.COBBLECOMMAND_DISABLED.toString());
/*    */         }
/*    */       } else {
/* 26 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\CobbleCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */