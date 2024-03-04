package com.sds.project0304;

/*
 static : 변수, 메서드등을 클래스 원본에 고정시키는 수식자
 abstract : 추상클래스, 추상메서드 선언시 사용
 final : js const 동일 의미  final로 선언된 변수는 변경이 불가함   
 			class 붙이면 더이상 상속을 허락하지 않겠다..세대가 여기 끝남..
 */
public final class ModifierTest {
		// 어느 누구도 재정의 금지!!(오버라이드 하지마라!)
		public final void fly() {
			
		}
		public static void main(String[] args) {
			
			final int x=5; // 이 시점부터는 변경이 불가함, 이 자체가 자바의 상수로 보기
			//에는 부족함..
			
			//x=8;
		}
}
