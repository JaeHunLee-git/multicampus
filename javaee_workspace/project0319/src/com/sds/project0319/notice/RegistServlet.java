package com.sds.project0319.notice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

//글쓰기 요청을 처리하는 서블릿 클래스 정의
public class RegistServlet extends HttpServlet{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
	
	//글쓰기 요청은 post 방식으로 전송되므로, 서블릿 부모가 가진 메서드인 doXXXX형 
	// 중 doPost() 로 처리하자
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//<%@ page contentType="text/html;charset=utf-8"%>
		response.setContentType("text/html;charset=utf-8"); //단축식으로 표현도 가능
		//response.setCharacterEncoding("utf-8");

		//클라이언트가 전송한 파라미터 3개 받기 (한글깨지지 않게 )
		request.setCharacterEncoding("utf-8");

		//파라미터 받기 
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		PrintWriter out =response.getWriter(); //응답 객체가 보유한 출력스트림 꺼내기 
		out.print("당신이 전송한 제목은 "+title+"<br>");

		//오라클에 insert 

		Connection con=null;
		PreparedStatement pstmt=null;

		//드라이버 로드 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("드라이버 로드 성공");

			con = DriverManager.getConnection(url, user, pass);

			if(con !=null){
				out.print("접속 성공");

				String sql="insert into notice(notice_idx, title, writer,content)";
				sql+=" values(seq_notice.nextval,? ,?, ?)";
				
				pstmt = con.prepareStatement(sql);//쿼리 객체 생성
				pstmt.setString(1, title); //첫번째 물음표는 제목
				pstmt.setString(2, writer); //두번째 물음표는 작성자
				pstmt.setString(3, content); //세번째 물음표는 내용 

				int result = pstmt.executeUpdate(); //쿼리실행 후 row count 반환 
				
				out.print("<script>");
				if(result > 0){
					out.print("alert('등록성공');");
					out.print("location.href='/notice/list';"); //클라이언트인 브라우저로 하여금 
																			//지정한 url로 다시 접속을 유도함
				}else{
					out.print("alert('등록실패');");
					out.print("history.back();");
				}							
				out.print("</script>");
	
			}else{
				out.print("접속 실패");
			}
		}catch(ClassNotFoundException e){
			out.print("드라이버 로드 실패");
		}catch(SQLException e){
			e.printStackTrace(); //톰켓 서버의 로그에 에러 원인 출력 
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
	} //doPost() 

}//class
