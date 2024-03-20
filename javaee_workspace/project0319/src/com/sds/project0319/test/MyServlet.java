package com.sds.project0319.test;

import javax.servlet.http.HttpServlet; //jsp��� �������� ��û�� ó���ϴ� ���� Ŭ���� 
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest; // ��û ��ü 
import javax.servlet.http.HttpServletResponse; //���� ��ü 
import java.io.PrintWriter;

//�� Ŭ������ ���� pc�� �ƴ϶�, ���� ������������ ���� �� �� �ִ� 
//Ŭ�����̸�, �̷��� ������ javaEE����� Ŭ������ ������ ����(Servlet)�̶� �Ѵ�
public class MyServlet extends HttpServlet{
	//�� Ŭ������ Ŭ���̾�Ʈ�� ���������� ��û�� �õ��Ҷ�, ���۵Ǵ� Ŭ�����̸�, 
	//������ jsp�� �ϴ� ������ �״�� �����Ѵ� 


	//�� �޼���� HttpServlet�� ������ doXXX�� �޼��� �� �ϳ��ν�, Ŭ���̾�Ʈ�� 
	//get������� ��û�� �õ��ϸ� �����ϴ� �޼����̴�..�׸��� ������ throws�� 
	//try~catch���� �����ڰ� ó���ϰ� ����������, �� �޼��� ȣ���ڿ���  ����ó����
	//����(å������)��Ű�� ����̴�..
	//���� �Ʒ��� �޼���� Ŭ���̾�Ʈ�� ��û�� ó���ؾ� �ϹǷ�,  jsp������ request�� response
	//�Ʒ��� �޼��忡���� �����ϰ� �ʿ��ϰ� �ȴ�..���� �Ű������� �־����� 
	//jsp������ request ���尴ü�� �ڷ��� : HttpServletRequest ����.
	//jsp������ response ���尴ü�� �ڷ��� : HttpServletResponse ����.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Ŭ���̾�Ʈ�� ���������� ����� �޽��� ��� 
		//������ Ŭ����������� �����ڰ� ������ �ڵ带 �ۼ��ؾ� �ϹǷ�, ���尴ü ������ ��������
		//�ʴ´�!!
			
		//jsp ������ page  ���ÿ������� ��õ�  contentType�� charset�� ���⼭�� �����ؾ���
		response.setContentType("text/html"); // contentType="text/html;"
		response.setCharacterEncoding("utf-8"); //contentType=";charset=utf-8"

		//���� ��ü�� ������ ��� ��Ʈ���� ���
		PrintWriter out=response.getWriter();	

		//���䰴ü�� ������ ��� ��Ʈ���� ���ڿ��� �������ѳ���!
		out.print("���� ù Servlet<br> success !!");
	}

	//�� �޼���� Ŭ���̾�Ʈ�� post ������� ��û�� �õ��Ҷ� ������ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html"); //jsp�� contentType="text/html;" ����
		response.setCharacterEncoding("utf-8"); //jsp�� contentType=";charset=utf-8" ����
		
		//�Ķ������ ���ڵ� ó�� 
		request.setCharacterEncoding("utf-8");

		//Ŭ���̾�Ʈ�� ������ �Ķ���͸� �޾ƺ��� 
		String title = request.getParameter("title");
		
		PrintWriter out = response.getWriter(); //response ��ü�κ��� ��Ʈ�� ���			
		out.print("����� ������ ������ "+title); //���⼭ ���������� ����� �߻��ϴ°� �ƴ϶�
												//���� response ��ü�� ������ ��� ��Ʈ���� , ���� 
												//������ ������ html �������� ���� ���ڿ��� ������ ����
	}
}
