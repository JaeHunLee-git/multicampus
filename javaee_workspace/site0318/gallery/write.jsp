<%@ page contentType="text/html;charset=utf-8"%>
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
	function send(){
		let form1 = document.getElementById("form1");
		form1.action="/gallery/regist.jsp"; //서버측의 업로드 요청을 받을 url
		form1.method="post";
		form1.submit(); //전송
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
			
			<input type="text" name="title" placeholder="제목 입력.."> 
			<input type="text" name="writer" placeholder="작성자 입력.."> 
			<textarea id="subject" name="content" placeholder="Write something.." style="height: 200px"></textarea>
			<input type="file" name="photo">	
			<p></p>
			<input type="button" value="업로드" onClick="send()">
		</form>
	</div>

</body>
</html>
