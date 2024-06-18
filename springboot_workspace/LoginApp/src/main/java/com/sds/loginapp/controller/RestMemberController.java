package com.sds.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.loginapp.domain.Member;
import com.sds.loginapp.model.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/rest/member/login")
	public ResponseEntity loginCheck(Member member) {
		
		return ResponseEntity.ok("로그인 성공(컨트롤러 메시지)");
	}
	
	@PostMapping("/rest/member")
	public ResponseEntity regist(Member member) {
		
		//비밀번호 암호화
		String encodedPass = passwordEncoder.encode(member.getPass());
		member.setPass(encodedPass);
		
		memberService.regist(member);
		
		return ResponseEntity.ok("가입 성공(컨트롤러 메시지)");
	}
	
}
