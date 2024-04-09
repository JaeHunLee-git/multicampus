package com.sds.mall.exception;

public class UseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExceptionTest et = new ExceptionTest();
	
		try {
			et.cal(); //계산해!!
		}catch(CalculateFailException e) {
			System.out.println("계산 결과 에러 발생 보고 ");
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}

}
