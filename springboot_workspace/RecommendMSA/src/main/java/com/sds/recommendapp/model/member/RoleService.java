package com.sds.recommendapp.model.member;

import com.sds.recommendapp.domain.Role;


public interface RoleService {
	public Role selectByName(String role_name);
	
}
