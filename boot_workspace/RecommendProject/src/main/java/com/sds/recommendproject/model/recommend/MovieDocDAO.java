package com.sds.recommendproject.model.recommend;

import java.util.List;
import java.util.Map;

import com.sds.recommendproject.domain.MovieDoc;


public interface MovieDocDAO {
	
	public int selectCount(); //총 레코드 수 
	//public List selectAllByMember(int member_idx);
	public List selectAll(Map map);
	public MovieDoc select(int movie_idx);
	public void insert(MovieDoc movieDoc);
}
