package com.sds.mall.exception;

public class CalculateFailException extends RuntimeException{
	
	
	public CalculateFailException(String msg){
		super(msg);
	}
	
	public CalculateFailException(String msg, Throwable e){
		super(msg, e);
	}
	
	public CalculateFailException(Throwable e){
		super(e);
	}
	
}
