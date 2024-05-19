package com.sds.springproject.model.member;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Member;
import com.sds.springproject.mybatis.MybatisManager;

//Member 테이블에 대한  CRUD 만을 수행하는 모델 영역의 객체 
public class MybatisMemberDAO implements MemberDAO{
	//private MybatisManager manager;
	
	//스프링 컨테이너에게 인스턴스를 주입받기 위한 setter메서드 정의
	/*
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	*/
	
	//회원 등록
	public int insert(Object obj, Member member) {
		int result = 0;
		SqlSession sqlSession=(SqlSession)obj; //mybatis의 쿼리 수행 객체 
		
		//sqlSession = manager.getSqlSession(); //factory 로부터 세션 하나 꺼내기
		
		result = sqlSession.insert("Member.insert", member);
		//if(result >0) { //성공이라면 commit
			//sqlSession.commit();
		//}
		//manager.release(sqlSession);//반납
		
		return result;
	}
	
	//회원목록 
	
	//회원 1건 가져오기 
	
	//회원 1건 수정하기 
	
	//회원 1건 삭제 
	
}
