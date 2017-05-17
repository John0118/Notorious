/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class BlockedCommandsHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public BlockedCommandsHandler(Notorious paramNotorious)
/*    */   {
/* 15 */     super(paramNotorious);
/*    */     
/* 17 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent paramPlayerCommandPreprocessEvent) {
/* 22 */     Player localPlayer = paramPlayerCommandPreprocessEvent.getPlayer();
/* 23 */     if (!localPlayer.isOp()) {
/* 24 */       for (String str : getInstance().getConfigHandler().getBlockedCommands()) {
/* 25 */         if (paramPlayerCommandPreprocessEvent.getMessage().startsWith("/" + str)) {
/* 26 */           paramPlayerCommandPreprocessEvent.setCancelled(true);
/* 27 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.COMMANDS_FORBIDDEN_COMMAND_MESSAGE.toString());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\BlockedCommandsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */