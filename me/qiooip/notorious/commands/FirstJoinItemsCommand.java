/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.config.UtilitiesFile;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ public class FirstJoinItemsCommand implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 19 */     if (paramCommand.getName().equalsIgnoreCase("firstjoinitems")) {
/* 20 */       if ((paramCommandSender instanceof Player)) {
/* 21 */         Player localPlayer = (Player)paramCommandSender;
/* 22 */         if (localPlayer.hasPermission("notorious.staff")) {
/* 23 */           if (paramArrayOfString.length == 0) {
/* 24 */             sendUsage(localPlayer);
/* 25 */           } else if (paramArrayOfString.length == 1) {
/* 26 */             if (paramArrayOfString[0].equals("set")) {
/* 27 */               if (localPlayer.hasPermission("notorious.joinitems.set")) {
/* 28 */                 ItemStack[] arrayOfItemStack = localPlayer.getInventory().getContents();
/* 29 */                 Notorious.getInstance().getConfigHandler().setFirstJoinItems(arrayOfItemStack);
/* 30 */                 Notorious.getInstance().getUtilitiesFile().getConfiguration().set("first-join-items", 
/* 31 */                   me.qiooip.notorious.utils.InventoryUtils.itemStackArrayToBase64(arrayOfItemStack));
/*    */                 try {
/* 33 */                   Notorious.getInstance().getUtilitiesFile().getConfiguration().save(Notorious.getInstance().getUtilitiesFile().getFile());
/*    */                 } catch (IOException localIOException2) {
/* 35 */                   localIOException2.printStackTrace();
/*    */                 }
/* 37 */                 localPlayer.getInventory().clear();
/* 38 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.FIRSTJOINITEMS_COMMAND_ADD_SUCCESSFUL.toString());
/*    */               } else {
/* 40 */                 localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/* 42 */             } else if (paramArrayOfString[0].equals("remove")) {
/* 43 */               if (localPlayer.hasPermission("notorious.joinitems.remove")) {
/* 44 */                 Notorious.getInstance().getConfigHandler().setFirstJoinItems(new ItemStack[36]);
/* 45 */                 Notorious.getInstance().getUtilitiesFile().getConfiguration().set("first-join-items", "");
/*    */                 try {
/* 47 */                   Notorious.getInstance().getUtilitiesFile().getConfiguration().save(Notorious.getInstance().getUtilitiesFile().getFile());
/*    */                 } catch (IOException localIOException1) {
/* 49 */                   localIOException1.printStackTrace();
/*    */                 }
/* 51 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.FIRSTJOINITEMS_COMMAND_REMOVE_SUCCESSFUL.toString());
/*    */               } else {
/* 53 */                 localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */               }
/*    */             } else {
/* 56 */               sendUsage(localPlayer);
/*    */             }
/*    */           } else {
/* 59 */             sendUsage(localPlayer);
/*    */           }
/*    */         } else {
/* 62 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 65 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 68 */     return true;
/*    */   }
/*    */   
/*    */   public void sendUsage(Player paramPlayer) {
/* 72 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.FIRSTJOINITEMS_COMMAND_SET_FORMAT.toString());
/* 73 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.FIRSTJOINITEMS_COMMAND_REMOVE_FORMAT.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\FirstJoinItemsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */