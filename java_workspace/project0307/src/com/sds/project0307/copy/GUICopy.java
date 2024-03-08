package com.sds.project0307.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICopy extends JFrame implements ActionListener, WindowListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	
	//필요한 객체가 있다면 has a 로 보유하자
	FileInputStream fis;
	FileOutputStream fos;
	
	public GUICopy() {
		super("파일 복사 프로그램");
		la_ori = new JLabel("원본 위치");
		la_dest = new JLabel("복사 위치");
		
		t_ori  = new JTextField("D:/java_workspace/project0307/res/winter_night.jpg");
		t_dest  = new JTextField("D:/java_workspace/project0307/res/winter_night2.jpg");
		
		bt = new JButton("복사 실행");
		
		//스타일 
		Dimension d1 = new Dimension(180, 35);
		Dimension d2 = new Dimension(480, 35);
		
		la_ori.setPreferredSize(d1);
		la_dest.setPreferredSize(d1);
		
		t_ori.setPreferredSize(d2);
		t_dest.setPreferredSize(d2);
		
		//조립
		this.setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		//버튼과 리스너 연결 
		bt.addActionListener(this);
		
		//윈도우 설정 
		setSize(700, 200);
		setVisible(true);
		setLocationRelativeTo(null);
		
		//스트림과 같이 사용 후, 메모리에서 해제시켜야할 자원이 있는 경우엔 아래의 프로세스 종료는 
		//위험하다..스트림 닫을 기회를 못 가지니깐!!
		//setDefaultCloseOperation(EXIT_ON_CLOSE);//x자 누르면 프로세스 종료
	}
	
	//복사실행
	public void copy() {
		//원본 파일에 빨대 꽂기(스트림 생성)
		
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			this.setTitle("파일을 대상으로 스트림 생성 성공");
			
			//생성된 스트림들을 이용하여, 데이터를 읽어들이고(read), 내뱉어보자(write)
			int data=-1; //바이트를 읽어서 보관해놓을 변수
			
			while(true){
				data = fis.read(); //1 byte 읽기
				if(data==-1)break; //계속 읽다가 파일의 끝에 도달하면, 루프 멈추기
				fos.write(data); // 읽어들였던 1 바이트를 비어 있던 파일에 출력
			}
			JOptionPane.showMessageDialog(this , "파일복사 완료!!!");
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this , "파일의 경로를 확인해주세요");
			e.printStackTrace();
		} catch (IOException e) {
			this.setTitle("파일 입출력 중 에러가 발생했어요");
			e.printStackTrace();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		copy();
	}
	
	public static void main(String[] args) {
		new GUICopy();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//창 닫을때
	public void windowClosing(WindowEvent e) {
		//우리가 생성해놓은 스트림을 닫자!!
		if(fis !=null) { //스트림이 존재한다면...
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
