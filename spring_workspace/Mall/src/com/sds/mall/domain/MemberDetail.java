package com.sds.mall.domain;

import lombok.Data;

@Data
public class MemberDetail {
	private int member_detail_idx;
	private String password;
	private String phone;
	private String addr;
	
	//DB 릴레이션에서 부모인 Member를 참조 
	private Member member;
}
