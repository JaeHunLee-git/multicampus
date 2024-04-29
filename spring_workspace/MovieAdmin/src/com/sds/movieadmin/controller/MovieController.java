package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.model.movie.MovieApiService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieApiService movieApiService;
	
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
	
	
}










