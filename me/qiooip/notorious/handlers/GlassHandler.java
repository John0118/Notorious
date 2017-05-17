/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import com.sk89q.worldedit.BlockVector;
/*     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*     */ import com.sk89q.worldguard.protection.managers.RegionManager;
/*     */ import com.sk89q.worldguard.protection.regions.ProtectedRegion;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.integration.WorldGuard;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ 
/*     */ public class GlassHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, List<Location>> locationsWorldGuard;
/*     */   private HashMap<UUID, List<Location>> locationsFaction;
/*     */   
/*     */   public GlassHandler(Notorious paramNotorious)
/*     */   {
/*  33 */     super(paramNotorious);
/*  34 */     this.locationsWorldGuard = new HashMap();
/*  35 */     this.locationsFaction = new HashMap();
/*     */     
/*  37 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*     */     Player[] arrayOfPlayer;
/*  42 */     int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/*  43 */       removeGlass(localPlayer);
/*     */     }
/*  45 */     this.locationsWorldGuard.clear();
/*  46 */     this.locationsFaction.clear();
/*     */   }
/*     */   
/*     */   @org.bukkit.event.EventHandler
/*     */   public void onPlayerMove(PlayerMoveEvent paramPlayerMoveEvent) {
/*  51 */     Player localPlayer = paramPlayerMoveEvent.getPlayer();
/*  52 */     Location localLocation1 = paramPlayerMoveEvent.getFrom();
/*  53 */     Location localLocation2 = paramPlayerMoveEvent.getTo();
/*     */     Iterator localIterator1;
/*  55 */     Object localObject; if (getInstance().getCombatTagHandler().isCombatTagged(localPlayer)) {
/*  56 */       for (localIterator1 = WorldGuard.getWorldGuard().getRegionManager(localLocation1.getWorld()).getRegions().values().iterator(); localIterator1.hasNext();) { localObject = (ProtectedRegion)localIterator1.next();
/*  57 */         if ((localObject != null) && (((ProtectedRegion)localObject).getFlag(com.sk89q.worldguard.protection.flags.DefaultFlag.PVP) == com.sk89q.worldguard.protection.flags.StateFlag.State.DENY)) {
/*  58 */           if (((ProtectedRegion)localObject).contains(localLocation2.getBlockX(), localLocation2.getBlockY(), localLocation2.getBlockZ())) {
/*  59 */             paramPlayerMoveEvent.setTo(localLocation1);
/*     */           }
/*  61 */           renderGlassWorldGuard(localPlayer, localLocation2, (ProtectedRegion)localObject);
/*     */         }
/*     */       }
/*  64 */     } else if (getInstance().getPlayerDataHandler().getByPlayer(localPlayer).getPvPTime() > 0) { Iterator localIterator2;
/*  65 */       for (localIterator1 = WorldGuard.getWorldGuard().getRegionManager(localLocation1.getWorld()).getRegions().values().iterator(); localIterator1.hasNext(); 
/*  66 */           localIterator2.hasNext())
/*     */       {
/*  65 */         localObject = (ProtectedRegion)localIterator1.next();
/*  66 */         localIterator2 = getInstance().getConfigHandler().getGlassProtectedRegions().iterator(); continue;String str = (String)localIterator2.next();
/*  67 */         if ((localObject != null) && (((ProtectedRegion)localObject).getId().equals(str))) {
/*  68 */           if (((ProtectedRegion)localObject).contains(localLocation2.getBlockX(), localLocation2.getBlockY(), localLocation2.getBlockZ())) {
/*  69 */             paramPlayerMoveEvent.setTo(localLocation1);
/*     */           }
/*  71 */           renderGlassWorldGuard(localPlayer, localLocation2, (ProtectedRegion)localObject);
/*     */         }
/*     */       }
/*     */       
/*  75 */       for (localIterator1 = Mango.getInstance().getClaimManager().getClaims().iterator(); localIterator1.hasNext();) { localObject = (Claim)localIterator1.next();
/*  76 */         if ((localObject != null) && 
/*  77 */           ((((Claim)localObject).getOwner() instanceof org.zencode.mango.factions.types.PlayerFaction))) {
/*  78 */           if (((Claim)localObject).isInside(localLocation2, true)) {
/*  79 */             paramPlayerMoveEvent.setTo(localLocation1);
/*     */           }
/*  81 */           renderGlassFaction(localPlayer, localLocation2, (Claim)localObject);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/*  86 */       removeGlass(localPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderGlassWorldGuard(Player paramPlayer, Location paramLocation, ProtectedRegion paramProtectedRegion) {
/*  91 */     if (paramProtectedRegion == null) { return;
/*     */     }
/*  93 */     int i = closest(paramLocation.getBlockX(), new int[] { paramProtectedRegion.getMinimumPoint().getBlockX(), paramProtectedRegion.getMaximumPoint().getBlockX() });
/*  94 */     int j = closest(paramLocation.getBlockZ(), new int[] { paramProtectedRegion.getMinimumPoint().getBlockZ(), paramProtectedRegion.getMaximumPoint().getBlockZ() });
/*     */     
/*  96 */     int k = Math.abs(paramLocation.getX() - i) < 8.0D ? 1 : 0;
/*  97 */     int m = Math.abs(paramLocation.getZ() - j) < 8.0D ? 1 : 0;
/*     */     
/*  99 */     if ((k == 0) && (m == 0)) { return;
/*     */     }
/* 101 */     ArrayList localArrayList = new ArrayList();
/* 102 */     int n; int i1; Location localLocation; if (k != 0) {
/* 103 */       for (n = -4; n < 5; n++) {
/* 104 */         for (i1 = -7; i1 < 8; i1++) {
/* 105 */           if (isInside(paramProtectedRegion.getMinimumPoint().getBlockZ(), paramProtectedRegion.getMaximumPoint().getBlockZ(), paramLocation.getBlockZ() + i1)) {
/* 106 */             localLocation = new Location(paramLocation.getWorld(), Double.valueOf(i).doubleValue(), Double.valueOf(paramLocation.getBlockY() + n).doubleValue(), Double.valueOf(paramLocation.getBlockZ() + i1).doubleValue());
/* 107 */             if ((!localArrayList.contains(localLocation)) && (!localLocation.getBlock().getType().isOccluding())) {
/* 108 */               localArrayList.add(localLocation);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 114 */     if (m != 0) {
/* 115 */       for (n = -4; n < 5; n++) {
/* 116 */         for (i1 = -7; i1 < 8; i1++) {
/* 117 */           if (isInside(paramProtectedRegion.getMinimumPoint().getBlockX(), paramProtectedRegion.getMaximumPoint().getBlockX(), paramLocation.getBlockX() + i1)) {
/* 118 */             localLocation = new Location(paramLocation.getWorld(), Double.valueOf(paramLocation.getBlockX() + i1).doubleValue(), Double.valueOf(paramLocation.getBlockY() + n).doubleValue(), Double.valueOf(j).doubleValue());
/* 119 */             if ((!localArrayList.contains(localLocation)) && (!localLocation.getBlock().getType().isOccluding())) {
/* 120 */               localArrayList.add(localLocation);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 126 */     updateWorldGuard(paramPlayer, localArrayList);
/*     */   }
/*     */   
/*     */   public void renderGlassFaction(Player paramPlayer, Location paramLocation, Claim paramClaim) {
/* 130 */     if (paramClaim == null) { return;
/*     */     }
/* 132 */     int i = closest(paramLocation.getBlockX(), new int[] { paramClaim.getCornerOne().getBlockX(), paramClaim.getCornerThree().getBlockX() });
/* 133 */     int j = closest(paramLocation.getBlockZ(), new int[] { paramClaim.getCornerOne().getBlockZ(), paramClaim.getCornerThree().getBlockZ() });
/*     */     
/* 135 */     int k = Math.abs(paramLocation.getX() - i) < 8.0D ? 1 : 0;
/* 136 */     int m = Math.abs(paramLocation.getZ() - j) < 8.0D ? 1 : 0;
/*     */     
/* 138 */     if ((k == 0) && (m == 0)) { return;
/*     */     }
/* 140 */     ArrayList localArrayList = new ArrayList();
/* 141 */     int n; int i1; Location localLocation; if (k != 0) {
/* 142 */       for (n = -4; n < 5; n++) {
/* 143 */         for (i1 = -7; i1 < 8; i1++) {
/* 144 */           if (isInside(paramClaim.getCornerOne().getBlockZ(), paramClaim.getCornerThree().getBlockZ(), paramLocation.getBlockZ() + i1)) {
/* 145 */             localLocation = new Location(paramLocation.getWorld(), Double.valueOf(i).doubleValue(), Double.valueOf(paramLocation.getBlockY() + n).doubleValue(), Double.valueOf(paramLocation.getBlockZ() + i1).doubleValue());
/* 146 */             if ((!localArrayList.contains(localLocation)) && (!localLocation.getBlock().getType().isOccluding())) {
/* 147 */               localArrayList.add(localLocation);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 153 */     if (m != 0) {
/* 154 */       for (n = -4; n < 5; n++) {
/* 155 */         for (i1 = -7; i1 < 8; i1++) {
/* 156 */           if (isInside(paramClaim.getCornerOne().getBlockX(), paramClaim.getCornerThree().getBlockX(), paramLocation.getBlockX() + i1)) {
/* 157 */             localLocation = new Location(paramLocation.getWorld(), Double.valueOf(paramLocation.getBlockX() + i1).doubleValue(), Double.valueOf(paramLocation.getBlockY() + n).doubleValue(), Double.valueOf(j).doubleValue());
/* 158 */             if ((!localArrayList.contains(localLocation)) && (!localLocation.getBlock().getType().isOccluding())) {
/* 159 */               localArrayList.add(localLocation);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 165 */     updateFaction(paramPlayer, localArrayList);
/*     */   }
/*     */   
/*     */   public int closest(int paramInt, int... paramVarArgs) {
/* 169 */     int i = paramVarArgs[0];
/* 170 */     for (int j = 0; j < paramVarArgs.length; j++) {
/* 171 */       if (Math.abs(paramInt - paramVarArgs[j]) < Math.abs(paramInt - i)) {
/* 172 */         i = paramVarArgs[j];
/*     */       }
/*     */     }
/* 175 */     return i;
/*     */   }
/*     */   
/*     */ 
/* 179 */   public boolean isInside(int paramInt1, int paramInt2, int paramInt3) { return Math.abs(paramInt1 - paramInt2) == Math.abs(paramInt3 - paramInt1) + Math.abs(paramInt3 - paramInt2); }
/*     */   
/*     */   public void updateWorldGuard(Player paramPlayer, List<Location> paramList) {
/*     */     Iterator localIterator;
/*     */     Location localLocation;
/* 184 */     if (this.locationsWorldGuard.containsKey(paramPlayer.getUniqueId())) {
/* 185 */       for (localIterator = ((List)this.locationsWorldGuard.get(paramPlayer.getUniqueId())).iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 186 */         if (!paramList.contains(localLocation)) {
/* 187 */           Block localBlock = localLocation.getBlock();
/* 188 */           paramPlayer.sendBlockChange(localLocation, localBlock.getTypeId(), localBlock.getData());
/*     */         }
/*     */       }
/* 191 */       for (localIterator = paramList.iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 192 */         paramPlayer.sendBlockChange(localLocation, 95, (byte)15);
/*     */       }
/*     */     }
/*     */     else {
/* 196 */       for (localIterator = paramList.iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 197 */         paramPlayer.sendBlockChange(localLocation, 95, (byte)15);
/*     */       }
/*     */     }
/* 200 */     this.locationsWorldGuard.put(paramPlayer.getUniqueId(), paramList);
/*     */   }
/*     */   
/*     */   public void updateFaction(Player paramPlayer, List<Location> paramList) { Iterator localIterator;
/*     */     Location localLocation;
/* 205 */     if (this.locationsFaction.containsKey(paramPlayer.getUniqueId())) {
/* 206 */       ((List)this.locationsFaction.get(paramPlayer.getUniqueId())).addAll(paramList);
/* 207 */       for (localIterator = paramList.iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 208 */         paramPlayer.sendBlockChange(localLocation, 95, (byte)15);
/*     */       }
/*     */     } else {
/* 211 */       for (localIterator = paramList.iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 212 */         paramPlayer.sendBlockChange(localLocation, 95, (byte)15);
/*     */       }
/* 214 */       this.locationsFaction.put(paramPlayer.getUniqueId(), paramList);
/*     */     } }
/*     */   
/*     */   public void removeGlass(Player paramPlayer) { Iterator localIterator;
/*     */     Location localLocation;
/*     */     Block localBlock;
/* 220 */     if (this.locationsWorldGuard.containsKey(paramPlayer.getUniqueId())) {
/* 221 */       for (localIterator = ((List)this.locationsWorldGuard.get(paramPlayer.getUniqueId())).iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 222 */         localBlock = localLocation.getBlock();
/* 223 */         paramPlayer.sendBlockChange(localLocation, localBlock.getTypeId(), localBlock.getData());
/*     */       }
/* 225 */       this.locationsWorldGuard.remove(paramPlayer.getUniqueId());
/*     */     }
/* 227 */     if (this.locationsFaction.containsKey(paramPlayer.getUniqueId())) {
/* 228 */       for (localIterator = ((List)this.locationsFaction.get(paramPlayer.getUniqueId())).iterator(); localIterator.hasNext();) { localLocation = (Location)localIterator.next();
/* 229 */         localBlock = localLocation.getBlock();
/* 230 */         paramPlayer.sendBlockChange(localLocation, localBlock.getTypeId(), localBlock.getData());
/*     */       }
/* 232 */       this.locationsFaction.remove(paramPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\GlassHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */