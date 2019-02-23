package com.cloud_note.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
    
	//��ȡ���ڵ�ʱ���
	public  static Timestamp  getNowTimestamp(){
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return time;
	}
	
	//����д�ɾ�̬��ԭ���ǿ���ֱ�ӵ��ã����ý���ʵ����   
	
	/*
	 * ����UUID�㷨��������
	 */
	public static String createdId(){
    	   //����ͨ�õ�Ψһ��ʶ��
    	   UUID uuid = UUID.randomUUID();
    	   
    	   return uuid.toString().replace("-", "");
       }
      
	/**
        * �������s���ܴ���
        * @param s �����ַ���
        * @return ���ܺ���ַ������
        * @throws Exception
        */
       
	public static String md5(String s) {
       //���ַ�����Ϣ����MD5���ܴ���
       
       /*
        * ���ڸ��������ĸ������ݣ�digest ����ֻ�ܱ�����һ�Ρ�
        * �ڵ��� digest ֮��
        * MessageDigest �����������ó����ʼ״̬��
        *public byte[] digest() ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣
        * �ڵ��ô˷���֮��ժҪ�����á�
        */
       //String.getBytes() ����һ���ֽ�����
      try{
       MessageDigest md =
            MessageDigest.getInstance("MD5"); //����ʵ��ָ��ժҪ�㷨�� MessageDigest ����
       byte[] output =md.digest(s.getBytes());
       //��MD5����������Base64ת�����ַ���
       String ret = 
    		   Base64.encodeBase64String(output);
       return ret;
       }
      
       catch(Exception e){
    	   throw new NoteException("�������ʧ�ܣ�",e);
    	   }
       }
       
       public static void main(String[] args){
    	//   System.out.println(md5("123456"));
    	   System.out.println(md5(createdId()));
       }
       
}
