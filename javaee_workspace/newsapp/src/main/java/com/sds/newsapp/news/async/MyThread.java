package com.sds.newsapp.news.async;

//이 클래스는 독립적으로 실행순서 없이 독자적으로 
//실행할 수 있는 단위이다 
public class MyThread extends Thread{

	@Override
	public void run() {
		for(int i=1;i<=20;i++) {
			System.out.println("A");
		}
	}
}
