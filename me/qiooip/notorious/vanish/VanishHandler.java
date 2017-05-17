/*     */ package me.qiooip.notorious.vanish;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Chest;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class VanishHandler extends Handler implements Listener
/*     */ {
/*     */   private ArrayList<UUID> vanishedPlayers;
/*     */   private ArrayList<UUID> silentView;
/*     */   
/*     */   public VanishHandler(Notorious paramNotorious)
/*     */   {
/*  41 */     super(paramNotorious);
/*  42 */     this.vanishedPlayers = new ArrayList();
/*  43 */     this.silentView = new ArrayList();
/*     */     
/*  45 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  49 */     this.vanishedPlayers.clear();
/*     */   }
/*     */   
/*     */   public boolean isVanished(Player paramPlayer) {
/*  53 */     return this.vanishedPlayers.contains(paramPlayer.getUniqueId());
/*     */   }
/*     */   
/*     */   public void vanishPlayer(Player paramPlayer)
/*     */   {
/*  58 */     this.vanishedPlayers.add(paramPlayer.getUniqueId());
/*  59 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; Player localPlayer; for (int i = 0; i < j; i++) { localPlayer = arrayOfPlayer[i];
/*  60 */       if (!localPlayer.hasPermission("notorious.vanish")) {
/*  61 */         localPlayer.hidePlayer(paramPlayer);
/*     */       }
/*     */     }
/*  64 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_ENABLED.toString());
/*  65 */     j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (i = 0; i < j; i++) { localPlayer = arrayOfPlayer[i];
/*  66 */       if ((localPlayer != paramPlayer) && (localPlayer.hasPermission("notorious.vanish"))) {
/*  67 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_ENABLED_STAFF_BROADCAST.toString().replace("<player>", paramPlayer.getName()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void vanishOnJoin(Player paramPlayer)
/*     */   {
/*  74 */     this.vanishedPlayers.add(paramPlayer.getUniqueId());
/*  75 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  76 */       if (!localPlayer.hasPermission("notorious.vanish")) {
/*  77 */         localPlayer.hidePlayer(paramPlayer);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void unvanishPlayer(Player paramPlayer)
/*     */   {
/*  84 */     this.vanishedPlayers.remove(paramPlayer.getUniqueId());
/*  85 */     Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; Player localPlayer; for (int i = 0; i < j; i++) { localPlayer = arrayOfPlayer[i];
/*  86 */       if (!localPlayer.canSee(paramPlayer)) {
/*  87 */         localPlayer.showPlayer(paramPlayer);
/*     */       }
/*     */     }
/*  90 */     paramPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_DISABLED.toString());
/*  91 */     j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (i = 0; i < j; i++) { localPlayer = arrayOfPlayer[i];
/*  92 */       if ((localPlayer != paramPlayer) && (localPlayer.hasPermission("notorious.vanish"))) {
/*  93 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_COMMAND_DISABLED_STAFF_BROADCAST.toString().replace("<player>", paramPlayer.getName()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<UUID> getVanishedPlayers() {
/*  99 */     return this.vanishedPlayers;
/*     */   }
/*     */   
/*     */   public ArrayList<UUID> getSilendViewers() {
/* 103 */     return this.silentView;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/* 108 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/* 109 */     OptionType localOptionType = OptionType.PLACE;
/*     */     
/* 111 */     if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 112 */       (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 113 */       paramBlockPlaceEvent.setCancelled(true);
/* 114 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_OPTIONS_PLACE_DENY.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent)
/*     */   {
/* 121 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 122 */     OptionType localOptionType = OptionType.BREAK;
/*     */     
/* 124 */     if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 125 */       (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 126 */       paramBlockBreakEvent.setCancelled(true);
/* 127 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_OPTIONS_BREAK_DENY.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerItemPickup(PlayerPickupItemEvent paramPlayerPickupItemEvent)
/*     */   {
/* 134 */     Player localPlayer = paramPlayerPickupItemEvent.getPlayer();
/* 135 */     OptionType localOptionType = OptionType.PICKUP;
/*     */     
/* 137 */     if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 138 */       (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 139 */       paramPlayerPickupItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAsyncPlayerChat(AsyncPlayerChatEvent paramAsyncPlayerChatEvent)
/*     */   {
/* 146 */     Player localPlayer = paramAsyncPlayerChatEvent.getPlayer();
/* 147 */     OptionType localOptionType = OptionType.CHAT;
/*     */     
/* 149 */     if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 150 */       (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 151 */       paramAsyncPlayerChatEvent.setCancelled(true);
/* 152 */       if (!Notorious.getInstance().getStaffChatHandler().getStaff().contains(localPlayer.getUniqueId())) {
/* 153 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_OPTIONS_CHAT_DENY.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/* 161 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 162 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 163 */     OptionType localOptionType1 = OptionType.CHEST;
/* 164 */     OptionType localOptionType2 = OptionType.INTERACT;
/*     */     
/* 166 */     if (this.vanishedPlayers.contains(localPlayer.getUniqueId())) { Block localBlock;
/* 167 */       if ((!localOptionType1.getPlayers().contains(localPlayer.getUniqueId())) && 
/* 168 */         (localAction.equals(Action.RIGHT_CLICK_BLOCK))) {
/* 169 */         localBlock = paramPlayerInteractEvent.getClickedBlock();
/* 170 */         if (((localBlock.getType().equals(Material.CHEST)) || (localBlock.getType().equals(Material.TRAPPED_CHEST))) && 
/* 171 */           (!localPlayer.isSneaking())) {
/* 172 */           paramPlayerInteractEvent.setCancelled(true);
/* 173 */           this.silentView.add(localPlayer.getUniqueId());
/* 174 */           Chest localChest = (Chest)localBlock.getState();
/* 175 */           Inventory localInventory = Bukkit.createInventory(null, localChest.getInventory().getSize());
/* 176 */           localInventory.setContents(localChest.getInventory().getContents());
/* 177 */           localPlayer.openInventory(localInventory);
/* 178 */           localPlayer.sendMessage(Language.VANISH_OPTIONS_CHEST_MESSAGE.toString());
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 183 */       if (!localOptionType2.getPlayers().contains(localPlayer.getUniqueId())) {
/* 184 */         if (localAction.equals(Action.PHYSICAL)) {
/* 185 */           paramPlayerInteractEvent.setCancelled(true);
/*     */         }
/* 187 */         if (localAction.equals(Action.RIGHT_CLICK_BLOCK)) {
/* 188 */           localBlock = paramPlayerInteractEvent.getClickedBlock();
/* 189 */           if ((localBlock.getType().equals(Material.LEVER)) || (localBlock.getType().equals(Material.WOOD_BUTTON)) || (localBlock.getType().equals(Material.STONE_BUTTON))) {
/* 190 */             paramPlayerInteractEvent.setCancelled(true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 199 */     Player localPlayer = (Player)paramInventoryClickEvent.getWhoClicked();
/* 200 */     InventoryAction localInventoryAction = paramInventoryClickEvent.getAction();
/*     */     
/* 202 */     if (this.silentView.contains(localPlayer.getUniqueId())) {
/* 203 */       if ((localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.SWAP_WITH_CURSOR))) {
/* 204 */         paramInventoryClickEvent.setCancelled(true);
/*     */       }
/* 206 */       paramInventoryClickEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClose(InventoryCloseEvent paramInventoryCloseEvent) {
/* 212 */     Player localPlayer = (Player)paramInventoryCloseEvent.getPlayer();
/* 213 */     if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 214 */       (this.silentView.contains(localPlayer.getUniqueId()))) {
/* 215 */       this.silentView.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamage(EntityDamageEvent paramEntityDamageEvent)
/*     */   {
/* 222 */     Entity localEntity = paramEntityDamageEvent.getEntity();
/* 223 */     OptionType localOptionType = OptionType.DAMAGE;
/*     */     
/* 225 */     if ((localEntity instanceof Player)) {
/* 226 */       Player localPlayer = (Player)localEntity;
/* 227 */       if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 228 */         (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 229 */         paramEntityDamageEvent.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDamageByEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent)
/*     */   {
/* 237 */     Entity localEntity1 = paramEntityDamageByEntityEvent.getEntity();
/* 238 */     Entity localEntity2 = paramEntityDamageByEntityEvent.getDamager();
/* 239 */     OptionType localOptionType = OptionType.DAMAGE;
/*     */     
/* 241 */     if (((localEntity1 instanceof Player)) && 
/* 242 */       ((localEntity2 instanceof Player))) {
/* 243 */       Player localPlayer = (Player)localEntity2;
/* 244 */       if ((this.vanishedPlayers.contains(localPlayer.getUniqueId())) && 
/* 245 */         (!localOptionType.getPlayers().contains(localPlayer.getUniqueId()))) {
/* 246 */         paramEntityDamageByEntityEvent.setCancelled(true);
/* 247 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_OPTIONS_DAMAGE_DENY.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent paramPlayerJoinEvent)
/*     */   {
/* 257 */     final Player localPlayer1 = paramPlayerJoinEvent.getPlayer();
/*     */     
/* 259 */     if (localPlayer1.hasPermission("notorious.vanish.onjoin"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 271 */       new org.bukkit.scheduler.BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 262 */           VanishHandler.this.vanishOnJoin(localPlayer1);
/*     */           
/* 264 */           localPlayer1.sendMessage(Language.PREFIX.toString() + Language.VANISH_ONJOIN_ENABLED.toString());
/* 265 */           Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 266 */             if ((localPlayer != localPlayer1) && (localPlayer.hasPermission("notorious.vanish"))) {
/* 267 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.VANISH_ONJOIN_ENABLED_STAFF_BROADCAST.toString().replace("<player>", localPlayer1.getName()));
/*     */             }
/*     */           }
/*     */         }
/* 271 */       }.runTaskLater(Notorious.getInstance(), 2L);
/*     */     }
/*     */     
/* 274 */     if (this.vanishedPlayers.size() != 0) { Player[] arrayOfPlayer;
/* 275 */       int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer2 = arrayOfPlayer[i];
/* 276 */         if ((this.vanishedPlayers.contains(localPlayer2.getUniqueId())) && 
/* 277 */           (!localPlayer1.hasPermission("notorious.vanish"))) {
/* 278 */           localPlayer1.hidePlayer(localPlayer2);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent)
/*     */   {
/* 287 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 288 */     if (this.vanishedPlayers.contains(localPlayer.getUniqueId())) {
/* 289 */       this.vanishedPlayers.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/* 295 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 296 */     if (this.vanishedPlayers.contains(localPlayer.getUniqueId())) {
/* 297 */       this.vanishedPlayers.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\vanish\VanishHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */