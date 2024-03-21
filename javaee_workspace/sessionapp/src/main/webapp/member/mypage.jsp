<%@page import="com.sds.sessionapp.member.Member"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
	<%
		//jsp 에서는 세션을 내장객체로 지원 한다, 명칭 또한 이미 정해진 session 이다 
		Member member = (Member)session.getAttribute("member");
		
	%>
	<h1><%=member.getName() %> 님의 정보 </h1>
	<p>
		아이디 : <%=member.getId() %> <br>
		이름 : <%=member.getName() %> <br>
		이메일 : <%=member.getEmail() %> <br>
		메일수신여부 : <%=member.getReceive() %> <br>
		가입일자 : <%=member.getRegdate() %> <br>
	</p>
	
</body>
</html>