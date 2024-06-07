package com.sds.recommendproject.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링의 빈을 등록 및 관리할 수 있는 xml을 대신할 수 있는 클래스로 선언 
@EnableWebSecurity // 스프링 시큐리티의 커스터마이징을 가능하게 하는 설정
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests(auth -> auth
			/*------------------------------------------
			권한이 필요 없는 uri
			------------------------------------------*/
			.requestMatchers("/site/**").permitAll() //정적자원 해제
			.requestMatchers("/list/view").permitAll() //추천 영화 페이지 요청 열기
			.requestMatchers("/list").permitAll() //추천 영화목록 요청 열기
				
			
			/*------------------------------------------
			권한이 필요한 uri
			------------------------------------------*/
			.anyRequest().authenticated()
		);

		
		return httpSecurity.build();
	}
	
}





