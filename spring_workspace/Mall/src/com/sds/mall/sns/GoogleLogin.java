package com.sds.mall.sns;

import org.springframework.stereotype.Component;

import lombok.Data;

//구글 로그인 인증 요청시 가져가야할 수많은 파라미터를 이 객체에 담아서 처리할 예정(관리의 편의성)
@Data
@Component
public class GoogleLogin {
	
	//웹사이트 이용자가 보게될 동의화면 주소  (구글에서 제공함), 변수명은 개발자가 정하자
	private String endpoint;
	
	//구글 개발자콘솔에서의 앱id
	private String client_id;
	
	//구글 개발자콘솔에서의 앱 비번
	private String client_secret;
	
	//구글로부터 콜백받을 url 
	private String redirect_uri;
	
	//구글로부터 전달받을 파라미터명
	private String response_type;
	
	//사용자로부터 수집하고싶은, 데이터 범위
	private String scope;
	
	//토큰 발급 요청 주소
	private String token_request_url;
	
	//SNS 3사가 모두 동일한 명칭을 쓰더라...
	private String grant_type;
	
	//회원정보 요청 시 url
	private String userinfo_url;
	
	//동의화면 이나, 로그인 요청시 구글에 전송할 파라미터 구성하기
	//이 URL 및 파라미터 구성은 구글 api 에서 이미 정해놓은 규칙을 따라야 한다..
	//따라서 필수적인 파라미터는 반드시 전송해야 한다..(google login api document)
	public String getGrantUrl() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(endpoint+"?client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&response_type="+response_type);
		sb.append("&scope="+scope);
		
		return sb.toString();
	}
}


















