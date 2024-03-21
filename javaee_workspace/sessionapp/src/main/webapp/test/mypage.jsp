<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//현재 이 jsp 요청과 관련된 세션 객체로부터 저장된 데이터 가져오기 
	//jsp에서 내장 객체 중 세션을 다루기 위한 내장객체명이 바로 session 
	String name = (String)session.getAttribute("name");
	String hobby = (String)session.getAttribute("hobby");
	int age = (Integer)session.getAttribute("age"); //unBoxing
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
	<h1>마이페이지</h1>
	<table width="300px" border="1px" align="center">
		<tr>
			<td>이름</td>
			<td><%=name %></td>
		</tr>
		<tr>
			<td>취미</td>
			<td><%=hobby %></td>
		</tr>
		<tr>
			<td>나이</td>
			<td><%=age %></td>
		</tr>		
	</table>
</body>
</html>