package com.cloud_note.util;
/**
 * �Զ����쳣
 * @author dengkai
 *
 */
public class NoteException extends RuntimeException{
    //дһ�����췽�������մ��ݵ���Ϣ�������ø��๹�췽��
	//����Ϣ���ݹ�ȥ
	public NoteException(String msg,Throwable e){
            	 super(msg,e);
            }
}
