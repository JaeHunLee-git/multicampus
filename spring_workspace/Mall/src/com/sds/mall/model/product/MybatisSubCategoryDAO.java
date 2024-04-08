package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//인터페이스 구현체..
@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	

	public List selectAll() {
		return null;
	}

	@Override
	public List selectAllByTopIdx(int topcategory_idx) {
		return sqlSessionTemplate.selectList("SubCategory.selectAllByTopIdx", topcategory_idx);
	}

}	
