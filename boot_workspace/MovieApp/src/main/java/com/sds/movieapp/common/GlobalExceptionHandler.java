package com.sds.movieapp.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import com.sds.movieapp.exception.JwtException;

import lombok.extern.slf4j.Slf4j;

//어플리케이션 내의 모든 컨트롤러 내에서 발생하는 예외는 여기서 처리가능
//이 글로벌 예외 클래스가, 컨트롤러에서 발생되는 모든 예외를 대신 처리하고 있지만 
//RestController와 Controller 에 따라 json으로 응답할지, html문서로 응답할지 구분되어야 함
//이에 대한 컨트롤러의 반환형 판단을 이용하여, json,html로 응답결과를 나누자
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	/*---------------------------------------------
	JWT 인증에 실패한 경우
	---------------------------------------------*/
	@ExceptionHandler(JwtException.class)
	public Object handle(JwtException e, HandlerMethod handlerMethod) {
		
		if(handlerMethod.getMethod().getReturnType().equals(ResponseEntity.class)) {
			log.debug( e.getMessage()+", 글로벌 예외처리");
			
			//자바의 객체는 JSoN으로 변경되어 반환 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", e.getMessage()); //에러 메시지 
			map.put("status", HttpStatus.UNAUTHORIZED.value()); //상태 코드
			map.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());//상태 코드에 대한 문자열
			
			return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
		}else {
			return "error/result"; //html 페이지명
		}
	}
	
	
	/*---------------------------------------------
	JWT 인증에 실패한 경우
	---------------------------------------------*/
	
}
