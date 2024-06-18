package com.sds.loginapp.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.loginapp.domain.Member;

@Mapper
public interface MemberDAO {

	public int insert(Member member);
	public Member select(Member member);
	public Member selectById(String id);
}
