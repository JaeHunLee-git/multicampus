package com.sds.seshop.admin;

import java.awt.Color;

import com.sds.seshop.main.Page;
import com.sds.seshop.main.ShopMain;

//상품 목록 페이지 
public class AdminList extends Page{
	//1000 x 800 페이지 정의
	public AdminList(ShopMain shopMain) {
		super(Color.ORANGE);
		this.shopMain =shopMain;
	}
}
