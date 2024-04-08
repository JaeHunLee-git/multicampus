package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.mall.domain.TopCategory;

//아래의 객체는 서비스 객체이므로, 스프링에게 component-scan 에 의해 자동 인스턴스 생성을 부탁하자
@Service
public class TopCategoryServiceImpl implements TopCategoryService{
	
	//서비스는 DAO에게 일을 시켜야 하므로, has a 관계로 보유하되, 느슨하게 보유하자...
	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	@Override
	public List selectAll() {
		return topCategoryDAO.selectAll();
	}

	@Override
	public TopCategory select(int topcategory_idx) {
		// TODO Auto-generated method stub
		return null;
	}

}
