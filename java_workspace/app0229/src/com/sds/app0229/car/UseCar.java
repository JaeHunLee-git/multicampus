package com.sds.app0229.use;

import com.sds.app0229.car.Car;

class UseCar{
	public static void main(String[] args){
		
		Car car = new Car();//차 한대 올리기 
		
		//자동차의 핸들의 타입을 출력 
		System.out.println(car.handle.type);

		//자동차 문짝의 색상 
		System.out.println(car.door[0].color);

		//자동차 바퀴의 브랜드 출력 
		System.out.println(car.wheel[0].brand);

	}
}
