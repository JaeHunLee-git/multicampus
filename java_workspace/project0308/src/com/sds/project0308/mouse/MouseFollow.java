package com.sds.project0308.mouse;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseFollow extends JFrame{
	GamePanel gamePanel;
	
	public MouseFollow() {
		gamePanel = new GamePanel();
		add(gamePanel);
		
		pack(); //내용물까지 만큼 줄어들어
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new MouseFollow();
	}

}
