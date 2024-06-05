package com.sds.recommendproject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="comments")
public class CommentsDoc {

	@Id
	private String id;
	
	private int member_idx; //누가
	private int movie_idx; //어떤 영화에 대해
	private String content; //어떤 평을
	private float score;//긍,부정 점수
}
