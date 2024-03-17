<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%!
	//jsp 페이지의 멤버영역 
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
%>
<%
	//클라이언트가 전송한 notice_idx 파라미터 값을 추출하자 
	String notice_idx = request.getParameter("notice_idx");

	String sql="delete notice where notice_idx="+notice_idx;
	out.print(sql);
	
	Connection con=null;
	PreparedStatement pstmt=null;

	con = DriverManager.getConnection(url, user, pass);
	
	pstmt=con.prepareStatement(sql);//쿼리문 준비 
	int result =  pstmt.executeUpdate(); //쿼리수행 

	if(result >0){
		out.print("<script>");
		out.print("alert('삭제성공');");
		out.print("location.href='/notice/list.jsp';");
		out.print("</script>");
	}else{
		out.print("실패");
	}
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();

%>