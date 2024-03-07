package com.sds.project0306.img;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MoveImg extends JFrame implements KeyListener{
	XCanvas can;
	
	public MoveImg() {
		can = new XCanvas(); //나만의 재정의 된 캔버스 생성 
		
		add(can); //BorderLayout.CENTER
		can.repaint();
		
		//누구와 리스너 연결 
		can.addKeyListener(this);
		
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //윈도우창 x자 누르면 프로세스 종료
	}
	
	
	public void keyPressed(KeyEvent e) {
		System.out.println("키 눌렀어?");
		
		// 좌, 상, 우, 하  에 해당하는 키코드값을 외우지 말고, KeyEvent 가 보유한 상수로 직관성있게 사용해보자
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT: can.x-=5  ;break; 
			case KeyEvent.VK_UP: can.y-=5  ;break; 
			case KeyEvent.VK_RIGHT: can.x+=5  ;break; 
			case KeyEvent.VK_DOWN: can.y+=5  ;break; 
		}
		//켄버스의 그림이 다시 그려지도록, repaint() 호출 
		can.repaint();
	}
	
	public static void main(String[] args) {
		new MoveImg();
	}

	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}

}
