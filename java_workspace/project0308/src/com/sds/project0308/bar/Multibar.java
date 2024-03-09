package com.sds.project0308.bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/*
 * 바를 3개 올려놓고, 서로 다른 속도로 움직이도록 처리해본다 
 * 특히 쓰레드로 서로 다른 속도를 구현해보겠다..
 * 실습목적) 하나의 프로세스 내에서  서로 다른 독립적인 쓰레드를 구현
 * */
public class Multibar extends JFrame{
	JButton bt;
	JProgressBar[] bars=new JProgressBar[3]; //바를 만든것은 아니고 공간 3개 확보
	
	public Multibar() {
		bt = new JButton("Start");
		
		setLayout(new FlowLayout());
		
		add(bt);
		
		//바를 3개 생성 
		for(int i=0;i<bars.length;i++) {
			bars[i] =new JProgressBar();
			bars[i].setPreferredSize(new Dimension(800, 60));
			bars[i].setBackground(Color.CYAN);
			bars[i].setForeground(Color.BLUE);
			bars[i].setStringPainted(true); //텍스트 표시 허용
			
			add(bars[i]);
		}
		
		//버튼과 리스너 연결 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startDownload();
			}
		});
		
		// 윈도우 설정 
		setSize(800,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	//쓰레드 인스턴스를 3개를 생성하여, 3개의 바를 배분, 
	//그리고 step값도 각각 틀리게 배분 후 
	//해당 쓰레드를 start()
	public void startDownload() {
		BarThread t1 = new BarThread(bars[0],3);
		BarThread t2 = new BarThread(bars[1],1);
		BarThread t3 = new BarThread(bars[2],5);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	public static void main(String[] args) {
		new Multibar();
	}

}


