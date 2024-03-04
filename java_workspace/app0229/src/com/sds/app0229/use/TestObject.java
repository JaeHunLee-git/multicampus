/*
자바에서는 개발자가 아무런 상속관계를 명시하지 않아도, 
기본적으로 , 무조건 상속되는 객체가 있는데, 이 최상위 객체를 Object 클래스라 하며
개발자가 명시하지 않는다

Object 를 import 하지 않아도, 에러가 발생하지 않는 이유는 
java.lang 패키지는 언어의 기본적인 객체들이 들어있기 때문에 자동 import 되어있슴
String, Object, Integer...

*/
package com.sds.app0229.use;

class TestObject{ 
	/*TestObject is a  Object */
	public static void main(String[] args){
			/*
			아래의 3 레퍼런스 변수 모두~~~~ 자식인 TestObject의 인스턴스 주소를 가리킨다. 
			그렇다면 상속관계에서 부모의 인스턴스 주소값을 가져올 방법은 없나? 없다!!
			왜? 인스턴스가 올라간적이 없기 때문에, 즉 자식의 인스턴스내에 부모의 재산이 들어간 상태
			*/
			TestObject to = new TestObject(); //(A)자식의 인스턴스 모든 영역(서랍장 포함)
			Object obj = new TestObject(); //(B)자식의 인스턴스내의 부모서랍장		
														//예외) 만일 자식이 오버라이드한 메서드가있다면 
														//아무리 obj.부모메서드() --> 자동으로 자식메서드 호출이됨
														//왜 자식메서드를 우선할까? 업그레이드했기 때문에
			TestObject re =(TestObject)obj; //(C)자식의 인스턴스 모든 영역(서랍장 포함)

			
	}
}
