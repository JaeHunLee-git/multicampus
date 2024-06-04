package com.sds.recommendmsa.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests( auth -> auth 
				
			/*-----------------------------------------------------------
			 권한 필요없는 uri 
			-----------------------------------------------------------*/
			.requestMatchers("/site/**").permitAll()
			.requestMatchers("/list/view").permitAll()

			/*-----------------------------------------------------------
			 권한 필요  
			-----------------------------------------------------------*/
			.anyRequest().authenticated()
			
		);
		return httpSecurity.build();
	}
	
}
