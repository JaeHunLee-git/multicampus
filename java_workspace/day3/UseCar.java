/*현실의 사물을 표현하는 게 아니라, 단지 main메서드를 보유하기 위한
	즉 실행을 위한 클래스임
*/
class UseCar{
	public static void main(String[] args) {
		Car c = new Car(); //자동차 인스턴스를 생성함 
		System.out.println(c.brand);
	}
}
