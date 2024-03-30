<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//클라이언트가 요청한 혈액형 파라미터에 대해 분석하여, 그 결과를
	//출력하자
	String blood = request.getParameter("blood");
	
	//판단시작 
	String msg=null;
	
	if(blood.equals("A")){
		msg="세심하고 꼼꼼하게 일을 처리한다";
	}else if(blood.equals("B")){
		msg="귀가 얇지 않다";
	}else if(blood.equals("AB")){
		msg="다채롭다";
	}else if(blood.equals("O")){
		msg="사교적이고 긍정적이다";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>당신이 선택한 혈액형에 대한 결과 </h1>
	<%=msg %>
</body>
</html>


