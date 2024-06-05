package com.sds.recommendproject;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
}








