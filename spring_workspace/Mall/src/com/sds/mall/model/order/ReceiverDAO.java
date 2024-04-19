package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Member;

public interface ReceiverDAO {
	public List selectAllByMember(Member member);
	
}
