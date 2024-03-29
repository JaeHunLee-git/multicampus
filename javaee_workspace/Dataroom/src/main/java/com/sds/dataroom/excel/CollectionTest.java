package com.sds.dataroom.excel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 ■ Collection Framework 이란?  
 * - 객체를 모아서 처리할때 유용한 기능을 지원하는 자바의 api
 * - java.util  패키지에서 지원  
 
 ■ 종류 - 3가지 모두 오직 객체자료형만을 다룬다 즉 기본자료형은 다루지 않음
  유형1 - List 형) 
  
  유형2 - Set 형)
  
  유형3 - Map형) 
 * */
public class CollectionTest {

	public static void main(String[] args) {
		//순서없이 모여진 모습을 표현한 api 가 바로  Set 이다 
		//ex) 과자 봉투에 들어있는 과자..
		
		Set set = new HashSet();
		
		set.add("사과");
		set.add("딸기");
		set.add("포도");
		set.add("바나나");
		set.add("레몬");
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) { //현재 내 위치에서 다음 요소에 객체가 존재한다면 true,  아니면 false
			String fruit = it.next();
			System.out.println(fruit);
		}
	}

}










