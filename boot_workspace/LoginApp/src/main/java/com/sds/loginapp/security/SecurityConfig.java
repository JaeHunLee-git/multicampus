package com.sds.loginapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public LoginFilter loginFilter() throws Exception{
		AuthenticationManager authenticationManager=authenticationConfiguration.getAuthenticationManager();
		LoginFilter loginFilter = new LoginFilter(authenticationManager);
		return loginFilter;
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeHttpRequests((auth) -> auth
			.requestMatchers("/").permitAll() //로그인 디자인 
			.requestMatchers("/member/**").permitAll() //로그인 디자인 
			.requestMatchers("/rest/member/**").permitAll()//로그인 관련 요청
			
			.anyRequest().authenticated()
		);
		
		httpSecurity.csrf((auth) -> auth.disable());
		
		
		/*-----------------------------------------------------
		UsernamePasswordAuthenticationFilter  를 재정의 하게 되면, 즉  LoginFilter 클래스를 정의하게 되면 
		기존의 폼로그인은 비활성화 시켜놓아야 하며, 
		
		 LoginFilter 클래스가 로그인 요청을 처리해야 하므로, setFilterProcessesUrl("/rest/member/login"); 를 등록해놓아야 함 
		-----------------------------------------------------*/
		httpSecurity
			.formLogin( login -> login
				.disable()
			);
			/*
			.logout(logout -> logout
				.logoutUrl("/member/logout") 
				.logoutSuccessUrl("/") //로그아웃 성공 후 리다이렉트 URL
				.permitAll()
			);
			*/
			
		httpSecurity.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
	
}
