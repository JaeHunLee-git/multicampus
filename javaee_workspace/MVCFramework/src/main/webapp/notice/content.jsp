<%@page import="com.sds.mvcframework.notice.model.Notice"%>
<%@page import="com.sds.mvcframework.notice.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! NoticeDAO noticeDAO = new NoticeDAO(); %>
<%
	//상세내용을 채워넣기 위해, 게시물 1건을 화면에 출력 
	String notice_idx = request.getParameter("notice_idx");

	Notice notice = noticeDAO.select(Integer.parseInt(notice_idx)); //게시물 1건 반환
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>

<%@ include file="/inc/header_link.jsp" %>
<script type="text/javascript">

//동기 방식의 전송
function edit(){
	$("form").attr({
		action:"/notice/edit", //서블릿에게 요청할 예정  
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