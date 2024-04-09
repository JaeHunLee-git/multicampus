package com.sds.mall.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sds.mall.domain.Color;
import com.sds.mall.domain.Product;
import com.sds.mall.domain.Psize;
import com.sds.mall.model.product.ProductService;
import com.sds.mall.model.product.TopCategoryService;

//관리자모드에서의 상품 관련 요청 처리 하위 컨트롤러
@Controller
public class ProductController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	//상품 등록폼 요청 처리 
	@GetMapping("/admin/product/registform") 
	public String getRegistForm(Model model) {
		//3단계: 모델에 일 시키기 
		List topList = topCategoryService.selectAll();
		
		//4단계: 가져갈 것이 있을 경우 결과 저장
		model.addAttribute("topList", topList);
		
		return "admin/product/regist";
	}
	
	//상품 업로드 요청 처리 (파일이 포함되어 있다)
	@PostMapping("/admin/product/regist")  // <mvc annotaion driven/> 등록 하면 이러한 속성도 지원함..
	public String regist(Product product, String[] color_name, String[] size_name) {
		System.out.println("하위 카테고리 "+product.getSubCategory().getSubcategory_idx());
		System.out.println("상품명 "+product.getProduct_name());
		System.out.println("브랜드 "+product.getBrand());
		System.out.println("가격 "+product.getPrice());
		System.out.println("상세설명 "+product.getDetail());
		
		System.out.println("색상 수는 "+color_name.length);
		System.out.println("사이즈 수는 "+size_name.length);
		
		//서비스에게 Product DTO를 전달하기 전에, 넘어온 색상 배열과, 사이즈 배열을 Product DTO안으로
		//밀어넣어서 한꺼번에 갖고 다니자!!
		List<Color> colorList = new ArrayList();
		for(String s : color_name) {
			Color color = new Color(); //empty DTO
			color.setColor_name(s);
		}
		
		List<Psize> psizeList = new ArrayList();
		for(String s : size_name) {
			Psize psize = new Psize(); //empty DTO
			psize.setSize_name(s);
		}		
		
		//배열을  List 로 변환한 후, 이 List 들을 Product 에 몰아넣자
		product.setColorList(colorList);
		product.setPsizeList(psizeList);
		
		//3단계: 서비스에게 일 시키기
		productService.regist(product); //컨트롤러는 서비스에게 추상적으로 업무를 시킬수록 장점이 있다
		
		//4단계: 저장할 것이 없고, 상품 목록을 다시 접속하도록 유도..
		return "redirect:/admin/product/list"; //404
	}
	
}










