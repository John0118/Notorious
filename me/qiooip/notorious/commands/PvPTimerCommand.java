/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.playerdata.PlayerData;
/*    */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PvPTimerCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramCommand.getName().equalsIgnoreCase("pvp")) {
/* 18 */       if ((paramCommandSender instanceof Player)) {
/* 19 */         Player localPlayer = (Player)paramCommandSender;
/* 20 */         if (paramArrayOfString.length == 0) {
/* 21 */           sendCommandUsage(localPlayer);
/* 22 */         } else if (paramArrayOfString.length == 1) {
/* 23 */           PlayerData localPlayerData = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/* 24 */           if (paramArrayOfString[0].equals("enable")) {
/* 25 */             if (localPlayerData.getPvPTime() > 0) {
/* 26 */               localPlayerData.setPvPTime(0);
/* 27 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_TIMER_DISABLED.toString());
/*    */             } else {
/* 29 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_TIMER_NOT_ACTIVE.toString());
/*    */             }
/* 31 */           } else if (paramArrayOfString[0].equals("time")) {
/* 32 */             if (localPlayerData.getPvPTime() > 0) {
/* 33 */               String str = StringUtils.formatSecondsToMinutes(localPlayerData.getPvPTime());
/* 34 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_TIMER_STATUS.toString().replace("<time>", str));
/*    */             } else {
/* 36 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_TIMER_NOT_ACTIVE.toString());
/*    */             }
/*    */           } else {
/* 39 */             sendCommandUsage(localPlayer);
/*    */           }
/*    */         } else {
/* 42 */           sendCommandUsage(localPlayer);
/*    */         }
/*    */       } else {
/* 45 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public void sendCommandUsage(Player paramPlayer) {
/* 52 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_FORMAT_ENABLE.toString());
/* 53 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.PVPTIMER_FORMAT_TIME.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\PvPTimerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */