package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.TopCategory;

//TopCategory 테이블에 대한 CRUD를 담당하는 DAO 객체 들 중 최상위 객체를 정의
public interface TopCategoryDAO {
	public List selectAll();//목록 가져오기
	public TopCategory select(int topcategory_idx); //한건 가져오기 
	
}
