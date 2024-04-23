package com.sds.testapp.exception;

//사용자 정의 RuntimeException 예외객체
public class NoticeException extends RuntimeException{

	public NoticeException(String msg) {
		super(msg);
	}
	
	public NoticeException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public NoticeException(Throwable e) {
		super(e);
	}
	
}
