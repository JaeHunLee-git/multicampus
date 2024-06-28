package com.sds.recommendproject.model.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendproject.domain.Sns;

@Mapper
public interface SnsDAO {
	public List selectAll();
	public Sns selectByName(String sns_name);
}
