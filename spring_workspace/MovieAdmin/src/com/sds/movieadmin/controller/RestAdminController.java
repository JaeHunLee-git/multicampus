package com.sds.movieadmin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;
import com.sds.movieadmin.model.admin.AdminService;

@RestController
public class RestAdminController {
	
	@Autowired
	private AdminService adminService;
	
	//관리자 가입 요청 처리 
	@PostMapping("/admin")
	public ResponseEntity regist(Admin admin) {
		
		adminService.regist(admin);//3단계: 일 시키기
		ResponseEntity entity=ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}
	
	//로그인 요청 처리 
	@PostMapping("/auth/admin")
	public ResponseEntity login(Admin admin, HttpSession session) {
		
		Admin dto = adminService.loginCheck(admin);
		
		//세션에 관리자 정보를 담아야 함..
		session.setAttribute("admin", dto);
		
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity handle(AdminException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		return entity;	
	}
}







