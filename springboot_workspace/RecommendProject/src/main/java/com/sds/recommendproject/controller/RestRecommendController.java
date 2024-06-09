package com.sds.recommendproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sds.recommendproject.domain.Member;
import com.sds.recommendproject.domain.Movie;
import com.sds.recommendproject.jwt.JwtParser;
import com.sds.recommendproject.jwt.KeyService;
import com.sds.recommendproject.model.member.MemberService;
import com.sds.recommendproject.model.recommend.RecommendService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestRecommendController {
	
	//클라이언트가 요청 헤더에 전달한 토큰을 넘겨받아, jwt를 꺼내보자 (인증: 클라우드 서비스를 이용하는 회원여부를 판단)
	@Autowired
	private KeyService keyService;
	
	@Autowired
	private JwtParser jwtParser;
	
	@Autowired
	private MemberService memberService; //회원정보 가져오기 위한 서비스 
	
	@Autowired
	private RecommendService recommendService;//영화 추천 정보 가져오기 위한 서비스 
	
	
	//영화 추천 목록 데이터 요청처리 (클라이언트가 비동기방식으로 헤더의 Authorization Bearer에 토큰(서명된 jwt)을 전송함 )
	@GetMapping("/list")
	public List<Movie> getList(@RequestHeader("Authorization") String header) throws Exception {
		
		log.debug("넘겨받은 헤더중 Authorization 의 값은 "+header);
		
		//Bearer 제거 후 토큰만 가져오기 
		String token = header.replace("Bearer ", "");  // 외에도  substring(7) 도 가능 
		
		//공개키로 풀자...서비스객체에게 일 시키기
		String publicKey = keyService.getPublicKey();
		
		log.debug("MovieApp가 제공하는 공개키 String은 "+publicKey);
		
		//공개키를 이용하여, 복호화된 jwt 의 payload(몸체) 부분을 암호분야 에서는 Claim이라 한다 
		Claims claims=Jwts.parser().setSigningKey(jwtParser.getPublicKeyFromString(publicKey)).parseClaimsJws(token).getBody();
		
		String uid = claims.getSubject(); // 우리의 경우 제목에 uid가 들어있씀
		log.debug("uid is "+uid);
		
		//uid  를 이용하여 회원정보 가져오기 (~~님 포함하여 기타 회원정보를)
		Member member = memberService.selectByUid(uid);
		
		//회원 정보를 이용하여 , 영화추천 목록 가져오기
		List<Movie> recommendList = recommendService.getList(member.getMember_idx());
		
		
		return recommendList;
	}
}




