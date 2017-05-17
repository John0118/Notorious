/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.platinum.PlatinumReviveHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.Message;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlatinumCommand implements CommandExecutor
/*    */ {
/* 19 */   private File deathbanFolder = new File(Notorious.getInstance().getDataFolder(), "deathban");
/* 20 */   private File deathbansFolder = new File(this.deathbanFolder, "deathbans");
/*    */   
/*    */ 
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 25 */     if (paramCommand.getName().equalsIgnoreCase("medic")) {
/* 26 */       if ((paramCommandSender instanceof Player)) {
/* 27 */         Player localPlayer = (Player)paramCommandSender;
/*    */         
/* 29 */         if (localPlayer.hasPermission("notorious.medic.revive"))
/*    */         {
/* 31 */           if (paramArrayOfString.length == 2) {
/* 32 */             if (paramArrayOfString[0].equals("revive")) {
/* 33 */               OfflinePlayer localOfflinePlayer = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/*    */               
/* 35 */               if (Notorious.getInstance().getPlatinumReviveHandler().isActive(localPlayer)) {
/* 36 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATINUM_REVIVE_COOLDOWN.toString().replace("<time>", StringUtils.formatMilisecondsToMinutes(Long.valueOf(Notorious.getInstance().getPlatinumReviveHandler().getMillisecondsLeft(localPlayer)))));
/*    */               } else {
/* 38 */                 revivePlayer(localPlayer, localOfflinePlayer);
/*    */               }
/*    */             } else {
/* 41 */               sendUsage(localPlayer);
/*    */             }
/*    */           } else {
/* 44 */             sendUsage(localPlayer);
/*    */           }
/*    */         }
/*    */         else {
/* 48 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 51 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public void revivePlayer(Player paramPlayer, OfflinePlayer paramOfflinePlayer) {
/* 58 */     File localFile = new File(this.deathbansFolder, paramOfflinePlayer.getUniqueId().toString() + ".yml");
/* 59 */     if (localFile.exists()) {
/* 60 */       localFile.delete();
/* 61 */       Message.sendMessage(Language.PLATINUM_REVIVE_MESSAGE.toString().replace("<player>", paramPlayer.getName()).replace("<target>", paramOfflinePlayer.getName()));
/* 62 */       Notorious.getInstance().getPlatinumReviveHandler().apply(paramPlayer);
/*    */     } else {
/* 64 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_NOT_DEATHBANNED.toString().replace("<player>", paramOfflinePlayer.getName()));
/*    */     }
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 69 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.PLATINUM_REVIVE_COMMAND_USAGE.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\PlatinumCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */