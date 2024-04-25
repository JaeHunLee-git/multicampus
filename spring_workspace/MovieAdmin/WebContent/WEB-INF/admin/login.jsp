<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

	<%@ include file="./inc/header_link.jsp" %>
	
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
	<!-- Content Wrapper. Contains page content -->
	
		<div class="card card-info mt-5 ml-5 mr-5">
		    <div class="card-header">
		        <h3 class="card-title">관리자 로그인</h3>
		    </div>
		    <!-- /.card-header -->
		    <!-- form start -->
		    <form class="form-horizontal">
		        <div class="card-body">
		            <div class="form-group row">
		                <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
		                <div class="col-sm-10">
		                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
		                </div>
		            </div>
		            <div class="form-group row">
		                <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
		                <div class="col-sm-10">
		                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
		                </div>
		            </div>
		            <div class="form-group row">
		                <div class="offset-sm-2 col-sm-10">
		                    <div class="form-check">
		                        <input type="checkbox" class="form-check-input" id="exampleCheck2">
		                        <label class="form-check-label" for="exampleCheck2">Remember me</label>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <!-- /.card-body -->
		        <div class="card-footer">
		            <button type="button" class="btn btn-info" id="bt_login">로그인</button>
		            <button type="button" class="btn btn-info" id="bt_regist">관리자 등록</button>
		            
		        </div>
		        <!-- /.card-footer -->
		    </form>
		</div>		
	
	<!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
	
<%@ include file="./inc/footer_link.jsp" %>	

</body>
</html>
<script type="text/javascript">
	$(function(){
		$("#bt_regist").click(function(){
			location.href="/admin/registform";
		});		
	});
</script>







