package thread;
/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����10:26:25
 */
public class ThreadTest2 implements Runnable{
 
	
	@Override
	public void run() {
		Thread thread=Thread.currentThread();
		for(int i=0;i<50;i++){
			System.out.println(thread.getName()+":"+i);
			
		}
			
	}

	public static void main(String[] args) {
		ThreadTest2 test2=new ThreadTest2();
//		Thread thread1=new Thread(test2);
//		thread1.start();
		new Thread(test2).start();
		ThreadTest2 test3=new ThreadTest2();
		new Thread(test3).start();
	}

}
