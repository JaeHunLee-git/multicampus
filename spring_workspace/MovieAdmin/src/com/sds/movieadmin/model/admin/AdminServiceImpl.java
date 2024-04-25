package com.sds.movieadmin.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
	
	public void regist(Admin admin) throws AdminException{
		adminDAO.insert(admin);
	}

	@Override
	public Admin loginCheck(Admin admin) {
	
		return null;
	}
	
}
