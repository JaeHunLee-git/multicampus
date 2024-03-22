package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet{ //서버에서만 실행될 수 있도록 서블릿으로 정의한다
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8"); //순서 매우 중요 
		//스트림을 얻기 전에 한글처리...
		
		PrintWriter out = response.getWriter();
		
		//클라이언트가 전송한 파라미터 받기 
		request.setCharacterEncoding("utf-8");
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		System.out.println("제목 "+title);
		System.out.println("작성자 "+writer);
		System.out.println("내용 "+content);
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName(driver);//드라이버 로드
			System.out.println("드라이버 로드");
			
			con = DriverManager.getConnection(url, user, pass);
			
			StringBuffer sb = new StringBuffer();
			
			if(con !=null) {
				//오라클에서는 바인드 변수 사용하려면 :변수명 이지만, jdbc코드에서는 ?물음표로 처리한다..
				//바인드 변수의 사용목적은 db 성능 개선 및 향상
				sb.append("insert into news(news_idx, title, writer, content)");
				sb.append(" values(seq_news.nextval, ?,?,?)"); 
				
				pstmt = con.prepareStatement(sb.toString()); //쿼리 객체 생성
				pstmt.setString(1, title);
				pstmt.setString(2, writer);
				pstmt.setString(3, content);
				
				int result = pstmt.executeUpdate(); //쿼리실행
				
				out.print("<script>");
				if(result >0){
					out.print("alert('등록 성공');");
					out.print("location.href='/news/list.jsp';");
				}else {
					out.print("alert('등록 실패');");
					out.print("history.back();");
				}
				out.print("</script>");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
}






