package com.sds.newsapp.map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//오직 store 테이블에 대한 CRUD만을 담당하는 객체인 DAO 정의 
public class StoreDAO {
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String pass="1234";	
	
	//등록
	public int insert(Store store) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //쿼리 수행 결과를 담게 될 변수 
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			
			String sql="insert into store(store_idx, name, lati, longi) values(seq_store.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, store.getName());
			pstmt.setDouble(2, store.getLati());
			pstmt.setDouble(3, store.getLongi());
			
			result = pstmt.executeUpdate(); //쿼리 실행
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		return result;
	}
		
	//목록
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list=new ArrayList();//Store DTO 담을 예정
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pass);
			
			String sql="select * from store";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();//쿼리 실행
			
			while(rs.next()) {
				//rs의 데이터를 DTO로 옮기기 
				Store store = new Store();
				store.setStore_idx(rs.getInt("store_idx"));
				store.setName(rs.getString("name"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				
				list.add(store); //채워진 DTO를 list 에 담기
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return list; //DTO 들이 채워진 list를 반환
	}
	
}





