<%@page import="com.sds.spring.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Board> boardList = (List)request.getAttribute("boardList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ include file="../inc/header_link.jsp" %>
<script type="text/javascript">
$(function(){
	$("button").click(()=>{
		//글쓰기 폼 요청 WEB-INF에 들어있는 jsp는 직접 접근 불가능, 반드시 하위컨트롤러를 거쳐야 한다
		location.href="/board/writeform";   
	});
});
</script>



</head>
<body>
	<div class="container">
	    <h2>Hoverable Dark Table</h2>
	    <p>The .table-hover class adds a hover effect (grey background color) on table rows:</p>
	    <table class="table table-dark table-hover">
	        <thead>
	            <tr>
	                <th>No</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>등록일</th>
	                <th>조회수</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<%for(int i=0;i<boardList.size();i++){ %>
	        	<%Board board = boardList.get(i); %>
	            <tr>
	                <td><%=i %></td>
	                <td>
	                	<a href="/board/detail?board_idx=<%=board.getBoard_idx()%>"><%=board.getTitle() %></a>
	                </td>
	                <td><%=board.getWriter() %></td>
	                <td><%=board.getRegdate()%></td>
	                <td><%=board.getHit() %></td>
	            </tr>
	            <%} %>
	            <tr>
	            	<td colspan="5">
	            		<button class="btn btn-primary">글쓰기</button>
	            	</td>
	            </tr>
	        </tbody>
	    </table>
	</div>
</body>
</html>