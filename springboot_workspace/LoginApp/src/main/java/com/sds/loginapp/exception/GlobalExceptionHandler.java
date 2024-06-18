package com.sds.loginapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*---------------------------------------------------------
	회원관련 예외
	---------------------------------------------------------*/
	@ExceptionHandler(MemberException.class)
	public Object handle(MemberException e, HandlerMethod handlerMethod){
		
		if(handlerMethod.getMethod().getReturnType().equals(ResponseEntity.class)) {
			log.debug(e.getMessage());
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", e.getMessage());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			
			log.debug("비동기 요청이므로, ResponseEntity로 응답합니다");
			
			return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			log.debug("동기 요청이므로 View 이름으로 응답합니다");
			return "error/result";
		}
	} 
	
}
