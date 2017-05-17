/*     */ package me.qiooip.notorious.deathban.commands;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.NumberUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class LivesCommand implements CommandExecutor
/*     */ {
/*  21 */   private File deathbanFolder = new File(Notorious.getInstance().getDataFolder(), "deathban");
/*  22 */   private File deathbansFolder = new File(this.deathbanFolder, "deathbans");
/*  23 */   private File livesFolder = new File(this.deathbanFolder, "lives");
/*     */   
/*     */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*     */   {
/*  27 */     if (paramCommand.getName().equalsIgnoreCase("lives")) { Player localPlayer;
/*  28 */       if ((paramCommandSender instanceof Player)) {
/*  29 */         localPlayer = (Player)paramCommandSender;
/*  30 */         if (paramArrayOfString.length == 0) {
/*  31 */           sendUsage(localPlayer);
/*  32 */         } else if (paramArrayOfString.length == 1) {
/*  33 */           if (paramArrayOfString[0].equals("check")) {
/*  34 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_CHECK_SELF.toString().replace("<lives>", String.valueOf(getLives(localPlayer))));
/*  35 */           } else if (paramArrayOfString[0].equals("revive")) {
/*  36 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_REVIVE_USAGE.toString());
/*  37 */           } else if (paramArrayOfString[0].equals("send")) {
/*  38 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_SEND_USAGE.toString());
/*     */           } else
/*  40 */             sendUsage(localPlayer);
/*     */         } else { Object localObject;
/*  42 */           if (paramArrayOfString.length == 2) {
/*  43 */             if (paramArrayOfString[0].equals("check")) {
/*  44 */               localObject = Bukkit.getPlayer(paramArrayOfString[1]);
/*  45 */               if (localObject != null) {
/*  46 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_CHECK_OTHERS.toString().replace("<player>", ((Player)localObject).getName()).replace("<lives>", String.valueOf(getLives((Player)localObject))));
/*     */               } else {
/*  48 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */               }
/*  50 */             } else if (paramArrayOfString[0].equals("revive")) {
/*  51 */               localObject = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/*  52 */               revivePlayer(localPlayer, (OfflinePlayer)localObject);
/*  53 */             } else if (paramArrayOfString[0].equals("send")) {
/*  54 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_SEND_USAGE.toString());
/*     */             } else {
/*  56 */               sendUsage(localPlayer);
/*     */             }
/*  58 */           } else if (paramArrayOfString.length == 3) { int k;
/*  59 */             if (paramArrayOfString[0].equals("send")) {
/*  60 */               localObject = Bukkit.getPlayer(paramArrayOfString[1]);
/*  61 */               if (localObject != null) {
/*  62 */                 if (localObject != localPlayer) {
/*  63 */                   if (NumberUtils.isInteger(paramArrayOfString[2])) {
/*  64 */                     k = Integer.parseInt(paramArrayOfString[2]);
/*  65 */                     sendLives(localPlayer, (Player)localObject, k);
/*     */                   } else {
/*  67 */                     localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                   }
/*     */                 } else {
/*  70 */                   localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_CAN_NOT_SEND_LIVES_TO_YOURSELF.toString());
/*     */                 }
/*     */               } else
/*  73 */                 localPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */             } else { int i1;
/*  75 */               if (paramArrayOfString[0].equals("set")) {
/*  76 */                 if (localPlayer.hasPermission("notorious.lives.set")) {
/*  77 */                   localObject = Bukkit.getPlayer(paramArrayOfString[1]);
/*  78 */                   if (localObject != null) {
/*  79 */                     if (NumberUtils.isInteger(paramArrayOfString[2])) {
/*  80 */                       k = Integer.parseInt(paramArrayOfString[2]);
/*  81 */                       setLives(localPlayer, (Player)localObject, k);
/*     */                     } else {
/*  83 */                       localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                     }
/*     */                   }
/*  86 */                   else if (NumberUtils.isInteger(paramArrayOfString[2])) {
/*  87 */                     OfflinePlayer localOfflinePlayer3 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/*  88 */                     i1 = Integer.parseInt(paramArrayOfString[2]);
/*  89 */                     setLives(localPlayer, localOfflinePlayer3, i1);
/*     */                   } else {
/*  91 */                     localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                   }
/*     */                 } else {
/*  94 */                   localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */                 }
/*  96 */               } else if (paramArrayOfString[0].equals("add")) {
/*  97 */                 if (localPlayer.hasPermission("notorious.lives.add")) {
/*  98 */                   localObject = Bukkit.getPlayer(paramArrayOfString[1]);
/*  99 */                   if (localObject != null) {
/* 100 */                     if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 101 */                       int m = Integer.parseInt(paramArrayOfString[2]);
/* 102 */                       addLives(localPlayer, (Player)localObject, m);
/*     */                     } else {
/* 104 */                       localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                     }
/*     */                   }
/* 107 */                   else if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 108 */                     OfflinePlayer localOfflinePlayer4 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/* 109 */                     i1 = Integer.parseInt(paramArrayOfString[2]);
/* 110 */                     addLives(localPlayer, localOfflinePlayer4, i1);
/*     */                   } else {
/* 112 */                     localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                   }
/*     */                 } else {
/* 115 */                   localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */                 }
/*     */               } else
/* 118 */                 sendUsage(localPlayer);
/*     */             }
/*     */           } else {
/* 121 */             sendUsage(localPlayer);
/*     */           }
/* 123 */         } } else if ((paramCommandSender instanceof ConsoleCommandSender)) {
/* 124 */         if (paramArrayOfString.length == 0) {
/* 125 */           sendUsage(paramCommandSender);
/* 126 */         } else if (paramArrayOfString.length == 1) {
/* 127 */           if (paramArrayOfString[0].equals("check")) {
/* 128 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/* 129 */           } else if (paramArrayOfString[0].equals("revive")) {
/* 130 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/* 131 */           } else if (paramArrayOfString[0].equals("send")) {
/* 132 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*     */           } else {
/* 134 */             sendUsage(paramCommandSender);
/*     */           }
/* 136 */         } else if (paramArrayOfString.length == 2) {
/* 137 */           if (paramArrayOfString[0].equals("check")) {
/* 138 */             localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/* 139 */             if (localPlayer != null) {
/* 140 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_CHECK_OTHERS.toString().replace("<player>", String.valueOf(getLives(localPlayer))));
/*     */             } else {
/* 142 */               paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */             }
/* 144 */           } else if (paramArrayOfString[0].equals("revive")) {
/* 145 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/* 146 */           } else if (paramArrayOfString[0].equals("send")) {
/* 147 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*     */           } else {
/* 149 */             sendUsage(paramCommandSender);
/*     */           }
/* 151 */         } else if (paramArrayOfString.length == 3) {
/* 152 */           if (paramArrayOfString[0].equals("send")) {
/* 153 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString()); } else { int n;
/* 154 */             if (paramArrayOfString[0].equals("set")) {
/* 155 */               localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/* 156 */               if (localPlayer != null) {
/* 157 */                 if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 158 */                   int i = Integer.parseInt(paramArrayOfString[2]);
/* 159 */                   setLives(paramCommandSender, localPlayer, i);
/*     */                 } else {
/* 161 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                 }
/*     */               }
/* 164 */               else if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 165 */                 OfflinePlayer localOfflinePlayer1 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/* 166 */                 n = Integer.parseInt(paramArrayOfString[2]);
/* 167 */                 setLives(paramCommandSender, localOfflinePlayer1, n);
/*     */               } else {
/* 169 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */               }
/*     */             }
/* 172 */             else if (paramArrayOfString[0].equals("add")) {
/* 173 */               localPlayer = Bukkit.getPlayer(paramArrayOfString[1]);
/* 174 */               if (localPlayer != null) {
/* 175 */                 if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 176 */                   int j = Integer.parseInt(paramArrayOfString[2]);
/* 177 */                   addLives(paramCommandSender, localPlayer, j);
/*     */                 } else {
/* 179 */                   paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */                 }
/* 181 */               } else if (NumberUtils.isInteger(paramArrayOfString[2])) {
/* 182 */                 OfflinePlayer localOfflinePlayer2 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/* 183 */                 n = Integer.parseInt(paramArrayOfString[2]);
/* 184 */                 addLives(paramCommandSender, localOfflinePlayer2, n);
/*     */               } else {
/* 186 */                 paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_INVALID_NUMBER.toString());
/*     */               }
/*     */             } else {
/* 189 */               sendUsage(paramCommandSender);
/*     */             }
/*     */           }
/* 192 */         } else { sendUsage(paramCommandSender);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 197 */     return true;
/*     */   }
/*     */   
/*     */   public int getLives(Player paramPlayer) {
/* 201 */     File localFile = new File(this.livesFolder, paramPlayer.getUniqueId().toString() + ".yml");
/* 202 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 203 */     int i = localYamlConfiguration.getInt("lives");
/* 204 */     return i;
/*     */   }
/*     */   
/*     */   public void revivePlayer(Player paramPlayer, OfflinePlayer paramOfflinePlayer) {
/* 208 */     File localFile1 = new File(this.livesFolder, paramPlayer.getUniqueId() + ".yml");
/* 209 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile1);
/* 210 */     int i = localYamlConfiguration.getInt("lives");
/* 211 */     if (i > 0) {
/* 212 */       File localFile2 = new File(this.deathbansFolder, paramOfflinePlayer.getUniqueId().toString() + ".yml");
/* 213 */       if (localFile2.exists()) {
/* 214 */         localFile2.delete();
/* 215 */         localYamlConfiguration.set("lives", Integer.valueOf(i - 1));
/*     */         try {
/* 217 */           localYamlConfiguration.save(localFile1);
/*     */         } catch (IOException localIOException) {
/* 219 */           localIOException.printStackTrace();
/*     */         }
/* 221 */         paramPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_SUCCESSFULLY_REVIVED_PLAYER.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */       } else {
/* 223 */         paramPlayer.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_NOT_DEATHBANNED.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */       }
/*     */     } else {
/* 226 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_ZERO_LIVES.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendLives(Player paramPlayer1, Player paramPlayer2, int paramInt) {
/* 231 */     File localFile1 = new File(this.livesFolder, paramPlayer1.getUniqueId() + ".yml");
/* 232 */     YamlConfiguration localYamlConfiguration1 = YamlConfiguration.loadConfiguration(localFile1);
/* 233 */     int i = localYamlConfiguration1.getInt("lives");
/* 234 */     if (i >= Math.abs(paramInt)) {
/* 235 */       File localFile2 = new File(this.livesFolder, paramPlayer2.getUniqueId() + ".yml");
/* 236 */       if (localFile2.exists()) {
/* 237 */         localYamlConfiguration1.set("lives", Integer.valueOf(i - Math.abs(paramInt)));
/* 238 */         YamlConfiguration localYamlConfiguration2 = YamlConfiguration.loadConfiguration(localFile2);
/* 239 */         localYamlConfiguration2.set("lives", Integer.valueOf(localYamlConfiguration2.getInt("lives") + Math.abs(paramInt)));
/* 240 */         paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_SUCCESSFULLY_SENT_LIVES.toString().replace("<amount>", String.valueOf(Math.abs(paramInt))).replace("<player>", paramPlayer2.getName()));
/*     */         try {
/* 242 */           localYamlConfiguration1.save(localFile1);
/* 243 */           localYamlConfiguration2.save(localFile2);
/*     */         } catch (IOException localIOException) {
/* 245 */           localIOException.printStackTrace();
/*     */         }
/*     */       } else {
/* 248 */         paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", paramPlayer2.getName()));
/*     */       }
/*     */     } else {
/* 251 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_NOT_ENOUGH_LIVES.toString().replace("<amount>", String.valueOf(Math.abs(paramInt))));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLives(CommandSender paramCommandSender, Player paramPlayer, int paramInt) {
/* 256 */     File localFile = new File(this.livesFolder, paramPlayer.getUniqueId().toString() + ".yml");
/* 257 */     if (localFile.exists()) {
/* 258 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 259 */       localYamlConfiguration.set("lives", Integer.valueOf(paramInt));
/* 260 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_SET.toString().replace("<player>", paramPlayer.getName()).replace("<amount>", String.valueOf(paramInt)));
/* 261 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_SET_RECEIVED.toString().replace("<amount>", String.valueOf(paramInt)).replace("<player>", paramCommandSender.getName()));
/*     */       try {
/* 263 */         localYamlConfiguration.save(localFile);
/*     */       } catch (IOException localIOException) {
/* 265 */         localIOException.printStackTrace();
/*     */       }
/*     */     } else {
/* 268 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", paramPlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLives(CommandSender paramCommandSender, OfflinePlayer paramOfflinePlayer, int paramInt)
/*     */   {
/* 274 */     File localFile = new File(this.livesFolder, paramOfflinePlayer.getUniqueId() + ".yml");
/* 275 */     if (localFile.exists()) {
/* 276 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 277 */       localYamlConfiguration.set("lives", Integer.valueOf(paramInt));
/* 278 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_SET.toString().replace("<player>", paramOfflinePlayer.getName()).replace("<amount>", String.valueOf(paramInt)));
/*     */       try {
/* 280 */         localYamlConfiguration.save(localFile);
/*     */       } catch (IOException localIOException) {
/* 282 */         localIOException.printStackTrace();
/*     */       }
/*     */     } else {
/* 285 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void addLives(CommandSender paramCommandSender, Player paramPlayer, int paramInt) {
/* 290 */     File localFile = new File(this.livesFolder, paramPlayer.getUniqueId().toString() + ".yml");
/* 291 */     if (localFile.exists()) {
/* 292 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 293 */       localYamlConfiguration.set("lives", Integer.valueOf(localYamlConfiguration.getInt("lives") + paramInt));
/* 294 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_ADDED.toString().replace("<amount>", String.valueOf(paramInt)).replace("<player>", paramPlayer.getName()));
/* 295 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_ADD_RECEIVED.toString().replace("<amount>", String.valueOf(paramInt)).replace("<player>", paramCommandSender.getName()));
/*     */       try {
/* 297 */         localYamlConfiguration.save(localFile);
/*     */       } catch (IOException localIOException) {
/* 299 */         localIOException.printStackTrace();
/*     */       }
/*     */     } else {
/* 302 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", paramPlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void addLives(CommandSender paramCommandSender, OfflinePlayer paramOfflinePlayer, int paramInt) {
/* 307 */     File localFile = new File(this.livesFolder, paramOfflinePlayer.getUniqueId() + ".yml");
/* 308 */     if (localFile.exists()) {
/* 309 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 310 */       localYamlConfiguration.set("lives", Integer.valueOf(localYamlConfiguration.getInt("lives") + paramInt));
/* 311 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_LIVES_ADDED.toString().replace("<amount>", String.valueOf(paramInt)).replace("<player>", paramOfflinePlayer.getName()));
/*     */       try {
/* 313 */         localYamlConfiguration.save(localFile);
/*     */       } catch (IOException localIOException) {
/* 315 */         localIOException.printStackTrace();
/*     */       }
/*     */     } else {
/* 318 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.LIVES_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendUsage(CommandSender paramCommandSender) {
/* 323 */     for (String str : Notorious.getInstance().getConfigHandler().getLivesUsageMessage()) {
/* 324 */       paramCommandSender.sendMessage(str);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\deathban\commands\LivesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */