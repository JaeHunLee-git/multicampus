package com.sds.project0305.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatB extends Frame implements KeyListener{
								/* is a */
	TextArea area; //html 에서의 textarea 와 동일
	Panel p_south;
	TextField t;
	ChatA chatA; //ChatA를 제어하기 위해 보유하자, 현재는 null
	
	public ChatB(ChatA chatA) {  // new ChatB(채팅A 인스턴스)
		area = new TextArea();
		p_south = new Panel();
		t = new TextField(15);
		this.chatA = chatA; //매개변수로 넘겨받은 chaA 의 주소값을 멤버변수로 보관해놓자
		//, 그래야 두고 두고 ChatA를 제어할수있으니 
		
		//스타일 적용 
		area.setBackground(Color.YELLOW);
		
		//조립 
		add(area); //BorderLayout.CENTER 부착
		p_south.add(t);//판자에 텍스트 필드 부착
		
		add(p_south, BorderLayout.SOUTH);//판자를 남쪽 영역에 부착 
		
		//텍스트 필드와 리스너와의 연결 
		t.addKeyListener(this);
		
		setBounds(550, 300, 300, 400);
		setVisible(true);
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//나의 area 에 로그 남기고
			String msg=t.getText();//텍스트 필드에 입력한 값
			this.area.append(msg+"\n");
			
			//chatA의 area에 로그 남기자
			chatA.area.append(msg+"\n");
			
			//입력한 값을 다시 초기화 
			t.setText("");
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}

}
