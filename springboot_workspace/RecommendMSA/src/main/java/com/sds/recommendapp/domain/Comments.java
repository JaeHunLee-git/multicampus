package com.sds.recommendapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Comments {
	private int member_idx;
	private int movie_idx;
	private String content;
}
