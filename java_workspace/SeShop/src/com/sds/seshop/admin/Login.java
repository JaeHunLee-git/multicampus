package com.sds.seshop.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sds.seshop.lib.EncryptionManager;
import com.sds.seshop.main.Page;
import com.sds.seshop.main.ShopMain;

//상품 등록 페이지 
public class Login extends Page{
	//1000 x 800 페이지 정의
	JLabel la_id, la_pass; 
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login, bt_regist;
	
	public Login(ShopMain shopMain) {
		super(Color.MAGENTA);
		this.shopMain=shopMain;
		
		//생성
		container = new JPanel();
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("Login");
		bt_regist = new JButton("관리자가입");
		
		//스타일
		container.setPreferredSize(new Dimension(600, 250));
		container.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(280, 40);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		
		
		//조립 
		container.add(la_id);
		container.add(t_id);
		container.add(la_pass);
		container.add(t_pass);
		container.add(bt_login);
		container.add(bt_regist);
		
		add(container);
		
		//로그인 버튼과 리스너 연결 
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		
		//관리자 가입 버튼과 리스너 연결 
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//회원가입 페이지로 전환, 이미 ShopMain에 정의해놓았다..
				shopMain.showHide(3);
			}
		});
	}
	
	
	//로그인 폼에 작성한 데이터와 오라클과 비교하기
	//레코드가 존재한다면, 관리자로 인정해주자, 아니면 욕하자!!
	public void loginCheck() {
		PreparedStatement pstmt=null; //쿼리 수행 인터페이스 객체 
		ResultSet rs=null; //select 문 수행 후 그 결과 표를 담기 위한 인터페이스 객체
									//try~catch 블럭 밖에 있어야, finally에서 닫을 수 있으니..
		
		String id=t_id.getText(); //유저가 입력한 id 값
		String pass=new String(t_pass.getPassword());  //유저가 입력한 패스워드 값
		pass = EncryptionManager.getConvertedData(pass);//평문을 암호화된 해시값로 변경
		
		String sql="select * from admin where id='"+id+"' and pass='"+pass+"' ";

		try {
			pstmt=shopMain.con.prepareStatement(sql);//쿼리준비
			//쿼리 실행 executeUpdate(dml), executeQuery(select) : ResultSet 표를 반환
			rs = pstmt.executeQuery(); //select 문 수행 후 표 반환(레코드가 존재할때만...)
			
			//아이디와 패스워드가 일치하는 레코드는 언제나 1건 밖에 없다!!
			//만일 레코드가 존재한다면 rs.next() 호출 시 true 가 반환될 것이다. 
			//왜? 커서가 1칸이라도 이동가능하므로
			boolean result = rs.next();
			if(result) {
				JOptionPane.showMessageDialog(this, "인증 성공");
				shopMain.loginflag=true;
				
				//프레임의 메시지를 출력
				shopMain.setCurrentTitle(id);
			}else {
				JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않아요");
				shopMain.loginflag=false;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		}
		
	}
}






