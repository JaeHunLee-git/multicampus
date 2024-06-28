package com.sds.recommendproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendController {
	
	@GetMapping("/list/view")
	public String getListView() {
		
		return "recommend/list";  // templates/recommend/list.html
	}
}
