<%@page import="com.sds.mvcproject.notice.model.Notice"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Notice notice =(Notice)request.getAttribute("notice");//요청객체에 심어진 DTO꺼내기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function edit(){
	$("form").attr({
		action:"/board/edit.do", //서블릿에게 요청할 예정  
		method:"post"
	});
	
	$("form").submit(); //전송
}

$(function(){
	$("#bt_edit").click(function(){
		if(confirm("수정하시겠어요?")){
			edit();
		}
	});
	
	$("#bt_del").click(function(){
		if(confirm("삭제하시겠어요?")){
			location.href="/board/delete.do?notice_idx=<%=notice.getNotice_idx()%>";
		}
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
		<input type="hidden" name="notice_idx" value="<%=notice.getNotice_idx()%>">
		
		<div class="card-body">
		
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" value="<%=notice.getTitle()%>">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="inputPassword3" class="col-sm-2 col-form-label" >작성자</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="writer" name="writer" value="<%=notice.getWriter()%>">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="inputEmail3" class="col-sm-2 col-form-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="content" name="content" placeholder="제목 입력"><%=notice.getContent() %> </textarea>
			</div>
		</div>
		</div>
	
		</div>
		
		<div class="card-footer">
			<button type="button" class="btn btn-info" id="bt_edit">글수정</button>
			<button type="button" class="btn btn-info" id="bt_del">글삭제</button>
			<button type="button" class="btn btn-default float-right" id="bt_list">목록</button>
		</div>
		
		</form>
		</div>	
	
	</div>
</div>

</body>
</html>