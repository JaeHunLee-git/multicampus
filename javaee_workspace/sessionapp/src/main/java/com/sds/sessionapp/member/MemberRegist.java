package com.sds.sessionapp.member;

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

import com.sds.sessionapp.common.EncryptionManager;
import com.sds.sessionapp.common.MailService;


public class MemberRegist extends HttpServlet{
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String password="1234";
	MailService mailService=new MailService();
	
	//회원가입 폼으로부터 전송되는 파라미터들은 Post 방식으로 요청이 되므로, doXXX형 메서드 중 doPost()
	//로 처리하자 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp의 page 지시영역 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); //응답 객체가 보유한 출력스트림을 얻기 
		
		System.out.println("요청 감지"); //콘솔에 출력 
		
		
		//클라이언트가 전송한 입력 양식의 파라미터들 받기
		request.setCharacterEncoding("utf-8"); //파라미터의 인코딩 처리(한글 안깨지게)
		
		String id 			= request.getParameter("id"); //아이디
		String pass 		= request.getParameter("pass"); //비밀번호
		String name 		= request.getParameter("name"); //이름 
		String email 		= request.getParameter("email"); //이메일 
		String receive 	= request.getParameter("receive"); //광고 메일 수신 여부
		
		System.out.println("id = "+id);
		System.out.println("pass = "+pass);
		System.out.println("name = "+name);
		System.out.println("email = "+email);
		System.out.println("receive = "+receive);
		
		String[] skillArray = request.getParameterValues("skill");
		
		for(String skill  : skillArray) {
			System.out.println(skill);
		}
		
		//비밀번호에 대한 암호화 해시값 처리 
		pass = EncryptionManager.getHashFromText(pass);
		
		//오라클에 넣기 
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName(driver); //드라이버 로드 
			con = DriverManager.getConnection(url, user, password); //접속
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				//쿼리 실행 
				String sql="insert into member(member_idx, id, pass, name,email, receive)";
				sql +=" values(seq_member.nextval, ?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);//쿼리수행 객체 생성 
				
				//쿼리 수행 전 바인드 변수 값 지정
				pstmt.setString(1, id);
				pstmt.setString(2, pass);
				pstmt.setString(3, name);
				pstmt.setString(4, email);
				pstmt.setString(5, receive);
				
				int result = pstmt.executeUpdate(); //DML 수행 후 record count 반환  insert 는 1
				
				out.print("<script>");
				if(result>0) {
					out.print("alert('회원가입 성공');");
					out.print("location.href='/member/login.jsp';"); //로그인 폼으로 재접속을 유도하자
					
					//이메일 발송  
					mailService.send(name, email);
					
				}else {
					out.print("alert('회원가입 실패');");
					out.print("history.back();"); //다시 회원가입 폼으로 돌아감
				}
				out.print("</script>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		
		
		
		//회원가입 축하 메일 발송 
	}
}




