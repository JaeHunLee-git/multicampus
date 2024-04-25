<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Arrays"%>
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
	String companyNm = request.getParameter("companyNm")==null?"":request.getParameter("companyNm");				//영화사명
	String ceoNm = request.getParameter("ceoNm")==null?"":request.getParameter("ceoNm");				//영화사 대표명
	String[] companyPartCdArr = request.getParameterValues("companyPartCdArr")==null? null:request.getParameterValues("companyPartCdArr"); //영화사분류	
	
	// 발급키
	String key = "430156241533f1d058c603178cc3ca0e";
	// KOBIS 오픈 API Rest Client를 통해 호출
    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

	// 영화사코드조회 서비스 호출 (boolean isJson, String curPage, String itemPerPage, String companyNm, String ceoNm, String companyPartCd)
    String companyCdResponse = service.getCompanyList(true, curPage, itemPerPage, companyNm, ceoNm, companyPartCdArr);
	// Json 라이브러리를 통해 Handling
	ObjectMapper mapper = new ObjectMapper();
	HashMap<String,Object> result = mapper.readValue(companyCdResponse, HashMap.class);

	request.setAttribute("result",result);
	
	// KOBIS 오픈 API Rest Client를 통해 코드 서비스 호출 (boolean isJson, String comCode )
	String companyPartCdResponse = service.getComCodeList(true,"2601");
	HashMap<String,Object> companyPartCd = mapper.readValue(companyPartCdResponse, HashMap.class);
	request.setAttribute("companyPartCd",companyPartCd);
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
<%
	String companyPartCds = "[";
	if(companyPartCdArr!=null){
		for(int i=0;i<companyPartCdArr.length;i++){
			companyPartCds += "'"+companyPartCdArr[i]+"'";
			if(i+1<companyPartCdArr.length){
				companyPartCds += ",";
			}
		}
		companyPartCds += "]";
%>

$(function(){
	var companyPartCd = <%=companyPartCds%>;
	$(companyPartCd).each(function(){
		$("input[name='companyPartCdArr'][value='"+this+"']").prop("checked",true);
	});
});

<%
	}
%>
</script>
</head>
<body>
	<c:out value="${result.companyListResult.totCnt}"/>
	<table border="1">
		<tr>
			<td>영화사코드</td><td>영화사명(영문)</td><td>영화사분류</td><td>대표자명</td><td>필모리스트</td>
		</tr>
	<c:if test="${not empty result.companyListResult.companyList}">
	<c:forEach items="${result.companyListResult.companyList}" var="company">
		<tr>
			<td><c:out value="${company.companyCd }"/></td><td><c:out value="${company.companyNm }"/></td><td><c:out value="${company.companyNmEn }"/></td><td><c:out value="${company.companyPartNames }"/></td>
			<td><c:out value="${company.ceoNm}"/></td><td><c:out value="${company.filmoNames}"/></td>						
		</tr>
	</c:forEach>
	</c:if>
	</table>
	<form action="">
		현재페이지 :<input type="text" name="curPage" value="<%=curPage %>">
		최대 출력갯수:<input type="text" name="itemPerPage" value="<%=itemPerPage %>">
		영화사명:<input type="text" name="companyNm" value="<%=companyNm %>">		
		대표자명:<input type="text" name="ceoNm" value="<%=ceoNm %>"> <br/>
		영화사분류:
			<c:forEach items="${companyPartCd.codes}" var="code" varStatus="status">
			<input type="checkbox" name="companyPartCdArr" value="<c:out value="${code.fullCd}"/>" id="companyTpCd_<c:out value="${code.fullCd}"/>"/> <label for="companyTpCd_<c:out value="${code.fullCd}"/>"><c:out value="${code.korNm}"/></label>
			<c:if test="${status.count %4 eq 0}"><br/></c:if>
			</c:forEach>
			<br/>
		<input type="submit" name="" value="조회">
	</form>
</body>
</html>