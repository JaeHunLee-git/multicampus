package com.sds.asynboard.pattern;

public class UseDog {
	
	public static void main(String[] args) {
		//Dog d1 = new Dog();
		//Dog d2 = new Dog();
		
		//강아지가 new를 못하게 방어하고 잇으므로, 
		//강아지가 제공하는 static 메서드로 강아지의 인스턴스를
		//얻어보자 
		Dog d1 = Dog.getInstance();
		Dog d2 = Dog.getInstance();
		System.out.println(d1);
		System.out.println(d2);
		
	}
}
