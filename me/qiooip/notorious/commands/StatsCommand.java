/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.integration.Vault;
/*    */ import me.qiooip.notorious.playerdata.PlayerData;
/*    */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.zencode.mango.Mango;
/*    */ import org.zencode.mango.factions.Faction;
/*    */ import org.zencode.mango.factions.FactionManager;
/*    */ 
/*    */ public class StatsCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 20 */     if (paramCommand.getName().equalsIgnoreCase("stats")) {
/* 21 */       if ((paramCommandSender instanceof Player)) {
/* 22 */         Player localPlayer = (Player)paramCommandSender;
/*    */         
/* 24 */         if (localPlayer.hasPermission("notorious.stats")) { Object localObject2;
/* 25 */           Object localObject1; Object localObject3; Object localObject4; if (paramArrayOfString.length == 0)
/*    */           {
/* 27 */             localObject2 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/*    */             
/* 29 */             if (localObject2 == null) {
/* 30 */               localObject1 = "No Faction";
/*    */             } else {
/* 32 */               localObject1 = ((Faction)localObject2).getName();
/*    */             }
/*    */             
/* 35 */             localObject3 = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/* 36 */             localObject4 = Vault.getPlayerGroup(localPlayer);
/*    */             
/* 38 */             sendStatsMessage(localPlayer, localPlayer, (String)localObject1, (String)localObject4, String.valueOf(((PlayerData)localObject3).getKills()), String.valueOf(((PlayerData)localObject3).getDeaths()));
/*    */           }
/* 40 */           else if (paramArrayOfString.length == 1) {
/* 41 */             localObject1 = org.bukkit.Bukkit.getPlayer(paramArrayOfString[0]);
/*    */             
/* 43 */             if ((localObject1 != null) && (!Notorious.getInstance().getVanishHandler().isVanished((Player)localObject1)))
/*    */             {
/* 45 */               localObject3 = Mango.getInstance().getFactionManager().getFaction((Player)localObject1);
/*    */               
/* 47 */               if (localObject3 == null) {
/* 48 */                 localObject2 = "No Faction";
/*    */               } else {
/* 50 */                 localObject2 = ((Faction)localObject3).getName();
/*    */               }
/*    */               
/* 53 */               localObject4 = Notorious.getInstance().getPlayerDataHandler().getByPlayer((Player)localObject1);
/* 54 */               String str = Vault.getPlayerGroup((Player)localObject1);
/*    */               
/* 56 */               sendStatsMessage(localPlayer, (Player)localObject1, (String)localObject2, str, String.valueOf(((PlayerData)localObject4).getKills()), String.valueOf(((PlayerData)localObject4).getDeaths()));
/*    */             } else {
/* 58 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[0]));
/*    */             }
/*    */           } else {
/* 61 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.STATS_COMMAND_USAGE.toString());
/*    */           }
/*    */         } else {
/* 64 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 67 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public void sendStatsMessage(Player paramPlayer1, Player paramPlayer2, String paramString1, String paramString2, String paramString3, String paramString4) {
/* 74 */     for (String str : Notorious.getInstance().getConfigHandler().getStatsCommandMessage()) {
/* 75 */       paramPlayer1.sendMessage(str.replace("<player>", paramPlayer2.getName()).replace("<faction>", paramString1).replace("<rank>", paramString2).replace("<kills>", paramString3).replace("<deaths>", paramString4));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\StatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */