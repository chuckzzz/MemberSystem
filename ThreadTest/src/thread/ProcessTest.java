package thread;

import java.io.File;
import java.io.IOException;

/**
 *@author:����
 *@version:Jdk1.7
 *@���ڣ�2016��11��29�� ����9:26:07
 */
public class ProcessTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		//�������̶���
		ProcessBuilder builder=new ProcessBuilder("C:"+File.separator+"Program Files (x86)"+File.separator+"Tencent"+File.separator+"QQ"+File.separator+"Bin"+File.separator+"QQScLauncher.exe");
		//��������
		Process process=builder.start();
		process.destroy();//��������
		process.waitFor();//�ȴ����̽���
	}

}
