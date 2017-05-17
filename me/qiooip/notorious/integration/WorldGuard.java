/*    */ package me.qiooip.notorious.integration;
/*    */ 
/*    */ import com.sk89q.worldedit.Vector;
/*    */ import com.sk89q.worldguard.bukkit.BukkitUtil;
/*    */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*    */ import com.sk89q.worldguard.protection.ApplicableRegionSet;
/*    */ import com.sk89q.worldguard.protection.flags.DefaultFlag;
/*    */ import com.sk89q.worldguard.protection.managers.RegionManager;
/*    */ import com.sk89q.worldguard.protection.regions.ProtectedRegion;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class WorldGuard
/*    */ {
/*    */   private static WorldGuardPlugin worldGuard;
/*    */   
/*    */   public static void setup()
/*    */   {
/* 24 */     Plugin localPlugin = Notorious.getInstance().getServer().getPluginManager().getPlugin("WorldGuard");
/* 25 */     if ((localPlugin == null) || (!(localPlugin instanceof WorldGuardPlugin))) {
/* 26 */       worldGuard = null;
/*    */     } else {
/* 28 */       worldGuard = (WorldGuardPlugin)localPlugin;
/*    */     }
/*    */   }
/*    */   
/*    */   public static WorldGuardPlugin getWorldGuard() {
/* 33 */     return worldGuard;
/*    */   }
/*    */   
/*    */ 
/*    */   public static boolean isPvPEnabled(Player paramPlayer)
/*    */   {
/* 39 */     Location localLocation = paramPlayer.getLocation();
/* 40 */     World localWorld = localLocation.getWorld();
/* 41 */     Vector localVector = BukkitUtil.toVector(localLocation);
/*    */     
/* 43 */     RegionManager localRegionManager = worldGuard.getRegionManager(localWorld);
/* 44 */     ApplicableRegionSet localApplicableRegionSet = localRegionManager.getApplicableRegions(localVector);
/*    */     
/* 46 */     return (localApplicableRegionSet.allows(DefaultFlag.PVP)) || (localApplicableRegionSet.getFlag(DefaultFlag.PVP) == null);
/*    */   }
/*    */   
/*    */   public static ProtectedRegion getProtectedRegion(Location paramLocation) {
/* 50 */     for (ProtectedRegion localProtectedRegion : worldGuard.getRegionManager(paramLocation.getWorld()).getApplicableRegions(paramLocation)) {
/* 51 */       if (localProtectedRegion != null)
/* 52 */         return localProtectedRegion;
/*    */     }
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\integration\WorldGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */