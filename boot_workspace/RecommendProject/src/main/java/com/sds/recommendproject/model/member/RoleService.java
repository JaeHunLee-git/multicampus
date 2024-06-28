package com.sds.recommendproject.model.member;

import com.sds.recommendproject.domain.Role;


public interface RoleService {
	public Role selectByName(String role_name);
	
}
