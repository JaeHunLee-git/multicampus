package com.sds.recommendproject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document
public class Genre {
	//@Id
	//private String id;
	
	private String genreNm;
}
