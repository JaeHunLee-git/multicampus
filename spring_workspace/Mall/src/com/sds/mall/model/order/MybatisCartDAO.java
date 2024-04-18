package com.sds.mall.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.exception.CartException;

@Repository
public class MybatisCartDAO implements CartDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Cart selectDuplicate(Cart cart) {
		return sqlSessionTemplate.selectOne("Cart.selectDuplicate", cart);
	}
	
	@Override
	public void insert(Cart cart) throws CartException{
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		
		if(result <1) {
			throw new CartException("장바구니 담기 실패");
		}
		
	}

	@Override
	public List selectByMember(Member member) {
		return sqlSessionTemplate.selectList("Cart.selectByMember", member);
	}

	@Override
	public Cart select(int cart_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cart cart) throws CartException{
		int result = sqlSessionTemplate.update("Cart.update", cart);
		
		if(result <1) {
			throw new CartException("장바구니 변경 실패");
		}
		
	}

	@Override
	public void delete(Cart cart) throws CartException{
		int result = sqlSessionTemplate.delete("Cart.delete", cart);
		
		if(result < 1) {
			throw new CartException("장바구니 1건 삭제 실패");
		}
		
	}
	
}






