package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.Product;

// Mybatis건 Hibernate  건 상관없는 가장 최상위 ProductDAO 인터페이스 정의
// 이 객체의 역할은 Service가 DAO를 느슨하게 보유하기 위함이다..
public interface ProductDAO {
	public List selectAll(); //모든 상품 가져오기 
	public List selectAllBySubIdx(int subcategory_idx);//선택된 하위 카테고리에 소속된 상품 들 가져오기
																				//select * from product where subcategory_idx=3;
	public void insert(Product product);//상품 한건 넣기
	public void update(Product product); //상품 한건 수정 
	public void delete(Product product); //상품 1건 삭제
	
}








