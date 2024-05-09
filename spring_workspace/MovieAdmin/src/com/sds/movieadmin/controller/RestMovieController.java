package com.sds.movieadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;
import com.sds.movieadmin.exception.UploadException;
import com.sds.movieadmin.model.movie.MovieApiService;
import com.sds.movieadmin.model.movie.MovieService;

@RestController
public class RestMovieController {
	
	@Autowired
	private MovieApiService movieApiService; //open api 서비스 
	
	@Autowired
	private MovieService movieService;
	
	//영화 검색 (국가 및 영화타입을 파라미터로 받는다)
	@GetMapping("/search/movie")
	public List getList(Movie movie) {
		//System.out.println("국가 코드 = "+movie.getRepNationCd());
		//System.out.println("영화유형은 "+movie.getMovieTypeCdArr()[0]);
		
		List movieList = movieApiService.getMovieList(movie);
		
		return movieList;
	}
	
	//한편 등록
	@PostMapping("/movie")
	public ResponseEntity regist(Movie movie) {
		
		System.out.println("영화코드 "+movie.getMovieCd());
		System.out.println("영화 url "+movie.getUrl());
		//3단계: 일 시키기 
		movieService.regist(movie);
		
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();//200 응답
		
		return entity;
	}
	
	
	//엑셀로 일괄등록 하기
	@PostMapping("/excel/movie")
	public ResponseEntity registExcel(Movie movie) {
		movieService.registExcel(movie);
		
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		return entity;
	}
	
	
	@ExceptionHandler(UploadException.class)
	public ResponseEntity handle(UploadException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
	
	@ExceptionHandler(MovieException.class)
	public ResponseEntity handle(MovieException e) {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}
}







