package com.sds.movieadmin.model.admin;

import com.sds.movieadmin.domain.Admin;

public interface AdminDAO {
	
	public void insert(Admin admin);
	public Admin loginCheck(Admin admin);
}
