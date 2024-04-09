package com.sds.mall.domain;
import lombok.Data;

@Data
public class Psize {
	private int psize_idx;
	private String size_name;
	private Product product; //부모 참조
}
