package stream.copy;
/*
파일을 복사해본다 
■ Stream 이란? 
	- 강의 지류, 물줄기 등 흐르는 물을 의미 
	- 컴퓨터 분야에서는 물의 흐름이 아닌 데이터의 흐름을 의미한다 
■ 방향성에 따른 구분 (IO)
	- Input(입력) : 실행중인 프로그램으로 데이터가 들어오는 모습
	- Output(출력) : 실행중인 프로그램에서 데이터가 나가는 모습 
*/
import java.io.FileInputStream;

public class ImageCopy {
	FileInputStream fis; //파일을 대상으로 한 입력스트림 객체 
						//이 클래스를 이용하면 실행중인 자바 프로그램으로 파일을 이루고 
						//있는 바이트 데이터들을 읽어 마실 수 있다
	String filename="D:/java_workspace/app0307/res/chicken.webp	";

	public ImageCopy(){
		fis = new FileInputStream(filename);			
	}
	public static void main(String[] args) {
		new ImageCopy();
	}
}
