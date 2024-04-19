package com.sds.mall.domain;

import lombok.Data;

@Data
public class Receiver {
	private int receiver_idx;
	private Member member; 
	private String addr;
}
