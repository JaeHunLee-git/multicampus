<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%!
	//jsp 페이지의 멤버영역 
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
%>
<%
	//클라이언트가 전송한 파라미터를 꺼내보자 
	String notice_idx = request.getParameter("notice_idx");

	//게시물 한건의 정보를 보여주자 
	String sql="select * from notice where notice_idx="+notice_idx;
	out.print(sql);
	

	Connection con=null;
	con = DriverManager.getConnection(url, user, pass);

	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	pstmt=con.prepareStatement(sql); //쿼리문 준비  
	rs = pstmt.executeQuery(); //select문 수행 후 표 받기
	
	rs.next(); //커서 한칸 전진
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script>


//자바스크립트는 db연동을 직접 수행할 수 없으므로, 서버측에 삭제를 요청하자
function del(){
	//서버측의 삭제를 담당하는 jsp에게 요청을 시도 
	location.href="/notice/del.jsp?notice_idx=<%=notice_idx%>";
}

</script>
</head>
<body>

<h3>글 상세보기</h3>

<div class="container">
  <form id="form1">
	<!--
	id 와 name  공통점: 문서내의 요소를 구분하기 위한 용도
	차이점 : name은 중복을 허용하고 + 서버에 전송시 전송 파라미터 역할까지 수행
				즉  http 상의 전송 시 데이터를 실어 나르는 변수 역할을 수행
	-->
    <input type="text" name="title" value="<%=rs.getString("title")%>">
    <input type="text" name="writer" value="<%=rs.getString("writer")%>">
    <textarea name="content" style="height:200px"><%=rs.getString("content")%></textarea>

    <input type="button" value="수정하기" onClick="">
	<input type="button" value="삭제하기" onClick="del();">
	<input type="button" value="목록보기" onClick="location.href='/notice/list.jsp'">

  </form>
</div>

</body>
</html>
<%
	if(rs!=null)rs.close();
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
%>