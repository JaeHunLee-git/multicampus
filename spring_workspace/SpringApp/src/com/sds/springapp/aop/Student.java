package com.sds.springapp.aop;


//학생을 정의한다 
public class Student {
	//private Instrument bell; //결합도를 낮추기 위해, 상위 자료형을 보유한다. 또한 new 해서는 안된다
	
	//new 하지 않고,스프링 컨테이너로부터 주입을 받기 위해서는 setter나 생성자 메서드를 준비해놓아야 한다
	//public void setBell(Instrument bell) {
	//	this.bell = bell;
	//}
	
	public void gotoSchool() {
		System.out.println("학교에 갑니다");
	}
	
	public void study() {
		System.out.println("수업 시작합니다");
	}
	
	public void haveLunch() {
		System.out.println("점심 먹어요");
	}
	
	public void goHome() {
		System.out.println("귀가 해요");
	}
}


