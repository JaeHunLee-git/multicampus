package com.sds.loginapp.model.member;

import com.sds.loginapp.domain.Member;

public interface MemberService {
	public void regist(Member member);
	public Member getMember(Member member);
}
