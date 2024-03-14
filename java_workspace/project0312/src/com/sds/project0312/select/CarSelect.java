package com.sds.project0312.select;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarSelect extends JFrame {
	JButton bt_connect;
	JButton bt_select;
	JPanel p_north; // 버튼 2개를 올려놓을 북쪽 패널
	JTable table;
	JScrollPane scroll;

//	String driver="com.mysql.jdbc.Driver";
//	String url="jdbc:mysql://localhost:3306/javase";
//	String user="root";
//	String pass;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "batman";
	String pass = "1234";
	Connection con = null;

	MyTableModel model; // JTable에게 데이터정보를 제공해주는 컨트롤러 중간자

	public CarSelect() {
		bt_connect = new JButton("접속");
		bt_select = new JButton("가져오기");
		p_north = new JPanel();
		table = new JTable(model = new MyTableModel()); // 컨트롤러를 넣을 예정
		scroll = new JScrollPane(table);

		// 북쪽 조립
		p_north.add(bt_connect);
		p_north.add(bt_select);
		add(p_north, BorderLayout.NORTH);// 프레임의 북쪽에 패널부착

		// 센터부착
		add(scroll);

		// 접속버튼과 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		// 조회 버튼과 리스너 연결
		bt_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});

		// 현재 프레임에 윈도우 리스너 연결
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// 데이터베이스 해제할 예정...
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				System.exit(0);
			}
		});

		setSize(600, 400);
		setVisible(true);
	}

	public void connect() {
		// 1단계: 드라이버 로드
		try {
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");

			// 2단계: 접속
			con = DriverManager.getConnection(url, user, pass);

			if (con == null) {
				this.setTitle("접속 실패");
			} else {
				this.setTitle("접속 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.setTitle("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 3단계: 쿼리문 수행
		// 4단계: 자원해제
	}

	// 원격지에 연결된 데이터베이스 접속 정보는 Connection 객체를 이용하여,
	// 쿼리문 객체를 생성한 후, 네트워크로 select 쿼리를 전송하고, 그 결과도 가져오자!!
	public void load() {
		
		//아래의 두 객체를 try 문 밖으로 빼놓아야, finally에서 닫을 수 있다..
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		// 접속이 된 이후에나 쿼리를 실행할 수 있으므로, Connection 객체로 부터 PreapredStatement를
		// 얻어올 수 밖에 없다
		String sql = "select * from car order by car_idx asc";

		// 쿼리문에 오타가 있을 수 있으므로, 예외처리
		try {
			pstmt = con.prepareStatement(sql); // 쿼리 실행 객체 생성(아직 쿼리 수행은 아니다)
			// DML인 경우 executeUpdate() 메서드 사용하고, select문인 경우 executeQuery()

			// 오라클의 표를 담고 있는 인터페이스가 ResultSet 이다
			rs = pstmt.executeQuery();

			// rs는 최초에는 아무런 열로 가리키고 있지 않고 있으며 가장 꼭대기에 올라와 있음
			// 여기서 꼭대기란, 첫번째 레코드 바로 위!!

			//비어있는 3개짜리 String 배열을 만들어서, 하나의 층에 있는 레코드를 담자
			
			
			//db 에 있는 레코드를 가져오기 전에 기존의 list 에 담겨져 있었던 데이터는 깨끗이 제거하고 
			//다시 담자 
			model.list.removeAll(model.list); //size 를 0으로 돌려놓음 싸~~악 비움
			
			while(rs.next()){// 한칸 전진
				String[] data = new String[3];
				
				// 컬럼의 자료형에 따라 int 일때 getInt(), varchar일때 getString() 될 수 있다
				System.out.println(rs.getInt("car_idx") + "," + rs.getString("name") + "," + rs.getInt("price"));
				data[0] = Integer.toString(rs.getInt("car_idx")) ; //pk 값
				data[1] = rs.getString("name"); //자동차 이름
				data[2] = Integer.toString(rs.getInt("price")); //자동차 가격 
				
				//하나의 자동차 레코드가 배열에 담겼으므로,  Table모델이 보유한 list에 추가하자
				model.list.add(data);
				System.out.println("현재까지 담아진 자동차 레코드 수는 "+model.list.size());
				
				//오직 모델만을 바라보고 있는 JTable을 갱신하여 추가된 레코드 가져오기
				table.updateUI(); //새로고침 
			}
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
			
		}

	}

	public static void main(String[] args) {
		new CarSelect();
	}

}
