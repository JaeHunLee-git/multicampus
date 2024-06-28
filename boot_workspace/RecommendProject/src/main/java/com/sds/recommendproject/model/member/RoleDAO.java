package com.sds.recommendproject.model.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendproject.domain.Role;

@Mapper
public interface RoleDAO {
	public List selectAll();
	public Role selectByName(String role_name);
	
}
