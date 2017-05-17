/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.InventoryUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryType.SlotType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class ReportHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<String, String> reporters;
/*     */   private HashMap<UUID, Long> reportCooldowns;
/*     */   private Inventory inventory;
/*     */   private ArrayList<InventoryItem> inventoryItems;
/*     */   
/*     */   public ReportHandler(Notorious paramNotorious)
/*     */   {
/*  34 */     super(paramNotorious);
/*  35 */     this.reporters = new HashMap();
/*  36 */     this.reportCooldowns = new HashMap();
/*  37 */     this.inventoryItems = new ArrayList();
/*     */     
/*  39 */     loadInventoryItems();
/*  40 */     createInventory();
/*  41 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  45 */     this.reportCooldowns.clear();
/*     */   }
/*     */   
/*     */   public void loadInventoryItems()
/*     */   {
/*  50 */     this.inventoryItems.clear();
/*  51 */     ConfigurationSection localConfigurationSection = getInstance().getConfigFile().getConfiguration().getConfigurationSection("report-inventory-items");
/*  52 */     for (String str : localConfigurationSection.getKeys(false)) {
/*  53 */       InventoryItem localInventoryItem = new InventoryItem();
/*  54 */       localInventoryItem.setSlot(Integer.valueOf(str).intValue() - 1);
/*  55 */       localInventoryItem.setReason(ChatColor.translateAlternateColorCodes('&', localConfigurationSection.getString(str + ".reason")));
/*  56 */       String[] arrayOfString = localConfigurationSection.getString(str + ".material-id").split(":");
/*  57 */       int i = Integer.valueOf(arrayOfString[1]).intValue();
/*     */       
/*  59 */       ItemStack localItemStack = new ItemStack(Material.getMaterial(Integer.valueOf(arrayOfString[0]).intValue()));
/*  60 */       ItemMeta localItemMeta = localItemStack.getItemMeta();
/*  61 */       localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', localConfigurationSection.getString(str + ".reason")));
/*  62 */       localItemStack.setItemMeta(localItemMeta);
/*  63 */       localItemStack.setDurability((short)i);
/*     */       
/*  65 */       localInventoryItem.setItem(localItemStack);
/*     */       
/*  67 */       this.inventoryItems.add(localInventoryItem);
/*     */     }
/*     */   }
/*     */   
/*     */   public void createInventory() {
/*  72 */     Inventory localInventory = Bukkit.createInventory(null, InventoryUtils.getInventorySize(this.inventoryItems.size()), getInstance().getConfigHandler().getReportInventoryTitle());
/*  73 */     for (InventoryItem localInventoryItem : this.inventoryItems) {
/*  74 */       localInventory.setItem(localInventoryItem.getSlot(), localInventoryItem.getItem());
/*     */     }
/*  76 */     this.inventory = localInventory;
/*     */   }
/*     */   
/*     */   public void addReporter(Player paramPlayer1, Player paramPlayer2) {
/*  80 */     this.reporters.put(paramPlayer1.getName(), paramPlayer2.getName());
/*     */   }
/*     */   
/*     */   public void applyCooldown(Player paramPlayer) {
/*  84 */     this.reportCooldowns.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getReportCommandDelay() * 1000));
/*     */   }
/*     */   
/*     */   public boolean isActive(Player paramPlayer) {
/*  88 */     return (this.reportCooldowns.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.reportCooldowns.get(paramPlayer.getUniqueId())).longValue());
/*     */   }
/*     */   
/*     */   public long getMillisecondsLeft(Player paramPlayer) {
/*  92 */     if (this.reportCooldowns.containsKey(paramPlayer.getUniqueId())) {
/*  93 */       return Math.max(((Long)this.reportCooldowns.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*     */     }
/*  95 */     return 0L;
/*     */   }
/*     */   
/*     */   public HashMap<String, String> getReporters() {
/*  99 */     return this.reporters;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, Long> getRequestCooldowns() {
/* 103 */     return this.reportCooldowns;
/*     */   }
/*     */   
/*     */   public Inventory getInventory() {
/* 107 */     return this.inventory;
/*     */   }
/*     */   
/*     */   public void setInventory(Inventory paramInventory) {
/* 111 */     this.inventory = paramInventory;
/*     */   }
/*     */   
/*     */   public ArrayList<InventoryItem> getInventoryItems() {
/* 115 */     return this.inventoryItems;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/* 120 */     Player localPlayer = (Player)paramInventoryClickEvent.getWhoClicked();
/* 121 */     InventoryAction localInventoryAction = paramInventoryClickEvent.getAction();
/* 122 */     Inventory localInventory = paramInventoryClickEvent.getInventory();
/* 123 */     ItemStack localItemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 125 */     if (paramInventoryClickEvent.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) return;
/* 126 */     if ((localItemStack == null) || (localItemStack.getType().equals(Material.AIR))) { return;
/*     */     }
/* 128 */     if (localInventory.getName().equals(this.inventory.getName())) {
/* 129 */       if ((localInventoryAction.equals(InventoryAction.HOTBAR_SWAP)) || (localInventoryAction.equals(InventoryAction.SWAP_WITH_CURSOR))) {
/* 130 */         paramInventoryClickEvent.setCancelled(true);
/*     */       }
/* 132 */       for (InventoryItem localInventoryItem : this.inventoryItems) {
/* 133 */         if (localItemStack.getItemMeta().getDisplayName().equals(localInventoryItem.getItem().getItemMeta().getDisplayName())) {
/* 134 */           Notorious.getInstance().getReportHandler().applyCooldown(localPlayer);
/* 135 */           paramInventoryClickEvent.setCancelled(true);
/* 136 */           for (String str : getInstance().getConfigHandler().getReportMessage()) {
/* 137 */             Bukkit.broadcast(str.replace("<reporter>", localPlayer.getName()).replace("<suspect>", (CharSequence)this.reporters.get(localPlayer.getName())).replace("<reason>", localItemStack.getItemMeta().getDisplayName()), "notorious.report.receive");
/*     */           }
/* 139 */           localPlayer.closeInventory();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClose(InventoryCloseEvent paramInventoryCloseEvent) {
/* 147 */     Player localPlayer = (Player)paramInventoryCloseEvent.getPlayer();
/* 148 */     Inventory localInventory = paramInventoryCloseEvent.getInventory();
/*     */     
/* 150 */     if ((localInventory.getName().equals(this.inventory.getName())) && 
/* 151 */       (this.reporters.containsKey(localPlayer.getName()))) {
/* 152 */       this.reporters.remove(localPlayer.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   public class InventoryItem
/*     */   {
/*     */     private int slot;
/*     */     private String reason;
/*     */     private ItemStack item;
/*     */     
/*     */     public InventoryItem() {}
/*     */     
/*     */     public int getSlot()
/*     */     {
/* 166 */       return this.slot;
/*     */     }
/*     */     
/*     */     public void setSlot(int paramInt) {
/* 170 */       this.slot = paramInt;
/*     */     }
/*     */     
/*     */     public String getReason() {
/* 174 */       return this.reason;
/*     */     }
/*     */     
/*     */     public void setReason(String paramString) {
/* 178 */       this.reason = paramString;
/*     */     }
/*     */     
/*     */     public ItemStack getItem() {
/* 182 */       return this.item;
/*     */     }
/*     */     
/*     */     public void setItem(ItemStack paramItemStack) {
/* 186 */       this.item = paramItemStack;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\ReportHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */