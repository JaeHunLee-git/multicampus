package com.sds.seshop.product;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// 상품 목록페이지의 JTable 이 바라보고 있는 컨트롤러 객체 
public class ProductTableModel extends AbstractTableModel{
	ArrayList<String[]> list=new ArrayList<String[]>();//size 0
	String[] column = {"product_idx","product_name","price","brand","filename","subcategory_idx"};
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return column.length;
	}

	public String getColumnName(int col) {
		return column[col];
	}
	
	public Object getValueAt(int row, int col) {
		String[] record = list.get(row);//행을 가져온다 
		
		return record[col];
	}
	
}
