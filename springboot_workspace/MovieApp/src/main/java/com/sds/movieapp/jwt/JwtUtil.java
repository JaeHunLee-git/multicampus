package com.sds.movieapp.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	private PrivateKey privateKey; //개인키 - 서명에 사용할 예정 
	private PublicKey publicKey;//공개키 - 클라이언트가 보유한 서명( jwt+개인키로 암호화된 결과)
	
	
	/*---------------------------------
	RSA 의 키 쌍을 생성하는 메서드 
	 ---------------------------------*/
	public JwtUtil() throws Exception{
		//RSA알고리즘을 이용한 키 쌍을 생성할 수 있는 객체 얻기 
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048); //비트 단위의 크기를 명시
		
		//이 시점에, 공개키와 개인키의 쌍을 만들어버리자 
		KeyPair keyPair = keyPairGen.generateKeyPair();
		privateKey = keyPair.getPrivate(); //개인키 생성 
		publicKey = keyPair.getPublic(); //공개키 생성 
	}
	
	/*---------------------------------
	JWT에 서명된 토큰 생성 (JWT+privateKey) 
	JWT에는 왠만해서는, 보안상 민감한 정보는 절대로 넣지말자!!
	 ---------------------------------*/
	public String generateToken(String id, String role, Long expireTime) throws Exception{
		//jwt 생성 
		return Jwts.builder()
		.setSubject(id) //username  채우기
		.claim("role", role) // claim(_ 메서드를 이용하여 넣고 싶은 데이터를 더 넣을 수 있다..
		.setIssuedAt(new Date()) //발행 시간
		.setExpiration(new Date(System.currentTimeMillis()+expireTime)) //만료 시간 설정, 밀리세컨드 단위
		.signWith(SignatureAlgorithm.RS256, privateKey)
		.compact(); //jwt 토큰 생성 및 직렬화(string으로 변환...) 
	}
	
	/*------------------------------------
	공개키를 Base64기반의 인코딩 문자열 반환
	------------------------------------*/
	public String getEncodedPublicKey() {
		
		byte[] publicKeyBytes = publicKey.getEncoded();
		String encodedPublicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
		log.debug("Base64 기반의 인코딩 결과 "+encodedPublicKey);
		
		return encodedPublicKey;
	}
	
	
}











