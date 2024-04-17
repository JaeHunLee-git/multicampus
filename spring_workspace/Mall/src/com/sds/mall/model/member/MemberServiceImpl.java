package com.sds.mall.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.mall.domain.Member;
import com.sds.mall.domain.MemberDetail;
import com.sds.mall.exception.MemberException;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberDetailDAO memberDetailDAO;
	
	//두개의 DAO들에게 일을 시키고, 만일 단 하나라도 RuntimeException이 전달되어 온다면, 트랜잭션을 rollback 처리를
	//스프링이 자동으로 처리
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Member member) throws MemberException {
		memberDAO.insert(member);//회원 테이블에 먼저 insert  후,  pk를 자동으로  member DTO에 채워넣음
		
		//member 안에 들어있는 memberDetail을 접근한다
		MemberDetail memberDetail = member.getMemberDetail();
		
		//그  memberDetail 보유한 member안에 member_idx 값을 넣어준다
		Member dto = new Member();
		dto.setMember_idx(member.getMember_idx());
		memberDetail.setMember(dto); //상세정보에 새롭게 생성된 DTO 주입
		
		
		memberDetailDAO.insert(member.getMemberDetail());
	}
	
	@Override
	public Member login(Member member) throws MemberException{
		return memberDAO.login(member);
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
	public void edit(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Member member) {
		// TODO Auto-generated method stub
		
	}
	
}
