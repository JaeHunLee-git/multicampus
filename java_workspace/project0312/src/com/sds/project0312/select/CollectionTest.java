package com.sds.project0312.select;

import java.util.ArrayList;

import javax.swing.JButton;

/*
 * 컬렉션 프레임웍에는 개발자가 특별한 제한을 가하지 않으면 객체들이 섞여 들어가는 것을 허용해준다
 * 만일 개발자가 이 부분이 마음에 들지 않는다면, 사용하고자 하는 컬렉션 프레임웍 객체의 자료형을 
 * 특정 자료형으로 제한할 수 있는데, 이러한 자료형을 제한하는 기법을 Generic Type 이라 한다..  
 * */
public class CollectionTest {

	public static void main(String[] args) {
		/*
		 * Generic 사용 시 장점
		 * 1) 자료형을 섞이지 않게 안전 장치 
		 * 2) 모든 컬렉션 프레임웍은 넣거나 뺄때 Object 형으로 사용을 해야 하지만, 제너릭을 이용하면 
		 *     Object 형이 아닌 제너릭으로 지정한 정확한 자료형으로 넣거나 뺄수 있어 추후 형변환 
		 *     과정이 불필요 (편함)
		 * */
		ArrayList<String> list = new ArrayList<String>();
		
		//컴파일 타임 즉 문법 검사 시점부터 제한을 가해준다..안전장치..
		list.add("banana");
		
		String str= list.get(0);
		//String str = (String)obj;
		str.charAt(0);
		
		

	}

}
