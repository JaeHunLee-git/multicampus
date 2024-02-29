/*비행기를 정의한다*/
class Plane{
	String line="747";
	String brand="대한항공";
	int seat=120;
	int height=0;

	//자바에서는 코드의 내용에 큰 차이가 없다면, 굳이 메서드명을 지어내려고 고생할 필요가 없다 
	//즉 메서드명은 직관성이 부여되기 때문에, 기존의 힘들게 명명해놓은 메서드명을 피해가기 위해 
	//로직에 큰 차이가 없음에도 불구하고, 새로운 메서드명을 지어낼 필요가 없다..
	//즉, 자바에서는 비슷한 로직일 경우 메서드명을 유지할 수있는 메서드 정의 기법을 지원해준다 
	//이러한 기법을 가리켜 메서드 오버로딩(OverLoading)  
	//메서드명은 동일하되, 전혀 구분이 불가하면 안되므로, 매개변수의 자료형과 갯수로 구분하는 기법

	//비행기가 난다 
	public void fly(){
		height=300;
	}

	//비행기가 조금 높게 난다 
	public void fly(int height){
		this.height=height;
	}

	//비행기가 조금더 높게 난다 
	public void fly(int height, String line){
		height=500;
	}
	
	public static void main(String[] args){
		Plane p = new Plane(); //비행기 인스턴스 만들기
		p.fly();
		p.fly(500);
		p.fly(700, "north-web 900라인");
	}
}
