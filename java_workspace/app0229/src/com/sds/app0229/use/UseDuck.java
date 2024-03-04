package com.sds.app0229.use;

import com.sds.app0229.bird.Bird;
import com.sds.app0229.bird.Duck;

class UseDuck {
	public static void main(String[] args) {
		//기본자료형 간 형변환 
		//자바에서는 비슷한 자료형이라면, 서로 형변환이 가능하다 
		//숫자(byte,short, int ,long, float, double), 문자(char)
		
		byte b=4;
		int x=7;
		x = b;
		

		//자바에서 객체형도 자료형이라면, 객체간 형변환도 가능할까?  yes
		//자바에서 상속관계에 있는 자료형은 서로 비슷한 자료형으로 인정된다.
		//따라서 객체간 형변환도 지원 

		//오리 한마리 인스턴스 올리기 
		Duck d = new Duck();
		//부모님이 물려준 , age를 사용해본다
		System.out.println(d.name);
		d.eat();


		Bird bird = new Duck();
		System.out.println(bird.age);

	    byte      =    int
		Duck dc = (Duck)bird;
		
	}
}
