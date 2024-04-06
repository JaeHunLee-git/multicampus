package com.sds.spring.exception;

//아래의 클래스는, RuntimeException을 상속받는, 나만의 커스터마이징 된 예외 클래스이다.
//강요된예외 - sun에서 아주 심각한 예외는 이미 컴파일 타임에 예외처리를 강요하는 예외
//런타임 - 컴파일 타임에 강요하지 않는 예외, 즉 처리 여부는 개발자의 몫이 예외.. 
public class BoardDMLException extends RuntimeException{
	
	public BoardDMLException(String msg) {
		//부모생성자에는 에러 메시지를 심을 수 있는 생성자가 지원 
		super(msg);
	}
	public BoardDMLException(String msg, Throwable e) {
		//부모생성자에는 에러 메시지를 심을 수 있는 생성자가 지원 
		super(msg, e);
	}
	
	
}
