/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Cow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class MobStackHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private List<EntityType> mobList;
/*     */   
/*     */   public MobStackHandler(Notorious paramNotorious)
/*     */   {
/*  31 */     super(paramNotorious);
/*  32 */     this.mobList = new ArrayList();
/*     */     
/*  34 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*  35 */     loadEntityList();
/*  36 */     startStackTask();
/*     */   }
/*     */   
/*     */   public void disable() {
/*  40 */     this.mobList.clear();
/*  41 */     Iterator localIterator2; for (Iterator localIterator1 = Bukkit.getWorlds().iterator(); localIterator1.hasNext(); 
/*  42 */         localIterator2.hasNext())
/*     */     {
/*  41 */       World localWorld = (World)localIterator1.next();
/*  42 */       localIterator2 = localWorld.getLivingEntities().iterator(); continue;LivingEntity localLivingEntity = (LivingEntity)localIterator2.next();
/*  43 */       if (((localLivingEntity instanceof org.bukkit.entity.Monster)) && (localLivingEntity.isCustomNameVisible())) {
/*  44 */         localLivingEntity.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void loadEntityList()
/*     */   {
/*  51 */     if (!this.mobList.isEmpty()) this.mobList.clear();
/*  52 */     for (String str : getInstance().getConfigHandler().getMobStackingEntity()) {
/*  53 */       EntityType localEntityType = EntityType.valueOf(str.toUpperCase());
/*  54 */       this.mobList.add(localEntityType);
/*     */     }
/*     */   }
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
/*     */   public void startStackTask()
/*     */   {
/*  75 */     new org.bukkit.scheduler.BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  61 */         int i = MobStackHandler.this.getInstance().getConfigHandler().getMobStackingRadius();
/*  62 */         List localList = MobStackHandler.this.mobList;
/*  63 */         Iterator localIterator2; for (Iterator localIterator1 = Bukkit.getServer().getWorlds().iterator(); localIterator1.hasNext(); 
/*  64 */             localIterator2.hasNext())
/*     */         {
/*  63 */           World localWorld = (World)localIterator1.next();
/*  64 */           localIterator2 = localWorld.getLivingEntities().iterator(); continue;LivingEntity localLivingEntity = (LivingEntity)localIterator2.next();
/*  65 */           if ((localList.contains(localLivingEntity.getType())) && (localLivingEntity.isValid())) {
/*  66 */             for (Entity localEntity : localLivingEntity.getNearbyEntities(i, i, i)) {
/*  67 */               if (((localEntity instanceof LivingEntity)) && (localEntity.isValid()) && (localList.contains(localEntity.getType()))) {
/*  68 */                 MobStackHandler.this.stackOne(localLivingEntity, (LivingEntity)localEntity, ChatColor.valueOf(MobStackHandler.this.getInstance().getConfigHandler().getMobStackColor()));
/*     */               }
/*     */               
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  75 */     }.runTaskTimer(getInstance(), 40L, 40L);
/*     */   }
/*     */   
/*     */   public void unstackOne(LivingEntity paramLivingEntity, ChatColor paramChatColor) {
/*  79 */     String str1 = paramLivingEntity.getCustomName();
/*  80 */     int i = getAmount(str1, paramChatColor);
/*  81 */     if (i <= 1) return;
/*  82 */     i--;
/*  83 */     String str2 = paramChatColor + "x" + i;
/*  84 */     LivingEntity localLivingEntity = (LivingEntity)paramLivingEntity.getWorld().spawnEntity(paramLivingEntity.getLocation(), paramLivingEntity.getType());
/*  85 */     localLivingEntity.setCustomName(str2);
/*  86 */     localLivingEntity.setCustomNameVisible(true);
/*  87 */     paramLivingEntity.setHealth(0.0D);
/*     */   }
/*     */   
/*     */   public void stackOne(LivingEntity paramLivingEntity1, LivingEntity paramLivingEntity2, ChatColor paramChatColor) {
/*  91 */     if (paramLivingEntity1.getType() != paramLivingEntity2.getType()) { return;
/*     */     }
/*  93 */     String str1 = paramLivingEntity1.getCustomName();
/*  94 */     int i = getAmount(str1, paramChatColor);
/*  95 */     int j = 1;
/*  96 */     if (isStacked(paramLivingEntity2, paramChatColor)) j = getAmount(paramLivingEntity2.getCustomName(), paramChatColor);
/*  97 */     paramLivingEntity2.remove();
/*  98 */     int k; String str2; if (i == 0) {
/*  99 */       k = j + 1;
/* 100 */       str2 = paramChatColor + "x" + k;
/* 101 */       paramLivingEntity1.setCustomName(str2);
/* 102 */       paramLivingEntity1.setCustomNameVisible(true);
/*     */     } else {
/* 104 */       k = i + j;
/* 105 */       str2 = paramChatColor + "x" + k;
/* 106 */       paramLivingEntity1.setCustomName(str2);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getAmount(String paramString, ChatColor paramChatColor) {
/* 111 */     if (paramString == null) { return 0;
/*     */     }
/* 113 */     String str1 = ChatColor.getLastColors(paramString);
/* 114 */     if (str1.equals(Integer.valueOf('§' + paramChatColor.getChar()))) return 0;
/* 115 */     String str2 = paramString.replace("x", "");
/* 116 */     String str3 = ChatColor.stripColor(str2.replace("§f", ""));
/* 117 */     str3 = ChatColor.stripColor(str3);
/* 118 */     if (!str3.matches("[0-9]+")) return 0;
/* 119 */     if (str3.length() > 4) { return 0;
/*     */     }
/* 121 */     return Integer.parseInt(str3);
/*     */   }
/*     */   
/*     */   public boolean isStacked(LivingEntity paramLivingEntity, ChatColor paramChatColor) {
/* 125 */     return getAmount(paramLivingEntity.getCustomName(), paramChatColor) != 0;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityDeath(EntityDeathEvent paramEntityDeathEvent) {
/* 130 */     if ((paramEntityDeathEvent.getEntity() instanceof LivingEntity)) {
/* 131 */       LivingEntity localLivingEntity = paramEntityDeathEvent.getEntity();
/* 132 */       if ((localLivingEntity.getType() != EntityType.PLAYER) && (localLivingEntity.getType() != EntityType.VILLAGER)) {
/* 133 */         unstackOne(localLivingEntity, ChatColor.valueOf(getInstance().getConfigHandler().getMobStackColor()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteractEntity(PlayerInteractEntityEvent paramPlayerInteractEntityEvent) {
/* 140 */     Player localPlayer = paramPlayerInteractEntityEvent.getPlayer();
/* 141 */     Entity localEntity = paramPlayerInteractEntityEvent.getRightClicked();
/* 142 */     ItemStack localItemStack = localPlayer.getItemInHand();
/*     */     
/* 144 */     if ((localItemStack == null) || (localItemStack.getType() == Material.AIR)) { return;
/*     */     }
/* 146 */     if ((localEntity instanceof Cow)) {
/* 147 */       Cow localCow1 = (Cow)localEntity;
/* 148 */       if ((localItemStack.getType() == Material.WHEAT) && 
/* 149 */         (localCow1.canBreed()) && (localCow1.isCustomNameVisible())) {
/* 150 */         localCow1.setBreed(false);
/* 151 */         Cow localCow2 = (Cow)localCow1.getWorld().spawnEntity(localCow1.getLocation(), EntityType.COW);
/* 152 */         localCow2.setBaby();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\MobStackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */