/*
현실의 자동차를 정의하기 위한 클래스가 아니라, Car.java에 자체적으로
실행부가 들어있지 않기 때문에, 현재 이 클래스에서 실행을 담당하겠다..
*/
class UseCar {
	// 현재 클래스에서 다른 클래스인 Car를 사용하려면, Car클래스의 컴파일 결과물인
	//.class 가 존재해야 한다
	public static void main(String[] args) {
		Car c = new Car(); //거푸집으로부터 인스턴스 한개를 생성하여, 
									//c라는 변수로 보관해놓음
		int r = c.getRandom();

		System.out.println(r);
	}
}
