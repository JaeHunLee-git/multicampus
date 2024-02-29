package test;//이 클래스가 컴파일 되면 classpath에 등록한 루트를 기준으로 
					//test라는 패키지 안에 위치하게 될 것임
import flower.Rose;

class UseRose{
	public static void main(String[] args) {
		Rose rose = new Rose();
		System.out.println(rose.getName());
	}
}
