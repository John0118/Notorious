/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event.Result;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class EnderPearlHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private HashMap<UUID, Long> cooldown;
/*     */   
/*     */   public EnderPearlHandler(Notorious paramNotorious)
/*     */   {
/*  28 */     super(paramNotorious);
/*  29 */     this.cooldown = new HashMap();
/*     */     
/*  31 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  35 */     this.cooldown.clear();
/*     */   }
/*     */   
/*     */   public boolean isActive(Player paramPlayer) {
/*  39 */     return (this.cooldown.containsKey(paramPlayer.getUniqueId())) && (System.currentTimeMillis() < ((Long)this.cooldown.get(paramPlayer.getUniqueId())).longValue());
/*     */   }
/*     */   
/*     */   public void apply(Player paramPlayer) {
/*  43 */     this.cooldown.put(paramPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis() + getInstance().getConfigHandler().getEnderpearlDuration() * 1000));
/*     */   }
/*     */   
/*     */   public long getMillisecondsLeft(Player paramPlayer) {
/*  47 */     if (this.cooldown.containsKey(paramPlayer.getUniqueId())) {
/*  48 */       return Math.max(((Long)this.cooldown.get(paramPlayer.getUniqueId())).longValue() - System.currentTimeMillis(), 0L);
/*     */     }
/*  50 */     return 0L;
/*     */   }
/*     */   
/*     */   public Long getByPlayerUUID(UUID paramUUID) {
/*  54 */     return (Long)this.cooldown.get(paramUUID.toString());
/*     */   }
/*     */   
/*     */   public HashMap<UUID, Long> getActiveCooldowns() {
/*  58 */     return this.cooldown;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent) {
/*  63 */     Player localPlayer = paramPlayerInteractEvent.getPlayer();
/*  64 */     Action localAction = paramPlayerInteractEvent.getAction();
/*     */     
/*  66 */     if (localPlayer.getGameMode().equals(GameMode.CREATIVE)) { return;
/*     */     }
/*  68 */     if ((paramPlayerInteractEvent.hasItem()) && ((localAction.equals(Action.RIGHT_CLICK_AIR)) || (localAction.equals(Action.RIGHT_CLICK_BLOCK))) && (paramPlayerInteractEvent.getItem().getType().equals(Material.ENDER_PEARL))) {
/*  69 */       if (isActive(localPlayer)) {
/*  70 */         paramPlayerInteractEvent.setUseItemInHand(Event.Result.DENY);
/*  71 */         localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENDERPEARL_DENY_MESSAGE.toString().replace("<time>", me.qiooip.notorious.utils.StringUtils.formatMilisecondsToSeconds(Long.valueOf(getMillisecondsLeft(localPlayer)))));
/*     */       }
/*  73 */       else if (!getInstance().getFreezeHandler().getFreezedPlayers().contains(localPlayer.getUniqueId())) {
/*  74 */         apply(localPlayer);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent paramPlayerDeathEvent)
/*     */   {
/*  82 */     Player localPlayer = paramPlayerDeathEvent.getEntity();
/*  83 */     if (this.cooldown.containsKey(localPlayer.getUniqueId())) {
/*  84 */       this.cooldown.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/*  90 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*  91 */     if (this.cooldown.containsKey(localPlayer.getUniqueId())) {
/*  92 */       this.cooldown.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/*  98 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/*  99 */     if (this.cooldown.containsKey(localPlayer.getUniqueId())) {
/* 100 */       this.cooldown.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\EnderPearlHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */