package com.sds.project0311.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberRegist extends JFrame{
	//서쪽 영역 필요 컴포넌트들 
	JPanel p_west;
	JTextField t_id; //아이디
	JTextField t_phone; //연락처 
	JTextField t_gender; //성별 
	JButton bt;//등록 버튼 
	
	//FruitModel model=new FruitModel(); //JTable 이 보여줄 데이터에 대한 정보를 제공하는 자
	MyTableModel model = new MyTableModel();
	JTable table;
	JScrollPane scroll;
	
	public MemberRegist() {
		p_west = new JPanel();
		t_id = new JTextField();
		t_phone = new JTextField();
		t_gender = new JTextField();
		
		//JTable의 3번째 생성자 방법인 즉 생성자에 이차원배열을 대입하는 방식은, 
		//Table이 보여줄 데이터를 고정하기 때문에, 데이터를 변경한다거나 교체가 불가능함 
		//유지보수성 또 떨어짐 , 예를 들어 과일을 보여주고 싶다면 과일용  JTable 이차원배열을 또 만들어야 함
		//따라서 4번째 생성자를 이용해본다. TableModel 을 이용하면 디자인인(View) JTable과 
		// 그 안에 보여질 데이터(Model)을 분리시켜 개발할 수 있으므로, 추후 다른 종류의 데이터를 보여
		//주어야 한다고 해도, 코드가 크게 바뀔것이 없다.  특히 기존의 JTable 관련 코드 유지가능.. 
		//즉 순수 데이터인 이차원 배열만 바꾸면 된다..
		table = new JTable(model);
		scroll = new JScrollPane(table);
		bt = new JButton("등록");
		
		//스타일 
		p_west.setPreferredSize(new Dimension(150,400));
		p_west.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(135, 45);
		t_id.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_gender.setPreferredSize(d);
		
		
		//조립 
		p_west.add(t_id);
		p_west.add(t_phone);
		p_west.add(t_gender);
		p_west.add(bt);
		
		add(p_west, BorderLayout.WEST);//패널을 서쪽에 부착 
		add(scroll); //센터에 스크롤 부착(테이블 포함된)
		
		//버튼과 리스너 연결 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("나 눌렀어?");
				regist();
			}
		});
		
		//윈도우 설정 
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//회원 추가하기
	public void regist() {
		/* 배열은 처음에 생성 시 그 크기를 정해놓으면, 수정이 불가하다 
		 * 따라서 기존의 data 변수가 가리키고 있던 배열은 버리고 새로운 배열을 생성하여 data에 대입해보자
		 * */
		
		//일차원 배열을 생성하여, ArrayList에 새로운 멤버 추가하기 
		String[] member = {
			t_id.getText(), 
			t_phone.getText(), 
			t_gender.getText()
		};
		
		model.list.add( member);
		System.out.println("현재 누적된 사원수는 "+model.list.size());
		
		//jtable 에 데이터 갱신 
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new MemberRegist();
	}
}





