package com.sds.recommendapp.exception;

public class RecommendException extends RuntimeException{
	
	public RecommendException(String msg) {
		super(msg);
	}
	public RecommendException(String msg, Throwable e) {
		super(msg, e);
	}
	public RecommendException(Throwable e) {
		super(e);
	}
	
}
