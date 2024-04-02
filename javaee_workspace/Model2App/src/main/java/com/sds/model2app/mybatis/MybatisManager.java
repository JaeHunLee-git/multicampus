package com.sds.model2app.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//SqlSession 들이 모여서 관리되는 SqlSessionFactory는 하나의 어플리케이션 내에서 
//여러개 만들 필요가 없다..따라서 아래의 클래스를 싱글턴으로 정의하고, 이 클래스의 멤버변수로
//SqlSessionFactory를 선언하면, 오직 1개의 싱글턴 객체에서 관리되는 SqlSessionFactory
//로 사용할 수 있게 된다..
public class MybatisManager {
	private static MybatisManager instance;
	SqlSessionFactory factory;
	
	//외부에서 절대로 new 연산자를 이용하여 인스턴스를 생성하지 못하게 막는다
	private MybatisManager() {
		
		String resource = "com/sds/model2app/mybatis/mybatis-config.xml";

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			inputStream.close(); //스트림 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	//인스턴스 생성을 막았으므로, 이 객체에 대한 인스턴스는 이 클래스가 책임져야 한다. 이 클래스가 아니면
	//절대로 외부에서 인스턴스를 얻어갈 수 없다.. 
	public static MybatisManager getInstance() {
		if(instance ==null) { //널인 경우만 메모리에 인스턴스를 생성하기 위한 조건, 따라서 오직 1회만 생성
			instance = new MybatisManager();
		}
		return instance;
	}
	
	//팩토리로부터 SqlSession 하나를 반환하기 
	public SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	//사용후 팩토리로 반납 
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}
}






