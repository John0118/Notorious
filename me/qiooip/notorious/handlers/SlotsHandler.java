/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.player.PlayerLoginEvent;
/*    */ import org.bukkit.event.player.PlayerLoginEvent.Result;
/*    */ import org.bukkit.event.server.ServerListPingEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class SlotsHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   private int maxSlots;
/*    */   
/*    */   public SlotsHandler(Notorious paramNotorious)
/*    */   {
/* 20 */     super(paramNotorious);
/*    */     
/* 22 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/* 23 */     this.maxSlots = getInstance().getConfigHandler().getSlotsDefaultAmount();
/*    */   }
/*    */   
/*    */   public int getMaxSlots() {
/* 27 */     return this.maxSlots;
/*    */   }
/*    */   
/*    */   public void setMaxSlots(int paramInt) {
/* 31 */     this.maxSlots = paramInt;
/*    */   }
/*    */   
/*    */   @EventHandler(priority=EventPriority.MONITOR)
/*    */   public void onPlayerLogin(PlayerLoginEvent paramPlayerLoginEvent)
/*    */   {
/* 37 */     Player localPlayer = paramPlayerLoginEvent.getPlayer();
/*    */     
/* 39 */     int i = org.bukkit.Bukkit.getOnlinePlayers().length;
/*    */     
/* 41 */     if (i >= this.maxSlots) {
/* 42 */       if (localPlayer.hasPermission("notorious.joinfullserver")) {
/* 43 */         paramPlayerLoginEvent.allow();
/*    */       } else {
/* 45 */         paramPlayerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_FULL, Language.JOIN_FULL_SERVER_MESSAGE.toString());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onServerListPing(ServerListPingEvent paramServerListPingEvent) {
/* 52 */     paramServerListPingEvent.setMaxPlayers(getMaxSlots());
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\SlotsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */