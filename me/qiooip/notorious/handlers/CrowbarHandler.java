/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.World.Environment;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.CreatureSpawner;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ import org.zencode.mango.factions.claims.ClaimManager;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ 
/*     */ public class CrowbarHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private Pattern pattern;
/*     */   
/*     */   public CrowbarHandler(Notorious paramNotorious)
/*     */   {
/*  39 */     super(paramNotorious);
/*  40 */     this.pattern = Pattern.compile("\\d+");
/*     */     
/*  42 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/*  48 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/*  49 */     ItemStack localItemStack1 = paramPlayerInteractEvent.getItem();
/*  50 */     Action localAction = paramPlayerInteractEvent.getAction();
/*     */     
/*  52 */     if (localItemStack1 == null) { return;
/*     */     }
/*  54 */     if ((localAction.equals(Action.LEFT_CLICK_BLOCK)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK))) {
/*  55 */       Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/*  56 */       World localWorld = localBlock.getWorld();
/*  57 */       org.bukkit.Location localLocation = localBlock.getLocation();
/*  58 */       if (isCrowbar(localItemStack1)) {
/*  59 */         paramPlayerInteractEvent.setCancelled(true);
/*  60 */         PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/*  61 */         org.zencode.mango.factions.Faction localFaction = null;
/*  62 */         if ((Mango.getInstance().getClaimManager().getClaimAt(localLocation) != null) && 
/*  63 */           ((Mango.getInstance().getClaimManager().getClaimAt(localLocation).getOwner() instanceof PlayerFaction))) {
/*  64 */           localFaction = Mango.getInstance().getClaimManager().getClaimAt(localLocation).getOwner();
/*     */         }
/*     */         
/*     */ 
/*  68 */         if ((localPlayerFaction != null) && (localFaction != null) && (localPlayerFaction != localFaction) && 
/*  69 */           (((PlayerFaction)localFaction).isRaidable())) { return;
/*     */         }
/*  71 */         if (!me.qiooip.notorious.integration.WorldGuard.isPvPEnabled(localPlayer)) return;
/*  72 */         if (localWorld.getEnvironment() == World.Environment.NETHER) {
/*  73 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.CROWBAR_DENY_USAGE_NETHER.toString());
/*  74 */           return;
/*     */         }
/*  76 */         if (localWorld.getEnvironment() == World.Environment.THE_END) {
/*  77 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.CROWBAR_DENY_USAGE_END.toString()); return; }
/*     */         int i;
/*     */         int j;
/*  80 */         if (localBlock.getType().equals(Material.MOB_SPAWNER)) {
/*  81 */           i = getUses(localItemStack1, 0);
/*  82 */           j = getUses(localItemStack1, 1);
/*  83 */           if (i > 0) {
/*  84 */             localWorld.playEffect(localLocation, Effect.STEP_SOUND, Material.MOB_SPAWNER.getId());
/*  85 */             CreatureSpawner localCreatureSpawner = (CreatureSpawner)localBlock.getState();
/*  86 */             String str = getInstance().getConfigHandler().getCrowbarSpawnerNameColor() + localCreatureSpawner.getSpawnedType().name() + " Spawner";
/*  87 */             ItemStack localItemStack2 = me.qiooip.notorious.utils.ItemStackUtils.setItemName(new ItemStack(Material.MOB_SPAWNER), str);
/*  88 */             localBlock.setType(Material.AIR);
/*  89 */             localWorld.dropItemNaturally(localLocation, localItemStack2);
/*  90 */             i--; if ((i <= 0) && (j <= 0)) {
/*  91 */               localPlayer.setItemInHand(new ItemStack(Material.AIR));
/*     */             } else {
/*  93 */               updateOrCreateCrowbarMeta(localItemStack1, i, j);
/*     */             }
/*  95 */             localPlayer.updateInventory();
/*     */           } else {
/*  97 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.CROWBAR_ZERO_USAGES_SPAWNERS.toString());
/*     */           }
/*  99 */         } else if (localBlock.getType().equals(Material.ENDER_PORTAL_FRAME)) {
/* 100 */           i = getUses(localItemStack1, 0);
/* 101 */           j = getUses(localItemStack1, 1);
/* 102 */           if (j > 0) {
/* 103 */             localBlock.setType(Material.AIR);
/* 104 */             localWorld.playEffect(localLocation, Effect.STEP_SOUND, Material.ENDER_PORTAL_FRAME.getId());
/* 105 */             localWorld.dropItemNaturally(localLocation, new ItemStack(Material.ENDER_PORTAL_FRAME));
/* 106 */             j--; if ((j <= 0) && (i <= 0)) {
/* 107 */               localPlayer.setItemInHand(new ItemStack(Material.AIR));
/*     */             } else {
/* 109 */               updateOrCreateCrowbarMeta(localItemStack1, i, j);
/*     */             }
/* 111 */             localPlayer.updateInventory();
/*     */           } else {
/* 113 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.CROWBAR_ZERO_USAGES_PORTALS.toString());
/*     */           }
/*     */         } else {
/* 116 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.CROWBAR_DENY_USAGE_MESSAGE.toString());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/* 124 */     Block localBlock = paramBlockPlaceEvent.getBlock();
/* 125 */     ItemStack localItemStack = paramBlockPlaceEvent.getItemInHand();
/* 126 */     String str1 = localItemStack.getItemMeta().getDisplayName();
/* 127 */     if ((localItemStack.getType().equals(Material.MOB_SPAWNER)) && (localItemStack.getItemMeta().hasDisplayName()) && 
/* 128 */       (str1.startsWith(getInstance().getConfigHandler().getCrowbarSpawnerNameColor())) && (str1.endsWith(" Spawner"))) {
/* 129 */       String str2 = ChatColor.stripColor(str1).replace(" Spawner", "").replace(" ", "_").toUpperCase();
/* 130 */       EntityType localEntityType = EntityType.valueOf(str2);
/* 131 */       CreatureSpawner localCreatureSpawner = (CreatureSpawner)localBlock.getState();
/* 132 */       localCreatureSpawner.setSpawnedType(localEntityType);
/* 133 */       localCreatureSpawner.update();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isCrowbar(ItemStack paramItemStack)
/*     */   {
/* 139 */     if ((paramItemStack != null) && (paramItemStack.getType().equals(Material.DIAMOND_HOE)) && (paramItemStack.getItemMeta().hasDisplayName()) && (paramItemStack.getItemMeta().getDisplayName().equals(getInstance().getConfigHandler().getCrowbarName()))) return true;
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public int getUses(ItemStack paramItemStack, int paramInt) {
/* 144 */     if ((paramItemStack != null) && (paramItemStack.getItemMeta().hasDisplayName()) && (paramItemStack.getItemMeta().getDisplayName().equals(getInstance().getConfigHandler().getCrowbarName())) && (paramItemStack.getItemMeta().hasLore())) {
/* 145 */       String str = (String)paramItemStack.getItemMeta().getLore().get(paramInt);
/* 146 */       Matcher localMatcher = this.pattern.matcher(ChatColor.stripColor(str));
/* 147 */       int i = 0;
/* 148 */       while (localMatcher.find()) {
/* 149 */         i = Integer.valueOf(localMatcher.group()).intValue();
/*     */       }
/* 151 */       return i;
/*     */     }
/* 153 */     return 0;
/*     */   }
/*     */   
/*     */   public ItemStack getNewCrowbar() {
/* 157 */     ItemStack localItemStack = new ItemStack(Material.DIAMOND_HOE, 1);
/* 158 */     int i = getInstance().getConfigHandler().getCrowbarUsesSpawners();
/* 159 */     int j = getInstance().getConfigHandler().getCrowbarUsesPortals();
/* 160 */     updateOrCreateCrowbarMeta(localItemStack, i, j);
/*     */     
/* 162 */     return localItemStack;
/*     */   }
/*     */   
/*     */   public void updateOrCreateCrowbarMeta(ItemStack paramItemStack, int paramInt1, int paramInt2) {
/* 166 */     ItemMeta localItemMeta = paramItemStack.getItemMeta();
/* 167 */     localItemMeta.setDisplayName(getInstance().getConfigHandler().getCrowbarName());
/* 168 */     ArrayList localArrayList = new ArrayList();
/* 169 */     for (String str : getInstance().getConfigHandler().getCrowbarLore()) {
/* 170 */       localArrayList.add(str.replaceAll("<scount>", String.valueOf(paramInt1)).replaceAll("<pcount>", String.valueOf(paramInt2)));
/*     */     }
/* 172 */     localItemMeta.setLore(localArrayList);
/* 173 */     paramItemStack.setItemMeta(localItemMeta);
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\CrowbarHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */