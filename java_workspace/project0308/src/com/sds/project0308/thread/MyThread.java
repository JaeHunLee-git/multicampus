package com.sds.project0308.thread;


//하나의 프로세스 내에서, 독립적으로 실행시키고 싶은 단위인 쓰레드를 
//나만의 로직으로 재정의 해보자
//쓰레드란 하나의 프로세스 내에서의 또 다른 작은 단위의 프로세스로 보자
public class MyThread extends Thread{
								/* is a */
	int count;
	String msg;//출력대상 문자열
	
	public MyThread(String msg) {
		this.msg=msg;
	}
	
	//개발자는 독립적으로 실행시키고 싶은 로직이 있다면 
	//run() 메서드에 작성해야 한다, 개발자가 정의해놓으면, run 메서드 호출은 
	//jvm 이 알아서 호출해준다 
	public void run() {
		while(true) {
			count++;
			System.out.println(msg);
			
			try {
				Thread.sleep(10); //1초 간  non-runnable에 있다가 와라!!(1초 뒤에 자동 복귀)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}

