package com.sds.project0308.mouse;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//화면에 등장할 주인공 
public class Hero{
	Toolkit kit=Toolkit.getDefaultToolkit(); //static 메서드로 인스턴스 얻음
	Image image;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	float a=0.1f; //비율계수 
	int targetX; //목표지점
	
	public Hero(String path, int x, int y, int width, int height, int velX, int velY) {
		image = kit.getImage(path);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
	}
	
	//주인공 움직임 
	public void tick() {
		//물리량 변화
		this.x +=this.velX;
		this.y +=this.velY;
	}
	
	//목표지점까지 감속도 운동 
	//나의 위치 = 현나위치 + 비율계수*(목표지점 - 현나위치)
	public void softTick() {
		//this.x = this.x + a*(targetX - this.x);
	}
	
	
	//화면에 그리기 
	//GamePanel 에서 모든 그래픽 처리가 
	//이루어지는 무대이기 때문에 
	//GamePanel 의 Graphics 객체를 얻어와야함
	public void render(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}
}



