package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 삭제 요청을 처리하는 서블릿
//직접 db를 삭제하지 않고, DAO에게 시킨다..
public class DeleteServlet extends HttpServlet{
	
	NewsDAO newsDAO;
		
	public DeleteServlet() {
		newsDAO = new NewsDAO();
	}
	
	//파라미터가 한개밖에 없고, get방식으로 전달되고 있으므로  doGet() 재정의 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기 , 한글 등의 데이터가 전달되어 오는것이 아니므로, 인코딩은 하지 않아도 됨 
		String news_idx = request.getParameter("news_idx");
		System.out.println("삭제할 news_idx 는 "+news_idx);
		
		//DAO 에게 db 삭제 시키자 
		int result = newsDAO.delete(Integer.parseInt(news_idx));
		
		out.print("<script>");
		if(result >0){
			out.print("alert('삭제 성공');");
			out.print("location.href='/news/list.jsp';");
		}else {
			out.print("alert('삭제 실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
	
}







