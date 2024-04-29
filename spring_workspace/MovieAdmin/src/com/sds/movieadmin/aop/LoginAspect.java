package com.sds.movieadmin.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.UnAuthorizedException;

//웹어플리케이션의 모든 컨트롤러의 요청을 낚아채서, 세션이 있는지 확인하고, 만일 없으면 에러를 일으키자
public class LoginAspect {
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj=null; //하위컨트롤러가 반환한 리턴값
		
		//request 요청 객체를 이용하여  session을 얻어와 그 안에 Admin DTO가 존재하는지 확인해보자 
		HttpServletRequest request=null;
		request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//검증이 필요한  uri 의 경우만...
		String uri = request.getRequestURI();
		if(
				uri.equals("/admin/loginform") || //로그인 폼 요청
				uri.equals("/admin/registform")||   //가입 요청 
				uri.equals("/auth/admin")   //로그인 요청 
		) {
			obj = joinPoint.proceed(); //원래 호출하려 했던 하위 컨트롤러의 메서드 호출해줌
		}else {
			//세션 검사 
			HttpSession session = request.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			
			if(admin !=null) { //로그인 성공한 관리자
				obj = joinPoint.proceed();
			}else { //인증 거치지 않고 접속하려는 의도...
				throw new UnAuthorizedException("로그인이 필요한 서비스입니다");
			}
		}
		return obj;
	}
}



