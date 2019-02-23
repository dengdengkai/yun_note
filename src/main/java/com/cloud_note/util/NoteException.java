package com.cloud_note.util;
/**
 * 自定义异常
 * @author dengkai
 *
 */
public class NoteException extends RuntimeException{
    //写一个构造方法，接收传递的信息，并调用父类构造方法
	//将信息传递过去
	public NoteException(String msg,Throwable e){
            	 super(msg,e);
            }
}
