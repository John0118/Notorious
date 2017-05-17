/*    */ package me.qiooip.notorious.vanish.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import me.qiooip.notorious.vanish.OptionType;
/*    */ import me.qiooip.notorious.vanish.VanishHandler;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class VanishCommand implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramCommand.getName().equalsIgnoreCase("vanish")) {
/* 18 */       if ((paramCommandSender instanceof Player)) {
/* 19 */         Player localPlayer = (Player)paramCommandSender;
/* 20 */         if (localPlayer.hasPermission("notorious.vanish")) {
/* 21 */           if (paramArrayOfString.length == 0) {
/* 22 */             if (Notorious.getInstance().getVanishHandler().getVanishedPlayers().contains(localPlayer.getUniqueId())) {
/* 23 */               Notorious.getInstance().getVanishHandler().unvanishPlayer(localPlayer);
/*    */             } else {
/* 25 */               Notorious.getInstance().getVanishHandler().vanishPlayer(localPlayer);
/*    */             }
/* 27 */           } else if (paramArrayOfString.length == 1) {
/* 28 */             if (paramArrayOfString[0].equalsIgnoreCase("options")) {
/* 29 */               localPlayer.sendMessage(Language.VANISH_OPTIONS_LIST_FORMAT.toString().replace("<options>", StringUtils.getVanishOptionsList(localPlayer)));
/* 30 */             } else if ((paramArrayOfString[0].equalsIgnoreCase("toggle")) || (paramArrayOfString[0].equalsIgnoreCase("t"))) {
/* 31 */               sendUsage(localPlayer);
/*    */             } else {
/* 33 */               sendUsage(localPlayer);
/*    */             }
/* 35 */           } else if (paramArrayOfString.length == 2) {
/* 36 */             if ((paramArrayOfString[0].equalsIgnoreCase("toggle")) || (paramArrayOfString[0].equalsIgnoreCase("t"))) {
/* 37 */               if (Notorious.getInstance().getVanishHandler().getVanishedPlayers().contains(localPlayer.getUniqueId())) {
/* 38 */                 String str = paramArrayOfString[1];
/* 39 */                 int i = 0;
/* 40 */                 OptionType[] arrayOfOptionType; int k = (arrayOfOptionType = OptionType.values()).length; for (int j = 0; j < k; j++) { OptionType localOptionType = arrayOfOptionType[j];
/* 41 */                   if (localOptionType.getName().equalsIgnoreCase(str)) {
/* 42 */                     i = 1;
/* 43 */                     if (localOptionType.getPlayers().contains(localPlayer.getUniqueId())) {
/* 44 */                       localOptionType.getPlayers().remove(localPlayer.getUniqueId());
/* 45 */                       localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_TOGGLE_MESSAGE.toString().replace("<option>", new StringBuilder().append(org.bukkit.ChatColor.RED).append(localOptionType.getName()).toString()));
/*    */                     } else {
/* 47 */                       localOptionType.getPlayers().add(localPlayer.getUniqueId());
/* 48 */                       localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_TOGGLE_MESSAGE.toString().replace("<option>", new StringBuilder().append(org.bukkit.ChatColor.GREEN).append(localOptionType.getName()).toString()));
/*    */                     }
/*    */                   }
/*    */                 }
/* 52 */                 if (i == 0) {
/* 53 */                   localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_OPTIONS_DOES_NOT_EXIST.toString().replace("<option>", str));
/* 54 */                   localPlayer.sendMessage(Language.VANISH_OPTIONS_LIST_FORMAT.toString().replace("<options>", StringUtils.getVanishOptionsList(localPlayer)));
/*    */                 }
/*    */               } else {
/* 57 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_TOGGLE_NOT_VANISHED.toString());
/*    */               }
/*    */             } else {
/* 60 */               sendUsage(localPlayer);
/*    */             }
/*    */           } else {
/* 63 */             sendUsage(localPlayer);
/*    */           }
/*    */         } else {
/* 66 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 69 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 76 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_USAGE.toString());
/* 77 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_OPTIONS_USAGE.toString());
/* 78 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_TOGGLE_USAGE.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\vanish\commands\VanishCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */