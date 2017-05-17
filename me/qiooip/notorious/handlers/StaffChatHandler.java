/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.event.player.PlayerKickEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class StaffChatHandler extends Handler implements Listener
/*    */ {
/*    */   private ArrayList<UUID> staff;
/*    */   
/*    */   public StaffChatHandler(Notorious paramNotorious)
/*    */   {
/* 23 */     super(paramNotorious);
/* 24 */     this.staff = new ArrayList();
/*    */     
/* 26 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public ArrayList<UUID> getStaff() {
/* 30 */     return this.staff;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onAsyncPlayerChat(AsyncPlayerChatEvent paramAsyncPlayerChatEvent) {
/* 35 */     Player localPlayer = paramAsyncPlayerChatEvent.getPlayer();
/* 36 */     if (this.staff.contains(localPlayer.getUniqueId())) {
/* 37 */       if (localPlayer.hasPermission("notorious.staffchat.use")) {
/* 38 */         paramAsyncPlayerChatEvent.setCancelled(true);
/* 39 */         org.bukkit.Bukkit.broadcast(Language.STAFF_CHAT_FORMAT.toString().replace("<player>", localPlayer.getName()).replace("<message>", paramAsyncPlayerChatEvent.getMessage()), "notorious.staffchat.receive");
/*    */       } else {
/* 41 */         this.staff.remove(localPlayer.getUniqueId());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuitEvent(PlayerQuitEvent paramPlayerQuitEvent) {
/* 48 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/* 49 */     if (this.staff.contains(localPlayer.getUniqueId())) {
/* 50 */       this.staff.remove(localPlayer.getUniqueId());
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerKickEvent(PlayerKickEvent paramPlayerKickEvent) {
/* 56 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/* 57 */     if (this.staff.contains(localPlayer.getUniqueId())) {
/* 58 */       this.staff.remove(localPlayer.getUniqueId());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\StaffChatHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */