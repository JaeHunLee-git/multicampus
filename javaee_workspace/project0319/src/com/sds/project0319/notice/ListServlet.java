package com.sds.project0319.notice;


import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

//�����̶�? ���� ���������� ����� �� �ִ� Ŭ���� 

public class ListServlet extends HttpServlet{
	//��� ��û�� ó���ϴ� �����̹Ƿ�, get����� ��û�� ó������ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�Խ��� ��� ��û�̹Ƿ�, ����Ŭ�� �������� 
	
		/*
		JSP�� ���� �Ѵ� �������� �����ϴ�. �� ���������� ����� �� �ִ� ������̴�...
		������, ������ ���� Ŭ������ �ڵ带 �ۼ��ϴ� ����, �������� ǥ���Ҷ� ��� �͵��� ���ڿ� ó���� �ؾ� 
		�ϹǷ�, ȿ������ �ſ� ��������...
		�ذ�å) jsp�� ���� �� ���� ������ ��������� ���� �Ǵ� ������ �ᱹ..������(View)�� ���� �ڵ���
				jsp �켼�ϰ�, �װ� �ƴ϶�� �������� ó���Ѵ�..(������ �׷���..)
		*/	
		PrintWriter out =  response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<head>");
		out.print("<body>");
		out.print("<table width=\"100%\" border=\"1px\">");
		out.print("<tr>");
		out.print("<td>No</td");
		out.print("<td>Title</td");
		out.print("<td>Writer</td");
		out.print("<td>regdate</td");
		out.print("</tr>");
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");	
	}
}	
