<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collection"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.util.JSONBuilder"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
------------------------------------------------------------
* @설명 : 영화코드 조회 REST 호출 - 서버측에서 호출하는 방식 예제
------------------------------------------------------------
-->
    <%
    // 파라메터 설정
	String curPage = request.getParameter("curPage")==null?"1":request.getParameter("curPage");					//현재페이지
	String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");		//결과row수
	String peopleNm = request.getParameter("peopleNm")==null?"":request.getParameter("peopleNm");				//영화인명
	String filmoNames = request.getParameter("filmoNames")==null?"":request.getParameter("filmoNames");				//영화인 대표명
	
	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";
	// KOBIS 오픈 API Rest Client를 통해 호출
    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

	// 영화사코드조회 서비스 호출 
    String peopleCdResponse = service.getPeopleList(true, curPage, itemPerPage, peopleNm, filmoNames);
	// Json 라이브러리를 통해 Handling
	ObjectMapper mapper = new ObjectMapper();
	HashMap<String,Object> result = mapper.readValue(peopleCdResponse, HashMap.class);

	request.setAttribute("result",result);
	
	
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<c:out value="${result.peopleListResult.totCnt}"/>
	<table border="1">
		<tr>
			<td>영화인코드</td><td>영화인명</td><td>영화인명(영문)</td><td>분야</td><td>필모리스트</td>
		</tr>
	<c:if test="${not empty result.peopleListResult.peopleList}">
	<c:forEach items="${result.peopleListResult.peopleList}" var="people">
		<tr>
			<td><c:out value="${people.peopleCd }"/></td><td><c:out value="${people.peopleNm }"/></td><td><c:out value="${people.peopleNmEn }"/></td><td><c:out value="${people.repRoleNm}"/></td>
			<td><c:out value="${people.filmoNames}"/></td>						
		</tr>
	</c:forEach>
	</c:if>
	</table>
	<form action="">
		현재페이지 :<input type="text" name="curPage" value="<%=curPage %>">
		최대 출력갯수:<input type="text" name="itemPerPage" value="<%=itemPerPage %>">
		영화인명:<input type="text" name="peopleNm" value="<%=peopleNm %>">		
		필모그래피:<input type="text" name="filmoNames" value="<%=filmoNames %>"> <br/>
		
		<input type="submit" name="" value="조회">
	</form>
</body>
</html>