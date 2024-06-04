package com.sds.recommendapp.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//스프링 3.0 부터 스프링빈 xml 파일을 대신하여 어노테이션 기반으로 설정할 수 있도록 지원..
/*
 <bean id="msg" class="SecurityFilterChain">
 	<constructor-args value="바보"/>
 </bean> 
 */ 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//@Autowired
	//private CustomEntryPoint customEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		/*-----------------------------------------
		URI 권한 설정 
		-----------------------------------------*/
		httpSecurity.authorizeHttpRequests( (auth)-> auth
			
			/*-------------------------------------
			 권한 필요없는 uri 설정
			-------------------------------------*/	
			//.requestMatchers("/site/**").permitAll() //정적 자원 열어 놓기
			//.requestMatchers("/list/view").permitAll() //영화 추천 목록 열어놓기
			.anyRequest().permitAll()
			
			/*-------------------------------------
			 권한이 요구되는 uri 설정
			-------------------------------------*/	
			//.anyRequest().authenticated()
			
		);
		
		
		/*-----------------------------------------
		 CSRF 공격 방지 비활성화 
		-----------------------------------------*/
		httpSecurity.csrf((auth)->auth.disable());
		

		/*-----------------------------------------
		인증되지 않은 사용자가, 보호된 리소스에 접근할때의 예외처리 설정 객체 등록
		-----------------------------------------*/
		//httpSecurity.exceptionHandling(e -> e.authenticationEntryPoint(customEntryPoint));

		
		/*-----------------------------------------
		JWT를 이용할 것이므로, 세션 관리를 비활성화하고, 상태 비저장(stateless) 방식 사용
		-----------------------------------------*/
		//httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		
		return httpSecurity.build();
	}

	
}











