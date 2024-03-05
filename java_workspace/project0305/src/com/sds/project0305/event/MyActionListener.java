package com.sds.project0305.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * 사용자들이 GUI 프로그램에서 이벤트를 일으키면 1차적으로는 OS(mac, window, linux)가 감지하여 
 * 해당 프로그램에 전달해 준다.. 
 * 우리의 경우 JVM이 이벤트 정보를 전달받는데, 이때 jvm은 자바에서 사용되는 이벤트로 재해석하여 
 * 정보를 담고 있는 이벤트 객체의 인스턴스를 생성한다, 예를 들면 클릭이벤트 정보를 os로 전달받으면
 * jvm은 자바에서는 click 대신 'action' 이라는 용어를 사용하므로, ActionEvent 객체를 메모리에 생성하게
 * 된다.. 이때 ActionEvent 객체는 결국 개발자에게 전달되어야, 개발자가 원하는 처리를 진행할 수 있는데,
 * 이렇게 개발자에게 이벤트 객체가 전달되려면 실시간으로 해당 이벤트가 발생할때마다 이를 감지할 수 있는 
 * 역할을 수행하는 객체가 필요한데, 이러한 객체를 가리켜 리스너(Listener)=청취자 라고 한다 
 * 이 리스너를 사용하기위해서는 인터페이스로 되어 있으므로, 자식 객체를 재정의하여 자식을 new 해야 한다..  
 * */

//인터페이스는 몸체없는 메서드만을 보유하므로, 상당히 추상적이다.따라서 추상메서드를 우리가 재정의하여 
//인스턴스를 생성해보자
public class MyActionListener implements ActionListener{

	//이 메서드는 사용자에 의해 액션이벤트가 발생할때마다, 호출되어 진다. 
	//이때, jvm 이 생성한 이벤트 정보 객체인 ActionEvent 인스턴스가 아래의 매개변수인 e 변수로 
	//대입이 되어진다..따라서 개발자는 더많은 이벤트 정보를 보고싶다면 매개변수인 e 를 이용하면 된다..
	public void actionPerformed(ActionEvent e) {
		//액션 이벤트가 발생할때, 개발자가 원하는 내용을 작성하자
		System.out.println(e);		
	}
	

	
}







