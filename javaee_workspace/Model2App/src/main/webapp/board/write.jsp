<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/inc/header.jsp" %>
<script type="text/javascript">

//폼의 입력 데이터들을 서버로 전송하자 
function regist(){
	$("form").attr({
		action:"/board/regist.do", 
		method:"post"
	});
	$("form").submit(); //전송
}

$(document).ready(function() {
	
	$("textarea").summernote({
		height:300, 
		placeholder:"내용 입력.."
	}); //textarea를 편집기로 변신!!
	
	//글쓰기 버튼에 이벤트 연결 
	$("button").click(function(){
		regist();
	});
});
</script>
</head>
<body>
<h2>글쓰기 폼</h2>
	<form>
	    <div class="form-group">
	        <input type="text" class="form-control" placeholder="제목 입력.." name="title">
	    </div>
	    <div class="form-group">
	        <input type="text" class="form-control" placeholder="작성자 입력.." name="writer">
	    </div>
	    <div class="form-group">
	        <textarea class="form-control" placeholder="Enter password" name="content"></textarea>
	    </div>

	    <button type="button" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>