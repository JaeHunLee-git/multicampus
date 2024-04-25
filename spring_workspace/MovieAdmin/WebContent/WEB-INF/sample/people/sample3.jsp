<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.people.PeopleAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.people.PeopleInfoResult"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeResultVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
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
	String peopleCd = request.getParameter("peopleCd")==null?"20164556":request.getParameter("peopleCd");						//영화코드
	
	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";	
	
	// KOBIS 오픈 API SOAP Client를 통해 호출
	PeopleInfoResult peopleInfoResult = new PeopleAPIServiceImplService().getPeopleAPIServiceImplPort().searchPeopleInfo(key, peopleCd);
	
	request.setAttribute("peopleInfoResult",peopleInfoResult);	
	
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
</head>
<body>	
	<table border="1">
		<tr>
			<td>영화인코드</td><td>영화인명</td><td>영화인명(영)</td><td>성별</td><td>영화인분류</td><td>필모리스트</td>			
		</tr>
		 <c:if test="${not empty peopleInfoResult.peopleInfo}">	
			<tr>
				<td><c:out value="${peopleInfoResult.peopleInfo.peopleCd}"/></td><td><c:out value="${peopleInfoResult.peopleInfo.peopleNm}"/></td>
				<td><c:out value="${peopleInfoResult.peopleInfo.peopleNmEn }"/></td><td><c:out value="${peopleInfoResult.peopleInfo.sex }"/></td>
				<td><c:out value="${peopleInfoResult.peopleInfo.repRoleNm }"/></td>
				<td>
					<c:if test="${not empty peopleInfoResult.peopleInfo}">
						<c:forEach items="${ peopleInfoResult.peopleInfo.filmos.filmo}" var="filmo">(<c:out value="${filmo.movieCd}"/>)<c:out value="${filmo.movieNm}"/>-<c:out value="${filmo.moviePartNm}"/>|</c:forEach>
					</c:if>
				</td>
			</tr>	
		</c:if> 
	</table>	
</body>
</html>