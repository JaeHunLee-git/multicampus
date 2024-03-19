<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
	//클라이언트가 전송한  gallery_idx를 추출
	String gallery_idx = request.getParameter("gallery_idx");

	String sql="select * from gallery where gallery_idx="+gallery_idx;
	out.print(sql);

	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
	
	Class.forName(driver);

	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	con = DriverManager.getConnection(url, user, pass);
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();

	rs.next(); //커서 한칸 전진
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

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
	//입력 양식의 데이터들을 서버로 전송하자
	function edit(){
		if(confirm("수정하시겠습니까?")){ //실수 방지
			let form1 = document.getElementById("form1");
			form1.action="/gallery/update.jsp"; //서버측의 업로드 요청을 받을 url
			form1.method="post";
			form1.submit(); //전송
		}
	}	

	function del(){
		if(confirm("정말 삭제하시겠어요?")){
			
			//location.href="/gallery/delete.jsp?gallery_idx=<%=gallery_idx%>";

			//파일명과 gallery_idx 두개이상을 전송해야 하므로, 아래쪽에 form태그에 
			//히든으로 파라미터들이 준비되어 있으므로...기존 폼을 전송해버리자 
			//삭제 요청시엔 파일을 전송하는 것이 아니므로, 
			//복합데이터형식인 multipart/form-data 로 보낼 필요가 없다!!!
			let form1 =  document.getElementById("form1");
			
			//프로그래밍 적으로 encoding 방식을 바꾸면 된다 
			form1.encoding="";  //js 에서는 enctype 속성이 encoding으로 처리해야 함..
			form1.method="post";
			form1.action="/gallery/delete.jsp";
			form1.submit();
		}
	}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<!-- 텍스트 데이터와 바이너리 데이터가 섞여 있을때는 복합 데이터임을 알려줘야 함
			생략시, 텍스트 데이터만 전송됨
		-->
		<form id="form1" enctype="multipart/form-data">
			<input style="background-color: yellow;" type="hidden" name="filename" value="<%=rs.getString("filename")%>">
			<input style="background-color: yellow;" type="hidden" name="gallery_idx" value="<%=rs.getInt("gallery_idx")%>">
			
			<input type="text" name="title" value="<%=rs.getString("title")%>"> 
			<input type="text" name="writer" value="<%=rs.getString("writer")%>"> 
			<textarea id="subject" name="content" style="height: 200px"><%=rs.getString("content")%></textarea>
			
			<p>
				<img src="/data/<%=rs.getString("filename")%>" width="200px">									
			</p>

			<input type="file" name="photo">	
			<p></p>
			<input type="button" value="수정" onClick="edit()">
			<input type="button" value="삭제" onClick="del()">
			<input type="button" value="목록" onClick="location.href='/gallery/list.jsp';">
		</form>
	</div>

</body>
</html>
<%
	if(rs!=null)rs.close();
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
%>
