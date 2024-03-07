package com.sds.project0306.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPic extends JFrame implements ActionListener{
	MyCanvas can;//그림을 그릴 빈 도화지 객체, 다른 컴포넌트들(예, 버튼, 텍스트필드 등)은 개발자가 그림을 
					//뺏어서 그리면 본연의 디자인을 해치므로, 특별한 이유가 아니면 그림을 뺏어그리지 말아야 하지만, 
					// Canvas  는 오히려 개발자가 적극적으로 그림을 그려야 하는 컴포넌트이다
					//사실  JPanel도 그림을 그리는데 아주 아주 많이 사용함 
	
	JPanel p_south;
	JButton bt;
	JTextField[] txt=new JTextField[4];
	
	public MyPic() {
		can = new MyCanvas();//빈 도화지 생성 
		p_south = new JPanel();
		bt = new JButton("action");
		
		for(int i=0;i<txt.length;i++) {
			txt[i] = new JTextField(5);
			p_south.add(txt[i]); //패널에 부착
		}
		
		//style 부여 
		can.setBackground(Color.YELLOW);
		
		//can.paint();//재정의 아니라 호출, 호출은 시스템이 하는 거임.
		
		//조립 
		add(can); //도화지를 센터에 부착 
		p_south.add(bt); //패널에 버튼 부착 
		add(p_south, BorderLayout.SOUTH);//남쪽에 패널 부착 
		
		//닫기 버튼 누르면, 프로세스 종료하는 메서드 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) 동일
		
		//버튼과 리스너 연결 
		bt.addActionListener(this);
		
		setSize(800,700);
		setVisible(true);
	}
	
	//MyCanvas의 paint() 메서드 안에 있는 선 그리기 좌표를 , 원하는 값으로 변경 후 
	//그림을 다시 그리게 하자
	public void drawLine() {
		can.x1=Integer.parseInt(txt[0].getText());
		can.y1=Integer.parseInt(txt[1].getText());
		can.x2=Integer.parseInt(txt[2].getText());
		can.y2=Integer.parseInt(txt[3].getText());
		
		//켄버스의 값을 위와 같이 변경 한 후, 사용자 행동에 의한 paint() 메서드 호출이 아닌
		//(윈도우창 등의 그림의 변화를 유발하는 행동),  프로그래밍적으로 paint() 메서드를 호출해보겠다
		
		//그림 그리는 행위는 시스템에 의해 컴포넌트가 알아서
		//하고 또 갱신하는 행위 조차도 시스템에 의해서
		//처리되는 것이므로, 개발자는 절대로 paint()메서드를
		//호출해서도 안되고 호출하는 법도 없슴 
		//결론) 시스템으로 하여금 paint() 메서드 호출하게끔 간접적으로 요청
		//repaint() 는 paint() 메서드를 간접적으로 호출하게끔 요청하는 것으로써, 
		//repaint() 호출 -->update() 기존 그림 지우기  --> paint()
		can.repaint(); //다시 그리기 요청
	}
	
	public void actionPerformed(ActionEvent e) {
		drawLine();
	}
	
	public static void main(String[] args) {
		new MyPic();
	}

}
