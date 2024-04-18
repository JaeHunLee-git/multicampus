package com.sds.mall.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.domain.Product;
import com.sds.mall.exception.CartException;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.order.CartService;

//장바구니와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private FormatManager formatManager;
	
	
	//장바구니 목록 요청 
	@GetMapping("/order/cart/list")
	public String getList(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member");
		
		//3단계: 일 시키기
		List cartList = cartService.selectByMember(member);
		
		model.addAttribute("cartList", cartList);//4단계: 결과 저장
		model.addAttribute("formatManager", formatManager);//4단계: 결과 저장
		
		return "shop/order/cart";
	}
	
	
	//장바구니 수정 요청 처리 
	@PostMapping("/order/cart/update")
	public String update(int[] product_idx , int[] ea, HttpSession session) {
		System.out.println("ea 배열의 길이는 "+ea.length);
		
		Member member = (Member)session.getAttribute("member");
		
		List cartList = new ArrayList();
		
		for(int i=0; i<product_idx.length;i++) {
			Cart cart = new Cart(); 
			//갯수, 누가, 어떤제품을..
			cart.setEa(ea[i]);//몇개를
			cart.setMember(member); //누가
			
			Product product = new Product();
			product.setProduct_idx(product_idx[i]);//어떤 제품을?
			cart.setProduct(product);
			
			cartList.add(cart);
		}
		
		//3단계: 서비스에 일 시키기 
		cartService.updateGroup(cartList);
		
		return "redirect:/order/cart/list";
	}
	
	//장바구니 1건 삭제
	@GetMapping("/order/cart/delete")
	public String delCart(Cart cart) {
		System.out.println("전달된 cart_idx is "+cart.getCart_idx());
		cartService.delete(cart);
		
		return "redirect:/order/cart/list";
	}
	
	
	
	//장바구니 관련 에러 처리
	@ExceptionHandler(CartException.class)
	public ModelAndView handle(CartException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
}











