<%@ page contentType="text/html;charset=utf-8" %>
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
	
	Class.forName("oracle.jdbc.driver.OracleDriver");//드라이버 로드 
	
	Connection con=null; 
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	con = DriverManager.getConnection(url, user, pass); //접속시도 

	if(con ==null){
		out.print("접속 실패");
	}else{
		out.print("접속 성공");
	}
	
	String sql="select * from notice order by notice_idx desc";
	out.print(sql);
	
	pstmt=con.prepareStatement(sql); //쿼리 준비 

	//쿼리실행  executeQuery(); 반환 값은 rs 
	rs = pstmt.executeQuery(); 

%>
  <!DOCTYPE html>
  <html>

  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
      table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
      }

      th,
      td {
        text-align: left;
        padding: 16px;
      }

      tr:nth-child(even) {
        background-color: #f2f2f2;
      }
    </style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
			
		//js에서의 addEventListener("load", function(){}); 동일
		$(function(){  
			//문서내의 버튼에 클릭 이벤트 연결 
			//JQuery의 형식  (주체-css선택자).어떻게()
			$("button").click(function(){
				//톰켓이 보유한 글쓰기 jsp를 요청하자!!
				location.href="/notice/write.jsp";
			});
			
		});
	</script>

  </head>

  <body>

    <h2>공지게시판</h2>

  <table>
    <tr>
        <th>idx</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
      
      <%while(rs.next()){ %>
      <tr>
        <td><%=rs.getInt("notice_idx")%></td>
        <td><a href="/notice/content.jsp?notice_idx=<%=rs.getInt("notice_idx")%>"><%=rs.getString("title")%></a></td>
        <td><%=rs.getString("writer")%></td>
        <td><%=rs.getString("regdate")%></td>
        <td><%=rs.getString("hit")%></td>
      </tr>
      <%}%>
		<tr>
			<td colspan="5">
				<button>글쓰기</button>
			</td>	
		</tr>

  </table>

  </body>

  </html>
  <%
	if(rs!=null)rs.close();
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
  %>