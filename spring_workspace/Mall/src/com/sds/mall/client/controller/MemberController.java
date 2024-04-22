package com.sds.mall.client.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.mall.domain.Member;
import com.sds.mall.exception.MemberException;
import com.sds.mall.model.member.MemberService;
import com.sds.mall.sns.GoogleLogin;
import com.sds.mall.sns.GoogleOAuthToken;

//회원과 관련된 요청을 전담하는 하위 컨트롤러 
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GoogleLogin googleLogin;
	
	
	
	//로그인 폼 요청 처리 
	@GetMapping("/member/loginform")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("shop/member/login");
		
		return mav;
	}
	
	
	//회원가입 폼 요청 처리 
	@GetMapping("/member/registform")
	public ModelAndView getRegistForm() {
		ModelAndView mav = new ModelAndView("shop/member/regist");
		
		return mav;
	}
	
	//회원가입 요청 처리 
	@PostMapping("/member/regist")
	@ResponseBody //이 어노테이션을 명시하면, 아래의 return 에 의한 문자열은 ViewResolver에 의해 jsp로 조합되지 않고 순수 데이터로 전송이 된다..
	public String regist(Member member) {
		//스프링은 클라이언트의 파라미터명과 서버측의 DTO의 멤버변수 명이 일치할 경우 자동 매핑을 처리
		//System.out.println("uid = "+member.getUid());
		//System.out.println("pass = "+member.getMemberDetail().getPassword());
		//System.out.println("nickname = "+member.getNickname());
		//System.out.println("email = "+member.getEmail());
		//System.out.println("phone = "+member.getMemberDetail().getPhone());
		//System.out.println("addr = "+member.getMemberDetail().getAddr());
		
		//3단계: 서비스에게 일 시키기
		memberService.regist(member); //이 메서드 호출 후, 우려했던 예외가 전달될 경우, 컨트롤러의 
		//기능 중 예외를 감지하는 @ExceptionHandler가 명시된 메서드가 호출된다.. 
		
		//클라이언트가 비동기방식으로 요청을 햇으므로, 응답 데이터는 jsp가 아니다 
		return "ok"; //ViewResolver에 의해 jsp로 해석하지마!!
	}
	
	//로그인 요청 처리 
	@PostMapping("/member/login")
	@ResponseBody
	public String login(Member member, HttpSession session) {
		//3단계: 일 시키기 
		Member dto = memberService.login(member);
		//dto 는 회원 로그인 성공한 者의 정보이다 
		//dto가 죽기 전에, 이 요청과 관련된 세션에 담아놓으면, 이 세션이 끝날때까지는 계속 꺼내먹을 수 있다 
		session.setAttribute("member", dto);
		
		return "ok";
	}
	
	//로그아웃 요청 처리 
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		//세션을 더이상 사용하지 못하게 하면 됨 
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	//사용자들이 최초 인증화면을 요청하거나, 인증화면 이후부터 로그인을 요청할때마다 구글 IDP가 우리에게 역으로 
	//요청이 들어오는데, 이 요청을 처리한다.. 
	//구글이 발급한 임시코드를 받아서, 우리가 보유한 client_secret 포함한 정보를 응답 정보로  POST 방식으로 구성하여 
	//파라미터를 전송하면, 구글이 다시 우리를 인증할 경우 최종 결과물로 token 을 보내온다..따라서 우리는 이 token을 받아서 
	//고객의 정보를 언제든 접근할 수 있다..단 scope 에 등록한 범위 내에서만...(이메일, openid, 프로필사진 이었슴)
	@GetMapping("/member/sns/google/callback")
	public ModelAndView googleCallback(String code, HttpSession session) {
		System.out.println("구글이 보내온 임시코드는 "+code);
		
		//구글이 보내온 임시code와 나의 client_id 및 client_secret를 조합하여, 구글 서버측에 token 발급을 요청해야 한다(Post)
		//Post 요청이 필요한 이유는 header+body 구성해서 요청할 것이므로..
		
		/*--------------------------
		 Post 방식의 머리 및 몸체 구성하기 (주의 html이 아닌 Java 코드 ,즉 html은 form 태그를 통해 자동으로 head,bofdy )
		 를 구성해주지만, 현재는 java코드로 작성해야 하므로 개발자가 일일이 header  및 body 구성코드를 작성해야 한다.. 
		--------------------------*/
		HttpHeaders headers=new HttpHeaders();//머리 객체 생성 
		headers.add("Content-Type", "application/x-www-form-urlencoded"); // form태그 사용시 자동 설정했던것임..
		
		//몸 만들기 
		//바디의 내용 구성시 파라미터=값, 파라미터=값의 반복이므로,  Map으로 구성 
		MultiValueMap<String, String> params=new LinkedMultiValueMap<String, String>();
		params.add("code", code); //내가 받은 코드 그대로 보낸다 
		params.add("client_id", googleLogin.getClient_id()); // 
		params.add("client_secret", googleLogin.getClient_secret()); // 
		params.add("redirect_uri", googleLogin.getRedirect_uri()); // 
		params.add("grant_type", googleLogin.getGrant_type()); // 
		
		
		HttpEntity httpEntity=new HttpEntity(params, headers);
		
		//토큰 요청 시도 (비동기 객체를 생성하여, 비동기방식으로 서버에 토큰을 얻어오자)
		RestTemplate restTemplate=new RestTemplate();
		
		//exchange(주소, 몸체내용, 방식, 응답받을자료형);
		String token_request_url = googleLogin.getToken_request_url();
		
		//서버와의 비동기 통신을 마치고 나면, 드뎌 토큰이 들어있다
		ResponseEntity<String> responseEntity=restTemplate.exchange(token_request_url, HttpMethod.POST , httpEntity, String.class);
		
		//응답 정보에서 토큰 추출하기 왜 추출하나? 사용자 정보를 서버에서 조회하려고
		String body = responseEntity.getBody(); //개발자가 exchage() 메서드의 매개변수 끝에 String.class로 명시했으므로, 응답결과가 String 이다
		System.out.println("토큰이 포함된 String 응답 정보 "+body);
		
		//응답정보가  String 형이므로, 그 안에 들어있는 access_token 를 추출하려면, JSON 으로 파싱이 되어야 한다..
		ObjectMapper objectMapper = new ObjectMapper(); //JSON 파서
		
		//파싱할때마다 소모되는 객체이므로 @Autowired 대상 아님
		GoogleOAuthToken oAuthToken=null;
		
		//파싱 시작 
		try {
			//파싱이 완료되면, GoogleOAuthToken의 인스턴스를 반환한다.
			oAuthToken = objectMapper.readValue(body, GoogleOAuthToken.class); //인수1: 파싱대상문자열, 인수2: 옮겨담을 객체형
			System.out.println("구글로 부터 인증 후 받은 토큰은 "+oAuthToken.getAccess_token());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//취득된 토큰을 이용하여 회원정보 접근
		String userinfo_url = googleLogin.getUserinfo_url(); //회원정보 요청할 주소
		
		//Get방식으로 요청한다 
		HttpHeaders headers2 = new HttpHeaders();//헤더 생성 
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		HttpEntity entity = new HttpEntity(headers2);
		
		//비동기 요청 
		RestTemplate restTemplate2 = new RestTemplate();
		//restTemplate2.exchange(주소, 전송메서드, 어떤형으로받을지);
		ResponseEntity<String> userEntity = restTemplate2.exchange(userinfo_url, HttpMethod.GET,entity ,String.class);
		
		String userBody = userEntity.getBody(); //요청 정보의 내용을 String 으로 받아둔다 
		System.out.println(userBody);
		
		//사용자 정보 파싱 및 추출 
		ObjectMapper objectMapper2 = new ObjectMapper();
		
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		//파싱시작 
		//Map에 보관된 사용자 정보 출력
		String uid=null;
		String email=null;
		String nickname=null;
		try {
			userMap = objectMapper2.readValue(userBody, HashMap.class);
			
			uid = (String)userMap.get("id");
			email = (String)userMap.get("email");
			nickname = (String)userMap.get("name");
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(uid);
		System.out.println(email);
		System.out.println(nickname);
		Member member = new Member();
		member.setUid(uid);
		member.setEmail(email);
		member.setNickname(nickname);
		member.setSns(memberService.selectByName("google"));
		
		//토큰은 사용자가 로그인할때마다, 값이 변경되는 임시코드이다. 따라서 개발자가 회원목록을 관리자에서 보고싶다면 토큰만으로는
		//해결되지 않는다...해결책) 중요한 정보는 우리 db에 저장해놓자 
		//회원등록은 최초에 한번한 수행하고, 이미 DB에 회원이 존재한다면(이미 가입회원이므로) 로그인만 처리한다
		Member dto = memberService.isSnSMember(member);
		
		if(dto ==null){//신규 가입이므로 우리 db insert
			memberService.regist(member); //신규가입
			dto = member; //세션에 담을 dto를, 기존 Member에 채워진 정보를 이용하여 대입
		}
		
		session.setAttribute("member", dto);
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	
	@ExceptionHandler(MemberException.class)
	@ResponseBody //비동기 요청에 대한 에러 처리 이므로, 응답 정보 또한 순수 데이터를 에러 응답을 보내자
	public String handle(MemberException e) {
		return "fail";
	}
	
}









