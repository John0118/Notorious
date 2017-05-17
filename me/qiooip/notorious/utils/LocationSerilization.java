/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public class LocationSerilization
/*    */ {
/*    */   public static String locationToString(Location paramLocation)
/*    */   {
/* 10 */     String str1 = null;
/*    */     
/* 12 */     String str2 = paramLocation.getWorld().getName();
/* 13 */     String str3 = String.valueOf(paramLocation.getBlockX());
/* 14 */     String str4 = String.valueOf(paramLocation.getBlockY());
/* 15 */     String str5 = String.valueOf(paramLocation.getBlockZ());
/*    */     
/* 17 */     str1 = String.valueOf(str2 + "," + str3 + "," + str4 + "," + str5);
/*    */     
/* 19 */     return str1;
/*    */   }
/*    */   
/*    */   public static Location stringToLocation(String paramString) {
/* 23 */     Location localLocation = null;
/* 24 */     String[] arrayOfString = paramString.split(",");
/*    */     
/* 26 */     World localWorld = org.bukkit.Bukkit.getWorld(arrayOfString[0]);
/* 27 */     String str1 = arrayOfString[1];
/* 28 */     String str2 = arrayOfString[2];
/* 29 */     String str3 = arrayOfString[3];
/*    */     
/* 31 */     localLocation = new Location(localWorld, Integer.valueOf(str1).intValue(), Integer.valueOf(str2).intValue(), Integer.valueOf(str3).intValue(), 0.0F, 0.0F);
/*    */     
/* 33 */     return localLocation;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\LocationSerilization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */