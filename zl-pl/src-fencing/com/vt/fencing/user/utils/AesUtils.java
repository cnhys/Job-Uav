package com.vt.fencing.user.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: bjapi-platform
 * @description: 加密解密工具  与.net 互通
 * @author: pangxuegang@ruijie.com.cn
 * @create: 2018-04-24 16:44
 **/

public class AesUtils {

    /**
      * @Description: 加密方法
      * @param: sSrc 要进行加密的源数据
      * @param: skey 要加密的key 必须为16位
      * @return: 加密后的数据
      * @Author: pangxuegang@ruijie.com.cn
      * @Date: 2018/6/8
      */
   public static String Encrypt(String sSrc, String sKey) throws Exception {
       if (sKey == null) {
           return null;
       }
       // 判断Key是否为16位
       if (sKey.length() != 16) {
           return null;
       }
       byte[] raw = sKey.getBytes("utf-8");
       SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
       Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
       cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
       byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

       return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
   }

   /**
    * @Description: 解密方法
    * @param: sSrc 要进行解密的源数据
    * @param: skey 要解密的key 必须为16位
    * @return: 解密后的数据
    * @Author: pangxuegang@ruijie.com.cn
    * @Date: 2018/6/8
    */
   public static String Decrypt(String sSrc, String sKey) throws Exception {
       try {
           // 判断Key是否正确
           if (sKey == null) {
               return null;
           }
           // 判断Key是否为16位
           if (sKey.length() != 16) {
               return null;
           }
           byte[] raw = sKey.getBytes("utf-8");
           SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
           Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
           cipher.init(Cipher.DECRYPT_MODE, skeySpec);
           byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
           try {
               byte[] original = cipher.doFinal(encrypted1);
               String originalString = new String(original,"utf-8");
               return originalString;
           } catch (Exception e) {
               System.out.println(e.toString());
               return null;
           }
       } catch (Exception ex) {
           return null;
       }
   }

}


