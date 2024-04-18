package com.sds.mall.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_idx;
	private int ea;
	private Member member;
	private Product product;
}
