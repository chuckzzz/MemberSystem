package thread;

/**
 * @author:����
 * @version:Jdk1.7
 * @���ڣ�2016��11��29�� ����9:35:40
 */
public class ThreadTest extends Thread {
	private String name;

	public ThreadTest(String name) {
		super();
		this.name = name;
	}

	public static void main(String[] args) {

		Thread thread = new ThreadTest("�ڹ�");
		Thread thread1 = new ThreadTuZi("����");
		thread1.start();
		thread.start();

	}

	@Override
	public void run() {

		

		for (int i = 0; i < 5; i++) {

			System.out.println(name + ":" + i);

		}
		System.out.println("�ڹ���ɱ���");

	}

}

class ThreadTuZi extends Thread {
	private String tuziname;

	public ThreadTuZi(String name) {
		super();
		this.tuziname = name;
	}


	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {

			System.out.println(tuziname + ":" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("������ɱ���");

	}

}
