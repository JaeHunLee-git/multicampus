package com.sds.movieadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//관리자 인증 관련된 요청을 처리하는 하위 컨트롤러  
@Controller
public class AdminController {
	
	@GetMapping("/admin/loginform")
	public String getLoginForm() {
		
		return "admin/login";
	}
	
	@GetMapping("/admin/registform")
	public String getRegistForm() {
		
		return "admin/regist";
	}
	
}
