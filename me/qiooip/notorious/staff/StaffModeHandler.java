/*     */ package me.qiooip.notorious.staff;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.handlers.CPSCountHandler;
/*     */ import me.qiooip.notorious.handlers.FreezeHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.vanish.VanishHandler;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType.SlotType;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class StaffModeHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, StaffPlayerData> staffModePlayers;
/*     */   private ArrayList<StaffModeItem> staffModeItems;
/*     */   
/*     */   public StaffModeHandler(Notorious paramNotorious)
/*     */   {
/*  45 */     super(paramNotorious);
/*  46 */     this.staffModePlayers = new HashMap();
/*  47 */     this.staffModeItems = new ArrayList();
/*     */     
/*  49 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*  50 */     loadStaffModeItems();
/*     */   }
/*     */   
/*     */   public void disable() {
/*     */     Player[] arrayOfPlayer;
/*  55 */     int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  56 */       disableStaffMode(localPlayer);
/*     */     }
/*     */     
/*  59 */     this.staffModePlayers.clear();
/*  60 */     this.staffModeItems.clear();
/*     */   }
/*     */   
/*     */   public void loadStaffModeItems()
/*     */   {
/*  65 */     if (this.staffModeItems != null) { this.staffModeItems.clear();
/*     */     }
/*  67 */     ConfigurationSection localConfigurationSection = getInstance().getConfigFile().getConfiguration().getConfigurationSection("staff-mode-items");
/*  68 */     for (String str : localConfigurationSection.getKeys(false)) {
/*  69 */       String[] arrayOfString = localConfigurationSection.getString(str + ".material-id").split(":");
/*  70 */       StaffModeItem localStaffModeItem = new StaffModeItem();
/*     */       
/*  72 */       int i = Integer.valueOf(arrayOfString[1]).intValue();
/*  73 */       ItemStack localItemStack = new ItemStack(Material.getMaterial(Integer.valueOf(arrayOfString[0]).intValue()));
/*  74 */       ItemMeta localItemMeta = localItemStack.getItemMeta();
/*  75 */       localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', localConfigurationSection.getString(str + ".name")));
/*  76 */       localItemStack.setItemMeta(localItemMeta);
/*  77 */       localItemStack.setDurability((short)i);
/*     */       
/*  79 */       localStaffModeItem.setItem(localItemStack);
/*  80 */       localStaffModeItem.setSlot(localConfigurationSection.getInt(str + ".slot") - 1);
/*  81 */       localStaffModeItem.setAction(localConfigurationSection.getString(str + ".action"));
/*     */       
/*  83 */       this.staffModeItems.add(localStaffModeItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public void enableStaffMode(Player paramPlayer) {
/*  88 */     StaffPlayerData localStaffPlayerData = new StaffPlayerData();
/*  89 */     localStaffPlayerData.setContents(paramPlayer.getInventory().getContents());
/*  90 */     localStaffPlayerData.setArmor(paramPlayer.getInventory().getArmorContents());
/*  91 */     localStaffPlayerData.setGameMode(paramPlayer.getGameMode());
/*     */     
/*  93 */     paramPlayer.getInventory().clear();
/*  94 */     paramPlayer.getInventory().setArmorContents(new ItemStack[4]);
/*     */     
/*  96 */     paramPlayer.setGameMode(org.bukkit.GameMode.CREATIVE);
/*     */     
/*  98 */     for (StaffModeItem localStaffModeItem : this.staffModeItems) {
/*  99 */       paramPlayer.getInventory().setItem(localStaffModeItem.getSlot(), localStaffModeItem.getItem());
/*     */     }
/*     */     
/* 102 */     this.staffModePlayers.put(paramPlayer.getUniqueId(), localStaffPlayerData);
/*     */   }
/*     */   
/*     */   public void disableStaffMode(Player paramPlayer) {
/* 106 */     if (this.staffModePlayers.containsKey(paramPlayer.getUniqueId())) {
/* 107 */       StaffPlayerData localStaffPlayerData = (StaffPlayerData)this.staffModePlayers.get(paramPlayer.getUniqueId());
/* 108 */       paramPlayer.getInventory().setContents(localStaffPlayerData.getContents());
/* 109 */       paramPlayer.getInventory().setArmorContents(localStaffPlayerData.getArmor());
/* 110 */       paramPlayer.setGameMode(localStaffPlayerData.getGameMode());
/*     */       
/* 112 */       this.staffModePlayers.remove(paramPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isInStaffMode(Player paramPlayer) {
/* 117 */     return this.staffModePlayers.containsKey(paramPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   public HashMap<UUID, StaffPlayerData> getStaffModePlayers() {
/* 121 */     return this.staffModePlayers;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
/* 126 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/*     */     
/* 128 */     if (this.staffModePlayers.containsKey(localPlayer.getUniqueId())) {
/* 129 */       paramBlockBreakEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/* 135 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/*     */     
/* 137 */     if (this.staffModePlayers.containsKey(localPlayer.getUniqueId())) {
/* 138 */       paramBlockPlaceEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 144 */     final Player localPlayer = (Player)paramInventoryClickEvent.getWhoClicked();
/* 145 */     ItemStack localItemStack = paramInventoryClickEvent.getCurrentItem();
/* 146 */     InventoryAction localInventoryAction = paramInventoryClickEvent.getAction();
/*     */     
/* 148 */     if (this.staffModePlayers.containsKey(localPlayer.getUniqueId())) {
/* 149 */       if (paramInventoryClickEvent.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) {
/* 150 */         paramInventoryClickEvent.setCancelled(true);
/* 151 */         return;
/*     */       }
/* 153 */       if (((localItemStack == null) || (localItemStack.getType().equals(Material.AIR))) && (
/* 154 */         (localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.SWAP_WITH_CURSOR)))) {
/* 155 */         paramInventoryClickEvent.setCancelled(true);
/* 156 */         return;
/*     */       }
/*     */       
/* 159 */       paramInventoryClickEvent.setCancelled(true);
/* 160 */       if ((localItemStack.hasItemMeta()) && 
/* 161 */         (localItemStack.getItemMeta().hasDisplayName())) {
/* 162 */         if (localItemStack.getItemMeta().getDisplayName().equals(ChatColor.RED + "Close Preview"))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */           new org.bukkit.scheduler.BukkitRunnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 166 */               localPlayer.closeInventory();
/*     */             }
/* 168 */           }.runTaskLater(Notorious.getInstance(), 1L);
/*     */         }
/* 170 */         for (StaffModeItem localStaffModeItem : this.staffModeItems) {
/* 171 */           if (localItemStack.getItemMeta().getDisplayName().equals(localStaffModeItem.getItem().getItemMeta().getDisplayName())) {
/* 172 */             if ((localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.SWAP_WITH_CURSOR))) {
/* 173 */               paramInventoryClickEvent.setCancelled(true);
/*     */             }
/* 175 */             paramInventoryClickEvent.setCancelled(true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/* 185 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 186 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 187 */     ItemStack localItemStack = paramPlayerInteractEvent.getItem();
/*     */     
/* 189 */     if ((localItemStack == null) || (localItemStack.getType().equals(Material.AIR))) return;
/* 190 */     if (!localItemStack.hasItemMeta()) return;
/* 191 */     if (!localItemStack.getItemMeta().hasDisplayName()) { return;
/*     */     }
/* 193 */     if ((this.staffModePlayers.containsKey(localPlayer.getUniqueId())) && (
/* 194 */       (localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK)))) {
/* 195 */       label267: for (StaffModeItem localStaffModeItem : this.staffModeItems) {
/* 196 */         if (localStaffModeItem.getItem().getItemMeta().getDisplayName().equals(localItemStack.getItemMeta().getDisplayName())) { String str;
/* 197 */           switch ((str = localStaffModeItem.getAction()).hashCode()) {case -1770434949:  if (str.equals("VANISH")) break; break; case -1509864210:  if (!str.equals("RANDOMTELEPORT")) {
/*     */               break label267;
/* 199 */               if (Notorious.getInstance().getVanishHandler().getVanishedPlayers().contains(localPlayer.getUniqueId())) {
/* 200 */                 Notorious.getInstance().getVanishHandler().unvanishPlayer(localPlayer);
/* 201 */                 continue; }
/* 202 */               Notorious.getInstance().getVanishHandler().vanishPlayer(localPlayer);
/*     */               
/* 204 */               continue;
/*     */             } else {
/* 206 */               randomTeleport(localPlayer); }
/*     */             break; } return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerInteractEntity(PlayerInteractEntityEvent paramPlayerInteractEntityEvent)
/*     */   {
/* 217 */     Player localPlayer1 = paramPlayerInteractEntityEvent.getPlayer();
/* 218 */     org.bukkit.entity.Entity localEntity = paramPlayerInteractEntityEvent.getRightClicked();
/* 219 */     ItemStack localItemStack = localPlayer1.getItemInHand();
/*     */     
/* 221 */     if ((localItemStack == null) || (localItemStack.getType().equals(Material.AIR))) return;
/* 222 */     if (!localItemStack.hasItemMeta()) return;
/* 223 */     if (!localItemStack.getItemMeta().hasDisplayName()) { return;
/*     */     }
/* 225 */     if ((this.staffModePlayers.containsKey(localPlayer1.getUniqueId())) && 
/* 226 */       ((localEntity instanceof Player))) {
/* 227 */       Player localPlayer2 = (Player)localEntity;
/* 228 */       label608: for (StaffModeItem localStaffModeItem : this.staffModeItems) {
/* 229 */         if (localStaffModeItem.getItem().getItemMeta().getDisplayName().equals(localItemStack.getItemMeta().getDisplayName())) { String str;
/* 230 */           switch ((str = localStaffModeItem.getAction()).hashCode()) {case 66950:  if (str.equals("CPS")) {} break; case 1525159171:  if (str.equals("INVINSPECT")) break; break; case 2081894039:  if (!str.equals("FREEZE")) {
/*     */               break label608;
/* 232 */               localPlayer1.openInventory(createInventory(localPlayer1, localPlayer2));
/* 233 */               localPlayer1.sendMessage(Language.PREFIX.toString() + Language.INVENTORY_INSPECT_MESSAGE.toString().replace("<player>", localPlayer2.getName()));
/* 234 */               continue;
/*     */             } else {
/* 236 */               if (Notorious.getInstance().getFreezeHandler().getFreezedPlayers().contains(localPlayer2.getUniqueId())) {
/* 237 */                 Notorious.getInstance().getFreezeHandler().removeFreeze(localPlayer1, localPlayer2);
/* 238 */                 continue; }
/* 239 */               Notorious.getInstance().getFreezeHandler().addFreeze(localPlayer1, localPlayer2);
/*     */               
/* 241 */               continue;
/*     */               
/* 243 */               if (Notorious.getInstance().getCPSCountHandler().getCpsCounters().containsKey(localPlayer2.getUniqueId())) {
/* 244 */                 if (((Integer)Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer2.getUniqueId())).intValue() < 5) {
/* 245 */                   localPlayer1.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_MESSAGE_FORMAT.toString().replace("<player>", localPlayer2.getName()).replace("<cps>", 
/* 246 */                     String.valueOf(Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer2.getUniqueId()))));
/*     */                 } else {
/* 248 */                   localPlayer1.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_MESSAGE_FORMAT.toString().replace("<player>", localPlayer2.getName()).replace("<cps>", 
/* 249 */                     String.valueOf(((Integer)Notorious.getInstance().getCPSCountHandler().getCpsCounters().get(localPlayer2.getUniqueId())).intValue() / 5)));
/*     */                 }
/*     */               } else
/* 252 */                 localPlayer1.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_COMMAND_PLAYER_NOT_IN_DATABASE.toString().replace("<player>", localPlayer2.getName())); }
/*     */             break; }
/* 254 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerDropItem(PlayerDropItemEvent paramPlayerDropItemEvent)
/*     */   {
/* 264 */     Player localPlayer = paramPlayerDropItemEvent.getPlayer();
/*     */     
/* 266 */     if (this.staffModePlayers.containsKey(localPlayer.getUniqueId())) {
/* 267 */       paramPlayerDropItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerPickupItem(PlayerPickupItemEvent paramPlayerPickupItemEvent) {
/* 273 */     Player localPlayer = paramPlayerPickupItemEvent.getPlayer();
/*     */     
/* 275 */     if (this.staffModePlayers.containsKey(localPlayer.getUniqueId())) {
/* 276 */       paramPlayerPickupItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/* 282 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*     */     
/* 284 */     disableStaffMode(localPlayer);
/*     */   }
/*     */   
/*     */   public void randomTeleport(Player paramPlayer)
/*     */   {
/* 289 */     ArrayList localArrayList = new ArrayList();
/* 290 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; Player localPlayer; for (int i = 0; i < j; i++) { localPlayer = arrayOfPlayer[i];
/* 291 */       if (localPlayer != paramPlayer) {
/* 292 */         localArrayList.add(localPlayer);
/*     */       }
/*     */     }
/*     */     
/* 296 */     if (localArrayList.size() != 0) {
/* 297 */       localPlayer = (Player)localArrayList.get(new Random().nextInt(localArrayList.size()));
/* 298 */       paramPlayer.teleport(localPlayer);
/* 299 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.RANDOM_TELEPORT_MESSAGE.toString().replace("<player>", localPlayer.getName()));
/*     */     }
/*     */   }
/*     */   
/*     */   public Inventory createInventory(Player paramPlayer1, Player paramPlayer2) {
/* 304 */     Inventory localInventory = Bukkit.createInventory(null, 54, "Inventory preview");
/*     */     
/* 306 */     ItemStack[] arrayOfItemStack1 = paramPlayer2.getInventory().getContents();
/* 307 */     ItemStack[] arrayOfItemStack2 = paramPlayer2.getInventory().getArmorContents();
/*     */     
/* 309 */     localInventory.setContents(arrayOfItemStack1);
/*     */     
/* 311 */     localInventory.setItem(45, arrayOfItemStack2[0]);
/* 312 */     localInventory.setItem(46, arrayOfItemStack2[1]);
/* 313 */     localInventory.setItem(47, arrayOfItemStack2[2]);
/* 314 */     localInventory.setItem(48, arrayOfItemStack2[3]);
/*     */     
/* 316 */     localInventory.setItem(36, createGlass(ChatColor.RED + "Inventory Preview"));
/* 317 */     localInventory.setItem(37, createGlass(ChatColor.RED + "Inventory Preview"));
/* 318 */     localInventory.setItem(38, createGlass(ChatColor.RED + "Inventory Preview"));
/* 319 */     localInventory.setItem(39, createGlass(ChatColor.RED + "Inventory Preview"));
/* 320 */     localInventory.setItem(40, createGlass(ChatColor.RED + "Inventory Preview"));
/* 321 */     localInventory.setItem(41, createGlass(ChatColor.RED + "Inventory Preview"));
/* 322 */     localInventory.setItem(42, createGlass(ChatColor.RED + "Inventory Preview"));
/* 323 */     localInventory.setItem(43, createGlass(ChatColor.RED + "Inventory Preview"));
/* 324 */     localInventory.setItem(44, createGlass(ChatColor.RED + "Inventory Preview"));
/* 325 */     localInventory.setItem(49, createGlass(ChatColor.RED + "Inventory Preview"));
/*     */     
/* 327 */     localInventory.setItem(50, createItem(Material.SPECKLED_MELON, ChatColor.RED + "Health", (int)paramPlayer2.getHealth()));
/* 328 */     localInventory.setItem(51, createItem(Material.GRILLED_PORK, ChatColor.RED + "Hunger", paramPlayer2.getFoodLevel()));
/* 329 */     localInventory.setItem(52, createSkull(paramPlayer2, ChatColor.GREEN + paramPlayer2.getName()));
/* 330 */     localInventory.setItem(53, createWool(ChatColor.RED + "Close Preview", 14));
/*     */     
/* 332 */     return localInventory;
/*     */   }
/*     */   
/*     */   public ItemStack createItem(Material paramMaterial, String paramString, int paramInt) {
/* 336 */     ItemStack localItemStack = new ItemStack(paramMaterial, paramInt);
/* 337 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 338 */     localItemMeta.setDisplayName(paramString);
/* 339 */     localItemStack.setItemMeta(localItemMeta);
/*     */     
/* 341 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public ItemStack createGlass(String paramString) {
/* 345 */     ItemStack localItemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
/* 346 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 347 */     localItemMeta.setDisplayName(paramString);
/* 348 */     localItemStack.setItemMeta(localItemMeta);
/*     */     
/* 350 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public ItemStack createWool(String paramString, int paramInt) {
/* 354 */     ItemStack localItemStack = new ItemStack(Material.WOOL, 1, (short)paramInt);
/* 355 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/* 356 */     localItemMeta.setDisplayName(paramString);
/* 357 */     localItemStack.setItemMeta(localItemMeta);
/* 358 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public ItemStack createSkull(Player paramPlayer, String paramString) {
/* 362 */     ItemStack localItemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 363 */     SkullMeta localSkullMeta = (SkullMeta)localItemStack.getItemMeta();
/* 364 */     localSkullMeta.setDisplayName(paramString);
/* 365 */     localSkullMeta.setOwner(paramPlayer.getName());
/* 366 */     localItemStack.setItemMeta(localSkullMeta);
/*     */     
/* 368 */     return localItemStack;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\staff\StaffModeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */