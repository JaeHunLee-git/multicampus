package com.sds.mall.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sds.mall.model.product.TopCategoryService;

//고객이 보게될 쇼핑몰 메인 요청을 처리하는 하위 컨트롤러
@Controller
public class MainController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@RequestMapping(value="/",  method=RequestMethod.GET)
	public String getMain(Model model) {	
		
		//3단계: 상위 카테고리 목록을 가져오기
		List topList = topCategoryService.selectAll();
		
		//4단계: 저장 
		model.addAttribute("topList", topList);
		
		return "shop/index";
	}
	
}





