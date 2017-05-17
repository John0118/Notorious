/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.block.Furnace;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.inventory.FurnaceBurnEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class FurnaceSpeedHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public FurnaceSpeedHandler(Notorious paramNotorious)
/*    */   {
/* 20 */     super(paramNotorious);
/*    */     
/* 22 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   @EventHandler(priority=EventPriority.MONITOR)
/*    */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/* 27 */     Action localAction = paramPlayerInteractEvent.getAction();
/* 28 */     if (localAction == Action.RIGHT_CLICK_BLOCK) {
/* 29 */       Block localBlock = paramPlayerInteractEvent.getClickedBlock();
/* 30 */       BlockState localBlockState = localBlock.getState();
/* 31 */       if ((localBlockState instanceof Furnace)) {
/* 32 */         Furnace localFurnace = (Furnace)localBlockState;
/* 33 */         localFurnace.setCookTime((short)(localFurnace.getCookTime() + Notorious.getInstance().getConfigHandler().getFurnaceSpeedMultiplier()));
/* 34 */         localFurnace.setBurnTime((short)Math.max(1, localFurnace.getBurnTime() - 1));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onFurnaceBurn(FurnaceBurnEvent paramFurnaceBurnEvent) {
/* 41 */     BlockState localBlockState = paramFurnaceBurnEvent.getBlock().getState();
/* 42 */     if ((localBlockState instanceof Furnace)) {
/* 43 */       Furnace localFurnace = (Furnace)localBlockState;
/* 44 */       if (getInstance().getConfigHandler().getFurnaceSpeedMultiplier() > 1) {
/* 45 */         new FurnaceUpdateTask(localFurnace).runTaskTimer(getInstance(), 1L, 1L);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public class FurnaceUpdateTask extends org.bukkit.scheduler.BukkitRunnable
/*    */   {
/*    */     private Furnace furnace;
/*    */     
/*    */     public FurnaceUpdateTask(Furnace paramFurnace) {
/* 55 */       this.furnace = paramFurnace;
/*    */     }
/*    */     
/*    */     public void run() {
/* 59 */       this.furnace.setCookTime((short)(this.furnace.getCookTime() + Notorious.getInstance().getConfigHandler().getFurnaceSpeedMultiplier()));
/* 60 */       this.furnace.setBurnTime((short)Math.max(1, this.furnace.getBurnTime() - 1));
/* 61 */       this.furnace.update();
/* 62 */       if (this.furnace.getBurnTime() <= 1) {
/* 63 */         cancel();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\FurnaceSpeedHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */