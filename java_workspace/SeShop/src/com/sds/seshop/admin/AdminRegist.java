package com.sds.seshop.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sds.seshop.lib.EncryptionManager;
import com.sds.seshop.lib.FileManager;
import com.sds.seshop.main.Page;
import com.sds.seshop.main.ShopMain;

//상품 등록 페이지 
public class AdminRegist extends Page{
	JLabel la_id, la_pass, la_email, la_profile;
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_email;
	JButton bt_profile;//프로필 사진 선택 창 띄우기 버튼
	JLabel la_dummy;//프로필 사진의 공백을 메울 목적
	JPanel p_preview; //사진 미리보기 
	JButton bt_regist, bt_login;
	JFileChooser chooser; //파일 탐색기
	Image image=null; //최초엔 그릴것이 없으므로 null, 아직 파일 선택도 안한 상태이니깐..
	String myName; //등록 메서드에서도 접근할 수 있도록, 멤버변수로 빼놓음
	File file; //파일 찾기 버튼 누를때, 유저가 선택한 파일을 담아둘 멤버변수 , 왜 빼놓았나? copy() 메서드 사용해야 하니깐!!!
	
	
	//1000 x 800 페이지 정의
	public AdminRegist(ShopMain shopMain) {
		super(Color.CYAN);
		this.shopMain =shopMain;
		
		container = new JPanel();
		
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		la_email = new JLabel("Email");
		la_profile = new JLabel("프로필사진");
		la_dummy=new JLabel("");
		
		t_id  = new JTextField();
		t_pass  =new JPasswordField();
		t_email  = new JTextField();
		bt_profile = new JButton("파일 찾기");
		
		//p_preview 라는 패널의 자식 클래스를 원래 .java  파일로 정의해야 하지만, 
		//재사용성이 떨어진다면, 즉 1회성라면 굳이 .java를 외부로 둘 필요가 없다..
		//내부익명 클래스로 정의하자 
		p_preview = new JPanel() {
			public void paint(Graphics g) {
				g.setColor(Color.YELLOW);//페인트 선택
				g.fillRect(0,0, 280, 280);
				
				if(image ==null) { //아직 사용자들이 이미지를 선택하지 않은 경우엔 문구를 보여준다..
					g.setColor(Color.RED);
					g.drawString("파일 선택", 40, 40);
				}else {
					//이미지 객체가 채워져 있다면 그림을 그린다....
					g.drawImage(image, 0, 0, 280, 280, p_preview);
				}
				
			}
		};
		
		bt_regist = new JButton("가입");
		bt_login = new JButton("Login");
		chooser = new JFileChooser("D:/js_workspace/images"); //디폴트 경로
		
		//스타일 
		container.setBackground(Color.LIGHT_GRAY);
		container.setPreferredSize(new Dimension(600, 700));
		
		Dimension d = new Dimension(280, 40);
		
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pass.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		la_email.setPreferredSize(d);
		t_email.setPreferredSize(d);
		la_profile.setPreferredSize(d);
		bt_profile.setPreferredSize(d);
		la_dummy.setPreferredSize(d);
		
		p_preview.setPreferredSize(new Dimension(280, 280));
		
		//조립 
		container.add(la_id);
		container.add(t_id);
		container.add(la_pass);
		container.add(t_pass);
		container.add(la_email);
		container.add(t_email);
		container.add(la_profile);
		container.add(bt_profile);
		container.add(la_dummy);
		container.add(p_preview);
		container.add(bt_regist);
		container.add(bt_login);	
		
		add(container);
		
		//파일 찾기 버튼과 리스너 연결 
		bt_profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preview();
			}
		});
		
		
		//가입 버튼에 리스너 연결 
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//관리자 오라클에 가입 시키기!!
				copy();
				regist();
			}
		});
		
		//로그인 버튼에 리스너 연결
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopMain.showHide(4);
			}
		});		
	}
	
	//프로필 파일 열어서 이미지를 패널에 그리기
	public void preview() {
		int result = chooser.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION) { //열기 눌르면..
			//유저가 선택한 파일 알아맞추고, 그 이미지 파일을 이용하여 p_preview 패널에  그림을 그려보자
			file = chooser.getSelectedFile(); //유저가 선택한 파일!!
			String filename = file.getAbsolutePath(); //현재 파일의 풀 하드 경로
			
			System.out.println("당신이 선택한 파일명 "+ filename);
			
			//선택한 파일로부터 확장자 구하기 
			String ext = FileManager.getExt(filename); //jpg
			
			//유일한 파일명으로 사용할 날짜 얻기 
			long time = System.currentTimeMillis(); //밀리세턴드까지의 시간 얻기 
			
			myName = time+"."+ext;//파일명 조합
			System.out.println(myName);
			
			
			
		
			ImageIcon icon = new ImageIcon(filename);
			image = icon.getImage(); //멤버변수인  image에 대입
			p_preview.repaint();//미리보기 패널에게 다시 그릴 것을 명령 
		}
	}
	
	//파일 복사 
	public void copy() {
		//원본 파일인 filename 과 연관된 입력스트림 연결하고, 바이트 데이터를 읽어서 마시면서 
		//동시에 빈파일(empty) 새로운 이름의 파일에 내려쓰자(출력)!!  = 파일복사 
		FileInputStream fis=null; //파일을 대상으로 한 바이트 기반의 입력 스트림 
		FileOutputStream fos=null; //파일을 대상으로 한 바이트 기반의 출력 스트림
		
		try {
			fis = new FileInputStream(file);//유저가 선택한 파일에 입력 스트림 꽂기
			//출력스트림으로 복사할 대상..
			fos = new FileOutputStream("C:/Users/zino1/SeShop/"+myName);
			System.out.println("스트림 생성 성공");
			
			//입력스트림으로부터 1byte씩 읽고, 다시 출력스트림으로 1byte씩 내려 쓰자 
			int data=-1;
			
			while(true) {
				data = fis.read(); //1byte 읽고 변수에 담기, 파일의 끝에 다다르면 -1을 반환..
				if(data==-1)break;//파일에 끝에 다다르면 루프 중단
				fos.write(data);
			}
			
			JOptionPane.showMessageDialog(this, "파일이 지정한 경로에 복사되었습니다");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("스트림 생성 실패");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	// 회원가입 메서드 정의 
	public void regist() {
		//쿼리문 수행
		PreparedStatement pstmt=null; //쿼리문 수행은 접속 이후에나 가능하므로, 접속객체로 부터 얻어야 함
		
		//shopMain의  con 나와 는 다른 패키지에 있는 멤버변수 이므로 즉 현재 default 접근제한자가 적용되
		//어 있으므로, 접근을 하려면, con의 접근제한을 느슨하게 하자 
		try {
			String id=t_id.getText(); //사용자가 입력한 id
			String pass= new String(t_pass.getPassword()); //사용자가 입력한 pass
			String email=t_email.getText();//사용자가 입력한 email
			String filename=myName;//사용자가 입력한 filename
			
			pass = EncryptionManager.getConvertedData(pass);
			
			String sql="insert into admin(admin_idx, id, pass, email, filename)";
			sql +=" values(seq_admin.nextval, '"+id+"', '"+pass+"', '"+email+"' ,'"+filename+"')";
			
			//오라클에 실행전에 우리가 먼저 검증해본다 
			System.out.println(sql);
			
			pstmt = shopMain.con.prepareStatement(sql);//쿼리문 준비 
			
			//준비된 쿼리문 수행하자 (DML-(insert, update, delete), select )
			int result = pstmt.executeUpdate(); //쿼리 실행!!! DML인 경우는 0이 나오면 실패
			
			if(result==0) {
				JOptionPane.showMessageDialog(this, "가입 실패");
			}else {
				JOptionPane.showMessageDialog(this, "가입 성공\n이용하시려면 로그인 해주세요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){//존재한다면 닫자
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
}









