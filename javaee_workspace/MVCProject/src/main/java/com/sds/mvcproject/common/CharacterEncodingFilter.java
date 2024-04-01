package com.sds.mvcproject.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * javaEE 에서는 서블릿이 요청을 처리하기 보다도 앞선 시점에, 필터 객체가 동작할 수 있는데
 * 개발자가 필터를 재정의하면, 서블릿이 동작하기 이전 시점에 공통적으로 처리하고 싶은 기능을
 * 구현할 수 있다
 * */
public class CharacterEncodingFilter implements Filter{
	String encoding;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿에게 request 객체가 전달되기 전에, 먼저 필터에서 무언가 원하는 작업을 해보자!!
		request.setCharacterEncoding(encoding);
		
		//낚아 챈 요청을 다시 원래 호출하려면, 서블릿이나 또 다른 필터로 전달... 
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}






