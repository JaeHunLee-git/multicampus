package com.sds.project0308.mouse;

import java.awt.Canvas;
import java.awt.Graphics;

public class MyCanvas extends Canvas{
	
	@Override
	public void paint(Graphics g) {
		g.fillOval(0, 0, 100, 100);
	}
}
