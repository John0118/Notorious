/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class RequestHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   private HashMap<UUID, Long> requestCooldowns;
/*    */   
/*    */   public RequestHandler(Notorious paramNotorious)
/*    */   {
/* 17 */     super(paramNotorious);
/* 18 */     this.requestCooldowns = new HashMap();
/*    */     
/* 20 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void disable() {
/* 24 */     this.requestCooldowns.clear();
/*    */   }
/*    */   
/*    */   public void applyCooldown(Player paramPlayer) {
/* 28 */     this.requestCooldowns.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getRequestCommandDelay() * 1000));
/*    */   }
/*    */   
/*    */   public boolean isActive(Player paramPlayer) {
/* 32 */     return (this.requestCooldowns.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.requestCooldowns.get(paramPlayer.getUniqueId())).longValue());
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(Player paramPlayer) {
/* 36 */     if (this.requestCooldowns.containsKey(paramPlayer.getUniqueId())) {
/* 37 */       return Math.max(((Long)this.requestCooldowns.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*    */     }
/* 39 */     return 0L;
/*    */   }
/*    */   
/*    */   public HashMap<UUID, Long> getRequestCooldowns() {
/* 43 */     return this.requestCooldowns;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\RequestHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */