package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderSummary;

public interface OrderService {
	public List selectReceiver(Member member);//받는 사람 가져오기 
	public List selectPaymethod();//결제 방법 가져오기 
	
	//주문 등록 
	public void order(OrderSummary orderSummary);
	
}
