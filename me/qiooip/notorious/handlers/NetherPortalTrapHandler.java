/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.integration.WorldGuard;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.World.Environment;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class NetherPortalTrapHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public NetherPortalTrapHandler(Notorious paramNotorious)
/*    */   {
/* 20 */     super(paramNotorious);
/*    */     
/* 22 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 27 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/* 28 */     Action localAction = paramPlayerInteractEvent.getAction();
/*    */     
/* 30 */     if (localAction == Action.RIGHT_CLICK_BLOCK) {
/* 31 */       Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/* 32 */       Location localLocation1 = localPlayer.getLocation();
/* 33 */       Location localLocation2 = localPlayer.getEyeLocation();
/*    */       
/* 35 */       if ((localBlock.getWorld().getEnvironment() == World.Environment.NETHER) && 
/* 36 */         (localLocation1.getBlock().getType() == Material.PORTAL) && (localLocation2.getBlock().getType() == Material.PORTAL) && 
/* 37 */         (localBlock.getType() == Material.PORTAL) && 
/* 38 */         (getInstance().getConfigHandler().isNetherPortalTrapFixEnabled()) && 
/* 39 */         (WorldGuard.getProtectedRegion(localBlock.getLocation()) == null)) {
/* 40 */         localBlock.setType(Material.AIR);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\NetherPortalTrapHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */