/*     */ package me.qiooip.notorious;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Logger;
/*     */ import me.qiooip.notorious.commands.CPSAlertsCommand;
/*     */ import me.qiooip.notorious.commands.CPSCountCommand;
/*     */ import me.qiooip.notorious.commands.ChatControlCommand;
/*     */ import me.qiooip.notorious.commands.CobbleCommand;
/*     */ import me.qiooip.notorious.commands.CoordsCommand;
/*     */ import me.qiooip.notorious.commands.EOTWCommand;
/*     */ import me.qiooip.notorious.commands.EndSpawnCommand;
/*     */ import me.qiooip.notorious.commands.FirstJoinItemsCommand;
/*     */ import me.qiooip.notorious.commands.FoundOreCommand;
/*     */ import me.qiooip.notorious.commands.FreezeCommand;
/*     */ import me.qiooip.notorious.commands.HelpCommand;
/*     */ import me.qiooip.notorious.commands.ListCommand;
/*     */ import me.qiooip.notorious.commands.LogoutCommand;
/*     */ import me.qiooip.notorious.commands.MapKitCommand;
/*     */ import me.qiooip.notorious.commands.MuteFactionCommand;
/*     */ import me.qiooip.notorious.commands.NotesCommand;
/*     */ import me.qiooip.notorious.commands.NotoriousCommand;
/*     */ import me.qiooip.notorious.commands.OresCommand;
/*     */ import me.qiooip.notorious.commands.PlatinumCommand;
/*     */ import me.qiooip.notorious.commands.PvPTimerCommand;
/*     */ import me.qiooip.notorious.commands.RequestCommand;
/*     */ import me.qiooip.notorious.commands.SOTWCommand;
/*     */ import me.qiooip.notorious.commands.SlotsCommand;
/*     */ import me.qiooip.notorious.commands.StaffChatCommand;
/*     */ import me.qiooip.notorious.commands.StaffScoreboardCommand;
/*     */ import me.qiooip.notorious.commands.StatsCommand;
/*     */ import me.qiooip.notorious.commands.SubclaimCommand;
/*     */ import me.qiooip.notorious.config.ConfigFile;
/*     */ import me.qiooip.notorious.config.ConfigHandler;
/*     */ import me.qiooip.notorious.config.CooldownFile;
/*     */ import me.qiooip.notorious.config.DeathBanFile;
/*     */ import me.qiooip.notorious.config.LimitersFile;
/*     */ import me.qiooip.notorious.config.MessagesFile;
/*     */ import me.qiooip.notorious.config.ModulesFile;
/*     */ import me.qiooip.notorious.config.UtilitiesFile;
/*     */ import me.qiooip.notorious.cooldown.CooldownHandler;
/*     */ import me.qiooip.notorious.deathban.DeathBanHandler;
/*     */ import me.qiooip.notorious.deathban.commands.DeathBanCommand;
/*     */ import me.qiooip.notorious.deathban.commands.LivesCommand;
/*     */ import me.qiooip.notorious.handlers.ArcherTagTimeHandler;
/*     */ import me.qiooip.notorious.handlers.AutoSmeltHandler;
/*     */ import me.qiooip.notorious.handlers.BlockedCommandsHandler;
/*     */ import me.qiooip.notorious.handlers.BookDisenchantHandler;
/*     */ import me.qiooip.notorious.handlers.CPSCountHandler;
/*     */ import me.qiooip.notorious.handlers.ChatControlHandler;
/*     */ import me.qiooip.notorious.handlers.CobbleHandler;
/*     */ import me.qiooip.notorious.handlers.CombatLoggerHandler;
/*     */ import me.qiooip.notorious.handlers.CombatTagHandler;
/*     */ import me.qiooip.notorious.handlers.CrowbarHandler;
/*     */ import me.qiooip.notorious.handlers.DeathMessageHandler;
/*     */ import me.qiooip.notorious.handlers.DeathSignHandler;
/*     */ import me.qiooip.notorious.handlers.DynamicPlayerHandler;
/*     */ import me.qiooip.notorious.handlers.EOTWHandler;
/*     */ import me.qiooip.notorious.handlers.EnchantmentLimiterHandler;
/*     */ import me.qiooip.notorious.handlers.EnderPearlHandler;
/*     */ import me.qiooip.notorious.handlers.FoundOreHandler;
/*     */ import me.qiooip.notorious.handlers.FreezeHandler;
/*     */ import me.qiooip.notorious.handlers.FurnaceSpeedHandler;
/*     */ import me.qiooip.notorious.handlers.GlassHandler;
/*     */ import me.qiooip.notorious.handlers.GoldenAppleHandler;
/*     */ import me.qiooip.notorious.handlers.ItemDisableHandler;
/*     */ import me.qiooip.notorious.handlers.LogoutHandler;
/*     */ import me.qiooip.notorious.handlers.LootingAmplifierHandler;
/*     */ import me.qiooip.notorious.handlers.MapKitHandler;
/*     */ import me.qiooip.notorious.handlers.MobStackHandler;
/*     */ import me.qiooip.notorious.handlers.NetherPortalTrapHandler;
/*     */ import me.qiooip.notorious.handlers.NotesHandler;
/*     */ import me.qiooip.notorious.handlers.OresHandler;
/*     */ import me.qiooip.notorious.handlers.PlateElevatorHandler;
/*     */ import me.qiooip.notorious.handlers.PlateElevatorTimeHandler;
/*     */ import me.qiooip.notorious.handlers.PotionLimiterHandler;
/*     */ import me.qiooip.notorious.handlers.PvPClassWarmupHandler;
/*     */ import me.qiooip.notorious.handlers.PvPTimerHandler;
/*     */ import me.qiooip.notorious.handlers.ReportHandler;
/*     */ import me.qiooip.notorious.handlers.RequestHandler;
/*     */ import me.qiooip.notorious.handlers.SOTWHandler;
/*     */ import me.qiooip.notorious.handlers.SOTWHandler.SOTWTask;
/*     */ import me.qiooip.notorious.handlers.SlotsHandler;
/*     */ import me.qiooip.notorious.handlers.StaffChatHandler;
/*     */ import me.qiooip.notorious.handlers.StrengthNerfHandler;
/*     */ import me.qiooip.notorious.handlers.SubclaimHandler;
/*     */ import me.qiooip.notorious.integration.Vault;
/*     */ import me.qiooip.notorious.kits.Archer;
/*     */ import me.qiooip.notorious.kits.Bard;
/*     */ import me.qiooip.notorious.kits.Miner;
/*     */ import me.qiooip.notorious.kits.data.BardClickableItemHandler;
/*     */ import me.qiooip.notorious.kits.data.BardHoldItemHandler;
/*     */ import me.qiooip.notorious.kits.utils.ArcherTag;
/*     */ import me.qiooip.notorious.kits.utils.ClassType;
/*     */ import me.qiooip.notorious.platinum.PlatinumReviveHandler;
/*     */ import me.qiooip.notorious.playerdata.PlayerData;
/*     */ import me.qiooip.notorious.playerdata.PlayerDataHandler;
/*     */ import me.qiooip.notorious.scoreboard.ScoreboardObject;
/*     */ import me.qiooip.notorious.scoreboard.ScoreboardObjectHandler;
/*     */ import me.qiooip.notorious.staff.StaffModeHandler;
/*     */ import me.qiooip.notorious.staff.commands.StaffModeCommand;
/*     */ import me.qiooip.notorious.utils.Language;
/*     */ import me.qiooip.notorious.utils.StringUtils;
/*     */ import me.qiooip.notorious.vanish.VanishHandler;
/*     */ import me.qiooip.notorious.vanish.commands.VanishCommand;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import subside.plugins.koth.adapter.Koth;
/*     */ import subside.plugins.koth.adapter.KothHandler;
/*     */ import subside.plugins.koth.adapter.RunningKoth;
/*     */ import subside.plugins.koth.adapter.TimeObject;
/*     */ 
/*     */ public class Notorious extends JavaPlugin
/*     */ {
/*     */   public static Notorious instance;
/*     */   private ConfigFile configFile;
/*     */   private ConfigHandler configHandler;
/*     */   private CooldownFile cooldownFile;
/*     */   private DeathBanFile deathBanFile;
/*     */   private ModulesFile modulesFile;
/*     */   private UtilitiesFile utilitiesFile;
/*     */   private LimitersFile limitersFile;
/*     */   private MessagesFile messagesFile;
/*     */   private CooldownHandler cooldownHandler;
/*     */   private Archer archerClass;
/*     */   private Bard bardClass;
/*     */   private Miner minerClass;
/*     */   private BardHoldItemHandler bardHoldItemHandler;
/*     */   private BardClickableItemHandler bardClickableItemHandler;
/*     */   private PlayerDataHandler playerDataHandler;
/*     */   private ScoreboardObjectHandler scoreboardDataHandler;
/*     */   private DeathBanHandler deathBanHandler;
/*     */   private StaffModeHandler staffModeHandler;
/*     */   private VanishHandler vanishHandler;
/*     */   private ArcherTagTimeHandler archerTagTimeHandler;
/*     */   private AutoSmeltHandler autoSmeltHandler;
/*     */   private BlockedCommandsHandler blockedCommandsHandler;
/*     */   private BookDisenchantHandler bookDisenchantHandler;
/*     */   private ChatControlHandler chatControlHandler;
/*     */   private CobbleHandler cobbleHandler;
/*     */   private CombatLoggerHandler combatLoggerHandler;
/*     */   private CombatTagHandler combatTagHandler;
/*     */   private CPSCountHandler cpsCountHandler;
/*     */   private DeathSignHandler deathSignHandler;
/*     */   private CrowbarHandler crowbarHandler;
/*     */   private DeathMessageHandler deathMessageHandler;
/*     */   private DynamicPlayerHandler dynamicPlayerHandler;
/*     */   private EnchantmentLimiterHandler enchantmentLimiterHandler;
/*     */   private EnderPearlHandler enderPearlHandler;
/*     */   private EOTWHandler eotwHandler;
/*     */   private FoundOreHandler foundOreHandler;
/*     */   private FreezeHandler freezeHandler;
/*     */   private FurnaceSpeedHandler furnaceSpeedHandler;
/*     */   private GlassHandler glassHandler;
/*     */   private GoldenAppleHandler goldenAppleHandler;
/*     */   private ItemDisableHandler itemDisableHandler;
/*     */   private SlotsHandler slotsHandler;
/*     */   private LogoutHandler logoutHandler;
/*     */   private MapKitHandler mapkitHandler;
/*     */   private LootingAmplifierHandler lootingAmplifierHandler;
/*     */   private MobStackHandler mobStackHandler;
/*     */   private NetherPortalTrapHandler netherPortalTrapHandler;
/*     */   private NotesHandler notesHandler;
/*     */   private OresHandler oresHandler;
/*     */   private PlateElevatorHandler plateElevatorHandler;
/*     */   private PotionLimiterHandler potionLimiterHandler;
/*     */   private PlateElevatorTimeHandler plateElevatorTimeHandler;
/*     */   private PlatinumReviveHandler platinumReviveHandler;
/*     */   private PvPClassWarmupHandler pvpClassWarmupHandler;
/*     */   private PvPTimerHandler pvpTimerHandler;
/*     */   private ReportHandler reportHandler;
/*     */   private RequestHandler requestHandler;
/*     */   private SOTWHandler sotwHandler;
/*     */   private StaffChatHandler staffChatHandler;
/*     */   private StrengthNerfHandler strengthNerfHandler;
/*     */   private SubclaimHandler subclaimHandler;
/*     */   
/*     */   public void onEnable()
/*     */   {
/* 194 */     loadConfig0();instance = this;
/*     */     
/* 196 */     this.configFile = new ConfigFile();
/* 197 */     this.cooldownFile = new CooldownFile();
/* 198 */     this.deathBanFile = new DeathBanFile();
/* 199 */     this.modulesFile = new ModulesFile();
/* 200 */     this.utilitiesFile = new UtilitiesFile();
/* 201 */     this.limitersFile = new LimitersFile();
/* 202 */     this.messagesFile = new MessagesFile();
/* 203 */     this.configHandler = new ConfigHandler(this);
/*     */     
/* 205 */     if (this.modulesFile.getBoolean("Archer")) this.archerClass = new Archer(this);
/* 206 */     if (this.modulesFile.getBoolean("Bard")) this.bardClass = new Bard(this);
/* 207 */     if (this.modulesFile.getBoolean("Miner")) { this.minerClass = new Miner(this);
/*     */     }
/* 209 */     if (this.modulesFile.getBoolean("Bard")) this.bardHoldItemHandler = new BardHoldItemHandler(this);
/* 210 */     if (this.modulesFile.getBoolean("Bard")) { this.bardClickableItemHandler = new BardClickableItemHandler(this);
/*     */     }
/* 212 */     this.playerDataHandler = new PlayerDataHandler(this);
/* 213 */     if (this.modulesFile.getBoolean("ScoreboardHandler")) { this.scoreboardDataHandler = new ScoreboardObjectHandler(this);
/*     */     }
/* 215 */     if (this.modulesFile.getBoolean("DeathBanHandler")) this.deathBanHandler = new DeathBanHandler(this);
/* 216 */     if (this.modulesFile.getBoolean("StaffModeHandler")) this.staffModeHandler = new StaffModeHandler(this);
/* 217 */     if (this.modulesFile.getBoolean("VanishHandler")) { this.vanishHandler = new VanishHandler(this);
/*     */     }
/* 219 */     this.cooldownHandler = new CooldownHandler(this);
/*     */     
/* 221 */     if (this.modulesFile.getBoolean("Archer")) this.archerTagTimeHandler = new ArcherTagTimeHandler(this);
/* 222 */     if (this.modulesFile.getBoolean("AuthSmeltHandler")) this.autoSmeltHandler = new AutoSmeltHandler(this);
/* 223 */     if (this.modulesFile.getBoolean("BlockedCommandsHandler")) this.blockedCommandsHandler = new BlockedCommandsHandler(this);
/* 224 */     if (this.modulesFile.getBoolean("BookDisenchantHandler")) this.bookDisenchantHandler = new BookDisenchantHandler(this);
/* 225 */     if (this.modulesFile.getBoolean("ChatControlHandler")) this.chatControlHandler = new ChatControlHandler(this);
/* 226 */     if (this.modulesFile.getBoolean("CobbleHandler")) this.cobbleHandler = new CobbleHandler(this);
/* 227 */     if (this.modulesFile.getBoolean("CombatLoggerHandler")) this.combatLoggerHandler = new CombatLoggerHandler(this);
/* 228 */     if (this.modulesFile.getBoolean("CombatTagHandler")) this.combatTagHandler = new CombatTagHandler(this);
/* 229 */     if (this.modulesFile.getBoolean("CPSCountHandler")) this.cpsCountHandler = new CPSCountHandler(this);
/* 230 */     if (this.modulesFile.getBoolean("CrowbarHandler")) this.crowbarHandler = new CrowbarHandler(this);
/* 231 */     if (this.modulesFile.getBoolean("DeathMessageHandler")) this.deathMessageHandler = new DeathMessageHandler(this);
/* 232 */     if (this.modulesFile.getBoolean("DeathSignHandler")) this.deathSignHandler = new DeathSignHandler(this);
/* 233 */     this.dynamicPlayerHandler = new DynamicPlayerHandler(this);
/* 234 */     if (this.modulesFile.getBoolean("EnchantmentLimiterHandler")) this.enchantmentLimiterHandler = new EnchantmentLimiterHandler(this);
/* 235 */     if (this.modulesFile.getBoolean("EnderPearlHandler")) this.enderPearlHandler = new EnderPearlHandler(this);
/* 236 */     if (this.modulesFile.getBoolean("EOTWHandler")) this.eotwHandler = new EOTWHandler(this);
/* 237 */     if (this.modulesFile.getBoolean("FoundOreHandler")) this.foundOreHandler = new FoundOreHandler(this);
/* 238 */     if (this.modulesFile.getBoolean("FreezeHandler")) this.freezeHandler = new FreezeHandler(this);
/* 239 */     if (this.modulesFile.getBoolean("FurnaceSpeedHandler")) this.furnaceSpeedHandler = new FurnaceSpeedHandler(this);
/* 240 */     if (this.modulesFile.getBoolean("GlassHandler")) this.glassHandler = new GlassHandler(this);
/* 241 */     if (this.modulesFile.getBoolean("GoldenAppleHandler")) this.goldenAppleHandler = new GoldenAppleHandler(this);
/* 242 */     if (this.modulesFile.getBoolean("ItemDisableHandler")) this.itemDisableHandler = new ItemDisableHandler(this);
/* 243 */     if (this.modulesFile.getBoolean("SlotsHandler")) this.slotsHandler = new SlotsHandler(this);
/* 244 */     if (this.modulesFile.getBoolean("LogoutHandler")) this.logoutHandler = new LogoutHandler(this);
/* 245 */     if (this.modulesFile.getBoolean("MapKitHandler")) this.mapkitHandler = new MapKitHandler(this);
/* 246 */     if (this.modulesFile.getBoolean("LootingAmplifier")) this.lootingAmplifierHandler = new LootingAmplifierHandler(this);
/* 247 */     if (this.modulesFile.getBoolean("MobStackHandler")) this.mobStackHandler = new MobStackHandler(this);
/* 248 */     if (this.modulesFile.getBoolean("NetherPortalTrapHandler")) this.netherPortalTrapHandler = new NetherPortalTrapHandler(this);
/* 249 */     if (this.modulesFile.getBoolean("NotesHandler")) this.notesHandler = new NotesHandler(this);
/* 250 */     if (this.modulesFile.getBoolean("OresHandler")) this.oresHandler = new OresHandler(this);
/* 251 */     if (this.modulesFile.getBoolean("PlateElevatorHandler")) this.plateElevatorHandler = new PlateElevatorHandler(this);
/* 252 */     if (this.modulesFile.getBoolean("PotionLimiterHandler")) this.potionLimiterHandler = new PotionLimiterHandler(this);
/* 253 */     if (this.modulesFile.getBoolean("PlateElevatorHandler")) this.plateElevatorTimeHandler = new PlateElevatorTimeHandler(this);
/* 254 */     if (this.modulesFile.getBoolean("MedicReviveHandler")) this.platinumReviveHandler = new PlatinumReviveHandler(this);
/* 255 */     this.pvpClassWarmupHandler = new PvPClassWarmupHandler(this);
/* 256 */     if (this.modulesFile.getBoolean("PvPTimerHandler")) this.pvpTimerHandler = new PvPTimerHandler(this);
/* 257 */     if (this.modulesFile.getBoolean("ReportHandler")) this.reportHandler = new ReportHandler(this);
/* 258 */     if (this.modulesFile.getBoolean("RequestHandler")) this.requestHandler = new RequestHandler(this);
/* 259 */     if (this.modulesFile.getBoolean("SOTWHandler")) this.sotwHandler = new SOTWHandler(this);
/* 260 */     if (this.modulesFile.getBoolean("StaffChatHandler")) this.staffChatHandler = new StaffChatHandler(this);
/* 261 */     if (this.modulesFile.getBoolean("StrengthNerfHandler")) this.strengthNerfHandler = new StrengthNerfHandler(this);
/* 262 */     if (this.modulesFile.getBoolean("SubclaimHandler")) { this.subclaimHandler = new SubclaimHandler(this);
/*     */     }
/* 264 */     if (this.modulesFile.getBoolean("ScoreboardHandler")) setupScoreboard();
/* 265 */     setupCombatClasses();
/* 266 */     setupDirectories();
/* 267 */     registerCommands();
/* 268 */     setupLanguage();
/*     */     
/* 270 */     Vault.setup();
/* 271 */     me.qiooip.notorious.integration.WorldGuard.setup();
/*     */   }
/*     */   
/*     */   public void onDisable()
/*     */   {
/* 276 */     this.playerDataHandler.disable();
/*     */     
/* 278 */     this.cooldownHandler.disable();
/*     */     
/* 280 */     if (this.modulesFile.getBoolean("DeathBanHandler")) this.deathBanHandler.disable();
/* 281 */     if (this.modulesFile.getBoolean("StaffModeHandler")) this.staffModeHandler.disable();
/* 282 */     if (this.modulesFile.getBoolean("VanishHandler")) { this.vanishHandler.disable();
/*     */     }
/* 284 */     if (this.modulesFile.getBoolean("Archer")) this.archerTagTimeHandler.disable();
/* 285 */     if (this.modulesFile.getBoolean("ChatControlHandler")) this.chatControlHandler.disable();
/* 286 */     if (this.modulesFile.getBoolean("CobbleHandler")) this.cobbleHandler.disable();
/* 287 */     if (this.modulesFile.getBoolean("CombatLoggerHandler")) this.combatLoggerHandler.disable();
/* 288 */     if (this.modulesFile.getBoolean("CombatTagHandler")) this.combatTagHandler.disable();
/* 289 */     if (this.modulesFile.getBoolean("CPSCountHandler")) this.cpsCountHandler.disable();
/* 290 */     if (this.modulesFile.getBoolean("EnchantmentLimiterHandler")) this.enchantmentLimiterHandler.disable();
/* 291 */     if (this.modulesFile.getBoolean("EnderPearlHandler")) this.enderPearlHandler.disable();
/* 292 */     if (this.modulesFile.getBoolean("FreezeHandler")) this.freezeHandler.disable();
/* 293 */     if (this.modulesFile.getBoolean("GlassHandler")) this.glassHandler.disable();
/* 294 */     if (this.modulesFile.getBoolean("LogoutHandler")) this.logoutHandler.disable();
/* 295 */     if (this.modulesFile.getBoolean("MapKitHandler")) this.mapkitHandler.disable();
/* 296 */     if (this.modulesFile.getBoolean("MobStackHandler")) this.mobStackHandler.disable();
/* 297 */     if (this.modulesFile.getBoolean("PlateElevatorHandler")) this.plateElevatorTimeHandler.disable();
/* 298 */     if (this.modulesFile.getBoolean("MedicReviveHandler")) this.platinumReviveHandler.disable();
/* 299 */     if (this.modulesFile.getBoolean("PotionLimiterHandler")) this.potionLimiterHandler.disable();
/* 300 */     this.pvpClassWarmupHandler.disable();
/* 301 */     if (this.modulesFile.getBoolean("ReportHandler")) this.reportHandler.disable();
/* 302 */     if (this.modulesFile.getBoolean("RequestHandler")) this.requestHandler.disable();
/*     */     ClassType[] arrayOfClassType;
/* 304 */     int j = (arrayOfClassType = ClassType.values()).length; for (int i = 0; i < j; i++) { ClassType localClassType = arrayOfClassType[i];
/* 305 */       Player[] arrayOfPlayer; int m = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int k = 0; k < m; k++) { Player localPlayer = arrayOfPlayer[k];
/* 306 */         Iterator localIterator2; for (Iterator localIterator1 = localClassType.getPlayers().iterator(); localIterator1.hasNext(); 
/* 307 */             localIterator2.hasNext())
/*     */         {
/* 306 */           UUID localUUID = (UUID)localIterator1.next();
/* 307 */           localIterator2 = localClassType.getEffects().iterator(); continue;PotionEffect localPotionEffect = (PotionEffect)localIterator2.next();
/* 308 */           if (localPlayer.getUniqueId().equals(localUUID)) {
/* 309 */             localPlayer.removePotionEffect(localPotionEffect.getType());
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 314 */       localClassType.getPlayers().clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setupScoreboard()
/*     */   {
/* 432 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         Player[] arrayOfPlayer;
/* 322 */         int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 323 */           ScoreboardObject localScoreboardObject = Notorious.this.scoreboardDataHandler.getScoreboardFor(localPlayer);
/* 324 */           localScoreboardObject.clear();
/*     */           
/* 326 */           PlayerData localPlayerData = Notorious.this.playerDataHandler.getByPlayer(localPlayer);
/*     */           
/* 328 */           if ((ClassType.ARCHER.getPlayers().contains(localPlayer.getUniqueId())) || (ClassType.BARD.getPlayers().contains(localPlayer.getUniqueId())) || 
/* 329 */             (ClassType.MINER.getPlayers().contains(localPlayer.getUniqueId())) || (Notorious.this.combatTagHandler.isCombatTagged(localPlayer)) || 
/* 330 */             (Notorious.this.enderPearlHandler.isActive(localPlayer)) || (localPlayerData.getPvPTime() > 0) || 
/* 331 */             ((Notorious.getInstance().getServer().getPluginManager().isPluginEnabled("KoTH")) && (!KothHandler.getInstance().getRunningKoths().isEmpty())) || 
/* 332 */             (Notorious.this.archerClass.getWarmups().containsKey(localPlayer.getUniqueId())) || (Notorious.this.bardClass.getWarmups().containsKey(localPlayer.getUniqueId())) || 
/* 333 */             (Notorious.this.minerClass.getWarmups().containsKey(localPlayer.getUniqueId())) || 
/* 334 */             (Notorious.this.plateElevatorHandler.getTeleportTasks().containsKey(localPlayer.getUniqueId())) || 
/* 335 */             (Notorious.this.sotwHandler.isRunning()) || (Notorious.this.logoutHandler.getLogoutTasks().containsKey(localPlayer.getUniqueId())) || 
/* 336 */             (Notorious.this.goldenAppleHandler.isActive(localPlayer)) || ((!Notorious.this.dynamicPlayerHandler.getStaffScoreboard().contains(localPlayer.getUniqueId())) && (localPlayer.hasPermission("notorious.staff")))) {
/* 337 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getScoreboardLine());
/*     */           }
/*     */           
/* 340 */           if ((localPlayer.hasPermission("notorious.staff")) && 
/* 341 */             (!Notorious.this.dynamicPlayerHandler.getStaffScoreboard().contains(localPlayer.getUniqueId()))) {
/* 342 */             if (Notorious.getInstance().getStaffModeHandler().isInStaffMode(localPlayer)) {
/* 343 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getStaffmodePlaceholder() + ChatColor.GREEN + " Enabled");
/*     */             } else {
/* 345 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getStaffmodePlaceholder() + ChatColor.RED + " Disabled");
/*     */             }
/* 347 */             if (Notorious.getInstance().getVanishHandler().getVanishedPlayers().contains(localPlayer.getUniqueId())) {
/* 348 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getVisibilityPlaceholder() + ChatColor.GREEN + " Vanished");
/*     */             } else {
/* 350 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getVisibilityPlaceholder() + ChatColor.RED + " Visible");
/*     */             }
/* 352 */             if (Notorious.getInstance().getStaffChatHandler().getStaff().contains(localPlayer.getUniqueId())) {
/* 353 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getChatModePlaceholder() + ChatColor.GREEN + " Staff Chat");
/*     */             }
/*     */             else {
/* 356 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getChatModePlaceholder() + ChatColor.RED + " Global Chat");
/*     */             }
/* 358 */             if (localPlayer.getGameMode() == GameMode.CREATIVE) {
/* 359 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getGameModePlaceholder() + ChatColor.GREEN + " Creative");
/*     */             } else {
/* 361 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getGameModePlaceholder() + ChatColor.RED + " Survival");
/*     */             }
/* 363 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getScoreboardLine());
/*     */           }
/*     */           
/*     */ 
/* 367 */           if (Notorious.this.sotwHandler.isRunning()) {
/* 368 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getSotwPlaceholder().replace("<time>", 
/* 369 */               StringUtils.formatSecondsToHours(Notorious.getInstance().getSOTWHandler().getSOTWTask().getTime())));
/*     */           }
/*     */           
/* 372 */           if ((Notorious.getInstance().getServer().getPluginManager().isPluginEnabled("KoTH")) && 
/* 373 */             (!KothHandler.getInstance().getRunningKoths().isEmpty())) {
/* 374 */             for (int k = 0; k < KothHandler.getInstance().getRunningKoths().size(); k++) {
/* 375 */               RunningKoth localRunningKoth = (RunningKoth)KothHandler.getInstance().getRunningKoths().get(k);
/* 376 */               String str = localRunningKoth.getTimeObject().getTimeLeftFormatted();
/* 377 */               localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getKothPlaceholder().replace("<kothname>", 
/* 378 */                 localRunningKoth.getKoth().getName()).replace("<time>", str));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 383 */           if (Notorious.this.combatTagHandler.isCombatTagged(localPlayer)) {
/* 384 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getCombatTagPlaceholder().replace("<time>", 
/* 385 */               StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.this.combatTagHandler.getMillisecondsLeft(localPlayer)))));
/*     */           }
/*     */           
/* 388 */           if (Notorious.this.enderPearlHandler.isActive(localPlayer)) {
/* 389 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getEnderPearlPlaceholder().replace("<time>", 
/* 390 */               StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.this.enderPearlHandler.getMillisecondsLeft(localPlayer)))));
/*     */           }
/*     */           
/* 393 */           if (localPlayerData.getPvPTime() > 0) {
/* 394 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getPvPTimerPlaceholder().replace("<time>", 
/* 395 */               StringUtils.formatSecondsToMinutes(localPlayerData.getPvPTime())));
/*     */           }
/*     */           
/* 398 */           Notorious.this.kitsScoreboardSetup(localPlayer, localScoreboardObject);
/*     */           
/* 400 */           if (Notorious.this.logoutHandler.getLogoutTasks().containsKey(localPlayer.getUniqueId())) {
/* 401 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getLogoutPlaceholder().replace("<time>", 
/* 402 */               StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.this.logoutHandler.getMillisecondsLeft(localPlayer)))));
/*     */           } else {
/* 404 */             Notorious.this.logoutHandler.applyWarmup(localPlayer);
/*     */           }
/*     */           
/* 407 */           if (Notorious.this.plateElevatorHandler.getTeleportTasks().containsKey(localPlayer.getUniqueId())) {
/* 408 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getElevatorTeleportPlaceholder().replace("<time>", 
/* 409 */               StringUtils.formatMilisecondsToSeconds(Long.valueOf(Notorious.this.plateElevatorTimeHandler.getMillisecondsLeft(localPlayer)))));
/*     */           }
/*     */           
/* 412 */           if (Notorious.this.goldenAppleHandler.isActive(localPlayer)) {
/* 413 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getGapplePlaceholder().replace("<time>", 
/* 414 */               StringUtils.formatSecondsToHours((int)(Notorious.this.goldenAppleHandler.getMillisecondsLeft(localPlayer) / 1000L))));
/*     */           }
/*     */           
/* 417 */           if ((ClassType.ARCHER.getPlayers().contains(localPlayer.getUniqueId())) || (ClassType.BARD.getPlayers().contains(localPlayer.getUniqueId())) || 
/* 418 */             (ClassType.MINER.getPlayers().contains(localPlayer.getUniqueId())) || (Notorious.this.combatTagHandler.isCombatTagged(localPlayer)) || 
/* 419 */             (Notorious.this.enderPearlHandler.isActive(localPlayer)) || (localPlayerData.getPvPTime() > 0) || 
/* 420 */             ((Notorious.getInstance().getServer().getPluginManager().isPluginEnabled("KoTH")) && (!KothHandler.getInstance().getRunningKoths().isEmpty())) || 
/* 421 */             (Notorious.this.archerClass.getWarmups().containsKey(localPlayer.getUniqueId())) || (Notorious.this.bardClass.getWarmups().containsKey(localPlayer.getUniqueId())) || 
/* 422 */             (Notorious.this.minerClass.getWarmups().containsKey(localPlayer.getUniqueId())) || 
/* 423 */             (Notorious.this.plateElevatorHandler.getTeleportTasks().containsKey(localPlayer.getUniqueId())) || 
/* 424 */             (Notorious.this.sotwHandler.isRunning()) || (Notorious.this.logoutHandler.getLogoutTasks().containsKey(localPlayer.getUniqueId())) || 
/* 425 */             (Notorious.this.goldenAppleHandler.isActive(localPlayer))) {
/* 426 */             localScoreboardObject.add(Notorious.getInstance().getConfigHandler().getScoreboardLine());
/*     */           }
/*     */           
/* 429 */           localScoreboardObject.update(localPlayer);
/*     */         }
/*     */       }
/* 432 */     }.runTaskTimerAsynchronously(this, 2L, 2L);
/*     */   }
/*     */   
/*     */   public void kitsScoreboardSetup(Player paramPlayer, ScoreboardObject paramScoreboardObject) {
/* 436 */     if ((this.archerClass.getWarmups().containsKey(paramPlayer.getUniqueId())) || (this.bardClass.getWarmups().containsKey(paramPlayer.getUniqueId())) || (this.minerClass.getWarmups().containsKey(paramPlayer.getUniqueId()))) {
/* 437 */       if (this.pvpClassWarmupHandler.isActive(paramPlayer)) {
/* 438 */         paramScoreboardObject.add(getConfigHandler().getPvpclassWarmupPlaceholder().replace("<time>", 
/* 439 */           StringUtils.formatMilisecondsToSeconds(Long.valueOf(this.pvpClassWarmupHandler.getMillisecondsLeft(paramPlayer)))));
/*     */       }
/*     */     } else {
/* 442 */       this.pvpClassWarmupHandler.applyWarmup(paramPlayer);
/*     */     }
/*     */     Object localObject2;
/* 445 */     int j = (localObject2 = ClassType.values()).length; Object localObject1; String str; for (int i = 0; i < j; i++) { localObject1 = localObject2[i];
/* 446 */       if (((ClassType)localObject1).getPlayers().contains(paramPlayer.getUniqueId())) {
/* 447 */         str = ((ClassType)localObject1).getName();
/* 448 */         paramScoreboardObject.add(getConfigHandler().getPvpclassActivePlaceholder().replace("<class>", str));
/*     */       }
/*     */     }
/*     */     
/* 452 */     if (getBardClass().getBardPowers().containsKey(paramPlayer.getUniqueId())) {
/* 453 */       paramScoreboardObject.add(getConfigHandler().getBardenergyPlaceholder().replace("<energy>", 
/* 454 */         String.valueOf(getBardClass().getBardPower(paramPlayer).getPower())));
/*     */     }
/*     */     
/* 457 */     if (getCooldownHandler().isActive(paramPlayer.getUniqueId().toString() + "_archerspeed")) {
/* 458 */       paramScoreboardObject.add(getConfigHandler().getArcherspeedPlaceholder().replace("<time>", 
/* 459 */         StringUtils.formatMilisecondsToSeconds(Long.valueOf(getCooldownHandler().getMillisecondsLeft(paramPlayer.getUniqueId().toString() + "_archerspeed")))));
/*     */     }
/*     */     
/* 462 */     if (getCooldownHandler().isActive(paramPlayer.getUniqueId().toString() + "_bardclickable")) {
/* 463 */       paramScoreboardObject.add(getConfigHandler().getCooldownPlaceholder().replace("<time>", 
/* 464 */         StringUtils.formatMilisecondsToSeconds(Long.valueOf(getCooldownHandler().getMillisecondsLeft(paramPlayer.getUniqueId().toString() + "_bardclickable")))));
/*     */     }
/*     */     
/* 467 */     j = (localObject2 = ArcherTag.values()).length; for (i = 0; i < j; i++) { localObject1 = localObject2[i];
/* 468 */       if (((ArcherTag)localObject1).getTaggedPlayers().contains(paramPlayer.getUniqueId())) {
/* 469 */         if (this.archerTagTimeHandler.isActive(paramPlayer)) {
/* 470 */           str = ((ArcherTag)localObject1).getLevel();
/* 471 */           paramScoreboardObject.add(getConfigHandler().getArchertagPlaceholder().replace("<level>", String.valueOf(str)).replace("<time>", 
/* 472 */             StringUtils.formatMilisecondsToSeconds(Long.valueOf(this.archerTagTimeHandler.getMillisecondsLeft(paramPlayer)))));
/*     */         } else {
/* 474 */           this.archerTagTimeHandler.applyWarmup(paramPlayer);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setupCombatClasses() {
/* 481 */     if (this.modulesFile.getBoolean("Archer"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 489 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/*     */           Player[] arrayOfPlayer;
/* 485 */           int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 486 */             Notorious.this.archerClass.checkPlayer(localPlayer);
/*     */           }
/*     */         }
/* 489 */       }.runTaskTimer(this, 20L, 20L);
/*     */     }
/*     */     
/* 492 */     if (this.modulesFile.getBoolean("Bard"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 500 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/*     */           Player[] arrayOfPlayer;
/* 496 */           int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 497 */             Notorious.this.bardClass.checkPlayer(localPlayer);
/*     */           }
/*     */         }
/* 500 */       }.runTaskTimer(this, 20L, 20L);
/*     */     }
/*     */     
/* 503 */     if (this.modulesFile.getBoolean("Miner"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 511 */       new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/*     */           Player[] arrayOfPlayer;
/* 507 */           int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { Player localPlayer = arrayOfPlayer[i];
/* 508 */             Notorious.this.minerClass.checkPlayer(localPlayer);
/*     */           }
/*     */         }
/* 511 */       }.runTaskTimer(this, 20L, 20L);
/*     */     }
/*     */     
/* 514 */     if (this.modulesFile.getBoolean("Archer")) {
/* 515 */       this.archerClass.enable();
/* 516 */       this.archerClass.checkEffects();
/*     */     }
/* 518 */     if (this.modulesFile.getBoolean("Bard")) {
/* 519 */       this.bardClass.enable();
/* 520 */       this.bardClass.checkEffects();
/* 521 */       this.bardClass.checkBardHoldItems();
/*     */     }
/* 523 */     if (this.modulesFile.getBoolean("Miner")) {
/* 524 */       this.minerClass.enable();
/* 525 */       this.minerClass.checkEffects();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setupDirectories() {
/* 530 */     if (!getDataFolder().exists()) {
/* 531 */       getDataFolder().mkdir();
/*     */     }
/*     */     
/* 534 */     File localFile1 = new File(getDataFolder(), "playerdata");
/* 535 */     if (!localFile1.exists()) {
/* 536 */       localFile1.mkdir();
/*     */     }
/*     */     File localFile2;
/* 539 */     if (this.modulesFile.getBoolean("DeathBanHandler")) {
/* 540 */       localFile2 = new File(getDataFolder(), "deathban");
/* 541 */       if (!localFile2.exists()) {
/* 542 */         localFile2.mkdir();
/*     */       }
/*     */     }
/*     */     
/* 546 */     if (this.modulesFile.getBoolean("DeathBanHandler")) {
/* 547 */       localFile2 = new File(new File(getDataFolder(), "deathban"), "deathbans");
/* 548 */       if (!localFile2.exists()) {
/* 549 */         localFile2.mkdir();
/*     */       }
/*     */     }
/*     */     
/* 553 */     if (this.modulesFile.getBoolean("DeathBanHandler")) {
/* 554 */       localFile2 = new File(new File(getDataFolder(), "deathban"), "inventories");
/* 555 */       if (!localFile2.exists()) {
/* 556 */         localFile2.mkdir();
/*     */       }
/*     */     }
/*     */     
/* 560 */     if (this.modulesFile.getBoolean("DeathBanHandler")) {
/* 561 */       localFile2 = new File(new File(getDataFolder(), "deathban"), "lives");
/* 562 */       if (!localFile2.exists()) {
/* 563 */         localFile2.mkdir();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void registerCommands() {
/* 569 */     if (this.modulesFile.getBoolean("ChatControlHandler")) getCommand("chat").setExecutor(new ChatControlCommand());
/* 570 */     if (this.modulesFile.getBoolean("CobbleHandler")) getCommand("cobble").setExecutor(new CobbleCommand());
/* 571 */     if (this.modulesFile.getBoolean("CoordsCommand")) getCommand("coords").setExecutor(new CoordsCommand());
/* 572 */     if (this.modulesFile.getBoolean("CPSCountHandler")) getCommand("cpsalerts").setExecutor(new CPSAlertsCommand());
/* 573 */     if (this.modulesFile.getBoolean("CPSCountHandler")) getCommand("cps").setExecutor(new CPSCountCommand());
/* 574 */     if (this.modulesFile.getBoolean("CrowbarHandler")) getCommand("crowbar").setExecutor(new me.qiooip.notorious.commands.CrowbarCommand());
/* 575 */     if (this.modulesFile.getBoolean("DeathBanHandler")) getCommand("deathban").setExecutor(new DeathBanCommand());
/* 576 */     if (this.modulesFile.getBoolean("SetEndSpawnCommand")) getCommand("endspawn").setExecutor(new EndSpawnCommand());
/* 577 */     if (this.modulesFile.getBoolean("EOTWHandler")) getCommand("eotw").setExecutor(new EOTWCommand());
/* 578 */     if (this.modulesFile.getBoolean("FoundOreHandler")) getCommand("foundore").setExecutor(new FoundOreCommand());
/* 579 */     if (this.modulesFile.getBoolean("FreezeHandler")) getCommand("freeze").setExecutor(new FreezeCommand());
/* 580 */     if (this.modulesFile.getBoolean("HelpCommand")) getCommand("help").setExecutor(new HelpCommand());
/* 581 */     if (this.modulesFile.getBoolean("FirstJoinItemsCommand")) getCommand("firstjoinitems").setExecutor(new FirstJoinItemsCommand());
/* 582 */     if (this.modulesFile.getBoolean("DeathBanHandler")) getCommand("lives").setExecutor(new LivesCommand());
/* 583 */     if (this.modulesFile.getBoolean("ListCommand")) getCommand("list").setExecutor(new ListCommand());
/* 584 */     if (this.modulesFile.getBoolean("LogoutHandler")) getCommand("logout").setExecutor(new LogoutCommand());
/* 585 */     if (this.modulesFile.getBoolean("MapKitHandler")) getCommand("mapkit").setExecutor(new MapKitCommand());
/* 586 */     if (this.modulesFile.getBoolean("FactionMuteCommand")) getCommand("mutefaction").setExecutor(new MuteFactionCommand());
/* 587 */     if (this.modulesFile.getBoolean("NotesHandler")) getCommand("notes").setExecutor(new NotesCommand());
/* 588 */     getCommand("notorious").setExecutor(new NotoriousCommand());
/* 589 */     if (this.modulesFile.getBoolean("OresHandler")) getCommand("ores").setExecutor(new OresCommand());
/* 590 */     if (this.modulesFile.getBoolean("MedicReviveHandler")) getCommand("medic").setExecutor(new PlatinumCommand());
/* 591 */     if (this.modulesFile.getBoolean("PvPTimerHandler")) getCommand("pvp").setExecutor(new PvPTimerCommand());
/* 592 */     if (this.modulesFile.getBoolean("ReportHandler")) getCommand("report").setExecutor(new me.qiooip.notorious.commands.ReportCommand());
/* 593 */     if (this.modulesFile.getBoolean("RequestHandler")) getCommand("request").setExecutor(new RequestCommand());
/* 594 */     if (this.modulesFile.getBoolean("SOTWHandler")) getCommand("sotw").setExecutor(new SOTWCommand());
/* 595 */     if (this.modulesFile.getBoolean("StaffModeHandler")) getCommand("staff").setExecutor(new StaffModeCommand());
/* 596 */     if (this.modulesFile.getBoolean("StaffChatHandler")) getCommand("staffchat").setExecutor(new StaffChatCommand());
/* 597 */     if (this.modulesFile.getBoolean("ScoreboardHandler")) getCommand("staffscoreboard").setExecutor(new StaffScoreboardCommand());
/* 598 */     if (this.modulesFile.getBoolean("StatsCommand")) getCommand("stats").setExecutor(new StatsCommand());
/* 599 */     if (this.modulesFile.getBoolean("SlotsHandler")) getCommand("slots").setExecutor(new SlotsCommand());
/* 600 */     if (this.modulesFile.getBoolean("VanishHandler")) getCommand("vanish").setExecutor(new VanishCommand());
/* 601 */     if (this.modulesFile.getBoolean("SubclaimHandler")) getCommand("subclaim").setExecutor(new SubclaimCommand());
/*     */   }
/*     */   
/*     */   public void setupLanguage() {
/* 605 */     File localFile = new File(getDataFolder(), "language.yml");
/*     */     
/* 607 */     if (!localFile.exists()) {
/*     */       try {
/* 609 */         getDataFolder().mkdir();
/* 610 */         localFile.createNewFile();
/*     */       } catch (IOException localIOException1) {
/* 612 */         getLogger().severe("Error: " + localIOException1.getMessage());
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 617 */       YamlConfiguration localYamlConfiguration = YamlConfiguration.loadConfiguration(localFile);
/* 618 */       Language[] arrayOfLanguage; int j = (arrayOfLanguage = Language.values()).length; for (int i = 0; i < j; i++) { Language localLanguage = arrayOfLanguage[i];
/* 619 */         if (localYamlConfiguration.getString(localLanguage.getPath()) == null) {
/* 620 */           localYamlConfiguration.set(localLanguage.getPath(), localLanguage.getValue());
/*     */         }
/*     */       }
/*     */       
/* 624 */       localYamlConfiguration.save(localFile);
/*     */       
/* 626 */       Language.setLangFile(localYamlConfiguration);
/*     */     } catch (IOException localIOException2) {
/* 628 */       getLogger().severe("Error: " + localIOException2.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public static Notorious getInstance() {
/* 633 */     return instance;
/*     */   }
/*     */   
/*     */   public ConfigFile getConfigFile() {
/* 637 */     return this.configFile;
/*     */   }
/*     */   
/*     */   public CooldownFile getCooldownFile() {
/* 641 */     return this.cooldownFile;
/*     */   }
/*     */   
/*     */   public ConfigHandler getConfigHandler() {
/* 645 */     return this.configHandler;
/*     */   }
/*     */   
/*     */   public void setConfigHandler(ConfigHandler paramConfigHandler) {
/* 649 */     this.configHandler = paramConfigHandler;
/*     */   }
/*     */   
/*     */   public DeathBanFile getDeathBanFile() {
/* 653 */     return this.deathBanFile;
/*     */   }
/*     */   
/*     */   public ModulesFile getModulesFile() {
/* 657 */     return this.modulesFile;
/*     */   }
/*     */   
/*     */   public UtilitiesFile getUtilitiesFile() {
/* 661 */     return this.utilitiesFile;
/*     */   }
/*     */   
/*     */   public LimitersFile getLimitersFile() {
/* 665 */     return this.limitersFile;
/*     */   }
/*     */   
/*     */   public MessagesFile getMessagesFile() {
/* 669 */     return this.messagesFile;
/*     */   }
/*     */   
/*     */   public ScoreboardObjectHandler getScoreboardDataHandler() {
/* 673 */     return this.scoreboardDataHandler;
/*     */   }
/*     */   
/*     */   public CooldownHandler getCooldownHandler() {
/* 677 */     return this.cooldownHandler;
/*     */   }
/*     */   
/*     */   public Archer getArcherClass() {
/* 681 */     return this.archerClass;
/*     */   }
/*     */   
/*     */   public Bard getBardClass() {
/* 685 */     return this.bardClass;
/*     */   }
/*     */   
/*     */   public Miner getMinerClass() {
/* 689 */     return this.minerClass;
/*     */   }
/*     */   
/*     */   public BardHoldItemHandler getBardHoldItemHandler() {
/* 693 */     return this.bardHoldItemHandler;
/*     */   }
/*     */   
/*     */   public BardClickableItemHandler getBardClickableItemHandler() {
/* 697 */     return this.bardClickableItemHandler;
/*     */   }
/*     */   
/*     */   public PlayerDataHandler getPlayerDataHandler() {
/* 701 */     return this.playerDataHandler;
/*     */   }
/*     */   
/*     */   public DeathBanHandler getDeathBanHandler() {
/* 705 */     return this.deathBanHandler;
/*     */   }
/*     */   
/*     */   public DynamicPlayerHandler getDynamicPlayerHandler() {
/* 709 */     return this.dynamicPlayerHandler;
/*     */   }
/*     */   
/*     */   public FoundOreHandler getFoundOreHandler() {
/* 713 */     return this.foundOreHandler;
/*     */   }
/*     */   
/*     */   public StaffModeHandler getStaffModeHandler() {
/* 717 */     return this.staffModeHandler;
/*     */   }
/*     */   
/*     */   public VanishHandler getVanishHandler() {
/* 721 */     return this.vanishHandler;
/*     */   }
/*     */   
/*     */   public ArcherTagTimeHandler getArcherTagTimeHandler() {
/* 725 */     return this.archerTagTimeHandler;
/*     */   }
/*     */   
/*     */   public ChatControlHandler getChatControlHandler() {
/* 729 */     return this.chatControlHandler;
/*     */   }
/*     */   
/*     */   public CobbleHandler getCobbleHandler() {
/* 733 */     return this.cobbleHandler;
/*     */   }
/*     */   
/*     */   public CombatLoggerHandler getCombatLoggerHandler() {
/* 737 */     return this.combatLoggerHandler;
/*     */   }
/*     */   
/*     */   public CombatTagHandler getCombatTagHandler() {
/* 741 */     return this.combatTagHandler;
/*     */   }
/*     */   
/*     */   public CPSCountHandler getCPSCountHandler() {
/* 745 */     return this.cpsCountHandler;
/*     */   }
/*     */   
/*     */   public CrowbarHandler getCrowbarHandler() {
/* 749 */     return this.crowbarHandler;
/*     */   }
/*     */   
/*     */   public EnchantmentLimiterHandler getEnchantmentLimiterHandler() {
/* 753 */     return this.enchantmentLimiterHandler;
/*     */   }
/*     */   
/*     */   public EnderPearlHandler getEnderPearlHandler() {
/* 757 */     return this.enderPearlHandler;
/*     */   }
/*     */   
/*     */   public EOTWHandler getEOTWHandler() {
/* 761 */     return this.eotwHandler;
/*     */   }
/*     */   
/*     */   public FreezeHandler getFreezeHandler() {
/* 765 */     return this.freezeHandler;
/*     */   }
/*     */   
/*     */   public LogoutHandler getLogoutHandler() {
/* 769 */     return this.logoutHandler;
/*     */   }
/*     */   
/*     */   public SlotsHandler getJoinFullServerHandler() {
/* 773 */     return this.slotsHandler;
/*     */   }
/*     */   
/*     */   public MapKitHandler getMapKitHandler() {
/* 777 */     return this.mapkitHandler;
/*     */   }
/*     */   
/*     */   public MobStackHandler getMobStackHandler() {
/* 781 */     return this.mobStackHandler;
/*     */   }
/*     */   
/*     */   public OresHandler getOresHandler() {
/* 785 */     return this.oresHandler;
/*     */   }
/*     */   
/*     */   public PlateElevatorTimeHandler getPlateElevatorTimeHandler() {
/* 789 */     return this.plateElevatorTimeHandler;
/*     */   }
/*     */   
/*     */   public PlatinumReviveHandler getPlatinumReviveHandler() {
/* 793 */     return this.platinumReviveHandler;
/*     */   }
/*     */   
/*     */   public PotionLimiterHandler getPotionLimtierHandler() {
/* 797 */     return this.potionLimiterHandler;
/*     */   }
/*     */   
/*     */   public ReportHandler getReportHandler() {
/* 801 */     return this.reportHandler;
/*     */   }
/*     */   
/*     */   public RequestHandler getRequestHandler() {
/* 805 */     return this.requestHandler;
/*     */   }
/*     */   
/*     */   public SOTWHandler getSOTWHandler() {
/* 809 */     return this.sotwHandler;
/*     */   }
/*     */   
/*     */   public StaffChatHandler getStaffChatHandler() {
/* 813 */     return this.staffChatHandler;
/*     */   }
/*     */   
/*     */   public SubclaimHandler getSubclaimHandler() {
/* 817 */     return this.subclaimHandler;
/*     */   }
/*     */   
/*     */   public StrengthNerfHandler getStrengthNerfHandler() {
/* 821 */     return this.strengthNerfHandler;
/*     */   }
/*     */   
/*     */   public PvPTimerHandler getPvpTimerHandler() {
/* 825 */     return this.pvpTimerHandler;
/*     */   }
/*     */   
/*     */   public NotesHandler getNotesHandler() {
/* 829 */     return this.notesHandler;
/*     */   }
/*     */   
/*     */   public NetherPortalTrapHandler getNetherPortalTrapHandler() {
/* 833 */     return this.netherPortalTrapHandler;
/*     */   }
/*     */   
/* 836 */   public LootingAmplifierHandler getLootingAmplifierHandler() { return this.lootingAmplifierHandler; }
/*     */   
/*     */   public ItemDisableHandler getItemDisableHandler()
/*     */   {
/* 840 */     return this.itemDisableHandler;
/*     */   }
/*     */   
/*     */   public FurnaceSpeedHandler getFurnaceSpeedHandler() {
/* 844 */     return this.furnaceSpeedHandler;
/*     */   }
/*     */   
/*     */   public DeathMessageHandler getDeathMessageHandler() {
/* 848 */     return this.deathMessageHandler;
/*     */   }
/*     */   
/*     */   public DeathSignHandler getDeathSignHandler() {
/* 852 */     return this.deathSignHandler;
/*     */   }
/*     */   
/*     */   public BookDisenchantHandler getBookDisenchantHandler() {
/* 856 */     return this.bookDisenchantHandler;
/*     */   }
/*     */   
/*     */   public BlockedCommandsHandler getBlockedCommandsHandler() {
/* 860 */     return this.blockedCommandsHandler;
/*     */   }
/*     */   
/*     */   public AutoSmeltHandler getAutoSmeltHandler() {
/* 864 */     return this.autoSmeltHandler;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\Notorious.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */