package com.sds.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//관리자모드 메인의 요청을 처리하는 하위 컨트롤러
//개발자가 @Controller 어노테이션에 아무것도 명시하지 않으면, 해당 클래스를 인스턴스화 시키면서 
//스프링이 자동으로 빈의 id를 소문자로 시작하는 클래스명을 할당한다..하지만, 개발자가 원하는 id가 있다면
//value 에 지정하면 된다
@Controller(value="adminMainController")
public class MainController {
	
	//<mvc:annotation-driven/> 등록 한 후 부터는 Spring MVC에서 컨트롤러 영역의 많은 기능을 
	//지원해줌..따라서 아래의 매핑도 줄여쓸 수 있다..
	//@RequestMapping(value="", RequestMethod.GET)
	//web.xml 에 작성한 풀 경로를 그대로 적자
	@GetMapping("/main")
	public String getMain() {
		
		return "admin/index";
	}
}





