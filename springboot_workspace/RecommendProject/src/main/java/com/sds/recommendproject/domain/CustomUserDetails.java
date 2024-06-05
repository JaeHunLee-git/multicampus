package com.sds.recommendproject.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CustomUserDetails implements UserDetails{

	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member=member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> list = new ArrayList();
		list.add(new GrantedAuthority() {
			public String getAuthority() {
				
				log.debug("롤네임은 "+member.getRole().getRole_name());
				
				return member.getRole().getRole_name();
			}
		});
		
		return list;
	}

	@Override
	public String getPassword() {
		
		return member.getMemberDetail().getPassword();
	}

	@Override
	public String getUsername() {
		return member.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
