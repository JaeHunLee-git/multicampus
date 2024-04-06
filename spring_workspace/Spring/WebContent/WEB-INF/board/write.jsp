<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header_link.jsp" %>
<script type="text/javascript">

	function regist(){
		$("form").attr({
			action:"/board/regist", 
			method:"post"
		});		
		$("form").submit();
	}
	
	$(function(){
		$('#content').summernote({
			height:200, 
			placeholder:"내용입력.."
		});
		
		$("#bt_regist").click(()=>{
			regist();
		});
	});

</script>
</head>
<body>
	
	<div class="container mt-5">
		<form>
		    <div class="form-group">
		        <input type="text" class="form-control" placeholder="제목 입력" name="title">
		    </div>
		    
		    <div class="form-group">
		        <input type="text" class="form-control" placeholder="작성자 입력.." name="writer">
		    </div>

		    <div class="form-group">
		        <textarea class="form-control" name="content" id="content"></textarea>
		    </div>
		    
		    <div class="form-group">
		    	<button type="button" class="btn btn-primary" id="bt_regist">전송</button>
		    </div>
		</form>	
	</div>
	
</body>
</html>