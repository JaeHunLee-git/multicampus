<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeResultVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.movie.MovieAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collection"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.util.JSONBuilder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
------------------------------------------------------------
* @설명 : 영화코드 조회 SOAP 예제
------------------------------------------------------------
-->
    <%
    // 파라메터 설정
	String curPage = request.getParameter("curPage")==null?"1":request.getParameter("curPage");					//현재페이지
	String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");		//결과row수
	String movieNm = request.getParameter("movieNm")==null?"":request.getParameter("movieNm");						//영화명
	String directorNm = request.getParameter("directorNm")==null?"":request.getParameter("directorNm");				//감독명
	String openStartDt = request.getParameter("openStartDt")==null?"":request.getParameter("openStartDt");			//개봉연도 시작조건 ( YYYY )
	String openEndDt = request.getParameter("openEndDt")==null?"":request.getParameter("openEndDt");				//개봉연도 끝조건 ( YYYY )	
	String prdtStartYear = request.getParameter("prdtStartYear")==null?"":request.getParameter("prdtStartYear");	//제작연도 시작조건 ( YYYY )
	String prdtEndYear = request.getParameter("prdtEndYear")==null?"":request.getParameter("prdtEndYear");			//제작연도 끝조건    ( YYYY )
	String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");			//대표국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
	String[] movieTypeCdArr = request.getParameterValues("movieTypeCdArr")==null? null:request.getParameterValues("movieTypeCdArr");	//영화형태코드 배열 (공통코드서비스에서 '2201'로 조회된 영화형태코드)

    %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/KobisOpenAPIRestService.js"></script>
<script type="text/javascript">
$(function(){
	var kobisService = new KobisOpenAPIRestService("430156241533f1d058c603178cc3ca0e");
	var resJson = null;
	try{
		resJson = kobisService.getMovieList(true,{curPage:"<%=curPage%>",itemPerPage:"<%=itemPerPage%>",movieNm:"<%=movieNm%>",directorNm:"<%=directorNm%>",openStartDt:"<%=openStartDt%>",openEndDt:"<%=openEndDt%>",prdtStartYear:"<%=prdtStartYear%>",prdtEndYear:"<%=prdtEndYear%>",repNationCd:"<%=repNationCd%>",movieTypeCdArr:"<%=movieTypeCdArr%>"});
	}catch(e){
		resJson = $.parseJSON(e.message);
	}
	if(resJson.failInfo){
		alert(resJson.failInfo.message);
	}else{
		var movieList = resJson.movieListResult.movieList;
		var totCnt = resJson.movieListResult.totCnt;
		
		for(var i=0;i<movieList.length;i++){
			var movie = movieList[i];
			
			var appendStr = "<tr><td>"+movie.movieNm+"</td><td>"+movie.movieNmEn+"</td><td>"+movie.prdtYear+"</td><td>"+movie.openDt+"</td><td>"+movie.repNationNm+"</td>";
			
			//감독
			appendStr += "<td>";			
			if(movie.directors != null && movie.directors != ""){
				for(var dir in movie.directors){
					appendStr += movie.directors[dir].peopleNm;
				}				
			}
			appendStr += "</td>";
			
			//영화사
			appendStr += "<td>";		
			if(movie.companys != null && movie.companys != ""){
				for(var com in movie.companys){
					appendStr += movie.companys[com].companyNm;
				}				
			}
			appendStr += "</td></tr>";
			
									
			$("#titleInfo p:eq(0)").html(totCnt);			
			$("#boxtab tbody").append(appendStr);
		}
	}
	
});
</script>
</head>
<body>
	<div id="titleInfo">
		<p></p>
		<p></p>	
	</div>
	<table border="1" id="boxtab">
		<tbody>
		<tr>
			<td>영화명</td><td>영화명(영)</td><td>제작연도</td><td>개봉연도</td><td>제작국가</td><td>감독</td>
			<td>참여영화사</td>
		</tr>
		</tbody>
	</table>
	<form action="">
		현재페이지 :<input type="text" name="curPage" value="<%=curPage %>">
		최대 출력갯수:<input type="text" name="itemPerPage" value="<%=itemPerPage %>">
		감독명:<input type="text" name="directorNm" value="<%=directorNm %>">		
		영화명:<input type="text" name="movieNm" value="<%=movieNm %>"> <br/>
		개봉연도조건:<input type="text" name="openStartDt" value="<%=openStartDt %>"> ~ <input type="text" name="openEndDt" value="<%=openEndDt %>">
		제작연도조건:<input type="text" name="prdtStartYear" value="<%=prdtStartYear %>"> ~ <input type="text" name="prdtEndYear" value="<%=prdtEndYear %>">		

		국적:<select name="repNationCd">
			<option value="">-전체-</option>
			<c:forEach items="${nationCd.codes}" var="code">
			<option value="<c:out value="${code.fullCd}"/>"<c:if test="${param.repNationCd eq code.fullCd}"> selected="seleted"</c:if>><c:out value="${code.korNm}"/></option>
			</c:forEach>
			</select><br/>
		영화형태:
			<c:forEach items="${movieTypeCd.codes}" var="code" varStatus="status">
			<input type="checkbox" name="movieTypeCdArr" value="<c:out value="${code.fullCd}"/>" id="movieTpCd_<c:out value="${code.fullCd}"/>"/> <label for="movieTpCd_<c:out value="${code.fullCd}"/>"><c:out value="${code.korNm}"/></label>
			<c:if test="${status.count %4 eq 0}"><br/></c:if>
			</c:forEach>
			<br/>
		<input type="submit" name="" value="조회">
	</form>
</body>
</html>