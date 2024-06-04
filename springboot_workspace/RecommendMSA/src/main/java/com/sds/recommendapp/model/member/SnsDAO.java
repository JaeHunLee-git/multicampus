package com.sds.recommendapp.model.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendapp.domain.Sns;

@Mapper
public interface SnsDAO {
	public List selectAll();
	public Sns selectByName(String sns_name);
}
