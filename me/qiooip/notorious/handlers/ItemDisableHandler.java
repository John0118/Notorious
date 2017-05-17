/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class ItemDisableHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public ItemDisableHandler(Notorious paramNotorious)
/*    */   {
/* 18 */     super(paramNotorious);
/*    */     
/* 20 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 25 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 26 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 27 */     Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/*    */     
/* 29 */     if ((localAction.equals(Action.RIGHT_CLICK_BLOCK)) && 
/* 30 */       (localBlock.getType().equals(Material.ENDER_CHEST))) {
/* 31 */       paramPlayerInteractEvent.setCancelled(true);
/* 32 */       localPlayer.sendMessage(Language.PREFIX.toString() + Language.ITEM_DISABLE_MESSAGE.toString().replace("<block>", localBlock.getType().name().replace("_", " ")));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\ItemDisableHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */