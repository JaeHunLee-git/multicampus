package com.sds.securityapp.model.member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//스프링 시큐리티의 UsernamePasswordAuthenticationFilter는, 이 서비스객체를 통해 데이터베이스 연동을 시도한다
//그리고, 이 서비스 객체가 작동되려면, AuthentiationManager가 필요하고, authenticate() 메서드가 호출되어야 한다..
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		log.debug("필터에서 이 서비스 객체에게 일을 시켰네요");
		
		//DAO
		
		//DAO를 이용하여 db연동 후, 그 결과를 반환...UserDetails  (시큐리티의 DTO)
		return null; //UserDetails
	}

}
