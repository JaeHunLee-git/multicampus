package com.sds.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sds.loginapp.domain.Member;
import com.sds.loginapp.model.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/*-------------------------------------------------
	로그인 폼 요청
	-------------------------------------------------*/
	@GetMapping("/member/loginform")
	public String getLoginForm() {
		return "member/loginform";
	}

	
	/*-------------------------------------------------
	로그인 처리 ( 동기방식 요청에 대한 )
	-------------------------------------------------*/
	@PostMapping("/member/login")
	public String login(Member member) {
		
		log.debug("id is "+member.getId());
		log.debug("id pass "+member.getPass());
		
		Member dto = memberService.getMember(member);
		
		log.debug("회원 로그인 성공 ");
		
		return "redirect:/";
	}
	
}
