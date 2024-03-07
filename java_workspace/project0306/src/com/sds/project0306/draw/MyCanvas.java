package com.sds.project0306.draw;

import java.awt.Canvas;
import java.awt.Graphics;

//Canvas 의 메서드인 paint() 메서드를 재정의 해야 하므로, 별도의 클래스 정의가 필요하다 
public class MyCanvas extends Canvas{
	int x1,y1,x2,y2;
	
	//켄버스의 그림을 뺏어서 그리자!!(재정의==오버라이드)
	public void paint(Graphics g) {
		System.out.println("paint() 호출..");
		//선, 점, 도형, 글씨, 이미지 등등 자바에서는 팔레트가 색상 이외의 업무도 담당 
		//사실 모든 그림은 팔레트가 거의 담당함..paint() 팔레트를 이용한 로직만 수행 
		g.drawLine(x1,y1,x2,y2);
	}
}
