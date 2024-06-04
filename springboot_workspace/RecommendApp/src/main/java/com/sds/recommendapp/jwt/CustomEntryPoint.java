package com.sds.recommendapp.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomEntryPoint implements AuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		
		log.debug("접근 권한이 없네요");
		
		//에러 메시지 응답 정보 
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error : Unauthorized"); //401 에러 (권한 없슴).
		
	}
}
