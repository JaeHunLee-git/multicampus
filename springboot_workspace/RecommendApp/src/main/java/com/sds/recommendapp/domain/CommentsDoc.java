package com.sds.recommendapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="comments")
public class CommentsDoc {
	
	@Id
	private String id;
	
	//누가
	private int member_idx;
	
	//어떤영화?
	private int movie_idx;
	
	//어떤평으로
	private String content;
	
	//어떤 점수로.
	private float score;
}