package com.sds.project0308.mouse;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestWin extends JFrame {
	JButton bt;
	Canvas can;
	
	public TestWin() {
		bt = new JButton("눌러봐");
		can = new Canvas() {
			public void paint(Graphics g) {
				g.fillOval(0, 0, 100, 100);
			}			
		};
		can.setPreferredSize(new Dimension(250, 200));
		setLayout(new FlowLayout());
		add(bt);
		add(can);

		// 버튼과 리스너 연결
		// 클릭이라는 행위를 .java 까지 만들어가면서 구현을 할 필요가 있는가?
		// 이벤트 구현 시 .java 까지 만들지 않고, 내부익명클래스로 많이 구현함..
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("나 눌렀어?");
			}
		});

		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestWin();
	}
}
