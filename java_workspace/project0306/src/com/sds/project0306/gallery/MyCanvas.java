package com.sds.project0306.gallery;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class MyCanvas extends Canvas{
	int index; //몇번째 이미지를 접근해야 할지를 결정짓는 변수
	GalleryMain galleryMain; //필요하면 보유하자 null
	
	String[] filename= {
		"D:\\java_workspace\\project0306\\images\\card\\h1.png",
		"D:\\java_workspace\\project0306\\images\\card\\h2.png",
		"D:\\java_workspace\\project0306\\images\\card\\h3.png",
		"D:\\java_workspace\\project0306\\images\\card\\h4.png",
		"D:\\java_workspace\\project0306\\images\\card\\h5.png",
		"D:\\java_workspace\\project0306\\images\\card\\h6.png",
		"D:\\java_workspace\\project0306\\images\\card\\h7.png",
		"D:\\java_workspace\\project0306\\images\\card\\h8.png",
		"D:\\java_workspace\\project0306\\images\\card\\h9.png",
		"D:\\java_workspace\\project0306\\images\\card\\joker.png"
	};
	
	//이미지 객체 배열 
	Image[] imageArray=new Image[filename.length];//String 배열만큼 배열의 크기 지정
	
	Toolkit kit=Toolkit.getDefaultToolkit(); //
	
	public MyCanvas(GalleryMain galleryMain ) {
		this.galleryMain = galleryMain;//프레임 넘겨받음
		
		for(int i=0;i<imageArray.length;i++) {
			imageArray[i] = kit.getImage(filename[i]);//이미지 생성 후 배열에 넣기
		}
	}
	
	//이전 이미지 보여주기
	public void prev() {
		if(index>=1) { //1까지만 아래의 코드가 실행되게..
			index--;
			repaint();//다시 그리기
		}else {
			//경고 창
			JOptionPane.showMessageDialog(galleryMain , "이미 처음 이미지입니다");
		}
	}
	
	//다음 이미지 보여주기 
	public void next() {
		if(index< imageArray.length-1) { //배열이 10이라면 8까지만...아래의 코드가 동작 
			index++;
			repaint();//다시 그리기
		}else {
			//경고 창
			JOptionPane.showMessageDialog(galleryMain , "다음 이미지가 없습니다");
		}
	}

	@Override
	public void paint(Graphics g) {
		//프로그램 가동과 동시에 0번째 이미지가 그려지게..
		g.drawImage(imageArray[index], 0, 0, 600,550, this);
	}
	
}




