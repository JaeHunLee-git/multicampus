package com.sds.project0308.mouse;

//게임 루프 역할을 수행할 쓰레드 
public class GameThread extends Thread{
	
	public void gameLoop() {
		//주인공.move();
		//적군의.move();
	}
	
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
}
