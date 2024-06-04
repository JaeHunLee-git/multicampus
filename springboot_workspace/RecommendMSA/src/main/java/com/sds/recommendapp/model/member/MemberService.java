package com.sds.recommendapp.model.member;

import com.sds.recommendapp.domain.Member;

public interface MemberService {

	public Member selectByUid(String uid);
	
}
