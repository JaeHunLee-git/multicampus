package com.sds.mall.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_idx;
	private String product_name;
	private String brand;
	private int price;
	private String filename;
	private String detail;
	
	private MultipartFile photo; //html 에서의 <input type="file"> 컴포넌트와 이름이 일치할경우 이 객체로 바이너리
												//를 포함한 정보들이 들어온다. 즉 업로드 정보가 들어온다..
	//하나의 상품은 여러개의 Color 를 자식으로 보유할 수 있다..
	List<Color> colorList; //product (1): color(多)
	List<Psize> psizeList; //product (1): psize(多)
	
	//부모를 참조 
	private SubCategory subCategory;
}







