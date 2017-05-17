/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class ChatControlHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   private boolean muted;
/*    */   private int delay;
/*    */   private HashMap<UUID, Long> chatCooldowns;
/*    */   
/*    */   public ChatControlHandler(Notorious paramNotorious)
/*    */   {
/* 23 */     super(paramNotorious);
/* 24 */     this.muted = false;
/* 25 */     this.delay = 0;
/* 26 */     this.chatCooldowns = new HashMap();
/*    */     
/* 28 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void disable() {
/* 32 */     this.chatCooldowns.clear();
/*    */   }
/*    */   
/*    */   public void applyCooldown(Player paramPlayer) {
/* 36 */     this.chatCooldowns.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + this.delay * 1000));
/*    */   }
/*    */   
/*    */   public boolean isActive(Player paramPlayer) {
/* 40 */     return (this.chatCooldowns.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.chatCooldowns.get(paramPlayer.getUniqueId())).longValue());
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(Player paramPlayer) {
/* 44 */     if (this.chatCooldowns.containsKey(paramPlayer.getUniqueId())) {
/* 45 */       return Math.max(((Long)this.chatCooldowns.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*    */     }
/* 47 */     return 0L;
/*    */   }
/*    */   
/*    */   public boolean isMuted() {
/* 51 */     return this.muted;
/*    */   }
/*    */   
/*    */   public void setMuted(boolean paramBoolean) {
/* 55 */     this.muted = paramBoolean;
/*    */   }
/*    */   
/*    */   public int getDelay() {
/* 59 */     return this.delay;
/*    */   }
/*    */   
/*    */   public void setDelay(int paramInt) {
/* 63 */     this.delay = paramInt;
/*    */   }
/*    */   
/*    */   public HashMap<UUID, Long> getChatCooldowns() {
/* 67 */     return this.chatCooldowns;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onAsyncPlayerChat(AsyncPlayerChatEvent paramAsyncPlayerChatEvent) {
/* 72 */     Player localPlayer = paramAsyncPlayerChatEvent.getPlayer();
/*    */     
/* 74 */     if ((this.muted) && 
/* 75 */       (!localPlayer.hasPermission("notorious.chatcontrol.mute.bypass"))) {
/* 76 */       paramAsyncPlayerChatEvent.setCancelled(true);
/* 77 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHATEVENT_MUTED_MESSAGE.toString());
/*    */     }
/*    */     
/* 80 */     if ((this.delay > 0) && 
/* 81 */       (!this.muted) && 
/* 82 */       (!localPlayer.hasPermission("notorious.chatcontrol.delay.bypass"))) {
/* 83 */       if (isActive(localPlayer)) {
/* 84 */         paramAsyncPlayerChatEvent.setCancelled(true);
/* 85 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.CHATCONTROL_CHATEVENT_DELAY_MESSAGE.toString().replace("<seconds>", 
/* 86 */           StringUtils.formatMilisecondsToSeconds(Long.valueOf(getMillisecondsLeft(localPlayer)))));
/*    */       } else {
/* 88 */         applyCooldown(localPlayer);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\ChatControlHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */