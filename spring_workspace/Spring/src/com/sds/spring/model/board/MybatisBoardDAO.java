package com.sds.spring.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.spring.domain.Board;
import com.sds.spring.exception.BoardDMLException;
import com.sds.spring.mybatis.MybatisManager;

//아래의 @Repository 를 명시하면 설정파일의 context:component-scan 에 의해 메모리에 올려질 인스턴스의 대상이 됨  
@Repository
public class MybatisBoardDAO implements BoardDAO{
	
	@Autowired
	private MybatisManager manager;
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=manager.getSqlSession();
		list=sqlSession.selectList("Board.selectAll");
		manager.release(sqlSession);
		
		return list;
	}

	@Override
	public Board select(int board_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	//throws 에 명시된 런타임 예외의 처리 책임자는  이 메서드를 호출한 서비스가 된다
	public void insert(Board board) throws BoardDMLException{
		SqlSession sqlSession = manager.getSqlSession();
		
		int result =  sqlSession.insert("Board.insert", board);
		
		result=0; //예외를 발생시키기 위한 테스트 
		
		if(result >0) {
			sqlSession.commit();
		}else {
			//실패했단 사실...
			System.out.println("예외 발생시킬 예정");
			new BoardDMLException("글 등록에 실패하였습니다");
		}
		manager.release(sqlSession);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		
	}

}
