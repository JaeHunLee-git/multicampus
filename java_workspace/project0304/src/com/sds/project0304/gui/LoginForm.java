package com.sds.project0304.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm {
	
	
	public static void main(String[] args) {
		Frame frame=new Frame();
		
		Panel content=new Panel(); //노란색 
		Panel footer=new Panel(); //녹색 
		
		//각각의 패널에 배경색을 부여해보자 		
		//색상을 적용함에 있어서 RGB 값을 이용하는 방법은 아주 정교하기 때문에 
		//유용하겠지만, 현재 우리로써는 그냥 정교함 보단 노란색을 적용하려면 직관성이 떨어짐
		//개발자는 사람이라서 RGB값을 색상을 구분한다는 것을 힘든 일.. 
		//이미 YELLOW 상수가 COLOR형 상수이기 때문에 반드시 RGB 값만 쓸수있는 것은 아님
		content.setBackground(Color.YELLOW);
		footer.setBackground(Color.GREEN);
		
		//content 패널에 라벨2개와 텍스트필드 2개를 넣자 
		Label la_id = new Label("ID");
		Label la_pass = new Label("Password");
		TextField t_id=new TextField();
		TextField t_pass=new TextField();
		
		//각 컴포넌트들의 스타일 적용
		la_id.setPreferredSize(new Dimension(120, 30));
		t_id.setPreferredSize(new Dimension(120, 30));
		la_pass.setPreferredSize(new Dimension(120, 30));
		t_pass.setPreferredSize(new Dimension(120, 30));
		
		
		//footer에 부착할 버튼 2개 생성 및 스타일 적용 
		Button bt_login = new Button("Login"); //로그인버튼
		Button bt_join = new Button("Join"); //가입 버튼
		
		//생성된 컴포넌트 4개를 content  판자에 자식으로 부착하자 		
		content.add(la_id);
		content.add(t_id);
		content.add(la_pass);
		content.add(t_pass);
		
		//생성된 버튼 2개를 footer 에 자식으로 부착하자 
		//footer는 개발자가 아무런 배치를 적용한 적이 없기 때문에 디폴트가 FlowLayout 이다
		footer.add(bt_login);
		footer.add(bt_join);
		
		//content 는 윈도우의 센터에 부착
		frame.add(content, BorderLayout.CENTER);
		
		//footer는 윈도우의 남쪽에 부착
		frame.add(footer, BorderLayout.SOUTH);
		
		frame.setSize(300, 155);
		frame.setVisible(true);
		
	}
}
