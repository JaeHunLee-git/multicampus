package com.sds.mall.exception;

public class ExceptionTest {

	//try~catch 로 에러를 잡기싫다면, 이 메서드내에서 발생한 예외에 대해, 메서드 호출자에게
	//책임을 떠 넘기는 키워드가 throws 
	public void cal() throws CalculateFailException{
		//에러..인간 감당 불가..
		//예외란(Exception)? 프로그램의 정상 수행을 방해하는 요인
		/*
		 * 1.강요된 예외 - 코드 작성 시 즉 컴파일할때 예외 처리를 하지 않으면 절대로 그냥 넘어가지 않는
		 * 					  	처리를 강제하는 예외..sun에 이미 정해놓아서, 처리하지 않으면 이클립스의 경우
		 * 						빨간 줄~~
		 * 
		 * 2.강요되지 않은 예외 - 개발자가 처리하지 않아도 아무리 뭐라 하지 않는다..즉 컴파일이 성공된다
		 * 							  강요되지 않은 예외는 RuntimeException 으로 대표됨..
		 * 							따라서 개발자가 자신만의 업무내용에 맞는 예외를 정의하고 싶다면 
		 * 							RuntimeException을 재정의해서 커스터마이징 하면 된다.
		 * */
		int[] arr = new int[3];
		try {
			arr[4]=5; //ArrayOutofBoundException
		}catch(ArrayIndexOutOfBoundsException e) {
			//내가 정의한 예외를 일부러 일으키자!! 에러를 일으켜보자 
			//throw 는 예외를 개발자가 일부러 발생시키는 명령어 이다
			throw new CalculateFailException("계산실수엿어요ㅜㅜ", e);
		}
	}

}
