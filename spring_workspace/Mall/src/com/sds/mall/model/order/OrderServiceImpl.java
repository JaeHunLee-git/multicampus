package com.sds.mall.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderDetail;
import com.sds.mall.domain.OrderSummary;
import com.sds.mall.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ReceiverDAO receiverDAO;
	
	@Autowired
	private PaymethodDAO paymethodDAO;
	
	//주문 DAO
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public List selectReceiver(Member member) {
		return receiverDAO.selectAllByMember(member);
	}

	@Override
	public List selectPaymethod() {
		return paymethodDAO.selectAll();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void order(OrderSummary orderSummary) throws OrderException{
		//주문 요약 넣기 
		orderSummaryDAO.insert(orderSummary); //mybatis의 select key 에의해 ordersummary_idx는 채워져 잇게됨
		
		//현재 회원의 장바구니 목록 가져오기
		List<Cart> cartList = cartDAO.selectByMember(orderSummary.getMember()); //현재 회원의 장바구니 목록
		
		//장바구니에 담긴 수만큼 반복하여 주문 상세 등록 
		for(Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail(); //empty
			orderDetail.setOrderSummary(orderSummary);
			orderDetail.setProduct(cart.getProduct());//장바구니에 잇는 Product 를대입 
			orderDetail.setEa(cart.getEa()); //장바구니에 잇는 갯수를 대입
			orderDetail.setProduct_name(cart.getProduct().getProduct_name());
			orderDetail.setPrice(cart.getProduct().getPrice());
			
			orderDetailDAO.insert(orderDetail);
			
			//회원이 사용하던 장바구니 비우기	
			cartDAO.delete(cart);
		}
		
	}
	
}














