package thread;
/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����10:34:35
 */
public class ThreadTest3 {

	public static void main(String[] args) {
		new ThreadTest3().fristThread();
		new ThreadTest3().secondTest();

	}
	public void fristThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread thread=Thread.currentThread();//���ص�ǰ�߳�ʵ��
				System.out.println(thread.isAlive());//�ж��߳��Ƿ���Ч
//				thread.setDaemon(true);//�жϵ�ǰ�߳��Ƿ�Ϊ��ǰ�߳�
				for(int i=0;i<10;i++){
					System.out.println("�ڹ�:"+i);
				}
				System.out.println("�ڹ�������!");
				
			}
		}).start();//�����߳�
	}
	public void secondTest(){
		new Thread(){

			@Override
			public void run() {
				Thread thread=Thread.currentThread();
				System.out.println(thread.isAlive());
				for(int i=0;i<10;i++){
					System.out.println("����:"+i);
				}
				System.out.println("����������");
			}
			
		}.start();
		
	}	

}
