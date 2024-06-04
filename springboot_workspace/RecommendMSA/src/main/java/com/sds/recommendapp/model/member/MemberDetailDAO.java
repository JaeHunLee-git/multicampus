package com.sds.recommendapp.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendapp.domain.MemberDetail;



@Mapper
public interface MemberDetailDAO {
	
	public int insert(MemberDetail memberDetail);	
	
}
