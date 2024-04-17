package com.sds.mall.model.member;

import java.util.List;

import com.sds.mall.domain.Member;

//모든 회원관련 DAO 중의 최상위 인터페이스, 서비스가 DAO 객체들을 느슨하게(loose coupling) 보유하게 하기 위해
public interface  MemberDAO{
	public void insert(Member member); //가입 
	public Member login(Member member); //로그인 처리 
	public List selectAll(); //회원목록
	public List selectAllBySnsIdx(int sns_idx); //1~4
	public Member select(int member_idx);//회원정보 1건 가져오기
	public void update(Member member); //회원정보 수정 
	public void delete(Member meMemberDAOmber); //회원 삭제
}
