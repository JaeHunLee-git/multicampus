package com.sds.springproject.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//이 클래스는  Mybatis의 설정 xml 파일을 읽어들여, SqlSessionFactory를 생성하기 위함이다.
//특히 SqlSessionFactory는 오직 1개만 만들기 위해, 아래의 클래스를 SingleTon 패턴으로 정의한다
public class MybatisManager {
	
	//2) 따라서  MybatisManager 객체의 인스턴스를 제공할 의무는 현재 클래스에게 있다..
	private static MybatisManager instance;
	SqlSessionFactory factory;
	
	//1) 외부의 어느 누구도 생성자 호출은 불가함
	private MybatisManager() {
		String resource = "com/sds/springproject/mybatis/mybatis-config.xml";
		InputStream is=null;
		try {
			is = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is !=null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//3) 외부의 누군가가 아래의 getter 를 호출하여 인스턴스를 가져가게끔 한다 
	// 하지만 아래의 메서드를 인스턴스 메서드로 정의하면, new  하지 않고는 접근할 없으므로 
	// static 메서드로 선언하자
	// static 영역은 같은 static 영역의 변수나 메서드만 접근할 수 있기 때문에, 멤버변수인 instance도 
	// static 으로 선언하자
	public static MybatisManager getInstance() {
		if(instance == null) { //if 조건문은 객체가 null이 아닐때는 작동하지 않으므로, 오직 1회만 수행
			instance = new MybatisManager();
		}
		return instance;
	}
	
	//SqlSession  얻어가기 
	public SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	//SqlSession 반납 
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}
}









