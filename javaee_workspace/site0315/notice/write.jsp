<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script>
/*
자바스크립트는 언어 능력이 부족해서가 아니라, 프론트 앤드 측 기술이므로, 
소스가 노출되어 지기 때문에 db 연동을 직접하게 되면, 보안상 취약....
따라서 직접 오라클에 연동하지말고, 원하는 데이터를 서버로 전송만 하고, 서버측의 
스크립트 기술인 백앤드 기술인 jsp에게 의뢰하자~!! (즉 요청하자...)
*/
function send(){
	//폼양식을,  서버측에 존재하는 페이지인 regist.jsp 에게 전송해보자 
	let form1 = document.getElementById("form1");
	form1.action="/notice/regist.jsp"; //폼전송 대상 
	form1.method="post"; //서버에 어떤 요청을 할때, 전송할 데이터가 있다면  post 방식으로
									//요청을 시도해야 한다..(HTTP 프로토콜에서 그러하다)
	form1.submit(); //전송 행위

}
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
	<!--
	id 와 name  공통점: 문서내의 요소를 구분하기 위한 용도
	차이점 : name은 중복을 허용하고 + 서버에 전송시 전송 파라미터 역할까지 수행
				즉  http 상의 전송 시 데이터를 실어 나르는 변수 역할을 수행
	-->
    <input type="text" name="title" placeholder="제목입력..">
    <input type="text" name="writer" placeholder="작성자 입력..">
    <textarea name="content" placeholder="내용입력.." style="height:200px"></textarea>

    <input type="button" value="글쓰기" onClick="send()">

  </form>
</div>

</body>
</html>
