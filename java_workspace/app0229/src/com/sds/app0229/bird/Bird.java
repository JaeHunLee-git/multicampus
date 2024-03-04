package com.sds.app0229.bird;

//모든 새의 어버이인 그냥 새를 정의해본다
public class Bird{
	public String name="난 그냥 새";
	public int age=23;
	/*
	public Bird(){
		super();
	}
	*/
	public void eat(){
		System.out.println("먹이를 먹어요");
	}

	public void fly(){
		System.out.println("부모 새가 날아요");
	}
}
