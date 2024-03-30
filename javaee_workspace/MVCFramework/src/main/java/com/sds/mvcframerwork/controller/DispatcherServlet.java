package com.sds.mvcframerwork.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모델2 방식 기반으로 어플리케이션을 구축할 경우, 모든 클라이언트의 요청마다 
//1:1 대응하는 컨트롤러를 매핑하게 되면,  web.xml의 매핑관리가 힘들어 진다..
//즉 유지보수성이 오히려 떨어진다..따라서 기존의 컨트롤러 보다 앞선 위치에 
//모든 요청을 하나의 진입점으로 몰아놓을 수 있는 대표 컨트롤러의 존재로 이 문제를 해결
//한다.. ex) 대기업의 서비스 센터의 업무와 동일하다
//이 클래스는 대형 어플리케이션의 모든~~~~요청을 혼자 받아야 하므로, 어떤 하위컨트롤러가
//업무를 담당해야 할지를 분석하는 등의 ..똑똑한 로직을 작성해야 함..
//참고로 수많은 클래스 명 중에서 DispatcherServlet 이라고 이름을 준 이유는, 
//스프링 창시자인 로드 존슨이 스프링 MVC에서, 이 객체명을 쓰기 때문에... 
public class DispatcherServlet extends HttpServlet{
	/*어떠한 분야의 프로그램을 개발하던지, 이 세상의 모든 컨트롤러 반드시 아래의 순서로 요청을 처리한다.
		[모든 컨트롤러의 일반적 요청 처리 절차]
		
	1) 요청을 받는다
	2) 요청을 분석한다 
	3) 알맞는 로직(모델) 객체에 일 시킨다 (하위 컨트롤러가)
	4) 일시킨 후 결과를 보여줄게 있다면 결과를 저장해놓는다 (하위 컨트롤러)
	5) 결과 페이지를 보여준다
	*/
	
	Properties props;
	FileInputStream fis; //Properties 객체는 파일 접근 능력이 없으므로, 스트림 객체를 의존함 
	
	//if문을 제거하기 위해, if문을 대신할 수 있는 외부의 설정 파일을 이용해본다.. 
	//매개변수로 전달되는 ServletConfig 객체는 서블릿과 관련된 환경정보를 가진 객체이다
	public void init(ServletConfig config) throws ServletException {
		props = new Properties(); //어떤 문자열 데이터가 key=value로 구성되어 있을경우 
												//key를 이용하여  value값을 접근할 수 있는 능력을 가진 객체
		try {
			//web.xml에 명시된 param 이름으로 접근
			String value = config.getInitParameter("contextConfigLocation");
			System.out.println(value);
			
			//아래의 스트림을 생성하기 위해서는 /WEB-INF/config.properties 경로만으로는 부족하다
			//따라서 플랫폼의 환경에 따른 풀경로를 조사하여, 스트림의 매개변수에 들어갈 경로를 만들어내자
			
			//ServletConfig 객체는 application 내장객체의 자료형인 ServletContext를 얻을 수 도 있다
			ServletContext context= config.getServletContext(); 
			String fullPath = context.getRealPath(value);
			System.out.println(fullPath);
			
			fis = new FileInputStream(fullPath);
			props.load(fis); //Properties 객체가 파일의 내용을 인식하는 시점임..
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//더블코딩이 일어나지 않는 방법???
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	//1단계: 요청을 받는다
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2단계: 요청을 분석한다 (클라이언트의 요청이 무엇인지 파악하여, 적절한 하위 컨트롤러에게 업무 전달)
		//System.out.println("클라이언트의 요청 감지");

		//클라이언트가 무엇을 원하는지 파악하려면, 클라이언트가 요청 시 사용한 URL 의 URI 를 분석하면 됨 
		// localhost:9999/blood.do  중  /blood.do 를  uri  본다 
		String uri = request.getRequestURI();
		System.out.println(uri +"로 요청을 시도했꾼요");
		
		//프로퍼티스 객체를 이용하여, 아래의 if문을 대신해보자 
		String className = props.getProperty(uri); //uri 변수에 들어있는 문자열로 검색을 하여, 그 값을 반환받음
		System.out.println(uri+" 요청에 동작을 수행할 하위 컨트롤러는 "+className);
		
		// className 변수는 실제 클래스가 아니라, 클래스의 경로를 나타내는 문자열일 뿐이므로 인스턴스화
		//시킬 수 없다!! 해결책 -  자바의 Class.forName() 메서드를 이용하면, 메서드의 매개변수로 
		//개발자가 static 영역으로 클래스를 Load 할 수 있다
		try {
			Class controllerClass=Class.forName(className.trim()); //클래스 동적 로드
			
			//static 영역에 올리는 것만으로는 하위 컨트롤러를 동작시킬 수 없다, 하위 컨트롤러가 보유한 
			//메서드가 인스턴스 메서드이기 때문에, 반드시 하위 컨트롤러들을 heap 으로 올리자 
			Controller obj =(Controller)controllerClass.newInstance();//new  연산자를 호출하는 효과와 같다, 즉 인스턴스 생성
			obj.execute(request, response); //다형성이 적용됨..
			//분명 자료형은 부모형인데, 동작은 자식으로 동작하기 때문에, 모습이 다형적이라고 해서 
			//다형성(polymorphism)이라 한다
			
			//obj.execute(); //Object  클래스는 최상위 클래스이므로, execute() 메서드를 가지지 않는다
			//MovieController controller=  (MovieController)obj;
			//BloodController controller=  (BloodController)obj;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		/*
		if(uri.equals("/blood.do")) { //혈액형 전문 하위 컨트롤러에게 업무를 전달할 것임
			BloodController controller = new BloodController();
			controller.execute(request, response);//하위 컨트롤러에게 요청 전달
			
		}else if(uri.equals("/movie.do")) {//영화 전문 하위 컨트롤러에게 업무를 전달할 것임
			MovieController controller = new MovieController();
			controller.execute(request, response); //하위 컨트롤러에게 요청 전달
		}
		*/
	}
	
}





















