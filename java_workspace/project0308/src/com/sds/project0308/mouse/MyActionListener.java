package com.sds.project0308.mouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//클릭 이벤트를 청취하는 객체 
public class MyActionListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("나 눌렀어?");
	}
}
