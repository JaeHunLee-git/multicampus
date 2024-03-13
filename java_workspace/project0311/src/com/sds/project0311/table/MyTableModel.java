package com.sds.project0311.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/*
 * JTable(보여지는 View)  과 모델영역 (순수 데이터와 그를 처리하는 로직(Model)) 을 
 * 분리시켜 주는 역할을 수행하는 즉 Controller의 역할을 수행하는 중재자인 
 * AbstractTableModel을 이용해보자
 */

public class MyTableModel extends AbstractTableModel{
	/*
	데이터가 되는 이차원 배열
	자바에서 배열은 길이가 고정되므로, 데이터가 추후 지속적인 변경사항이 발생한다면 배열보다는 보다 유연한 컬렉션 
	프레임웍을 이용해본다
	- 컬렉션 프레임웍? java에서 객체만을 모아서 처리할때 유용한 기능을 지원하는 패키지 
	이 패키지 안에는 여러가지 객체들이 지원되는데, 유형은 3가지로 나뉜다 
	1) List 형 - 순서있는 집합을 표현한 객체, List 자체는 인터페이스이므로, 이를 상속받는 하위 자식을 이용함 
	               배열과 거의 같지만, 길이가 유동적, 오직 객체만을 담을 수 있슴 
	2) Set 형 - 순서없는 집합을 표현한 객체, Set 자체는 인터페이스이므로, 이를 상속받는 하위 자식을 이용함 
	3) Map 형  - Key-Value의 쌍으로 집합을 표현한 객체 , Map 자체는 인터페이스이므로, 이를 상속받는 하위 자식을 이용함
	*/
	
	//배열을 대체할 보다 유연한 컬렉션 프레임웍의 List의 자식 중 하나인 ArrayList를 사용해본다 
	ArrayList list=new ArrayList();
	String[] column= {"아이디","연락처","성별"};
	
	public MyTableModel() {
		//데이터 초기화 
		//list에 한 사람의 정보를 넣어보자
		String[] member= {"SMITH","018","남"};
		list.add(member); //리스트에 일차원 배열을 추가
	}
	
	public int getRowCount() {
		return list.size(); //아파트 층수
	}

	public int getColumnCount() {
		return column.length;
	}
	
	//컬럼의 제목을 반환하는 메서드 
	//getColumnCount()  메서드에서 반환된 값 만큼 0부터 1씩 증가하면서 여러번 호출
	public String getColumnName(int col) {
		System.out.println(col+"번째 컬럼이름이 궁금해");
		return column[col];
	}
	
	//재정의된 모든 메서드는 결국  JTable을 위한 것이다. JTable이 데이터를 보여주기 위한 목적으로 호출하는 메서드들이다 
	//따라서 아래의 getValueAt() 메서드는 getRowCount() x getColumnCount() 반환 값 만큼 호출한다 
	//2층 3호수 라면 총 6번 호출 
	// 0,0    0,1    0,2    -- 1층 
	// 1,0    1,1    1,2    -- 1층 
	// 2,0    2,1    2,2    -- 1층 
	
	public Object getValueAt(int row, int col) {
		Object obj = list.get(row);
		String[] member = (String[])obj; //Object형태로 들어가 있던 일차원배열을 다시 형변환해서 복원
		return member[col]; //이차원 배열을 이루고 있는 각 칸을 JTable이 이메서드의 반환값을 이용하여 왕창 채운다
	}
}









