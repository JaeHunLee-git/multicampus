package com.sds.mall.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.SnS;

@Repository
public class MybatisSnSDAO implements SnSDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public SnS select(int sns_idx) {
		return sqlSessionTemplate.selectOne("SnS.select", sns_idx);
	}

	@Override
	public SnS selectByName(String sns_name) {
		return sqlSessionTemplate.selectOne("SnS.selectByName", sns_name);
	}

}
