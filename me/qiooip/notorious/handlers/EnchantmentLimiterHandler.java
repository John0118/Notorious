/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.inventory.InventoryType.SlotType;
/*     */ import org.bukkit.event.player.PlayerFishEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class EnchantmentLimiterHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private ArrayList<EnchantmentLimit> enchantmentLimits;
/*     */   
/*     */   public EnchantmentLimiterHandler(Notorious paramNotorious)
/*     */   {
/*  33 */     super(paramNotorious);
/*  34 */     this.enchantmentLimits = new ArrayList();
/*     */     
/*  36 */     loadEnchantmentLimits();
/*  37 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  41 */     this.enchantmentLimits.clear();
/*     */   }
/*     */   
/*     */   public void loadEnchantmentLimits() {
/*  45 */     ConfigurationSection localConfigurationSection = getInstance().getLimitersFile().getConfiguration().getConfigurationSection("enchantment-limiter");
/*  46 */     for (String str : localConfigurationSection.getKeys(false))
/*  47 */       if (localConfigurationSection.getInt(str) != -1) {
/*  48 */         EnchantmentLimit localEnchantmentLimit = new EnchantmentLimit();
/*  49 */         localEnchantmentLimit.setEnchantment(Enchantment.getByName(str));
/*  50 */         localEnchantmentLimit.setLevel(localConfigurationSection.getInt(str));
/*     */         
/*  52 */         this.enchantmentLimits.add(localEnchantmentLimit);
/*     */       }
/*     */   }
/*     */   
/*     */   public ArrayList<EnchantmentLimit> getEnchantmentLimits() {
/*  57 */     return this.enchantmentLimits;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEnchantItem(EnchantItemEvent paramEnchantItemEvent) {
/*  62 */     Map localMap = paramEnchantItemEvent.getEnchantsToAdd();
/*  63 */     for (EnchantmentLimit localEnchantmentLimit : this.enchantmentLimits) {
/*  64 */       if ((localMap.containsKey(localEnchantmentLimit.getEnchantment())) && (((Integer)localMap.get(localEnchantmentLimit.getEnchantment())).intValue() > localEnchantmentLimit.getLevel())) {
/*  65 */         localMap.remove(localEnchantmentLimit.getEnchantment());
/*  66 */         if (localEnchantmentLimit.getLevel() > 0) {
/*  67 */           localMap.put(localEnchantmentLimit.getEnchantment(), Integer.valueOf(localEnchantmentLimit.getLevel()));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
/*  75 */     Player localPlayer = (Player)paramInventoryClickEvent.getWhoClicked();
/*  76 */     Inventory localInventory = paramInventoryClickEvent.getInventory();
/*  77 */     InventoryType.SlotType localSlotType = paramInventoryClickEvent.getSlotType();
/*     */     
/*  79 */     if ((localInventory.getType().equals(InventoryType.ANVIL)) && (localSlotType.equals(InventoryType.SlotType.RESULT))) {
/*  80 */       ItemStack localItemStack = paramInventoryClickEvent.getCurrentItem();
/*  81 */       for (EnchantmentLimit localEnchantmentLimit : this.enchantmentLimits) { Object localObject;
/*  82 */         if ((localItemStack.hasItemMeta()) && (localItemStack.getItemMeta().hasLore())) {
/*  83 */           for (Iterator localIterator2 = localItemStack.getItemMeta().getLore().iterator(); localIterator2.hasNext();) { localObject = (String)localIterator2.next();
/*  84 */             if (((String)localObject).equals(getInstance().getConfigHandler().getUnrepairableLoreLine())) {
/*  85 */               paramInventoryClickEvent.setCancelled(true);
/*  86 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENCHANTMENTLIMITER_NOT_REPAIRABLE_MESSAGE.toString());
/*  87 */               return;
/*     */             }
/*     */           }
/*     */         } else {
/*  91 */           if (localItemStack.getType().equals(Material.ENCHANTED_BOOK)) {
/*  92 */             localObject = (EnchantmentStorageMeta)localItemStack.getItemMeta();
/*  93 */             if ((((EnchantmentStorageMeta)localObject).getStoredEnchants().containsKey(localEnchantmentLimit.getEnchantment())) && (((Integer)((EnchantmentStorageMeta)localObject).getStoredEnchants().get(localEnchantmentLimit.getEnchantment())).intValue() > localEnchantmentLimit.getLevel())) {
/*  94 */               paramInventoryClickEvent.setCancelled(true);
/*  95 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENCHANTMENTLIMITER_CAN_NOT_MERGE.toString());
/*  96 */               return;
/*     */             }
/*     */           }
/*  99 */           if ((localItemStack.getEnchantments().containsKey(localEnchantmentLimit.getEnchantment())) && (((Integer)localItemStack.getEnchantments().get(localEnchantmentLimit.getEnchantment())).intValue() > localEnchantmentLimit.getLevel())) {
/* 100 */             localItemStack.removeEnchantment(localEnchantmentLimit.getEnchantment());
/* 101 */             if (localEnchantmentLimit.getLevel() > 0) {
/* 102 */               localItemStack.addEnchantment(localEnchantmentLimit.getEnchantment(), localEnchantmentLimit.getLevel());
/*     */             }
/* 104 */             localPlayer.updateInventory();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerFish(PlayerFishEvent paramPlayerFishEvent)
/*     */   {
/* 117 */     org.bukkit.entity.Entity localEntity = paramPlayerFishEvent.getCaught();
/*     */     
/* 119 */     if ((localEntity != null) && ((localEntity instanceof ItemStack))) {
/* 120 */       ItemStack localItemStack = (ItemStack)localEntity;
/* 121 */       if ((localItemStack.getEnchantments() != null) && (!localItemStack.getEnchantments().isEmpty())) {
/* 122 */         HashMap localHashMap = new HashMap(localItemStack.getEnchantments());
/* 123 */         for (EnchantmentLimit localEnchantmentLimit : this.enchantmentLimits)
/* 124 */           if ((localHashMap.containsKey(localEnchantmentLimit.getEnchantment())) && (((Integer)localHashMap.get(localEnchantmentLimit.getEnchantment())).intValue() > localEnchantmentLimit.getLevel())) {
/* 125 */             localHashMap.remove(localEnchantmentLimit.getEnchantment());
/* 126 */             if (localEnchantmentLimit.getLevel() > 0)
/* 127 */               localHashMap.put(localEnchantmentLimit.getEnchantment(), Integer.valueOf(localEnchantmentLimit.getLevel()));
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDeath(EntityDeathEvent paramEntityDeathEvent) {
/*     */     Iterator localIterator2;
/*     */     label189:
/* 137 */     for (Iterator localIterator1 = paramEntityDeathEvent.getDrops().iterator(); localIterator1.hasNext(); 
/*     */         
/*     */ 
/* 140 */         localIterator2.hasNext())
/*     */     {
/* 137 */       ItemStack localItemStack = (ItemStack)localIterator1.next();
/* 138 */       if ((localItemStack == null) || (localItemStack.getType().equals(Material.AIR)) || (localItemStack.getEnchantments() == null) || (localItemStack.getEnchantments().isEmpty())) break label189;
/* 139 */       HashMap localHashMap = new HashMap(localItemStack.getEnchantments());
/* 140 */       localIterator2 = this.enchantmentLimits.iterator(); continue;EnchantmentLimit localEnchantmentLimit = (EnchantmentLimit)localIterator2.next();
/* 141 */       if ((localHashMap.containsKey(localEnchantmentLimit.getEnchantment())) && (((Integer)localHashMap.get(localEnchantmentLimit.getEnchantment())).intValue() > localEnchantmentLimit.getLevel())) {
/* 142 */         localHashMap.remove(localEnchantmentLimit.getEnchantment());
/* 143 */         if (localEnchantmentLimit.getLevel() > 0) {
/* 144 */           localHashMap.put(localEnchantmentLimit.getEnchantment(), Integer.valueOf(localEnchantmentLimit.getLevel()));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public class EnchantmentLimit
/*     */   {
/*     */     private Enchantment enchantment;
/*     */     private int level;
/*     */     
/*     */     public EnchantmentLimit() {}
/*     */     
/*     */     public Enchantment getEnchantment()
/*     */     {
/* 160 */       return this.enchantment;
/*     */     }
/*     */     
/*     */     public void setEnchantment(Enchantment paramEnchantment) {
/* 164 */       this.enchantment = paramEnchantment;
/*     */     }
/*     */     
/*     */     public int getLevel() {
/* 168 */       return this.level;
/*     */     }
/*     */     
/*     */     public void setLevel(int paramInt) {
/* 172 */       this.level = paramInt;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\EnchantmentLimiterHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */