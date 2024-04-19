package com.sds.mall.client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.OrderSummary;
import com.sds.mall.exception.OrderException;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.order.CartService;
import com.sds.mall.model.order.OrderService;

//결제와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class PaymentController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private FormatManager formatManager;
	
	@GetMapping("/order/payment/payform")
	public String getForm(HttpSession session, Model model) {
		
		Member member = (Member)session.getAttribute("member");
		
		//3단계: 받는 사람 정보, 결제방법 가져오기, 장바구니 
		List receiverList		= orderService.selectReceiver(member);
		List paymethodList 	= orderService.selectPaymethod();
		List cartList = cartService.selectByMember(member);//회원이 사용중인 장바구니, 결제 완료 후 삭제할 예정
		
		
		//4단계: 저장 
		model.addAttribute("receiverList", receiverList);
		model.addAttribute("paymethodList", paymethodList);
		model.addAttribute("cartList", cartList);
		model.addAttribute("formatManager", formatManager);
		
		return "shop/order/checkout";
	}
	
	//주문요청 처리, 전송되는 파라미터  - total_buy, total_pay, paymethod_idx, receiver_idx 
	@PostMapping("/order/payment/pay")
	public String regist(OrderSummary orderSummary, HttpSession session) {
		System.out.println("total_buy is "+orderSummary.getTotal_buy());
		System.out.println("total_pay is "+orderSummary.getTotal_pay());
		System.out.println("receiver_idx is "+orderSummary.getReceiver().getReceiver_idx());
		System.out.println("paymethod_idx is "+orderSummary.getPaymethod().getPaymethod_idx());
		
		//현재 로그인 한 회원의 정보를 OrderSummary에 대입 
		Member member = (Member)session.getAttribute("member");
		orderSummary.setMember(member);//dto 대입
		
		//3단계: 주문 등록 
		orderService.order(orderSummary);
		
		return "redirect:/";
	}
	
	@ExceptionHandler(OrderException.class)
	public ModelAndView handle(OrderException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
	
}














