package com.sds.mall.domain;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_idx;
	private String topname;
	
	//"하나의 TopCategory가 여러개의 ( 多수) 하위 카테고리를 거느리고 있다" 라는 개념을 자바로
	//표현하면 아래와 같다
	private List<SubCategory> subList;
}
