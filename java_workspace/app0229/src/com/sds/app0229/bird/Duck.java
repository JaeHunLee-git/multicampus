package com.sds.app0229.bird;

//새의 자식인 오리를 정의한다
public class Duck extends Bird{
						/*  is a */
	public String color="white";
	/*
	public Duck(){
		super();
	}
	*/
	public void quack(){
		System.out.println("오리가 꿱꿱 거려요");
	}
	public void fly(){
		System.out.println("오리가 퍼덕 거려요");
	}

	public static void main(String[] args){
		Bird bird = new Bird();
		bird.fly();

		Bird d = new Duck();
		d.fly();

		
	}
}
