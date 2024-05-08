package com.sds.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


//영화 추천 요청을 처리하는 하위 컨트롤러
@Slf4j
@Controller
public class RecommendController {
	
	@GetMapping("/movie/recommend/list")
	public String getList() {
		
		return "recommend/list";
	}
}
