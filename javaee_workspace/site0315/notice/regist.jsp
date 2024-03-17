<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>

<%!
	//jsp 페이지의 멤버영역 
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
%>
<%

	/*
	클라이언트 측에서 전송한 제목, 작성자, 내용을 네트워크로 전송 받아서, 
	오라클에 대신 넣어주자 
	클라이언트가 요청을 시도할때, 서버측에서는 해당 요청을 처리하기 위해 무조건 생성되는 
	내장객체 2개가 있다.
	1) request  객체 : 클라이언트의 요청 정보가 들어있슴..(특히 전송된 데이터를 추출할때 유용)
	2) response 객체 : 클라이언트에게 응답할 정보를 가진 객체 (추후 응답시 html 재구성할때)
	*/
	
	//클라이언트가 전송한 데이터 받기 (파라미터(변수) 값 받기 )
	//request 객체를 이용하여, 전송된 파라미터에 대한 인코딩 처리 
	request.setCharacterEncoding("utf-8"); //주의할점: 파라미터 받기 전에 세팅

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	//서버에 제대로 도착했는지 체크해본다
	out.print("전송된 제목은 "+title+"<br>");
	out.print("전송된 작성자는 "+writer+"<br>");
	out.print("전송된 내용은 "+content+"<br>");

	//전송되어 온 파라미터를 이용하여  SQL 준비한 뒤  쿼리 실행
	//WEB-INF 는 절대 외부에서 접근이 불가능 따라서 아래와 같은 접근 불가 
	// http://localhost:8888/WEB-INF/lib/ojbc6.jar (불가)
	//보안 처리된 디렉토리 이므로, 주로 .class 나 .jar를 넣는다..
	//Tomcat은 server.xml, 새로운 class 추가, .jar 추가 되면 재가동해야 함
	Class.forName("oracle.jdbc.driver.OracleDriver");

	out.print("드라이버 로드 성공");

	//오라클 접속 
	Connection con=null;
	PreparedStatement pstmt=null;

	con = DriverManager.getConnection(url, user, pass); //접속 시도

	if(con==null){
		out.print("접속 실패");
	}else{
		out.print("접속 성공");

		String sql="insert into notice(notice_idx, title, writer, content)";
		sql +=" values(seq_notice.nextval, '"+title+"' , '"+writer+"' ,'"+content+"')";

		pstmt=con.prepareStatement(sql); //쿼리수행 객체 생성

		//쿼리실행 DML executeUpdate() 수행 후,  insert 이므로, 성공시 반드시1 반환 0이면 실패
		int result = pstmt.executeUpdate();
		
		if(result<1){
			out.print("등록실패");
			//욕하고, 다시 뒤로 돌아감
		}else{
			out.print("등록성공");
			//리스트 보여주기 
			out.print("<script>");
			out.print("alert('등록성공');"); //out.print() 자바스크립트 출력시 반드시 세미콜론; 넣자
											//않넣으면 문장이 아직 종결되지 않은 것으로 판단한다..
			out.print("location.href='/notice/list.jsp';");
			out.print("</script>");
		}
	}

	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
%>