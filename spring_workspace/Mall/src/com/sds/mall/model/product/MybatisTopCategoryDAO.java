package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.TopCategory;

//스프링의 component:scan 에게 이 DAO의 인스턴스 생성을 부탁하자  
@Repository
public class MybatisTopCategoryDAO implements TopCategoryDAO{
	
	//자동 주입 받기 
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		return sqlSessionTemplate.selectList("TopCategory.selectAll");
	}

	@Override
	public TopCategory select(int topcategory_idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
