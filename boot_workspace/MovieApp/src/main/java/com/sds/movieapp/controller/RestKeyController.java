package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletContext;

//같은 스프링 클라우드 영역내에 있는 서버들에게 , 공개키를 제공해주기 위한 api를 열어주자 

@RestController
public class RestKeyController {
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/jwt/key")   //http://localhost:7979/movieapp/jwt/key
	public String getKey() {
		return (String)servletContext.getAttribute("key");
	}
	
}
