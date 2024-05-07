package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.movieapp.domain.CustomUserDetails;
import com.sds.movieapp.domain.Member;
import com.sds.movieapp.domain.Movie;
import com.sds.movieapp.model.movie.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	//영화 상세보기 요청
	@GetMapping("/movie/detail")
	public String getDetail(Model model ,@RequestParam(value="movie_idx", defaultValue="0") int movie_idx) {
		
		//최종적으로 우리 mysql의 정보와 + open API의 영화정보가 합쳐진 DTO 가져옴 
		Movie movie = movieService.select(movie_idx);//영화 한건 조회 
		System.out.println("영화 url은 "+movie.getUrl());
		System.out.println("영화명 "+movie.getMovieNm());
		
		model.addAttribute("movie", movie);

		return "movie/detail";
	}
	
}
