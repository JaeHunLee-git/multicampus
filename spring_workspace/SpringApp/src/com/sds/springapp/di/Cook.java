package com.sds.springapp.di;

//요리사를 정의한다 ( 프라이팬을 들고 있는..)
public class Cook {
	//has a 관계로 프라이팬을 정의하자 
	private Pan pan; //has a 관계로 보유할 객체는 정확한 하위 자료형을 보유하지 말고, 
				//상위 자료형으로 가야, 영향을 덜 받게 된다.즉 결합도가 낮아짐
				//일반적으로 프로그래밍에서 결합도는 낮아야 좋다
	
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public Cook() {
		//has a 관계에서, 부품이 되는 객체를 상위자료형으로 선언한다 할지라도, 
		//new 연산자 뒤에 정확한 하위 자료형을 명시해야하는 원칙때문에, 아직까지는 
		//DI를 완성하지 못한 상태임..따라서 여전히 외부의 변화에 결합도 높은 상태이다..
		//pan = new FriPan();
	}
	
	public void makeFood() {
		pan.boil();
	}
}
