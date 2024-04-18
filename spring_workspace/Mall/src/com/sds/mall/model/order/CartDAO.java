package com.sds.mall.model.order;

import java.util.List;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;

public interface CartDAO {
	public Cart selectDuplicate(Cart cart); //중복된 상품 있는지 여부 조회
	public void insert(Cart cart); //장바구니 등록 
	public List selectByMember(Member member);//장바구니 목록
	public Cart select(int cart_idx);
	public void update(Cart cart); //수정 
	public void delete(Cart cart); //삭제
}
