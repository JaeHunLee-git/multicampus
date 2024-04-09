package com.sds.mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//하위 컨트롤러 서비스의 구현체 
@Service  // <component scan=""> 에 의해 메모리에 인스턴스 생성 대상이 될 수 있다
public class SubCategoryServiceImpl implements SubCategoryService{
	
	//느슨하게(상위 객체자료형을 ..) DAO를 보유하자!!
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllByTopIdx(int topcategory_idx) {
		return subCategoryDAO.selectAllByTopIdx(topcategory_idx);
	}
	
}
