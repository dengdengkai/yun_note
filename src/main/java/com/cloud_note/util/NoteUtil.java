package com.cloud_note.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
    
	//获取现在的时间戳
	public  static Timestamp  getNowTimestamp(){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return time;
	}
	
	//方法写成静态的原因是可以直接调用，不用进行实例化   
	
	/*
	 * 利用UUID算法生成主键
	 */
	public static String createdId(){
    	   //产生通用的唯一标识符
    	   UUID uuid = UUID.randomUUID();
    	   
    	   return uuid.toString().replace("-", "");
       }
      
	/**
        * 将传入的s加密处理
        * @param s 明文字符串
        * @return 加密后的字符串结果
        * @throws Exception
        */
       
	public static String md5(String s) {
       //将字符串信息采用MD5加密处理
       
       /*
        * 对于给定数量的更新数据，digest 方法只能被调用一次。
        * 在调用 digest 之后，
        * MessageDigest 对象被重新设置成其初始状态。
        *public byte[] digest() 通过执行诸如填充之类的最终操作完成哈希计算。
        * 在调用此方法之后，摘要被重置。
        */
       //String.getBytes() 返回一个字节数组
      try{
       MessageDigest md =
            MessageDigest.getInstance("MD5"); //返回实现指定摘要算法的 MessageDigest 对象
       byte[] output =md.digest(s.getBytes());
       //将MD5处理结果利用Base64转化成字符串
       String ret = 
    		   Base64.encodeBase64String(output);
       return ret;
       }
      
       catch(Exception e){
    	   throw new NoteException("密码加密失败！",e);
    	   }
       }
       
       public static void main(String[] args){
    	//   System.out.println(md5("123456"));
    	   System.out.println(md5(createdId()));
       }
       
}
