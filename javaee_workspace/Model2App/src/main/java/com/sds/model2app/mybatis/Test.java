package com.sds.model2app.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sds.model2app.domain.Board;

//mybatis는  javase, javaee 모두 사용이 가능한 플랫폼 중립적 퍼시스턴스(db영역) 프레임웍
//웹인 model2에서 사용하기 전에 미리  javase로 동작 여부를 확인해보자
//1.mybatis 설치가 필요 (  즉 jar 를 보유) 
/*
 * mybatis는 개발자가 java 코드안에 쿼리문을 작성하지 않고, xml에서 SQL문을 작성할 수 
 * 있도록 지원해줌 즉 jdbc 코드를 직접 핸들링 하지 않고 내부적으로  jdbc를 처리해줌 
 * 따라서 개발자는 쿼리문에 집중할 수 잇으며 기존 jdbc 시절에 작성했던 수많은 상투적 코드
 * 들을 작성하지 않아도 된다..
 * 예)  Connection con, PreparedStatement pstmt... 직접 사용 X
 * */
public class Test {
	
	public static void main(String[] args) {
		//마이바티스 관련설정파일을 읽어들여, 자바코드에서 mybatis를 사용하는 코드 
		
		
		try {
			
			String resource = "com/sds/model2app/mybatis/mybatis-config.xml";
			//마이바티스 설정  xml에 접근하여 스트림을 생성하기
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			//스트림을 이용하여, 쿼리문 수행 객체인 SqlSession 이라는 객체를 얻기 위해 SqlSessionFactory 얻기 
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
			
			SqlSession sqlSession = factory.openSession(); //쿼리 수행 객체 얻기!!
			inputStream.close();
			
			//제목, 작성자, 내용을 DTO에 채워서 아래의 메서드 매개변수로 넘기자 
			Board board = new Board(); //empty 
			board.setTitle("마이바티스로 넣은 제목");
			board.setWriter("batman");
			board.setContent("퍼시스턴스 영역의 프레임웍인 마이바티스 이용해보자");
			
			//mybatis의 SqlSession은 내부적으로 jdbc 이용하여 쿼리문을 실행하는데, 디폴트 트랜잭션 설정
			//autoCommit = false 로 되어 있다 Connection객체의 setAutoCommit(false); 동일함
			int result = sqlSession.insert("com.sds.model2app.domain.Board.insert", board);
			sqlSession.commit();//커밋이 발생함
			System.out.println(result);
			sqlSession.close(); //세션 닫기 
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

}







