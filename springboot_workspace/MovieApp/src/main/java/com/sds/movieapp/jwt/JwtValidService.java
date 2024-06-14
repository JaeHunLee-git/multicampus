package com.sds.movieapp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieapp.domain.Member;
import com.sds.movieapp.exception.JwtException;
import com.sds.movieapp.model.member.MemberDAO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;


//인증 처리를 수반하는 모든 컨트롤러가 공통으로 사용할 jwt 인증 서비스 객체 정의 
@Slf4j
@Service
public class JwtValidService {
	
	@Autowired
	private KeyService keyService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MemberDAO memberDAO;
	
	//토큰을 넘겨받아, 유효할 경우 Member 자체를 반환하는 메서드
	public Member getMemberFromJwt(String token) throws JwtException {
		//넘겨받은 token은 순수 jwt 가 아니라, 비밀키에 의해 서명되어 암호화 되어 있다. 
		//따라서, 공개키를 이용하여 암호를 풀어보자 
		String publicKey =keyService.getPublicKey(); 
		
		Claims claims=null;
		
		try {
			claims = Jwts.parser().setSigningKey(jwtUtil.getPublicKeyFromString(publicKey)).parseClaimsJws(token).getBody();
			
		}catch (Exception e) {
			log.debug("JWT 인증실패");
			throw new JwtException("로그인이 필요한 서비스입니다");
		}
		
		String uid = claims.getSubject(); //JWT의 body의 제목에 넣어 둔 uid를 꺼내자
		Member member=memberDAO.selectByUid(uid);		
		return member;
	} 
}
