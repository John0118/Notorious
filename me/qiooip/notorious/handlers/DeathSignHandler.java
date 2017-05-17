/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.ItemStackUtils;
/*     */ import org.apache.commons.lang.time.FastDateFormat;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class DeathSignHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   public DeathSignHandler(Notorious paramNotorious)
/*     */   {
/*  31 */     super(paramNotorious);
/*     */     
/*  33 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent) {
/*  38 */     Player localPlayer1 = paramPlayerDeathEvent.getEntity();
/*  39 */     Player localPlayer2 = localPlayer1.getKiller();
/*     */     
/*  41 */     if ((localPlayer2 != null) && (localPlayer1 != localPlayer2)) {
/*  42 */       paramPlayerDeathEvent.getDrops().add(getDeathSign(localPlayer1, localPlayer2));
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSignChange(SignChangeEvent paramSignChangeEvent) {
/*  48 */     if (isDeathSign(paramSignChangeEvent.getBlock())) {
/*  49 */       paramSignChangeEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent) {
/*  55 */     Block localBlock = paramBlockBreakEvent.getBlock();
/*  56 */     if (isDeathSign(localBlock)) {
/*  57 */       paramBlockBreakEvent.setCancelled(true);
/*  58 */       BlockState localBlockState = localBlock.getState();
/*  59 */       Sign localSign = (Sign)localBlockState;
/*  60 */       localBlock.getWorld().dropItemNaturally(localBlock.getLocation(), ItemStackUtils.setItemNameAndLore(new ItemStack(Material.SIGN, 1), getInstance().getConfigHandler().getDeathsignName(), Arrays.asList(localSign.getLines())));
/*  61 */       localBlock.setType(Material.AIR);
/*  62 */       localBlockState.update();
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/*  68 */     Block localBlock = paramBlockPlaceEvent.getBlock();
/*  69 */     Player localPlayer = paramBlockPlaceEvent.getPlayer();
/*  70 */     ItemStack localItemStack = localPlayer.getItemInHand();
/*  71 */     if (((localBlock.getState() instanceof Sign)) && (localItemStack.hasItemMeta()) && (localItemStack.getItemMeta().hasDisplayName()) && 
/*  72 */       (localItemStack.getItemMeta().getDisplayName().equalsIgnoreCase(getInstance().getConfigHandler().getDeathsignName()))) {
/*  73 */       BlockState localBlockState = localBlock.getState();
/*  74 */       Sign localSign = (Sign)localBlockState;
/*  75 */       int i = 0;
/*  76 */       List localList = localItemStack.getItemMeta().getLore();
/*  77 */       for (String str : localList) {
/*  78 */         if (i <= 4) {
/*  79 */           localSign.setLine(i++, str);
/*  80 */           if (i == 4) {
/*     */             break;
/*     */           }
/*     */         }
/*     */       }
/*  85 */       localSign.update();
/*  86 */       localPlayer.closeInventory();
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getDeathSign(Player paramPlayer1, Player paramPlayer2)
/*     */   {
/*  92 */     FastDateFormat localFastDateFormat = FastDateFormat.getInstance(getInstance().getConfigHandler().getDeathsignDateFormat(), TimeZone.getTimeZone(getInstance().getConfigHandler().getDeathsignTimezone()), java.util.Locale.ENGLISH);
/*     */     
/*  94 */     ItemStack localItemStack = new ItemStack(Material.SIGN, 1);
/*  95 */     ItemMeta localItemMeta = localItemStack.getItemMeta();
/*  96 */     localItemMeta.setDisplayName(getInstance().getConfigHandler().getDeathsignName());
/*  97 */     ArrayList localArrayList = new ArrayList();
/*  98 */     for (String str : getInstance().getConfigHandler().getDeathsignLore()) {
/*  99 */       localArrayList.add(str.replace("<killer>", paramPlayer2.getName()).replace("<victim>", paramPlayer1.getName()).replace("<date>", localFastDateFormat.format(System.currentTimeMillis())));
/*     */     }
/* 101 */     localItemMeta.setLore(localArrayList);
/* 102 */     localItemStack.setItemMeta(localItemMeta);
/*     */     
/* 104 */     return localItemStack;
/*     */   }
/*     */   
/*     */   private boolean isDeathSign(Block paramBlock) {
/* 108 */     BlockState localBlockState = paramBlock.getState();
/* 109 */     if ((localBlockState instanceof Sign)) {
/* 110 */       Sign localSign = (Sign)localBlockState;
/* 111 */       String[] arrayOfString = localSign.getLines();
/* 112 */       return (arrayOfString.length > 0) && (arrayOfString[1] != null) && (arrayOfString[1].equals(getInstance().getConfigHandler().getDeathsignLore().get(1)));
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\DeathSignHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */