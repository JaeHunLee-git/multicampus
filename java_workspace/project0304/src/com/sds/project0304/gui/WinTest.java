package com.sds.project0304.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.FlowLayout;
/*너비 500, 높이 600 인 윈도우창을 생성해보자
 * java의 GUI 객체들이 모여 있는 패키지는 java.awt 패키지 이다 
 * */
import java.awt.Frame;
import java.awt.TextField;

public class WinTest {
	
	public static void main(String[] args) {
		/* java gui  에서는 윈도우 객체는 최상위 객체이므로, 직접사용하지 않고 그 하위 객체인 
		 * Frame 객체를 사용함 
		 * */
		
		/*
		 *sun 또는 남이 제공해준 클래스를 사용하는 방법
		  api 를 빨리 보는 방법 
		  
		 1) 현재 클래스의 사용목적을 알아야 함, 즉 뭐하는 클래스인지? 
	     ex) Frame은?  윈도우를 표현한 클래스
	     
	     2) 사용해보기
	         - 사용한다는것은 메모리에 올려야 하는 것이고, 자바에서는 객체를 메모리에 
	            올릴때 3가지 유형에 따라 올리는 방법이 틀리므로, 객체의 유형을 조사 
	            a) 일반클래스 - new 연산자 직접 올릴 수 있다. 
	            b) 추상클래스 - new 연산자 직접 올릴 수 없다.. 
	                                (1) 자식 클래스를 정의하여 자식을 new 
	                                (2) sun 에 별도로 제공해준 방법이 있을 경우 그 방법
	            c) 인터페이스  - new 연산자 직접 올릴 수 없다.. 
	                                (1) 자식 클래스를 정의하여 자식을 new 
	                                (2) sun 에 별도로 제공해준 방법이 있을 경우 그 방법
		 */
		Frame frame=null; //객체의 인스턴스가 존재하지 않는 상태
		
		//프레임을 메모리에 올려보자~~
		frame = new Frame();//일반 클래스이므로 new 로 올릴 수 있다...
		frame.setVisible(true);
		//setSize() 메서드를 이용하여 너비 ,높이를 부여하자 
		frame.setSize(500, 600);
		
		//이 프레임위에, 각종 UI  컴포넌트들(버튼, 텍스트박스..)을 올려보자 
		Button bt=new Button("나버튼"); //처음보는 클래스를 메모리에 올리자 
		
		//버튼이 윈도창 만하게, 크게 붙는 이유는? 
		//모든 GUI 프로그램에서는, 컨테이너인 부모에 자식요소를 붙일때는 반드시 어떤 방식으로
		//붙일지 즉 배치방법인 레이아웃이 결정되어야 한다.
		//만일 개발자가 레이아웃방식을 아무것도 지정하지 않으면 디폴트로 적용되는 배치방법
		//있는데, 우리의 경우 현재 BorderLayout 이라는 배치 방법이 적용되어, 
		//버튼이 대왕만하게 등장하게 됨...이 문제를 해결하려면 다양한 배치방법을 배워야 함 
		//현재는 배치방법을 배우지 않은 상태이므로, FlowLayout 을 적용해봄 
		frame.setLayout(new FlowLayout());
		
		//html에서의 텍스트박스는 JAVA에서는 TextField 라 함 
		TextField t1=new TextField(15);
		
		//html에서의 select 박스는 java에서는 Choice 라 함 
		Choice ch=new Choice();
		ch.add("Java");
		ch.add("Oracle");
		ch.add("JSP");
		ch.add("Spring");
		
		//html에서의 checkbox는 java에서도 checkbox 라 함 
		Checkbox box=new Checkbox("Travel");
		
		
		frame.add(bt);//윈도우창에 버튼부착
		frame.add(t1);//윈도우창에 텍스트 필드 부착
		frame.add(ch);//윈도우창에 초이스 부착 
		frame.add(box);//윈도우창에 체크박스 부착 
		
	}
}

