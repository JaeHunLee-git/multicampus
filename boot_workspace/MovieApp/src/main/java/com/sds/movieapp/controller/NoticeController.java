package com.sds.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sds.movieapp.domain.NoticeDoc;
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
	
	//글내용 보기 페이지 요청 
	@GetMapping("/cs/notice/detailform")
	public String getDetailForm(Model model , NoticeDoc noticeDoc) {
		
		model.addAttribute("noticeDoc", noticeDoc);
		
		return "cs/notice/content";
	}
}








