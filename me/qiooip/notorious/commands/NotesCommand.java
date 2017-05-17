/*     */ package me.qiooip.notorious.commands;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class NotesCommand implements CommandExecutor
/*     */ {
/*     */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*     */   {
/*  18 */     if (paramCommand.getName().equalsIgnoreCase("notes")) {
/*  19 */       if (paramCommandSender.hasPermission("notorious.notes")) {
/*  20 */         if (paramArrayOfString.length == 0) {
/*  21 */           sendUsage(paramCommandSender);
/*  22 */         } else if (paramArrayOfString.length == 1) {
/*  23 */           if (paramArrayOfString[0].equalsIgnoreCase("check")) {
/*  24 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_CHECK_USAGE.toString());
/*  25 */           } else if (paramArrayOfString[0].equalsIgnoreCase("add")) {
/*  26 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_ADD_USAGE.toString());
/*  27 */           } else if (paramArrayOfString[0].equalsIgnoreCase("remove")) {
/*  28 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_REMOVE_USAGE.toString());
/*     */           } else
/*  30 */             sendUsage(paramCommandSender); } else { Player localPlayer;
/*     */           PlayerData localPlayerData1;
/*  32 */           int m; if (paramArrayOfString.length == 2) {
/*  33 */             if (paramArrayOfString[0].equalsIgnoreCase("check")) {
/*  34 */               localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/*  35 */               if (localPlayer != null) {
/*  36 */                 localPlayerData1 = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  37 */                 if (!localPlayerData1.getNotes().isEmpty()) {
/*  38 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_MESSAGE.toString().replace("<player>", localPlayer.getName()));
/*  39 */                   for (int j = 0; j < localPlayerData1.getNotes().size(); j++) {
/*  40 */                     m = j + 1;
/*  41 */                     paramCommandSender.sendMessage(Language.NOTES_FORMAT.toString().replace("<number>", String.valueOf(m)).replace("<note>", (CharSequence)localPlayerData1.getNotes().get(j)));
/*     */                   }
/*     */                 } else {
/*  44 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_NO_NOTES.toString().replace("<player>", localPlayer.getName()));
/*     */                 }
/*     */               } else {
/*  47 */                 paramCommandSender.sendMessage(Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */               }
/*  49 */             } else if (paramArrayOfString[0].equalsIgnoreCase("add")) {
/*  50 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_ADD_USAGE.toString());
/*  51 */             } else if (paramArrayOfString[0].equalsIgnoreCase("remove")) {
/*  52 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_REMOVE_USAGE.toString());
/*     */             } else {
/*  54 */               sendUsage(paramCommandSender);
/*     */             }
/*     */           }
/*  57 */           else if (paramArrayOfString[0].equalsIgnoreCase("add")) {
/*  58 */             localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/*  59 */             if (localPlayer != null) {
/*  60 */               localPlayerData1 = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  61 */               StringBuilder localStringBuilder = new StringBuilder();
/*  62 */               for (m = 2; m < paramArrayOfString.length; m++) {
/*  63 */                 localStringBuilder.append(paramArrayOfString[m]).append(" ");
/*     */               }
/*  65 */               localPlayerData1.getNotes().add(localStringBuilder.toString());
/*  66 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_NOTE_ADDED.toString().replace("<player>", localPlayer.getName()));
/*     */             } else {
/*  68 */               paramCommandSender.sendMessage(Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */             }
/*  70 */           } else if (paramArrayOfString[0].equalsIgnoreCase("remove")) {
/*  71 */             localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/*  72 */             if (localPlayer != null) {
/*  73 */               if (me.qiooip.notorious.utils.NumberUtils.isInteger(paramArrayOfString[2])) {
/*  74 */                 int i = Integer.valueOf(paramArrayOfString[2]).intValue();
/*  75 */                 int k = Math.abs(i);
/*  76 */                 if (i != 0) {
/*  77 */                   PlayerData localPlayerData2 = Notorious.getInstance().getPlayerDataHandler().getByPlayer(localPlayer);
/*  78 */                   if (localPlayerData2.getNotes().size() < k) {
/*  79 */                     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_NOTE_DOESNT_EXIST.toString().replace("<number>", String.valueOf(k)));
/*     */                   } else {
/*  81 */                     int n = k - 1;
/*  82 */                     localPlayerData2.getNotes().remove(n);
/*  83 */                     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_NOTE_REMOVED.toString().replace("<number>", String.valueOf(k)));
/*     */                   }
/*     */                 } else {
/*  86 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                 }
/*     */               } else {
/*  89 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */               }
/*     */             } else {
/*  92 */               paramCommandSender.sendMessage(Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */             }
/*     */           } else {
/*  95 */             sendUsage(paramCommandSender);
/*     */           }
/*     */         }
/*     */       } else {
/*  99 */         paramCommandSender.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */       }
/*     */     }
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   public void sendUsage(CommandSender paramCommandSender) {
/* 106 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_CHECK_USAGE.toString());
/* 107 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_ADD_USAGE.toString());
/* 108 */     paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.NOTES_COMMAND_REMOVE_USAGE.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\NotesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */