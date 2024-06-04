package com.sds.recommendmsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class RecommendConroller {
	
	@GetMapping("/list/view")
	public String getListView() {
		
		log.debug("추천 목록 호출 ");
		
		return "recommend/list";
	}
}
