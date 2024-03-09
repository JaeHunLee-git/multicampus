package com.sds.project0308.sprite;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//여러 스프라이트 이미지 이용하여,  쓰레드 에니메이션 효과를 내본다
public class Animation extends JFrame implements ActionListener{
	JButton bt;
	MyPanel p_content;
		
	public Animation() {
		bt = new JButton("start");
		p_content = new MyPanel();
		
		add(bt, BorderLayout.NORTH); //북쪽에 버튼 부착
		add(p_content); //프레임에 패널 부착 
		
		//버튼과 리스너 연결
		bt.addActionListener(this);
		
		//윈도우 설정 
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//현재 보고 있는 이미지의 다음 이미지를 보여주기 
	public void move() {
		//MyPanel 보유한 index 변수의 값을 증가시킨 후 다시 그리게 하자!! 
		p_content.index++;
		
		if(p_content.index>= p_content.imgArray.length) {
			p_content.index=0;
		}
		
		p_content.repaint();//다시 그리기 요청
	}
	
	public void actionPerformed(ActionEvent e) {
		//자동으로  move() 를 호출해야 하므로, 
		//쓰레드에게 move() 호출을 맡기자!!
		MyThread mt = new MyThread(this);
		mt.start(); //runnable로 밀어넣기
	}
	
	public static void main(String[] args) {
		new Animation();
	}
}
