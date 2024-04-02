<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
	<h1>에러 발생</h1>
	<p>이용에 불편을 드려 죄송합니다.</p>
	 
	<p><%=request.getAttribute("msg")%></p>
	 
	<p>목록 <a href="/board/list.do">바로가기</a></p>
	 
</body>
</html>