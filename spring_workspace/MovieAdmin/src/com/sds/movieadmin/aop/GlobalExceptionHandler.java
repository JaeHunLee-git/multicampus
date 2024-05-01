package com.sds.movieadmin.aop;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sds.movieadmin.exception.UnAuthorizedException;

//하위 컨트롤러를 포함하여, 어플리케이션의 전반적인 영역에 걸쳐 발생하는 예외를 아래의 
//어노테이션만 명시하면 이 클래스에 모두 처리할 수 있다..따라서 예외 처리를 여기저기 파편화시키지
//하나의 공통된 클래스에서 관리하고 싶다면, @ControllerAdvice 를 이용할 수 있다

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UnAuthorizedException.class)
	public  String handle(UnAuthorizedException e) {
		
		return "admin/error/result";
	}
	
	@ExceptionHandler(FileUploadBase.SizeLimitExceededException.class)
	public ResponseEntity handle(FileUploadBase.SizeLimitExceededException e) {
		
		//응답코드 생성 
		ResponseEntity entity = ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
		
		return entity;
	}
}







