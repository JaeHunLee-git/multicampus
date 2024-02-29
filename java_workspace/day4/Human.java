/*
인류의 보편적 특징을 정의하는 클래스 
즉 모든 인종의 최상위 객체 
*/
class Human{
	int eyenum=2; //눈 2개 
	int legs=2;

	int age;
	
	public Human(){
	}

	public Human(int eyenum, int legs){
		this.eyenum=eyenum;
		this.legs=legs;
	}

	public void eat(){
		System.out.println("살려고 먹어요");
	}
	public void think(){
		System.out.println("지성이 있어요");
	}

}
