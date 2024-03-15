<%@ page contentType="text/html;charset=utf-8"%>

<%!
	String title="java 백엔드 개발자 과정";

	//멤버 영역(멤버변수와 메서드 정의 영역)
	public String getMsg(){
		return title;
	}
%>

<%
/*
1) jsp 파일 정의하는 법  - 확장자만  .jsp이면 됨 

2) jsp 코드를 작성할 수 있는 영역  (4가지 영역 )

	- 지시영역 :  [%@   %]  현재 페이지의 종류(이미지,음원,text문서중html, xml,json..)
									현재 페이지의 인코딩 지정 등..현재 페이지의 설정관련 코드
									import 등..
	- 선언부 : [%!    %] 현재 jsp의 멤버 변수와 멤버 메서드를 작성할 수 있는 영역 

	- 스크립틀릿(Scriptlet)  [%  %] : service() 메서드의 영역, 로직을 작성하는 영역 

*/
	
	for(int i=1;i<=10;i++){
		out.print(getMsg());// 클라이언트 웹브라우저에 지정한 문자열 출력! 이때 문자열이 
									//<태그>인 경우 클라이언트인 웹브라우저는 태그로 해석해 버림..

	}
%>