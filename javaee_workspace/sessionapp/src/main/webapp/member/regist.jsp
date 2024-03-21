<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

.input-container {
	display: -ms-flexbox; /* IE10 */
	display: flex;
	width: 100%;
	margin-bottom: 15px;
}

.icon {
	padding: 10px;
	background: dodgerblue;
	color: white;
	min-width: 50px;
	text-align: center;
}

.input-field {
	width: 100%;
	padding: 10px;
	outline: none;
}

.input-field:focus {
	border: 2px solid dodgerblue;
}

/* Set a style for the submit button */
.btn {
	background-color: dodgerblue;
	color: white;
	padding: 15px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.btn:hover {
	opacity: 1;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function regist(){
		//아이디를 입력 안 한 경우
		if($("input[name='id']").val().length<1){
			alert("아이디를 입력하세요");
			$("input[name='id']").focus();
		}else if($("input[name='pass']").val().length<1){
			alert("비밀번호를 입력하세요");
			$("input[name='pass']").focus();
		}else{
			//입력을 모두 제대로 했다면. 서버로 전송 
			$("form").attr("action", "/member/regist"); //요청 url 
			$("form").attr("method", "post"); //요청 방법 
			$("form").submit(); //전송
		}
		
		
	}
	
	$(function(){
		$("button").click(function(){
			regist();
		});		
	});
</script>
</head>
<body>

	<form style="max-width: 500px; margin: auto">
		<h2>Register Form</h2>
		<!-- ID -->
		<div class="input-container">
			<i class="fa fa-user icon"></i> 
			<input class="input-field" type="text" placeholder="Your ID..." name="id">
		</div>

		<!-- Pass -->
		<div class="input-container">
			<i class="fa fa-key icon"></i> 
			<input class="input-field" type="password" placeholder="Password" name="pass">
		</div>
		
		<!-- Name -->
		<div class="input-container">
			<i class="fa fa-user icon"></i> 
			<input class="input-field" type="text" placeholder="Username" name="name">
		</div>
		
		<!-- Email -->
		<div class="input-container">
			<i class="fa fa-envelope icon"></i> 
			<input class="input-field" type="text" placeholder="Email" name="email">
		</div>
		
		<!-- 광고수신 여부 -->
		<div class="input-container">
			<i class="fa fa-envelope icon"></i> 
			<input type="radio" name="receive" value="1">예
			<input type="radio" name="receive" value="0">아니오
		</div>
		
		<!-- 보유 스킬 -->
		<div class="input-container">
			<i class="fa fa-envelope icon"></i> 
			<input type="checkbox" name="skill" value="java">Java
			<input type="checkbox" name="skill" value="spring">Spring
			<input type="checkbox" name="skill" value="oracle">Oracle
			<input type="checkbox" name="skill" value="mybatis">Mybatis
			<input type="checkbox" name="skill" value="hibernate">Hibernate
		</div>


		<button type="button" class="btn">Register</button>
	</form>

</body>
</html>
