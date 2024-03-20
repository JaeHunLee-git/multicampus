package com.sds.project0319.notice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

//�۾��� ��û�� ó���ϴ� ���� Ŭ���� ����
public class RegistServlet extends HttpServlet{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";
	
	//�۾��� ��û�� post ������� ���۵ǹǷ�, ���� �θ� ���� �޼����� doXXXX�� 
	// �� doPost() �� ó������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		//<%@ page contentType="text/html;charset=utf-8"%>
		response.setContentType("text/html;charset=utf-8"); //��������� ǥ���� ����
		//response.setCharacterEncoding("utf-8");

		//Ŭ���̾�Ʈ�� ������ �Ķ���� 3�� �ޱ� (�ѱ۱����� �ʰ� )
		request.setCharacterEncoding("utf-8");

		//�Ķ���� �ޱ� 
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		PrintWriter out =response.getWriter(); //���� ��ü�� ������ ��½�Ʈ�� ������ 
		out.print("����� ������ ������ "+title+"<br>");

		//����Ŭ�� insert 

		Connection con=null;
		PreparedStatement pstmt=null;

		//����̹� �ε� 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("����̹� �ε� ����");

			con = DriverManager.getConnection(url, user, pass);

			if(con !=null){
				out.print("���� ����");

				String sql="insert into notice(notice_idx, title, writer,content)";
				sql+=" values(seq_notice.nextval,? ,?, ?)";
				
				pstmt = con.prepareStatement(sql);//���� ��ü ����
				pstmt.setString(1, title); //ù��° ����ǥ�� ����
				pstmt.setString(2, writer); //�ι�° ����ǥ�� �ۼ���
				pstmt.setString(3, content); //����° ����ǥ�� ���� 

				int result = pstmt.executeUpdate(); //�������� �� row count ��ȯ 
				
				out.print("<script>");
				if(result > 0){
					out.print("alert('��ϼ���');");
					out.print("location.href='/notice/list';"); //Ŭ���̾�Ʈ�� �������� �Ͽ��� 
																			//������ url�� �ٽ� ������ ������
				}else{
					out.print("alert('��Ͻ���');");
					out.print("history.back();");
				}							
				out.print("</script>");
	
			}else{
				out.print("���� ����");
			}
		}catch(ClassNotFoundException e){
			out.print("����̹� �ε� ����");
		}catch(SQLException e){
			e.printStackTrace(); //���� ������ �α׿� ���� ���� ��� 
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
