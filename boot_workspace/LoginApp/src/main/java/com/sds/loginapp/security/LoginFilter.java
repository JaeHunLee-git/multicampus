package com.sds.loginapp.security;

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

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	
	public LoginFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
		this.setFilterProcessesUrl("/rest/member/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		log.debug("로그인을 시도중임");
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, pass);

		return this.getAuthenticationManager().authenticate(authToken);
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		log.debug("LoginFilter에서 인증성공");
		chain.doFilter(request, response);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		log.debug("인증실패");
		
	}
	
}
