package com.sds.securityapp.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/*
 * 스프링이 지원하는 기본 로그인 폼 및 프로세스는 사실 현실적으로 그대로 쓸수가 없다.
 * 왜? 로그인 성공 후 세션에 무언가 담을때 개발자가 원하는 데이터를 담아야 하기때문에..
 *      jwt를 생성 한다던가..하여간 무언가 하고싶다면 당연히 커스텀처리 해야 한다..
 * */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	public LoginFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
		this.setFilterProcessesUrl("/rest/member/login");
		this.authenticationManager = authenticationManager;
	}
	//로그인을 시도하는 메서드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		log.debug("로그인 시도 중 ");
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		log.debug("id is "+id);
		log.debug("pass is "+pass);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, pass);
		
		return authenticationManager.authenticate(authToken); //db 연동(UserDetailsService 에게 일시킴) 후 그 결과 반환...
	}
	
	
}










