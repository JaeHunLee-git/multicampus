package stream.copy;
/*
������ �����غ��� 
�� Stream �̶�? 
	- ���� ����, ���ٱ� �� �帣�� ���� �ǹ� 
	- ��ǻ�� �о߿����� ���� �帧�� �ƴ� �������� �帧�� �ǹ��Ѵ� 
�� ���⼺�� ���� ���� (IO)
	- Input(�Է�) : �������� ���α׷����� �����Ͱ� ������ ���
	- Output(���) : �������� ���α׷����� �����Ͱ� ������ ��� 
*/
import java.io.FileInputStream;

public class ImageCopy {
	FileInputStream fis; //������ ������� �� �Է½�Ʈ�� ��ü 
						//�� Ŭ������ �̿��ϸ� �������� �ڹ� ���α׷����� ������ �̷�� 
						//�ִ� ����Ʈ �����͵��� �о� ���� �� �ִ�
	String filename="D:/java_workspace/app0307/res/chicken.webp	";

	public ImageCopy(){
		fis = new FileInputStream(filename);			
	}
	public static void main(String[] args) {
		new ImageCopy();
	}
}
