package com.sds.project0306.graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

//평상시에는 버튼을 그냥 쓰기만 했지만, 이번에는 버튼이 보유한 paint() 메서드를
//뺏기 위해 별도의 클래스로 버튼을 상속받아서 paint() 오버라이딩 해본다 
public class MyButton extends JButton{
	
	//버튼이 스스로 자기 자신을 그릴때 사용하는 메서드인 paint() 를 우리가 방해해서
	//재정의 해본다
	@Override
	public void paint(Graphics g) {
		System.out.println("버튼의 그림 방해중..");
		g.setColor(Color.YELLOW);
		g.drawString("Im button", 0, 10);
	}
	
}
