package com.sds.project0306.painting;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

//그림이 그려질 캔버스 정의
public class PaintCanvas extends Canvas{
	int x, y;
	Painter painter; //필요하면 보유하자!! 주소값을 넘겨받자
	//넘겨받는 방법은 메서드를 통해 처리
	
	public PaintCanvas(Painter painter) {
		this.setBackground(Color.YELLOW);
		this.painter = painter; //멤버변수로 보관
	}
	
	@Override
	public void paint(Graphics g) {
		//현재까지 list에 누적된 모든 점들을 다 그리기 
		for(int i=0;i<painter.list.size();i++) {
			//컬렉션 프레임웍에 사물을 넣고, 뺄때는 Object 형으로
			//처리한다..그 이유는 다양한 자료형을 넣거나 뺄수있기 때문에
			
			Object obj = painter.list.get(i);
			int[] pos=(int[])obj;
			
			g.fillOval(pos[0], pos[1], 4, 4); // 채워진 원을 그리자
		}
	}
}
