package com.sds.springproject.model.bio;

import org.apache.ibatis.session.SqlSession;

import com.sds.springproject.domain.Bio;
import com.sds.springproject.mybatis.MybatisManager;

//오직 Bio 테이블에 대한 CRUD를 담당
public class MybatisBioDAO implements BioDAO{
	/*
	private MybatisManager manager; //주입을 받아야 한다..따라서 setter 준비하자
	
	public void setManager(MybatisManager manager) {
		this.manager = manager;
	}
	*/
	
	public int insert(Object obj, Bio bio) {
		int result = 0;
		SqlSession sqlSession=(SqlSession)obj;
		
		//sqlSession = manager.getSqlSession();//쿼리 실행 객체 
		result  = sqlSession.insert("Bio.insert", bio);
		//if(result>0) {
			//sqlSession.commit();
		//}
		//manager.release(sqlSession);
		return result;
	}
}
