package com.sds.movieapp;

import java.io.IOException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sds.movieapp.jwt.JwtUtil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

/*
 *  무려 Spring 3.0 부터 이미 스프링설정  xml을 대신할 어노테이션을 지원하고 있었다..
 *  @Configuration  : 스프링 빈설정 xml을 대신하겠다 
 *  
	<bean id="key" class="java.lang.String">
		<constructor-arg value="6621a466612aa701c749b417034901a3"/>
	</bean>		
	
	<bean id="kobisOpenAPIRestService" class="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService">
		<constructor-arg ref="key"/>
	</bean>
 *  
 * */
@Configuration
public class AppConfig { //root-context.xml 대신 함
	
	@Bean
	public String key() {
		return "6621a466612aa701c749b417034901a3";
	}
	
	@Bean
	public KobisOpenAPIRestService kobisOpenAPIRestService(String key) {
		return new KobisOpenAPIRestService(key);
	}
	
	/*------------------------------------------------
	 Komoran 등록
	 <bean id="komoran" class="~~~~.Komoran">
	------------------------------------------------*/
	@Bean
	public Komoran komoran() {
		//설정 파일들의 위치...
		String modelPath=null;
		try {
			modelPath = new ClassPathResource("model_light").getFile().getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Komoran(modelPath); //분석 정보가 들어있는 데이터의 위치 경로
	}
	
	@Bean  // <bean id="jwtUtil" class="com.sds.~~.JwtUtil"></bean>
	public JwtUtil jwtUtil() throws Exception{
		return new JwtUtil(); //이 시점에 생성자 호출에 의해, 비밀키와 공개키도 함께 생성되 버림
	}
	
	/*------------------------------------------------
	어플리케이션 가동 시점 부터 공개키와 비밀키를 생성하고, 
	특히 공개키의 경우엔 나 아닌 다른 MSA가 가져갈 수 있도록, api로 제공을 해주되, 
	MovieApp의 경우 파일로 공개키를 저장해놓지 않고, ServletContext(application 내장객) 객체에 담아놓자
	
	아래의 객체를 빈으로 등록해 놓으면, 어플리케이션이 가동될때를 감지하여, 자신이 보유한 
	이벤트 메서드를 실행한다
	------------------------------------------------*/
	@Bean
	public ServletContextInitializer  servletContextInitializer(JwtUtil jwtUtil) {
		return new ServletContextInitializer() {
			
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setAttribute("key", jwtUtil.getEncodedPublicKey());
			}
		};
	}
}









