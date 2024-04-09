package com.sds.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.mall.model.product.SubCategoryService;

@Controller
public class SubCategoryController {
	
	//DI를 위한 느슨하게 서비스 객체를 보유하자 
	@Autowired
	private SubCategoryService subCategoryService;
	
	
	//하위 카테고리 목록을 가져오되, 부모인 topcategory_idx 에 소속되는 목록만... 
	//단, 클라이언트가 원하는 응답 결과는 jsp가 아니다!!! 왜?? 비동기방식으로는 페이지 새로고침을 원하는 것이
	//아니므로, 서버측에서는 응답 정보로 페이지를 보내면 아니되며, 순수 데이터 형태(xml, json, text) 로 
	//보내줘야 클라인트가 목적에 맞게 사용할 수 있다..
	//아래의 메서드의 반환값이 InternalResourceViewResolver와 같은 jsp 파일명으로 해석이 되지 않으려면
	//현지 이 메서드의 반환값이 데이터라는 표시를 해줘야 한다..
	@ResponseBody // ViewSolver가 jsp로 조합하는 것을 방지하고  return에 명시된 문자열을 전송
	@GetMapping("/admin/subcategory/list")
	public List getSubListByTopIdx(int topcategory_idx, Model model) {
		//3단계: 모델에게 일 시키기 
		List subList = subCategoryService.selectAllByTopIdx(topcategory_idx);
		System.out.println(topcategory_idx+"에 소속된 하위 카테고리는 "+subList);
		
		//클라이언트가 비동기 요청으로, 즉 순수 데이터만을 받아가기를 원하므로, 
		//subList를 문자열화 시켜 전송해주자
		
		//스프링에서는 문자열로 응답을 보낼때, 특히 json 형식으로 보낼 일이 있다면, 자동으로 자바의 객체를 JSON문자열로 
		//convert 해주는 기능을 지원한다..
		//이 기능을 이용하려면, jackson databind 라이브러리를 설치해야 한다.
		
		return subList; //  /WEB-INF/babo.jsp
	}
}



