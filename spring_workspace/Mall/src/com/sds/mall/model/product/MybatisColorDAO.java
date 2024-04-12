package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Color;
import com.sds.mall.exception.ColorException;

//ColorDAO를 구현한 자식 객체 , 이 객체가 Repository로 등록되어야 함
@Repository
public class MybatisColorDAO implements ColorDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Color color) throws ColorException{ //서비스에 예외 전달
		int result = sqlSessionTemplate.insert("Color.insert", color);
		
		if(result <1) {
			throw new ColorException("상품 색상 등록 실패");
		}
		
	}
	
}



