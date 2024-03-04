package com.sds.project0304.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

/*
 * 자바의 컴포넌트 배치 방법 중 FlowLayout 을 학습한다 
 * FlowLayout 은 수직이나 수평 방향으로 컴포넌트들을 흐르도록 하는 배치방법 
 * */
public class FlowLayoutTest {

	public static void main(String[] args) {
		Frame frame=new Frame(); //프레임 생성 
		
		Button[] btn = new Button[20];
		
		//부착하기 전에 FlowLayout 을 적용하지 않으면 디폴트 배치인 BorderLayout이 적용되어버린다 
		FlowLayout flow=new FlowLayout();
		
		frame.setLayout(flow); //이 시점 부터는 윈도우에 컴포넌트를 부착할때, 
		//수직, 수평으로 흐르면서 부착됨
		for(int i=0;i<btn.length;i++) {
			frame.add(btn[i]=new Button(i+"btn"));
		}
		
		frame.setSize(300,400);
		frame.setVisible(true);

	}

}
