package thread;

import javax.swing.Box;

/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����11:18:35
 */
public class SwapThread extends Thread{

	private Box box;

private SwapThread(Box box){
	this.box=box;
	
}
public void run(){
	for(int i=0;i<1000;i++){
//		box.swap();
	}
}
	public static void main(String[] args) throws InterruptedException{
		final Box box=new Box(0);
		Thread t1=new SwapThread(box);
		Thread t2=new SwapThread(box);
		t1.start();
		t1.join();//���첽ִ�и�Ϊ˳��ִ��
		t2.start();
		t2.join();
		System.out.println(box);
	}

}
