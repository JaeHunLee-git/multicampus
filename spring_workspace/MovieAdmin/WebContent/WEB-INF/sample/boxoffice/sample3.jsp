<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeResultVO"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.boxoffice.BoxOfficeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.boxoffice.DailyBoxOfficeResult"%>
<%@page import="java.util.List"%>
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
* @설명 : 일별 박스오피스 SOAP 방식 예제
------------------------------------------------------------
-->
    <%
    // 파라메터 설정
	String targetDt = request.getParameter("targetDt")==null?"20120101":request.getParameter("targetDt");			//조회일자
	String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");		//결과row수
	String multiMovieYn = request.getParameter("multiMovieYn")==null?"":request.getParameter("multiMovieYn");		//“Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
	String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");			//“K: : 한국영화 “F” : 외국영화 (default : 전체)
	String wideAreaCd = request.getParameter("wideAreaCd")==null?"":request.getParameter("wideAreaCd");				//“0105000000” 로서 조회된 지역코드

	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";
	// KOBIS 오픈 API SOAP Client를 통해 호출
	DailyBoxOfficeResult dailyBoxOfficeResult = new BoxOfficeAPIServiceImplService().getBoxOfficeAPIServiceImplPort().searchDailyBoxOfficeList(key,targetDt,itemPerPage,multiMovieYn,repNationCd,wideAreaCd);
	
	request.setAttribute("dailyResult",dailyBoxOfficeResult);

	// KOBIS 오픈 API SOAP Client를 통해 코드 서비스 호출
	List<CodeResultVO>  codeList = new CodeAPIServiceImplService().getCodeAPIServiceImplPort().searchCodeList(key,"0105000000");
	request.setAttribute("codeList",codeList);
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
</head>
<body>
	<c:out value="${dailyResult.boxofficeType}"/>
	<c:out value="${dailyResult.showRange}"/><br/>
	<table border="1">
		<tr>
			<td>순위</td><td>영화명</td><td>개봉일</td><td>매출액</td><td>매출액점유율</td><td>매출액증감(전일대비)</td>
			<td>누적매출액</td><td>관객수</td><td>관객수증감(전일대비)</td><td>누적관객수</td><td>스크린수</td><td>상영횟수</td>
		</tr>
	<c:if test="${not empty dailyResult.dailyBoxOfficeList.dailyBoxOffice}">
	<c:forEach items="${dailyResult.dailyBoxOfficeList.dailyBoxOffice}" var="boxoffice">
		<tr>
			<td><c:out value="${boxoffice.rank }"/></td><td><c:out value="${boxoffice.movieNm }"/></td><td><c:out value="${boxoffice.openDt }"/></td><td><c:out value="${boxoffice.salesAmt }"/></td>
			<td><c:out value="${boxoffice.salesShare }"/></td><td><c:out value="${boxoffice.salesInten }"/>/<c:out value="${boxoffice.salesChange }"/></td><td><c:out value="${boxoffice.salesAcc }"/></td><td><c:out value="${boxoffice.audiCnt }"/></td>
			<td><c:out value="${boxoffice.audiInten }"/>/<c:out value="${boxoffice.audiChange }"/></td><td><c:out value="${boxoffice.audiAcc }"/></td><td><c:out value="${boxoffice.scrnCnt }"/></td>
			<td><c:out value="${boxoffice.showCnt }"/></td>
		</tr>
	</c:forEach>
	</c:if>
	</table>
	<form action="">
		일자:<input type="text" name="targetDt" value="<%=targetDt %>">
		최대 출력갯수:<input type="text" name="itemPerPage" value="<%=itemPerPage %>">
		영화구분:<select name="multiMovieYn">
			<option value="">-전체-</option>
			<option value="Y"<c:if test="${param.multiMovieYn eq 'Y'}"> selected="seleted"</c:if>>다양성영화</option>
			<option value="N"<c:if test="${param.multiMovieYn eq 'N'}"> selected="seleted"</c:if>>상업영화</option>
		</select>
		국적:<select name="repNationCd">
			<option value="">-전체-</option>
			<option value="K"<c:if test="${param.repNationCd eq 'K'}"> selected="seleted"</c:if>>한국</option>
			<option value="F"<c:if test="${param.repNationCd eq 'F'}"> selected="seleted"</c:if>>외국</option>
			</select>
		지역:<select name="wideAreaCd">
			<option value="">-전체-</option>
			<c:forEach items="${codeList}" var="code">
			<option value="<c:out value="${code.fullCd}"/>"<c:if test="${param.wideAreaCd eq code.fullCd}"> selected="seleted"</c:if>><c:out value="${code.korNm}"/></option>
			</c:forEach>
			</select>
			<br/>
			<input type="submit" name="" value="조회">
	</form>
</body>
</html>