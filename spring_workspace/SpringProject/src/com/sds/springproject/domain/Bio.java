package com.sds.springproject.domain;

import lombok.Data;

@Data
public class Bio {
	private int bio_idx;
	private String blood;
	private float height;
	private float weight;	
	//부모의 정보 
	private Member member;
}
