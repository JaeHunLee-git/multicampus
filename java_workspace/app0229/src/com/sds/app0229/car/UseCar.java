package com.sds.app0229.use;

import com.sds.app0229.car.Car;

class UseCar{
	public static void main(String[] args){
		
		Car car = new Car();//�� �Ѵ� �ø��� 
		
		//�ڵ����� �ڵ��� Ÿ���� ��� 
		System.out.println(car.handle.type);

		//�ڵ��� ��¦�� ���� 
		System.out.println(car.door[0].color);

		//�ڵ��� ������ �귣�� ��� 
		System.out.println(car.wheel[0].brand);

	}
}
