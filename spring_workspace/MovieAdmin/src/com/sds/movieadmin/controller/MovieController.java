package com.sds.movieadmin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.movieadmin.common.Pager;
import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.model.movie.MovieApiService;
import com.sds.movieadmin.model.movie.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieApiService movieApiService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Pager pager;
	
	
	//영화진흥위원회 제공 모든 영화 가져오기 
	@GetMapping("/movie/api/list")
	public String getApiList(Movie movie, Model model) {

		List moveList = movieApiService.getMovieList(movie);//3단계: 일시키기 
		model.addAttribute("movieList", moveList);
		
		return "admin/movie/list";
	}
	
	//사이트 영화 등록폼 요청 처리 
	@GetMapping("/movie/registform")
	public String getRegistForm(Model model) {
		
		//국가 목록 가져오기 
		List nationList = movieApiService.getNationList();
		
		//영화 유형 가져오기
		List movieTypeList = movieApiService.getTypeList();
		
		model.addAttribute("nationList", nationList);
		model.addAttribute("movieTypeList", movieTypeList);
		
		return "admin/movie/regist";
	}
	
	//웹사이트에 등록된 영화 목록 가져오기 요청 처리 , currentPage 파라미터가 전송되어오지 않으면 디폴트값을 "1"로 지정
	@GetMapping("/movie/site/list")
	public String getSiteList(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		
		pager.init(movieService.selectCount(), currentPage); //페이징 처리 계산하기
		
		Map map = new HashMap();
		map.put("startIndex", pager.getStartIndex()); //몇번째 부터
		map.put("rowCount", pager.getPageSize()); //몇개
		
		//3단계: 일 시키기
		List movieList = movieService.selectAll(map);
		
		model.addAttribute("movieList", movieList);//4단계: 결과 저장
		model.addAttribute("pager", pager);//4단계: 결과 저장
		
		return "admin/movie/site_list";
	}
	
	//엑셀 등록 폼 요청 처리 
	@GetMapping("/movie/excel/registform")
	public String getExcelForm() {
		return "admin/movie/upload";
	}
}










