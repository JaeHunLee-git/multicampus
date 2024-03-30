<%@ page contentType="text/html;charset=UTF-8"%>
<%
	/*
	1) 특정한 개발방법론을 적용하지 않고, 오직 jsp 만으로 개발하는 방법 
		- 장점) 개발속도가 무지 빠르다, 규모가 작은 규모의 어플리케이션 개발시 유리하다
		- 단점) 유지보수성이 떨어진다. 즉 코드의 재사용성이 떨어짐. 따라서 디자인이 변경되어도 디자인과 
				함께 작성된 로직과 컨트롤러 코드가 함께 버려져야 함..
					
	2) 모델1 - Model, View, Controller가 합쳐진 코드에서 Model 영역을 완전히 분리시켜놓은 개발방법 
				 모델1이란 용어는 어디서 등장? javaEE 기반에서 사용되는 용어 
			     javaEE에 의하면 model1 중 로직을 순수한 java 코드로 작성함이 원칙(Plain Old Java Object)
				모델1 은 아직 디자인과 컨트롤러가 합쳐져 있는 상태의 개발 방식을 말함
				ex) 게시판 제작 시 로직을 DAO로 분리시켜서 개발했던 방식이 모델1 방식 이었슴...

	현재 result.jsp 페이지는 모델1이기 때문에 여전히 View(디자인)와 Controller(컨트롤러)가 함께 작성되어
	있다..따라서 result.jsp 페이지를 버리게 되면 컨트롤러가 함께 버려지게 된다..
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>당신이 선택한 영화에 대한 결과 </h1>
	<%String msg=(String)request.getAttribute("msg"); %>
	<%=msg %>
</body>
</html>


