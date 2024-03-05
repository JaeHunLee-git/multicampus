package com.sds.project0305.addr;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FrameA extends Frame {
	//버튼들을 보유하자
	Button bt_open; //다른 창을 띄울 버튼 
	Button[] bt_color=new Button[7]; //버튼을 생성한 것이 아니라, 버튼이 들어갈 자리7개 확보 
	Color[] colorArray= {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
			Color.BLUE, Color.MAGENTA, Color.GRAY};
	
	public FrameA() {
		bt_open = new Button("open");
		
		this.setLayout(new FlowLayout());//FlowLayout 으로 전환
		
		add(bt_open); //프레임에 부착 
		
		for(int i=0;i<colorArray.length;i++) {
			bt_color[i] = new Button();//버튼 생성 
			bt_color[i].setBackground(colorArray[i]);//버튼에 색상 적용
			bt_color[i].setPreferredSize(new Dimension(70, 35));
			add(bt_color[i]); //배열의 i번째 버튼을 프레임에 부착 
		}
		
		//열기 버튼과 리스너연결 
		BtnListener btListener = new BtnListener(this);
		bt_open.addActionListener(btListener);
		
		//7개의 버튼과 리스너 연결 
		bt_color[0].addActionListener(btListener);
		bt_color[1].addActionListener(btListener);
		bt_color[2].addActionListener(btListener);
		bt_color[3].addActionListener(btListener);
		bt_color[4].addActionListener(btListener);
		bt_color[5].addActionListener(btListener);
		bt_color[6].addActionListener(btListener);
		
		this.setSize(300,400);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrameA();
	}

}
