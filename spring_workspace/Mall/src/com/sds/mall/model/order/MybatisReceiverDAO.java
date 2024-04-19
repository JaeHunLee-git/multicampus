package com.sds.mall.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Member;

@Repository
public class MybatisReceiverDAO implements ReceiverDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAllByMember(Member member) {
		return sqlSessionTemplate.selectList("Receiver.selectAllByMember", member);
	}
	
}




