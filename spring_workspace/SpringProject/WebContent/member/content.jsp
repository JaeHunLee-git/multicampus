<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
$(function(){
	$("")	
});
</script>
</head>
<body>
	<div class="container mt-5">
		<h2>사원 등록</h2>
		<form>
		    
		    <div class="form-group">
		        <input type="text" class="form-control" placeholder="Your ID..." name="id">
		    </div>
		    
		    <div class="form-group">
		        <input type="password" class="form-control" placeholder="Enter password" name="pass">
		    </div>
		    
		    <div class="form-group">
		        <input type="text" class="form-control" placeholder="name.." name="name">
		    </div>

		    <div class="form-group">
		        <input type="email" class="form-control" placeholder="Enter email" name="email">
		    </div>

		    <div class="form-group">
		        <input type="text" class="form-control" placeholder="혈액형" name="blood">
		    </div>

		    <div class="form-group">
		        <input type="number" class="form-control" placeholder="키입력(숫자로)" name="height">
		    </div>

		    <div class="form-group">
		        <input type="number" class="form-control" placeholder="몸무게 입력(숫자로)" name="weight">
		    </div>
		    
		    <button type="button" class="btn btn-primary" id="bt_regist">Submit</button>
		    
		</form>
	</div>
</body>




</html>