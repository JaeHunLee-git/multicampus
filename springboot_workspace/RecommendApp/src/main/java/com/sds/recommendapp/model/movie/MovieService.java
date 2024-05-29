package com.sds.recommendapp.model.movie;

import java.util.List;
import java.util.Map;

import com.sds.recommendapp.domain.Movie;


public interface MovieService {
	public int selectCount();
	public List selectAll(Map map);
	public Movie select(int movie_idx);
	public List getMovieTypeList();//영화유형 가져오기 (장편, 단편, 옴니버스..)
}
