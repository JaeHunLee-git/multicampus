package com.sds.spring.model.board;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.spring.domain.Board;
import com.sds.spring.hibernate.HibernateManager;

//Board 관련  DAO 중, 하이버네이트를 이용한 DAO 클래스 정의
@Repository
public class HibernateBoardDAO implements BoardDAO {
	
	@Autowired
	private HibernateManager manager;
	
	@Override
	public List selectAll() {
		System.out.println("DAO의 selectAll() 호출");
		//하이버네이트의 세션 을 얻어와서 쿼리실행 
		Session session = manager.getSession();
		Query<Board> query=session.createQuery("FROM Board", Board.class); //모든 레코드 가져와서 DTO 자동으로 매핑
		
		List list = query.list(); //java.util의 List로 옮기자
		manager.release(session); //세션 돌려보내기
		
		return list;
	}

	@Override
	public Board select(int board_idx) {
		Session session = manager.getSession();
		//한건 가져오기
		Query query=session.createQuery("FROM Board WHERE board_idx = :board_idx", Board.class);
		query.setParameter("board_idx", board_idx);
		Board board =(Board)query.uniqueResult();
		manager.release(session);
		
		return board;
	}

	@Override
	public void insert(Board board) {
		Session session = manager.getSession();
		
		//DML은 트랜잭션 처리
		session.beginTransaction(); //트랜잭션 시작
		session.save(board); //DTO의 프로퍼티 값을 db TAble 에 반영
		session.getTransaction().commit(); //트랜잭션 커밋 
		manager.release(session);
	}

	@Override
	public void update(Board board) {
		Session session = manager.getSession();
		session.beginTransaction();
		session.update(board);
		session.getTransaction().commit(); //트랜잭션 커밋
		manager.release(session);
	}

	@Override
	public void delete(Board board) {
		Session session = manager.getSession();
		session.beginTransaction();
		session.delete(board);
		session.getTransaction().commit(); //트랜잭션 커밋
		manager.release(session);
	}
	
}
