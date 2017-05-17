/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class CobbleHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   private ArrayList<UUID> cobblePlayers;
/*    */   
/*    */   public CobbleHandler(Notorious paramNotorious)
/*    */   {
/* 21 */     super(paramNotorious);
/* 22 */     this.cobblePlayers = new ArrayList();
/*    */     
/* 24 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void disable() {
/* 28 */     this.cobblePlayers.clear();
/*    */   }
/*    */   
/*    */   public ArrayList<UUID> getCobblePlayers() {
/* 32 */     return this.cobblePlayers;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerPickupItem(PlayerPickupItemEvent paramPlayerPickupItemEvent) {
/* 37 */     Player localPlayer = paramPlayerPickupItemEvent.getPlayer();
/* 38 */     Item localItem = paramPlayerPickupItemEvent.getItem();
/*    */     
/* 40 */     if ((localItem.getItemStack().getType().equals(Material.COBBLESTONE)) && 
/* 41 */       (this.cobblePlayers.contains(localPlayer.getUniqueId()))) {
/* 42 */       paramPlayerPickupItemEvent.setCancelled(true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\CobbleHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */