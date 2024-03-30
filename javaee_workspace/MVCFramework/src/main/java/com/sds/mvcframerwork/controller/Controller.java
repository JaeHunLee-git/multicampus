package com.sds.mvcframerwork.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*컨트롤러의 최상위 객체를 인터페이스로 정의하는 이유는 ?
 * 이 인터페이스를 상속받는 모든~~ 자식들이 반드시 구현해야 할 메서드를
 * 강제하기 위해...개발의 일관성, 다형성 효과를 볼 수 있다
 * */ 
public interface Controller {
	//모든 자식이 반드시 구현해야할 메서드 기준 세우기 (추상메서드)
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;           
}
