/*
개발자가 클래스를 정의한다는 것은 기존
에 없었던 새로운 자료형을 창조해내는
것과 같다. 따라서 아래의 Cat클래스는
Cat 형 자료형이다
*/
class Cat{
	String name;
	int age;
	String color;	
	
	/*
	시스템 즉 컴파일러에 의해 정의된
	생성자를 가리켜 디폴트 생성자라 함
	디폴트 생성자의 목적은 최소한의 관여
	를 통해 에러만 안나도록 존재..
	public Cat(){
	}
	*/

	//개발자가 생성자를 명시하면, 더이상
	//컴파일러에 의한 즉 에러방지용이었떤
	//디폴트 생성자는 존재하지 않는다
	//생성자에 매개변수를 두어서, 다양한 인스턴스를 생성해본다..
	//생성자도 메서드이므로, 매개변수를 사용할 수 있다..
	//생성자 메서드는 반환형을 두어서는 안된다
	//void 등의 반환형을 두는 순간, 일반메서드가 되어버려서 
	//new 연산자 뒤에서 호출되는 생성자로서 역할을 수행X(호출X)
	/*
	public Cat(String name, int age, String color){
		
		//this란 인스턴스가 자기 자신을 가리키기 위한 레퍼런스 변수

		this.name=name;
		this.age=age;
		this.color=color;
		System.out.println("내가 정의한 생성자 호출");
	}
	*/

	//일반메서드 들...
	public void setName(String name){
		this.name=name;
	}
	public void setAge(int age){
		this.age=age;
	}
	public void setColor(String color){
		this.color=color;
	}
}
