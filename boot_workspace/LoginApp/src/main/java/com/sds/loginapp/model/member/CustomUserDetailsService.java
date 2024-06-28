package com.sds.loginapp.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sds.loginapp.domain.CustomUserDetails;
import com.sds.loginapp.domain.Member;
import com.sds.loginapp.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService  implements UserDetailsService{

	@Autowired
	private MemberDAO memberDAO;
	
	private final PasswordEncoder passwordEncoder; //추가
	
	//생성자로 주입 
	public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;		
	}
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		log.debug("id is "+id);
		
		Member member = memberDAO.selectById(id);
		
		if(member ==null) {
			throw new MemberException("회원과 일치하는 정보 없슴");
		}	
		
		log.debug("db 의 비번은 "+member.getPass());
		log.debug("새롭게 생성된 비번은 "+ passwordEncoder.encode(member.getPass()));
		
		return new CustomUserDetails(member);
	}
}