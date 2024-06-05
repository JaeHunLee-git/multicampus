package com.sds.recommendproject.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//open api 에서 가져온 영화 1건을 담게될 DTO
@Data
@Document(collection="movie")
public class MovieDoc {

	@Id
	private String id;
	
	private int movie_idx;
	private String movieNm; //요청 파라미터와 중복
	private String openDt;
	private String typeNm;
	private String[] nations;
	private String[] genres;
	private String[] directors;
	private String[] actors;
	private String showTm;
	
}


