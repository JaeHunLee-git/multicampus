//그냥 실행을 위한 클래스
class UseHuman {
	public static void main(String[] args){
		/*아시아인과 서양인은 실질적으로 메서드를 각각 1개씩만 보유하고 있다.
			하지만, 상속을 통해 부모의 재산도 접근할 수 있으므로, 
			많은 속성과 메서드를 보유한 결과가 되어버린다..
		*/
		Asian as = new Asian();
		as.farmRice(); //동양인 클래스가 자체적으로 보유하고 있는 자신의 메서드
		as.eat();//부모에게 물려받은 메서드 호출 
		as.think();//부모에게 물려받은 메서드 호출 


	}
}
