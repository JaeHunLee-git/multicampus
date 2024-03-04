package com.sds.project0304.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

/*
 * 자바의 컴포넌트 중,  Panel 은 판자이며 다른 컴포넌트(버튼, Checkbox ..)과는 다르게 
 * 배치 능력을 보유하고 있다. 즉 배치관리자(BorderLayout, GridLayout, FlowLayout..)의 적용이 가능
 * 마치 웹브라우저라는 윈도우를 내부적으로 디자인 영역을 나눌때 쓰는 div과 비슷한 역할을 수행
 * div 와 마찬가지로 투명이다 
 * */
public class PanelTest {

	public static void main(String[] args) {
		Frame frame=new Frame("Panel Study");
		
		frame.setLayout(new FlowLayout()); //디폴트가 BorderLayout이므로, 패널 테스트에 지장..
		
		//div 역할을 수행할 Panel 을 두개 준비하자 
		Panel p1=new Panel(); 	//첫번째 판자떼기
		Panel p2=new Panel();	//두번째 판자떼기
		/*판자의 능력
		 * 1) 배치관리자를 사용할 수 있는 능력 
		 *      만일 개발자가 아무것도 배치관리자를 적용하지 않으면 패널을 디폴트로 FlowLayout 적용
		 * 2) Frame의 능력인줄만 알았던 컴포넌트  add() 능력도 가지고 있다 
		 *     즉 Container 가 될 수 있다 (parent가 될 수 있다 주의-Super 용어랑 구부하자)
		 * */
		p1.setBackground(new Color(255, 130,135));
		p2.setBackground(new Color(255, 78, 97));
		
		p1.setPreferredSize(new Dimension(200, 150)); //크기 조정 
		p2.setPreferredSize(new Dimension(200, 150)); //크기 조정 
		
		frame.add(p1); //윈도우에 판자1 부착
		frame.add(p2); //윈도우에 판자2 부착
		
		frame.setVisible(true);
		frame.setSize(300, 350);

	}

}




