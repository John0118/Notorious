/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.handlers.ReportHandler;
/*    */ import me.qiooip.notorious.kits.data.BardHoldItemHandler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class NotoriousCommand implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramCommand.getName().equalsIgnoreCase("notorious")) {
/* 18 */       if ((paramCommandSender instanceof Player)) {
/* 19 */         Player localPlayer = (Player)paramCommandSender;
/* 20 */         if (paramArrayOfString.length == 0) {
/* 21 */           sendCoreInfo(localPlayer);
/* 22 */         } else if (paramArrayOfString.length == 1) {
/* 23 */           if (paramArrayOfString[0].equals("reload")) {
/* 24 */             if (localPlayer.hasPermission("notorious.reload")) {
/* 25 */               reloadConfigs(localPlayer);
/*    */             } else {
/* 27 */               localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */             }
/*    */           }
/*    */         } else {
/* 31 */           sendCoreInfo(localPlayer);
/*    */         }
/*    */       }
/* 34 */       else if (paramArrayOfString.length == 0) {
/* 35 */         sendCoreInfo(paramCommandSender);
/* 36 */       } else if (paramArrayOfString.length == 1) {
/* 37 */         if (paramArrayOfString[0].equals("reload")) {
/* 38 */           reloadConfigs(paramCommandSender);
/*    */         }
/*    */       } else {
/* 41 */         sendCoreInfo(paramCommandSender);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public void sendCoreInfo(CommandSender paramCommandSender) {
/* 50 */     if (Notorious.getInstance().getConfigHandler().isEnableCoreInfo()) {
/* 51 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + ChatColor.GRAY + "Plugin created by " + ChatColor.DARK_AQUA + "qIooIp" + ChatColor.GRAY + ".");
/* 52 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + ChatColor.GRAY + "You are using version " + ChatColor.DARK_AQUA + Notorious.getInstance().getDescription().getVersion() + ChatColor.GRAY + ".");
/*    */     }
/*    */   }
/*    */   
/*    */   public void reloadConfigs(CommandSender paramCommandSender) {
/* 57 */     Notorious.getInstance().getConfigFile().load();
/* 58 */     Notorious.getInstance().getDeathBanFile().load();
/* 59 */     Notorious.getInstance().getMessagesFile().load();
/* 60 */     Notorious.getInstance().getLimitersFile().load();
/* 61 */     Notorious.getInstance().setupLanguage();
/*    */     
/* 63 */     Notorious.getInstance().setConfigHandler(new me.qiooip.notorious.config.ConfigHandler(Notorious.getInstance()));
/*    */     
/* 65 */     Notorious.getInstance().getDeathBanHandler().loadDeathBanTimes();
/* 66 */     Notorious.getInstance().getEnchantmentLimiterHandler().loadEnchantmentLimits();
/* 67 */     Notorious.getInstance().getPotionLimtierHandler().loadPotionLimits();
/* 68 */     Notorious.getInstance().getBardClickableItemHandler().getBardClickableItems().clear();
/* 69 */     Notorious.getInstance().getBardHoldItemHandler().getBardHoldItems().clear();
/* 70 */     Notorious.getInstance().getBardClickableItemHandler().loadBardClickableItems();
/* 71 */     Notorious.getInstance().getBardHoldItemHandler().loadBardHoldItems();
/* 72 */     Notorious.getInstance().getScoreboardDataHandler().reload();
/* 73 */     Notorious.getInstance().getReportHandler().loadInventoryItems();
/* 74 */     Notorious.getInstance().getReportHandler().createInventory();
/* 75 */     Notorious.getInstance().getStaffModeHandler().loadStaffModeItems();
/* 76 */     Notorious.getInstance().getMobStackHandler().loadEntityList();
/* 77 */     Notorious.getInstance().getMapKitHandler().loadInventory();
/* 78 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.CORE_RELOAD_MESSAGE.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\NotoriousCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */