package com.sds.recommendapp.model.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.recommendapp.domain.Role;

@Mapper
public interface RoleDAO {
	public List selectAll();
	public Role selectByName(String role_name);
	
}
