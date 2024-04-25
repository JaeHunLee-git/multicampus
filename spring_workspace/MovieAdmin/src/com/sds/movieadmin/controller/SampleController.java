package com.sds.movieadmin.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

@RestController
public class SampleController {
	
	//샘플1
	@GetMapping(value="/movie/sample1", produces="application/json;charset=UTF-8")
	public String sample1() {
	    // 파라메터 설정
		String curPage = "";					//현재페이지
		String itemPerPage = "";		//결과row수
		String movieNm = "";						//영화명
		String directorNm = "";				//감독명
		String openStartDt = "";			//개봉연도 시작조건 ( YYYY )
		String openEndDt = "";				//개봉연도 끝조건 ( YYYY )	
		String prdtStartYear = "";	//제작연도 시작조건 ( YYYY )
		String prdtEndYear = "";			//제작연도 끝조건    ( YYYY )
		String repNationCd = "";			//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
		String[] movieTypeCdArr =null;	//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)
		
		
		//불러오기 테스트
		// 발급키
		String key = "8ac8ba9af16821f7cb3cab09288b6e18";
		
		// KOBIS 오픈 API Rest Client를 통해 호출
	    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    
	    String movieCdResponse=null;	
		try {
		    movieCdResponse = service.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("nationCd",nationCd);		
		return movieCdResponse;
	}
	
	//샘플2
	@GetMapping("/movie/sample2")
	public String sample2() {
		return "sample/movie/sample2";
	}
	
	//샘플3
	@GetMapping("/movie/sample3")
	public String sample3() {
		return "sample/movie/sample3";
	}
	
}	
