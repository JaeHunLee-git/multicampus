<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	function regist(){
		$("form").attr({
			method:"post",
			action:"/board/regist.do"
		});
		$("form").submit();
		
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();	
		});		
		
		$("#bt_list").click(function(){
			location.href="/board/list.do";
		});
	});

</script>
</head>
<body>

<div class="row">
	<div class="col-12">
	
		<div class="card card-info">
			<div class="card-header">
				<h3 class="card-title">Horizontal Form</h3>
			</div>
		
		<form class="form-horizontal">
		<div class="card-body">
		
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="title" placeholder="제목 입력">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="inputPassword3" class="col-sm-2 col-form-label">작성자</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="writer" placeholder="작성자입력">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="content" placeholder="제목 입력"> </textarea>
			</div>
		</div>
		</div>
	
		</div>
		
		<div class="card-footer">
			<button type="button" class="btn btn-info" id="bt_regist">글쓰기</button>
			<button type="button" class="btn btn-default float-right" id="bt_list">목록</button>
		</div>
		
		</form>
		</div>	
	
	</div>
</div>

</body>
</html>