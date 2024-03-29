<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/inc/header_link.jsp" %>
<script type="text/javascript">
	$(function(){
			
		//서버에 파라미터를 전송하되, 텍스트와 바이너리가 복합된 형태의 데이터를 전송해야 하므로, 
		//encoding 을 multipart/form-data  , 서버측에서는 파일업로드 컴포넌트가 준비되어야 함
		$("#bt_regist").click(function(){
			$("form").attr({
				action:"/board/regist", 
				method:"post",
				enctype:"multipart/form-data"
			});
			$("form").submit();
		});
		
	});
</script>
</head>
<body>

	<div class="container">
		<h2>글쓰기 폼</h2>
		<!-- 입력 폼 시작  -->
		<form>
			<div class="form-group">
				<input type="text" class="form-control" name="title" placeholder="제목입력..">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" name="writer" placeholder="작성자..">
			</div>
			<div class="form-group">
				<textarea class="form-control" name="content" placeholder="작성자.."></textarea>
			</div>
			<div class="form-group">
				<input type="file" class="form-control" name="file">
			</div>
			
			<button type="button" class="btn btn-primary" id="bt_regist">Submit</button>
		</form>

	</div>

</body>
</html>
