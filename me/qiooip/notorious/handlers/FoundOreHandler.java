/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class FoundOreHandler extends Handler implements Listener
/*    */ {
/*    */   private List<java.util.UUID> players;
/*    */   
/*    */   public FoundOreHandler(Notorious paramNotorious)
/*    */   {
/* 28 */     super(paramNotorious);
/* 29 */     this.players = new ArrayList();
/*    */     
/* 31 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void toggleAlerts(Player paramPlayer) {
/* 35 */     if (this.players.contains(paramPlayer.getUniqueId())) {
/* 36 */       this.players.remove(paramPlayer.getUniqueId());
/*    */       
/* 38 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.FOUND_ORE_RECIEVING_ENABLED.toString());
/*    */     } else {
/* 40 */       this.players.add(paramPlayer.getUniqueId());
/*    */       
/* 42 */       paramPlayer.sendMessage(Language.PREFIX.toString() + Language.FOUND_ORE_RECIEVING_DISABLED.toString());
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockPlace(BlockPlaceEvent paramBlockPlaceEvent) {
/* 48 */     Block localBlock = paramBlockPlaceEvent.getBlock();
/* 49 */     for (Material localMaterial : getInstance().getConfigHandler().getFoundOreMaterials()) {
/* 50 */       if (localBlock.getType().equals(localMaterial)) {
/* 51 */         localBlock.setMetadata("FoundOre", new FixedMetadataValue(getInstance(), Boolean.TRUE));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockBreak(BlockBreakEvent paramBlockBreakEvent)
/*    */   {
/* 59 */     Player localPlayer = paramBlockBreakEvent.getPlayer();
/* 60 */     Block localBlock1 = paramBlockBreakEvent.getBlock();
/*    */     
/* 62 */     if (getInstance().getChatControlHandler().isMuted()) return;
/* 63 */     if (localPlayer.getGameMode() == org.bukkit.GameMode.CREATIVE) return;
/*    */     int m;
/* 65 */     int n; label337: for (Iterator localIterator = getInstance().getConfigHandler().getFoundOreMaterials().iterator(); localIterator.hasNext(); 
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 81 */         m < n)
/*    */     {
/* 65 */       Material localMaterial = (Material)localIterator.next();
/* 66 */       if ((!localBlock1.getType().equals(localMaterial)) || (localBlock1.hasMetadata("FoundOre"))) break label337;
/* 67 */       int i = 0;
/* 68 */       for (int j = -3; j < 4; j++) {
/* 69 */         for (int k = -3; k < 4; k++) {
/* 70 */           for (m = -3; m < 4; m++) {
/* 71 */             Block localBlock2 = localBlock1.getRelative(j, k, m);
/* 72 */             if ((localBlock2.getType().equals(localMaterial)) && (!localBlock2.hasMetadata("FoundOre"))) {
/* 73 */               i++;
/* 74 */               localBlock2.setMetadata("FoundOre", new FixedMetadataValue(getInstance(), Boolean.TRUE));
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/* 79 */       String str = localBlock1.getType().name().toLowerCase().replace("_", " ");
/*    */       Player[] arrayOfPlayer;
/* 81 */       n = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length;m = 0; continue;Object localObject = arrayOfPlayer[m];
/* 82 */       if ((((Player)localObject).hasPermission("notorious.foundore.recieve")) && (!this.players.contains(((Player)localObject).getUniqueId()))) {
/* 83 */         ((Player)localObject).sendMessage(Language.FOUND_ORE_MESSAGE.toString().replace("<player>", localPlayer.getName()).replace("<count>", 
/* 84 */           String.valueOf(i)).replace("<material>", str));
/*    */       }
/* 81 */       m++;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent)
/*    */   {
/* 93 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*    */     
/* 95 */     if (this.players.contains(localPlayer.getUniqueId())) {
/* 96 */       this.players.remove(localPlayer.getUniqueId());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\FoundOreHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */