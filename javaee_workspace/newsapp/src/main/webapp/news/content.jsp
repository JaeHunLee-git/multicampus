<%@page import="com.sds.newsapp.news.News"%>
<%@page import="com.sds.newsapp.news.NewsDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	NewsDAO newsDAO = new NewsDAO();
%>
<%
	//클라이언트가 전송한 news_idx 파라미터를 받아서, 
	//DAO에게 한건 가져오게 시키자!!!
	String news_idx = request.getParameter("news_idx");  // null
	out.print("클라이언트가 전송한 idx 값은"+news_idx);
	
	//ResultSet 대신 , News DTO로 게시물 1건 반환 받음
	News news = newsDAO.select(Integer.parseInt(news_idx)); 
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
<%@ include file="../inc/head.jsp" %>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
	$(function(){
		$('#content').summernote();
		
		$("#bt_del").click(function(){
			if(confirm("삭제하시겠어요?")){
				//delete news where news_idx=3
				location.href="/news/delete?news_idx=<%=news_idx%>";
			}
		});
		
		$("#bt_list").click(function(){
			location.href="/news/list.jsp";
		});
		
		$("#bt_edit").click(function(){ //수정 버튼 누르면..
			if(confirm("수정하시겠어요?")){
				$("form").attr({
					action:"/news/edit",
					method:"post"
				});
				$("form").submit();//전송
			}
		});
	});
</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form>
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			 
			<input type="text" name="title" value="<%=news.getTitle()%>"> 
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea id="content" name="content" placeholder="내용.." style="height: 400px"><%=news.getContent()%></textarea>

			<input type="button" value="수정" id="bt_edit">
			<input type="button" value="삭제" id="bt_del">
			<input type="button" value="목록" id="bt_list">
		</form>
	</div>

</body>
</html>
