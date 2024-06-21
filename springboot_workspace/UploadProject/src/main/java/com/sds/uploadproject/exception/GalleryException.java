package com.sds.uploadproject.exception;

public class GalleryException extends RuntimeException{
	
	public GalleryException(String msg) {
		super(msg);
	}
	public GalleryException(String msg, Throwable e) {
		super(msg, e);
	}
	public GalleryException(Throwable e) {
		super(e);
	}
	
}
