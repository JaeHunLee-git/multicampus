package com.sds.mall.model.member;

import com.sds.mall.domain.SnS;

public interface SnSDAO {
	
	public SnS select(int sns_idx);
	public SnS selectByName(String sns_name);
}
