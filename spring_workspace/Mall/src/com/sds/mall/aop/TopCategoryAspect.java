package com.sds.mall.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.model.product.TopCategoryService;

/*
 * 어플리케이션에서 전반적으로 사용되는 공통 코드가 발견된다면, 일일이 코드로 중복작성하지 말고, 
 * 하나의 관점(Aspect) 으로 정의하여, 개발자가 원하는 시점에 발동시키면 된다..
 * DI는 결합도를 낮추는게 목적임.. 
 * AOP는 결합도를 없애버리는게 목적임..
 * 
 * 아래의 클래스는, 쇼핑몰에서 언제나 보여줘야될 최상위 카테고리 조회를 모든 컨트롤러에서 
 * 중복해서 작성하지 않고, AOP기술을 이용하여, 모든 컨트롤러가 작동하는 시점에, 관여하도록
 * 정의하겠다!!
 * */

public class TopCategoryAspect {
	
	@Autowired
	private TopCategoryService topCategoryService;
		
	//이 메서드는, 대표 컨트롤러가 하위 컨트롤러에게 제어권을 넘길때 관여할 예정
	//즉 하위 컨트롤러가 동작할때 관여시키자 
	//코드 작성 전에 스프링 지원하는 AoP 의존성 jar 가 필요, aspect-weaver 
	public Object getTopCategoryList(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnObj=null; //원래 호출하려면 메서드 호출 후 반환되는 객체 (String ,ModelAndView 등)
		
		String targetName = joinPoint.getTarget().getClass().getName(); //원래 호출하려던 객체
		System.out.println("원래 호출하려면 클래스는 "+targetName);
		
		Signature sig=joinPoint.getSignature(); //원래 호출하려면 메서드
		System.out.println("원래 호출하려면 메서드는 "+sig.getName());
		
		
		//쇼핑몰의 모든 요청에 대해서  topCategory를 가져오는 비효율적이다..따라서 필요한 경우만 TopCategory선별해서 
		//구해오려면, 요청의 uri를 파악하여 개발자가 원하는 경우만  TopCategory를 심어주자 
		//따라서 uri를 얻어오려면 request 객체가 필요하다 
		HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uri = request.getRequestURI(); //클라이언트의 URI  정보 
		
		if( 
				uri.equals("/member/regist") || //회원가입 요청의 경우엔 비동기 이므로, TopCategory 필요없다 
				uri.equals("/member/login") || //로그인 요청의 경우도 제외
				uri.equals("/order/cart/regist")  //장바구니 요청의 경우도 제외
		) { 
			//TopCategory를 구하지 않아도 되는 경우
			returnObj=joinPoint.proceed(); //그냥 가던길 가라(하위 컨트롤러 메서드 호출)
		}else {
			//TopCategory가 필요한 경우
			//하위 컨트롤러들의 메서드가 호출되기 전에 TopCategory 목록을 여기서 구해놓자 
			//현재 요청에의해 생성된 request객체에 topList를 심어놓자, 즉 컨트롤러의 topList 가져올 업무를 여기서 대신 처리 
			List topList = topCategoryService.selectAll();
			//원래 호출하려면 메서드 호출함
			returnObj=joinPoint.proceed(); //가던갈 가게 해줌..
			ModelAndView mav=null;
			
			//만일 하위 컨트롤러에서 반환된 자료형이 ModelAndView  인 경우  Model에 topList를 심어놓자
			if(returnObj instanceof ModelAndView) {
				mav = (ModelAndView)returnObj;
				mav.addObject("topList", topList);
			}else {
				//ModelAndView 를 쓰지 않고 저장해야 한다.. 
				request.setAttribute("topList", topList);
			}
		}
		//아래의 return 문에 의해 , 대표 컨트롤러에게 returnObj 인 ModelAndView나 View가 전달되므로, 
		//전달되기 전에, 빨리 심어놓자 
		//아래의 리턴값은 하위 컨트롤러가 반환한 값을 존중해줘야 한다.따라서 어느때는 ModelAndView 될수
		//있고, ResponseBody 가 명시된 경우에는  그냥 String일 수도 있다..따라서 joinPoint.proceed()
		//메서드에서 반환되는 값을 존중해줘서 그래도 반환.. 
		return returnObj;
	}
	
}













