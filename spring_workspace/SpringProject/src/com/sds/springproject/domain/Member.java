package com.sds.springproject.domain;

import lombok.Data;

@Data
public class Member {
	private int member_idx;
	private String id;
	private String pass;
	private String name;
	private String email;
	private String regdate;
}
