/*
별도의 제한 조건은 없다!!
현실에 존재하는 자동차를 최대한 현실을 반영하여 클래스를 설계해보세요
Has a 관계란? 객체가 다른 객체를 부품으로 갖는 관계 
				자바 언어로  has a 관계를 표현할때는, 멤버변수에 객체자료형을 두면 됨
*/
package com.sds.app0229.car;
public class Car {
	public Wheel[] wheel; //객체자료형도 자료형이므로, 멤버변수로 선언이 당연히 가능
	public Handle handle;
	public Door[] door;
	int price; // has a 관계라 하지 않는다. 왜? is a, has a 모두 객체간 관계를 말함

	//has a 관계로 즉 객체를 보유한 클래스의 인스턴스를 올릴때는 초기화 작업이 일반 자료형에 비해
	//처리 해야할 작업이 많다..따라서 적극적으로 생성자를 활용하자 
	public Car(){
		//바퀴 제조
		wheel =new Wheel[4]; //배열 생성, 무조건 그 크기를 명시해야 한다
		wheel[0] = new Wheel();//바퀴1개 생성 
		wheel[1] = new Wheel();//바퀴1개 생성 
		wheel[2] = new Wheel();//바퀴1개 생성 
		wheel[3] = new Wheel();//바퀴1개 생성 
	
		handle = new Handle(); //핸들 생성 

		door = new Door[2]; //2개짜리 스포츠카용 문짝 생성할 공간마련
		door[0] = new Door(); //문짝 1개 생성
		door[1] = new Door(); //문짝 1개 생성
	}
}
