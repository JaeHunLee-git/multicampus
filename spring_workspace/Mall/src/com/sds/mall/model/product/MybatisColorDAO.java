package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Color;

//ColorDAO를 구현한 자식 객체 , 이 객체가 Repository로 등록되어야 함
@Repository
public class MybatisColorDAO implements ColorDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Color color) {
		sqlSessionTemplate.insert("Color.insert", color);
	}
	
}



