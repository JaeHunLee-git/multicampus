package com.sds.mvcproject.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*모든 요청을 1차적으로 처리하는 대표 컨트롤러
 
 javaEE 컨트롤러 업무 처리 절차 
 1) 요청을 받는다 ( 대표 컨트롤러 ) 
 2) 요청을 분석한다 ( 대표 컨트롤러 ) 
 3) 알맞는 모델에 일 시킨다 ( 하위 컨트롤러 )
 4) 결과View로 가져갈 것이 있다면 결과 저장(하위 컨트롤러)
 5) 결과를 보여줄 적절한 뷰를 선택한다 (누구?)
 * */ 
public class DispatcherServlet extends HttpServlet{
	
	//실행중인 프로그램이 텍스트파일을 읽어야 들어야 하므로, 파일입력스트림을 사용하자 
	//읽어들인 데이터를 대상으로  key, value를 구분할 수 있는 능력이 있는 자바의 객체를
	//사용해본다(java.util 패키지에 들어있는 Properties 를 이용해본다 - Map의 자식)
	FileInputStream fis=null;
	Properties props=null;
	
	//서블릿이 요청을 분석할때 필요한 파일이 이미 로드되어 있어야 하므로, 서블릿의 인스턴스 생성시 
	//서블릿의 초기화를 담당하는  init에서 파일을 로드해보자 
	public void init(ServletConfig config) throws ServletException {
		//jsp에서의 application 내장 객체는 서블릿 API 에서 ServletContext 라는 자료형이다 
		ServletContext application=config.getServletContext();
		
		//web.xml의 init-param 태그에 명시된 파라미터명을 이용하여 그 값을 얻어오자
		//이 서블릿이 탄생 시점에 호출되는 init()생명주기 메서드에서..
		String contextConfigLocation=config.getInitParameter("contextConfigLocation");
		
		String realPath = application.getRealPath(contextConfigLocation);
		System.out.println("어플리케이션 내의 지정한 디렉토리의 실제 경로는 "+realPath);
		
		
		try {
			fis = new FileInputStream(realPath); //스트림 생성(이 시점부터 읽을 수 있다)
			props = new Properties(); 
			//프로퍼티스 객체는 스트림 객체를 통해 파일의 정보를 사용할 수 있다. 
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//요청을 받으려면, doXXX 형 메서드를 재정의 해야 하는데, 이때 클라이언트가 post,get 중 어떤
	//방식으로 요청을 시도할지 알수 없으므로, 모든 요청 방식에 대비해놓자!! 
	//해결책) 하나의 메서드로 몰아서 처리.. 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}
	
	protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//블로그 글쓰기, 목록요청, 삭제, 수정, 카페글요청, 메일, 회원가입, 로그인...
		//수많은 클라이언트의 요청을 분석하는 도구로 if 조건문을 사용하지 말자!! 왜?? 
		//만일 그럴경우, if문이 너무나 많이 나열되며, 요청이 추가되면 이 클래스를 열어서 if문을 추가하고
		//컴파일까지 진행해야 한다..해결책? 자바 코드에서 요청을 처리할 하위 컨트롤러를 매핑하지말고, 
		//외부의 구조화된 설정파일 들을 이용한다( xml, json, properties 파일 등은 조건문을 대신할 수
		//있는 Text 기반의 데이터베이스이다)
		
		//클라이언트의 요청을 검색어로 사용하여 매핑파일에서 key값을 이용한 value를 추출해보자 
		String uri = request.getRequestURI();
		System.out.println("클라이언트의 요청 uri는 "+uri);
		
		String value = props.getProperty(uri); //uri를 key로 사용한, value값 접근
		System.out.println(uri+"와 매핑되는 value값은 "+value);
		
		//value로 반환된 하위 컨트롤러의 경로는 실제 클래스가 아닌 단순한 문자열에 불과하다..
		//따라서 실제 클래스를 Load도 하고 인스턴스도 생성하자 
		try {
			Class controllerClass = Class.forName(value);//문자열로 지정한 클래스를 로드
			//인스턴스 1개 만들기(new 연산자만 인스턴스를 만들 수 있는 것이 아니라, Class 클래스에는
			
			
			//인스턴스를 생성시켜주는 메서드를 지원한다)
			Controller controller=(Controller)controllerClass.newInstance();
			
			//분석 후 하위 컨트롤러에게 요청 전달 (3단계, 4단계는 하위 컨트롤러가 담당 ) 
			controller.execute(request, response);
			
			//5단계(포워딩 하거나, 리다이렉트 하거나)
			//어떤 뷰이름을 통해서, jsp나 url 링크를 사용할지 하위 컨트롤들에게 물어보자 
			String viewPage = props.getProperty(controller.getViewName());
			System.out.println(uri+" 요청에 대해 보여줄 결과 URL은 "+viewPage);
			
			if(controller.isForward()){
				RequestDispatcher dis=request.getRequestDispatcher(viewPage);//포워딩할 주소
				dis.forward(request, response); //뷰 페이지로 포워딩
			}else {
				response.sendRedirect(viewPage);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	//서블릿이 소멸될때, 생성된 스트림도 함께 제거하자 
	@Override
	public void destroy() {
		if(fis !=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}








