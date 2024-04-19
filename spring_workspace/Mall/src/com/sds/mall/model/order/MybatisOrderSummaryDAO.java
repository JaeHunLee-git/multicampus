package com.sds.mall.model.order;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.OrderSummary;
import com.sds.mall.exception.OrderException;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(OrderSummary orderSummary) throws OrderException{
		int result = sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);
		if(result <1) {
			throw new OrderException("주문 등록 실패");
		}
	}

}
