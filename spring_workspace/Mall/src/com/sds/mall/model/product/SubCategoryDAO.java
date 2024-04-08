package com.sds.mall.model.product;

import java.util.List;

//SubCategoryDAO의 최상위 인터페이스 
public interface SubCategoryDAO {
	public List selectAll(); //모든 거 가져오기
	public List selectAllByTopIdx(int topcategory_idx);//부모에 소속된 목록가져오기
	
}
