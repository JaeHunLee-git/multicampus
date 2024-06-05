package com.sds.recommendproject.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendproject.domain.Member;



@Mapper
public interface MemberDAO {
	public int insert(Member member);
	public Member selectByUid(String uid);
	
}
