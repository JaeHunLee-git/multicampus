package com.sds.recommendapp.model.recommend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.recommendapp.domain.MovieDoc;



@Repository
public class MovieDocDAOImpl implements MovieDocDAO{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//몽고DB에 존재하는 모든 영화가져오기 
	public List selectAll(Map map) {  //selectAll(null) 페이징 처리 안하기 위해..
		List list  = null;
		
		if(map==null) { //페이징 처리 무시하고 가져오기
			list = mongoTemplate.findAll(MovieDoc.class);
		}else {//페이징 처리 적용하여 가져오기
			int startIndex =(int)map.get("startIndex");
			int rowCount =(int)map.get("rowCount");
			Query query = new Query().skip(startIndex).limit(rowCount);
			list = mongoTemplate.find(query, MovieDoc.class);
		}
		return list;
	}

	@Override
	public MovieDoc select(int movie_idx) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movie_idx").is(movie_idx));
		MovieDoc movieDoc = mongoTemplate.findOne(query, MovieDoc.class);
		
		return movieDoc;
	}

	@Override
	public void insert(MovieDoc movieDoc) {
		// TODO Auto-generated method stub
		
	}

}
