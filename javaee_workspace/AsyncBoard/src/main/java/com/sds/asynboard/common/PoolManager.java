package com.sds.asynboard.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//클라이언트의 요청이 들어오면, 해당 클라이언트에게 Connection 객체를 제공해주기 위한 
//풀 관리자 객체를 정의하자. 다 사용된 Connection 객체는 다시 풀도 돌려보내는 기능도 포함
public class PoolManager {
	InitialContext context; //검색 객체
	DataSource ds; //커넥션 풀 구현체 
	
	//생성자를 묶어놓았으므로 PoolManager 의 인스턴스를 제공할 의무 또한 현재 클래스가 부담해야 한다
	private static PoolManager instance;
	
	//싱글턴으로 정의하려면 생성자에 대한 접근을 제한한다
	private PoolManager() {
		try {
			context = new InitialContext();//검색 객체 생성 
			//검색 시작(server.xml 에 명시된 jndi 찾으러 출발)
			ds=(DataSource)context.lookup("java:comp/env/jndi/oracle");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	//인스턴스 변수인 instance 변수에 대해 접근이 불가능하므로.. 아래에서 제공되는 getter를 통해 
	//인스턴스를 가져갈 수 있도록 메서드를 정의
	public static PoolManager getInstance() {
		//instance 변수가  null일때만 인스턴스를 생성해주면, 오직 1번만 인스턴스를 만들게 됨 
		if(instance ==null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	
	//풀에 모여있는 커넥션 중 하나를 꺼내기 
	public Connection getConnection() {
		Connection con=null;
		
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con; 
	}
	
	//매개변수로 전달된 Connection 을 다시 풀로 돌려보내기 
	public void release(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void release(Connection con, PreparedStatement pstmt){
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
















