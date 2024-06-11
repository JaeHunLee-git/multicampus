package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieapp.exception.JwtException;
import com.sds.movieapp.sns.KaKaoLogin;
import com.sds.movieapp.sns.NaverLogin;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestMemberController {
	
	@Autowired
	private NaverLogin naverLogin;
	
	@Autowired
	private KaKaoLogin kakaoLogin;
	
	
	@Autowired
	private ServletContext servletContext;
	
	//로그인 요청에 필요한 링크 주소 및 파라미터 생성 요청 처리 
	@GetMapping("/rest/member/authform/{sns}")
	public ResponseEntity getLink(@PathVariable("sns") String sns) {
		
		ResponseEntity entity=null;
		
		if(sns.equals("google")) {
			//entity=ResponseEntity.ok(naverLogin.getGrantUrl());  //내용을 보내야 하므로, body도 구성하자 
		}else if(sns.equals("naver")) {
			entity=ResponseEntity.ok(naverLogin.getGrantUrl());  //내용을 보내야 하므로, body도 구성하자
		}else if(sns.equals("kakao")) {
			entity=ResponseEntity.ok(kakaoLogin.getGrantUrl());  //내용을 보내야 하므로, body도 구성하자
		}
		
		return entity; 
	}
	
	
	//넘겨받은 토큰이 유효한지 여부를 따져보고, 유효할 경우엔  Member 를 json으로 응답 
	@GetMapping("/rest/member/logincheck")
	public ResponseEntity getLoginMember(@RequestHeader("Authorization") String header) {
		
		log.debug("토큰 검증 요청");
		
		//넘어온 헤더값 중, Bearer를 제외한 순수 토큰만 추출 
		String token = header.replace("Bearer", "");
		
		//넘겨받은 token은 순수 jwt 가 아니라, 비밀키에 의해 서명되어 암호화 되어 있다. 
		//따라서, 공개키를 이용하여 암호를 풀어보자 
		
		
		
		
		return null;
	}
	
	//아예 헤더가 없는 경우
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity handle(MissingRequestHeaderException e) {
		log.debug("헤더 값이 없네요");
		ResponseEntity entity =ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   
		return entity;
	}
	
	//헤더는 있지만 유효하지 않은 경우
	@ExceptionHandler(JwtException.class)
	public ResponseEntity handle(JwtException e) {
		log.debug("헤더 값은 존재하지만, 토큰이 유효하지 않네요");
		ResponseEntity entity =ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();   
		return entity;
	}
	
}













