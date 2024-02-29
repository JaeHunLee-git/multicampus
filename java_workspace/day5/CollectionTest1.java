//현재 클래스와는 다른 위치에 있는 외부 클래스를 사용하려면
//해당 외부 클래스의 위치를 등록해야 한다 
import java.util.ArrayList;  //java패키지 밑에 util패키지 밑에 위치

class CollectionTest1{
	/*
	[ 자바 배열의 특징 ]
	1.한 종류의 데이터만 담을 수 있다.  ex) int[] arr=new int[5];
	2.크기가 고정되어 있다, 따라서 동적 변경은 불가능
	3.모든 배열은 객체로 본다. 따라서 배열변수명을 출력하면 주소값이 출력
		ex)  char[]  arr2 = new char[2];
				System.out.println(arr2); //배열의 힙에서의 주소값 @23214
	[수업주제]		
	배열의 특징 중, 크기가 고정되어 있다는 특징은 동적 변경을 목적으로 프로그램에서
	는 한계점..예) 총알 발사할때마다 배열에 담는 것은 자바에서 불가능..
						Bullet[]  bulletArray = new Bullet[크기고정];
	해결책) 자바의 라이브러리 중 collection framework 이라 불리는 라이브러리를
			사용해보자 (class 들을 모아놓은 집합)
	■ 컬렉션 프레임웍이란?
	   - 자바에서 객체를 모아서 처리할때 유용한 기능을 지원하는 
	      자바의 라이브러리(패키지로 지원)  java.util 패키지(디렉토리)에

	■ 컬렉션을 이루는 객체들의 유형 
		컬렉션 프레임웍의 관심의 대상은 오직 객체이다. 따라서 자바의 기본자료형은
		컬렉션 프레임웍 라이브러리의 대상이 아니다!!
		오직 객체만을 대상으로 한다!!
	
		1) 순서가 있는 유형 (List 형)
		2) 순서가 없는 유형 (Set 형)
		3) key-value 로 이루어진 유형 (Map형)
	*/
	public static void main(String[] args) {
		//컬렉션 프레임웍의 순서있는 객체들의 집합을 제어할 수 있는 객체인 
		//List의 자식인 ArrayList 를 사용해본다 
		//List는 우리가 사용해왔던 배열과 거의 같다. 단 차이점은 다음과 같다
		//1) 크기가 동적으로 변경될 수 있다(고무줄 처럼 js 의 배열과 동일한 효과) 
		//2) 오직 객체만을 담을 수 있다
		//3) 객체들을 섞어서 담을 수도 있다
		ArrayList list = new ArrayList(); //인스턴스 생성 
		
		list.add("apple");  //js 의 push()와 동일 
		list.add("banana");
		list.add("grape");

		System.out.println("리스트의 사이즈는 "+list.size());

	}
}
