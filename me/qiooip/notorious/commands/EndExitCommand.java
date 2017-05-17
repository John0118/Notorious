/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.UtilitiesFile;
/*    */ import me.qiooip.notorious.utils.Language;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class EndExitCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 18 */     if (paramCommand.getName().equalsIgnoreCase("endexit")) {
/* 19 */       if ((paramCommandSender instanceof Player)) {
/* 20 */         Player localPlayer = (Player)paramCommandSender;
/* 21 */         if (localPlayer.hasPermission("notorious.endexit.set")) {
/* 22 */           Notorious.getInstance().getConfigHandler().setEndSpawnLocation(localPlayer.getLocation());
/* 23 */           Notorious.getInstance().getUtilitiesFile().getConfiguration().set("end-exit", me.qiooip.notorious.utils.LocationSerilization.locationToString(localPlayer.getLocation()));
/*    */           try {
/* 25 */             Notorious.getInstance().getUtilitiesFile().getConfiguration().save(Notorious.getInstance().getUtilitiesFile().getFile());
/*    */           } catch (IOException localIOException) {
/* 27 */             localIOException.printStackTrace();
/*    */           }
/* 29 */           int i = (int)localPlayer.getLocation().getX();
/* 30 */           int j = (int)localPlayer.getLocation().getY();
/* 31 */           int k = (int)localPlayer.getLocation().getZ();
/* 32 */           localPlayer.sendMessage(Language.PREFIX.toString() + Language.ENDEXIT_SET_MESSAGE.toString().replace("<x>", 
/* 33 */             String.valueOf(i)).replace("<y>", String.valueOf(j)).replace("<z>", String.valueOf(k)));
/*    */         } else {
/* 35 */           localPlayer.sendMessage(Language.COMMANDS_NO_PERMISSION_MESSAGE.toString());
/*    */         }
/*    */       } else {
/* 38 */         paramCommandSender.sendMessage(Language.COMMANDS_FOR_PLAYER_USE_ONLY.toString());
/*    */       }
/*    */     }
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\EndExitCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */