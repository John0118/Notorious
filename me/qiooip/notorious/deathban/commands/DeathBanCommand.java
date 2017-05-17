/*     */ package me.qiooip.notorious.deathban.commands;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.InventoryUtils;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.apache.commons.lang.time.DurationFormatUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType.SlotType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class DeathBanCommand implements org.bukkit.command.CommandExecutor, org.bukkit.event.Listener
/*     */ {
/*  32 */   private File deathbanFolder = new File(Notorious.getInstance().getDataFolder(), "deathban");
/*  33 */   private File deathbansFolder = new File(this.deathbanFolder, "deathbans");
/*  34 */   private File inventoriesFolder = new File(this.deathbanFolder, "inventories");
/*     */   
/*     */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*     */   {
/*  38 */     if (paramCommand.getName().equalsIgnoreCase("deathban")) { Object localObject1;
/*  39 */       if ((paramCommandSender instanceof Player)) {
/*  40 */         localObject1 = (Player)paramCommandSender;
/*  41 */         if ((((Player)localObject1).hasPermission("notorious.deathban.check")) || (((Player)localObject1).hasPermission("notorious.deathban.revive")) || (((Player)localObject1).hasPermission("notorious.deathban.rollback"))) {
/*  42 */           if (paramArrayOfString.length == 0) {
/*  43 */             sendUsage((CommandSender)localObject1);
/*  44 */           } else if (paramArrayOfString.length == 1) {
/*  45 */             if (paramArrayOfString[0].equals("check")) {
/*  46 */               if (((Player)localObject1).hasPermission("notorious.deathban.check")) {
/*  47 */                 ((Player)localObject1).sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_COMMAND_CHECK_USAGE.toString());
/*     */               } else {
/*  49 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*  51 */             } else if (paramArrayOfString[0].equals("revive")) {
/*  52 */               if (((Player)localObject1).hasPermission("notorious.deathban.revive")) {
/*  53 */                 ((Player)localObject1).sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_COMMAND_REVIVE_USAGE.toString());
/*     */               } else {
/*  55 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*  57 */             } else if (paramArrayOfString[0].equals("rollback")) {
/*  58 */               if (((Player)localObject1).hasPermission("notorious.deathban.rollback")) {
/*  59 */                 ((Player)localObject1).sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_COMMAND_INVENTORY_ROLLBACK_USAGE.toString());
/*     */               } else {
/*  61 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*     */             } else {
/*  64 */               sendUsage((CommandSender)localObject1);
/*     */             }
/*  66 */           } else if (paramArrayOfString.length == 2) { Object localObject2;
/*  67 */             if (paramArrayOfString[0].equals("check")) {
/*  68 */               if (((Player)localObject1).hasPermission("notorious.deathban.check")) {
/*  69 */                 localObject2 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/*  70 */                 checkPlayer((CommandSender)localObject1, (OfflinePlayer)localObject2);
/*     */               } else {
/*  72 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*  74 */             } else if (paramArrayOfString[0].equals("revive")) {
/*  75 */               if (((Player)localObject1).hasPermission("notorious.deathban.revive")) {
/*  76 */                 localObject2 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/*  77 */                 revivePlayer((CommandSender)localObject1, (OfflinePlayer)localObject2);
/*     */               } else {
/*  79 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*  81 */             } else if (paramArrayOfString[0].equals("rollback")) {
/*  82 */               if (((Player)localObject1).hasPermission("notorious.deathban.rollback")) {
/*  83 */                 localObject2 = Bukkit.getPlayer(paramArrayOfString[1]);
/*  84 */                 if (localObject2 != null) {
/*  85 */                   checkPlayerInv((Player)localObject1, (Player)localObject2);
/*     */                 } else {
/*  87 */                   ((Player)localObject1).sendMessage(Language.COMMANDS_PLAYER_NOT_ONLINE.toString().replace("<player>", paramArrayOfString[1]));
/*     */                 }
/*     */               } else {
/*  90 */                 ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */               }
/*     */             } else {
/*  93 */               sendUsage((CommandSender)localObject1);
/*     */             }
/*     */           } else {
/*  96 */             sendUsage((CommandSender)localObject1);
/*     */           }
/*     */         } else {
/*  99 */           ((Player)localObject1).sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*     */         }
/* 101 */       } else if ((paramCommandSender instanceof ConsoleCommandSender)) {
/* 102 */         if (paramArrayOfString.length == 0) {
/* 103 */           sendUsage(paramCommandSender);
/* 104 */         } else if (paramArrayOfString.length == 1) {
/* 105 */           if (paramArrayOfString[0].equals("check")) {
/* 106 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_COMMAND_CHECK_USAGE.toString());
/* 107 */           } else if (paramArrayOfString[0].equals("revive")) {
/* 108 */             paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_COMMAND_REVIVE_USAGE.toString());
/*     */           } else {
/* 110 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*     */           }
/* 112 */         } else if (paramArrayOfString.length == 2) {
/* 113 */           if (paramArrayOfString[0].equals("revive")) {
/* 114 */             localObject1 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/* 115 */             revivePlayer(paramCommandSender, (OfflinePlayer)localObject1);
/* 116 */           } else if (paramArrayOfString[0].equals("check")) {
/* 117 */             localObject1 = Bukkit.getOfflinePlayer(paramArrayOfString[1]);
/* 118 */             checkPlayer(paramCommandSender, (OfflinePlayer)localObject1);
/*     */           } else {
/* 120 */             paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*     */           }
/*     */         } else {
/* 123 */           sendUsage(paramCommandSender);
/*     */         }
/*     */       }
/*     */     }
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public void checkPlayer(CommandSender paramCommandSender, OfflinePlayer paramOfflinePlayer) {
/* 131 */     File localFile = new File(this.deathbansFolder, paramOfflinePlayer.getUniqueId().toString() + ".yml");
/* 132 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 133 */     long l = localYamlConfiguration.getLong("ban_until");
/* 134 */     if (localFile.exists()) {
/* 135 */       String str1 = "";
/* 136 */       if (l - System.currentTimeMillis() <= 0L) {
/* 137 */         str1 = String.valueOf("Unbanned");
/*     */       } else {
/* 139 */         str1 = DurationFormatUtils.formatDurationWords(l - System.currentTimeMillis(), true, true);
/*     */       }
/* 141 */       for (String str2 : Notorious.getInstance().getConfigHandler().getDeathbanCheckMessage()) {
/* 142 */         paramCommandSender.sendMessage(str2.replace("<player>", paramOfflinePlayer.getName()).replace("<duration>", 
/* 143 */           str1).replace("<reason>", localYamlConfiguration.getString("death_message")).replace("<location>", 
/* 144 */           localYamlConfiguration.getString("coords")));
/*     */       }
/*     */     } else {
/* 147 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_NOT_DEATHBANNED.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void revivePlayer(CommandSender paramCommandSender, OfflinePlayer paramOfflinePlayer) {
/* 152 */     File localFile = new File(this.deathbansFolder, paramOfflinePlayer.getUniqueId().toString() + ".yml");
/* 153 */     if (localFile.exists()) {
/* 154 */       localFile.delete();
/* 155 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_SUCCESSFULLY_REVIVED_PLAYER.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */     } else {
/* 157 */       paramCommandSender.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_NOT_DEATHBANNED.toString().replace("<player>", paramOfflinePlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void rollbackPlayerInv(Player paramPlayer1, Player paramPlayer2) {
/* 162 */     File localFile = new File(this.inventoriesFolder, paramPlayer2.getUniqueId().toString() + ".yml");
/* 163 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 164 */     boolean bool = localYamlConfiguration.getBoolean("used");
/* 165 */     if (localFile.exists()) {
/* 166 */       if (!bool) {
/*     */         try {
/* 168 */           ItemStack[] arrayOfItemStack1 = InventoryUtils.itemStackArrayFromBase64(localYamlConfiguration.getString("inventory"));
/* 169 */           ItemStack[] arrayOfItemStack2 = InventoryUtils.itemStackArrayFromBase64(localYamlConfiguration.getString("armor"));
/* 170 */           paramPlayer2.getInventory().setContents(arrayOfItemStack1);
/* 171 */           paramPlayer2.getInventory().setArmorContents(arrayOfItemStack2);
/* 172 */           localYamlConfiguration.set("used", Boolean.valueOf(true));
/* 173 */           localYamlConfiguration.save(localFile);
/*     */         } catch (IOException localIOException) {
/* 175 */           System.out.println(localIOException.getMessage());
/*     */         }
/* 177 */         paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_SUCCESSFULLY_ROLLBACKED_INVENTORY.toString().replace("<player>", paramPlayer2.getName()));
/* 178 */         paramPlayer2.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_SUCCESSFULLY_ROLLBACKED_INVENTORY_PLAYER.toString().replace("<player>", paramPlayer1.getName()));
/*     */       } else {
/* 180 */         paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_INVENTORY_ALREADY_ROLLBACKED.toString().replace("<player>", paramPlayer2.getName()));
/*     */       }
/*     */     } else {
/* 183 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_HAS_NOT_DIED_YET.toString().replace("<player>", paramPlayer2.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkPlayerInv(Player paramPlayer1, Player paramPlayer2) {
/* 188 */     File localFile = new File(this.inventoriesFolder, paramPlayer2.getUniqueId().toString() + ".yml");
/* 189 */     YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 190 */     boolean bool = localYamlConfiguration.getBoolean("used");
/* 191 */     Inventory localInventory = Bukkit.createInventory(null, 54, paramPlayer2.getName() + "'s Inventory");
/* 192 */     if (localFile.exists()) {
/* 193 */       if (!bool) {
/*     */         try {
/* 195 */           ItemStack[] arrayOfItemStack1 = InventoryUtils.itemStackArrayFromBase64(localYamlConfiguration.getString("inventory"));
/* 196 */           ItemStack[] arrayOfItemStack2 = InventoryUtils.itemStackArrayFromBase64(localYamlConfiguration.getString("armor"));
/*     */           
/* 198 */           localInventory.setContents(arrayOfItemStack1);
/*     */           
/* 200 */           localInventory.setItem(45, arrayOfItemStack2[0]);
/* 201 */           localInventory.setItem(46, arrayOfItemStack2[1]);
/* 202 */           localInventory.setItem(47, arrayOfItemStack2[2]);
/* 203 */           localInventory.setItem(48, arrayOfItemStack2[3]);
/*     */           
/* 205 */           localInventory.setItem(36, createGlass(ChatColor.RED + "Inventory Preview"));
/* 206 */           localInventory.setItem(37, createGlass(ChatColor.RED + "Inventory Preview"));
/* 207 */           localInventory.setItem(38, createGlass(ChatColor.RED + "Inventory Preview"));
/* 208 */           localInventory.setItem(39, createGlass(ChatColor.RED + "Inventory Preview"));
/* 209 */           localInventory.setItem(40, createGlass(ChatColor.RED + "Inventory Preview"));
/* 210 */           localInventory.setItem(41, createGlass(ChatColor.RED + "Inventory Preview"));
/* 211 */           localInventory.setItem(42, createGlass(ChatColor.RED + "Inventory Preview"));
/* 212 */           localInventory.setItem(43, createGlass(ChatColor.RED + "Inventory Preview"));
/* 213 */           localInventory.setItem(44, createGlass(ChatColor.RED + "Inventory Preview"));
/* 214 */           localInventory.setItem(49, createGlass(ChatColor.RED + "Inventory Preview"));
/*     */           
/* 216 */           localInventory.setItem(52, createWool(ChatColor.RED + "Close Preview", 14));
/* 217 */           localInventory.setItem(53, createWool(ChatColor.GREEN + "Rollback Inventory (" + paramPlayer2.getName() + ")", 5));
/*     */           
/* 219 */           paramPlayer1.openInventory(localInventory);
/*     */         } catch (IOException localIOException) {
/* 221 */           System.out.println(localIOException.getMessage());
/*     */         }
/*     */       } else {
/* 224 */         paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_INVENTORY_ALREADY_ROLLBACKED.toString().replace("<player>", paramPlayer2.getName()));
/*     */       }
/*     */     } else {
/* 227 */       paramPlayer1.sendMessage(Language.PREFIX.toString() + Language.DEATHBAN_PLAYER_HAS_NOT_DIED_YET.toString().replace("<player>", paramPlayer2.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack createWool(String paramString, int paramInt) {
/* 232 */     ItemStack localItemStack = new ItemStack(Material.WOOL, 1, (short)paramInt);
/* 233 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 234 */     localItemMeta.setDisplayName(paramString);
/* 235 */     localItemStack.setItemMeta(localItemMeta);
/* 236 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public ItemStack createGlass(String paramString) {
/* 240 */     ItemStack localItemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
/* 241 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 242 */     localItemMeta.setDisplayName(paramString);
/* 243 */     localItemStack.setItemMeta(localItemMeta);
/*     */     
/* 245 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public void sendUsage(CommandSender paramCommandSender) {
/* 249 */     for (String str : Notorious.getInstance().getConfigHandler().getDeathbanUsageMessage()) {
/* 250 */       paramCommandSender.sendMessage(str);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 256 */     final Player localPlayer1 = (Player)paramInventoryClickEvent.getWhoClicked();
/* 257 */     ItemStack localItemStack = paramInventoryClickEvent.getCurrentItem();
/* 258 */     Inventory localInventory = paramInventoryClickEvent.getInventory();
/*     */     
/* 260 */     if (!localInventory.getName().endsWith("'s Inventory")) return;
/* 261 */     if (paramInventoryClickEvent.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) return;
/* 262 */     if (localItemStack.getType().equals(Material.AIR)) { return;
/*     */     }
/* 264 */     if (localInventory.getName().endsWith("'s Inventory")) {
/* 265 */       paramInventoryClickEvent.setCancelled(true);
/* 266 */       if (localItemStack.getItemMeta().getDisplayName().contains("Close"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 271 */         new BukkitRunnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 269 */             localPlayer1.closeInventory();
/*     */           }
/* 271 */         }.runTaskLater(Notorious.getInstance(), 1L);
/* 272 */       } else if (localItemStack.getItemMeta().getDisplayName().contains("Rollback")) {
/* 273 */         String[] arrayOfString = paramInventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().split("\\(");
/* 274 */         Player localPlayer2 = Bukkit.getPlayer(arrayOfString[1].replaceAll("\\)", ""));
/* 275 */         if (localPlayer2 != null) {
/* 276 */           rollbackPlayerInv(localPlayer1, localPlayer2);
/*     */         } else {
/* 278 */           localPlayer1.sendMessage("ďż˝cPlayer " + arrayOfString[1].replaceAll("\\)", "") + " is not online!");
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 284 */         new BukkitRunnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 282 */             localPlayer1.closeInventory();
/*     */           }
/* 284 */         }.runTaskLater(Notorious.getInstance(), 1L);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\deathban\commands\DeathBanCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */