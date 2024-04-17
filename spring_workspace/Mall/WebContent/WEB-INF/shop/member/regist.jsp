<%@ page contentType="text/html;charset=UTF-8"%>
<%

%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>
	
	<%@ include file="../inc/header_link.jsp" %>	
	
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
	
	<%@ include file="../inc/header.jsp" %>
	

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <a href="#">로그인 </a>                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
					<div class="card card-info">
					    <div class="card-header">
					        <h3 class="card-title">회원 로그인</h3>
					    </div>
					    <!-- /.card-header -->
					    <!-- form start -->
					    <form class="form-horizontal">
					        <div class="card-body">
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">ID</label>
					                <div class="col-sm-10">
					                    <input type="text" name="uid" class="form-control" placeholder="Your ID...">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <label for="inputPassword3"  class="col-sm-2 col-form-label">Password</label>
					                <div class="col-sm-10">
					                    <input type="password" class="form-control" name="password" placeholder="Password">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">닉네임</label>
					                <div class="col-sm-10">
					                    <input type="text" name="nickname" class="form-control"  placeholder="닉네임...">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">이메일</label>
					                <div class="col-sm-10">
					                    <input type="email" name="email" class="form-control"  placeholder="이메일...">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">연락처</label>
					                <div class="col-sm-10">
					                    <input type="text" name="phone" class="form-control"  placeholder="연락처...">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <label for="inputEmail3" class="col-sm-2 col-form-label">주소</label>
					                <div class="col-sm-10">
					                    <input type="text" name="addr" class="form-control"  placeholder="주소...">
					                </div>
					            </div>
					            
					            <div class="form-group row">
					                <div class="offset-sm-2 col-sm-10">
					                    <button>Google 로그인</button>
					                    <button>Naver 로그인</button>
					                    <button>Kakao 로그인</button>
					                </div>
					            </div>
					        </div>
					        <!-- /.card-body -->
					        <div class="card-footer">
					            <button type="button" class="btn btn-info" id="bt_login">로그인</button>
					            <button type="button" class="btn btn-info" id="bt_regist">회원가입</button>
					        </div>
					        <!-- /.card-footer -->
					    </form>
					</div>                	
                </div>
            </div>

        </div>
    </section>
    <!-- Product Details Section End -->

	

	<%@ include file="../inc/insta.jsp" %>
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- Search End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
</body>
</html>
<script type="text/javascript">
	//비동기로 전송하기 
	function regist(){
		$.ajax({
			url:"/member/regist",
			type:"post", 
			data:{
				uid:$("input[name='uid']").val(),
				"memberDetail.password":$("input[name='password']").val(),
				nickname:$("input[name='nickname']").val(),
				email:$("input[name='email']").val(),
				"memberDetail.phone":$("input[name='phone']").val(),
				"memberDetail.addr":$("input[name='addr']").val(),
				"sns.sns_idx":4
			},
			success:function(result, status, xhr){
				if(result=="ok"){ //가입 성공이라면
					alert("가입을 축하드립니다.\n이용하시려면 로그인해 주세요");
					location.href="/member/loginform";
				}else{
					alert("회원가입에 실패하였습니다");
				}
			}
		});
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();
		});		
	});
</script>










