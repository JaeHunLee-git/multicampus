<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeResultVO"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieInfoResult"%>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
------------------------------------------------------------
* @설명 : 영화코드 조회 REST 호출 - 서버측에서 호출하는 방식 예제
------------------------------------------------------------
-->
    <%
    // 파라메터 설정	
	String movieCd = request.getParameter("movieCd")==null?"20126674":request.getParameter("movieCd");						//영화코드
	
	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";	
	
	// KOBIS 오픈 API SOAP Client를 통해 호출
	MovieInfoResult movieInfoResult = new MovieAPIServiceImplService().getMovieAPIServiceImplPort().searchMovieInfo(key, movieCd);
	request.setAttribute("movieInfoResult",movieInfoResult);	
	
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
			<td>영화명</td><td>영화명(영)</td><td>상영시간</td><td>제작년도</td><td>개봉일</td><td>영화형태</td>
			<td>장르</td><td>제작국가</td><td>감독</td><td>배우</td><td>제작사</td>
		</tr>
	<c:if test="${not empty movieInfoResult.movieInfo}">	
		<tr>
			<td><c:out value="${movieInfoResult.movieInfo.movieNm}"/></td><td><c:out value="${movieInfoResult.movieInfo.movieNmEn }"/></td>
			<td><c:out value="${movieInfoResult.movieInfo.showTm }"/><td><c:out value="${movieInfoResult.movieInfo.prdtYear }"/></td>
			<td><c:out value="${movieInfoResult.movieInfo.openDt }"/></td><td><c:out value="${movieInfoResult.movieInfo.typeNm}"/></td>			
			<td><c:forEach items="${movieInfoResult.movieInfo.genres.genre}" var="genre"><c:out value="${genre.genreNm}"/>,</c:forEach></td>
			<td><c:forEach items="${movieInfoResult.movieInfo.nations.nation}" var="nation"><c:out value="${nation.nationNm}"/>,</c:forEach></td>
			<td><c:forEach items="${movieInfoResult.movieInfo.directors.director}" var="director"><c:out value="${director.peopleNm}"/>,</c:forEach></td>			
			<td><c:forEach items="${movieInfoResult.movieInfo.actors.actor}" var="actor"><c:out value="${actor.peopleNm}"/>,</c:forEach></td>
			<td><c:forEach items="${movieInfoResult.movieInfo.companys.company}" var="company"><c:out value="${company.companyNm}"/>,</c:forEach></td>
		</tr>	
	</c:if>
	</table>	
</body>
</html>