/*    */ package me.qiooip.notorious.staff.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.staff.StaffModeHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class StaffModeCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("staff")) {
/* 16 */       if ((paramCommandSender instanceof Player)) {
/* 17 */         Player localPlayer = (Player)paramCommandSender;
/* 18 */         if (localPlayer.hasPermission("notorious.staffmode")) {
/* 19 */           if (Notorious.getInstance().getStaffModeHandler().isInStaffMode(localPlayer)) {
/* 20 */             Notorious.getInstance().getStaffModeHandler().disableStaffMode(localPlayer);
/* 21 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.STAFFMODE_COMMAND_DISABLED.toString());
/*    */           } else {
/* 23 */             Notorious.getInstance().getStaffModeHandler().enableStaffMode(localPlayer);
/* 24 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.STAFFMODE_COMMAND_ENABLED.toString());
/*    */           }
/*    */         } else {
/* 27 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 30 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\staff\commands\StaffModeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */