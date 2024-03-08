package com.sds.project0307.monitor;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

//파일을 복사하는 과정에서 실행중인 프로그램으로 들어오고 나가는 바이트들을 육안으로 확인해보기 위한
//프로그램 
public class CopyMonitor extends JFrame implements ActionListener, WindowListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JTextArea area;
	JButton bt;
	FileInputStream fis;
	FileOutputStream fos;
	
	public CopyMonitor() {
		super("복사 데이터 모니터링"); //부모님인 JFrame의 생성자 호출
		
		la_ori = new JLabel("원본 경로");
		la_dest = new JLabel("복사 경로");
		t_ori = new JTextField("D:/java_workspace/project0307/res/memo.txt");
		t_dest = new JTextField("D:/java_workspace/project0307/res/memo2.txt");
		area = new JTextArea();
		bt = new JButton("복사실행");
		
		//스타일 
		Dimension d1 = new Dimension(150, 35);//JLabel 꺼
		Dimension d2 = new Dimension(620, 35); //JTextField 꺼
		Dimension d3 = new Dimension(750, 350); //JTextArea 꺼
		
		la_ori.setPreferredSize(d1);
		la_dest.setPreferredSize(d1);
		t_ori.setPreferredSize(d2);
		t_dest.setPreferredSize(d2);
		area.setPreferredSize(d3);
		
		//조립 
		setLayout(new FlowLayout());
		
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(area);
		add(bt);
		
		bt.addActionListener(this); //버튼과 리스너 연결 
		this.addWindowListener(this);//윈도우창과 리스너 연결 
		
		//윈도우 설정 
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	//스트림을 이용하여, 데이터 읽고, 내뱉기
	public void copy() {
		//스트림 생성하기 
		try {
			fis = new FileInputStream(t_ori.getText()); //파일을 대상으로 한 입력 스트림
			fos = new FileOutputStream(t_dest.getText()); //파일을 대상으로 한 출력 스트림
			this.setTitle("파일을 대상으로 스트림 생성함");
			
			//파일의 끝에 도달할때까지, 읽기와 쓰기를 반복해보자 
			int data=-1;
			
			while(true) {
				data=fis.read(); //1 byte 읽기
				if(data==-1)break;//루프 중단
				fos.write(data); //1 byte 쓰기
				
				//area에 현재 프로그램을 거쳐가고 있는 바이트 알갱이들을 출력해보자
				//java 의 모든 기본자료형마다 1:1 대응되는 Wrapper 클래스가 지원됨 
				//Wrapper의 목적: 기본자료형과 객체자료형간의 형변환을 지원
				// int형 3을 객체형인 "3" 스트링 객체로 바꾼다거나, 또는 역으로 "3" 을 int 형 3으로 바꿀때..
				//byte : Byte, short:Short, int:Integer
				//String str = Integer.toString(data);
				//area.append(str);
				System.out.print((char)data);
			}
			
			JOptionPane.showMessageDialog(this, "복사완료");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			this.setTitle("스트림 생성 실패");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		copy();
	}
	
	public void windowClosing(WindowEvent e) {
		//스트림이 생성되어 있다면 닫자 
		if(fis !=null) {
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(fos !=null) {
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}	
	
	public static void main(String[] args) {
		new CopyMonitor();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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






