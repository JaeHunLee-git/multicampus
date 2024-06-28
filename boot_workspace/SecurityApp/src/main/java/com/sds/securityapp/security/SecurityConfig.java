package com.sds.securityapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration // 스프링의 빈 등록 시 xml기반이 아닌 자바 클래스 기반으로 지원하는 어노테이션
@EnableWebSecurity //시큐리티와 관련된 자동 설정들이 지원되는 어노테이션(무조건 사용하자)
public class SecurityConfig {
	
	//데이터베이스 연동을 시도하는 UserDetailsService 서비스 객체에게 일을 시키기 위한 객체가 바로 
	//AuthenticationManager이므로, 이 객체의 인스턴스를 생성해야 한다...하지만, 
	//이 객체를 생성하기 위한 객체가 바로  AuthenticationConfiguration 이다 
	//5부터 autowire 지원함,즉 필터보다도 먼저 생성이 되어 있다는 뜻임
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean  //spring legacy mvc 의 경우 <bean class="~~LoginFilter"></bean>
	public LoginFilter loginFilter() throws Exception{
		return new LoginFilter( authenticationConfiguration.getAuthenticationManager());
	}

	//필터 체인 정보를 가진 빈 등록 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.csrf((auth) -> auth.disable()); //현재는 외부에서 들어오는 요청에 무방비(다행인건 크롬브라우저가 1차적으로 방어)
		
		httpSecurity.authorizeHttpRequests(auth -> auth
			.requestMatchers("/").permitAll() //로그인 디자인 
			.requestMatchers("/member/**").permitAll() //로그인 디자인 
			.requestMatchers("/rest/member/**").permitAll()//로그인 관련 요청
			
			.anyRequest().authenticated() //권한이 무엇이든 상관없이 무조건 로그인 한 유저에게만 허용
		);
		
		//스프링 시큐리티는 보안을 철저히 지원하기 위한 프레임웍이므로, 개발자는 CSRF 토큰 또는 JWT 토큰 중 자신에게 맞는 
		//방식을 이용하면 된다.. 
		
		//스프링이 기본적으로 지원하는 디자인 폼을 쓰기 싫으면 아래처럼설정
		httpSecurity
			.formLogin(login->login
				//.loginPage("/member/loginform").permitAll()
				//.loginProcessingUrl("/rest/member/login").permitAll()
				.disable()
			);
		
		
		//아래에서 시큐리티의 필터 단계 중, 어느 단계에 끼어들지를 명시
		httpSecurity.addFilterBefore( loginFilter() , UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
}













