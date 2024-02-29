class Dog{
	String name;
	int age;
	
	//개성있는 강아지를 탄생시키기 위해 생성자를 정의한다
	//개발자가 생성자를 정의하는 순간부터는, 컴파일러가 더이상 
	//디폴트 생성자를 정의하지 않는다..왜?? 컴파일러에 의한 생성자는
	//에러를 내지 않기 위한 최소한의 관여였기 때문에..
	public Dog(String name, int age){		
		this.name=name;
		this.age=age;
	}

}
