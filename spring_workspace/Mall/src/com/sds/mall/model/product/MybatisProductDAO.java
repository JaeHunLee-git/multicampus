package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Product;

//Mybatis 기술을 이용한  CRUD 를 담당하는 ProductDAO 의 구현체, 자식객체
@Repository
public class MybatisProductDAO implements ProductDAO{

	@Autowired  //스프링 주도하여 데이터베이스를 연동하였으며,  mybatis 또한 mybatis spring 의존성 라이브러리를 이용하여
	//스프링이 제어하기 때문에, 개발자는 오직 SqlSessionTemplate 넘겨받아 사용하기만 하면 됨
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllBySubIdx(int subcategory_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product product) {
		sqlSessionTemplate.insert("Product.insert", product);
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		
	}

}
