package com.sds.project0306.painting;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

//그림판을 만들어보자
public class Painter extends JFrame implements MouseMotionListener{
	PaintCanvas can;
	
	/*
	자바에서는 배열이 생성 시 그 크기를 미리 결정해야 하므로, 크기가 동적으로 변하는 프로그램에서는
	사용이 적당하지 않다.. 해결책) 고무줄 처럼 늘어나는 데이터 구조를 활용해야 한다.. 
	자바에서는 collection framework 에서 해결 가능
 	■ 컬렉션 프레임웍이란? 자바의 자료구조 패키지임 
 	  - 객체를 모아서 처리할때 유용한 기능등을 지원하는 패키지 java.util 
 	  - 이 세상의 모든~~ 객체가 모여있는 모습을 단 3가지 관점으로 바라본다 
 	   1) 순서 있게 모여 있는 모습 - List 유형 
 	   2) 순서 없이 모여 있는 모습 - Set 유형 
 	   3) key - value 의 쌍으로 모여있는 모습 - Map 유형
 	   
 	    주의) 컬렉션 프레임웍의 대상이 되는 자료형은 오직 객체 자료형만을 대상으로 함 
 	    
 	    본 예제에서는 List 인터페이스의 자식들 중 ArrayList 를 사용해본다 
 	    ArrayList는 배열과 거의 비슷함 
 	    차임점이라면 다음과 같다
 	    1) 크기가 동적으로 늘어남 
 	    2) 배열은 기본 자료형도 담을 수 있지만, ArrayList은 오직 객체만을 담는다 
 	    3) 배열은 한 종류의 자료형만 담을 수 있지만, ArrayList는 자료형을 섞어서도 담을 수 있다..
	 */
	
	ArrayList list;
	
	public Painter() {
		list = new ArrayList(); //비어 있는 리스트 생성 , 현재는 길이가 0이다
		
		can = new PaintCanvas(this); //캔버스 생성 
		
		add(can);
		
		//캔버스와 리스너 연결 
		can.addMouseMotionListener(this);
		
		setSize(1400, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Painter();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("드래그 중...");
		//마우스의 x좌표와 y좌표 구하기 
		can.x = e.getX();//마우스의 x좌표
		can.y = e.getY();//마우스의 y좌표
		
		int[] pos = {can.x, can.y}; //크기가 2인 정수 배열, 점 1개를 표현 
		//x,y 를 계속 누적해놓고 캔버스의 paint() 메서드에서는
		//점만 하나 그리지 말고, 누적된 모든 점을 그리게 되면 
		//자취를 그리게 되므로 선이 그려진다..자취를 출력 
		//따라서 점들을 모아놓자!!
		
		//고무줄처럼 늘어나는 배열인 list에 담자 
		//js에서의 push()와 동일한 메서드가 add()이다 
		list.add(pos); //자바에서는 배열을 객체로 취급하므로, 담을 수 있다
		
		System.out.println("현재까지 누적된 점의 수는 "+list.size());
		can.repaint(); //그림 갱신 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
