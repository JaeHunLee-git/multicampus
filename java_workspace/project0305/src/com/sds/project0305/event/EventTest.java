package com.sds.project0305.event;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

//현재 정의하고 있는 클래스를 프레임 자체로 정의해보자 (is a 정의)
public class EventTest extends Frame {
	/* EventTest is a Frame */
	// has a 관계란 , 멤버변수가 객체자료형일때를 의미
	// 어떤 사물이 사물을 부품으로 보유하는 관계
	
	//멤버변수들은 개발자가 초기화하지 않으면 컴파일러에 의해 디폴트값이 들어감 
	//객체자료형의 경우 - null로,  기본자료형 숫자-0, 논리값-false, 문자 - 공백
	Button bt; // EventTest has a Button 버튼을 가지고 있다
	int x; // x는 기본자료형이지, 객체자료형이 아니므로 has a 관계 아니다!
	
	TextField t;
	Choice ch;//html 에서의 select 박스와 동일
	

	public EventTest() {
		bt = new Button("Click Me !");
		t = new TextField(15); //주의 15자만 허용하는 것이 아니라, 15자 크기를 확보하고 탄생
		ch = new Choice(); 
		//초이스 아이템들을 넣어보기  html 에서의 select 의 option과 동일 
		ch.addItem("apple");
		ch.addItem("banana");
		ch.addItem("grape");
		ch.addItem("Lemon");
		
		
		//프레임에 부착 (아무런 배치관리자를 적용하지 않으면 Frame 경우 BorderLayout)
		this.setLayout(new FlowLayout());
		add(bt);//프레임에 버튼 부착
		add(t); //프레임엑 텍스트필드 부착 
		add(ch); //프레임에 초이스 부착 
		
		//버튼에 리스너 연결 js와 상당히 흡사
		MyActionListener my=new MyActionListener();
		bt.addActionListener(my);//리스너 객체의 인스턴스가 매개변수 전달되어야 된다...
		
		//텍스트 필드에 키보드 이벤트 리스너 연결 
		t.addKeyListener(new MyKeyListener());
		
		//초이스에 아이템 변경 리스너 연결 
		ch.addItemListener(new MyItemListener());
		
		//현재 프레임에 윈도우 리스너 연결 
		this.addWindowListener(new MyWndowListener());
		
		this.setBackground(Color.YELLOW);//프레임에 배경색 적용
		setSize(300,400);//윈도우 크기 지정
		setVisible(true); //윈도우 창 보이게 처리
	}

	public static void main(String[] args) {
		//객체 생성후 할일이 없다면, 굳이 변수를 사용할 이유가 없기 때문에 아래와 같이 
		//생성만 했다
		new EventTest();
	}

}
