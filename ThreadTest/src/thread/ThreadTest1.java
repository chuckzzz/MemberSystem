package thread;
/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����10:13:33
 */
public class ThreadTest1 extends Thread {
private String name;
private String name1;

public ThreadTest1(String name) {
	super();
	this.name = name;
}



@Override
public void run() {
	Thread thread=Thread.currentThread();
	for(int i=0;i<10;i++){
		System.out.println(name+":"+i);
		
	}
	System.out.println("�ڹ� ������");
	
}



public static void main(String[] args) throws InterruptedException{
	ThreadTest1 test1=new ThreadTest1("�ڹ�");
	ThreadTest1 test2=new ThreadTest1("����");
	test1.setPriority(Thread.MAX_PRIORITY);//���ȼ�
	System.out.println(test1.getPriority());
	
	System.out.println(test2.getPriority());
	test1.start();
	test2.start();
	test2.join();//�����߳��첽ִ��
//	test2.join();
}




}

