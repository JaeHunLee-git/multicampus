package com.sds.project0312.select;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/*
 * 유지보수성을 높이기 위한 개발 방법 이론인  MVC 패턴에 의하면, 
 * 디자인 영역과, 데이터 및 데이터를 제어하는 Model영역은 분리시켜 개발해야, 
 * 추후 유지보수성이 높아진다...유지보수성이 높아지면 비용이 감소하고, 비용이 감소하면 기업이 돈을 번다
 * 
 *  JTable (View)   -- Controller (TableModel)   -- 데이터,ArrayList() 모델영역
 */
public class MyTableModel extends AbstractTableModel{
	
	ArrayList<String[]> list = new ArrayList<String[]>(); //size 0인 비어있는 리스트생성
	
	String[] column = {"car_idx","name","price"};
	
	//TableModel 객체가 제공해주는 모든 메서드는 결국 View 단인 JTable을 위한 메서드들이다.. 
	
	public int getRowCount() { //몇행인지...반환
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public int getColumnCount() { //몇열인지...반환
		// TODO Auto-generated method stub
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) { //컬럼명 반환
		return column[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) { //각 행,열에 무엇을 넣어야 하는지 반환
		String[] data = list.get(row);
		return data[col];
	}
	
}





