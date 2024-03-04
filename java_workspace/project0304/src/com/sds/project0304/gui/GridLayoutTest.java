package com.sds.project0304.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;

/*
 * 자바의 배치 방법 중 GridLayout 을 알아보자
 * Grid는 격자를 말하는 것으로써, 행과 열로 컴포넌트를 배치하는 방법을 제공함
 * */
public class GridLayoutTest {

	public static void main(String[] args) {
		Frame frame=new Frame(); //윈도우 생성 
		GridLayout grid=new GridLayout(3, 2); //3층 2호 
		
		frame.setLayout(grid); //원하는 배치관리자 객체
		//바로 이 시점 부터는 Frame의 디폴트 배치관리자인 BorderLayout이 적용되는 게 아니라, 
		//GridLayout 으로 적용됨. 
		Button[] btn = new Button[6];
		
		for(int i=0;i<btn.length;i++) {
			btn[i] =new Button(i+"'st button");
			frame.add( btn[i]); //윈도우에 버튼 부착
		}
		frame.setSize(300,200); //너비 300, 높이 200
		frame.setVisible(true);//윈도우창 보이게
	}

}
