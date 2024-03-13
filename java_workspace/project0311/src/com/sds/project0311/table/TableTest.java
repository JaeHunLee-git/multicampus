package com.sds.project0311.table;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * 자바에서 데이터를 표구조로 보여주는 컴포넌트인 JTable 학습
 * */
public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll; 
	
	//3층의 4호수짜리 데이터
	String[][] data = {
			{"사과","딸기","바나나","포도"}, 
			{"Audi","BMW","Benz","Kia"}, 
			{"Java","JSP","Spark","MongoDB"} 
	};
	
	String[] cols= {"컬럼1","컬럼2","컬럼3","컬럼4"};
	
	public TableTest() {
		table=  new JTable(data, cols); //5층 4호수
		scroll = new JScrollPane(table); //테이블에 스크롤 적용 
		
		//스타일 
		//table.setPreferredSize(new Dimension(500, 400));
		
		//setLayout(new FlowLayout());
		add(scroll);
		
		setVisible(true);
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public static void main(String[] args) {
		new TableTest();
	}

}
