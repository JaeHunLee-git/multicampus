package com.sds.spring.model.board;

import java.util.List;

import com.sds.spring.domain.Board;

//특정 퍼시스턴스 기술에 국한되지 않는 Board와 관련된 DAO 들의 중립적 최상위 객체인 인터페이스 정의 
public interface BoardDAO {
	public List selectAll(); //모든 레코드 가져오기 
	public Board select(int board_idx); //데이터 한건 가져오기
	public void insert(Board board); //한건 넣기
	public void update(Board board); //한건 수정
	public void delete(Board board);//한건 삭제
}
