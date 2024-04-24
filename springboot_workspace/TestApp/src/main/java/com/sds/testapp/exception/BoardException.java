package com.sds.testapp.exception;

//사용자 정의 RuntimeException 예외객체
public class BoardException extends RuntimeException{

	public BoardException(String msg) {
		super(msg);
	}
	
	public BoardException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public BoardException(Throwable e) {
		super(e);
	}
	
}
