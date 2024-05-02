package com.sds.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sds.movieapp.sns.NaverLogin;

@Controller
public class MemberController {
	
	@Autowired
	private NaverLogin naverLogin;
	
	//로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public String getLoginForm() {
		
		return "member/login";
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
		System.out.println("네이버가 보낸 인증 완료 정보는 "+body);
		
		return null;
	}
}










