package com.sds.movieapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sds.movieapp.common.Pager;
import com.sds.movieapp.domain.NoticeDoc;
import com.sds.movieapp.exception.NoticeException;
import com.sds.movieapp.model.cs.notice.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	
	//게시물 목록 페이지 요청
	@GetMapping("/cs/notice/list")
	public String getListPage() {
		
		return "cs/notice/list";
	}
	
	//글쓰기 폼 요청 처리 
	@GetMapping("/cs/notice/writeform")
	public String getWriteForm() {
		return "cs/notice/regist";
	}
	
}








