<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			location.href="/board/write.jsp";
		});
	});
</script>
</head>
<body>
	<h2>Hoverable Dark Table</h2>
	<p>The .table-hover class adds a hover effect (grey background
		color) on table rows:</p>
		
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

			<tr>
				<td><%=1%></td>
				<td>
					<a href="/board/detail?board_idx="></a>
				</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="5">
					<button class="btn btn-primary">글등록</button>
				</td>
			</tr>
			
		</tbody>
	</table>
</body>
</html>




