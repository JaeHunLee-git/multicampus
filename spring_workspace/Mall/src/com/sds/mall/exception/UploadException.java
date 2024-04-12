package com.sds.mall.exception;

/*자바에서 예외란 ? 실행중인 프로그램의 정상 수행을 방해하는 요인을 말한다
 * 
 *자바의 예외 유형? 
 *1) 강요된 예외  -  sun에서 중대하다고 판단되는 예외..
 *						 컴파일 타임 즉 코드 작성 시 이 예외를 처리하지 않으면 코드는 컴파일 막기 때문에 강제하는 예외라 한다
 *
 *2) 강요하지 않는 예외 - 강제하지 않는 나머지 상황들...모두 말한다..
 *								비강제예외는 RuntimeException을 상속받아 개발자가 재정의할 수 있다
 * 
 * */
public class UploadException extends RuntimeException{
	//이 객체에는 개발자가 원하는 메세지나, 에러의 원인을 담아야 하는데, 
	//이 정보들을 담으려면, RuntimeException의 생성자를 이용해야 한다..
	//따라서 부모인 RuntimeException의 생성 자 중 원하는 생성자를 호출하자 
	//주의) 자바에서 자식이 부모의 생성자를 호출하는 시점은 아무때나 가능한 것이 아니라, 자식이 태어나기 직전 시 부모를 먼저 
	//		호출해야 한다.. (즉 자식 생성자의 맨 윗줄에 부모를 먼저 호출해야 한다..)
	public UploadException(String msg) {
		super(msg);
	}
	public UploadException(Throwable e) { //Exception 보다 더 위
		super(e);
	}
	
	public UploadException(String msg, Throwable e) { 
		super(msg, e);
	}
	
	
}






