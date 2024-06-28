package com.sds.recommendproject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document
public class Nation {
	//@Id
	//private String id;
	
	private String fullCd;
	private String korNm;
	private String engNm;
	
}
