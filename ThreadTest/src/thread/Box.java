package thread;

import java.io.PrintWriter;

/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����11:21:58
 */
public class Box {
	private int i=1000;
	private int j=2000;
	public void swap(){
//		synchronized(this){
			int t=i;
			i=j;
//			Thread.yield();
			j=t;
//		}
	}
	public String toString(){
		return String.format("%d,%d",i,j );
	}
	
	

}
