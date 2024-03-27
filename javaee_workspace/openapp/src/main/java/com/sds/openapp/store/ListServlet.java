package com.sds.openapp.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//맛집 목록 요청을 처리하는 서블릿 
public class ListServlet extends HttpServlet{
	//GET 방식 요청 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String perPage = request.getParameter("perPage");
		
		System.out.println("요청받았슴 "+perPage);
		
		//충청도 오픈api 서버에 HTTP 요청을 시도하자... 따라서 HttpURLConnection 사용해본다
		String serviceURL="http://apis.data.go.kr/6430000/cbRecreationalFoodInfoService/getRecreationalFoodInfo";
		String serviceKey="TPK6sq5VdCOFrijK99CmJHQCEVer9GwK4sxLvP6ED6dBExrBc6FO298QjQadJsw7C4sDZ8yBXJfsYZ/VT6LG0A==";
		
		StringBuilder sb = new StringBuilder();
		sb.append(serviceURL);
		sb.append("?serviceKey="+URLEncoder.encode(serviceKey, "UTF-8"));
		sb.append("&perPage="+URLEncoder.encode(perPage, "UTF-8"));
		
		URL url=new URL(sb.toString());
		URLConnection urlCon=url.openConnection();
		HttpURLConnection con=(HttpURLConnection)urlCon; //down casting
		
		//HTTP 헤더 구성 
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type","application/json");
		
		//요청에 대한 응답 가져오기
		int result = con.getResponseCode();
		
		InputStream is=null; //바이트 기반의 입력스트림
		InputStreamReader reader=null; //문자 기반의 입력스트림
		BufferedReader buffr=null; //버퍼 처리된 문자기반의 입력스트림
		
		if(result >=200 && result<=300) {
			//서버로부터  json  문자열 읽어들이기... 따라서 스트림이 필요함 
			System.out.println("Open API 서버로 부터 응답을 무사히 받음");
			
			is = con.getInputStream(); //바이트 기반의 입력 스트림 얻기
			reader = new InputStreamReader(is); //문자 기반으로 업그레이드(한 자씩 읽어들임. 효율X) 
			buffr  =new BufferedReader(reader); //버퍼 스트림으로 업그레이드 (한 줄씩 읽어들임.효율O)
			
			//한줄씩 모두 읽어들여보자
			String str=null;
			while(true) {
				str=buffr.readLine();
				if(str ==null)break; //루프 빠져나오기
				System.out.println(str); //우리가 보기 위함이고.. 응답 정보가 가진 PrintWriter에 
				//json 문자열을 차곡 차곡 쌓아놓자... 
				out.print(str);
			}
		}
		if(is!=null)is.close();
		if(reader !=null)reader.close();
		if(buffr!=null)buffr.close();
		
		con.disconnect();
	}
}









