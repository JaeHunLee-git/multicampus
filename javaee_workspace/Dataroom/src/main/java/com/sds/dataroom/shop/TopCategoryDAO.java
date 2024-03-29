package com.sds.dataroom.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.dataroom.common.PoolManager;

public class TopCategoryDAO {

	PoolManager pool=PoolManager.getInstance();
	
	//모든 카테고리 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list = new ArrayList();
		
		con = pool.getConnection();
		String sql="select * from topcategory";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				TopCategory dto = new TopCategory();
				dto.setTopcategory_idx(rs.getInt("topcategory_idx"));
				dto.setTopname(rs.getString("topname"));
				
				list.add(dto);//리스트에 추가 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
}










