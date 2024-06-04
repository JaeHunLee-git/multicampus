package com.sds.recommendapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document
public class Director {
	
	//private String id;
	
	private String peopleNm;
	private String peopleNmEn;
}
