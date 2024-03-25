package com.sds.poolproject.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//이 클래스의 정의는 필수는 아니지만, 만일 이러한 객체의 존재가 없다면 DAO에서 
//xml의 커넥션풀을 검색하여, DataSource 를 얻어오는 코드를 일일이 메서드마다 보유해야 한다..
//개발의 효율성 떨어진다..따라서 아래의 PoolManager가 검색을 대신 해주고, DataSource로부터
//필요한 Connection이 있을때,  DAO에게 빌려준다거나 반납도 받는 대행자 역할을 수행한다 
public class PoolManager {
	InitialContext context; // xml 검색 객체
	DataSource ds; //커넨션풀 구현체 
	
	
	public PoolManager() {
		
		//검색 객체 생성
		try {
			context = new InitialContext();
			ds =(DataSource)context.lookup("java:comp/env/jndi/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}  
		
	}
	
	//DAO 등이 커넥션풀로부터 Connection 한개를 얻어갈 수 있도록 메서드를 제공해주자
	public Connection getConnection() {
		Connection con=null;
		
		try {
			con=ds.getConnection();//풀로부터 한개 꺼내기
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con; //꺼낸 접속 객체 반환
	}
	
	//DAO등이 다 사용하고나서, 반납을 요청받는 메서드 정의 
	public void release(Connection con) { //반납하길 원하는 Connection 객체 매개변수로 정의
		if(con!=null) {
			try {
				con.close(); //여기서의 close()는 반납이다!!
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(Connection con, PreparedStatement pstmt) { //반납하길 원하는 Connection 객체 매개변수로 정의
		if(pstmt!=null) {
			try {
				pstmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close(); //여기서의 close()는 반납이다!!
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) { //반납하길 원하는 Connection 객체 매개변수로 정의
		if(rs!=null) {
			try {
				rs.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null) {
			try {
				pstmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close(); //여기서의 close()는 반납이다!!
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
}





