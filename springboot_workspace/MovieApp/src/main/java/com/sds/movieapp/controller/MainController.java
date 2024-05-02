package com.sds.movieapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sds.movieapp.common.Pager;
import com.sds.movieapp.domain.Movie;
import com.sds.movieapp.domain.MovieType;
import com.sds.movieapp.model.movie.MovieService;

//메인과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class MainController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Pager pager;
	
	@GetMapping("/")
	public String getMain(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		pager.init(movieService.selectCount(), currentPage);
		
		//영화 10편 가져오기
		Map map = new HashMap();
		map.put("startIndex", pager.getStartIndex()); //몇번째 부터
		map.put("rowCount", 3); //몇건
		
		List<Movie> movieList = movieService.selectAll(map);//3단계 : 일 시키기
		List<MovieType> movieTypeList = movieService.getMovieTypeList(); //3단계 : 일 시키기
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("movieTypeList", movieTypeList);//4단계: 뷰에 보여줄 결과 저장
		
		return "main/index";
	}
}
