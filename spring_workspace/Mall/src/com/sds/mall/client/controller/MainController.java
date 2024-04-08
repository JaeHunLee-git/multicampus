package com.sds.mall.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//고객이 보게될 쇼핑몰 메인 요청을 처리하는 하위 컨트롤러
@Controller
public class MainController {
	
	@RequestMapping(value="/",  method=RequestMethod.GET)
	public String getMain() {
		
		return "shop/index";
	}
	
}





