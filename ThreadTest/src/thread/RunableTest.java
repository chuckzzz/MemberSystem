package thread;
/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��30�� ����6:04:57
 */
public class RunableTest implements Runnable {
	private String name;

	public RunableTest(String name) {
		super();
		this.name = name;
	}

	public static void main(String[] args) {

		Thread thread =new Thread( new RunableTest("�ڹ�"));
		Thread thread1 = new Thread(new RunableTuZi("����"));
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

class RunableTuZi implements Runnable {
	private String tuziname;

	public RunableTuZi(String name) {
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
