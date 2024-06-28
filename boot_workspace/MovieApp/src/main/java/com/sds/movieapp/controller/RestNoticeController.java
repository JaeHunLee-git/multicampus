package com.sds.movieapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieapp.domain.Member;
import com.sds.movieapp.domain.NoticeDoc;
import com.sds.movieapp.exception.JwtException;
import com.sds.movieapp.jwt.JwtValidService;
import com.sds.movieapp.model.cs.notice.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestNoticeController {
	
	@Autowired
	private JwtValidService jwtValidService;
	
	@Autowired
	private NoticeService noticeService;
	
	
	//글쓰기 요청 처리 
	@PostMapping("/rest/cs/notice")
	public ResponseEntity regist(@RequestHeader("Authorization") String header, NoticeDoc noticeDoc) {
		
		log.debug("글쓰기 요청 받음");
		
		String token = header.replace("Bearer", "");
		
		Member member = jwtValidService.getMemberFromJwt(token);
		
		if(member ==null) {
			throw new JwtException("로그인이 필요한 서비스입니다");
		}
		
		noticeService.regist(noticeDoc);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	//글목록 요청 처리 
	@GetMapping("/rest/cs/notice")
	public Map selectAll( 
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", defaultValue="10") int pageSize) {
		
		Map map = new HashMap();
		map.put("startIndex", (currentPage-1)*pageSize);
		map.put("rowCount", pageSize);
		
		List noticeList = noticeService.selectAll(map);
		
		map.put("noticeList", noticeList);
		
		//몽고db의 모든 게시물 수 
		int totalRecord = noticeService.selectCount();
		map.put("totalRecord", totalRecord);
		
		
		return map;
	}
	
	//글 상세보기 요청 처리 (Restful 하기 때문에  @Pathvariable 이용해야 함 즉 경로에 파라미터가 포함되어 있기 때문..)
	@GetMapping("/rest/cs/notice/{id}")
	public NoticeDoc select(	@RequestHeader(name="Authorization") String header , 
										@PathVariable(name="id") String id) {
		
		//회원검증
		String token = header.replace("Bearer ", "");
		
		Member member = jwtValidService.getMemberFromJwt(token);
		if(member ==null) {
			throw new JwtException("로그인이 필요한 서비스입니다");
		}
		
		//게시물 가져오기 
		NoticeDoc noticeDoc = new NoticeDoc();
		noticeDoc.setId(id);
		noticeDoc = noticeService.select(noticeDoc);
		
		return noticeDoc;
	}
	
	
}
















