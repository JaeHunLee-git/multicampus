package com.sds.recommendapp.model.recommend;

import java.util.List;
import java.util.Map;

import com.sds.recommendapp.domain.MovieDoc;



public interface MovieDocDAO {
	
	public int selectCount();//총 레코드 수
	public List selectAll(Map map);//모든 영화 
	public MovieDoc select(int movie_idx);//영화 한건 가져오기 
	public void insert(MovieDoc movieDoc);
	
}
