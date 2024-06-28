package com.sds.securityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	//로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public String getLoginForm() {
		
		return "member/login";
	}
}
