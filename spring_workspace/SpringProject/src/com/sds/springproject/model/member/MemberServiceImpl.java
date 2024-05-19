package com.sds.springproject.model.member;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Bio;
import com.sds.springproject.model.bio.BioDAO;
import com.sds.springproject.mybatis.MybatisManager;


//기존의 하위 컨트롤러가 오버해서 담당하고 있었던, 모델 영역의 코드를 이 서비스 객체에서 전담하면 된다...
public class MemberServiceImpl implements MemberService{
	
	private MybatisManager manager; 
	private MemberDAO memberDAO; //느슨한 보유(상위 자료형의 보유)
	private BioDAO bioDAO; //느슨한 보유 (상위 자료형의 보유)
	
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setBioDAO(BioDAO bioDAO) {
		this.bioDAO = bioDAO;
	}
	
	
	public int regist(Bio bio) {
		int result = 0;
		
		SqlSession sqlSession=null;
		sqlSession = manager.getSqlSession();
		
		//회원 등록 후, 성공하면 취득된  member_idx를 이용하여, Bio에도  insert 
		result = memberDAO.insert(sqlSession, bio.getMember());
		
		if(result > 0) {
			result = bioDAO.insert(sqlSession, bio);
			
			if(result > 0) {
				sqlSession.commit(); //트랜잭션 commit
			}
		}
		manager.release(sqlSession);//반납
		
		return result;
	}
	
}













