package com.sds.springapp.aop;

//학생의 하루 일과 중 각 이벤트마다 벨을 울리기 위한 객체 
public class Bell implements Instrument{
	public void sound() {
		System.out.println("♬ 딩동댕 ♬");
	}
}
