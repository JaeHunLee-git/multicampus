package com.sds.recommendproject.model.recommend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.recommendproject.domain.MovieDoc;
import com.sds.recommendproject.exception.MovieException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MovieDocDAOImpl implements MovieDocDAO{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	/*
	public List selectAllByMember(int member_idx) {
		Query query = new Query();
		query.addCriteria(Criteria.where("member_idx").is(member_idx));
		
		return mongoTemplate.find(query, MovieDoc.class);
	}
	*/
	@Override
	public List selectAll(Map map) {
		
		List list = null;
		
		if(map==null) {
			list = mongoTemplate.findAll(MovieDoc.class);
		}else {
			int startIndex = (int)map.get("startIndex"); //몇번째 index부터~
			int rowCount =(int)map.get("rowCount"); //몇개?
			Query query = new Query().skip(startIndex).limit(rowCount);
			list = mongoTemplate.find(query, MovieDoc.class);			
		}
		
		return list;
	}

	@Override
	public MovieDoc select(int movie_idx) {
		log.debug("movie_idx is "+movie_idx);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("movie_idx").is(movie_idx));
		MovieDoc movie =  mongoTemplate.findOne(query, MovieDoc.class);
		log.debug("넘겨받은 "+movie_idx+"에  해당하는 movie 의 이름은 "+movie.getMovieNm());
		
		return movie;
	}

	@Override
	public void insert(MovieDoc movie) throws MovieException{
		MovieDoc dto = mongoTemplate.insert(movie);
		if(dto == null) {
			throw new MovieException("몽고DB 영화 등록 실패");
		}
	}
	
}
