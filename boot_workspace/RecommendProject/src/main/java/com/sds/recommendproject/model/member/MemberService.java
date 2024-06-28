package com.sds.recommendproject.model.member;

import com.sds.recommendproject.domain.Member;

public interface MemberService {

	public Member selectByUid(String uid);
	
}
