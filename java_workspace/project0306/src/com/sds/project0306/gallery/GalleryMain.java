package com.sds.project0306.gallery;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GalleryMain extends JFrame implements ActionListener{
	MyCanvas can;
	JPanel p_south;
	JButton bt_prev, bt_next;
	
	public GalleryMain() {
		can = new MyCanvas(this);
		p_south = new JPanel();
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		
		//style 
		can.setPreferredSize(new Dimension(600, 550));
		
		//부착 
		add(can);
		p_south.add(bt_prev); //패널에 버튼 부착
		p_south.add(bt_next); //패널에 버튼 부착
		add(p_south, BorderLayout.SOUTH);//남쪽에 패널 부착 
		
		//버튼과 리스너 연결 
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
		
	public void actionPerformed(ActionEvent e) {
		//자바에서는 이벤트를 일으킨 컴포넌트를 가리켜 이벤트 소스(Event Source)라 한다.. 
		//따라서 e 변수로부터 누가 눌렸는지를 알아내자 
		JButton obj =(JButton)e.getSource(); //action event 는 버튼에만 한정된 이벤트가 아니므로, 어떤 컴포넌트에 연결이 
		
		//되었는지 알수가 없으므로 Object 형으로 반환된다
		if(obj==bt_prev) {
			//이전 이미지 보여주기
			can.prev();
		}else if(obj==bt_next) {
			//다음 이미지 보여주기
			can.next();
		}
		
	}
	
	public static void main(String[] args) {
		new GalleryMain();		
	}

}
