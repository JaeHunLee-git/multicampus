package com.sds.project0305.addr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//사용자의 액션이벤트를 감지할 리스너 클래스 정의 
public class BtnListener implements ActionListener{
	FrameA frameA;//이 객체의 인스턴스가 소멸할때까지 살아있슴
	FrameB frameb; //null
	
	//FrameA 를 전달받을 수 있는 생성자를 정의하자 
	//아래의 생성자 메서드를 호출하는 자는 반드시 FrameA 형인
	//인스턴스를 넘겨야 한다..
	public BtnListener(FrameA frameA) {
		this.frameA = frameA;
	}
	
	//사용자가 액션 이벤트를 일으킬때마다 호출되는 메서드
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e);
		
		//이벤트 정보가 들어있는 e 객체에서 원하는 정보를 추출하자, 특히 우리가 필요한건 어떤 버튼이
		//눌렸는지 알아야 한다.. 
		//이벤트 프로그래밍에서, 이벤트를 일으큰 주체가 되는 컴포넌트를 이벤트 소스(source)한다..
		//따라서 우리는  Event Source  를 얻어와야 한다.. 
		
		//getSource() 메서드의 반환형은 Button이 아니라, 자바의 최상위 객체인 Object 가 반환된다. 
		//왜? 사실 action은 버튼만 일으킬 수 있는 이벤트가 아니라, 대부분의 컴포넌들이 일으킬 수 있는 
		//이벤트이다. 따라서 어떤 때는 버튼이 아닌 경우도 있으므로, 반환형을 Button으로 고정할 수 없기때문에
		//보다 유연하고 폭넓은 자료형이 Object 로 줘버린다..이때 개발자가 이 Object 자료형을 자신이 원하는
		//원래의 컴포넌트 자료형으로 알맞게 형변환하여 사용하면 된다.
		//즉 sun 에서는 개발자가 어떤 컴포넌트에  action 이벤트를 연결햇는지 예측 못하기 때문에 
		//보다 폭넓은 자료형인 Object 로 받는다
		Object obj = e.getSource();//이벤트를 일으킨 객체를 반환 
		//Button btn = (Button)obj;//우리는 액션이벤트를 버튼에 연결했기때문에 버튼형으로 바꿔서 사용할 수 있다.
		
		// FrameA의 친구인 FrameB를 생성하자 
		
		//open 버튼을  누르면..
		if(obj == frameA.bt_open) {
			//존재하지 않을때만 즉 친구가 한명도 없을때만..new할 꺼임
			if(frameb == null) { //frameb의 주소값일 비어있을때 즉 친구가 없을때만 생성하겠다..
				frameb = new FrameB();
			}
		}else if(obj == frameA.bt_color[0]) {
			System.out.println("FrameB의 배경색 빨간색을 원하는 군요");
			frameb.setBackground(Color.RED);
		}else if(obj == frameA.bt_color[1]) {
			System.out.println("FrameB의 배경색 주황색을 원하는 군요");
			frameb.setBackground(Color.ORANGE);
		}else if(obj == frameA.bt_color[2]) {
			System.out.println("FrameB의 배경색 노란색을 원하는 군요");
			frameb.setBackground(Color.YELLOW);
		}else if(obj == frameA.bt_color[3]) {
			System.out.println("FrameB의 배경색 초록색을 원하는 군요");
			frameb.setBackground(Color.GREEN);
		}else if(obj == frameA.bt_color[4]) {
			System.out.println("FrameB의 배경색 파란색을 원하는 군요");
			frameb.setBackground(Color.BLUE);
		}else if(obj == frameA.bt_color[5]) {
			System.out.println("FrameB의 배경색 마젠타를 원하는 군요");
			frameb.setBackground(Color.MAGENTA);
		}else if(obj == frameA.bt_color[6]) {
			System.out.println("FrameB의 배경색 회색을 원하는 군요");
			frameb.setBackground(Color.GRAY);
		}

		
	}
	
}
