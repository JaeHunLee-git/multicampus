<%@ page contentType="text/html;charset=UTF-8"%>
<%
/*
읽혀진 데이터들의 양이 많을 경우, 하나의 페이지내에서 모두~ 보여주려고 하면, 스크롤이 생겨
사용자들에게 불편함을 주게 된다..
이 문제를 해결하려면, 데이터를 한꺼번에 보여주려고 하지 말고, 조금씩 분할하여 보여주면 된다
이때 분할된 각각의 단위를 페이지라 하며, 이 페이지와 관련된 산수 로직을 페이징 처리라 한다..
개발자마다 산수 로직은 틀리며 각자 알아서 자기만의 로직을 적용하면 된다..

페이징 처리의 가장 근본이 되는 데이터는 총 레코드 수이다..이유는? 레코드가 존재해야 분할하던
말던 하지...
*/
int totalRecord=926; //총 레코드 수
int pageSize=10;//한 페이지당 10건씩 보여주기
//나머지 페이지를 보여주기 위해서는 페이지 분할 번호가 등장해야 하는데, 이때 총 몇페이지인지를 계산
int totalPage=(int)Math.ceil((float)totalRecord/pageSize); //총 페이지 수
//out.print(    (int)Math.ceil((float)totalRecord/pageSize)   );
int blockSize=10; //한 블럭당 몇 페이지씩 보여줄지 결정

int currentPage=1; //클라이언트가 현재 보려고 하는 페이지 즉 현재 페이지 

//혹여나, 사용자가 현재 list.jsp를 요청하면서, 전달된 파라미터 중 currentPage라는 파라미터가 있다면
//그 그 값을 currentPage 변수값으로 사용하자!!
//모든 파라미터 변수는 사실 상 문자열로 처리되므로, 파라미터가 넘어오지 않았다는 것은 String 객체가
//메모리에 올라오지 못했단 것이다.. String은 객체이므로 당연하다..
if(request.getParameter("currentPage") !=null){ 
	//넘어온 파라미터가 있을때만...아래의 처리...
	currentPage=Integer.parseInt(request.getParameter("currentPage"));
}
%>
<%="totalRecord = "+totalRecord+"<br>"%>
<%="pageSize = "+pageSize+"<br>"%>
<%="totalPage = "+totalPage+"<br>"%>
<%="currentPage = "+currentPage+"<br>"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/admin/dist/css/adminlte.min.css">
<style type="text/css">
	/*우리만의 스타일 클래스를 정의해보자  */
	.pageStyle{
		font-size:13px;
		font-weight:bold;
		color:red;
	}
</style>
</head>
<body>

<!-- Admin LTE의 테이블 사용  -->
	<div class="row">
		<div class="col-9">
		
		<div class="card">
		     <div class="card-header">
		       <h3 class="card-title">공지 게시판</h3>
		
		       <div class="card-tools">
		         <div class="input-group input-group-sm" style="width: 150px;">
		       <input type="text" name="table_search" class="form-control float-right" placeholder="Search">
		
		       <div class="input-group-append">
		         <button type="submit" class="btn btn-default">
		           <i class="fas fa-search"></i>
		         </button>
		       </div>
		     </div>
		   </div>
		 </div>
		 <!-- /.card-header -->
		 <div class="card-body table-responsive p-0">
		   <table class="table table-hover text-nowrap">
		     <thead>
		       <tr>
		         <th>No</th>
		         <th>Title</th>
		         <th>Writer</th>
		         <th>Date</th>
		         <th>Read</th>
		       </tr>
		     </thead>
		     
		     <tbody>
		     	<%for(int i=1;i<=pageSize;i++){ %>
		       <tr>
		         <td>183</td>
		         <td>John Doe</td>
		         <td>11-7-2014</td>
		         <td><span class="tag tag-success">Approved</span></td>
		         <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
		       </tr>
		       <%} %>
		       
		     </tbody>
		   </table>
		 </div>
		 <!-- /.card-body -->
		<!-- 카드 푸터 시작 -->
			<div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item"><a class="page-link" href="#">«</a></li>
                  
                  <%for(int i=firstPage;i<=lastPage;i++){%>
                  	<li class="page-item">
                  		<a class="page-link  <%if(currentPage==i){%>pageStyle<%}%>" href="/notice/list.jsp?currentPage=<%=i%>"><%=i%></a>
                  	</li>
                  <%} %>
                  <li class="page-item"><a class="page-link" href="#">»</a></li>
                </ul>
            </div>		
		<!-- 카드 푸터 끝 -->			 
		 
		         </div>
		
		</div>
	</div>
		

</body>
</html>