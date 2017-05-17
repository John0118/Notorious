/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.zencode.mango.Mango;
/*    */ import org.zencode.mango.factions.FactionManager;
/*    */ import org.zencode.mango.factions.types.PlayerFaction;
/*    */ 
/*    */ public class MuteFactionCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 18 */     if (paramCommand.getName().equalsIgnoreCase("mutefaction")) {
/* 19 */       if (paramCommandSender.hasPermission("notorious.mutefaction")) {
/* 20 */         if (paramArrayOfString.length == 1) {
/* 21 */           org.zencode.mango.factions.Faction localFaction = Mango.getInstance().getFactionManager().getFactionByName(paramArrayOfString[0]);
/*    */           
/* 23 */           if (localFaction != null) {
/* 24 */             if ((localFaction instanceof PlayerFaction)) {
/* 25 */               PlayerFaction localPlayerFaction = (PlayerFaction)localFaction;
/* 26 */               if (!localPlayerFaction.getOnlinePlayers().isEmpty()) {
/* 27 */                 for (Player localPlayer : localPlayerFaction.getOnlinePlayers()) {
/* 28 */                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Language.MUTE_FACTION_COMMAND.toString().replace("<player>", localPlayer.getName()));
/*    */                 }
/*    */               } else {
/* 31 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.MUTE_FACTION_NO_ONLINE_PLAYERS.toString().replace("<faction>", localPlayerFaction.getName()));
/*    */               }
/*    */             } else {
/* 34 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.MUTE_FACTION_NOT_EXISTANT.toString().replace("<faction>", paramArrayOfString[0]));
/*    */             }
/*    */           }
/*    */         }
/*    */         else
/*    */         {
/* 40 */           paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.MUTE_FACTION_USAGE.toString());
/*    */         }
/*    */       } else {
/* 43 */         paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */       }
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\MuteFactionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */