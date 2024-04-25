<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.company.CompanyAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.company.CompanyInfoResult"%>
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
	String companyCd = request.getParameter("companyCd")==null?"20122497":request.getParameter("companyCd");						//영화코드
	
	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";	
	
	// KOBIS 오픈 API SOAP Client를 통해 호출
	CompanyInfoResult companyInfoResult = new CompanyAPIServiceImplService().getCompanyAPIServiceImplPort().searchCompanyInfo(key, companyCd);
	request.setAttribute("companyInfoResult",companyInfoResult);	
	
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
			<td>영화사코드</td><td>영화사명</td><td>영화사명(영)</td><td>대표자명</td><td>영화사분류</td><td>필모리스트</td>			
		</tr>
	<c:if test="${not empty companyInfoResult.companyInfo}">	
		<tr>
			<td><c:out value="${companyInfoResult.companyInfo.companyCd}"/></td><td><c:out value="${companyInfoResult.companyInfo.companyNm}"/></td>
			<td><c:out value="${companyInfoResult.companyInfo.companyNmEn }"/></td><td><c:out value="${companyInfoResult.companyInfo.ceoNm }"/></td>
			<td><c:forEach items="${companyInfoResult.companyInfo.parts.part}" var="part"><c:out value="${part.companyPartNm}"/>,</c:forEach></td>
			<td><c:forEach items="${companyInfoResult.companyInfo.filmos.filmo}" var="filmo">(<c:out value="${filmo.movieCd}"/>)<c:out value="${filmo.movieNm}"/>-<c:out value="${filmo.companyPartNm}"/>,</c:forEach></td>
		</tr>	
	</c:if>
	</table>	
</body>
</html>