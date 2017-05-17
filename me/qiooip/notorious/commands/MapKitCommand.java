/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.MapKitHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class MapKitCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 15 */     if (paramCommand.getName().equalsIgnoreCase("mapkit")) {
/* 16 */       if ((paramCommandSender instanceof Player)) {
/* 17 */         Player localPlayer = (Player)paramCommandSender;
/* 18 */         if (paramArrayOfString.length == 0) {
/* 19 */           localPlayer.openInventory(Notorious.getInstance().getMapKitHandler().getMapKitInventory());
/* 20 */         } else if ((paramArrayOfString.length == 1) && 
/* 21 */           (paramArrayOfString[0].equals("edit"))) {
/* 22 */           if (localPlayer.hasPermission("notorious.mapkit.edit")) {
/* 23 */             Notorious.getInstance().getMapKitHandler().getEditingMapKit().add(localPlayer.getUniqueId());
/* 24 */             localPlayer.openInventory(Notorious.getInstance().getMapKitHandler().getMapKitInventory());
/*    */           } else {
/* 26 */             localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */           }
/*    */         }
/*    */       }
/*    */       else {
/* 31 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\MapKitCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */