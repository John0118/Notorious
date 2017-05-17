/*     */ package me.qiooip.notorious.handlers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class CPSCountHandler extends Handler implements org.bukkit.event.Listener
/*     */ {
/*     */   private ArrayList<UUID> alerts;
/*     */   private HashMap<UUID, Integer> cpsCounters;
/*     */   private HashMap<UUID, Long> startTimes;
/*     */   
/*     */   public CPSCountHandler(Notorious paramNotorious)
/*     */   {
/*  28 */     super(paramNotorious);
/*  29 */     this.alerts = new ArrayList();
/*  30 */     this.cpsCounters = new HashMap();
/*  31 */     this.startTimes = new HashMap();
/*     */     
/*  33 */     getInstance().getServer().getPluginManager().registerEvents(this, getInstance());
/*     */   }
/*     */   
/*     */   public void disable() {
/*  37 */     this.cpsCounters.clear();
/*  38 */     this.startTimes.clear();
/*     */   }
/*     */   
/*     */   public ArrayList<UUID> getAlertsRecievers() {
/*  42 */     return this.alerts;
/*     */   }
/*     */   
/*     */   public HashMap<UUID, Integer> getCpsCounters() {
/*  46 */     return this.cpsCounters;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent paramPlayerInteractEvent)
/*     */   {
/*  52 */     Player localPlayer1 = paramPlayerInteractEvent.getPlayer();
/*  53 */     Action localAction = paramPlayerInteractEvent.getAction();
/*     */     
/*  55 */     if ((paramPlayerInteractEvent.getItem() != null) && (paramPlayerInteractEvent.getItem().getType().equals(Material.DIAMOND_PICKAXE))) return;
/*  56 */     if ((paramPlayerInteractEvent.getItem() != null) && (paramPlayerInteractEvent.getItem().getType().equals(Material.IRON_PICKAXE))) return;
/*  57 */     if ((paramPlayerInteractEvent.getItem() != null) && (paramPlayerInteractEvent.getItem().getType().equals(Material.DIAMOND_SPADE))) return;
/*  58 */     if ((paramPlayerInteractEvent.getItem() != null) && (paramPlayerInteractEvent.getItem().getType().equals(Material.IRON_SPADE))) { return;
/*     */     }
/*  60 */     if ((localAction.equals(Action.LEFT_CLICK_AIR)) || (localAction.equals(Action.LEFT_CLICK_BLOCK))) {
/*  61 */       if ((!this.cpsCounters.containsKey(localPlayer1.getUniqueId())) || (!this.startTimes.containsKey(localPlayer1.getUniqueId()))) {
/*  62 */         this.cpsCounters.put(localPlayer1.getUniqueId(), Integer.valueOf(0));
/*  63 */         this.startTimes.put(localPlayer1.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
/*     */       }
/*  65 */       this.cpsCounters.put(localPlayer1.getUniqueId(), Integer.valueOf(((Integer)this.cpsCounters.get(localPlayer1.getUniqueId())).intValue() + 1));
/*  66 */       if (System.currentTimeMillis() - ((Long)this.startTimes.get(localPlayer1.getUniqueId())).longValue() >= 5000L) {
/*  67 */         if (((Integer)this.cpsCounters.get(localPlayer1.getUniqueId())).intValue() > getInstance().getConfigHandler().getCpsCountForWarning() * 5) {
/*  68 */           this.startTimes.put(localPlayer1.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
/*  69 */           Player[] arrayOfPlayer; int j = (arrayOfPlayer = org.bukkit.Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer2 = arrayOfPlayer[i];
/*  70 */             if ((localPlayer2.hasPermission("notorious.cps.recieve")) && 
/*  71 */               (!this.alerts.contains(localPlayer2.getUniqueId()))) {
/*  72 */               localPlayer2.sendMessage(Language.PREFIX.toString() + Language.CPSCOUNT_WARNING_MESSAGE.toString().replace("<player>", localPlayer1.getName()).replace("<cps>", 
/*  73 */                 String.valueOf(((Integer)this.cpsCounters.get(localPlayer1.getUniqueId())).intValue() / 5)));
/*     */             }
/*     */           }
/*     */           
/*  77 */           this.cpsCounters.put(localPlayer1.getUniqueId(), Integer.valueOf(1));
/*     */         }
/*  79 */         this.startTimes.put(localPlayer1.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
/*  80 */         this.cpsCounters.put(localPlayer1.getUniqueId(), Integer.valueOf(1));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent) {
/*  87 */     Player localPlayer = paramPlayerQuitEvent.getPlayer();
/*  88 */     if (this.cpsCounters.containsKey(localPlayer.getUniqueId())) {
/*  89 */       this.cpsCounters.remove(localPlayer.getUniqueId());
/*     */     }
/*  91 */     if (this.startTimes.containsKey(localPlayer.getUniqueId())) {
/*  92 */       this.startTimes.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerKick(PlayerKickEvent paramPlayerKickEvent) {
/*  98 */     Player localPlayer = paramPlayerKickEvent.getPlayer();
/*  99 */     if (this.cpsCounters.containsKey(localPlayer.getUniqueId())) {
/* 100 */       this.cpsCounters.remove(localPlayer.getUniqueId());
/*     */     }
/* 102 */     if (this.startTimes.containsKey(localPlayer.getUniqueId())) {
/* 103 */       this.startTimes.remove(localPlayer.getUniqueId());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\handlers\CPSCountHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */