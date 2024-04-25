<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeAPIServiceImplService"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.comm.CodeResultVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.kobis.kobisopenapi.consumer.soap.people.PeopleAPIServiceImplService"%>
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
	String peopleNm = request.getParameter("peopleNm")==null?"":request.getParameter("peopleNm");				//영화인명
	String filmoNames = request.getParameter("filmoNames")==null?"":request.getParameter("filmoNames");				//영화인 대표명
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
		resJson = kobisService.getPeopleList(true,{curPage:"<%=curPage%>",itemPerPage:"<%=itemPerPage%>",peopleNm:"<%=peopleNm%>",filmoNames:"<%=filmoNames%>"});
	}catch(e){
		resJson = $.parseJSON(e.message);
	}
	if(resJson.failInfo){
		alert(resJson.failInfo.message);
	}else{
		var peopleList = resJson.peopleListResult.peopleList;
		var totCnt = resJson.peopleListResult.totCnt;
		
		for(var i=0;i<peopleList.length;i++){
			var people = peopleList[i];
			
			var appendStr = "<tr><td>"+people.peopleCd+"</td><td>"+people.peopleNm+"</td><td>"+people.peopleNmEn+"</td><td>"+people.repRoleNm+"</td><td>"+people.filmoNames+"</td>";		
			appendStr += "</tr>";			

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
			<td>영화인코드</td><td>영화인명</td><td>영화인명(영문)</td><td>분야</td><td>필모리스트</td>
		</tr>
		</tbody>
	</table>
	<form action="">
		현재페이지 :<input type="text" name="curPage" value="<%=curPage %>">
		최대 출력갯수:<input type="text" name="itemPerPage" value="<%=itemPerPage %>">
		영화인명:<input type="text" name="peopleNm" value="<%=peopleNm %>">		
		필모리스트:<input type="text" name="filmoNames" value="<%=filmoNames %>"> <br/>
		
		<input type="submit" name="" value="조회">
	</form>
</body>
</html>