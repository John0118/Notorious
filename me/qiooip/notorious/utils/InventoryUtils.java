/*    */ package me.qiooip.notorious.utils;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.io.BukkitObjectInputStream;
/*    */ import org.bukkit.util.io.BukkitObjectOutputStream;
/*    */ import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
/*    */ 
/*    */ public class InventoryUtils
/*    */ {
/*    */   public static String itemStackArrayToBase64(ItemStack[] paramArrayOfItemStack)
/*    */   {
/*    */     try
/*    */     {
/* 16 */       ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 17 */       BukkitObjectOutputStream localBukkitObjectOutputStream = new BukkitObjectOutputStream(localByteArrayOutputStream);
/*    */       
/* 19 */       localBukkitObjectOutputStream.writeInt(paramArrayOfItemStack.length);
/*    */       
/* 21 */       for (int i = 0; i < paramArrayOfItemStack.length; i++) {
/* 22 */         localBukkitObjectOutputStream.writeObject(paramArrayOfItemStack[i]);
/*    */       }
/*    */       
/* 25 */       localBukkitObjectOutputStream.close();
/* 26 */       return Base64Coder.encodeLines(localByteArrayOutputStream.toByteArray());
/*    */     } catch (Exception localException) {
/* 28 */       throw new IllegalStateException("Unable to save item stacks.", localException);
/*    */     }
/*    */   }
/*    */   
/*    */   public static ItemStack[] itemStackArrayFromBase64(String paramString) {
/*    */     try {
/* 34 */       ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
/* 35 */       BukkitObjectInputStream localBukkitObjectInputStream = new BukkitObjectInputStream(localByteArrayInputStream);
/* 36 */       ItemStack[] arrayOfItemStack = new ItemStack[localBukkitObjectInputStream.readInt()];
/*    */       
/* 38 */       for (int i = 0; i < arrayOfItemStack.length; i++) {
/* 39 */         arrayOfItemStack[i] = ((ItemStack)localBukkitObjectInputStream.readObject());
/*    */       }
/*    */       
/* 42 */       localBukkitObjectInputStream.close();
/* 43 */       return arrayOfItemStack;
/*    */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 45 */       throw new java.io.IOException("Unable to decode class type.", localClassNotFoundException);
/*    */     }
/*    */   }
/*    */   
/*    */   public static int getInventorySize(int paramInt) {
/* 50 */     if (paramInt <= 9) return 9;
/* 51 */     if ((paramInt > 9) && (paramInt <= 18)) return 18;
/* 52 */     if ((paramInt > 18) && (paramInt <= 27)) return 27;
/* 53 */     if ((paramInt > 27) && (paramInt <= 36)) return 36;
/* 54 */     if ((paramInt > 36) && (paramInt <= 45)) return 45;
/* 55 */     if ((paramInt > 45) && (paramInt <= 54)) return 54;
/* 56 */     return 9;
/*    */   }
/*    */ }


/* Location:              C:\Users\leagu\Desktop\Notorious-Mango.jar!\me\qiooip\notorious\utils\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */