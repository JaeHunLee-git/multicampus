package com.sds.project0308.bar;

import javax.swing.JProgressBar;

//바의 값을 증가시킬 쓰레드 정의, .java로 별도로 정의하는 이유는 ? 
//여러개의 인스턴스를 생성하기 위해...
public class BarThread extends Thread{
	JProgressBar bar;
	int step; //몇씩 증가시킬 지 결정짓는 변수 
	int n;
	boolean flag=true;
	
	public BarThread(JProgressBar bar, int step ) {
		this.bar =bar;
		this.step=step;
	}
	
	public void download() {
		n += step;
		bar.setValue(n);
	}
	
	public void run() {
		while(flag) {
			download();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}






