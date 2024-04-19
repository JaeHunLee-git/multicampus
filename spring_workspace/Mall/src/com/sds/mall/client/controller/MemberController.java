package com.sds.mall.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.MemberException;
import com.sds.mall.model.member.MemberService;

//회원과 관련된 요청을 전담하는 하위 컨트롤러 
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("shop/member/login");
		
		return mav;
	}
	
	
	//회원가입 폼 요청 처리 
	@GetMapping("/member/registform")
	public ModelAndView getRegistForm() {
		ModelAndView mav = new ModelAndView("shop/member/regist");
		
		return mav;
	}
	
	//회원가입 요청 처리 
	@PostMapping("/member/regist")
	@ResponseBody //이 어노테이션을 명시하면, 아래의 return 에 의한 문자열은 ViewResolver에 의해 jsp로 조합되지 않고 순수 데이터로 전송이 된다..
	public String regist(Member member) {
		//스프링은 클라이언트의 파라미터명과 서버측의 DTO의 멤버변수 명이 일치할 경우 자동 매핑을 처리
		//System.out.println("uid = "+member.getUid());
		//System.out.println("pass = "+member.getMemberDetail().getPassword());
		//System.out.println("nickname = "+member.getNickname());
		//System.out.println("email = "+member.getEmail());
		//System.out.println("phone = "+member.getMemberDetail().getPhone());
		//System.out.println("addr = "+member.getMemberDetail().getAddr());
		
		//3단계: 서비스에게 일 시키기
		memberService.regist(member); //이 메서드 호출 후, 우려했던 예외가 전달될 경우, 컨트롤러의 
		//기능 중 예외를 감지하는 @ExceptionHandler가 명시된 메서드가 호출된다.. 
		
		//클라이언트가 비동기방식으로 요청을 햇으므로, 응답 데이터는 jsp가 아니다 
		return "ok"; //ViewResolver에 의해 jsp로 해석하지마!!
	}
	
	//로그인 요청 처리 
	@PostMapping("/member/login")
	@ResponseBody
	public String login(Member member, HttpSession session) {
		//3단계: 일 시키기 
		Member dto = memberService.login(member);
		//dto 는 회원 로그인 성공한 者의 정보이다 
		//dto가 죽기 전에, 이 요청과 관련된 세션에 담아놓으면, 이 세션이 끝날때까지는 계속 꺼내먹을 수 있다 
		session.setAttribute("member", dto);
		
		return "ok";
	}
	
	//로그아웃 요청 처리 
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		//세션을 더이상 사용하지 못하게 하면 됨 
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@ExceptionHandler(MemberException.class)
	@ResponseBody //비동기 요청에 대한 에러 처리 이므로, 응답 정보 또한 순수 데이터를 에러 응답을 보내자
	public String handle(MemberException e) {
		return "fail";
	}
	
}









