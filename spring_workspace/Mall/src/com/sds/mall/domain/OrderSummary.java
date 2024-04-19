package com.sds.mall.domain;

import lombok.Data;

@Data
public class OrderSummary {
	private int ordersummary_idx;
	private Member member;
	private Paymethod paymethod;
	private Receiver receiver;
	private String orderdate;
	private int total_buy; //구매금액 
	private int total_pay; //실제 지불한 금액 (할인 등에 의해..)
}
