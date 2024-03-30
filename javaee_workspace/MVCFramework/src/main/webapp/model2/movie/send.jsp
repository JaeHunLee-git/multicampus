<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function send(){
	let form = document.querySelector("form");
	form.action="/movie.do";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
	
	<pre>
		<form>
			<select name="movie">
				<option value="">영화 선택 ▼</option>
				<option value="파묘">파묘</option>
				<option value="미션임파서블5">미션임파서블5</option>
				<option value="듄2">듄2</option>
				<option value="스타워즈">스타워즈</option>
			</select>
			<br>
			<button type="button" onClick="send()">전송</button>
		</form>
	</pre>
	
</body>
</html>

