/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.UtilitiesFile;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World.Environment;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class EndSpawnCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 19 */     if (paramCommand.getName().equalsIgnoreCase("endspawn")) {
/* 20 */       if ((paramCommandSender instanceof Player)) {
/* 21 */         Player localPlayer = (Player)paramCommandSender;
/* 22 */         if (localPlayer.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
/* 23 */           if (localPlayer.hasPermission("notorious.endspawn.set")) {
/* 24 */             Notorious.getInstance().getConfigHandler().setEndSpawnLocation(localPlayer.getLocation());
/* 25 */             Notorious.getInstance().getUtilitiesFile().getConfiguration().set("end-spawn", me.qiooip.notorious.utils.LocationSerilization.locationToString(localPlayer.getLocation()));
/*    */             try {
/* 27 */               Notorious.getInstance().getUtilitiesFile().getConfiguration().save(Notorious.getInstance().getUtilitiesFile().getFile());
/*    */             } catch (IOException localIOException) {
/* 29 */               localIOException.printStackTrace();
/*    */             }
/* 31 */             int i = (int)localPlayer.getLocation().getX();
/* 32 */             int j = (int)localPlayer.getLocation().getY();
/* 33 */             int k = (int)localPlayer.getLocation().getZ();
/* 34 */             localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENDSPAWN_SET_MESSAGE.toString().replace("<x>", 
/* 35 */               String.valueOf(i)).replace("<y>", String.valueOf(j)).replace("<z>", String.valueOf(k)));
/*    */           } else {
/* 37 */             localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */           }
/*    */         } else {
/* 40 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENDSPAWN_MUST_BE_IN_END.toString());
/*    */         }
/*    */       } else {
/* 43 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\EndSpawnCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */