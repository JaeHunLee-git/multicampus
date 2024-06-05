package com.sds.recommendproject.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Member {
	private int member_idx;
	private String uid;
	private String nickname;
	private String email;
	private Sns sns;
	private Role role;

	private MemberDetail memberDetail;
}
