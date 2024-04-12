package com.sds.mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Psize;
import com.sds.mall.exception.PsizeException;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(Psize psize) throws PsizeException{ //서비스에 전달
		int result = sqlSessionTemplate.insert("Psize.insert", psize);
		
		//result=0; //일부러 테스트 목적
		
		if(result <1) {
			throw new PsizeException("상품의 사이즈 등록에 실패하였습니다");
		}
	}
	
}
