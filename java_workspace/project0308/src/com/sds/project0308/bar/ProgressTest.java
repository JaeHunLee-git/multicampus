package com.sds.project0308.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressTest extends JFrame{
	
	JButton bt;
	JProgressBar bar;
	int n;
	Thread thread; //바를 자동으로 증가시킬 쓰레드
	boolean flag=true; //쓰레드 실행 여부를 결정짓는 논리값 
	
	public ProgressTest() {
		bt = new JButton("Download");
		bar = new JProgressBar();
		
		setLayout(new FlowLayout());
		
		//스타일 
		bar.setPreferredSize(new Dimension(760, 50));
		bar.setBackground(Color.YELLOW);
		bar.setForeground(Color.ORANGE);
		
		add(bt);
		add(bar);
		
		//버튼과 리스너 연결 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("나 눌럿어?");
				
				thread  = new Thread() {
					public void run() {
						while(flag) {
							
							download();	
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				thread.start(); //쓰레드 시작
			}
		});
		
		setSize(800,250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//진행바를 증가시켜보자
	public void download() {
		n+=2;
		bar.setStringPainted(true);
		
		bar.setValue(n);
		bar.setString("다운로드 진행율 "+n+"%");
		
		if(n >=100) {
			flag=false;
		}
	}
	
	public static void main(String[] args) {
		new ProgressTest();
	}

}
