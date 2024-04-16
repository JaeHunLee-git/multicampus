package com.sds.mall.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Product;
import com.sds.mall.exception.ProductException;

//Mybatis 기술을 이용한  CRUD 를 담당하는 ProductDAO 의 구현체, 자식객체
@Repository
public class MybatisProductDAO implements ProductDAO{

	@Autowired  //스프링 주도하여 데이터베이스를 연동하였으며,  mybatis 또한 mybatis spring 의존성 라이브러리를 이용하여
	//스프링이 제어하기 때문에, 개발자는 오직 SqlSessionTemplate 넘겨받아 사용하기만 하면 됨
	private SqlSessionTemplate sqlSessionTemplate;
	
	//모든 레코드 가져오기
	public List selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
	}
	
	@Override
	public Product select(int product_idx) {
		return sqlSessionTemplate.selectOne("Product.select", product_idx);
	}
	
	@Override
	public List selectAllByTopIdx(int topcategory_idx) {
		return sqlSessionTemplate.selectList("Product.selectAllByTopIdx", topcategory_idx);
	}
	
	@Override
	public List selectAllBySubIdx(int subcategory_idx) {
		return sqlSessionTemplate.selectList("Product.selectAllBySubIdx", subcategory_idx);
	}

	//상품 등록
	public void insert(Product product) throws ProductException{ //서비스에 전달 
		int result = sqlSessionTemplate.insert("Product.insert", product);
		
		//result=0; //예외 테스트 목적 상 0 
		
		if(result <1) { // insert 처리 안됨.
			throw new ProductException("상품이 등록되지 않았습니다");
		}
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
