package com.sds.mall.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//결제와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class PaymentController {
	@GetMapping("/order/payment/payform")
	public String getForm() {
		
		return "shop/order/checkout";
	}
}
