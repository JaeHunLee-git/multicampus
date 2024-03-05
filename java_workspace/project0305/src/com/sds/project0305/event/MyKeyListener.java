package com.sds.project0305.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//사용자가 키보드와 관련된 이벤트를 발생시키면, 아래의 리스너 객체가 감지를 한다..
//하지만, 감지했을때 어떤 코드를 작성할지 여부는 개발자의 몫이므로, 추상메서드만을 가진 
//인터페이스로 제공된다..따라서 우리가 원하는 코드를 오버라이딩 해보자
public class MyKeyListener implements KeyListener{

	//키보드 누를때 호출되는 메서드  ex) 총알발사 
	public void keyPressed(KeyEvent e) {
	}
	
	//키보드 눌렀다 뗄때 호출되는 메서드   ex) 입력완료 시
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
		if(e.getKeyCode()==10) { //Enter 를 눌렀다면..
			System.out.println("엔터 쳤어?");
		}
	}
	
	//타이핑할때
	public void keyTyped(KeyEvent e) {
	}


}
