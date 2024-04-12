package com.sds.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class TestController {
	
	@GetMapping("/test")  //웹브라우저에    localhost:7777/admin/test
	public String getTest() {
		System.out.println("test");
		return null;
	}
}
