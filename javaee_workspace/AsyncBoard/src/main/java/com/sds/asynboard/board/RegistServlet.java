package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//글쓰기 요청을 처리하는 서블릿 
public class RegistServlet extends HttpServlet{
	BoardDAO boardDAO=new BoardDAO();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println("title is "+title);
		System.out.println("writer is "+writer);
		System.out.println("content is "+content);
		
		//파라미터를 모아 전달하기 위해 DTO에 담기 
		Board board = new Board(); //empty 상태
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//DAO 에게 등록을 시킨다 
		int result = boardDAO.insert(board);
		
		if(result >0) {
			out.print("ok");
		}else {
			out.print("fail");
		}
		
	}
}











