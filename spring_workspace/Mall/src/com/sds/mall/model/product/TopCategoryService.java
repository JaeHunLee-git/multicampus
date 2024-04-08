package com.sds.mall.model.product;

import java.util.List;

import com.sds.mall.domain.TopCategory;

public interface TopCategoryService {
	public List selectAll();
	public TopCategory select(int topcategory_idx);
	
}
