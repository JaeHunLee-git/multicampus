package com.sds.recommendproject.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.recommendproject.domain.Member;
import com.sds.recommendproject.exception.MemberException;

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
	
	
	@Override
	public Member selectByUid(String uid) throws MemberException{
		return memberDAO.selectByUid(uid);
	}
}
