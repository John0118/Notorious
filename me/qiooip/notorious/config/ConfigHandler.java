/*     */ package me.qiooip.notorious.config;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.qiooip.notorious.Notorious;
/*     */ import me.qiooip.notorious.utils.Handler;
/*     */ import me.qiooip.notorious.utils.InventoryUtils;
/*     */ import me.qiooip.notorious.utils.LocationSerilization;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
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
/*     */ public class ConfigHandler
/*     */   extends Handler
/*     */ {
/*     */   private String scoreboard_title;
/*     */   private String scoreboard_line;
/*     */   private String combattag_placeholder;
/*     */   private String enderpearl_placeholder;
/*     */   private String pvptimer_placeholder;
/*     */   private String sotw_placeholder;
/*     */   private String koth_placeholder;
/*     */   private String pvpclass_warmup_placeholder;
/*     */   private String pvpclass_active_placeholder;
/*     */   private String archerspeed_placeholder;
/*     */   private String archertag_placeholder;
/*     */   private String bardenergy_placeholder;
/*     */   private String cooldown_placeholder;
/*     */   private String elevator_teleport_placeholder;
/*     */   private String logout_placeholder;
/*     */   private String gapple_placeholder;
/*     */   private String staffmode_placeholder;
/*     */   private String vanished_placeholder;
/*     */   private String staffchat_placeholder;
/*     */   private String gamemode_placeholder;
/*     */   private int combattag_duration;
/*     */   private List<String> combattag_disabled_commands;
/*     */   private int enderpearl_duration;
/*     */   private String crowbar_name;
/*     */   private List<String> crowbar_lore;
/*     */   private int crowbar_uses_spawners;
/*     */   private int crowbar_uses_portals;
/*     */   private String crowbar_spawner_name_color;
/*     */   private int golden_apple_cooldown;
/*     */   private String deathsign_timezone;
/*     */   private String deathsign_date_format;
/*     */   private String deathsign_name;
/*     */   private List<String> deathsign_lore;
/*     */   private int medic_revive_cooldown;
/*     */   private String subclaim_sign_title;
/*     */   private String subclaim_captains_only;
/*     */   private String subclaim_leader_only;
/*     */   private boolean nether_portal_trap_fix_enabled;
/*     */   private String mob_stack_color;
/*     */   private int mob_stacking_radius;
/*     */   private List<String> mob_stacking_entity;
/*     */   private int default_sotw_duration;
/*     */   private int pvp_timer_duration;
/*     */   private List<String> pvp_timer_disabled_commands;
/*  77 */   private List<Material> pvp_timer_disabled_items = new ArrayList();
/*     */   
/*     */   private List<String> glass_protected_regions;
/*     */   
/*     */   private boolean strength_nerf_enabled;
/*     */   
/*     */   private int strength1_nerf_percentage;
/*     */   
/*     */   private int strength2_nerf_percentage;
/*     */   
/*     */   private int combat_logger_time;
/*     */   
/*     */   private String mapkit_inventory_name;
/*     */   
/*     */   private int mapkit_inventory_size;
/*     */   
/*     */   private int logout_time;
/*     */   
/*     */   private int request_command_delay;
/*     */   
/*     */   private String ores_inventory_name;
/*     */   
/*     */   private int report_command_delay;
/*     */   
/*     */   private String report_inventory_title;
/*     */   
/*     */   private int cps_count_for_warning;
/*     */   private String unrepairable_lore_line;
/*     */   private int looting_bonus;
/*     */   private int furnace_speed_multiplier;
/*     */   private int elevator_teleport_delay;
/*     */   private Material elevator_bottom_block;
/* 109 */   private List<Material> found_ore_materials = new ArrayList();
/*     */   
/*     */   private boolean enable_core_info;
/*     */   
/*     */   private List<String> blocked_commands;
/*     */   
/*     */   private int freeze_message_interval;
/*     */   
/*     */   private List<String> freeze_disabled_commands;
/*     */   
/*     */   private List<String> freeze_command_message;
/*     */   
/*     */   private List<String> help_command_message;
/*     */   
/*     */   private List<String> list_command_message;
/*     */   
/*     */   private List<String> on_join_message;
/*     */   
/*     */   private List<String> report_message;
/*     */   
/*     */   private Location end_spawn_location;
/*     */   
/*     */   private List<String> slots_command_message;
/*     */   
/*     */   private List<String> stats_command_message;
/*     */   
/*     */   private int slots_default_amount;
/*     */   
/*     */   private ItemStack[] first_join_items;
/*     */   
/*     */   private int pvp_class_warmup_duration;
/*     */   
/*     */   private int archer_speedboost_level;
/*     */   private int archer_speedboost_duration;
/*     */   private int archer_speedboost_cooldown;
/*     */   private int archer_tag_duration;
/*     */   private int bard_clickable_items_cooldown;
/* 146 */   private ArrayList<PotionEffect> archer_potion_effects = new ArrayList();
/* 147 */   private ArrayList<PotionEffect> bard_potion_effects = new ArrayList();
/* 148 */   private ArrayList<PotionEffect> miner_potion_effects = new ArrayList();
/*     */   
/* 150 */   private ArrayList<PotionEffect> archer_tag_effects_tag1 = new ArrayList();
/* 151 */   private ArrayList<PotionEffect> archer_tag_effects_tag2 = new ArrayList();
/* 152 */   private ArrayList<PotionEffect> archer_tag_effects_tag3 = new ArrayList();
/*     */   
/* 154 */   private List<String> coords_message = new ArrayList();
/*     */   
/*     */   private boolean deathban_enabled;
/*     */   
/*     */   private List<String> deathban_usage_message;
/*     */   
/*     */   private List<String> deathban_check_message;
/*     */   private List<String> lives_usage_message;
/*     */   private List<String> subclaim_command_message;
/*     */   
/*     */   public ConfigHandler(Notorious paramNotorious)
/*     */   {
/* 166 */     super(paramNotorious);
/*     */     
/* 168 */     this.scoreboard_title = getInstance().getConfigFile().getString("scoreboard-title");
/* 169 */     this.scoreboard_line = getInstance().getConfigFile().getString("scoreboard-line");
/* 170 */     this.combattag_placeholder = getInstance().getConfigFile().getString("combattag-placeholder");
/* 171 */     this.enderpearl_placeholder = getInstance().getConfigFile().getString("enderpearl-placeholder");
/* 172 */     this.pvptimer_placeholder = getInstance().getConfigFile().getString("pvptimer-placeholder");
/* 173 */     this.sotw_placeholder = getInstance().getConfigFile().getString("sotw-placeholder");
/* 174 */     this.koth_placeholder = getInstance().getConfigFile().getString("koth-placeholder");
/* 175 */     this.pvpclass_warmup_placeholder = getInstance().getConfigFile().getString("pvpclass-warmup-placeholder");
/* 176 */     this.pvpclass_active_placeholder = getInstance().getConfigFile().getString("pvpclass-active-placeholder");
/* 177 */     this.archerspeed_placeholder = getInstance().getConfigFile().getString("archerspeed-placeholder");
/* 178 */     this.archertag_placeholder = getInstance().getConfigFile().getString("archertag-placeholder");
/* 179 */     this.bardenergy_placeholder = getInstance().getConfigFile().getString("bardenergy-placeholder");
/* 180 */     this.cooldown_placeholder = getInstance().getConfigFile().getString("cooldown-placeholder");
/* 181 */     this.elevator_teleport_placeholder = getInstance().getConfigFile().getString("elevator-teleport-placeholder");
/* 182 */     this.logout_placeholder = getInstance().getConfigFile().getString("logout-placeholder");
/* 183 */     this.gapple_placeholder = getInstance().getConfigFile().getString("gapple-placeholder");
/* 184 */     this.staffmode_placeholder = getInstance().getConfigFile().getString("staffmode-placeholder");
/* 185 */     this.vanished_placeholder = getInstance().getConfigFile().getString("visibility-placeholder");
/* 186 */     this.staffchat_placeholder = getInstance().getConfigFile().getString("chatmode-placeholder");
/* 187 */     this.gamemode_placeholder = getInstance().getConfigFile().getString("gamemode-placeholder");
/*     */     
/* 189 */     this.combattag_duration = getInstance().getConfigFile().getInt("combat-tag-duration");
/* 190 */     this.combattag_disabled_commands = getInstance().getConfigFile().getStringList("combat-tag-disabled-commands");
/*     */     
/* 192 */     this.enderpearl_duration = getInstance().getConfigFile().getInt("enderpearl-duration");
/*     */     
/* 194 */     this.crowbar_name = getInstance().getConfigFile().getString("crowbar-name");
/* 195 */     this.crowbar_lore = getInstance().getConfigFile().getStringList("crowbar-lore");
/* 196 */     this.crowbar_uses_spawners = getInstance().getConfigFile().getInt("crowbar-uses-spawners");
/* 197 */     this.crowbar_uses_portals = getInstance().getConfigFile().getInt("crowbar-uses-portals");
/* 198 */     this.crowbar_spawner_name_color = getInstance().getConfigFile().getString("crowbar-spawner-name-color");
/*     */     
/* 200 */     this.golden_apple_cooldown = getInstance().getConfigFile().getInt("golden-apple-cooldown");
/*     */     
/* 202 */     this.deathsign_timezone = getInstance().getConfigFile().getString("deathsign-timezone");
/* 203 */     this.deathsign_date_format = getInstance().getConfigFile().getString("deathsign-date-format");
/* 204 */     this.deathsign_name = getInstance().getConfigFile().getString("deathsign-name");
/* 205 */     this.deathsign_lore = getInstance().getConfigFile().getStringList("deathsign-lore");
/*     */     
/* 207 */     this.subclaim_sign_title = getInstance().getConfigFile().getString("sign-title");
/* 208 */     this.subclaim_captains_only = getInstance().getConfigFile().getString("captains-only");
/* 209 */     this.subclaim_leader_only = getInstance().getConfigFile().getString("leader-only");
/*     */     
/* 211 */     this.nether_portal_trap_fix_enabled = getInstance().getConfigFile().getBoolean("nether-portal-trap-fix-enabled");
/*     */     
/* 213 */     this.mob_stack_color = getInstance().getConfigFile().getString("stack-color");
/* 214 */     this.mob_stacking_radius = getInstance().getConfigFile().getInt("stacking-radius");
/* 215 */     this.mob_stacking_entity = getInstance().getConfigFile().getStringList("stacking-entity");
/*     */     
/* 217 */     this.default_sotw_duration = getInstance().getConfigFile().getInt("default-sotw-time");
/*     */     
/* 219 */     this.pvp_timer_duration = getInstance().getConfigFile().getInt("pvp-timer-duration");
/* 220 */     this.pvp_timer_disabled_commands = getInstance().getConfigFile().getStringList("pvp-timer-disabled-commands");
/* 221 */     this.pvp_timer_disabled_items.clear();
/* 222 */     String str; for (Object localObject2 = getInstance().getConfigFile().getStringList("pvp-timer-disabled-items").iterator(); ((Iterator)localObject2).hasNext();) { str = (String)((Iterator)localObject2).next();
/* 223 */       this.pvp_timer_disabled_items.add(Material.getMaterial(Integer.valueOf(str).intValue()));
/*     */     }
/* 225 */     this.glass_protected_regions = getInstance().getConfigFile().getStringList("glass-protected-regions");
/*     */     
/* 227 */     this.strength_nerf_enabled = getInstance().getConfigFile().getBoolean("strength-nerf-enabled");
/* 228 */     this.strength1_nerf_percentage = getInstance().getConfigFile().getInt("strength1-nerf-percentage");
/* 229 */     this.strength2_nerf_percentage = getInstance().getConfigFile().getInt("strength2-nerf-percentage");
/*     */     
/* 231 */     this.combat_logger_time = getInstance().getConfigFile().getInt("combat-logger-time");
/*     */     
/* 233 */     this.mapkit_inventory_name = ChatColor.translateAlternateColorCodes('&', getInstance().getConfigFile().getString("mapkit-inventory-name"));
/* 234 */     this.mapkit_inventory_size = getInstance().getConfigFile().getInt("mapkit-inventory-size");
/*     */     
/* 236 */     this.logout_time = getInstance().getConfigFile().getInt("logout-time");
/*     */     
/* 238 */     this.request_command_delay = getInstance().getConfigFile().getInt("request-command-delay");
/*     */     
/* 240 */     this.ores_inventory_name = getInstance().getConfigFile().getString("ores-inventory-name");
/*     */     
/* 242 */     this.report_command_delay = getInstance().getConfigFile().getInt("report-command-delay");
/* 243 */     this.report_inventory_title = getInstance().getConfigFile().getString("report-inventory-title");
/*     */     
/* 245 */     this.cps_count_for_warning = getInstance().getConfigFile().getInt("cps-amount-for-warning");
/*     */     
/* 247 */     this.unrepairable_lore_line = getInstance().getConfigFile().getString("unrepairable-lore-line");
/*     */     
/* 249 */     this.looting_bonus = getInstance().getConfigFile().getInt("looting-bonus");
/*     */     
/* 251 */     this.medic_revive_cooldown = getInstance().getConfigFile().getInt("medic-revive-cooldown");
/*     */     
/* 253 */     this.furnace_speed_multiplier = getInstance().getConfigFile().getInt("furnace-speed-multiplier");
/*     */     
/* 255 */     this.elevator_teleport_delay = getInstance().getConfigFile().getInt("elevator-teleport-delay");
/* 256 */     this.elevator_bottom_block = Material.getMaterial(getInstance().getConfigFile().getInt("elevator-bottom-block-id"));
/*     */     
/* 258 */     if (this.found_ore_materials != null) this.found_ore_materials.clear();
/* 259 */     for (localObject2 = getInstance().getConfigFile().getStringList("tracking-block-ids").iterator(); ((Iterator)localObject2).hasNext();) { str = (String)((Iterator)localObject2).next();
/* 260 */       this.found_ore_materials.add(Material.getMaterial(Integer.valueOf(str).intValue()));
/*     */     }
/*     */     
/* 263 */     this.enable_core_info = getInstance().getConfigFile().getBoolean("enable-core-info");
/*     */     
/* 265 */     this.blocked_commands = getInstance().getConfigFile().getStringList("blocked-commands");
/*     */     
/* 267 */     this.freeze_message_interval = getInstance().getConfigFile().getInt("freeze-message-interval");
/* 268 */     this.freeze_disabled_commands = getInstance().getConfigFile().getStringList("freeze-disabled-commands");
/* 269 */     this.freeze_command_message = getInstance().getMessagesFile().getStringList("freeze-message");
/*     */     
/* 271 */     this.help_command_message = getInstance().getMessagesFile().getStringList("help-message");
/*     */     
/* 273 */     this.list_command_message = getInstance().getMessagesFile().getStringList("list-message");
/*     */     
/* 275 */     this.on_join_message = getInstance().getMessagesFile().getStringList("on-join-message");
/*     */     
/* 277 */     this.report_message = getInstance().getMessagesFile().getStringList("report-message");
/*     */     
/* 279 */     this.slots_command_message = getInstance().getMessagesFile().getStringList("slots-command-message");
/*     */     
/* 281 */     this.slots_default_amount = getInstance().getConfigFile().getInt("default-slots-amount");
/*     */     
/* 283 */     this.stats_command_message = getInstance().getMessagesFile().getStringList("stats-command-message");
/*     */     
/* 285 */     if (getInstance().getUtilitiesFile().getString("end-spawn").equals("")) {
/* 286 */       this.end_spawn_location = null;
/*     */     } else {
/* 288 */       this.end_spawn_location = LocationSerilization.stringToLocation(getInstance().getUtilitiesFile().getString("end-spawn"));
/*     */     }
/*     */     try
/*     */     {
/* 292 */       if (getInstance().getUtilitiesFile().getString("first-join-items").equals("")) {
/* 293 */         this.first_join_items = new ItemStack[36];
/*     */       } else {
/* 295 */         this.first_join_items = InventoryUtils.itemStackArrayFromBase64(getInstance().getUtilitiesFile().getString("first-join-items"));
/*     */       }
/*     */     } catch (IOException localIOException) {
/* 298 */       localIOException.printStackTrace();
/*     */     }
/*     */     
/* 301 */     this.pvp_class_warmup_duration = getInstance().getConfigFile().getInt("pvp-class-warmup-duration");
/*     */     
/* 303 */     this.archer_speedboost_level = getInstance().getConfigFile().getInt("archer-speedboost-level");
/* 304 */     this.archer_speedboost_duration = getInstance().getConfigFile().getInt("archer-speedboost-duration");
/* 305 */     this.archer_speedboost_cooldown = getInstance().getConfigFile().getInt("archer-speedboost-cooldown");
/* 306 */     this.archer_tag_duration = getInstance().getConfigFile().getInt("archer-tag-duration");
/*     */     
/* 308 */     this.bard_clickable_items_cooldown = getInstance().getConfigFile().getInt("bard-clickable-items-cooldown");
/*     */     
/* 310 */     if (this.archer_potion_effects != null) this.archer_potion_effects.clear();
/* 311 */     int i; int j; for (localObject2 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("archer-potion-effects").getKeys(false).iterator(); ((Iterator)localObject2).hasNext();) { localObject1 = (String)((Iterator)localObject2).next();
/* 312 */       i = getInstance().getConfigFile().getInt("archer-potion-effects." + (String)localObject1 + ".duration");
/* 313 */       j = getInstance().getConfigFile().getInt("archer-potion-effects." + (String)localObject1 + ".level") - 1;
/* 314 */       this.archer_potion_effects.add(new PotionEffect(PotionEffectType.getByName((String)localObject1), i, j));
/*     */     }
/*     */     
/* 317 */     if (this.bard_potion_effects != null) this.bard_potion_effects.clear();
/* 318 */     for (localObject2 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("bard-potion-effects").getKeys(false).iterator(); ((Iterator)localObject2).hasNext();) { localObject1 = (String)((Iterator)localObject2).next();
/* 319 */       i = getInstance().getConfigFile().getInt("bard-potion-effects." + (String)localObject1 + ".duration");
/* 320 */       j = getInstance().getConfigFile().getInt("bard-potion-effects." + (String)localObject1 + ".level") - 1;
/* 321 */       this.bard_potion_effects.add(new PotionEffect(PotionEffectType.getByName((String)localObject1), i, j));
/*     */     }
/*     */     
/* 324 */     if (this.miner_potion_effects != null) this.miner_potion_effects.clear();
/* 325 */     for (localObject2 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("miner-potion-effects").getKeys(false).iterator(); ((Iterator)localObject2).hasNext();) { localObject1 = (String)((Iterator)localObject2).next();
/* 326 */       i = getInstance().getConfigFile().getInt("miner-potion-effects." + (String)localObject1 + ".duration");
/* 327 */       j = getInstance().getConfigFile().getInt("miner-potion-effects." + (String)localObject1 + ".level") - 1;
/* 328 */       this.miner_potion_effects.add(new PotionEffect(PotionEffectType.getByName((String)localObject1), i, j));
/*     */     }
/*     */     
/* 331 */     Object localObject1 = getInstance().getConfigFile().getConfiguration().getConfigurationSection("archer-tag-effects");
/* 332 */     if (this.archer_tag_effects_tag1 != null) this.archer_tag_effects_tag1.clear();
/* 333 */     int k; for (Iterator localIterator = ((ConfigurationSection)localObject1).getConfigurationSection("tag1").getKeys(false).iterator(); localIterator.hasNext();) { localObject2 = (String)localIterator.next();
/* 334 */       j = ((ConfigurationSection)localObject1).getInt("tag1." + (String)localObject2 + ".duration") * 20;
/* 335 */       k = ((ConfigurationSection)localObject1).getInt("tag1." + (String)localObject2 + ".level") - 1;
/* 336 */       this.archer_tag_effects_tag1.add(new PotionEffect(PotionEffectType.getByName((String)localObject2), j, k));
/*     */     }
/*     */     
/* 339 */     if (this.archer_tag_effects_tag2 != null) this.archer_tag_effects_tag2.clear();
/* 340 */     for (localIterator = ((ConfigurationSection)localObject1).getConfigurationSection("tag2").getKeys(false).iterator(); localIterator.hasNext();) { localObject2 = (String)localIterator.next();
/* 341 */       j = ((ConfigurationSection)localObject1).getInt("tag2." + (String)localObject2 + ".duration") * 20;
/* 342 */       k = ((ConfigurationSection)localObject1).getInt("tag2." + (String)localObject2 + ".level") - 1;
/* 343 */       this.archer_tag_effects_tag2.add(new PotionEffect(PotionEffectType.getByName((String)localObject2), j, k));
/*     */     }
/*     */     
/* 346 */     if (this.archer_tag_effects_tag3 != null) this.archer_tag_effects_tag3.clear();
/* 347 */     for (localIterator = ((ConfigurationSection)localObject1).getConfigurationSection("tag3").getKeys(false).iterator(); localIterator.hasNext();) { localObject2 = (String)localIterator.next();
/* 348 */       j = ((ConfigurationSection)localObject1).getInt("tag3." + (String)localObject2 + ".duration") * 20;
/* 349 */       k = ((ConfigurationSection)localObject1).getInt("tag3." + (String)localObject2 + ".level") - 1;
/* 350 */       this.archer_tag_effects_tag3.add(new PotionEffect(PotionEffectType.getByName((String)localObject2), j, k));
/*     */     }
/*     */     
/* 353 */     this.coords_message = getInstance().getMessagesFile().getStringList("coords-message");
/*     */     
/* 355 */     this.deathban_enabled = getInstance().getDeathBanFile().getBoolean("deathban-enabled");
/*     */     
/* 357 */     this.deathban_usage_message = getInstance().getMessagesFile().getStringList("deathban-usage-message");
/* 358 */     this.deathban_check_message = getInstance().getMessagesFile().getStringList("deathban-check-message");
/* 359 */     this.lives_usage_message = getInstance().getMessagesFile().getStringList("lives-usage-message");
/*     */     
/* 361 */     this.subclaim_command_message = getInstance().getMessagesFile().getStringList("subclaim-command-message");
/*     */   }
/*     */   
/*     */   public String getScoreboardTitle() {
/* 365 */     return this.scoreboard_title;
/*     */   }
/*     */   
/*     */   public String getScoreboardLine() {
/* 369 */     return this.scoreboard_line;
/*     */   }
/*     */   
/*     */   public String getCombatTagPlaceholder() {
/* 373 */     return this.combattag_placeholder;
/*     */   }
/*     */   
/*     */   public String getEnderPearlPlaceholder() {
/* 377 */     return this.enderpearl_placeholder;
/*     */   }
/*     */   
/*     */   public String getPvPTimerPlaceholder() {
/* 381 */     return this.pvptimer_placeholder;
/*     */   }
/*     */   
/*     */   public String getSotwPlaceholder() {
/* 385 */     return this.sotw_placeholder;
/*     */   }
/*     */   
/*     */   public String getKothPlaceholder() {
/* 389 */     return this.koth_placeholder;
/*     */   }
/*     */   
/*     */   public String getPvpclassWarmupPlaceholder() {
/* 393 */     return this.pvpclass_warmup_placeholder;
/*     */   }
/*     */   
/*     */   public String getPvpclassActivePlaceholder() {
/* 397 */     return this.pvpclass_active_placeholder;
/*     */   }
/*     */   
/*     */   public String getArcherspeedPlaceholder() {
/* 401 */     return this.archerspeed_placeholder;
/*     */   }
/*     */   
/*     */   public String getArchertagPlaceholder() {
/* 405 */     return this.archertag_placeholder;
/*     */   }
/*     */   
/*     */   public String getBardenergyPlaceholder() {
/* 409 */     return this.bardenergy_placeholder;
/*     */   }
/*     */   
/*     */   public String getCooldownPlaceholder() {
/* 413 */     return this.cooldown_placeholder;
/*     */   }
/*     */   
/*     */   public String getElevatorTeleportPlaceholder() {
/* 417 */     return this.elevator_teleport_placeholder;
/*     */   }
/*     */   
/*     */   public String getLogoutPlaceholder() {
/* 421 */     return this.logout_placeholder;
/*     */   }
/*     */   
/*     */   public String getGapplePlaceholder() {
/* 425 */     return this.gapple_placeholder;
/*     */   }
/*     */   
/*     */   public String getStaffmodePlaceholder() {
/* 429 */     return this.staffmode_placeholder;
/*     */   }
/*     */   
/*     */   public String getVisibilityPlaceholder() {
/* 433 */     return this.vanished_placeholder;
/*     */   }
/*     */   
/*     */   public String getChatModePlaceholder() {
/* 437 */     return this.staffchat_placeholder;
/*     */   }
/*     */   
/*     */   public String getGameModePlaceholder() {
/* 441 */     return this.gamemode_placeholder;
/*     */   }
/*     */   
/*     */   public int getCombatTagDuration() {
/* 445 */     return this.combattag_duration;
/*     */   }
/*     */   
/*     */   public List<String> getCombattagDisabledCommands() {
/* 449 */     return this.combattag_disabled_commands;
/*     */   }
/*     */   
/*     */   public int getEnderpearlDuration() {
/* 453 */     return this.enderpearl_duration;
/*     */   }
/*     */   
/*     */   public String getCrowbarName() {
/* 457 */     return this.crowbar_name;
/*     */   }
/*     */   
/*     */   public List<String> getCrowbarLore() {
/* 461 */     return this.crowbar_lore;
/*     */   }
/*     */   
/*     */   public int getCrowbarUsesSpawners() {
/* 465 */     return this.crowbar_uses_spawners;
/*     */   }
/*     */   
/*     */   public int getCrowbarUsesPortals() {
/* 469 */     return this.crowbar_uses_portals;
/*     */   }
/*     */   
/*     */   public String getCrowbarSpawnerNameColor() {
/* 473 */     return this.crowbar_spawner_name_color;
/*     */   }
/*     */   
/*     */   public int getGoldenAppleCooldown() {
/* 477 */     return this.golden_apple_cooldown;
/*     */   }
/*     */   
/*     */   public String getDeathsignTimezone() {
/* 481 */     return this.deathsign_timezone;
/*     */   }
/*     */   
/*     */   public String getDeathsignDateFormat() {
/* 485 */     return this.deathsign_date_format;
/*     */   }
/*     */   
/*     */   public String getDeathsignName() {
/* 489 */     return this.deathsign_name;
/*     */   }
/*     */   
/*     */   public List<String> getDeathsignLore() {
/* 493 */     return this.deathsign_lore;
/*     */   }
/*     */   
/*     */   public String getSubclaimSignTitle() {
/* 497 */     return this.subclaim_sign_title;
/*     */   }
/*     */   
/*     */   public String getSubclaimCaptainsOnly() {
/* 501 */     return this.subclaim_captains_only;
/*     */   }
/*     */   
/*     */   public String getSubclaimLeaderOnly() {
/* 505 */     return this.subclaim_leader_only;
/*     */   }
/*     */   
/*     */   public boolean isNetherPortalTrapFixEnabled() {
/* 509 */     return this.nether_portal_trap_fix_enabled;
/*     */   }
/*     */   
/*     */   public String getMobStackColor() {
/* 513 */     return this.mob_stack_color;
/*     */   }
/*     */   
/*     */   public int getMobStackingRadius() {
/* 517 */     return this.mob_stacking_radius;
/*     */   }
/*     */   
/*     */   public List<String> getMobStackingEntity() {
/* 521 */     return this.mob_stacking_entity;
/*     */   }
/*     */   
/*     */   public int getDefaultSotwDuration() {
/* 525 */     return this.default_sotw_duration;
/*     */   }
/*     */   
/*     */   public int getPvPTimerDuration() {
/* 529 */     return this.pvp_timer_duration;
/*     */   }
/*     */   
/*     */   public List<String> getPvpTimerDisabledCommands() {
/* 533 */     return this.pvp_timer_disabled_commands;
/*     */   }
/*     */   
/*     */   public List<Material> getPvPTimerDisabledItems() {
/* 537 */     return this.pvp_timer_disabled_items;
/*     */   }
/*     */   
/*     */   public List<String> getGlassProtectedRegions() {
/* 541 */     return this.glass_protected_regions;
/*     */   }
/*     */   
/*     */   public boolean isStrengthNerfEnabled() {
/* 545 */     return this.strength_nerf_enabled;
/*     */   }
/*     */   
/*     */   public int getStrength1NerfPercentage() {
/* 549 */     return this.strength1_nerf_percentage;
/*     */   }
/*     */   
/*     */   public int getStrength2NerfPercentage() {
/* 553 */     return this.strength2_nerf_percentage;
/*     */   }
/*     */   
/*     */   public int getCombatLoggerTime() {
/* 557 */     return this.combat_logger_time;
/*     */   }
/*     */   
/*     */   public String getMapkitInventoryName() {
/* 561 */     return this.mapkit_inventory_name;
/*     */   }
/*     */   
/*     */   public int getMapkitInventorySize() {
/* 565 */     return this.mapkit_inventory_size;
/*     */   }
/*     */   
/*     */   public int getLogoutTime() {
/* 569 */     return this.logout_time;
/*     */   }
/*     */   
/*     */   public int getRequestCommandDelay() {
/* 573 */     return this.request_command_delay;
/*     */   }
/*     */   
/*     */   public String getOresInventoryName() {
/* 577 */     return this.ores_inventory_name;
/*     */   }
/*     */   
/*     */   public int getReportCommandDelay() {
/* 581 */     return this.report_command_delay;
/*     */   }
/*     */   
/*     */   public String getReportInventoryTitle() {
/* 585 */     return this.report_inventory_title;
/*     */   }
/*     */   
/* 588 */   public int getCpsCountForWarning() { return this.cps_count_for_warning; }
/*     */   
/*     */   public String getUnrepairableLoreLine()
/*     */   {
/* 592 */     return this.unrepairable_lore_line;
/*     */   }
/*     */   
/*     */   public int getLootingBonus() {
/* 596 */     return this.looting_bonus;
/*     */   }
/*     */   
/*     */   public int getFurnaceSpeedMultiplier() {
/* 600 */     return this.furnace_speed_multiplier;
/*     */   }
/*     */   
/*     */   public int getElevatorTeleportDelay() {
/* 604 */     return this.elevator_teleport_delay;
/*     */   }
/*     */   
/*     */   public Material getElevatorBottomBlock() {
/* 608 */     return this.elevator_bottom_block;
/*     */   }
/*     */   
/*     */   public List<Material> getFoundOreMaterials() {
/* 612 */     return this.found_ore_materials;
/*     */   }
/*     */   
/*     */   public boolean isEnableCoreInfo() {
/* 616 */     return this.enable_core_info;
/*     */   }
/*     */   
/*     */   public List<String> getBlockedCommands() {
/* 620 */     return this.blocked_commands;
/*     */   }
/*     */   
/*     */   public int getFreezeMessageInterval() {
/* 624 */     return this.freeze_message_interval;
/*     */   }
/*     */   
/*     */   public List<String> getFreezeDisabledCommands() {
/* 628 */     return this.freeze_disabled_commands;
/*     */   }
/*     */   
/*     */   public List<String> getFreezeCommandMessage() {
/* 632 */     return this.freeze_command_message;
/*     */   }
/*     */   
/*     */   public List<String> getHelpCommandMessage() {
/* 636 */     return this.help_command_message;
/*     */   }
/*     */   
/*     */   public List<String> getListCommandMessage() {
/* 640 */     return this.list_command_message;
/*     */   }
/*     */   
/*     */   public List<String> getOnJoinMessage() {
/* 644 */     return this.on_join_message;
/*     */   }
/*     */   
/*     */   public List<String> getReportMessage() {
/* 648 */     return this.report_message;
/*     */   }
/*     */   
/*     */   public List<String> getSlotsCommandMessage() {
/* 652 */     return this.slots_command_message;
/*     */   }
/*     */   
/*     */   public Location getEndSpawnLocation() {
/* 656 */     return this.end_spawn_location;
/*     */   }
/*     */   
/*     */   public void setEndSpawnLocation(Location paramLocation) {
/* 660 */     this.end_spawn_location = paramLocation;
/*     */   }
/*     */   
/*     */   public ItemStack[] getFirstJoinItems() {
/* 664 */     return this.first_join_items;
/*     */   }
/*     */   
/*     */   public void setFirstJoinItems(ItemStack[] paramArrayOfItemStack) {
/* 668 */     this.first_join_items = paramArrayOfItemStack;
/*     */   }
/*     */   
/*     */   public int getSlotsDefaultAmount() {
/* 672 */     return this.slots_default_amount;
/*     */   }
/*     */   
/*     */   public int getPvpClassWarmupDuration() {
/* 676 */     return this.pvp_class_warmup_duration;
/*     */   }
/*     */   
/*     */   public int getArcherSpeedboostLevel() {
/* 680 */     return this.archer_speedboost_level;
/*     */   }
/*     */   
/*     */   public int getArcherSpeedboostDuration() {
/* 684 */     return this.archer_speedboost_duration;
/*     */   }
/*     */   
/*     */   public int getArcherSpeedboostCooldown() {
/* 688 */     return this.archer_speedboost_cooldown;
/*     */   }
/*     */   
/*     */   public int getArcherTagDuration() {
/* 692 */     return this.archer_tag_duration;
/*     */   }
/*     */   
/*     */   public int getBardClickableItemsCooldown() {
/* 696 */     return this.bard_clickable_items_cooldown;
/*     */   }
/*     */   
/*     */   public ArrayList<PotionEffect> getArcherPotionEffects() {
/* 700 */     return this.archer_potion_effects;
/*     */   }
/*     */   
/*     */   public ArrayList<PotionEffect> getBardPotionEffects() {
/* 704 */     return this.bard_potion_effects;
/*     */   }
/*     */   
/*     */   public ArrayList<PotionEffect> getMinerPotionEffects() {
/* 708 */     return this.miner_potion_effects;
/*     */   }
/*     */   
/*     */   public ArrayList<PotionEffect> getArcherTagEffectsTag1() {
/* 712 */     return this.archer_tag_effects_tag1;
/*     */   }
/*     */   
/*     */   public ArrayList<PotionEffect> getArcherTagEffectsTag2() {
/* 716 */     return this.archer_tag_effects_tag2;
/*     */   }
/*     */   
/* 719 */   public ArrayList<PotionEffect> getArcherTagEffectsTag3() { return this.archer_tag_effects_tag3; }
/*     */   
/*     */   public List<String> getCoordsMessage()
/*     */   {
/* 723 */     return this.coords_message;
/*     */   }
/*     */   
/*     */   public boolean isDeathbanEnabled() {
/* 727 */     return this.deathban_enabled;
/*     */   }
/*     */   
/*     */   public List<String> getDeathbanUsageMessage() {
/* 731 */     return this.deathban_usage_message;
/*     */   }
/*     */   
/*     */   public List<String> getDeathbanCheckMessage() {
/* 735 */     return this.deathban_check_message;
/*     */   }
/*     */   
/*     */   public List<String> getLivesUsageMessage() {
/* 739 */     return this.lives_usage_message;
/*     */   }
/*     */   
/*     */   public List<String> getSubclaimCommandMessage() {
/* 743 */     return this.subclaim_command_message;
/*     */   }
/*     */   
/*     */   public List<String> getStatsCommandMessage() {
/* 747 */     return this.stats_command_message;
/*     */   }
/*     */   
/*     */   public int getMedicReviveCooldown() {
/* 751 */     return this.medic_revive_cooldown;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\config\ConfigHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */