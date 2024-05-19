package com.sds.springproject.model.member;

import com.sds.springproject.domain.Bio;

//모델 영역에서 DAO들에게 업무를 시킬 수 있는 서비스 객체, 
//만일 서비스가 존재하지 않을 경우, 서비스의 역할을 하위 Controller가 담당해야 하므로, 
//컨트롤러가 모델의 역할까지 수행하게 되어, MVC라는 패턴의 목적(철저히 분리)에 위배됨
public interface MemberService{
	
	//회원 등록 
	public int regist(Bio bio);
}
