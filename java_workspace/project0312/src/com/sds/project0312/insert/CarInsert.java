package com.sds.project0312.insert;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/*mysql 의 테이블에 데이터 넣어보기
   드라이버란? 프로그래밍 언어가 DBMS 제품을 제어하기 위한 소프트웨어이고, 이 소프트웨어는 
   DBMS 제조사가 개발자들에게 제공할 의무가 있다, 제공하지 않으면??? 상품 못팔아 먹는다...
   MySQL을 연동하려면 MySQL 제조사로 가서 드라이버를 다운로드 받으면 됨
   
   [MySQL 가동 법 - 바이너리 압축 버전 인 경우 즉 installer 없이 설치된 경우]
   1.환경변수 path에 ~~/bin 까지 등록되어 있어야 함 
   2.cmd창에서 mysqld.exe  가동 프로그램으로 서버를 시작한다
   3.새로운 cmd 창을 열고서 mysql  -h  localhost  -u  root  
*/

public class CarInsert extends JFrame{
	JButton bt_connect; //접속
	JTextField t_name; //차 이름
	JTextField t_price; //차 가격 
	JButton bt_regist; //등록 
	
	//db 정보는 절대로 노출되어서는 안됌
	
//	String driver="oracle.jdbc.driver.OracleDriver";
//	String url="jdbc:oracle:thin:@localhost:1521:XE";
//	String user="batman";
//	String pass="1234";
	
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/javase";
	String user="root";
	String pass="";
	
	Connection con=null; //접속이 성공된 이후에 접속 정보를 가진 객체
	
	public CarInsert() {
		//생성 
		bt_connect = new JButton("접속");
		t_name = new JTextField();
		t_price = new JTextField();
		bt_regist = new JButton("등록");
		
		//스타일
		Dimension d = new Dimension(370, 38);
		t_name.setPreferredSize(d);
		t_price.setPreferredSize(d);
		
		//조립
		setLayout(new FlowLayout());
		add(bt_connect);
		add(t_name);
		add(t_price);
		add(bt_regist);
		
		//접속 버튼과 리스너 연결 
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		//현재 창에 윈도우 리스너 연결 
		this.addWindowListener(new WindowAdapter() {
			//우리 대신 이벤트 관련 Listener를 구현한 객체들을 가리켜 어댑터라 한다..
			//어댑터는 인터페이스의 추상메서드들을 재정의해놓았으므로, 우리가 그중 원하는 것만 
			//오버라이드하여 사용하면 된다
			public void windowClosing(WindowEvent e) {
				//데이터베이스 접속 끊기!!
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				System.exit(0);//현재 프로세스도 종료
			}
		});
		
		
		setSize(400,250);
		setVisible(true);		
	}
	
	public void connect() {
		/*1단계: 드라이버 로드 */
		try {
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");
			
			/*2단계: 접속 
			 *접속이 성공이 된 이후에, Connection 인터페이스의 인스턴스가 반환된다..
			 * */
			con = DriverManager.getConnection(url, user, pass);
			if(con==null) {
				this.setTitle("접속실패");
			}else {
				this.setTitle("접속성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.setTitle("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//등록 (네트워크를 통해 준비된 쿼리문을 전송하자)
	public void regist() {
		PreparedStatement pstmt=null; //쿼리문 담당 객체 
		
		//Oracle
		//String sql="insert into car(car_idx, name, price)"; 
		//sql+=" values(seq_car.nextval,'"+t_name.getText()+"', "+t_price.getText()+")";
		
		//MySQL
		String sql="insert into car(name, price)";
		sql+=" values('"+t_name.getText()+"', "+t_price.getText()+")";
		
		try {
			//쿼리문 수행하기
			//쿼리문은 접속이 성공되어야 실행할 수 있기 때문에,접속 정보를 가진 Connection 객체로부터 
			//인스턴스를 얻어올 수 있다..
			pstmt = con.prepareStatement(sql); //쿼리문 준비만 한거다....
			int result=pstmt.executeUpdate(); //DML(insert, update ,delete)의 경우 이 메서드로 실행
			//insert 문에 의해 영향을 받은 row count 1이므로 1이 반환됨 (언제나 1건씩 들어가므로...)
			//update문에 의해 영향을 받은 row count는 0보다 커야 한다 (여러건 수정이 가능하므로...)
			//delete 문에 의해 영향을 받은 row count는 0보다 커야 한다 (여러건 삭제가 가능하므로..)
			
			if(result<1) {
				this.setTitle("등록 실패");
			}else {
				this.setTitle("등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt !=null) {   //PreparedStatement 객체는 쿼리문 1개당 1:1 대응하면 쿼리 수행후 닫아버린다
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	public static void main(String[] args) {
		new CarInsert();

	}


}
