package com.sds.movieadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//관리자 메인 요청 처리
	@GetMapping("/")
	public String getMain() {
		
		System.out.println("메인 요청");
		
		return "admin/index";
	}
}
