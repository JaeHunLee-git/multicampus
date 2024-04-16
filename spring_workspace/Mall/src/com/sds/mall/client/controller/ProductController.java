package com.sds.mall.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sds.mall.domain.Product;
import com.sds.mall.model.common.FormatManager;
import com.sds.mall.model.product.ProductService;
import com.sds.mall.model.product.TopCategoryService;

//쇼핑몰에서 상품과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class ProductController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	//model 패키지에 두었으므로, root.xml의 <component-scan> 에 의해 어플리케이션 가동과 동시에 이미 메모리에 올라와 있슴
	@Autowired
	private FormatManager formatManager;
	
	//상품 목록 요청 
	@GetMapping("/product/list")
	public ModelAndView getList( 
			@RequestParam(value="topcategory_idx", defaultValue="0") int topcategory_idx, 
			@RequestParam(value="subcategory_idx", defaultValue="0") int subcategory_idx
			) {
		
		System.out.println("넘어온 topcategory_idx is "+topcategory_idx);
		
		//product/list.jsp에서도 include가 들어있으므로
		//, topList 라는 키값을 갖는 List를 심어놓지 않으면  nullpointerException 난다
		//List topList = topCategoryService.selectAll(); //3단계: 카테고리 가져오기 
		
		//3단계: 상품 목록 가져오기 
		//넘겨받은 topcategory_idx가 0이라면, 아무런 상위 카테고리를 선택하지 않은 경우이므로, 
		//모든 상품을 보여주자 
		List productList = null;
		
		if(topcategory_idx>0) { //파라미터가 넘어온 경우 0이 아니다!!, 상위카테고리를 희망하면..
			productList = productService.selectAllByTopIdx(topcategory_idx);
		}else if(subcategory_idx >0){//하위 카테고리를 희망하면..
			productList = productService.selectAllBySubIdx(subcategory_idx);
		}else{ //아무것도 넘어오지 않은 경우, 모든 상품 보기를 희망하면..1
			productList = productService.selectAll(); //모든 상품 가져오기 
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("productList", productList);//4단계: 결과저장
		mav.addObject("formatManager", formatManager);//4단계: 결과저장
		
		mav.setViewName("shop/product/list");
		
		return mav;
	} 
	
	//상품 1건 상세보기 
	@GetMapping("/product/detail")
	public ModelAndView getDetail(Model model, int product_idx) {

		Product product = productService.select(product_idx);//3단계: 상세내용 한건 가져오기
		ModelAndView mav = new ModelAndView();
		mav.addObject("product", product); //4단계: 결과 저장 
		mav.addObject("formatManager", formatManager);//4단계: 결과저장
		
		mav.setViewName("shop/product/detail");
		
		return mav;
	}
	
}














