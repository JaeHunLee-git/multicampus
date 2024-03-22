package com.sds.newsapp.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sds.newsapp.news.News;
import com.sds.newsapp.news.NewsDAO;

//뉴스 기사 게시판 등록 폼 
public class WriteForm extends JFrame{
	JTextField t_title, t_writer;
	JTextArea area;
	JScrollPane scroll;
	JButton bt;
	NewsDAO newsDAO;
	
	public WriteForm() {
		
		//생성 
		t_title = new JTextField();
		t_writer = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt = new JButton("등록");
		newsDAO = new NewsDAO(); //데이터 베이스 연동 객체 생성 
		
		//스타일 
		Dimension d = new Dimension(280, 40);
		
		t_title.setPreferredSize(d);
		t_writer.setPreferredSize(d);
		scroll.setPreferredSize(new Dimension(280, 200));
		
		//부착 
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt);
		
		//버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//뉴스기스 한건을 채워서 매개변수로 전달하자!!
				News news = new News(); //비어 있는 바구니 객체 한개 생성(DTO)
														//더 이상 배열 같은 자료형 쓰지 말고, 객체지향 적으로 살자
				
				news.setTitle(t_title.getText());//제목 채우기
				news.setWriter(t_writer.getText());//작성자 채우기 
				news.setContent(area.getText());//내용 채우기 
				
				int result = newsDAO.insert(news);//채워진 뉴스 전달
				
				if(result>0) {
					JOptionPane.showMessageDialog(WriteForm.this, "등록성공");
				}else {
					JOptionPane.showMessageDialog(WriteForm.this, "등록실패");
				}
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,400);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		new WriteForm();
	}

}
