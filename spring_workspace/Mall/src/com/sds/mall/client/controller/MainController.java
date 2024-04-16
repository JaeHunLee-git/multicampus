package com.sds.mall.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//고객이 보게될 쇼핑몰 메인 요청을 처리하는 하위 컨트롤러
@Controller
public class MainController {
	
	@RequestMapping(value="/",  method=RequestMethod.GET)
	public ModelAndView getMain(Model model) {	
		ModelAndView mav = new ModelAndView("shop/index");
		
		return mav;
	}
	
}





