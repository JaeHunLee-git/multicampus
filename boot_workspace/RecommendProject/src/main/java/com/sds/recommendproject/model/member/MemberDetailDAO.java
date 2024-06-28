package com.sds.recommendproject.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendproject.domain.MemberDetail;



@Mapper
public interface MemberDetailDAO {
	
	public int insert(MemberDetail memberDetail);	
	
}
