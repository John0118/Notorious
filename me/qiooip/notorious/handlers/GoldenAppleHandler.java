/*    */ package me.qiooip.notorious.handlers;
/*    */ 
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.playerdata.PlayerData;
/*    */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*    */ import me.qiooip.notorious.utils.Handler;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import me.qiooip.notorious.utils.StringUtils;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GoldenAppleHandler extends Handler implements org.bukkit.event.Listener
/*    */ {
/*    */   public GoldenAppleHandler(Notorious paramNotorious)
/*    */   {
/* 19 */     super(paramNotorious);
/*    */     
/* 21 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*    */   }
/*    */   
/*    */   public void applyCooldown(Player paramPlayer) {
/* 25 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/* 26 */     localPlayerData.setGoldenAppleCooldown(System.currentTimeMillis() + getInstance().getConfigHandler().getGoldenAppleCooldown() * 1000);
/*    */   }
/*    */   
/*    */   public boolean isActive(Player paramPlayer) {
/* 30 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/* 31 */     return System.currentTimeMillis() < localPlayerData.getGoldenAppleCooldown();
/*    */   }
/*    */   
/*    */   public long getMillisecondsLeft(Player paramPlayer) {
/* 35 */     PlayerData localPlayerData = getInstance().getPlayerDataHandler().getByPlayer(paramPlayer);
/* 36 */     return Math.max(localPlayerData.getGoldenAppleCooldown() - System.currentTimeMillis(), 0L);
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onPlayerItemConsume(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/* 41 */     Player localPlayer = paramPlayerItemConsumeEvent.getPlayer();
/* 42 */     ItemStack localItemStack = paramPlayerItemConsumeEvent.getItem();
/*    */     
/* 44 */     if ((localItemStack.getType().equals(Material.GOLDEN_APPLE)) && (localItemStack.getDurability() == 1)) {
/* 45 */       if (isActive(localPlayer)) {
/* 46 */         paramPlayerItemConsumeEvent.setCancelled(true);
/* 47 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.GOLDENAPPLE_COOLDOWN_DENY_MESSAGE.toString().replace("<time>", 
/* 48 */           StringUtils.formatSecondsToHours((int)(getMillisecondsLeft(localPlayer) / 1000L))));
/*    */       } else {
/* 50 */         applyCooldown(localPlayer);
/* 51 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.GOLDENAPPLE_COOLDOWN_STARTED_MESSAGE.toString().replace("<time>", 
/* 52 */           StringUtils.formatSecondsToHours(getInstance().getConfigHandler().getGoldenAppleCooldown())));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\GoldenAppleHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */