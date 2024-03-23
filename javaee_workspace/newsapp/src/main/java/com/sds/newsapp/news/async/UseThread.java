package com.sds.newsapp.news.async;
/*
 * 쓰레드란?
 *  - 하나의 프로세스 내에서 독립적으로 실행되는 세부 실행단위
 *  
 *  자바 언어는 쓰레드 기반의 언어이므로, 사실 메인 실행부조차 쓰레드이다..
 * */
public class UseThread {

	public static void main(String[] args) {
	
		MyThread t = new MyThread();
		t.start();
		
		int[] arr = new int[3];
		arr[0]=5;
		arr[1]=8;
		arr[2]=9;
		arr[3]=1;

	}

}


