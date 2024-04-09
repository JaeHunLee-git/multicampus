package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.Product;

public interface ProductService {
	//상품목록, 등록, 수정, 삭제 
	
	public void regist(Product product); //파일을 포함한 상품 등록 (db insert  + 업로드)
	public List selectAll(); //모든 상품 가져오기 
	public List selectAllBySubIdx(int subcategory_idx); //하위 카테고리에 소속된 상품 목록 가져오기
	public Product select(int product_idx); //상품 1건 보기 
	public void update(Product product); //상품 1건 수정 
	public void delete(Product product); //상품 1건 삭제 
	
	
}
