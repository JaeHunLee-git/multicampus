package com.sds.movieadmin.model.admin;

import com.sds.movieadmin.domain.Admin;

public interface AdminService {

	public void regist(Admin admin);
	public Admin loginCheck(Admin admin);
	
}
