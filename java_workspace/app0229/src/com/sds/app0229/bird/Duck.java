package com.sds.app0229.bird;

//���� �ڽ��� ������ �����Ѵ�
public class Duck extends Bird{
						/*  is a */
	public String color="white";
	/*
	public Duck(){
		super();
	}
	*/
	public void quack(){
		System.out.println("������ ��� �ŷ���");
	}
	public void fly(){
		System.out.println("������ �۴� �ŷ���");
	}

	public static void main(String[] args){
		Bird bird = new Bird();
		bird.fly();

		Bird d = new Duck();
		d.fly();

		
	}
}
