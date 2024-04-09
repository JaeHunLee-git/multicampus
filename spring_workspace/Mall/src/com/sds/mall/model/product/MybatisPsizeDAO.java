package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Psize;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Psize psize) {
		sqlSessionTemplate.insert("Psize.insert", psize);
	}
	
}
