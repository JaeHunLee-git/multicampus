package com.sds.mall.domain;

import lombok.Data;

@Data
public class SubCategory {
	private int subcategory_idx;
	private String subname;
	
	//자바는 fk 대신,  부모 객체를 보유한다(fk는 이미 객체 안의 topcategory_idx로 포함되어 있다) 
	private TopCategory topCategory;
}
