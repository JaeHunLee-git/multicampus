package com.sds.mall.domain;

import lombok.Data;

@Data
public class Color {
	private int color_idx;
	private String color_name;
	private Product product; //부모 참조
}
