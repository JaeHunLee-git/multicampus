package com.sds.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//메인과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class MainController {
	
	@GetMapping("/")
	public String getMain() {
		
		return "main/index";
	}
}
