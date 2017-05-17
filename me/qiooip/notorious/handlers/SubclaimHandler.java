/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Chest;
/*     */ import org.bukkit.block.DoubleChest;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.material.Attachable;
/*     */ import org.zencode.mango.Mango;
/*     */ import org.zencode.mango.factions.Faction;
/*     */ import org.zencode.mango.factions.FactionManager;
/*     */ import org.zencode.mango.factions.claims.Claim;
/*     */ import org.zencode.mango.factions.claims.ClaimManager;
/*     */ import org.zencode.mango.factions.types.PlayerFaction;
/*     */ 
/*     */ public class SubclaimHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private BlockFace[] SIGN_FACES;
/*     */   
/*     */   public SubclaimHandler(Notorious paramNotorious)
/*     */   {
/*  40 */     super(paramNotorious);
/*  41 */     this.SIGN_FACES = new BlockFace[] { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP };
/*     */     
/*  43 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public boolean isSubclaimable(Block paramBlock) {
/*  47 */     return (paramBlock.getType() == Material.CHEST) || (paramBlock.getType() == Material.TRAPPED_CHEST);
/*     */   }
/*     */   
/*     */   public boolean isSubclaim(Block paramBlock) {
/*  51 */     for (int i = 0; i < this.SIGN_FACES.length; i++) {
/*  52 */       BlockFace localBlockFace = this.SIGN_FACES[i];
/*  53 */       if ((paramBlock.getRelative(localBlockFace).getType() == Material.WALL_SIGN) && ((paramBlock.getRelative(localBlockFace).getState() instanceof Sign))) {
/*  54 */         Sign localSign = (Sign)paramBlock.getRelative(localBlockFace).getState();
/*  55 */         return localSign.getLine(0).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimSignTitle());
/*     */       }
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDoubleSubclaim(Block paramBlock) {
/*  62 */     if (isSubclaimable(paramBlock)) {
/*  63 */       for (int i = 0; i < this.SIGN_FACES.length; i++) {
/*  64 */         BlockFace localBlockFace = this.SIGN_FACES[i];
/*  65 */         if (isSubclaimable(paramBlock.getRelative(localBlockFace))) {
/*  66 */           Block localBlock = paramBlock.getRelative(localBlockFace);
/*  67 */           if (isSubclaim(localBlock)) return true;
/*     */         }
/*     */       }
/*     */     }
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public Block getDoubleChest(Block paramBlock) {
/*  75 */     if (isSubclaimable(paramBlock)) {
/*  76 */       for (int i = 0; i < this.SIGN_FACES.length; i++) {
/*  77 */         BlockFace localBlockFace = this.SIGN_FACES[i];
/*  78 */         if (isSubclaimable(paramBlock.getRelative(localBlockFace))) {
/*  79 */           Block localBlock = paramBlock.getRelative(localBlockFace);
/*  80 */           if (isSubclaim(localBlock)) return localBlock;
/*     */         }
/*     */       }
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public Sign getSign(Block paramBlock) {
/*  88 */     for (int i = 0; i < this.SIGN_FACES.length; i++) {
/*  89 */       BlockFace localBlockFace = this.SIGN_FACES[i];
/*  90 */       if ((paramBlock.getRelative(localBlockFace).getType() == Material.WALL_SIGN) && ((paramBlock.getRelative(localBlockFace).getState() instanceof Sign))) {
/*  91 */         Sign localSign = (Sign)paramBlock.getRelative(localBlockFace).getState();
/*  92 */         return localSign;
/*     */       }
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */   
/*     */   private Block getBlockAttachedTo(Block paramBlock) {
/*  99 */     org.bukkit.material.MaterialData localMaterialData = paramBlock.getState().getData();
/* 100 */     BlockFace localBlockFace = BlockFace.DOWN;
/* 101 */     if ((localMaterialData instanceof Attachable)) {
/* 102 */       localBlockFace = ((Attachable)localMaterialData).getAttachedFace();
/*     */     }
/* 104 */     return paramBlock.getRelative(localBlockFace);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSignChange(SignChangeEvent paramSignChangeEvent) {
/* 109 */     Player localPlayer1 = paramSignChangeEvent.getPlayer();
/* 110 */     Block localBlock = getBlockAttachedTo(paramSignChangeEvent.getBlock());
/* 111 */     String[] arrayOfString = paramSignChangeEvent.getLines();
/*     */     
/* 113 */     if ((isSubclaimable(localBlock)) && 
/* 114 */       (StringUtils.containsIgnoreCase(arrayOfString[0], "subclaim"))) {
/* 115 */       PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer1);
/* 116 */       Faction localFaction = null;
/* 117 */       if (Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()) != null) {
/* 118 */         if ((Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner() instanceof PlayerFaction)) {
/* 119 */           localFaction = Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner();
/*     */         } else {
/* 121 */           getSign(localBlock).getBlock().breakNaturally();
/*     */         }
/*     */       }
/* 124 */       if ((localFaction == null) || (localPlayerFaction != localFaction)) {
/* 125 */         paramSignChangeEvent.setCancelled(true);
/* 126 */         localPlayer1.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_NOT_IN_OWN_CLAIM.toString());
/* 127 */         paramSignChangeEvent.getBlock().breakNaturally();
/* 128 */       } else if (isSubclaim(localBlock)) {
/* 129 */         paramSignChangeEvent.setCancelled(true);
/* 130 */         localPlayer1.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_ALREADY_EXISTS.toString().replace("<block>", localBlock.getType().name().replace("_", " ")));
/* 131 */         paramSignChangeEvent.getBlock().breakNaturally();
/*     */       }
/* 133 */       else if (!paramSignChangeEvent.isCancelled()) { Iterator localIterator;
/* 134 */         Object localObject1; if ((paramSignChangeEvent.getLine(1).equalsIgnoreCase("Leader")) || (paramSignChangeEvent.getLine(1).equalsIgnoreCase("Admin"))) {
/* 135 */           if (!localPlayerFaction.getLeader().equals(localPlayer1.getUniqueId())) {
/* 136 */             paramSignChangeEvent.setCancelled(true);
/* 137 */             paramSignChangeEvent.getBlock().breakNaturally();
/*     */           } else {
/* 139 */             paramSignChangeEvent.setLine(0, getInstance().getConfigHandler().getSubclaimSignTitle());
/* 140 */             paramSignChangeEvent.setLine(1, getInstance().getConfigHandler().getSubclaimLeaderOnly());
/*     */             
/* 142 */             for (localIterator = localPlayerFaction.getOnlinePlayers().iterator(); localIterator.hasNext();) { localObject1 = (Player)localIterator.next();
/* 143 */               ((Player)localObject1).sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_SUCCESSFULLY_CREATED.toString().replace("<player>", localPlayer1.getName()).replace("<block>", 
/* 144 */                 localBlock.getType().name().replace("_", " ")).replace("<x>", String.valueOf(localBlock.getLocation().getBlockX())).replace("<y>", 
/* 145 */                 String.valueOf(localBlock.getLocation().getBlockY())).replace("<z>", String.valueOf(localBlock.getLocation().getBlockZ())));
/*     */             }
/*     */           }
/* 148 */         } else if ((paramSignChangeEvent.getLine(1).equalsIgnoreCase("Captain")) || (paramSignChangeEvent.getLine(1).equalsIgnoreCase("Mod"))) {
/* 149 */           if ((!localPlayerFaction.getOfficers().contains(localPlayer1.getUniqueId())) && (!localPlayerFaction.getLeader().equals(localPlayer1.getUniqueId()))) {
/* 150 */             paramSignChangeEvent.setCancelled(true);
/* 151 */             paramSignChangeEvent.getBlock().breakNaturally();
/*     */           } else {
/* 153 */             paramSignChangeEvent.setLine(0, getInstance().getConfigHandler().getSubclaimSignTitle());
/* 154 */             paramSignChangeEvent.setLine(1, getInstance().getConfigHandler().getSubclaimCaptainsOnly());
/*     */             
/* 156 */             for (localIterator = localPlayerFaction.getOnlinePlayers().iterator(); localIterator.hasNext();) { localObject1 = (Player)localIterator.next();
/* 157 */               ((Player)localObject1).sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_SUCCESSFULLY_CREATED.toString().replace("<player>", localPlayer1.getName()).replace("<block>", 
/* 158 */                 localBlock.getType().name().replace("_", " ")).replace("<x>", String.valueOf(localBlock.getLocation().getBlockX())).replace("<y>", 
/* 159 */                 String.valueOf(localBlock.getLocation().getBlockY())).replace("<z>", String.valueOf(localBlock.getLocation().getBlockZ())));
/*     */             }
/*     */           }
/*     */         }
/* 163 */         else if ((!localPlayerFaction.getOfficers().contains(localPlayer1.getUniqueId())) && (!localPlayerFaction.getLeader().equals(localPlayer1.getUniqueId()))) {
/* 164 */           paramSignChangeEvent.setCancelled(true);
/* 165 */           paramSignChangeEvent.getBlock().breakNaturally();
/*     */         } else {
/* 167 */           localObject1 = new ArrayList(3);
/* 168 */           for (int i = 1; i < arrayOfString.length; i++) {
/* 169 */             localObject2 = arrayOfString[i];
/* 170 */             if (StringUtils.isNotBlank((String)localObject2)) {
/* 171 */               ((List)localObject1).add(localObject2);
/*     */             }
/*     */           }
/* 174 */           paramSignChangeEvent.setLine(0, getInstance().getConfigHandler().getSubclaimSignTitle());
/* 175 */           if (((List)localObject1).size() == 1) {
/* 176 */             paramSignChangeEvent.setLine(1, (String)((List)localObject1).get(0));
/*     */           }
/* 178 */           if (((List)localObject1).size() == 2) {
/* 179 */             paramSignChangeEvent.setLine(1, (String)((List)localObject1).get(0));
/* 180 */             paramSignChangeEvent.setLine(2, (String)((List)localObject1).get(1));
/*     */           }
/* 182 */           if (((List)localObject1).size() == 3) {
/* 183 */             paramSignChangeEvent.setLine(1, (String)((List)localObject1).get(0));
/* 184 */             paramSignChangeEvent.setLine(2, (String)((List)localObject1).get(1));
/* 185 */             paramSignChangeEvent.setLine(3, (String)((List)localObject1).get(2));
/*     */           }
/* 187 */           if (((List)localObject1).isEmpty()) {
/* 188 */             paramSignChangeEvent.setLine(1, localPlayer1.getName());
/*     */           }
/* 190 */           for (Object localObject2 = localPlayerFaction.getOnlinePlayers().iterator(); ((Iterator)localObject2).hasNext();) { Player localPlayer2 = (Player)((Iterator)localObject2).next();
/* 191 */             localPlayer2.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_SUCCESSFULLY_CREATED.toString().replace("<player>", localPlayer1.getName()).replace("<block>", 
/* 192 */               localBlock.getType().name().replace("_", " ")).replace("<x>", String.valueOf(localBlock.getLocation().getBlockX())).replace("<y>", 
/* 193 */               String.valueOf(localBlock.getLocation().getBlockY())).replace("<z>", String.valueOf(localBlock.getLocation().getBlockZ())));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent)
/*     */   {
/* 205 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 206 */     Block localBlock = paramBlockBreakEvent.getBlock();
/*     */     
/* 208 */     if (localPlayer.hasPermission("notorious.subclaim.bypass")) { return;
/*     */     }
/* 210 */     if (isSubclaimable(localBlock)) { Object localObject1;
/* 211 */       Object localObject2; if (isSubclaim(localBlock)) {
/* 212 */         localObject1 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/* 213 */         localObject2 = null;
/* 214 */         if (Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()) != null) {
/* 215 */           if ((Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner() instanceof PlayerFaction)) {
/* 216 */             localObject2 = Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner();
/*     */           } else {
/* 218 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/*     */         }
/* 221 */         if (((PlayerFaction)localObject2).isRaidable()) {
/* 222 */           paramBlockBreakEvent.setCancelled(false);
/* 223 */         } else if (localObject1 != localObject2) {
/* 224 */           paramBlockBreakEvent.setCancelled(true);
/* 225 */         } else if (getSign(localBlock).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimLeaderOnly())) {
/* 226 */           if (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId())) {
/* 227 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 228 */               localBlock.getType().name().replace("_", " ")));
/* 229 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 231 */         } else if (getSign(localBlock).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimCaptainsOnly())) {
/* 232 */           if ((!((PlayerFaction)localObject1).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId()))) {
/* 233 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 234 */               localBlock.getType().name().replace("_", " ")));
/* 235 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 237 */         } else if ((!((PlayerFaction)localObject1).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId()))) {
/* 238 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 239 */             localBlock.getType().name().replace("_", " ")));
/* 240 */           paramBlockBreakEvent.setCancelled(true);
/*     */         }
/* 242 */       } else if (isDoubleSubclaim(localBlock)) {
/* 243 */         localObject1 = getDoubleChest(localBlock);
/* 244 */         localObject2 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/* 245 */         Faction localFaction = null;
/* 246 */         if (Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()) != null) {
/* 247 */           if ((Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()).getOwner() instanceof PlayerFaction)) {
/* 248 */             localFaction = Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()).getOwner();
/*     */           } else {
/* 250 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/*     */         }
/* 253 */         if (((PlayerFaction)localFaction).isRaidable()) {
/* 254 */           paramBlockBreakEvent.setCancelled(false);
/* 255 */         } else if (localObject2 != localFaction) {
/* 256 */           paramBlockBreakEvent.setCancelled(true);
/* 257 */         } else if (getSign((Block)localObject1).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimLeaderOnly())) {
/* 258 */           if (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId())) {
/* 259 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 260 */               localBlock.getType().name().replace("_", " ")));
/* 261 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 263 */         } else if (getSign((Block)localObject1).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimCaptainsOnly())) {
/* 264 */           if ((!((PlayerFaction)localObject2).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId()))) {
/* 265 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 266 */               localBlock.getType().name().replace("_", " ")));
/* 267 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 269 */         } else if ((!((PlayerFaction)localObject2).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId()))) {
/* 270 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM.toString().replace("<block>", 
/* 271 */             ((Block)localObject1).getType().name().replace("_", " ")));
/* 272 */           paramBlockBreakEvent.setCancelled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSignBreakEvent(BlockBreakEvent paramBlockBreakEvent) {
/* 280 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 281 */     Block localBlock = paramBlockBreakEvent.getBlock();
/*     */     
/* 283 */     if (localPlayer.hasPermission("notorious.subclaim.bypass")) { return;
/*     */     }
/* 285 */     if ((localBlock.getType() == Material.SIGN) || ((localBlock.getType() == Material.WALL_SIGN) && ((localBlock.getState() instanceof Sign)))) {
/* 286 */       Sign localSign = (Sign)localBlock.getState();
/* 287 */       PlayerFaction localPlayerFaction = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/* 288 */       Faction localFaction = null;
/* 289 */       if (Mango.getInstance().getClaimManager().getClaimAt(localSign.getLocation()) != null) {
/* 290 */         if ((Mango.getInstance().getClaimManager().getClaimAt(localSign.getLocation()).getOwner() instanceof PlayerFaction)) {
/* 291 */           localFaction = Mango.getInstance().getClaimManager().getClaimAt(localSign.getLocation()).getOwner();
/*     */         } else {
/* 293 */           paramBlockBreakEvent.setCancelled(true);
/*     */         }
/*     */       }
/* 296 */       if (localSign.getLine(0).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimSignTitle())) {
/* 297 */         if (((PlayerFaction)localFaction).isRaidable()) {
/* 298 */           paramBlockBreakEvent.setCancelled(false);
/* 299 */         } else if (localPlayerFaction != localFaction) {
/* 300 */           paramBlockBreakEvent.setCancelled(true);
/* 301 */         } else if (localSign.getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimLeaderOnly())) {
/* 302 */           if (!localPlayerFaction.getLeader().equals(localPlayer.getUniqueId())) {
/* 303 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SIGN.toString());
/* 304 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 306 */         } else if (localSign.getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimCaptainsOnly())) {
/* 307 */           if ((!localPlayerFaction.getOfficers().contains(localPlayer.getUniqueId())) && (!localPlayerFaction.getLeader().equals(localPlayer.getUniqueId()))) {
/* 308 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SIGN.toString());
/* 309 */             paramBlockBreakEvent.setCancelled(true);
/*     */           }
/* 311 */         } else if ((!localPlayerFaction.getOfficers().contains(localPlayer.getUniqueId())) && (!localPlayerFaction.getLeader().equals(localPlayer.getUniqueId()))) {
/* 312 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_DESTROY_SIGN.toString());
/* 313 */           paramBlockBreakEvent.setCancelled(true);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 321 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/*     */     
/* 323 */     if (localPlayer.hasPermission("notorious.subclaim.bypass")) { return;
/*     */     }
/* 325 */     if (paramPlayerInteractEvent.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
/* 326 */       Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/* 327 */       if (isSubclaimable(localBlock)) { Object localObject1;
/* 328 */         Object localObject2; if (isSubclaim(localBlock)) {
/* 329 */           localObject1 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/* 330 */           localObject2 = null;
/* 331 */           if (Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()) != null) {
/* 332 */             if ((Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner() instanceof PlayerFaction)) {
/* 333 */               localObject2 = Mango.getInstance().getClaimManager().getClaimAt(localBlock.getLocation()).getOwner();
/*     */             } else {
/* 335 */               getSign(localBlock).getBlock().breakNaturally();
/*     */             }
/*     */           }
/* 338 */           if (((PlayerFaction)localObject2).isRaidable()) {
/* 339 */             paramPlayerInteractEvent.setCancelled(false);
/* 340 */           } else if (localObject1 != localObject2) {
/* 341 */             paramPlayerInteractEvent.setCancelled(true);
/* 342 */           } else if (getSign(localBlock).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimLeaderOnly())) {
/* 343 */             if (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId())) {
/* 344 */               paramPlayerInteractEvent.setCancelled(true);
/* 345 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 346 */                 localBlock.getType().name().replace("_", " ")));
/*     */             }
/* 348 */           } else if (getSign(localBlock).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimCaptainsOnly())) {
/* 349 */             if ((!((PlayerFaction)localObject1).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId()))) {
/* 350 */               paramPlayerInteractEvent.setCancelled(true);
/* 351 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 352 */                 localBlock.getType().name().replace("_", " ")));
/*     */             }
/* 354 */           } else if ((!getSign(localBlock).getLine(1).equalsIgnoreCase(localPlayer.getName())) && (!getSign(localBlock).getLine(2).equalsIgnoreCase(localPlayer.getName())) && (!getSign(localBlock).getLine(3).equalsIgnoreCase(localPlayer.getName())) && 
/* 355 */             (!((PlayerFaction)localObject1).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject1).getLeader().equals(localPlayer.getUniqueId()))) {
/* 356 */             paramPlayerInteractEvent.setCancelled(true);
/* 357 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 358 */               localBlock.getType().name().replace("_", " ")));
/*     */           }
/*     */         }
/* 361 */         else if (isDoubleSubclaim(localBlock)) {
/* 362 */           localObject1 = getDoubleChest(localBlock);
/* 363 */           localObject2 = Mango.getInstance().getFactionManager().getFaction(localPlayer);
/* 364 */           Faction localFaction = null;
/* 365 */           if (Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()) != null) {
/* 366 */             if ((Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()).getOwner() instanceof PlayerFaction)) {
/* 367 */               localFaction = Mango.getInstance().getClaimManager().getClaimAt(((Block)localObject1).getLocation()).getOwner();
/*     */             } else {
/* 369 */               getSign((Block)localObject1).getBlock().breakNaturally();
/*     */             }
/*     */           }
/* 372 */           if (((PlayerFaction)localFaction).isRaidable()) {
/* 373 */             paramPlayerInteractEvent.setCancelled(false);
/* 374 */           } else if (localObject2 != localFaction) {
/* 375 */             paramPlayerInteractEvent.setCancelled(true);
/* 376 */           } else if (getSign((Block)localObject1).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimLeaderOnly())) {
/* 377 */             if (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId())) {
/* 378 */               paramPlayerInteractEvent.setCancelled(true);
/* 379 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 380 */                 localBlock.getType().name().replace("_", " ")));
/*     */             }
/* 382 */           } else if (getSign((Block)localObject1).getLine(1).equalsIgnoreCase(getInstance().getConfigHandler().getSubclaimCaptainsOnly())) {
/* 383 */             if ((!((PlayerFaction)localObject2).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId()))) {
/* 384 */               paramPlayerInteractEvent.setCancelled(true);
/* 385 */               localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 386 */                 localBlock.getType().name().replace("_", " ")));
/*     */             }
/* 388 */           } else if ((!getSign((Block)localObject1).getLine(1).equalsIgnoreCase(localPlayer.getName())) && (!getSign((Block)localObject1).getLine(2).equalsIgnoreCase(localPlayer.getName())) && (!getSign((Block)localObject1).getLine(3).equalsIgnoreCase(localPlayer.getName())) && 
/* 389 */             (!((PlayerFaction)localObject2).getOfficers().contains(localPlayer.getUniqueId())) && (!((PlayerFaction)localObject2).getLeader().equals(localPlayer.getUniqueId()))) {
/* 390 */             paramPlayerInteractEvent.setCancelled(true);
/* 391 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.SUBCLAIM_CAN_NOT_OPEN.toString().replace("<block>", 
/* 392 */               localBlock.getType().name().replace("_", " ")));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onInventoryMoveItem(InventoryMoveItemEvent paramInventoryMoveItemEvent)
/*     */   {
/* 402 */     Inventory localInventory1 = paramInventoryMoveItemEvent.getSource();
/* 403 */     Inventory localInventory2 = paramInventoryMoveItemEvent.getDestination();
/* 404 */     org.bukkit.inventory.InventoryHolder localInventoryHolder = localInventory1.getHolder();
/* 405 */     Location localLocation = null;
/*     */     
/* 407 */     if ((localInventoryHolder instanceof Chest)) {
/* 408 */       localLocation = ((Chest)localInventoryHolder).getLocation();
/* 409 */     } else if ((localInventoryHolder instanceof DoubleChest)) {
/* 410 */       localLocation = ((DoubleChest)localInventoryHolder).getLocation();
/*     */     }
/*     */     
/* 413 */     if ((localLocation != null) && 
/* 414 */       ((isSubclaim(localLocation.getBlock())) || (isDoubleSubclaim(localLocation.getBlock()))) && (localInventory2.getType() == org.bukkit.event.inventory.InventoryType.HOPPER)) paramInventoryMoveItemEvent.setCancelled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\SubclaimHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */