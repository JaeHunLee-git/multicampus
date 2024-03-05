package com.sds.project0305.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatA extends Frame implements ActionListener, KeyListener{
								/* is a */
	TextArea area; //html 에서의 textarea 와 동일
	Panel p_south;
	TextField t;
	Button bt;
	ChatB chatB; //자바에서는 제어하고싶은 객체가 있다면 주소값을 보유하면 된다
	
	
	public ChatA() {
		area = new TextArea();
		p_south = new Panel();
		t = new TextField(15);
		bt = new Button("open");
		
		//스타일 적용 
		area.setBackground(Color.YELLOW);
		
		//조립 
		add(area); //BorderLayout.CENTER 부착
		p_south.add(t);//판자에 텍스트 필드 부착
		p_south.add(bt); //판자에 버튼 부착 
		
		add(p_south, BorderLayout.SOUTH);//판자를 남쪽 영역에 부착 
		
		//열기 버튼과 리스너와의 연결 
		//OpenListener openListener=new OpenListener(); //리스너 객체 생성
		bt.addActionListener(this); //내가 귀달린 者이다 
		
		
		//입력 텍스트필드와 리스너연결
		//MyKeyListener mkListener = new MyKeyListener();//리스너 객체 생성
		t.addKeyListener(this); //내가 귀달린 者이다 
		
		setBounds(200,300, 300, 400); //위치 + 너비 까지 지정 가능
		setVisible(true);
	}
	
	//ActionListener의 추상메서드 오버라이딩 
	public void actionPerformed(ActionEvent e) {
		System.out.println("나 눌럿어?");
		//친구인 ChatB를 생성하자
		if(chatB==null)
			chatB = new ChatB(this); //이 찰나를 놓치지 말고, ChatB에게 ChatA 의 주소값을 전달해주자
	}
	
	// KeyListener 추상메서드들 오버라이딩
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		//Java에서는 상수가 바로 직관성을 보유한 의미가 부여된 데이터
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println("엔터쳣어?");
			
			//나의 area에 채팅 로그 추가
			String msg = this.t.getText();
			this.area.append(msg+"\n");
			
			//chatB area에 채팅 로그 추가
			this.chatB.area.append(msg+"\n");
			
			//로그를 남긴 후에는 입력했던 텍스트를 다시 초기화 
			t.setText("");
		}
		
	}

	public static void main(String[] args) {
		new ChatA();
	}
}
