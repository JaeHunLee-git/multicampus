package com.sds.project0308.mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

//게임의 모든 요소들이 등장하게 될 무대
public class GamePanel extends JPanel{
	public static final int WIDTH=1500; //고정
	public static final int HEIGHT=850; //고정
	Hero hero;
	Thread thread;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		hero = new Hero("D:/java_workspace/project0308/res/android.png", 100, 200, 60, 60, 1, 1);
		
		/*클래스안에 또 다른 클래스를 정의하는 기법을 내부클래스라 하며 
		 *특히 이름이 없는 내부클래스 정의 기법을 내부익명클래스라 한다
		 *클래스의 재사용성이 떨어질때, .java 까지 만들어야 할까?
		 *1) 재사용 가능성이 별로 없는 클래스의 경우는 내부익명으로...많이 씀 주로 이벤트 구현 시
		 *2) 바깥 쪽 외부 클래스의 멤버(변수, 메서드)를 함께 쓰고 싶을때    
		 * 내부 익명클래스 
		 * 
		 * */
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						gameLoop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start(); //쓰레드 가동 시작
	}
	
	public void gameLoop() {
		System.out.println("gameLoop calling...");
		repaint();//update() --> paint() 
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);//그래픽에 사용될 페인트 색 결정 
		g.fillRect(0, 0, WIDTH, HEIGHT); //화면 초기화 
		//나의 g를 주인공에 전달 
		hero.tick();
		hero.render(g);
		//g.drawImage(hero.image, hero.x, hero.y, hero.width, hero.height, this);
	}
}




