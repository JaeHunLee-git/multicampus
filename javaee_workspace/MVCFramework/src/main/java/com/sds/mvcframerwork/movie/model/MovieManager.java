package com.sds.mvcframerwork.movie.model;

//이 클래스는 웹용도 아니고, 독립실행형 전용 클래스도 아닌 그냥 java분야의 중립적 로직 객체인 
//모델이다!!
public class MovieManager {
	
	public String getAdvice(String movie) {
		String msg=null;
		
		if(movie.equals("파묘")) {
			msg="천만관객 동원 공포영화";	
		}else if(movie.equals("미션임파서블5")) {
			msg="톰크루즈 주연 액션 시리즈";	
		}else if(movie.equals("듄2")) {
			msg="거대한 스케일의 SF영화";	
		}else if(movie.equals("스타워즈")) {
			msg="스타워즈 마지막편";	
		} 
		return msg;
	}
}
