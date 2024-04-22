package com.sds.mall.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.mall.domain.Member;
import com.sds.mall.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result <1) { 
			//실패한 경우  RuntimeException  을 일으켜야, 스프링에서 트랜잭션을 rollback 해준다 	
			throw new MemberException("회원 정보가 입력되지 못했습니다");
		}		
	}
	
	@Override
	public Member login(Member member) throws MemberException{
		//회원이 존재한다면 로그인 성공, 일치하는 회원이 없다면 로그인 실패
		Member dto = sqlSessionTemplate.selectOne("Member.login", member);
		
		if(dto ==null) { //로그인 실패
			System.out.println("로그인 실패");
			throw new MemberException("회원정보가 일치하지 않습니다");
		}
		return dto;
	}
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAllBySnsIdx(int sns_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Member isSnSMember(Member member) {
		return sqlSessionTemplate.selectOne("Member.isSnSMember", member);
	}
	
	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}
	
}
