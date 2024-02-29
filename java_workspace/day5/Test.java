class Test{
	public static void main(String[] args){
		/*자바스크립트 처럼, 자바도 배열의 길이를 동적으로 늘리는 방법은
			없을까? 고무줄 처럼...즉 크기를 유동적으로 처리할 수 없나?
			답) 자바에서 배열은 크기 조정이 불가
			해결책) 컬렉션 프레임웍 라이브러리에서 js  보다 훨씬 다양한
			           자료 구조를 지원
			자바에서 컬렉션 프레임웍이나 기타 라이브러리를 이용하려면? 
			api document를 보는 방법부터 알아야 함 
		*/			

		//현재 우리 디렉토리에는 String.class  파일이 존재하지 않더라도, 
		//java.lang 이라는 디렉토리에 존재하는 String.class는 
		//자동으로 위치가 현재 개발중인 클래스로 인식되어져 있다..
		//결론: String.class 를 가지고 있는 상황임
		String str="korea , fighting";
		char c = str.charAt(6);
		System.out.println(c);

		String[] result = str.split(","); //쉼표를 기준으로 문자열 배열을 반환받음
		System.out.println("분리시킨 결과 배열의 길이는 "+result.length);
		System.out.println("0번째 값은 "+result[0].trim());
		System.out.println("1번째 값은 "+result[1].trim());

		
		//같은 디렉토리에  Dog 형을 가지고 있으므로, 에러 안남
		Dog d = new Dog("아롱이",6);

		//Cat c = new Cat(); 현재 디렉토리에 Cat 이 존재하지 않음

	}
}
