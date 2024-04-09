package com.sds.mall.model.product;

import java.util.List;

public interface SubCategoryService {
	public List selectAll(); //모든 거 가져오기
	public List selectAllByTopIdx(int topcategory_idx);//부모에 소속된 목록 가져오기
	
}
