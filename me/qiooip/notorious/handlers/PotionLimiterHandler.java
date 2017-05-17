/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.ThrownPotion;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.PotionSplashEvent;
/*     */ import org.bukkit.event.inventory.BrewEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.BrewerInventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.potion.Potion;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class PotionLimiterHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private ArrayList<PotionLimit> potionLimits;
/*     */   
/*     */   public PotionLimiterHandler(Notorious paramNotorious)
/*     */   {
/*  30 */     super(paramNotorious);
/*  31 */     this.potionLimits = new ArrayList();
/*     */     
/*  33 */     loadPotionLimits();
/*  34 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  38 */     this.potionLimits.clear();
/*     */   }
/*     */   
/*     */   public void loadPotionLimits() {
/*  42 */     ConfigurationSection localConfigurationSection = getInstance().getLimitersFile().getConfiguration().getConfigurationSection("potion-limiter");
/*  43 */     for (String str : localConfigurationSection.getKeys(false))
/*  44 */       if (localConfigurationSection.getInt(str + ".level") != -1) {
/*  45 */         PotionLimit localPotionLimit = new PotionLimit();
/*  46 */         localPotionLimit.setType(PotionEffectType.getByName(str));
/*  47 */         localPotionLimit.setLevel(localConfigurationSection.getInt(str + ".level"));
/*  48 */         localPotionLimit.setExtended(localConfigurationSection.getBoolean(str + ".extended"));
/*     */         
/*  50 */         this.potionLimits.add(localPotionLimit);
/*     */       }
/*     */   }
/*     */   
/*     */   public ArrayList<PotionLimit> getPotionLimits() {
/*  55 */     return this.potionLimits;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPotionBrew(BrewEvent paramBrewEvent) {
/*  60 */     final BrewerInventory localBrewerInventory = paramBrewEvent.getContents();
/*     */     
/*  62 */     final ItemStack localItemStack = localBrewerInventory.getIngredient().clone();
/*  63 */     final ItemStack[] arrayOfItemStack = new ItemStack[3];
/*  64 */     for (int i = 0; i < 3; i++) {
/*  65 */       if (paramBrewEvent.getContents().getItem(i) != null) {
/*  66 */         arrayOfItemStack[i] = localBrewerInventory.getItem(i).clone();
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/* 101 */     new org.bukkit.scheduler.BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  72 */         for (int i = 0; i < 3; i++) {
/*  73 */           if (localBrewerInventory.getItem(i) != null) { Iterator localIterator2;
/*  74 */             for (Iterator localIterator1 = Potion.fromItemStack(localBrewerInventory.getItem(i)).getEffects().iterator(); localIterator1.hasNext(); 
/*  75 */                 localIterator2.hasNext())
/*     */             {
/*  74 */               PotionEffect localPotionEffect = (PotionEffect)localIterator1.next();
/*  75 */               localIterator2 = PotionLimiterHandler.this.potionLimits.iterator(); continue;PotionLimiterHandler.PotionLimit localPotionLimit = (PotionLimiterHandler.PotionLimit)localIterator2.next();
/*  76 */               if (localPotionLimit.getType().equals(localPotionEffect.getType())) {
/*  77 */                 int j = localPotionLimit.getLevel();
/*  78 */                 int k = localPotionEffect.getAmplifier() + 1;
/*  79 */                 Potion localPotion = Potion.fromItemStack(localBrewerInventory.getItem(i));
/*  80 */                 int m; if ((j == 0) || (k > j)) {
/*  81 */                   localBrewerInventory.setIngredient(localItemStack);
/*  82 */                   for (m = 0; m < 3; m++) {
/*  83 */                     localBrewerInventory.setItem(m, arrayOfItemStack[m]);
/*     */                   }
/*  85 */                   return;
/*     */                 }
/*  87 */                 if ((localPotion.hasExtendedDuration()) && (!localPotionLimit.isExtended())) {
/*  88 */                   localBrewerInventory.setIngredient(localItemStack);
/*  89 */                   for (m = 0; m < 3; m++) {
/*  90 */                     localBrewerInventory.setItem(m, arrayOfItemStack[m]);
/*     */                   }
/*  92 */                   return;
/*     */                 }
/*     */                 
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 101 */     }.runTaskLater(Notorious.getInstance(), 1L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerItemConsume(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/* 106 */     Player localPlayer = paramPlayerItemConsumeEvent.getPlayer();
/* 107 */     ItemStack localItemStack = paramPlayerItemConsumeEvent.getItem();
/*     */     
/* 109 */     if (!localItemStack.getType().equals(Material.POTION)) return;
/* 110 */     if ((localItemStack.getType().equals(Material.POTION)) && (localItemStack.getDurability() == 0)) return;
/*     */     Iterator localIterator2;
/* 112 */     for (Iterator localIterator1 = Potion.fromItemStack(localItemStack).getEffects().iterator(); localIterator1.hasNext(); 
/* 113 */         localIterator2.hasNext())
/*     */     {
/* 112 */       PotionEffect localPotionEffect = (PotionEffect)localIterator1.next();
/* 113 */       localIterator2 = this.potionLimits.iterator(); continue;PotionLimit localPotionLimit = (PotionLimit)localIterator2.next();
/* 114 */       if (localPotionLimit.getType().equals(localPotionEffect.getType())) {
/* 115 */         int i = localPotionLimit.getLevel();
/* 116 */         int j = localPotionEffect.getAmplifier() + 1;
/* 117 */         Potion localPotion = Potion.fromItemStack(localItemStack);
/* 118 */         if ((i == 0) || (j > i)) {
/* 119 */           paramPlayerItemConsumeEvent.setCancelled(true);
/* 120 */           localPlayer.setItemInHand(new ItemStack(Material.AIR));
/* 121 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.POTIONLIMITER_DENY_MESSAGE.toString());
/* 122 */           return;
/*     */         }
/* 124 */         if ((localPotion.hasExtendedDuration()) && (!localPotionLimit.isExtended())) {
/* 125 */           paramPlayerItemConsumeEvent.setCancelled(true);
/* 126 */           localPlayer.setItemInHand(new ItemStack(Material.AIR));
/* 127 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.POTIONLIMITER_DENY_MESSAGE.toString());
/* 128 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onPotionSplash(PotionSplashEvent paramPotionSplashEvent)
/*     */   {
/* 139 */     ThrownPotion localThrownPotion = paramPotionSplashEvent.getPotion();
/*     */     Iterator localIterator2;
/* 141 */     for (Iterator localIterator1 = localThrownPotion.getEffects().iterator(); localIterator1.hasNext(); 
/* 142 */         localIterator2.hasNext())
/*     */     {
/* 141 */       PotionEffect localPotionEffect = (PotionEffect)localIterator1.next();
/* 142 */       localIterator2 = this.potionLimits.iterator(); continue;PotionLimit localPotionLimit = (PotionLimit)localIterator2.next();
/* 143 */       if (localPotionLimit.getType().equals(localPotionEffect.getType())) { int j;
/* 144 */         if ((localThrownPotion.getShooter() instanceof Player)) {
/* 145 */           Player localPlayer = (Player)localThrownPotion.getShooter();
/* 146 */           j = localPotionLimit.getLevel();
/* 147 */           int k = localPotionEffect.getAmplifier() + 1;
/* 148 */           Potion localPotion2 = Potion.fromItemStack(localThrownPotion.getItem());
/* 149 */           if ((j == 0) || (k > j)) {
/* 150 */             paramPotionSplashEvent.setCancelled(true);
/* 151 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.POTIONLIMITER_DENY_MESSAGE.toString());
/* 152 */             return;
/*     */           }
/* 154 */           if ((localPotion2.hasExtendedDuration()) && (!localPotionLimit.isExtended())) {
/* 155 */             paramPotionSplashEvent.setCancelled(true);
/* 156 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.POTIONLIMITER_DENY_MESSAGE.toString());
/*     */           }
/*     */         }
/*     */         else {
/* 160 */           int i = localPotionLimit.getLevel();
/* 161 */           j = localPotionEffect.getAmplifier();
/* 162 */           Potion localPotion1 = Potion.fromItemStack(localThrownPotion.getItem());
/* 163 */           if ((i == 0) || (j > i)) {
/* 164 */             paramPotionSplashEvent.setCancelled(true);
/* 165 */             return;
/*     */           }
/* 167 */           if ((localPotion1.hasExtendedDuration()) && (!localPotionLimit.isExtended())) {
/* 168 */             paramPotionSplashEvent.setCancelled(true);
/* 169 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public class PotionLimit
/*     */   {
/*     */     private PotionEffectType type;
/*     */     private int level;
/*     */     private boolean extended;
/*     */     
/*     */     public PotionLimit() {}
/*     */     
/*     */     public PotionEffectType getType()
/*     */     {
/* 187 */       return this.type;
/*     */     }
/*     */     
/*     */     public void setType(PotionEffectType paramPotionEffectType) {
/* 191 */       this.type = paramPotionEffectType;
/*     */     }
/*     */     
/*     */     public int getLevel() {
/* 195 */       return this.level;
/*     */     }
/*     */     
/*     */     public void setLevel(int paramInt) {
/* 199 */       this.level = paramInt;
/*     */     }
/*     */     
/*     */     public boolean isExtended() {
/* 203 */       return this.extended;
/*     */     }
/*     */     
/*     */     public void setExtended(boolean paramBoolean) {
/* 207 */       this.extended = paramBoolean;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\PotionLimiterHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */