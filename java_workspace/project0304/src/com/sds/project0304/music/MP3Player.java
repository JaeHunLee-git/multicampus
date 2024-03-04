package com.sds.project0304.music;

//들고 다닐 수 있는 포터블한 플레이어 정의
//개발자 : A라는 사원 
//이 사원에게 구현을 강제시켜보자 

// Board b = new MP3Player();
public class MP3Player extends MusicPlayer implements Board, ZetFly{
																/* Board 자체를 상속받는게 아니라, Board의 기능을
																상속받게 된다, 클래스간 다중 상속을 피할 수 있다*/
	String brand;
	String productName;
	
	public void showEqualizer() {//이퀄라이저 시각화 시키기 
	}
	public void setVolume() { //볼륨을 조절하기
		System.out.println("볼륨을 조절합니다");
	}
	
	//@(어노테이션) 주석이긴 하나, 프로그래밍 시 사용 및 해석되는 주석
	// java 5 버전 부터 지원되기 시작했으며, 프로그램 언어에서 무언가를 표시할때 사용
	//spring 에서 압도적으로 많이 사용됨
	public void playMp3File() {
		System.out.println("MP3 파일을 재생합니다");
	}
	
	//Board의 기능을 나의 상황에 맞게 오버라이딩 한다
	@Override
	public void roll() {
		System.out.println("이 음향기기를 타고 다녀요");
	}
	
	@Override
	public void fly() {
		System.out.println("이 음향기기를 타고 날아요");
	}
}	





