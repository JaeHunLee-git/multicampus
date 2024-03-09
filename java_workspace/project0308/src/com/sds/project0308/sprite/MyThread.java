package com.sds.project0308.sprite;

//현재 프로그램내에서 독립적으로 실행될 수 있는 세부 실행단위인 쓰레드를 정의해본다
public class MyThread extends Thread{
	
	Animation ani; //필요하면 보유하라
	
	public MyThread(Animation ani) {
		this.ani = ani;
	}
	
	//독립적으로 실행시키고 싶은 코드가 있을때 run() 에 작성해야 함 
	@Override
	public void run() {
		while(true) {
			//Animation 클래스가 보유한 move() 메서드 호출
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ani.move();
		}
	}
}
