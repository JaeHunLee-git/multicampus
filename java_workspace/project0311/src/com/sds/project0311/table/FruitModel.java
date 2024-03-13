package com.sds.project0311.table;

import javax.swing.table.AbstractTableModel;

/*
 * JTable(보여지는 View)  과 모델영역 (순수 데이터와 그를 처리하는 로직(Model)) 을 
 * 분리시켜 주는 역할을 수행하는 즉 Controller의 역할을 수행하는 중재자인 
 * AbstractTableModel을 이용해보자
 */

public class FruitModel extends AbstractTableModel{
	//데이터가 되는 이차원 배열
	String[][] data={
		{"배","12000","베트남"},
		{"딸기","13000","태국"},
		{"사과","5800","한국"}
	};
	String[] column= {"과일명","가격","생산지"};
	
	public int getRowCount() {
		return data.length; //아파트 층수
	}

	public int getColumnCount() {
		return column.length;
	}
	//재정의된 모든 메서드는 결국  JTable을 위한 것이다. JTable이 데이터를 보여주기 위한 목적으로 호출하는 메서드들이다 
	//따라서 아래의 getValueAt() 메서드는 getRowCount() x getColumnCount() 반환 값 만큼 호출한다 
	//2층 3호수 라면 총 6번 호출 
	// 0,0    0,1    0,2    -- 1층 
	// 1,0    1,1    1,2    -- 1층 
	// 2,0    2,1    2,2    -- 1층 
	
	public Object getValueAt(int row, int col) {
		return data[row][col]; //이차원 배열을 이루고 있는 각 칸을 JTable이 이메서드의 반환값을 이용하여 왕창 채운다
	}
}









