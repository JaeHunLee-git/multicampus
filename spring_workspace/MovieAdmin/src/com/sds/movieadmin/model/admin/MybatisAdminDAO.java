package com.sds.movieadmin.model.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.movieadmin.domain.Admin;
import com.sds.movieadmin.exception.AdminException;

@Repository
public class MybatisAdminDAO implements AdminDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Admin admin) throws AdminException{
		int result = sqlSessionTemplate.insert("Admin.insert", admin);
		
		if(result <1) {
			throw new AdminException("관리자 등록 실패");
		}
	}

	@Override
	public Admin loginCheck(Admin admin) {

		return null;
	}
	
}
