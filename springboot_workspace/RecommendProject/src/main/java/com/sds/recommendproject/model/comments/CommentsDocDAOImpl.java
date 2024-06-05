package com.sds.recommendproject.model.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sds.recommendproject.domain.CommentsDoc;
import com.sds.recommendproject.exception.CommentsException;



@Repository
public class CommentsDocDAOImpl implements CommentsDocDAO{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List selectAllByMember(int member_idx) {
		Query query = new Query();
		
		query.addCriteria(Criteria.where("member_idx").is(member_idx));
		return mongoTemplate.find(query, CommentsDoc.class);
	}
	
	public void insert(CommentsDoc comments) throws CommentsException{
		
		try {
			CommentsDoc dto = mongoTemplate.insert(comments);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommentsException("몽고DB 코멘트 등록실패");
		}
	}

	public CommentsDoc select(int member_idx) {
		Query query = new Query();
		query.addCriteria(Criteria.where("member_idx").is(member_idx));
		
		CommentsDoc comments = mongoTemplate.findOne(query, CommentsDoc.class);
		return comments;
	}
	
}
