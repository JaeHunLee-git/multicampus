package com.sds.loginapp.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.loginapp.domain.Member;
import com.sds.loginapp.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;

	public void regist(Member member) throws MemberException {
		int result = memberDAO.insert(member);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
	}

	@Override
	public Member getMember(Member member) throws MemberException{
		Member dto = memberDAO.select(member);
		if(dto==null) {
			throw new MemberException("로그인 정보가 올바르지 않습니다");
		}
		return dto;
	}
	
}
