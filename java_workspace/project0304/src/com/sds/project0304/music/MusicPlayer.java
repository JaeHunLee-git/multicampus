package com.sds.project0304.music;

//모든 20명의 개발자 사원이 반드시 상속받아야 할 최상위 음향기기를 정의 
public abstract class MusicPlayer {
	int vol;
	
	//이 클래스를 상속받는 자식이 누군지는 모르겠지만, 니가 좀 알아서 너 상황에 맞게
	//커스터마이징 해라!! 즉 오버라이딩 해서 써라..
	public abstract void setVolume();
		
	
	public abstract void playMp3File();
}
