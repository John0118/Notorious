/*     */ package me.qiooip.notorious.utils;
/*     */ 
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public enum Language
/*     */ {
/*   8 */   PREFIX("PREFIX", "&8[&c&lCore&8] "), 
/*     */   
/*  10 */   JOIN_MESSAGE("JOIN_MESSAGE", "&3&l[&a&lJoin&3&l] &a&l<player>"), 
/*  11 */   QUIT_MESSAGE("QUIT_MESSAGE", "&3&l[&c&lQuit&3&l] &c&l<player>"), 
/*     */   
/*  13 */   COMMANDS_FOR_PLAYER_USE_ONLY("COMMANDS.FOR_PLAYER_USE_ONLY", "&4Sorry. &cFor player use only!"), 
/*  14 */   COMMANDS_FOR_CONSOLE_USE_ONLY("COMMANDS.FOR_CONSOLE_USE_ONLY", "&4Sorry. &cFor console use only!"), 
/*  15 */   COMMANDS_NO_PERMISSION_MESSAGE("COMMANDS.NO_PERMISSION_MESSAGE", "&4Sorry. &cYou do not have permission."), 
/*  16 */   COMMANDS_FORBIDDEN_COMMAND_MESSAGE("COMMANDS.FORBIDDEN_COMMAND_MESSAGE", "&7This command is &3Forbidden&7!"), 
/*  17 */   COMMANDS_INVALID_NUMBER("COMMANDS.INVALID_NUMBER", "&7Please use a valid &3Number&7!"), 
/*  18 */   COMMANDS_PLAYER_NOT_ONLINE("COMMANDS.PLAYER_NOT_ONLINE", "&7Player &3<player> &7is not online!"), 
/*     */   
/*  20 */   PVPTIMER_FORMAT_ENABLE("PVPTIMER.FORMAT_ENABLE", "&7/&3pvp enable &7- Enable PvP for yourself"), 
/*  21 */   PVPTIMER_FORMAT_TIME("PVPTIMER.FORMAT_TIME", "&7/&3pvp time &7- Check time left on your timer"), 
/*  22 */   PVPTIMER_TIMER_NOT_ACTIVE("PVPTIMER.TIMER_NOT_ACTIVE", "&7Your &3&lPvP Timer &7is not active!"), 
/*  23 */   PVPTIMER_TIMER_DISABLED("PVPTIMER.TIMER_DISABLED", "&7You have disabled your &3&lPvP Timer&7!"), 
/*  24 */   PVPTIMER_TIMER_STATUS("PVPTIMER.TIMER_STATUS", "&7Your &3&lPvP Timer &7time is: &c<time>"), 
/*  25 */   PVPTIMER_FROZEN_MESSAGE("PVPTIMER.FROZEN_MESSAGE", "&7Your &3&lPvP Timer &7is now &afrozen&7."), 
/*  26 */   PVPTIMER_UNFROZEN_MESSAGE("PVPTIMER.UNFROZEN_MESSAGE", "&7Your &3&lPvP Timer &7is now &cunfrozen&7."), 
/*  27 */   PVPTIMER_PVPDENY_ATTACKER("PVPTIMER.PVPDENY_ATTACKER", "&7You can't attack &3<name> &7while his &3PvP Timer &7is active!"), 
/*  28 */   PVPTIMER_PVPDENY_VICTIM("PVPTIMER.PVPDENY_VICTIM", "&7You can't attack other players while your &3PvP Timer &7is active!"), 
/*  29 */   PVPTIMER_COMMAND_DENY_MESSAGE("PVPTIMER.COMMAND_DENY_MESSAGE", "&7You can't use this &3Command &7with active PvP Timer!"), 
/*  30 */   PVPTIMER_ITEM_DENY_MESSAGE("PVPTIMER.ITEM_DENY_MESSAGE", "&7You can't use &3<item> &7with active PvP Timer!"), 
/*  31 */   PVPTIMER_END_PORTAL_TELEPORT_DENY("PVPTIMER.END_PORTAL_TELEPORT_DENY", "&7You can't use &3Portals &7with active &3PvP Timer&7!"), 
/*     */   
/*  33 */   FREEZE_USAGE("FREEZE.USAGE", "&7Usage: &3/&7freeze &3<&7player&3>"), 
/*  34 */   FREEZE_DISABLED_COMMAND_MESSAGE("FREEZE.DISABLED_COMMAND_MESSAGE", "&7You can't use this &3Command &7when frozen!"), 
/*  35 */   FREEZE_CAN_NOT_FREEZE_YOURSELF("FREEZE.CAN_NOT_FREEZE_YOURSELF", "&7You can not freeze &3yourself&7!"), 
/*  36 */   FREEZE_FREEZED_MESSAGE_PLAYER("FREEZE.FREEZED_MESSAGE.PLAYER", "&eYou just froze &2<target>&e."), 
/*  37 */   FREEZE_UNFREEZED_MESSAGE_PLAYER("FREEZE.UNFREEZED_MESSAGE.PLAYER", "&eYou just unfroze &2<target>&e."), 
/*  38 */   FREEZE_UNFREEZED_MESSAGE_TARGET("FREEZE.UNFREEZED_MESSAGE.TARGET", "&eYou have been unfrozen by &2<player>&e."), 
/*  39 */   FREEZE_PVP_DENY_MESSAGE_DAMAGER("FREEZE.PVP_DENY_MESSAGE.DAMAGER", "&7You can not &3damage &7other players while frozen!"), 
/*  40 */   FREEZE_PVP_DENY_MESSAGE_VICTIM("FREEZE.PVP_DENY_MESSAGE.VICTIM", "&3<player> &7is frozen. You can not &3damage &7him!"), 
/*  41 */   FREEZE_QUIT_WHEN_FROZEN("FREEZE.QUIT_WHEN_FROZEN", "&3Attention&7! &c<player> &7left when frozen!"), 
/*     */   
/*  43 */   GOLDENAPPLE_COOLDOWN_STARTED_MESSAGE("GOLEDNAPPLE.COOLDOWN_STARTED_MESSAGE", "&7You have used &3Super Golden Apple&7. Starting cooldown at &c<time>&7."), 
/*  44 */   GOLDENAPPLE_COOLDOWN_DENY_MESSAGE("GOLEDNAPPLE.COOLDOWN_DENY_MESSAGE", "&7You are on &3Super Golden Apple &7cooldown for: &c<time>&7."), 
/*     */   
/*  46 */   CHATCONTROL_CHAT_MUTED("CHATCONTROL.CHAT.MUTED", "&7Chat was &cMuted &7by &3<player>&7."), 
/*  47 */   CHATCONTROL_CHAT_ALREADY_MUTED("CHATCONTROL.CHAT.ALREADY_MUTED", "&7Chat is already &3Muted&7!"), 
/*  48 */   CHATCONTROL_CHAT_UNMUTED("CHATCONTROL.CHAT.UNMUTED", "&7Chat was &aUnmuted &7by &3<player>&7."), 
/*  49 */   CHATCONTROL_CHAT_NOT_MUTED("CHATCONTROL.CHAT.NOT_MUTED", "&7Chat is not &3Muted&7!"), 
/*  50 */   CHATCONTROL_CHAT_DELAY_BROADCAST("CHATCONTROL.CHAT.DELAY_BROADCAST", "&3Chat &7delay has been set to &c<delay>&7!"), 
/*  51 */   CHATCONTROL_CHAT_CLEAR_BROADCAST("CHATCONTROL.CHAT.CLEAR_BROADCAST", "&3Chat &7has been cleared by &c<player>&7."), 
/*  52 */   CHATCONTROL_CHATEVENT_MUTED_MESSAGE("CHATCONTROL.CHATEVENT.MUTED_MESSAGE", "&7You can't send messages while &3Chat &7is muted!"), 
/*  53 */   CHATCONTROL_CHATEVENT_DELAY_MESSAGE("CHATCONTROL.CHATEVENT.DELAY_MESSAGE", "&7You can't use &3Chat &7for &c<seconds> &7more seconds."), 
/*  54 */   CHATCONTROL_COMMAND_MUTE_FORMAT("CHATCONTROL.COMMAND.MUTE_FORMAT", "&7/&3chat mute"), 
/*  55 */   CHATCONTROL_COMMAND_UNMUTE_FORMAT("CHATCONTROL.COMMAND.UNMUTE_FORMAT", "&7/&3chat unmute"), 
/*  56 */   CHATCONTROL_COMMAND_CLEAR_FORMAT("CHATCONTROL.COMMAND.CLEAR_FORMAT", "&7/&3chat clear"), 
/*  57 */   CHATCONTROL_COMMAND_DELAY_FORMAT("CHATCONTROL.COMMAND.DELAY_FORMAT", "&7/&3chat delay &7<&3seconds&7>"), 
/*     */   
/*  59 */   COBBLECOMMAND_ENABLED("COBBLECOMMAND.ENABLED", "&3Cobblestone &7pickup &aEnabled&7."), 
/*  60 */   COBBLECOMMAND_DISABLED("COBBLECOMMAND.DISABLED", "&3Cobblestone &7pickup &cDisabled&7."), 
/*     */   
/*  62 */   CPSCOUNT_WARNING_MESSAGE("CPSCOUNT.WARNING_MESSAGE", "&7Player &3<player> &7might be using &cClicker&7! &a[&c<cps>&a]"), 
/*  63 */   CPSCOUNT_COMMAND_FORMAT("CPSCOUNT.COMMAND.FORMAT", "&7/&3cps &7<&3player&7>"), 
/*  64 */   CPSCOUNT_COMMAND_PLAYER_NOT_ONLINE("CPSCOUNT.COMMAND.PLAYER_NOT_ONLINE", "&7Player &3<player> &7is not online!"), 
/*  65 */   CPSCOUNT_COMMAND_MESSAGE_FORMAT("CPSCOUNT.COMMAND.MESSAGE_FORMAT", "&7Player: &c<player> &7CPS Count: &c<cps>"), 
/*  66 */   CPSCOUNT_COMMAND_PLAYER_NOT_IN_DATABASE("CPSCOUNT.COMMAND.LAYER_NOT_IN_DATABASE", "&7Player &3<player> &7is not in database!"), 
/*     */   
/*  68 */   CROWBAR_ZERO_USAGES_SPAWNERS("CROWBAR.ZERO_USAGES.SPAWNERS", "&7This &3Crowbar &7has &c0 &7more usages for &3Spawners&7!"), 
/*  69 */   CROWBAR_ZERO_USAGES_PORTALS("CROWBAR.ZERO_USAGES.PORTALS", "&7This &3Crowbar &7has &c0 &7more usages for &3Portals&7!"), 
/*  70 */   CROWBAR_DENY_USAGE_MESSAGE("CROWBAR.DENY_USAGE.MESSAGE", "&7For use on &3Spawners &7and &3Portal Frames&7 only!"), 
/*  71 */   CROWBAR_DENY_USAGE_NETHER("CROWBAR.DENY_USAGE.NETHER", "&3Crowbar &7is disabled in &3Nether&7!"), 
/*  72 */   CROWBAR_DENY_USAGE_END("CROWBAR.DENY_USAGE.END", "&3Crowbar &7is disabled in &3End&7!"), 
/*     */   
/*  74 */   DEATHMESSAGE_PLAYER_NAME_FORMAT("DEATHMESSAGE.PLAYER_NAME_FORMAT", "&3<player>&8[&4<kills>&8]"), 
/*  75 */   DEATHMESSAGE_KILLER_NAME_FORMAT("DEATHMESSAGE.KILLER_NAME_FORMAT", "&3<killer>&8[&4<kills>&8]"), 
/*  76 */   DEATHMESSAGE_REASON_BLOCK_EXPLOSION("DEATHMESSAGE.REASON.BLOCK_EXPLOSION", "<player> &7was blown up by &3TNT&7."), 
/*  77 */   DEATHMESSAGE_REASON_CONTACT("DEATHMESSAGE.REASON.CONTACT", "<player> &7was slain by angry &3Cactus&7."), 
/*  78 */   DEATHMESSAGE_REASON_CUSTOM("DEATHMESSAGE.REASON.CUSTOM", "<player> &7died."), 
/*  79 */   DEATHMESSAGE_REASON_DROWNING("DEATHMESSAGE.REASON.DROWNING", "<player> &7don't know how to &3Swim&7."), 
/*  80 */   DEATHMESSAGE_REASON_ENTITY_ATTACK_ENTITY("DEATHMESSAGE.REASON.ENTITY_ATTACK_ENTITY", "<player> &7was slain by &3<entity>&7."), 
/*  81 */   DEATHMESSAGE_REASON_ENTITY_ATTACK_PLAYER_ITEM("DEATHMESSAGE.REASON.ENTITY_ATTACK_PLAYER_ITEM", "<player> &7was slain by <killer> &7using &3<item>&7."), 
/*  82 */   DEATHMESSAGE_REASON_ENTITY_ATTACK_PLAYER_NO_ITEM("DEATHMESSAGE.REASON.ENTITY_ATTACK_PLAYER_NO_ITEM", "<player> &7was slain by <killer>&7."), 
/*  83 */   DEATHMESSAGE_REASON_ENTITY_EXPLOSION("DEATHMESSAGE.REASON.ENTITY_EXPLOSION", "<player> &7was blown up by &3Creeper&7."), 
/*  84 */   DEATHMESSAGE_REASON_FALL("DEATHMESSAGE.REASON.FALL", "<player> &7hit the grount too hard."), 
/*  85 */   DEATHMESSAGE_REASON_FALLING_BLOCK("DEATHMESSAGE.REASON.FALLING_BLOCK", "<player> &7died to a &3Falling Block&7."), 
/*  86 */   DEATHMESSAGE_REASON_FIRE("DEATHMESSAGE.REASON.FIRE", "<player> &7went up in flames."), 
/*  87 */   DEATHMESSAGE_REASON_FIRE_TICK("DEATHMESSAGE.REASON.FIRE_TICK", "<player> &7went up in flames."), 
/*  88 */   DEATHMESSAGE_REASON_LAVA("DEATHMESSAGE.REASON.LAVA", "<player> &7tried to swim in &3Lava&7."), 
/*  89 */   DEATHMESSAGE_REASON_LIGHTNING("DEATHMESSAGE.REASON.LIGHTNING", "<player> &7was slain by &3Lightning&7. :O"), 
/*  90 */   DEATHMESSAGE_REASON_MAGIC("DEATHMESSAGE.REASON.MAGIC", "<player> &7died to a &3Voodoo&7."), 
/*  91 */   DEATHMESSAGE_REASON_MELTING("DEATHMESSAGE.REASON.MELTING", "<player> &7died."), 
/*  92 */   DEATHMESSAGE_REASON_POISON("DEATHMESSAGE.REASON.POISON", "<player> &7was poisoned."), 
/*  93 */   DEATHMESSAGE_REASON_PROJECTILE_ENTITY("DEATHMESSAGE.REASON.PROJECTILE_ENTITY", "<player> &7was shoot by &3Skeleton&7."), 
/*  94 */   DEATHMESSAGE_REASON_PROJECTILE_ITEM("DEATHMESSAGE.REASON.PROJECTILE_ITEM", "<player> &7was shoot by <killer> &7using &3<item>&7."), 
/*  95 */   DEATHMESSAGE_REASON_PROJECTILE_NO_ITEM("DEATHMESSAGE.REASON.PROJECTILE_NO_ITEM", "<player> &7was shoot by <killer>&7."), 
/*  96 */   DEATHMESSAGE_REASON_STARVATION("DEATHMESSAGE.REASON.STARVATION", "<player> &7forgot to &3Eat&7."), 
/*  97 */   DEATHMESSAGE_REASON_SUFFOCATION("DEATHMESSAGE.REASON.SUFFOCATION", "<player> &7suffocated."), 
/*  98 */   DEATHMESSAGE_REASON_SUICIDE("DEATHMESSAGE.REASON.SUICIDE", "<player> &7killed himself."), 
/*  99 */   DEATHMESSAGE_REASON_THORNS("DEATHMESSAGE.REASON.THORNS", "<player> &7was killed by &aThorns&7."), 
/* 100 */   DEATHMESSAGE_REASON_VOID("DEATHMESSAGE.REASON.VOID", "<player> &7fell into the &3Void&7."), 
/* 101 */   DEATHMESSAGE_REASON_WITHER("DEATHMESSAGE.REASON.WITHER", "<player> &7withered away."), 
/*     */   
/* 103 */   DEATHBAN_BAN_MESSAGE("DEATHBAN.BAN_MESSAGE", "&7You are &3DeathBanned &7for: &c<time>&7."), 
/* 104 */   DEATHBAN_JOIN_AGAIN_FOR_REVIVE("DEATHBAN.JOIN_AGAIN_FOR_REVIVE", "&7You have &3<amount> &7live(s). Please join again to use &3Live&7."), 
/* 105 */   DEATHBAN_COMMAND_CHECK_USAGE("DEATHBAN.COMMAND.CHECK_USAGE", "&3Usage&7: /&3deathban check &7[&3player&7]"), 
/* 106 */   DEATHBAN_COMMAND_REVIVE_USAGE("DEATHBAN.COMMAND.REVIVE_USAGE", "&3Usage&7: /&3deathban revive &7[&3player&7]"), 
/* 107 */   DEATHBAN_COMMAND_INVENTORY_ROLLBACK_USAGE("DEATHBAN.COMMAND.INVENTORY_ROLLBACK_USAGE", "&3Usage&7: /&3deathban inventory rollback &7[&3player&7]"), 
/* 108 */   DEATHBAN_PLAYER_NOT_DEATHBANNED("DEATHBAN.PLAYER_NOT_DEATHBANNED", "&7Player &3<player> &7is not &3DeathBanned&7!"), 
/* 109 */   DEATHBAN_SUCCESSFULLY_REVIVED_PLAYER("DEATHBAN.SUCCESSFULLY_REVIVED_PLAYER", "&7You successfully revived &3<player>&7."), 
/* 110 */   DEATHBAN_SUCCESSFULLY_ROLLBACKED_INVENTORY("DEATHBAN.SUCCESSFULLY_ROLLBACKED_INVENTORY", "&7Successfully rollbacked &3<player>'s &7inventory."), 
/* 111 */   DEATHBAN_SUCCESSFULLY_ROLLBACKED_INVENTORY_PLAYER("DEATHBAN.SUCCESSFULLY_ROLLBACKED_INVENTORY_PLAYER", "&7Your inventory was rollbacked by &3<player>&7."), 
/* 112 */   DEATHBAN_INVENTORY_ALREADY_ROLLBACKED("DEATHBAN.INVENTORY_ALREADY_ROLLBACKED", "&7Somebody already rollbacked &3<player>'s &7inventory!"), 
/* 113 */   DEATHBAN_PLAYER_HAS_NOT_DIED_YET("DEATHBAN.PLAYER_HAS_NOT_DIED_YET", "&7Player &3<player> &7has not died yet!"), 
/*     */   
/* 115 */   LIVES_COMMAND_REVIVE_USAGE("LIVES.COMMAND.REVIVE_USAGE", "&3Usage&7: /&3lives revive &7[&3player&7]"), 
/* 116 */   LIVES_COMMAND_SEND_USAGE("LIVES.COMMAND.SEND_USAGE", "&3Usage&7: /&3lives send &7[&3player&7] [&3amount&7]"), 
/* 117 */   LIVES_COMMAND_SUCCESSFULLY_REVIVED_PLAYER("", "&7You successfully revived &3<player>&7."), 
/* 118 */   LIVES_COMMAND_SUCCESSFULLY_SENT_LIVES("LIVES.COMMAND.SUCCESSFULLY_SENT_LIVES", "&7You sent &3<amount> &7lives to &3<player>&7."), 
/* 119 */   LIVES_COMMAND_PLAYER_NOT_IN_DATABASE("LIVES.COMMAND.PLAYER_NOT_IN_DATABASE", "&3<player> &7is not in database!"), 
/* 120 */   LIVES_COMMAND_CAN_NOT_SEND_LIVES_TO_YOURSELF("LIVES.COMMAND.CAN_NOT_SEND_LIVES_TO_YOURSELF", "&7You can't send lives to &3Yourself&7!"), 
/* 121 */   LIVES_COMMAND_PLAYER_NOT_ONLINE("LIVES.COMMAND.PLAYER_NOT_ONLINE", "&7Player &3<player> &7is not online!"), 
/* 122 */   LIVES_COMMAND_CHECK_SELF("LIVES.COMMAND.CHECK_SELF", "&3Your lives: &c<lives>"), 
/* 123 */   LIVES_COMMAND_CHECK_OTHERS("LIVES.COMMAND.CHECK_OTHERS", "&3<player>'s lives: &c<lives>"), 
/* 124 */   LIVES_COMMAND_LIVES_ADD_RECEIVED("LIVES.COMMAND.LIVES_ADD_RECEIVED", "&3<player> &7added &3<amount> &7lives to you account."), 
/* 125 */   LIVES_COMMAND_LIVES_ADDED("LIVES.COMMAND.LIVES_ADDED", "&7You added &3<amount> &7lives to &3<player>'s &7account."), 
/* 126 */   LIVES_COMMAND_LIVES_SET_RECEIVED("LIVES.COMMAND.LIVES_SET_RECEIVED", "&7Your lives were set to &3<amount> &7by &3<player>&7."), 
/* 127 */   LIVES_COMMAND_LIVES_SET("LIVES.COMMAND.LIVES_SET", "&7You set &3<player>'s &7lives to &3<amount>&7."), 
/* 128 */   LIVES_COMMAND_ZERO_LIVES("LIVES.COMMAND.ZERO_LIVES", "&7You have &30 &7lives!"), 
/* 129 */   LIVES_COMMAND_NOT_ENOUGH_LIVES("LIVES.COMMAND.NOT_ENOUGH_LIVES", "&cYou don't have &3<amount> &7lives."), 
/*     */   
/* 131 */   SPAWNERS_DISABLE_PLACE_NETHER("SPAWNERS.DISABLE_PLACE.END", "&3Spawner &7placing is disabled in &3Nether&7!"), 
/* 132 */   SPAWNERS_DISABLE_PLACE_END("SPAWNERS.DISABLE_PLACE.END", "&3Spawner &7placing is disabled in &3End&7!"), 
/* 133 */   SPAWNERS_DISABLE_BREAK_NETHER("SPAWNERS.DISABLE_BREAK.NETHER", "&3Spawner &7breaking is disabled in &3Nether&7!"), 
/* 134 */   SPAWNERS_DISABLE_BREAK_END("SPAWNERS.DISABLE_BREAK.END", "&3Spawner &7breaking is disabled in &3End&7!"), 
/*     */   
/* 136 */   SOTW_FORMAT_START("SOTW.FORMAT_START", "&7/&3SOTW start &7<&3time&7>"), 
/* 137 */   SOTW_FORMAT_STOP("SOTW.FORMAT_STOP", "&7/&3SOTW stop"), 
/* 138 */   SOTW_NOT_RUNNING("SOTW.NOT_RUNNING", "&3SOTW &7timer is currently not running!"), 
/* 139 */   SOTW_ALREADY_RUNNING("SOTW.ALREADY_RUNNING", "&7There is &3SOTW &7timer already running!"), 
/*     */   
/* 141 */   EOTW_FORMAT_START("EOTW.FORMAT_START", "/eotw start <time>"), 
/* 142 */   EOTW_FORMAT_STOP("EOTW.FORMAT_STOP", "/eotw stop"), 
/* 143 */   EOTW_NOT_RUNNING("EOTW.NOT_RUNNING", "&3EOTW &7is currently not active!"), 
/* 144 */   EOTW_ALREADY_RUNNING("EOTW.ALREADY_RUNNING", "&3EOTW &7is already active!"), 
/* 145 */   EOTW_TIMER_STARTED("EOTW.TIMER_STARTED", "&3EOTW &7timer has been activated. &3EOTW &7will start in &4<seconds> &7seconds!"), 
/* 146 */   EOTW_BROADCAST_START("EOTW.BROADCAST_START", "&4Warning! &3EOTW &7has been activated. All factions became raidable."), 
/* 147 */   EOTW_BROADCAST_STOP("EOTW.BROADCAST_STOP", "&3EOTW &7has been canceled."), 
/*     */   
/* 149 */   ORES_COMMAND_FORMAT("ORES.COMMAND_FORMAT", "&7/&3ores &7<&3player&7>"), 
/* 150 */   ORES_MINED_FORMAT("ORES.MINED_FORMAT", "&3Mined&7: &c<amount>"), 
/*     */   
/* 152 */   COMBATTAG_MESSAGE_TAGGER("COMBATTAG.MESSAGE.TAGGER", "&7You have combat tagged &3&l<name> &7for &3&l<time> &7seconds."), 
/* 153 */   COMBATTAG_MESSAGE_OTHER("COMBATTAG.MESSAGE.OTHER", "&7You have been tagged by &3&l<name> &7for &3&l<time> &7seconds."), 
/* 154 */   COMBATTAG_COMMAND_DENY_MESSAGE("COMBATTAG.COMMAND_DENY_MESSAGE", "&7You can't use this &3Command &7in combat!"), 
/* 155 */   COMBATTAG_END_PORTAL_TELEPORT_DENY("COMBATTAG.END_PORTAL_TELEPORT_DENY", "&7You can't use &3Portals &7when in &3Combat&7!"), 
/*     */   
/* 157 */   COMBATLOGGER_DEATHMESSAGE_WITH_KILLER("COMBATLOGGER.DEATHMESSAGE.WITH_KILLER", "&c(&7CombatLogger&c) &3<player>&8[&4<kills1>&8] &7has been killed by &3<killer>&8[&4<kills2>&8]&7."), 
/* 158 */   COMBATLOGGER_DEATHMESSAGE_NO_KILLER("COMBATLOGGER.DEATHMESSAGE.NO_KILLER", "&c(&7CombatLogger&c) &3<player>&8[&4<kills1>&8] &7has died."), 
/* 159 */   COMBATLOGGER_ON_DEATH_MESSAGE("COMBATLOGGER.ON_DEATH_MESSAGE", "&7You were &3Killed &7because your &3CombatLogger &7died."), 
/*     */   
/* 161 */   LOGOUT_KICK_MESSAGE("LOGOUT.KICK_MESSAGE", "You have been safely logged out."), 
/* 162 */   LOGOUT_TELEPORT_CANCELLED("LOGOUT.TELEPORT_CANCELLED", "&3Logout &7cancelled because you &4<reason>&7!"), 
/* 163 */   LOGOUT_ALREADY_RUNNING("LOGOUT.ALREADY_RUNNING", "&7You &3Logout &7task is already running!"), 
/* 164 */   LOGOUT_START_MESSAGE("LOGOUT.START_MESSAGE", "&aYou will be safely &3Logged &aout in &c<seconds> &aseconds."), 
/*     */   
/* 166 */   ENDERPEARL_DENY_MESSAGE("ENDERPEARL.DENY_MESSAGE", "&7You can't use &2&lEnderPearl &7for &2&l<time> &7more seconds!"), 
/*     */   
/* 168 */   ENCHANTMENTLIMITER_NOT_REPAIRABLE_MESSAGE("ENCHANTMENTLIMITER.NOT_REPAIRABLE_MESSAGE", "&7This item is not &3Repairable&7!"), 
/* 169 */   ENCHANTMENTLIMITER_CAN_NOT_MERGE("ENCHANTMENTLIMITER.CAN_NOT_MERGE", "&7You can't merge those &3Items&7!"), 
/*     */   
/* 171 */   FIRSTJOINITEMS_COMMAND_SET_FORMAT("FIRSTTIMEITEMS.COMMAND_SET_FORMAT", "&7/&3joinitems set"), 
/* 172 */   FIRSTJOINITEMS_COMMAND_REMOVE_FORMAT("FIRSTTIMEITEMS.COMMAND_REMOVE_FORMAT", "&7/&3joinitems remove"), 
/* 173 */   FIRSTJOINITEMS_COMMAND_ADD_SUCCESSFUL("FIRSTTIMEITEMS.COMMAND_ADD_SUCCESSFUL", "&7You have successfully set &3First Join &7items."), 
/* 174 */   FIRSTJOINITEMS_COMMAND_REMOVE_SUCCESSFUL("FIRSTTIMEITEMS.COMMAND_REMOVE_SUCCESSFUL", "&7You have successfully removed &3First Join &7items."), 
/*     */   
/* 176 */   COMBATCLASS_WARMING_UP("COMBATCLASS.WARMING_UP", "&5&l<name> &bclass warming up."), 
/* 177 */   COMBATCLASS_WARMUP_CANCELLED("COMBATCLASS.WARMUP_CANCELLED", "&5&l<name> &cclass warmup cancelled!"), 
/* 178 */   COMBATCLASS_CLASS_DISABLED("COMBATCLASS.CLASS_DISABLED", "&5&l<name> &cclass disabled!"), 
/* 179 */   COMBATCLASS_CLASS_ACTIVATED("COMBATCLASS.CLASS_ACTIVATED", "&5&l<name> &bclass activated."), 
/*     */   
/* 181 */   ALERTS_COMMAND_ENABLED("ALERTS_COMMAND.ENABLED", "&7You have &aEnabled &7alerts recieving."), 
/* 182 */   ALERTS_COMMAND_DISABLED("ALERTS_COMMAND.DISABLED", "&7You have &cDisabled &7alerts recieving."), 
/*     */   
/* 184 */   STAFFSCOREBOARD_COMMAND_ENABLED("STAFFSCOREBOARD_COMMAND.ENABLED", "&7You have &aEnabled &7staff scoreboard."), 
/* 185 */   STAFFSCOREBOARD_COMMAND_DISABLED("STAFFSCOREBOARD_COMMAND.DISABLED", "&7You have &cDisabled &7staff scoreboard."), 
/*     */   
/* 187 */   POTIONLIMITER_DENY_MESSAGE("POTIONLIMITER.DRINK_DENY_MSSAGE", "&7This &3PotionEffect &7is disabled!"), 
/*     */   
/* 189 */   ARCHER_SPEEDBOOST_ACTIVATED("ARCHER.SPEEDBOOST.ACTIVATED", "&3&lArcher &7speed boost activated for &3<seconds> &7seconds."), 
/* 190 */   ARCHER_SPEEDBOOST_COOLDOWN_DENY("ARCHER.SPEEDBOOST.COOLDOWN_DENY", "&7You can't use &3&lArcher &7speed boost for &3<seconds> &7seconds."), 
/* 191 */   ARCHER_TAG_VICTIM("ARCHER.MARK.VICTIM", "&7Archer tagged by &3<tagger>&7! Tag <level>: &c<effect>"), 
/* 192 */   ARCHER_TAG_TAGGER("ARCHER.MARK.TAGGER", "&7You archer tagged &3<victim>&7! Tag <level>: &c<effect>"), 
/*     */   
/* 194 */   BARD_CLICKABLE_ITEMS_ACTIVE_COOLDOWN("BARD.CLICKABLE_ITEMS.ACTIVE_COOLDOWN", "&7You are on cooldown for &3&l<seconds> &7more seconds!"), 
/* 195 */   BARD_CLICKABLE_ITEMS_NOT_ENOUGH_ENERGY("BARD.CLICKABLE_ITEMS.NOT_ENOUGH_ENERGY", "&7You don't have enough &3&lEnergy &7for that effect!"), 
/* 196 */   BARD_NOT_IN_FACTION_MESSAGE("BARD.NOT_IN_FACTION_MESSAGE", "&7You can't bard while not in &3Faction&7!"), 
/* 197 */   BARD_CLICKABLE_ITEM_MESSAGE_BARD_FRIENDLY("BARD.CLICKABLE_ITEM.MESSAGE_BARD.FRIENDLY", "&7You have given your faction members &a&l<effect>&7."), 
/* 198 */   BARD_CLICKABLE_ITEM_MESSAGE_BARD_ENEMY("BARD.CLICKABLE_ITEM.MESSAGE_BARD.ENEMY", "&7You have given your enemies &c&l<effect>&7."), 
/* 199 */   BARD_CLICKABLE_ITEM_MESSAGE_OTHERS("BARD.CLICKABLE_ITEM.MESSAGE_OTHERS", "&7You have been given &a&l<effect>&7."), 
/*     */   
/* 201 */   BOOKDISENCHANT_MESSAGE("BOOKDISENCHANT_MESSAGE", "&7You have removed all &3Enchantments &7from this book."), 
/*     */   
/* 203 */   ITEM_DISABLE_MESSAGE("ITEM_DISABLE_MESSAGE", "&7You can't interract with &3<block>&7!"), 
/*     */   
/* 205 */   MAPKIT_EDIT_MESSAGE("MAPKIT.EDIT_MESSAGE", "&7You successfully edited &3MapKit&7."), 
/*     */   
/* 207 */   PLATE_ELEVATOR_CREATED_MESSAGE("PLATE_ELEVATOR.CREATED_MESSAGE", "&7You have successfully created &3Elevator&7."), 
/* 208 */   PLATE_ELEVATOR_DESTROYED_MESSAGE("PLATE_ELEVATOR.DESTROYED_MESSAGE", "&7You have successfully destroyed &4Elevator&7."), 
/* 209 */   PLATE_ELEVATOR_TELEPORT_MESSAGE("PLATE_ELEVATOR.TELEPORT_MESSAGE", "&7Teleporting in &3<seconds> &7seconds."), 
/* 210 */   PLATE_ELEVATOR_TELEPORT_CANCELLED("PLATE_ELEVATOR.TELEPORT_CANCELLED", "&7Teleport cancelled because you &4<reason>&7!"), 
/*     */   
/* 212 */   NOTES_COMMAND_CHECK_USAGE("NOTES_COMMAND_USAGE", "&7/&3notes check &7<&3player&7>"), 
/* 213 */   NOTES_COMMAND_ADD_USAGE("NOTES.COMMAND.ADD_USAGE", "&7/&3notes add &7<&3player&7> &7<&3note&7>"), 
/* 214 */   NOTES_COMMAND_REMOVE_USAGE("NOTES.COMMAND.REMOVE_USA", "&7/&3notes remove &7<&3player&7> &7<&3number&7>"), 
/* 215 */   NOTES_MESSAGE("NOTES.MESSAGE", "&7Notes of player &3<player>&7:"), 
/* 216 */   NOTES_FORMAT("NOTES.FORMAT", " &3<number>. &7<note>"), 
/* 217 */   NOTES_NO_NOTES("NOTES.NO_NOTES", "&7Player &3<player> &7doesn't have notes."), 
/* 218 */   NOTES_NOTE_ADDED("NOTES.NOTE_ADDED", "&7Successfully added note for player &3<player>&7."), 
/* 219 */   NOTES_NOTE_REMOVED("NOTES.NOTE_REMOVED", "&7Note with number &3<number> &7has been removed."), 
/* 220 */   NOTES_NOTE_DOESNT_EXIST("NOTES.NOTE_DOESNT_EXIST", "&7Note with number &3<number> &7doesn't exist."), 
/*     */   
/* 222 */   CORE_RELOAD_MESSAGE("CORE_RELOAD_MESSAGE", "&dConfig and Language files successfully reloaded."), 
/*     */   
/* 224 */   FOUND_ORE_MESSAGE("FOUND_ORE.MESSAGE", "&4[&cFO&4] &3<player> &7has found &c<count> &7of &3<material>&7."), 
/* 225 */   FOUND_ORE_RECIEVING_ENABLED("FOUND_ORE.RECIEVING_ENABLED", "&7FoundOre alerts recieveing &aenabled&7."), 
/* 226 */   FOUND_ORE_RECIEVING_DISABLED("FOUND_ORE.RECIEVING_DISABLED", "&7FoundOre alerts recieveing &cdisabled&7."), 
/*     */   
/* 228 */   JOIN_FULL_SERVER_MESSAGE("JOIN_FULL_SERVER.MESSAGE", "&cServer is &4Full&c. In order to play donate at: &bshop.example.com"), 
/*     */   
/* 230 */   PLATINUM_REVIVE_COMMAND_USAGE("MEDIC_REVIVE.COMMAND_USAGE", "&7/&3medic revive &7<&3player&7>"), 
/* 231 */   PLATINUM_REVIVE_MESSAGE("MEDIC_REVIVE.MESSAGE", "&7* &6<player> &3has used his &bMedic &3rank to revive &6<target>&3."), 
/* 232 */   PLATINUM_REVIVE_COOLDOWN("MEDIC_REVIVE.COOLDOWN", "&7You can't use &bMedic &7revive for &c&l<time> &7more minutes!"), 
/*     */   
/* 234 */   REQUEST_COMMAND_FORMAT("REQUEST.COMMAND_FORMAT", "&7/&3request &7<&3message&7>"), 
/* 235 */   REQUEST_STAFF_MESSAGE_FORMAT("REQUEST.STAFF_MESSAGE_FORMAT", "&4[&3Request&4] &b<player> &7: &c<message>"), 
/* 236 */   REQUEST_DELAY_MESSAGE("REQEST.DELAY_MESSAGE", "&7You can't use &3Request &7for &c<seconds> &7more seconds!"), 
/*     */   
/* 238 */   REPORT_COMMAND_FORMAT("REPORT.COMMAND_FORMAT", "&7/&3report &7<&3player&7>"), 
/* 239 */   REPORT_DELAY_MESSAGE("REPORT.DELAY_MESSAGE", "&7You can't use &3Report &7for &c<seconds> &7more seconds!"), 
/* 240 */   REPORT_CAN_NOT_REPORT_YOURSELF("REPORT.CAN_NOT_REPORT_YOURSELF", "&7You can't &3Report &7yourself!"), 
/*     */   
/* 242 */   ENDSPAWN_MUST_BE_IN_END("ENDSPAWN.MUST_BE_IN_END", "&7You must be in &3End &7to execute this command!"), 
/* 243 */   ENDSPAWN_SET_MESSAGE("ENDSPAWN.SET_MESSAGE", "&3End Spawn &7set to: &c<x>&7, &c<y>&7, &c<z>&7."), 
/*     */   
/* 245 */   ENDEXIT_SET_MESSAGE("ENDEXIT.SET_MESSAGE", "&3End Exit &7set to: &c<x>&7, &c<y>&7, &c<z>&7."), 
/*     */   
/* 247 */   FOCUS_COMMAND_USAGE("FOCUS.COMMAND_USAGE", "&7/&3focus &7<&3player&7>"), 
/* 248 */   FOCUS_NO_PERMISSION("FOCUS.NO_PERMISSION", "&7You must be at least faction &3Moderator &7to use focus."), 
/* 249 */   FOCUS_ALREADY_FOCUSING("FOCUS.ALREADY_FOCUSING", "&cYour faction is already focusing on &b<target>&c."), 
/* 250 */   FOCUS_NO_FACTION("FOCUS.NO_FACTION", "&7You have to have &3Faction &7to use focus."), 
/* 251 */   FOCUS_CAN_NOT_FOCUS("FOCUS.CAN_NOT_FOCUS", "&cYou can't focus that person!"), 
/* 252 */   FOCUS_NOT_FOCUSING("FOCUS.NOT_FOCUSING", "&cYou faction is not focusing on anyone."), 
/* 253 */   FOCUS_REMOVED("FOCUS.REMOVED_SUCCESSFULLY", "&7Focus succesfully removed."), 
/*     */   
/* 255 */   RANDOM_TELEPORT_MESSAGE("RANDOM_TELEPORT_MESSAGE", "&7Randomly teleported you to &3<player>&7."), 
/*     */   
/* 257 */   SLOTS_COMMAND_USAGE("SLOTS.COMMAND_USAGE", "&7/&3slots &7<&3number&7>"), 
/*     */   
/* 259 */   STATS_COMMAND_USAGE("STATS_COMMAND_USAGE", "&7/&3stats &7<&3player&7>"), 
/*     */   
/* 261 */   MUTE_FACTION_USAGE("MUTE_FACTION.USAGE", "&7/&3mutefaction &7<&3faction&7>"), 
/* 262 */   MUTE_FACTION_NOT_EXISTANT("MUTE_FACTION.NOT_EXISTANT", "&7Faction &c<faction> &7doesn't exist!"), 
/* 263 */   MUTE_FACTION_NO_ONLINE_PLAYERS("MUTE_FACTION.NO_ONLINE_PLAYERS", "&cFaction &3<faction> &chas no players online!"), 
/* 264 */   MUTE_FACTION_COMMAND("MUTE_FACTION.COMMAND", "mute <player> 1h Your faction has been muted!"), 
/*     */   
/* 266 */   INVENTORY_INSPECT_MESSAGE("INVENTORY_INSPECT_MESSAGE", "&7Opening inventory of &3<player>&7. Can't edit."), 
/*     */   
/* 268 */   STAFF_CHAT_FORMAT("STAFFCHAT.FORMAT", "&3&l[&b&lStaff&3&l] &a<player> &7: &c<message>"), 
/* 269 */   STAFF_CHAT_ENABLED("STAFFCHAT.ENABLED", "&7Staff chat &aenabled&7."), 
/* 270 */   STAFF_CHAT_DISABLED("STAFFCHAT.DISABLED", "&7Staff chat &cdisabled&7."), 
/*     */   
/* 272 */   STAFFMODE_COMMAND_ENABLED("STAFFMODE.COMMAND.ENABLED", "&7You have &aenabled &3StaffMode&7."), 
/* 273 */   STAFFMODE_COMMAND_DISABLED("STAFFMODE.COMMAND.DISABLED", "&7You have &cdisabled &3StaffMode&7."), 
/*     */   
/* 275 */   SUBCLAIM_SUCCESSFULLY_CREATED("SUBCLAIM.SUCCESSFULLY_CREATED", "&7Player &3<player> &7created subclaimed §c<block> &7at &c<x>&7, &c<y>&7, &c<z>&7."), 
/* 276 */   SUBCLAIM_CAN_NOT_DESTROY_SIGN("SUBCLAIM.CAN_NOT_DESTROY_SIGN", "&7You can't destroy this subclaim &3SIGN&7!"), 
/* 277 */   SUBCLAIM_CAN_NOT_DESTROY_SUBCLAIM("SUBCLAIM.CAN_NOT_DESTROY_SUBCLAIM", "&7You can't destroy this subclaimed &3<block>&7!"), 
/* 278 */   SUBCLAIM_CAN_NOT_OPEN("SUBCLAIM.CAN_NOT_OPEN", "&7You can't open this subclaimed &3<block>&7!"), 
/* 279 */   SUBCLAIM_NOT_IN_OWN_CLAIM("SUBCLIAM.NOT_IN_OWN_CLAIM", "&7Must be in your own &3Claim&7!"), 
/* 280 */   SUBCLAIM_ALREADY_EXISTS("SUBCLAIM.ALREADY_EXISTS", "&7There is already a subclaim on this &3<block>&7."), 
/*     */   
/* 282 */   VANISH_COMMAND_USAGE("VANISH.COMMAND.USAGE", "&7/&3vanish"), 
/* 283 */   VANISH_COMMAND_OPTIONS_USAGE("VANISH.COMMAND.OPTIONS_USAGE", "&7/&3vanish options"), 
/* 284 */   VANISH_COMMAND_TOGGLE_USAGE("VANISH.COMMAND.TOGGLE_USAGE", "&7/&3vanish toggle &7<&3option&7>"), 
/* 285 */   VANISH_COMMAND_TOGGLE_MESSAGE("VANISH.COMMAND.TOGGLE_MESSAGE", "&3You toggled&7: <option>"), 
/* 286 */   VANISH_COMMAND_TOGGLE_NOT_VANISHED("VANISH.COMMAND.TOGGLE_NOT_VANISHED", "&7Can't toggle &3Options &7while not &3Vanished&7!"), 
/* 287 */   VANISH_COMMAND_ENABLED("VANISH.COMMAND.ENABLED", "&7You have &aenabled &3Vanish&7."), 
/* 288 */   VANISH_COMMAND_ENABLED_STAFF_BROADCAST("VANISH.COMMAND.ENABLED_STAFF_BROADCAST", "&7Player &3<player> &7has &3Vanished&7! :O"), 
/* 289 */   VANISH_COMMAND_DISABLED("VANISH.COMMAND.DISABLED", "&7You have &cdisabled &3Vanish&7."), 
/* 290 */   VANISH_COMMAND_DISABLED_STAFF_BROADCAST("VANISH.COMMAND.DISABLED_STAFF_BROADCAST", "&7Player &3<player> &7has become &3Visible&7!"), 
/* 291 */   VANISH_ONJOIN_ENABLED("VANISH.ONJOIN.ENABLED", "&7You have joined &3Vanished&7."), 
/* 292 */   VANISH_ONJOIN_ENABLED_STAFF_BROADCAST("VANISH.ONJOIN.ENABLED_STAFF_BROADCAST", "&7Player &3<player> &7has joined &3Vanished&7."), 
/* 293 */   VANISH_OPTIONS_LIST_FORMAT("VANISH.OPTIONS_LIST_FORMAT", "&3You can toggle: <options>"), 
/* 294 */   VANISH_OPTIONS_DOES_NOT_EXIST("VANISH.OPTIONS.DOES_NOT_EXIST", "&7Option &3<option> &7doesn't exist!"), 
/* 295 */   VANISH_OPTIONS_CHEST_MESSAGE("VANISH.OPTIONS_CHEST_MESSAGE", "&4[&cSilent&4] &7Opening silently. Can not edit."), 
/* 296 */   VANISH_OPTIONS_DAMAGE_DENY("VANISH.OPTIONS.DAMAGE_DENY", "&7You can't &3Deal Damage &7when vanished!"), 
/* 297 */   VANISH_OPTIONS_PLACE_DENY("VANISH.OPTIONS.PLACE_DENY", "&7You can't &3Place Blocks &7when vanished!"), 
/* 298 */   VANISH_OPTIONS_BREAK_DENY("VANISH.OPTIONS.BREAK_DENY", "&7You can't &3Break Blocks &7when vanished"), 
/* 299 */   VANISH_OPTIONS_CHAT_DENY("VANISH.OPTIONS.CHAT_DENY", "&7You can't &3Chat &7when vanished!"), 
/* 300 */   VANISH_OPTIONS_PICKUP_DENY("VANISH.OPTIONS.PICKUP_DENY", "&7You can't &3Pickup Items &7when vanished!");
/*     */   
/*     */   private String path;
/*     */   private String value;
/*     */   private static YamlConfiguration language;
/*     */   
/*     */   private Language(String paramString2, String paramString3) {
/* 307 */     this.path = paramString2;
/* 308 */     this.value = paramString3;
/*     */   }
/*     */   
/*     */   public static void setLangFile(YamlConfiguration paramYamlConfiguration) {
/* 312 */     language = paramYamlConfiguration;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 316 */     return ChatColor.translateAlternateColorCodes('&', language.getString(this.path, this.value));
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 320 */     return this.path;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 324 */     return this.value;
/*     */   }
/*     */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\Language.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */