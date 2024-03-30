package com.sds.mvcframerwork.gui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sds.mvcframerwork.blood.model.BloodManager;

public class SendForm extends JFrame{
	Choice ch;
	JButton bt;
	BloodManager manager;
	
	public SendForm() {
		ch = new Choice();
		bt = new JButton("판단 요청");
		manager = new BloodManager();
		
		ch.add("A");
		ch.add("B");
		ch.add("AB");
		ch.add("O");
		
		//스타일
		Dimension d = new Dimension(260, 40);
		ch.setPreferredSize(d);
		
		//조립 
		setLayout(new FlowLayout());
		add(ch);
		add(bt);
		
		setSize(300,250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//버튼과 리스너 연결 
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = manager.getAdvice(ch.getSelectedItem());
				JOptionPane.showMessageDialog(SendForm.this , msg);
			}
		});
		
	}
	public static void main(String[] args) {
		new SendForm();
	}

}
