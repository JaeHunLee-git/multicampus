package com.sds.mall.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.mall.domain.Cart;
import com.sds.mall.domain.Member;
import com.sds.mall.exception.CartException;
import com.sds.mall.model.order.CartService;

//비동기 요청에 대한 처리만을 담당하는 컨트롤러 
//따라서 이 컨트롤러에서의 모든 응답은 jsp가 아닌 순수 데이터 (json, xml , text 등등..)
//RestController와 일반 Controller의 차이점 RestController의 모든 메서드에는 @ResponseBody가 디폴트로 적용
@RestController
public class RestCartController {
	
	@Autowired
	private CartService cartService;
	
	//장바구니 등록 요청 
	@PostMapping("/order/cart/regist")
	//순수 데이터를 반환하는것이며, jsp로 resolve  하지 않아야 한다
	public ResponseEntity regist(Cart cart, HttpSession session) {
		System.out.println(cart.getProduct().getProduct_idx());//어떤 상품을 
		System.out.println(cart.getEa());//몇개나
		
		//MemberController 에서 member 라는 이름으로 넣었으므로, 꺼내자..
		Member member = (Member)session.getAttribute("member"); 
		cart.setMember(member);
		System.out.println(cart.getMember().getMember_idx());//누가 
		
		//3단계: 일 시키기 
		cartService.regist(cart);
		
		//Http프로토콜에 맞게 응답 정보를 구성해주는 객체 (머리, 몸체, 상태코드 등을 구성할 수 있게 해준다)
		//따라서 그냥 성의없게 ok 문자열 등을 단순히 보내는 것보다 훨씬 체계적이다..따라서 클라이언트가 서버의 상황을 정확히 알 수 있다
		ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();
		
		return entity;
	}

	//장바구니 관련 에러처리 
	@ExceptionHandler(CartException.class)
	public ResponseEntity handle() {
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}	

}
