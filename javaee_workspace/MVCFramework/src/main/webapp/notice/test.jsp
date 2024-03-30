<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//고양이가 지원하는 커넥션풀에 접근해보기...
	
	//아래의 데이터소스가 바로 커넥션풀을 구현한 구현체인데, 저 객체를 접근하려면 server.xml, context.xml
	//외부 자원에 명시되어 있으므로, 자바코드로 xml에서 자원을 검색을 시도해야 한다.. 
	Context context=null; // xml 등 외부 자원을 검색하는 객체 
	DataSource ds=null;  //커넥션풀 구현체
	
	//참고로 java:comp/env  까지는 이미 정해진 이름이므로 누락해서는 안됌
	context = new InitialContext();
	
	//jndi 이름을 통해 구현체를 얻는다!
	ds=(DataSource)context.lookup("java:comp/env/jndi/oracle");
	
	Connection con = ds.getConnection(); //풀로부터 커넥션 객체 1개를 꺼내기
	//주의 여기서 접속이 발생된 것이 아니라, 이미 접속되어진 객체를 그냥 얻어온 것이다..
	//따라서 앞으로 개발자는 빌려온 Connection 을 이용하여 원하는 쿼리문 날리고, Connection 을
	//끊지 않고 다시 반납하자.. 
	
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="select * from notice";
	
	pstmt=con.prepareStatement(sql);
	rs = pstmt.executeQuery(); // select문 수행 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%while(rs.next()){%>
	<%=rs.getString("title")%><br>		
	<%} %>
</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close(); //톰켓의 풀로부터 얻어온 Connection을 반납하는 코드는 따로 있는게 아니라, 그냥 close()
	//하면 된다..이렇게 하면 풀로 돌려보내는 것이다..
%>



