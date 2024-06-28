package com.sds.movieapp.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.movieapp.domain.Member;
import com.sds.movieapp.domain.MemberDetail;
import com.sds.movieapp.domain.Role;
import com.sds.movieapp.domain.Sns;
import com.sds.movieapp.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private SnsDAO snsDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private MemberDetailDAO memberDetailDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void regist(Member member) throws MemberException{
		//홈페이지 회원의 sns 정보 가져오기
		Sns sns = snsDAO.selectByName(member.getSns().getSns_name());
		member.setSns(sns);//sns_idx가 채워진 DTO를 다시 Member DTO  에 대입
		
		//회원의 권한 가져오기
		Role role = roleDAO.selectByName(member.getRole().getRole_name());
		member.setRole(role); //role_idx가 채워진 DTO를 다시 Member DTO  에 대입
		
		int result = memberDAO.insert(member);
		
		log.debug("member result is "+result);
		
		if(result <1) {
			throw new MemberException("회원 등록 실패");
		}
		
		//현재 기준 회원 상세정보를 입력처리해야 되는 회원은 홈페이지 회원이므로, 조건문으로 처리.. 
		if(sns.getSns_name().equals("homepage")) {
			//회원 상세 정보 등록 
			MemberDetail memberDetail = member.getMemberDetail();
			memberDetail.setMember(member);
			
			
			memberDetail.setPassword(passwordEncoder.encode(memberDetail.getPassword())); 
			
			//비밀번호 암호화 처리 
			result = memberDetailDAO.insert(memberDetail);//회원 상세 정보 등록
			
			log.debug("memberDetail result is "+result);
			
			if(result <1) {
				throw new MemberException("회원 추가정보 등록 실패");
			}
		}
	}
	
	@Override
	public Member selectByUid(String uid) {
		return memberDAO.selectByUid(uid);
	}
}






