package com.sds.project0305.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;

public class WebSite extends Frame {
								/* is a */
	/*윈도우가 버튼들을 가지고 있다 
	 * Frame has a Button
	 * */
	Panel p_north; //버튼들을 FlowLayout으로 얹혀놓을 패널
	Button[] bt=new Button[4]; //버튼 4개를 담을수있는 공간 마련
	String[] naviTitle= {"introduce","brand","product","contact"};
	Label subNavi;
	Panel content;
	Label copyright;
	
	//has a  관계에 있는 사물들은 이 WebSite 객체가 탄생할때 함께
	//탄생해야 할 부품들이다..따라서 생성자의 기회를 놓치지 말자!
	public WebSite() {
		p_north = new Panel();
		
		//부품으로 보유한 객체들을 메모리에 생성하기
		for(int i=0;i<bt.length;i++) {
			bt[i] = new Button(naviTitle[i]);
			 
			//패널은 배치능력이 있는 컴포넌트이고, 디폴트가 FlowLayout이다
			p_north.add(bt[i]);//생성되는 버튼들을 판자에 부착
		}
		
		subNavi = new Label("apple banana strawberry pine");
		content = new Panel();
		copyright = new Label("copyright all reserved");
		
		//원하는 스타일 적용
		
		subNavi.setBackground(Color.YELLOW); //좌측 라벨에 배경색 적용
		subNavi.setPreferredSize(new Dimension(80, 300));
		content.setBackground(Color.GREEN);
		copyright.setBackground(Color.RED);
		copyright.setPreferredSize(new Dimension(500, 100));
		
		
		
		
		//컨테이너인 프레임에 부착해보자 
		//생성자도 멤버메서드 이므로, 멤버메서드 영역에서는 인스턴스가 
		//자기 자신을 가리킬때, this 레퍼런스 변수를 사용할 수 있으며, 
		//이때 this 조차 생략이 가능
		//레이아웃을 명시하지 않으면 Frame은  BorderLayout을 디폴트로 적용
		this.add(p_north, BorderLayout.NORTH);
		this.add(content);//방위를 명시하지 않으면, 디폴트는 CENTER
		this.add(copyright, BorderLayout.SOUTH);
		this.add(subNavi, BorderLayout.WEST);
		
		//프레임은 기본적으로 눈에 보이지 않는 속성, 크기도 잡혀있지 않다 
		this.setVisible(true);
		this.setSize(500, 400);
	}
	
	public static void main(String[] args) {
		WebSite w = new WebSite();

	}

}
