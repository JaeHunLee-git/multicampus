package com.sds.project0304.music;

//도로에 타고 다닐 수 스케이트 보드를 정의
//인터페이스란? 완전한 객체가 아닌, 기능 즉 메서드만을 보유한 객체를 말한다..
//ex) 새  --> 완전한 새가 아닌 새의 날아다니는 행위 보유한 객체 
//어차피 추상메서드만을 보유하는 객체이므로, 메서드와 객체 선언부에 굳이 abstract 사용할 필요가 없다
//interface 는 완전한 class가 아니기 때문에 다중상속 문제를 피해할 수 있다..  
public interface Board {
	
	public void roll();
}	
