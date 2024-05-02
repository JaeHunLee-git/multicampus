package com.sds.movieapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.movieapp.domain.Member;
import com.sds.movieapp.domain.Role;
import com.sds.movieapp.exception.MemberException;
import com.sds.movieapp.model.member.MemberService;
import com.sds.movieapp.sns.NaverLogin;
import com.sds.movieapp.sns.NaverOAuthToken;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private NaverLogin naverLogin;
	
	@Autowired
	private MemberService memberService;
	
	
	//로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public String getLoginForm() {
		
		return "member/login";
	}
	
	//로그인 요청 처리 (스프링 부트의 시큐리티가 로그인 검증을 알아서 하므로, 로그인 성공시 보여질 페이지만 명시하자)
	@PostMapping("/member/login")
	public String login(Member member) {
		
		return "redirect:/"; //로그인 성공 시 메인을 재 요청하기
	}
	
	//회원가입 폼 요청 처리 
	@GetMapping("/member/joinform")
	public String getJoinForm() {
		
		return "member/join";
	}
	
	//홈페이지 회원가입 요청 처리 
	@PostMapping("/member/join")
	public String join(Member member) {
		
		log.info("member uid "+member.getUid());
		log.info("member uid "+member.getEmail());
		log.info("member uid "+member.getNickname());
		log.info("member uid "+member.getSns().getSns_name());
		
		//일반유저가 홈페이지 가입 시엔 USER 권한을 부여하자 
		Role role = new Role();
		role.setRole_name("USER");
		member.setRole(role);
		
		memberService.regist(member);//3단계: 일 시키기 (가입)
		
		return null;
	}
	
	//네이버 서버에서 들어온 콜백 요청처리
	//결과 처리 후, 로그인 요청한 사용자가 보게될 화면을 보여줘야 하므로, return  값은 html이 되어야 한다..
	//따라서 ModelAndView or String 와야 함
	@GetMapping("/member/sns/naver/callback")
	public ModelAndView naverCallback(HttpServletRequest request) {
		
		String code = request.getParameter("code");

		/*--------------------------------
		 * 토큰 요청을 위한 Post 헤더와 Body 구성
		 *--------------------------------*/
		String token_url = naverLogin.getToken_request_url();
		
		//바디구성 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("code", code);
		params.add("client_id", naverLogin.getClient_id());
		params.add("client_secret", naverLogin.getClient_secret());
		params.add("redirect_uri", naverLogin.getRedirect_uri()); //콜백 주소 
		params.add("grant_type", naverLogin.getGrant_type());
		params.add("state", naverLogin.getState());
		
		//post의 헤더 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		//머리와 몸을 합치기
		HttpEntity entity=new HttpEntity(params, headers);
		
		//비동기 방식으로  post 요청 (ajax 아님) 
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(token_url, HttpMethod.POST, entity, String.class);//요청 시작
		
		/*--------------------------------
		 *응답정보에 들어있는 데이터 중 토큰 꺼내기 
		 *--------------------------------*/
		String body = responseEntity.getBody();
		log.info("네이버가 보낸 인증 완료 정보는 "+body);
		
		//String 에 불과한 자료에서 토큰을 접근하려면 JSON 을 파싱해야 한다..(json simple...구글)
		//jackson lib 에서 지원하는 ObjectMapper도 있다 
		ObjectMapper objectMapper = new ObjectMapper();
		
		NaverOAuthToken oAuthToken=null;
		
		try {
			oAuthToken = objectMapper.readValue(body, NaverOAuthToken.class);//json 파싱 후 자바 객체에 담는다
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		/*--------------------------------
		 *토큰 정보를 이용하여 네이버 회원 정보 가져오기  
		 *--------------------------------*/
		String userinfo_url = naverLogin.getUserinfo_url();
		
		//Get 방식을 적용한 헤더 구성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity2 =new HttpEntity(headers2); //HTTP 머리와 몸을 하나의 단위로 묶어주는 객체
		
		//비동기 객체를 이용한 요청 (주의 : 프론트앤드 측의 Ajax 기술아님!!) 
		RestTemplate restTemplate2 = new RestTemplate();
		ResponseEntity<String> userEntity=restTemplate2.exchange(userinfo_url , HttpMethod.GET , entity2 , String.class);//비동기요청
		
		String userBody = userEntity.getBody();
		log.info(userBody);
		
		//사용자 정보 추출하기 
		ObjectMapper objectMapper2 = new ObjectMapper();
		
		//준비된 DTO가 없을 경우, HashMap 꺼내자 
		HashMap<String, Object> userMap = null;
		
		try {
			userMap = objectMapper2.readValue(userBody, HashMap.class); //두번째 인수는 인스턴스가 아닌 동적 클래스
																							//이므로, HashMap.class가 와야함
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map response = (Map)userMap.get("response");
		
		String id =(String)response.get("id");
		String email =(String)response.get("email");
		String name =(String)response.get("name");
		
		//중복된 회원이 없다면, 가입을 시킨다...(즉 최초 한번은 가입을 회원 정보를 보관해놓자..)
		
		
		//세션을 할당하여, 메인으로 보낸다..
		
		return null;
	}
	
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(MemberException e) {
		
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		
		return mav;
	}
	
}












