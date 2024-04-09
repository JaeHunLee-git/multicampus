package com.sds.mall.model.product;

import com.sds.mall.domain.Color;

//서비스가 느슨하게 보유하기 위한 ColorDAO의 최상위 인터페이스 
public interface ColorDAO {
	public void insert(Color color);//1건 등록
}
