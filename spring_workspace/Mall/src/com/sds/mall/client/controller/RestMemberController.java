package com.sds.mall.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.mall.sns.GoogleLogin;

//회원관련 요청 중 Rest 한 비동기 요청만을 처리하는 하위 컨트롤러
@RestController
public class RestMemberController {
	
	@Autowired
	private GoogleLogin googleLogin;
	
	//구글의 인증화면 주소에 대한 요청 처리 
	@GetMapping("/rest/member/authform/google")
	public ResponseEntity getGoogleUrl() {
		
		String url = googleLogin.getGrantUrl();
		
		ResponseEntity entity = ResponseEntity.ok(url);
		
		return entity;
	}
	
	//네이버의 인증화면 주소에 대한 요청 처리 
	
	
	//카카오의 인증화면 주소에 대한 요청 처리 
}
