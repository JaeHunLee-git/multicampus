/*
같은 디렉토리에 있는 Account 클래스를 맘대로 주물러본다
= 현재는 보안상 매우 취약
*/
class UseAccount{
	public static void main(String[] args) {
		//인스턴스 올리기 
		Account acc = new Account();
		acc.master="나";
		//acc.balance=500000000; //변수 직접 접근 금지에 의한 컴파일 에러
		
		//결과 출력 
		System.out.println(acc.master);

		//변수를 직접접근을 막아놓았으므로, 간접적인 방법으로 사용해야 한다.. 
		//이때 데이터를 읽으려면 getter 를, 데이터를 쓰려면 setter 를 이용하자 
		acc.setBalance(700000000); //setter를 이용한 변수값 변경 (조작)
		System.out.println(acc.getBalance()); //getter를 통한 변수 읽기
	}
}
