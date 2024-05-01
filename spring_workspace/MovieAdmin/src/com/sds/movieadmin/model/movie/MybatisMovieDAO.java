package com.sds.movieadmin.model.movie;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;

@Repository
public class MybatisMovieDAO implements MovieDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public int selectCount() {
		return sqlSessionTemplate.selectOne("Movie.selectCount");
	}
	
	public List selectAll(Map map) {
		return sqlSessionTemplate.selectList("Movie.selectAll", map);
	}
	
	public void insert(Movie movie) throws MovieException{
		int result = sqlSessionTemplate.insert("Movie.insert", movie);
		
		if(result <1) {
			throw new MovieException("영화 등록 실패");
		}
	}
	
	@Override
	public void deleteAll() {
		int result = sqlSessionTemplate.delete("Movie.deleteAll");
	}
	
}




