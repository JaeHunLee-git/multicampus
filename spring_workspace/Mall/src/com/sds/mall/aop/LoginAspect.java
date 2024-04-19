package com.sds.mall.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.UnAuthorizedException;

//회원에게만 제공되는 요청에 대해, 로그인을 햇는지 여부를 체크하는 Aspect 
public class LoginAspect {
	
	//보안인증이 필요한 요청인지 여부를 알려주는 메서드  true  - 회원인증이 필요한 요청, false-인증 필요없는 요청
	public boolean isSecureUri(String uri) {
		
		//아래 배열에 들어있는 uri는 인증이 필요한 목록이다..
		String[] secureURI= {
			"/order/cart/list",	
			"/member/mypage/main",	
			"/order/cart/regist",	
		};
		//매개변수로 전달된 uri가 위의 배열내에 포함되어 잇다면 count++; 
		//따라서 count 가 최종적으로 0보다 크다면 , 포함되어 있다는 것임 즉 회원인증 서비스가 필요한 경우임..
		int count=0;
		for( String str : secureURI) {
			if(str.equals(uri)) { //보안이필요한 uri 임이 발견됨..
				count++;
			}
		}
		
		if(count>0) {
			return true;
		}else {
			return false;			
		}
	}

	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		Object obj=null; //하위 컨트롤러들이 반환하게될 결과 객체 (ModelAndView or String)
		
		//이 Aspect가 낚아챈 요청에 대한 URI. 정보가 로그인이 필요한 서비스라면 , 세션에 Member 가 들었는지 , 즉 
		//로그인 인증을 거친 회원인지 따져본다.. 
		//만일 로그인 하지 않았다면 에러를 일으켜 적절한 로그인 안내 페이지로 방향 전환 
		//로그인 햇다면 가던길 가게.. 
		HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String uri = request.getRequestURI(); //현재 요청의 uri
		
		if(isSecureUri(uri)) { // true 라면 보안이 필요한 서비스...
			//세션안에 Member가 들어있는지 알아보자 
			HttpSession session = request.getSession();//요청 객체로 부터 이 요청과 연관된 세션 얻기
			Member member = (Member)session.getAttribute("member");
			
			if(member !=null) { //로그인을 햇으므로, 가던길 가게하자
				obj = joinPoint.proceed(); //원래 호출하려던 하위 컨트롤러 메서드 호출
			}else {
				//로그인을 하지 않으면, 예외를 발생시켜서, 에러페이지를 보여준다.. 
				throw new UnAuthorizedException("로그인이 필요한 서비스입니다");
			}
		}else {
			obj = joinPoint.proceed();
		}
		
		return obj;
	}
}




