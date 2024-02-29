/*동양인을 정의한다*/
class Asian extends Human{
	//extends 를 선언한 시점부터는, Asian 객체는 Human의 모든 요소(변수, 함수)
	//를 마치 내 것처럼 , 즉 내가 소유한 것처럼 사용이 가능...

	int age;

	public void farmRice(){
		System.out.println("쌀농사를 지어요");
	}
	
	//개발자가 일단 생성자를 정의하면 , 절대로 
	//컴파일러는 관여하지 않는다...
	//컴파일러에 의한 디폴트 생성자는 에러를 일의키
	//지 않기 위한 최소한의 관여였기 때문...
	public Asian(){
		super(2, 2); //생략됨 
		//이유? 자식인 Asian이 초기화되기 전에 
		//상식적으로 부모인 Human이 먼저 초기화
		//되어야 하기 때문에 컴파일러에 의해 super()
		//무조건적으로 호출된다..
	}
	
}
