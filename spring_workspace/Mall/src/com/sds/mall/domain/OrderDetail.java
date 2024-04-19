package com.sds.mall.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int orderdetail_idx;
	private OrderSummary orderSummary; //어떤 주문에 대한 것인지..
	private Product product;
	private int ea;
	private String product_name;
	private int price;
}
