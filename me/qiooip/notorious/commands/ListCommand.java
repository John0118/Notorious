/*    */ package me.qiooip.notorious.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import me.qiooip.notorious.Notorious;
/*    */ import me.qiooip.notorious.config.ConfigHandler;
/*    */ import me.qiooip.notorious.vanish.VanishHandler;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ListCommand implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
/*    */   {
/* 19 */     if (paramCommand.getName().equalsIgnoreCase("list")) {
/* 20 */       ArrayList localArrayList = new ArrayList();
/* 21 */       Player[] arrayOfPlayer; int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length; for (int i = 0; i < j; i++) { localObject = arrayOfPlayer[i];
/* 22 */         if ((((Player)localObject).hasPermission("notorious.staff.list")) && (!Notorious.getInstance().getVanishHandler().getVanishedPlayers().contains(((Player)localObject).getUniqueId()))) {
/* 23 */           localArrayList.add(((Player)localObject).getName());
/*    */         }
/*    */       }
/*    */       
/* 27 */       Object localObject = new StringBuilder();
/* 28 */       String str; for (Iterator localIterator = localArrayList.iterator(); localIterator.hasNext();) { str = (String)localIterator.next();
/* 29 */         ((StringBuilder)localObject).append(str).append(", ");
/*    */       }
/*    */       
/* 32 */       if (((StringBuilder)localObject).length() != 0) {
/* 33 */         ((StringBuilder)localObject).delete(((StringBuilder)localObject).length() - 2, ((StringBuilder)localObject).length());
/*    */       }
/*    */       
/* 36 */       for (localIterator = Notorious.getInstance().getConfigHandler().getListCommandMessage().iterator(); localIterator.hasNext();) { str = (String)localIterator.next();
/* 37 */         paramCommandSender.sendMessage(str.replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().length)).replace("<max>", String.valueOf(Notorious.getInstance().getJoinFullServerHandler().getMaxSlots())).replace("<stafflist>", (CharSequence)localObject));
/*    */       }
/*    */     }
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\commands\ListCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */