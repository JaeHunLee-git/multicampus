package com.sds.movieapp.model.cs.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.sds.movieapp.domain.NoticeDoc;
import com.sds.movieapp.exception.NoticeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MongoNoticeDAO implements NoticeDAO{
	
	@Autowired
	private MongoTemplate mongoTemplate; //xml 이 아닌 @Configuration 에 의해 등록됨

	public int selectCount() {
		Query query = new Query();
		
		long total = mongoTemplate.count(query, NoticeDoc.class);
		return (int)total;
	}

	@Override
	public List selectAll(Map map) {
		int startIndex = (int)map.get("startIndex"); //몇번째 index부터~
		int rowCount =(int)map.get("rowCount"); //몇개?
		
		Query query = new Query().skip(startIndex).limit(rowCount);
		
		return mongoTemplate.find(query, NoticeDoc.class);
	}

	@Override
	public NoticeDoc select(NoticeDoc notice) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(notice.getId()));
		NoticeDoc dto = mongoTemplate.findOne(query, NoticeDoc.class);
		
		return dto;
	}
	
	
	public void insert(NoticeDoc notice) throws NoticeException{
		NoticeDoc dto = mongoTemplate.insert(notice);
		
		log.info("글 등록 결과는 "+dto);
		
		if(dto ==null) {
			throw new NoticeException("글등록 실패");
		}
	}

	@Override
	public void update(NoticeDoc notice) throws NoticeException{
		NoticeDoc dto = mongoTemplate.save(notice);
		
		if(dto ==null) {
			throw new NoticeException("글 수정 실패");
		}
	}

	@Override
	public void delete(NoticeDoc notice) throws NoticeException{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(notice.getId()));
		
		DeleteResult result = mongoTemplate.remove(query, NoticeDoc.class);
		
		if(result.getDeletedCount() < 1) {
			throw new NoticeException("글 삭제 실패");
		}
	}
	
}
