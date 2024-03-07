package com.sds.project0306.graphic;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

/*        현실에서의 그래픽 구성 요소      |     전산에서의 그래픽 구성 요소 
 *       그림의 주체: 사람                                컴포넌트 스스로 
 *       붓으로 그림을 그린다                         paint()  메서드로 그림을 그린다
 *       도화지에 그림을 그린다                      컴포넌트 자신에게 그린다(바디 페인팅)
 *       팔레트로 다양한 효과                        그래픽 전담 객체가 별도 지원 (java - Graphics 객체)
 *       
 *      모든 컴포넌트가 위의 원칙대로 스스로 그림을 그린다..
 *      그 중 JFrame 을 대상으로 증명을 해보자 
 * */
public class GraphicTest extends JFrame{
	MyButton bt;

	Canvas can; //컴포넌트 중 도화지 같은 역할을 하는 컴포넌트...
						//개발자가 적극적으로 그림을 그려야 하는 컴포넌트 이므로,  paint() 메서드를 재정의한다
						//별도의 클래스를 정의해야 함...
	
	
	//실험: 컴포넌트 스스로가 자기 자신을 잘 그리고 있었떤 상황에, 우리가 껴들어서 
	//우리만의 그림을 그려보자 
	public void paint(Graphics g) {
		System.out.println("프레임의 그림을 방해햇슴");
	}
	
	public GraphicTest() {
		bt = new MyButton(); //나의 버튼 생성 
		
		setLayout(new FlowLayout());
		
		add(bt);//프레임에 부착
		
		setSize(600, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GraphicTest();
	}

}
