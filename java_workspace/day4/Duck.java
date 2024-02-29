/*
 새의 자식 중, 오리를 정의한다
*/
class Duck extends Bird{
	//보편적 특징은 부모클래스에 이미 보유중이
	//므로, 여기서는 오리만이 갖는 특성등을
	//표현하는 게 효율적이다..
	/*
	boolean hasWing=true;
	String color;
	*/
	public Duck(){
		//상속관계에서 super() 디폴트 생성자를 자동 호출
		//부모가 매개변수 있는 생성자만 가지고 있을때는
		//컴파일러에 의한 자동호출에 의지하지 말고,
		//개발자가 직접 나서야 한다. 
		super("yellow");
		System.out.println("난 오리");
	}

	public void quack(){
		System.out.println("꿱꿱");
	}

}
