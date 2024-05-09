package com.sds.movieadmin.model.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.MovieDoc;
import com.sds.movieadmin.exception.MovieException;

@Repository
public class MongoMovieDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public void insert(MovieDoc movieDoc) throws MovieException{
		
		try {
			mongoTemplate.insert(movieDoc, "movie");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MovieException("몽고DB movie 컬렉션에 입력 실패");
		}
	}
	
	//모든 다큐먼트 삭제 
	public void delete() throws MovieException {
		try {
			mongoTemplate.remove(new Query(), "movie");
		} catch (Exception e) {
			e.printStackTrace();
			throw new MovieException("몽고DB movie 컬렉션 삭제 실패");
		}
	}
	
	
}
