package com.sds.seshop.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sds.seshop.admin.AdminList;
import com.sds.seshop.admin.AdminRegist;
import com.sds.seshop.admin.Login;
import com.sds.seshop.product.ProductList;
import com.sds.seshop.product.ProductRegist;

//쇼핑몰 관리자 페이지의 메인 
public class ShopMain extends JFrame{
	JPanel p_north;
	JButton bt_regist, bt_list, bt_admin, bt_join, bt_login;
	String[] iconArray= {"product.png","list.png","admin.png","join.png","login.png"};
	
	//js 와 달리, 자바의 순수 배열은 js 처럼 다양한 indexOf  등이 없다. 대신 컬렉션 프레임웍에 들어있다.
	//아래의 배열을 Collection Framework 에서 지원하는 3가지 객체 중 선택하여 바꾸자
	//1) 순서잇는 집합  2) 순서없는 집합  3)  key-value
	ArrayList<JButton> btn=new ArrayList<JButton>(); //5개짜리 빈 공간(버튼만 담을 수 있는...)
	
	JPanel p_center; //페이지들이 교체될 컨테이너(상품등록페이지, 상품목록, 관리자명단, 로그인폼)
	
	//상품등록 페이지 
	public Page[] pages = new Page[5]; //모든 페이지를 담게될 배열 (오직 Page형만 올 수 있다)
	
	//직관성을 위해 의미가 부여된 상수를 활용하자
	public static final int PRODUCT_REGIST=0; 
	public static final int PRODUCT_LIST=1; 
	public static final int ADMIN_LIST=2; 
	public static final int ADMIN_REGIST=3; 
	public static final int LOGIN=4;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="seshop";
	String password="1234";
	public Connection con; //접속 한 이후,그 접속 정보를 가진 객체. 정보를 가지고 있으므로 추후 접속해제 까지 가능
	public boolean loginflag=false; //로그인 했는지 여부를 판단해주는 멤버변수
	
	public ShopMain() {
		connect();//오라클 접속 시도
		
		p_north = new JPanel();
		
		Dimension d = new Dimension(75, 60);
		
		for(int i=0;i<iconArray.length;i++) {
			JButton bt = new JButton(getIcon(iconArray[i]));
			bt.setPreferredSize(d);
			p_north.add(bt);//북쪽 패널에 버튼 부착
			btn.add(bt);//ArrayList에 추가하기
		}
		
		//센터 프레임 생성 
		p_center = new JPanel();
		p_center.setBackground(Color.YELLOW);
		
		//5페이지 생성 
		pages[PRODUCT_REGIST] = new ProductRegist(this); //상품 등록 객체는 ShoPMain 인스턴스 주소값을 원함
		pages[PRODUCT_LIST] = new ProductList(this); //상품 목록 객체는 ShoPMain 인스턴스 주소값을 원함
		pages[ADMIN_LIST] = new AdminList(this); //관리자 목록 객체는 ShoPMain 인스턴스 주소값을 원함
		pages[ADMIN_REGIST] = new AdminRegist(this); //로그인 객체는 ShoPMain 인스턴스 주소값을 원함
		pages[LOGIN] = new Login(this); //로그인 객체는 ShoPMain 인스턴스 주소값을 원함
		
		//5페이지 중 누구를 보여줄지를 생성시점에 결정하자 
		showHide(pages.length-1);//로그이인인 마지막 페이지부터 보자
		
		//5페이지를 센터에 부착
		for(int i=0;i<pages.length;i++) {
			p_center.add(pages[i]);
		}
		
		//프레임에 부착 
		add(p_north, BorderLayout.NORTH);
		add(p_center); //BorderLayout의 CENTER에 부착 
		
		//윈도우 어댑터를 익명으로 연결 (어댑터란 우리 대신 리스너를 구현한 객체 즉 어댑터가 바로 리스너임) 
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//db커넥션을 닫을 예정 
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				System.exit(0);//실행중인 프로그램을 프로세스..프로세스 종료
			}
		});
		
		//상품 등록 버튼에 리스너 연결 
		for(int i=0;i<btn.size();i++) { //버튼 수 만큼 반복
			btn.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//지금 당신이 누른 버튼의 index 는 ~~
					int index=btn.indexOf(e.getSource());
					
					System.out.println("당신이 누는 버튼의 배열의 index 는 "+index);
					showHide(index); //방금 눌린 버튼의 index 넘기자!
					
					//상품 목록 버튼이라면..상품 갱신 메서드 호출 
					if(index==PRODUCT_LIST) {
						getProductList();
					}
					
				}
			});
		}
		
		setSize(1000,850);
		setVisible(true);
		
		
	}
	
	//오라클 접속
	public void connect() {
		this.setTitle("접속 시도중...");
		
		try {
			Class.forName(driver);
			this.setTitle("드라이버 로드 성공");
			
			//접속 시도
			//접속이 성공되면 con은 더이상 null 이 아니게 됨..
			con=DriverManager.getConnection(url, user, password);
			
			if(con ==null) {
				this.setTitle("접속 실패");
			}else {
				this.setTitle("오라클 연결 됨");
				System.out.println("오라클 연결 ");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.setTitle("드라이버를 확인해주세요");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//5 페이지중 어느 페이지를 보여줘야 할지를 결정짓는 메서드
	//js 시간에 탭메뉴 만들기라는 주제로 했었다!!!
	//showHide(0); 0번째 페이지 보여주기, showHide(3) 3번째 페이지만 보여주기 
	public void showHide(int index) {//몇번째만 보여줄지 그 index만을 넘겨받자 
		for(int j=0; j<pages.length;j++){
			if(j==index){//선택한 index 번째 페이지만
				pages[j].setVisible(true); //선택한 index 번째 페이지만 true
			}else {
				pages[j].setVisible(false); //조건에 맞지 않으면 false
			}
		}
	}
	
	//상품 목록 버튼 누를때 처리 
	public void getProductList() {
		ProductList productList = (ProductList)pages[ShopMain.PRODUCT_LIST];
		productList.getProductList();//상품 갱신
		productList.table.updateUI();//JTable 새로고침
	}
	
	//지정한 경로의 아이콘을 반환해주는 메서드 
	//filename에 원하는 이미지 명을 넣으면, 아이콘으로 변환하여 줌
	public ImageIcon getIcon(String filename) {
		
		//패키지를 기준으로 자원의 경로를 얻기 
		URL url = this.getClass().getClassLoader().getResource(filename);
		ImageIcon icon = new ImageIcon(url);
		//ImageIcon 클래스 자체에는 크기 조정하는 메서드가 지원되지 않으므로, Image로 변환한 후 
		//Image가 보유한 getScaledInstance() 메서드로 크기를 조정해서 와보자 
		Image image = icon.getImage(); //이미지로 변환 (이미지가 이미지 아이콘보다 더 넓은 개념)
		image = image.getScaledInstance(75, 60, Image.SCALE_SMOOTH);
		
		return new ImageIcon(image);
	}
	
	//제목창 관리 메서드 정의 
	public void setCurrentTitle(String id) {
		if(loginflag) {
			//로그인 성공시의 메시지
			this.setTitle("오라클 접속 중 - "+id+"님 로그인 중");
		}else {
			//로그인 실패시의 메시지
			this.setTitle("오라클 접속 중 - 로그인해 주세요");
		}
	}
	
	
	public static void main(String[] args) {
		new ShopMain();
	}

}
