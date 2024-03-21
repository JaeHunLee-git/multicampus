package site0319;

public class StringTest {

	public static void main(String[] args) {
		
		String s1="korea"; //스트링 객체 생성, 묵시적 생성법(implicit 생성법)
		String m1="korea";
		System.out.println(s1 == m1); //내용이 같아서가 아니라, Constant Pool  에 의해
		//중복된 korea  문자열 상수는 생성되지 않기 때문에 기존의  korea 문자열 상수를 s1과 m1이 
		//가리키게 되므로, 결국 두 레퍼런스 변수의 주소값이 같아서 true가 일 뿐이다..
		
		//따라서 자바에서 객체간 내용을 비교하고 싶다면 무엇을 이용해야 하는가? 
		//자바의 최상위 객체인 Object 객체 보유한 equals() 메서드를 이용하면 된다.
		System.out.println(s1.equals(m1));
		
		
		//String 은 자바에서 객체이기 때문에 또한 일반클래스로 지원되기 때문에 new 연산자로 생성가능
		String s2 = new String("korea"); // explicit 생성법
		String m2 = new String("korea");
		
		System.out.println(s1 == s2);
			
		//스트링 2번째 특징 
		//불변(immutable)의 특징 즉 수정불가한 상수이다
		String s="banana";
		for(int i=1;i<=100;i++) {
			s = s+i;
			System.out.println(s);
		}
		//위와 같이  스트링 객체는 변경이 불가한 상수이므로, 반복문에서 + 연결자로 문자열을 추가할경우
		//수정되는 것이 아니라, 그냥 새로운 문자객체를 생성해버린다..
		
		//해결책) 수정가능한 문자열 객체처리를 이용하면 됨..StringBuilder, StringBuffer 사용 
		StringBuilder sb = new StringBuilder(); //String 클래스 아님 
		
		String test="";
		for(int i=1;i<10;i++) {
			sb.append("앙");
		}
		System.out.println(sb.toString());
	}

}
